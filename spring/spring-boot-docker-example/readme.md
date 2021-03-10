# Command to build docker image
1. Have Dockerfile in the project directory with the set of commands
2. Go to the directory
a. Execute 
   docker login
   gradlew.bat clean assemble
   docker build -t ashismo/spring-boot-docker .
   docker run -p 8070:8060 ashismo/spring-boot-docker    -- 8060 port is mapped to 8080