# docker-spring-boot-quickdev
Sample project that enables Spring Boot quick reload on multiple projects in a docker-compose suite of services

The purpose of this project is to demonstrate how to use the Spring Boot LiveReload feature in conjunction with a docker-compose suite of services.  The project is simple: a Maven project with three sub-modules.  Two modules are web services using Spring Boot 2.  The third is a library containing shared classes.  Accessing the endpoint of A will result in a message containing a response from A and also a response from B (via reactive webflux web client).  This is to demonstrate how a change in B can be live-reloaded and A can pick up the change.

To build the project, or a module, from the command line:
- mvn clean install

To run each module from the command line:
- mvn spring-boot:run

The real magic here is in the spring-boot-devtools dependency, and how we've configured our docker images to take advantage of this.  This enables the live reload feature.  To take full advantage of this, you'll want to load all of this up in your favorite IDE.  Here, I've used IntelliJ with the Docker plugin installed.

To run via IntelliJ:
- Go to "Run" -> "Edit Configurations" and create a new run profile of type "Docker" -> "docker-compose".  Point this at the docker-compose.yaml file in the root directory.
- Next, run this configuration.  You'll see two containers get deployed: "dqdt-a" and "dqdt-b".  

Once you're up and running, you can point a browser at localhost:7580 and you should see a JSON response containing a message from A and a message from B.

Next, try making changes:
- in dqdt-a, make a modification to the message that's returned.  Once you're happy with your changes, hit Command-F9 to build.  This causes Spring Boot to detect the changes and quickly redeploy the application.  You can see this happen in the logs; to view simply run "docker-compose logs -f dqdt-a" from the root of this project, or view the logs in IntelliJ directly.
- in dqdt-b, make a change to the message.  Do the same steps as above.  Reload localhost:7580 to see the changes.

You could take this a step further and have different docker-compose files, one for development (like this one), and one for production that builds a real image (skips all the mvnw stuff).

Happy efficient coding!
