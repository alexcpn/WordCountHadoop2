This is basically the WordCount example with a simple maven setup for Hadoop 2, since most of the tutorials are with Hadoop 1.0 and I found there are small changes with libs and usage of the API's in Hadoop 2. Also this has the settings for running Junit test cases with MRUnit which basically helped me in understanding more about Map Reduce

THis is pretty old; If you need to use it to test SONAR

```
/coding/WordCountHadoop2$ mvn clean verify sonar:sonar 
\ -Dsonar.projectKey=test 
\ -Dsonar.host.url=http://localhost:9000 
\ -Dsonar.login=<token>
```