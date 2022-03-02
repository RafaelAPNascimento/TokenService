Simulating a token service at localhost:8181/api/token/issue. This endpoint receives a credential and returns a user token if the credential are valid. The credentials are valid if the password matches the username in uppercase (see the tests).

The token service component works with a random delay between 0 and 5000 milliseconds. We use a thread pool to show how this can significantly improve the throughput (see the Server.java class). We also use a jmeter performance test to prove that (check /src/test/resources/TokenServiceTest.jmx)  

For example: With 200 users, when the pool has only 1 thread, the request throughput is 11/min. If we increase the pool to 8 threads, we get a request throughput of 1.5/sec (the perfomance may vary depending how many cors your cpu has)  

Build:
 
    mvn clean package
 
 Run:
    
    mvn exec:java
 
 
 Integration Tests (**when application is up and running**):
 
    mvn integration-test
    
    
 throughput = task / time unit    
 Throughput matters when we have a concurrent flow of tasks and we want to perform as many tasks as possible as fast as possible. In this case, throughput is the right performance metrics.
 Thread Pooling is one of the techniques to improve througput.
     