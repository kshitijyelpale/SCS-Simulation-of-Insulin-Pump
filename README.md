# Software Simulation of an Insulin and Glucagon Pump

## Project Details:
This project was carried out under a module, Safety Critical Systems, in the Master's Course, High Integrity Systems at Frankfurt University of Applied Sciences. Please find the project report [here.](Documentation/SCS%20Project%20Report%20-%20Group%20E.pdf)

## Project Team Members: 
Saﬁr Mohammad Shaikh, Kshitij Yelpale, Faiz Usmani, Shubham Girdhar, Asad Ahmed

## Technologies Used:
* Language: JavaScript
* FrontEnd: Angular JS, HTML5, CSS3, JQUERY
* BackEnd: Spring Boot
* Middleware Framework: JHipster
* Documentation: Latex

## How to Run?
1. Clone the Repository:
```sh
git clone https://github.com/Safir-Mohammad-Mustak-Shaikh/Custody-Transfer-using-Hyperledger-Fabric.git
```
2. Import the project in Eclipse (using pom.xml)

3. Open the Terminal (Current Project Path) and run:
```sh
mvn spring-boot:run
```
6. Now, the app is running on port 8080.

## More Details:

* 'inglpump-0.0.1-SNAPSHOT.jar' is the project for checking out, just need to make sure mysql 5.6 or above should be running with username 'root' and no password(empty) 

* Sources code is in 'inProject' directory

* Project's final documentation report is in Documentation folder named "SCS Project Report - Group E.pdf". 

## Project scope:

This project is developed as part of the module Safety Critical Systems. Safety Critical Systems have a direct impact on life, human health and the environment. Medical instruments are certainly Safety Critical. The intent here is to study and replicate the human body’s blood glucose behaviour. Then simulate an Insulin-Glucagon pump that works autonomously and injects the required hormone to make sure that the blood glucose levels remain in safe range. The pump should be able to select and calculate the quantities of the hormone that it has to be injected. The safety and security aspects like alert messages, email and user authentication are thought off and handled. The system is designed as a web application. User interface for the system is from the perspective of a patient. Although various other user perspectives could be implemented (like doctor, nurse etc), due to overrunning cost estimates, currently the functionalities are limited to patient only.
