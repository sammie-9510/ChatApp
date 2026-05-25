# Chat Appilication Part 2

## Student Infromation
- Name: Esamisipho Ndiko
- Student Number: ST10531476
- Module: PROG5121

---

# Project Information

This project required building a two-part where we are allowing user to message. 
Part 1 involved creating a user registration and login system, in part 2 we made some changes where users enter their first name, last name, username (max 5 characters with an underscore), password (minimum 8 characters with uppercase, number, and special character), and cell phone number (international format starting with +). 
The system validates all inputs using regular expressions and loops until valid data is entered, providing clear requirement reminders when validation fails. 
Login functionality allows 3 attempts before terminating the session. 
Part 2 built upon this by implementing a messaging system accessible only after successful login. 
Users specify how many messages to send, then enter recipient phone numbers and message text (max 250 characters) in a for loop. 
Each message automatically generates a unique 10-digit ID and creates a hash in the format "FirstTwoIDDigits:MessageNumber:FirstWordLastWord" in uppercase. 
After entering each message, users view the complete message details (ID, Hash, Recipient, Message) and choose to Send, Store to JSON file, or Discard it. 
The entire application runs in a while loop menu system with three options: Send Messages, Show Recently Sent Messages (displays "Coming Soon"), and Quit.
All validation methods and core functionality were tested using JUnit 4 unit tests following the Arrange-Act-Assert pattern, with tests covering valid and invalid inputs, edge cases, and the POE-specified test data. 
The project used Maven for dependency management (org.json library for JSON storage), maintained version control through Git with meaningful commits on a feature branch, and implemented GitHub Actions for continuous integration to automatically run all tests on every push. 
The complete project demonstrates object-oriented programming, input validation, string manipulation, file I/O, loops, conditional statements, exception handling, and professional software development practices including unit testing. 
