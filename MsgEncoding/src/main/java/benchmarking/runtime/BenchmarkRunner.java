package benchmarking.runtime;

import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BenchmarkRunner {

    public static void main(String[] args) throws RunnerException {
        System.out.println("Welcome to the serialization benchmarker.");
        printHelp();

        String simpleClassName = DummyBenchmarker.class.getSimpleName();
        String fileName = "benchmarker-dummy";
        String pathToResults = "jmh-results/";

        if (args.length >= 2) {
            String arg00 = args[0].toLowerCase();
            String arg01 = args[1].toLowerCase();
            if (arg00.equals("-t") || arg00.equals("--type") ) {

                if (arg01.equals("dcachey")) {
                    simpleClassName = GeneralDcacheyBenchmarker.class.getSimpleName();
                    fileName = "dcachey-pmpum-" + fileName;
                }

                // Protobuf
                else if (arg01.equals("proto-testobj")) {
                    simpleClassName = ProtoTestobjBenchmarker.class.getSimpleName();
                    fileName = "proto-testobj-" + fileName;
                }
                else if (arg01.equals("proto-container")) {
                    simpleClassName = ProtoContainerBenchmarker.class.getSimpleName();
                    fileName = "proto-container-" + fileName;
                }

                // Protostuff
                else if (arg01.equals("protostuff-testobj")) {
                    simpleClassName = ProtostuffTestobjBenchmarker.class.getSimpleName();
                    fileName = "protostuff-testobj-" + fileName;
                }
                else if (arg01.equals("protostuff-container")) {
                    simpleClassName = ProtoStuffContainerBenchmarker.class.getSimpleName();
                    fileName = "protostuff-container-" + fileName;
                }

                // AvroBinary
                else if (arg01.equals("avro-testobj")) {
                    simpleClassName = AvroTestobjBenchmarker.class.getSimpleName();
                    fileName = "avro-testobj-" + fileName;
                }
                else if (arg01.equals("avro-container")) {
                    simpleClassName = AvroContainerBenchmarker.class.getSimpleName();
                    fileName = "avro-container-" + fileName;
                }

                // AvroJSON
                else if (arg01.equals("avrojson-testobj")) {
                    simpleClassName = AvroJsonTestobjBenchmarker.class.getSimpleName();
                    fileName = "avro-json-testobj-" + fileName;
                }
                else if (arg01.equals("avrojson-container")) {
                    simpleClassName = AvroJsonContainerBenchmarker.class.getSimpleName();
                    fileName = "avro-json-container-" + fileName;
                }

                // Hessian
                else if (arg01.equals("hessian-testobj")) {
                    simpleClassName = HessianTestobjBenchmarker.class.getSimpleName();
                    fileName = "hessian-testobj-" + fileName;
                }
                else if (arg01.equals("hessian-container")) {
                    simpleClassName = HessianContainerBenchmarker.class.getSimpleName();
                    fileName = "hessian-container-" + fileName;
                }

                // Kryo
                else if (arg01.equals("kryo-testobj")) {
                    simpleClassName = KryoTestobjBenchmarker.class.getSimpleName();
                    fileName = "kryo-testobj-" + fileName;
                }
                else if (arg01.equals("kryo-container")) {
                    simpleClassName = KryoContainerBenchmarker.class.getSimpleName();
                    fileName = "kryo-container-" + fileName;
                }

                // Fst
                else if (arg01.equals("fst-testobj")) {
                    simpleClassName = FstTestobjBenchmarker.class.getSimpleName();
                    fileName = "fst-testobj-" + fileName;
                }
                else if (arg01.equals("fst-container")) {
                    simpleClassName = FstContainerBenchmarker.class.getSimpleName();
                    fileName = "fst-container-" + fileName;
                }

                // Java
                else if (arg01.equals("java-testobj")) {
                    simpleClassName = JavaTestobjBenchmarker.class.getSimpleName();
                    fileName = "java-testobj-" + fileName;
                }
                else if (arg01.equals("java-container")) {
                    simpleClassName = JavaContainerBenchmarker.class.getSimpleName();
                    fileName = "java-container-" + fileName;
                }

                else { // default: dummy
                    simpleClassName = DummyBenchmarker.class.getSimpleName();
                    fileName = "dummy-" + fileName;

                    if (!arg01.equals("dummy")) {
                        System.out.println("No valid serialization protocol specified. Defaulting to Dummy-Serializer.");
                    }
                }
            }
        }

        if (args.length >= 4) {
            String arg02 = args[2].toLowerCase();
            String arg03 = args[3];
            if ((arg02.equals("-p") || arg02.equals("-o") || arg02.equals("--out"))
                    && isValidPath(arg03)) {
                pathToResults = arg03;
                pathToResults += !pathToResults.endsWith("/") ? "/" : ""; // add slash to end if not exists
            }
        }

        if (args.length >= 6) {
            String arg04 = args[4].toLowerCase();
            String arg05 = args[5];
            if (arg04.equals("-n") || arg04.equals("--name") ) {
                fileName = arg05;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String date = sdf.format( Calendar.getInstance().getTime() );

        Options opt = new OptionsBuilder()
                .include(simpleClassName)
                .resultFormat(ResultFormatType.JSON)
                .result(pathToResults + date + "-" + fileName + ".json")
                .output(pathToResults + date + "-" + fileName + ".log")
                .threads(1)
                .build();

        new Runner(opt).run();
    }

    private static void printHelp() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Usage:");
        System.out.println(" -t| --type <type>       |  <type> is [kryo-X|fst-X|hessian-X");
        System.out.println("                         |            |avro-X|avrojson-X");
        System.out.println("                         |            |proto-X|protostuff-X");
        System.out.println("                         |            |java-X|dcachey|dummy],");
        System.out.println("                         |        X = [testobj|container]");
        System.out.println(" -o| --out  <out-path>   |  path to output files");
        System.out.println("[-n| --name <out-name>]  |  name of result files");
        System.out.println("------------------------------------------------------------");
    }

    private static boolean isValidPath(String pathstring) {
        return pathstring.matches(".*[/\n\r\t\0\f`?*\\<>|\":].*");
    }

}
