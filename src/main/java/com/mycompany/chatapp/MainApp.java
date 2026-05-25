/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
/* this section of the package we are going to
have the user put their details to register and login
to the app using scanner 
The scanner class is used to get the users input
*/
import java.util.Scanner;
import java.util.ArrayList;

public class MainApp {
    public static void main(String[] args){
        
        //Scanner helps by allowing the user to enter their information
        Scanner input = new Scanner(System.in);
        
        //Make a object for the login class to be able to call its method
        Login login = new Login();
        
        /* Now the user must enter their information
        to register by enterinf their first name and last name and creating their username,
        password and entering their
        phone number
        */
        System.out.println("===============================");
        System.out.println("       WELCOME TO QUICKCHAT ");
        System.out.println("===============================");
        
        // Now we have the user register
        System.out.println("\n=== User Resgistration === ");
        
        System.out.println("Enter your first name: ");
        String firstName = input.nextLine();
        
        System.out.println("Enter your last name: ");
        String lastName = input.nextLine();
        
        /* we prompt the user to enter their first name last name and 
        create a password and user name but we guide them to what is required until 
        registion is successful
        */
        String username;
        
        while (true){
            
            System.out.println("Enter a username (it must contain an underscore (_) and be 5 characters long): ");
            username = input.nextLine();
            
            if(login.checkUserName(username)){
                System.out.println("Username successfully captured.");
                
                break;
            } else {
                System.out.println("Username is incorrectly formatted; please ensure your username contains and underscore and it is five characters long, please try again");
            }
        }
        
        String password;
        
        while (true){
            System.out.println("Enter a password(it must contain at least 8 characters, 1 capital letter, 1 number and a speciacl charater(!,@,#,&)): ");
            password = input.nextLine();
            
            if(login.checkPasswordComplexity(password)){
                System.out.println("Password successfully captured.");
                
                break;
            } else{
                System.out.println("Passwordis incorrectly formatted, pleaes ensure your password is 8 characters long, contain 1 captital letter, 1 number,1 special character (!,@,#,&), please try again");
                
            }
        }
        
        String phoneNumber;
        
        while (true){
            System.out.println("Enter a South African phone number (it must include an international code e.g. +27820745681): ");
            phoneNumber = input.nextLine();
            
            if(login.checkCellPhoneNumber(phoneNumber)){
                System.out.println("Cellphone number successfully captured.");
                
                break;
            } else{
                System.out.println("Cell phoone number is incorrectly formatted or does not contain the South African international code, please try again.");
            }
        }
        
        //Now lets call on the register user method and store the message it will return
        String response = login.registerUser(username, password, phoneNumber);
        
        //Display the registration message
        System.out.println(response);
        
        /*If registration is successful and complete
        the user may now login by entering their username and 
        writing their password to login successfully, the user is given 3 attempts to successfully
        login or the code with end
        */
        System.out.println("\n=== User Login === ");
        
        boolean loggedIn = false;
        int attempts = 0;
        
        while(!loggedIn && attempts < 3){
            System.out.println("Enter your username: ");
            String loginUsername = input.nextLine();
            
            System.out.println("Enter your password: ");
            String loginPassword = input.nextLine();
            
             //Now lets call the user login to check if deyails match the stored details
            loggedIn = login.loginUser(loginUsername, loginPassword);
            
            //Display the correct login message
            String loginMessage = login.returnLoginStatus(loggedIn);
            System.out.println(loginMessage);
            
            attempts++;
            
            if(!loggedIn && attempts < 3){
                System.out.println("You have " + (3 - attempts) + "attempt(s)" + "remaining");
            }
        }
        
        if(!loggedIn && attempts <3){
            System.out.println("You have reached the maximum number of attempts to login");
        } else {
            
            System.out.println("\nWELCOME TO QIUCKCHAT.");
        }
        
    /* When sucessully logined in the user with head to th main menu where the user is askes to send messages
        check messages send or quit the app and the user ptompted to choose which option
        */
        if (loggedIn){
         
           boolean running = true;
           ArrayList<String> sentMessages = new ArrayList<>();
        
            while (running){
                 System.out.println("\n=========================");
                 System.out.println("          MAIN MEUN");
                 System.out.println("===========================");
                 System.out.println("1.) Send Messages");
                 System.out.println("2.) Show recently sent messages");
                 System.out.println("3.) Quit");
                 System.out.println("===========================");
                 System.out.println("Enter your choice: ");
            
            int choice = input.nextInt();
            input.nextLine();
            
            Message message = null;
            /* we loop the code so when the user chooese an option the code will know what to do
            we used switch cases for this, fisrt case is for whem the user chooses to sent messages
            the users asked how man messages they sent , the  recipeint number and the code will deplay
            a message hash and message id number with the user message and recipients number
            */
            switch (choice){
                case 1:
                    System.out.println("\nHow many messages would you like to send?");
                    int numMessages = input.nextInt();
                    input.nextLine();
                    
                    for (int i = 0; i < numMessages; i++){
                        int messageNumber = i + 1;
                        
                        System.out.println("\n==== MESSAGE " + messageNumber + " OF " + numMessages + " ====");
                        
                        message = new Message(messageNumber, " ", " ");
                        String messageID = message.generateMessageID();
                        System.out.println("Message ID: " + messageID);
                    
                        
                        System.out.println("Enter recipient cell number (with country code +27): ");
                        String recipient = input.nextLine();
                        
                        message = new Message(messageNumber, recipient, " ");
                        
                        if((recipient.startsWith("+27") && recipient.length() <=12)){
                            System.out.println(message.checkRecipientCell());
                        }
                        
                        System.out.println("Enter your message (max 250 characters): ");
                        String messageText = input.nextLine();
                        
                        message = new Message (messageNumber, recipient, messageText);
                        
                        if (messageText.length() <= 250){
                            System.out.println(message.checkMessageLength());
                        }
                        
                        String result = message.createMessageHash();
                        System.out.println("\n Message Hash: " + result);
                        
                        System.out.println("\n=====================");
                        System.out.println("    MESSAGE DETAILS");
                        System.out.println("=======================");
                        String results = message.printMessage();
                               System.out.println(results);
                        
                        String Result = message.sentMessage();
                               System.out.println(Result);
                               
                               sentMessages.add(message.printMessage());
                         
                    }  
                              
                        System.out.println("===================");
                        System.out.println("Total messages sent: " + message.returnTotalMessage());
                        System.out.println("===================\n");
                        
                        System.out.println("\n===== ALL SENT MESSAGES ======");
                        System.out.println("-----------------------------------------");
                        for(String messages : sentMessages){
                            System.out.println(messages);
                            System.out.println("--------------------------------------");
                        }   
                        break;    
                case 2: // this case is for option which only says coming soon as we not required yet to do
                    System.out.println("\nComing Soon.");
                    break;
                case 3: // this case is for when the user chooses option to exit the app but quiting
                    running = false;
                    System.out.println("\nGoodbye, Thank you for using QuickChat!");
                    break;   
                default: // the defailt is added in case the user chooses and invalid option and will be told to choose the correct options
                    System.out.println("\n Invalid choice. Please select 1, 2, or 3.");
            }
        }
          
    } else { //this is for when the user has unsuccessfully logged in
            System.out.println("Login was unsuccessful, please try again.");
        }
    }
}



