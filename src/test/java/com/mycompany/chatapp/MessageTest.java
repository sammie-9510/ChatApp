/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author hp
 */
import org.junit.jupiter.api.BeforeEach;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class MessageTest {
    //Now we are to test the message objects, created before each test
    private Message message1;
    private Message message2;
    
    /*@Before runs before every single test method
    creats fresh message objects with test data
    */
    @BeforeEach
    public void setUp(){
        //Test data message1
        //Message number: 1
        //Recipient: +27718693002
        //Message: "Hi, Mike, can you join us for dinner tonight?"
        message1 = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        //Test data message 2:
        //Message number: 2
        //Recipient: 08575975889 (invalid number has no country code)
        //Message: Hi Keegen, did you receive the package?"
        message2 = new Message(2, "08575975889", "Hi Keegan, did you receive the package?");
    }
    
    /*now we will testing the message length
    Test 1: messages length validation
    Test 2: messages length invalidation
    */
    @Test
    public void testCheckMessageLenth_ValidMessage_returnsSuccess(){
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        String result = message.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }
    @Test
    public void testCheckMessageLength_over250chars_returnFailureWithCount(){
        String longMessage = "";
        
        for (int i = 0; i < 260; i++){
            longMessage += "a";
        }
        Message message = new Message(1, "+27718693002", longMessage);
        
        String result = message.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
    }
    @Test 
    public void testCheckMessageLenth_exactlyAtLimit_returnSuccess(){
        String exactMessage = "";
        
        for(int i = 0; i< 250; i++){
            exactMessage += "a";
        }
        Message message = new Message(1, "+27718693002", exactMessage);
        
        String result = message.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }
    @Test
    public void testCheckMessageLength_oneOver_returnsFailureWithCountOf1(){
        String oneOverMessage = "";
        
        for(int i = 0; i < 251; i++){
            oneOverMessage += "a";
        }
        Message message = new Message(1, "+27718693002", oneOverMessage);
        
        String result = message.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 1; please reduse the size.", result);
    }
    
    /* Now we are testing the recipient cell phone number
    Test 1: valid recipient number
    Test 2: invalid recipient number
    */
    @Test
    public void testRecipientCell_validNumber_returnsSuccess(){
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        String result = message.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }
    @Test
    public void testRecipientCellInvalidation(){
        Message message = new Message(2, "08575975889", "Hi Keegan, did you receive the package?");
        String result = message.checkRecipientCell();
        
        String expected = "Cell phone number is incorrectly formatted or does not not contain an South African international code. Please correct the number and try again";
        assertEquals(expected, result);
    }
    
    /*Now we test the message hash 
    Test 1: should end woth the correct words in uppercase
    Test 2: shoul all be uppercase 
    Test 3: hash generates works correctly
    */
    @Test
    public void testCreatMessageHash_correctFormat_withExpectedWordsEnd(){
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        String hash = message.getMessageHash();
        assertTrue(hash.endsWith(":1:HITONIGHT"));
    }
    @Test
    public void testCreatMessageHash_Uppercase(){
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        String hash = message.getMessageHash();
        assertEquals(hash.toUpperCase(), hash);
    }
    @Test
    public void testCreateMessageHash_multipleMessages_loopTest(){
        Message[] messages = { message1, message2};
        
        String[] expectedEndings = {
            "HITONIGHT",
            "HIPAYMENT"
        };
        
        for(int i = 0; i < messages.length; i++){
            
            String hash = messages[i].createMessageHash();
            assertTrue(hash.contains(expectedEndings[i]));
        }
    }
    
    /* testing message id
    Test 1: message id validation
    Test 2: message id invaldation
    */
    @Test
    public void testCheckMessageID_generatedID_isNotNull(){
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        String ID = message.generateMessageID();
        assertNotNull("Message ID should not be null", ID);
    }
    @Test
    public void testCheckMessageIDValidation(){
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        boolean isValid = message.checkMessageID();
        assertTrue("Message ID should be exactly 10 characters", isValid);
    }
    
    //Now testing send message action
    /* when user sends message, correct message is returned
    this test check the return valus, not actual user input
    */
    @Test
    public void testSentMessageReturns(){
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        String result = message.sentMessage();
        assertNotNull("sentMessage should return a String", result);
    }
    
    @Test
    public void testCheckMessageID_generatedID_isExactly10Chars(){
        Message message = new Message(1, "+27718693002", "Hi Mike, can you join us for dinner tonight");
        
        boolean ID = message.checkMessageID();
        
        assertTrue(ID);
    }
    
    class TestTableMessage extends Message{
        
        private int option;
        
        public TestTableMessage(int messageNumber, String recipient, String messageText, int option){
           super(messageNumber, recipient, messageText);
           this.option = option;
        }
        
        @Override
         
        public String sentMessage(){
            
            switch(option){
                case 1:
                    return "Message successfully sent.";
                case 2:
                    return "Press 0 to delete the message.";
                case 3:
                    return "Message successfully stored.";
                default:
                    return "Invalid option, Please choose option 1, 2, or 3";
            }
        }
    }
    
    @Test
    public void testSentMessage_userSelectsDisregard_returnsCorrectString(){
        TestTableMessage message = new TestTableMessage(1, "+27718693002", "Hi Mike, can you join us for dinner tonight", 2);
        
        String result = message.sentMessage();
        
        assertEquals("Press 0 to delete the message.", result);
    }
    
    @Test
    public void testSentMessagesArray_correctlyPopulated(){
        Message. getSentMessages().clear();
        
        Message.getSentMessages().add("Did you get the cake?");
        Message.getSentMessages().add("Its is dinner time!");
        
        assertTrue(Message.getSentMessages().add("Did you get the cake?"));
        assertTrue(Message.getSentMessages().add("It is dinner time!"));
    }
    
    @Test
    public void testDisplayLongestMessage_returnsCorrectMessage(){
        Message.getStoredMessages().clear();
        
        Message.getStoredMessages().add("Ok, I am leaving without you.");
        Message.getStoredMessages().add("Where are you? You are late! I have asked you to be on time");
        
        String expected = "Where are you? You are late! I have asked you to be on time";
        assertEquals(expected, Message.displayLongestMessage());
    }
    
    @Test
    public void testSearchByMessageID_resturnsCorrectMessage(){
        Message.getMessageIDs().clear();
        Message.getSentMessages().clear();
        
        Message.getMessageIDs().add("0838884567");
        Message.getSentMessages().add("It is dinner time");
        
        assertEquals("It is dinner time!", Message.searchByMessageID("0838884567"));
    }    
    @Test
    public void testSearchByRecipient_returnsAllMatchingMessages(){
        Message.getRecipientList().clear();
        Message.getSentMessages().clear();
        
        Message.getRecipientList().add("+2783884567");
        Message.getSentMessages().add("Where are you? You are late! I have asked you to be on time");
        
        Message.getRecipientList().add("+2783884567");
        Message.getSentMessages().add("Ok, I am leaving without you.");
        
        String result = Message.searchByRecipient("+2783884567");
        assertTrue(result.contains("Where are you? You are late! I have asked you to be on time"));
        
        
        assertTrue(result.contains("Ok, I am leaving withoutbyou"));
    }
    
    @Test
    public void testDeletebyHash_removesCorrectMessage(){
        Message.getMessageHashes().clear();
        Message.getSentMessages().clear();
        
        Message.getMessageHashes().add("00:2:WHERETIME");
        Message.getSentMessages().add("Where are you? You are late! I have asked you to be on time");
        
        String expected = "Message: " + "Where are you? You are late! I have asked you to be on time" + " successfully deleted.";
        
        assertEquals(expected, Message.deleteByHash("00:2:WHERETIME"));
    }
    
    @Test
    public void testDisplayReport_containsRequiredFields(){
        Message.getMessageHashes().clear();
        Message.getRecipientList().clear();
        Message.getSentMessages().clear();
        
        Message.getMessageHashes().add("00:1:DIDCAKE");
        Message.getRecipientList().add("+27834557896");
        Message.getSentMessages().add("Did you get the cake?");
        
        String report = Message.printMessages();
        
        assertTrue(report.contains("00:1:DIDCAKE"));
        assertTrue(report.contains("+27834557896"));
        assertTrue(report.contains("Did you get the cake"));
    }
}

