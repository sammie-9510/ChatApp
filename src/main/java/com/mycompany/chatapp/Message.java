/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
/** In this part 2 of the POE we will be creating code for the user 
 * to send messages after successfully registered to the app
 */
import java.util.Random;
import java.io.IOException;
import org.json.JSONObject;
import java.io.FileWriter;

public class Message {
    
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    public Message(int messageNumber, String recipient, String messageText){
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
    private String generateMessageID(){
        
        Random random = new Random();
        long id = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(id);   
    }
    
    public boolean checkMessageID(){
        
        return messageID.length() == 10;
    }
    
    public String checkRecipientCell(){
        
        if (recipient.startsWith("+27") && recipient.length() <=12){
            return ("Cell phone number successfully captured.");
        } else{
            return ("Cell phone number is incorrectly formatted or does not contain and international code. Please correct the number and try again.");
        }
    }
    
    public String checkMessageLength(){
        
        if (messageText.length() <= 250){
            return ("Message ready to send.");
        } else {
            int over = messageText.length() - 250;
            return ("Message exceeds 250 characters by " + over + "; please reduce the size.");
        }
    }
    
    public String createMessageHash(){
        
        String idPart = messageID.substring(0, 2);
        
        String[] words = messageText.trim().split(" ");
        
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }
    
    public String sentMessage(){
       
        System.out.println("What would you like to do with this message?");
        System.out.println("1.) Send Message");
        System.out.println("2.) Disregard Message");
        System.out.println("3.) Store message to send later");
        
        int choice = 0;
        
        switch (choice){
            case 1:
                
                System.out.println("Message ID: " + messageID);
                System.out.println("Message Hash: " + messageHash);
                System.out.println("Recipient: " + recipient);
                System.out.println("Message: " + messageText);
                return ("Message succesfully sent.");
            
            case 2:
                return ("Press 0 to delete the message.");
                
            case 3:
                storeMessage();
                return ("Message successfully stored.");
               
            default:
                return ("Invalid option.");
        }
    }
    
    public void storeMessage(){
        JSONObject json = new JSONObject();
        json.put("messageID", this.messageID);
        json.put("messageNumber", this.messageNumber);
        json.put("recipient", this.recipient);
        json.put("message", this.messageText);
        json.put("messageHash", this.messageHash);
        
        try (FileWriter file = new FileWriter("message.json", true)){
            file.write(json.toString() + " ");
            file.flush();
        } catch (IOException e){
            System.out.println("Erro saving message: " + e.getMessage());
        }
    }
}

