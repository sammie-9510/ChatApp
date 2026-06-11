/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
/** In this part 2 of the POE we will be creating code for the user 
 * to send messages after successfully registered to the app
 */
import java.io.BufferedReader;
import java.util.Random;
import java.io.IOException;
import org.json.JSONObject;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Message {
    
    private static List<String> sentMessages = new ArrayList<>();
    private static List<String> disregardedMessages = new ArrayList<>();
    private static List<String> storedMessages = new ArrayList<>();
    private static List<String> messageHashes = new ArrayList<>();
    private static List<String> messageIDs = new ArrayList<>();
    private static List<String> recipientList = new ArrayList<>();
    
    //These are the objects declared to call upon
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;
    
    private static int totalMessages = 0;
    
    public Message(int messageNumber, String recipient, String messageText){
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageID = generateMessageID();
        this.messageHash = createMessageHash();
    }
    
    public String getMessageID(){
        return messageID;
    }
    
    public String getMessageHash(){
        return messageHash;
    }
    
    public String getRecipient(){
        return recipient;
    }
    
    public String getMessageText(){
        return messageText;
    }
    
    public int getMessageNumber(){
        return messageNumber;
    }
    
    
    //This is for generatind the message id that must be no longer than 10 characters
    String generateMessageID(){
        
        Random random = new Random();
        long id = 1000000000L + (long)(random.nextDouble() * 9000000000L);
        return String.valueOf(id);   
    }
    
    public boolean checkMessageID(){
        
        return messageID.length() == 10;
    }
    //THis for to check the correct way the recipient cell phone should be formatted
    public String checkRecipientCell(){
        
        if (recipient.startsWith("+27") && recipient.length() <=12){
            return ("Cell phone number successfully captured.");
        } else{
            return ("Cell phone number is incorrectly formatted or does not contain and international code. Please correct the number and try again.");
        }
    }
    //This is to check if message legth of the message os going to send it is not longer than 250 characters
    public String checkMessageLength(){
        
        if (messageText.length() <= 250){
            return ("Message ready to send.");
        } else {
            int over = messageText.length() - 250;
            return ("Message exceeds 250 characters by " + over + "; please reduce the size.");
        }
    }
    //this is for after the user as send their it wil print the messages details which is the message id, meaasge hash
    // recpient number and the message the user wrote
    public String printMessage(){
        return  "Message ID: " + messageID + "\n" +
                "Message Hash: " + messageHash + "\n" +
                "Recipient: " + recipient + "\n" +
                "Message: " + messageText;
    }
    
    public int returnTotalMessage(){
        return totalMessages;
    }
    //This is to generate the message hash, which must be uppercase and have the first word and last word of the message
    public String createMessageHash(){
        
        String idPart = messageID.substring(0, 2);
        
        String[] words = messageText.trim().split("\\s+");
        
        String firstWord = words[0];
        String lastWord = words[words.length - 1];
        
        String hash = idPart + ":" + messageNumber + ":" + firstWord + lastWord;
        return hash.toUpperCase();
    }
    //This is to prompted the user to choose either to send the message, disregard the message or store it
    public String sentMessage(){
        Scanner input = new Scanner(System.in);
       
        System.out.println("What would you like to do with this message?");
        System.out.println("1.) Send Message");
        System.out.println("2.) Disregard Message");
        System.out.println("3.) Store message to send later");
        
        int choice = input.nextInt();
        
        switch (choice){
            case 1:
                totalMessages++;
                return "Message succesfully sent.";
            
            case 2:
                return "Press 0 to delete the message.";
                
            case 3:
                storeMessage();
                System.out.println("Message saved to messages.json.");
                return "Message successfully stored.";
               
            default:
                return "Invalid option. Please choose option 1, 2 or 3.";
        }
    }
    //This is whwere th message is stored in JSON object
    /* JSON library used :org.json
    source: https://mvnrespository.com/artifact/org.json/json
    */
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
            System.out.println("Error saving message: " + e.getMessage());
        }
    }
    
    public static String displayLongestMessage(){
        String longest = "";
        for(String message : storedMessages){
            if(message.length() > longest.length()){
                longest = message;
            }
        }
        return longest;
    }
    
   public static String searchByMessageID(String ID){
       for(int i = 0; i < messageIDs.size(); i++){
           if(messageIDs.get(i).equals(ID)){
               return sentMessages.get(i);
           }
       }
       return "Message not found.";
   }
   
   public static String searchByRecipient(String recipient){
       StringBuilder results = new StringBuilder();
       for(int i = 0; i < recipientList.size(); i++){
           if (recipientList.get(i).equals(recipient)){
               results.append(sentMessages.get(i)).append("\n");
           }
   }
       return results.toString();
   }
   
   public static String deleteByHash(String Hash){
       for(int i = 0; i < messageHashes.size(); i++){
           if(messageHashes.get(i).equals(Hash)){
               String deleteMessage = sentMessages.get(i);
               
               messageHashes.remove(i);
               messageIDs.remove(i);
               recipientList.remove(i);
               sentMessages.remove(i);
               
               return "Message: " + deleteMessage + " successfully deleted.";
           }
       }
       
       return "Hash not found.";
   }
   
   /* JSON library used :org.json
    source: https://mvnrespository.com/artifact/org.json/json
    */
   public static void loadStoredMessages(){
       try(BufferedReader reader = new BufferedReader(new FileReader("messages.json"))){
           String line;
           
           while((line = reader.readLine()) != null){
           JSONObject object = new JSONObject(line);
           storedMessages.add(object.getString("messageText"));
       }
       } catch (IOException e){
           System.out.println("No stored messages found.");
       }
   }
   
   public static String printMessages(){
       StringBuilder report = new StringBuilder();
       
       report.append("======= MESSAGE REPORT ========\n");
       for(int i = 0; i < sentMessages.size(); i++){
           report.append("---------------------------------\n");
           report.append("Hash: ").append(messageHashes.get(i)).append("\n");
           report.append("Recipient: ").append(recipientList.get(i)).append("\n");
           report.append("Message: ").append(sentMessages.get(i)).append("\n");
       }
       
       return report.toString();
   }
   
   public static String displayStoredMessages(){
       StringBuilder result = new StringBuilder();
       
       for(String message : storedMessages){
           result.append(message).append("\n");
       }
       
       return result.toString();
   }
   
   public static List<String> getSentMessages(){
       return sentMessages;
   }
   
   public static List<String> getDisregardedMessages(){
       return disregardedMessages;
   }
   
   public static List<String> getStoredMessages(){
       return storedMessages;
   }
   
   public static List<String> getMessageHashes(){
       return messageHashes;
   }
   
   public static List<String> getMessageIDs(){
       return messageIDs;
   }
   
   public static List<String> getReciptentList(){
       return recipientList;
   }
}

