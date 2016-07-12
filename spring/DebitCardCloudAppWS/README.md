TomcatHelloWorldApp Sample
==============

This project contains a simple Servlet application.

## Building with Maven

This project can be built with [Apache Maven](http://maven.apache.org/). Use the following steps to run the application with Maven:

1. Execute full Maven build:
    ```bash
    $ mvn clean install
    ```

2. To push the application to Bluemix using the cf command line tool:
    ```bash
    $ cf push <appname> -p target/TomcatHelloWorldApp.war
    ```

# Notice

© Copyright IBM Corporation 2016.

# License

```text
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
````
