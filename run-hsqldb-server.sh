mvn dependency:copy-dependencies
cd data
java -cp ../dependency/hsqldb-2.3.3.jar org.hsqldb.Server
