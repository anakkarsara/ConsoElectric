# ConsoElectric
A social network app that allows to its users to monitor their power consumption and compare it with their friends'.

### Prepare your environment

First of all, start with cloning the project, then import it in your IDE as a Maven Project.
Next, you need to run ```run-hsqldb-server.bat``` on windows (.sh on linux) in order to start the database.

### How to start your app
You need to start your servlet by running the command ```tomcat7:run```. To do that, if you are on eclipse, you can follow these steps:
```
1- Right click on your projet in the projects explorer
2- run as
3- maven build …
4- put tomcat7:run dans le goal and click Run.
```
The app's homepage is launched on:
```
localhost:9000/index.html.
```
