/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;

/**
 *
 * @author hp
 */
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {
    //Now we are to test the message objects, created before each test
    private Message message1;
    private Message message2;
    
    /*@Before runs before every single test method
    creats fresh message objects with test data
    */
    @Before
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
    public void testCheckMessageLenthValidation(){
        String result = message1.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }
    @Test
    public void testCheckMessageLengthInvalidation(){
        String longMessage = "a".repeat(260);
        Message messageLong = new Message(1, "+27718693002", longMessage);
        
        String result = messageLong.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 10; please reduce the size.", result);
    }
    @Test 
    public void testCheckMessageLenthExact(){
        String exactMessage = "a".repeat(250);
        Message messageExact = new Message(1, "+27718693002", exactMessage);
        
        String result = messageExact.checkMessageLength();
        assertEquals("Message ready to send.", result);
    }
    @Test
    public void testCheckMessageLengthOverOne(){
        String oneOverMessage = "a".repeat(251);
        Message messageOneOver = new Message(1, "+27718693002", oneOverMessage);
        
        String result = messageOneOver.checkMessageLength();
        assertEquals("Message exceeds 250 characters by 1; please reduse the size.", result);
    }
    
    /* Now we are testing the recipient cell phone number
    Test 1: valid recipient number
    Test 2: invalid recipient number
    */
    @Test
    public void testRecipientCellValidation(){
        String result = message1.checkRecipientCell();
        assertEquals("Cell phone number successfully captured.", result);
    }
    @Test
    public void testRecipientCellInvalidation(){
        String result = message2.checkRecipientCell();
        
        String expected = "Cell phone number is incorrectly formatted or does not not contain an South African international code. Please correct the number and try again";
        assertEquals(expected, result);
    }
    
    /*Now we test the message hash 
    Test 1: should end woth the correct words in uppercase
    Test 2: shoul all be uppercase 
    Test 3: hash generates works correctly
    */
    @Test
    public void testCreatMessageHashUppercaseEnd(){
        String hash = message1.getMessageHash();
        assertTrue("Hash should end with :1:HITONIGHT", hash.endsWith(":1:HITONIGHT"));
    }
    @Test
    public void testCreatMessageHashUppercase(){
        String hash = message1.getMessageHash();
        assertEquals(hash.toUpperCase(), hash);
    }
    @Test
    public void testCreateMessageHashValidation(){
        String[] messages = {
            "Hi Mike , can you join us for dinner tonight?",
            "Hello world",
            "Testing one two three four"
        };
        
        String[] expectedEndings = {
            "HITONIGHT",
            "HELLOWORLD",
            "TESTINGFOUR"
        };
        
        for(int i = 0; i < messages.length; i++){
            Message message = new Message(i + 1, "+27718693002", messages[i]);
            
            String hash = message.getMessageHash();
            assertTrue("Hash should contain " + expectedEndings[i],
                    hash.contains(expectedEndings[i]));
        }
    }
    
    /* testing message id
    Test 1: message id validation
    Test 2: message id invaldation
    */
    @Test
    public void testCheckMessageIDInvalidation(){
        String ID = message1.getMessageID();
        assertNotNull("Message ID should not be null", ID);
    }
    @Test
    public void testCheckMessageIDValidation(){
        boolean isValid = message1.checkMessageID();
        assertTrue("Message ID should be exactly 10 characters", isValid);
    }
    
    //Now testing send message action
    /* when user sends message, correct message is returned
    this test check the return valus, not actual user input
    */
    @Test
    public void testSentMessageReturns(){
        String result = message1.sentMessage();
        assertNotNull("sentMessage should return a String", result);
    }
}

