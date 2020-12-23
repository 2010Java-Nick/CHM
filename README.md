# Covid Human Matcher (CHM)

# Project Description
The Covid Human Matcher (CHM) allows for users to connect with other users based on shared interests. All users will pose a question to new matches that will need to be answered before the match is confirmed. Once this question is answered, both users will have the ability to chat. They will have the ability to send various types of multimedia over messages depending on user type as well. They will have the ability to send various types of multimedia over messages depending on user type as well. We also wanted to give users more control over who they match with and who they maintain connections with. One button press should prevent any further contact with a given match.

## Technologies Used

* Java built web application deployed on an Azure VM with a Jenkins CI/CD pipeline.
* Utilizes the Spring Framework for infrastructure.
* Implements Hibernate ORM to connect to a PostgreSQL database.
* Extended Spring MVC module for a MVC design pattern. 
* Angular framework for front-end UI.
* Bootstrap CDN
* Leveraged Spring AOP / AspectJ for aspect based logging.
* Junit and Mockito for dependency based server-side unit tests.
* Java Web Tokens(JWT)

## Features

* Login/Create account and reveive a JWT
* Setup profile with interests and icebreaker questsion
* View current profile information
* View potential matches with other users based on interests
* Insert/Update payment information
* Update profile with new information
* Validate input from user before sending to backend
* Logout of account

To-do list:

* Prevent site access when not logged in and redirect to login page
* Message other users and start a chat
* Add additional match verification and blocking
* Improve testing coverage over entire backend and frontend
* Improve match view layout with Bootstrap and Angular Material


## Set Up (Local)
   
- Clone repo
- Pull all dependencies with gradle and npm
- Connect backend to database by configuring environment variables
- Start Tomcat server using preferred IDE
- Serve Angular application using ng serve

## Usage

- Navigate to http://localhost:'port'/ to be redirected to the login page.
