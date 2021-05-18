# Work In Progress: Service aggregating events  
  
## Brief Description  
This website allows the organizers to enter events and collect entries.  
Every registered user can sign up and search for events as also to leave comments.  
Unregistered users are able to see events and their description.  
  
_Project is build using Spring Boot REST, Angular, Sprig Boot Security(using JWT), ~~JPA (Hibernate)~~ , MongoDB._
  
## Main system functions  
* User registration and login.  
* Events created and edited by organizers (user with special role).  
* Commenting on events by userswho are logged in.  
* Signing up for events.  
* Event search engine.  
  
## Used technology  
* Spring Boot
* JPA (Hibernate)  
* Angular as a view layer
* Spring Security using JWT  
  
## Frontend  
Frontend is running on **port 8080** together with backend.  
If you want to run it seperatly on **port 4200**, then you should  
navigate to frontend _(Angular)_ root folder and run:  
```
npm install
```  
When node packeges are installed serve Angular on port 4200 with  
```
ng serve
```  
## Backend  
Backend is working on **port 8080**  
To run spring boot use:  
```
mvn springboot:run
```
