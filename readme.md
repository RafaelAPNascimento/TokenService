Simulating a token service at localhost:8181/api/token/issue. This endpoint receives a credential and returns a user token if the credential are valid. The credentials are valid if the password matches the username in uppercase (see the tests).

The token service component works with a random delay between 0 and 5000 milliseconds. We use a thread pool to show how this can increase the throughput significantly (see the Server.java class). We also use a jmeter performance test to prove that (check /src/test/resources/TokenServiceTest.jmx)  

Build:
 
    mvn clean package
 
 Run:
    
    mvn exec:java
 
 
 Integration Tests (**when application is up and running**):
 
    mvn integration-test
    
     