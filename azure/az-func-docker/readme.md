# How to create azure function in java to run on docker

TUTORIAL: https://medium.com/swlh/azure-functions-docker-java-135c827593e9

1. Execute the below command to create the project
mvn archetype:generate -DarchetypeGroupId=com.microsoft.azure -DarchetypeArtifactId=azure-functions-archetype -Ddocker
2. Run docker application
3. Go to the folder and execute the following command to build and deploy on docker
docker build -t az-functions-java:1.0 .
4. Run azure function
docker run -p 8080:80 az-functions-java:1.0
5. Hit the following URL to test
http://localhost:8080/api/HttpExample?name=ashish