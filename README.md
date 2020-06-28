# Benchmarking Serializers

This project was created in order to evaluate different serialization protocols with respect to (de-)serialization speed and encoding size. It was conducted in the context of the [dCache](https://www.dcache.org/) project, aiming to find a suitable replacement for *Java Object Serialization*, which dCache used for encoding messages sent among microservices.

The following serializers are tested:

- [Java Object Serialization](https://docs.oracle.com/javase/8/docs/technotes/guides/serialization/index.html)
- [Apache Avro](https://avro.apache.org/) (JSON and binary formats)
- [fast-serialization](https://github.com/RuedigerMoeller/fast-serialization)
- [Hessian](http://hessian.caucho.com/doc/hessian-overview.xtp)
- [Kryo](https://github.com/EsotericSoftware/kryo)
- [Protocol Buffers](https://github.com/protocolbuffers/protobuf)
- [Protostuff](https://protostuff.github.io/)


### Testing Concepts for Generalizability

In order to be able to generalize the results of benchmarking individual objects, different sets of classes were created that focus on a certain aspect of parameter changes in order to evaluate their impact. These are the following:

| Class Group | Description  |
| :----       | :---         |
| type-list   | Lists of type String, Integer, Double filled with pre-generated value lists of sizes 10, 100, 500, 1000, 10000, 100000|
| composite   | Different objects composed of each other and primitive/complex types for testing different aspects that are problematic for certain serializers |
| dcache-like | Representative message class 'PoolManagerPoolUpMessage' from dCache |

The validity of the built serializers for all protocols are tested via JUnit tests that can be executed in one go by running the `TestSuiteSerialization` in the test folder.

### Encoding Size

In order to generate JSON files that contain information on the encoding sizes when serializing the messages mentioned above with the supported serializers, the main method in class `benchmarking.size.SerSizer` needs to be executed. The result files are written to `MsgEncoding/jmh-results/sersizes`.

### (De-)Serialization Speed

In order to measure the (de-)serialization speed of different serializers, this project uses the [JMH](https://openjdk.java.net/projects/code-tools/jmh/) harness for benchmarking code written in languages targeting the JVM.

To start benchmarking, the main method in class `benchmarking.runtime.BenchmarkRunner` needs to be called. It takes command line arguments for selecting the serialization type, the path to which the output files should be written and optionally the name of the result files, which are always prepended with the current date. By default, a dummy serializer is executed, the results are written to the `jmh-results` folder.

Although it may be executed from an IDE directly, it is best to build a jar file fist, which can then be executed via command line in a controlled environment.

These are the supported command line arguments:
```
 -t| --type <type>       |  <type> is [kryo-X|fst-X|hessian-X
                         |            |avro-X|avrojson-X
                         |            |proto-X|protostuff-X
                         |            |java-X|dcachey|dummy],
                         |        X = [testobj|container]
 -o| --out  <out-path>   |  path to output files
[-n| --name <out-name>]  |  name of result files
```

where *testobj* refers to the *composite* class group, *container* to the *type-list* one and *dcachey* to the *dcache-like* group.

Under the folder `deploy`, a [Singularity](https://singularity.lbl.gov/) container build file can be found, which accepts a jar file of the *MsgEncoding* benchmarking project and allows for a more controlled environment when deploying and executing benchmarkers. The script `start-all-benchmarkers.sh` takes such a built singularity image as well as a configuration file with a single benchmarker configuration per line:

`<unique benchmarker name> <serialization protocol> <result directory>`

It then creates and starts a benchmarker container for each of them. It is usually advisable to start a single benchmarker in parallel. However, the script allows to bind each benchmarker to a single CPU by using cgroups when started as user *root*, which allows to have reasonable encapsulation and minimize interference among several benchmarker processes.

### Prerequisites

This project was tested using Java 8. In order to use it, a jar file of the Hessian serializer (originally version 4.0.60) needs to be downloaded and linked to.