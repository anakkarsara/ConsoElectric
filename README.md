# ConsoElectric
  A social network app that allows to its users to monitor their power consumption and compare it with their friends'.
 
 ### Prepare your environment
 
 First of all, start with cloning the project, then import it in your IDE as a Maven Project.
 Next, you need to run ```run-hsqldb-server.bat``` on windows (.sh on linux) in order to start the database.
 
 ### How to start your app
 You need to start your servlet by running the command ```tomcat7:run```. To do that, if you are on eclipse, you can follow these steps:
 ```
- Right click on your projet in the projects explorer
 - run as
 - maven build …
 - put tomcat7:run dans le goal and click Run.
 ```
 The app's homepage is launched on:
 ```
 localhost:9002/index.html.
 ```
 ## Api services' URIs
 
 Resource used : ```application/json```
 Base URI : ```localhost:9002/rest/```
 
 
 | Method     | URL | Action   |
 | :------- | ----: | :---: |
 | GET    | /person  |  display all the people   |
 | GET    | /person/{id}  |  display one specific person with the given {id} parameter   |
 | POST    | /person  |  create a new person   |
 | DELETE    | /person/{id}  |  delete one specific person with the given {id} parameter   |
 | GET    | /home  |  display all the homes   |
 | GET    | /home/{id}  |  display one specific home with the given {id} parameter   |
 | DELETE    | /home/{id}  |  delete one specific home with the given {id} parameter   |
