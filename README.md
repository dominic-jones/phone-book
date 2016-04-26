##PhoneBook example application

Application elements have been chosen here to run in memory in a simple way.

To run the application, run 
`./go.sh` 
or 
`mvn clean compile spring-boot:run`

###Application
Spring Boot was used here to stand up a full app with little configuration. It is not a technology I have used much
before, but it was useful and quick here.

RestAssured is a library I had never used before, but has a nice fluent interface for testing that I learned with this
project. Examples of this can be seen in PhoneBookControllerIT.

Persistence is hsqldb embedded, running in memory only.
Sample data is populated on start-up.

###Constraints
For validation for my parameters, I went with loose validation only. I required names and numbers to be non-empty only.
Phone numbers can be varied depending on countries, and a user might have numbers from multiple countries in their 
phone book. The number of use cases around what codes / country codes / area codes a number might contain is very large,
and Google Contacts apps, for example, allow very loose validation to prevent falsely ruling numbers invalid.