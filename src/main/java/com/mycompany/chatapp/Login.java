/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

  /* This package is the part 1 of the my PoE
        this is to create a chat app and i am requierd to 
        make a login class, this class is to build a login 
        process for a user to first register than login to the app
*/


public class Login {
    /* In order to login the user must provide their details 
     such as a username, a password amd a phone number in  
     order to register once details put in their details
     will be saved
    */
    
    // The variables written are to be used when capturing the user's details
    String username;
    String password;
    String phoneNumber;
    String firstName;
    String lastName;
    
    /* To check the if user's username is valid, 
    it must contain and underscore ("_") 
    and the characters must be not longer than 5 characters
    */
     public boolean checkUserName(String username) {
         
         //Username must contain ("_") check for the underscore
         // Username must be <=5 long check if username is no longer tan 5 characters
         return username.contains("_") && username.length() <= 5;
     }
     
     /* To check if the user's password is acceptable it must contain
     at least 8 charaters long
     1 capital letter
     at least 1 one number
     atleast 1 special character (!,@,%,#,ect.)
     */
     public boolean checkPasswordComplexity(String password){
         
         /*The variable used are to check whether the user
         has the correct vaule to make a correct password
         */
         boolean hasCapital = false;
         boolean hasNumber = false;
         boolean hasSpecial = false;
         
         /*Check if the user has a capital letter,
         a number and a special character to check if the
         user's password is valid
         */
         for (int i = 0; i < password.length(); i++) {
             char c = password.charAt(i);
             
             if (Character.isUpperCase(c)){
                 hasCapital = true;
             } else if (Character.isDigit(c)){
                 hasNumber = true;
             } else if (!Character.isLetterOrDigit(c)){
                 hasSpecial = true;
             }
         }
         //User password must 8 character long
         //User password must contain a capital letter
         //User password must contain a number
         //User name must contain a special character
         return password.length() >= 8 && hasCapital && hasNumber && hasSpecial;
         
         
     }
     
     /* To check if the user's cellphone number is correct 
     the phone number must contain the South African international code
     which is +27 and 9 digits follow after the international code
     Overall the the phone number must be 12 characters long
     */
     public boolean checkCellPhoneNumber(String phoneNumber){
         
         //User phone number must contain internationl code +27
         //User phone number must be 12 digits long
         return phoneNumber.startsWith("+27") && phoneNumber.length() <= 12;
     }
     
     /*checks for the username , password and phone number
     if they are correct. 
     Check if the data information the user put gets stores
     if everything is correct if not the return messages 
     should pop up.
     */
     public String registerUser(String username, String password, String phoneNumber){
         
         if (!checkUserName(username)){
             return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
         }
         if (!checkPasswordComplexity(password)){
             return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
         }
         if (!checkCellPhoneNumber(phoneNumber)){
             return "Cell phone number incorrectly formatted or does not contain South African internationl code.";
         }
         
         //Display the user's username,password and phone number
         this.username = username;
         this.password = password;
         this.phoneNumber = phoneNumber;
         
         //Display that the user succesfully registered
         return "User registered succeffully.";
         
     }
     
     /* Now that the user has successfully registered now 
     the user can login using their username 
     and their enter their password
     */
     public boolean loginUser(String username, String password){
         
         //The user has to put in their username and password to login
         return this.username.equals(username) && this.password.equals(password);
     }
     
     /*If user successfully logged in diplay "welcome username 
     it is great to see you again" if the login details are not 
     correct than dosplay "username or paasword is incorrect
     please try again later"
     */
     public String returnLoginStatus(boolean success){
         if (success){
             return "Welcome " + username + " its is great to see you again.";
         } else {
             return "Username or password incorrect, please try agian";
         }
     }
            
    
    
    
}

