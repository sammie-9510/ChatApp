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

public class MainApp {
    public static void main(String[] args){
        
        //Scanner helps by allowing the user to enter their information
        Scanner input = new Scanner(System.in);
        
        //Make a object for the login class to be able to call its method
        Login login = new Login();
        
        /* Now the user must enter their information
        to register by creating their username,
        password and entering their
        phone number
        */
        System.out.println("=== User Resgistration === ");
        
        //User must create and enter their username
        System.out.println("Enter a username: ");
        String username = input.nextLine();
        
        //User must create and enter their password
        System.out.println("Enter a password: ");
        String password = input.nextLine();
        
        //User must enter their South African phone number
        System.out.println("Enter your South African phone number (+27...): ");
        String phoneNumber = input.nextLine();
        
        //Now lets call on the register user method and store the message it will return
        String response = login.registerUser(username, password, phoneNumber);
        
        //Display the registration message
        System.out.println(response);
        
        /*If registration is successful and complete
        the user may now login by entering their username and 
        writing their password to login successfully
        */
        System.out.println("\n=== User Login === ");
        
        //User must enter their username
        System.out.println("Enter your user: ");
        String loginUsername = input.nextLine();
        
        //User must enter their password
        System.out.println("Enter your password: ");
        String loginPassword = input.nextLine();
        
        //Now lets call the user login to check if deyails match the stored details
        boolean loggedIn = login.loginUser(loginUsername, loginPassword);
        
        //Display the correct login message
        String loginMessage = login.returnLoginStatus(loggedIn);
        System.out.println(loginMessage);
    
        if (loggedIn){
            System.out.println("\nWELCOME TO QUICKCHAT");
        } else {
            System.out.println("Login failed. Exiting.");
        }
        
       
        
        boolean running = true;
        
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
            
            switch (choice){
                case 1:
                    System.out.println("\nHow many messages would you like to send?");
                    int numMessages = input.nextInt();
                    input.nextLine();
                    
                    for (int i = 0; i < numMessages; i++){
                        int messageNumber = i + 1;
                        
                        System.out.println("\n=====================");
                        System.out.println(" MESSAGE " + messageNumber + " OF 1");
                        System.out.println("========================");
                        
                        System.out.println("Enter recipient cell number (with country code +27): ");
                        String recipient = input.nextLine();
                        
                        System.out.println("Enter your message (max 250 characters): ");
                        String messageText = input.nextLine();
                        
                        Message message = new Message(messageNumber, recipient, messageText);
                        
                        System.out.println(message.checkMessageLength());
                        
                        if (messageText.length() > 250){
                            System.out.println(message.checkRecipientCell());
                            
                            String Results = message.sentMessage();
                            System.out.println(Results);
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
                        
                       
                    }  
                              
                        System.out.println("===================");
                        System.out.println("Total messages processed: " + numMessages);
                        System.out.println("===================\n");
                        
                    break;
                    
                case 2:
                    System.out.println("\nComing Soon.");
                    break;
                case 3:
                    running = false;
                    System.out.println("\nGoodbye!");
                    break;
                    
                default:
                    System.out.println("\n Invalid choice. Please select 1, 2, or 3.");
            }
        }
        
       
    }
}



