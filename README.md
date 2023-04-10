# WordCount
## General Information
### Description

<p> This is a word count assessment program. <br>
The goal of this program is to determine the frequency that words appear in  the given text. </p>

### Ticket Tracking Standards
<p>For the purpose of planning, tracking and version control of work, we are using Jira and Github. The following standards need to be kept by when making any changes:</p>

1) Any work needs a sized Jira ticket. <br> Please see: https://heinrich-personal.atlassian.net/jira/software/projects/OA/boards/1
2) Any work needs to be done in a dedicated branch in Github.  <br> Please see: https://github.com/HBurgers/word-count-assessment
3) Branches in Github needs to be named according to the following rules
   1) type/ticketNumber description
      1) Type describes the type of work that needs to be done. Examples are feature and bugfix
      2) TicketNumber is the ticket number in Jira. For example, OA-1
      3) Description is the description of what is being done in that branch.
   2) Example of correctly named branches are: "feature/OA-1 Implement integration tests" and "bugfix/OA-3 Fix NullpointerException in controller"
4) When creating a PR:
   1) Provide screenshots as proof of work, where possible
   2) Add a link to the ticket in the description
   3) You will need a review and approval from a code owner before you can merge any changes.
5) After creating a PR, place a link to the PR in the related ticket in Jira.
6) Add screenshots as proof of work in the Jira ticket.
7) After merging a PR, delete the branch if it won't be used anymore. Also move the ticket to "Done" if you meet the definition of done.


### Technologies and Frameworks

1) JDK Version: Oracle OpenJDK 19.0.2
2) Spring Boot Version: 3.0.5
3) Maven
4) JUnit 5
5) Docker

Please see the pom.xml file for more detail.

## Running the system

<p>There are two main ways that you can run the system.
The first being by running the jar and second being by starting up the docker image.
</p>

### Running the jar

In order to run this application from its jar follow the below steps
1) Navigate to the project root folder in a terminal.
2) Run the following command: "mvn clean install"
3) Followed by "java -jar target/word-count-0.0.1-SNAPSHOT.jar"


### Running the docker container

<p>In order to run this application f rom its container follow the below steps<br>
Not that it is required to have docker installed on your computer.</p>

1) Navigate to the project root folder in a terminal.
2) Run the following command: "mvn clean install"
3) Then build the image by running: "docker build --tag=word-count:latest ."
4) Lastly, run the following command to start up the container: "docker run -p8080:8080 word-count:latest"
   1) Not that if port 8080 is in use in your system, you can change the port it uses on your computer by changing the first occurance of 8080 in the above command.

<p>After successfully starting the system, you can access all the endpoints under "localhost:8080", unless you specified a different port. </p> 

### Endpoints

<p>There are 3 endpoints in this system. <br>
Please replace 8080 with your chosen port, TEXT with the text you would like to search and WORD with the word you would like to know the frequency of. 
</p> 


1) localhost:8080/v1/word/highest/frequency?text=TEXT
   1) This endpoint will return the frequency of the most frequent word in the text provided.
   2) TEXT needs to be replaced with the text that needs to be searched.
   3) This endpoint will return a single integer value indicating the highest frequency.
2) localhost:8080/v1/word/frequency?text=TEXT&word=WORD
   1) This endpoint will return the frequency that the provided word occurs in the provided text.
   2) TEXT is the text that needs to be searched. 
   3) WORD is the word that needs to be searched for.
   4) This endpoint will return a single integer value indicating the requested frequency.
3) localhost:8080/v1/word/nth/frequency?text=TEXT&n=N
   1) This endpoint determines and returns a list of the n'th most frequent words.
   2) TEXT is the text that needs to be searched.
   3) N is an <b>integer value</b> indicating how many results should be returned.
   4) The list of words are in the form of a list of WordFrequency objects. They are ordered by frequency, highest to lowest, and then alphabetically ordered. 

Swagger Document containing detailed descriptions of each endpoint is located at: http://localhost:8080/swagger-ui.html <br>
Remember to change 8080 to your chosen port, if it has been changed.

<p>Also note that a postman collection has been added to this project, located under: "src/test/resources/postman/WordFrequencyAssessment.postman_collection.json" <br>
Note that this is only a basic collection containing three example. Take note that the variables for "hostName" and "port" default to "localhost" and "8080" respectively.</p>

### Testing
<p>The tests implemented in the system has been divided into two main categories:</p>

1) Unit Tests
2) Integration Tests

#### Unit Tests

<p>There are a total of 40 unit tests in the system. They are located under "src/test/java/com/assessment/ordina/wordcount/unit". <br> 
Since the system currently only consists of a single service and no database access, it wasn't needed to add many mocking. Thus, they were only written in JUnit 5.</p>

#### Integration Tests

<p>
This system has 10 integration tests that are ran via the controller. As stated under the "Unit Tests" section, the system only consists of a single service. There were thus no interaction between services to test. There was, however, interaction between the controller and the service to test.
</p>
<p>Integration tests in the system were implemented as Contract tests. In these tests, a contract is defined for each endpoint specifying the endpoint, input and expected results.
Tests are then generated from provided contracts.
</p>
<p>The contracts are located at the following location: "src/test/resources/contracts" and the generated tests will be under the following location after a clean install has been performed: "target/generated-test-sources/contracts/com/assessment/ordina/wordcount/contract".</p>

#### Code Coverage

<p>For this system, I used Jacoco for code coverage. After tests have been run, a code coverage report is generated by Jacoco. This report is located at: "target/site/jacoco/index.html"</p>
<p>Current code coverage as on 10/04/2023 is 97% with a 100% cover for both service and model implementations.</p>




