# PA165 - SportsClub

###Course: 
PA165 Vývoj programových systémů v jazyce Java
###Project Topic: 
Sports Club
###Brief:
Think of a sports club for a team sport (football, ice hockey etc.). 
Create a system managing teams and players. Each team is defined by its 
name and an age group (men, juniors etc.) given by an interval between 
two years of birth. Each player is described by his first name, last name, 
height, weight and date of birth. Each team has a roster of players listing 
their jersey numbers on the team. It is important that each player's age 
group matches the age group of the team, but a player is allowed to be on 
the roster of a team one level above (i.e. a younger player can be on the 
team of a higher age group). That implies a player can be listed on multiple 
team rosters.
--------------------------------
##1st Milestone Project Requirements

1st milestone project done: Mon 31.10. 23:59
1st milestone evaluation submitted: Thur 3. 11. 15:59

###Project Requirements:

- create a project in a Github repository that is publicly accessible (for read) choose a short and descriptive name. Create some project wiki to publish other information for your project.
- on the project wiki there will be a project description, a use case diagram and a class diagram for entity classes. There will be at least two user roles in the use case diagram. Associations between entities will be present in the class diagram.
- create 4 entity classes for your project.
- create a DAO layer interface (with proper javadoc).
- create the JPA implementation of the DAO classes (CRUD operations are enough for the first checkpoint).
- create unit tests for DAO classes (use in-memory database).
- every team member should work with different entities on different parts of the project (e.g. member 1 will create implementation of DAO for entity A, but member 2 will create unit test for entity A). In every class there will  be javadoc @author with the name of the class author. Also you must commit into Git only the changes that you made yourself. If you commit on behalf of somebody else this will not be regarded as his work!
- the project will be built using maven, and make sure you have all dependencies correctly configured, so it is possible to build it using just the command line.

###Evaluation Checklist:

You can assign maximum 10 points to the project you are reviewing.
* -3 points if it is not possible to compile the project using “mvn clean install”. You must either make this work or ask the team to fix this ASAP because it’s hard to check the code without this.
* -1 point for each minor occurrence of a team member not contributing enough.
* -1 points for incorrectly implementing equals/hash code.
* -1 points for every minor occurrence of missing methods/tests (e.g. missing important method on a DAO object or a missing test for that method).
* If a team member was not contributing at all, or very little, you should explicitly say this in your evaluation report. Tutor will follow up on this information.

Note: 
From this milestone your tutor will also assign you maximum 5 points based on the quality of the review that was submitted.

