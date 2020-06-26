#!/bin/bash

# Fancy colors
RED='\033[0;31m'
BLUE='\033[0;34m'
PURPLE='\033[0;35m'
CYAN='\033[0;36m'
NC='\033[0m' # No Color


if [[ "$1" == "help" || "$1" == "-h" || "$1" == "--help" ]]
    then
    echo "----------------------------------------------------------------------"
    echo "Starts multiple benchmarkers, each running in a Singularity container."
    echo ""
    echo "The benchmarkers are described in a config file, in which"
    echo "each line describes a benchmarker configuration:"
    echo "<unique benchmarker name> <serialization protocol> <result directory>"
    echo "with <serialization protocol> := [kryo-X|fst-X|hessian-X"
    echo "                                 |avro-X|avrojson-X"
    echo "                                 |proto-X|java-X|dummy"
    echo "                                 |testobj|container]"
    echo ""
    echo "Usage:"
    echo "    param 1:  <benchmarker container>"
    echo "    param 2:  <config file>"
    echo "----------------------------------------------------------------------"
    exit 0
fi

containerpath=$1
configfile=$2

if [[ "$containerpath" == "" || "$configfile" == "" ]]
  then
    echo -e "${RED}ERROR:${NC}   Not enough parameters provided."
    exit 1
fi

startedasroot=1
if [[ $EUID -ne 0 ]]; then
    echo -e "${RED}WARNING:${NC} Start script as root in order to run each benchmarker on a separate CPU."
    startedasroot=0
else
    echo -e "${BLUE}INFO:${NC}    Started script as root, running each benchmarker on a separate CPU."
fi

cpus_available=$(( $(lscpu | awk '/^Socket\(s\)/{ print $2 }') * $(lscpu | awk '/^Core\(s\) per socket/{ print $4 }') ))

counter=0
while read p; do
    if [[ $startedasroot == 1 && $counter -gt $cpus_available ]]
        then
        echo -e "${RED}ERROR:${NC}   Not enough CPUs available for starting another benchmarker. ($cpus_available CPUs available) Exiting script."
        exit 1
    fi

    paramarray=($p)
    bmname=${paramarray[0]//\"}
    serprotocol=${paramarray[1]//\"}
    resultdir=${paramarray[2]//\"}
    echo -e "${BLUE}INFO:${NC}    Started benchmarker '$bmname' | type: $serprotocol | result dir: $resultdir"
    mkdir -p $resultdir

    if [[ $startedasroot == 1 ]]
        then
        # Create cgroups file for limiting container to specific cpu
        echo "[cpu]" > "${PWD}/cgroups${counter}.toml"
        echo "    cpus = \"$counter\"" >> "${PWD}/cgroups${counter}.toml"

        # Create container
        export SINGULARITYENV_BM_NAME=$bmname && export SINGULARITYENV_SER_PROTOCOL=$serprotocol && singularity run --apply-cgroups $PWD/cgroups$counter.toml --contain --bind $resultdir/:/results/ $containerpath $bmname > /dev/null 2>&1 &

        # Remove cgroups file for created container
        #rm "${PWD}/cgroups${counter}.toml"

        ((counter+=1))
    else
        export SINGULARITYENV_BM_NAME=$bmname && export SINGULARITYENV_SER_PROTOCOL=$serprotocol && singularity run --contain --bind $resultdir/:/results/ $containerpath $bmname > /dev/null 2>&1 &
    fi

done < $configfile
