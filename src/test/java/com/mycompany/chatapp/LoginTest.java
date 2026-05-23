/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.chatapp;
/*This section of our package a shows how 
to test how program using JUnit testing.
JUnit is the standard open-source framework
for unit testing in a Java programming languaage
*/


import org.junit.Test;

import static org.junit.Assert.*;


public class LoginTest {
    
    Login login = new Login();
    //Lets test the programs username
    @Test
    public void testUsernameValidation(){
        boolean result = login.checkUserName("kyl_1");
        assertTrue(result);
    }
        
        @Test
        public void testUsernameInvalidation(){
        boolean result = login.checkUserName("kyle!!!!");
        assertFalse(result);
        
        
        }
        
     
    //Lets test the programs password
        @Test
        public void testPasswordComplexityValidation(){
            boolean result = login.checkPasswordComplexity("Ch&&sec@ke99!");
            assertTrue(result);
        }
        
        @Test
        public void testPasswordComplexityInvalidation(){
            boolean result = login.checkPasswordComplexity("paasword");
            assertFalse(result);
      
        }
        
        //Lets test the programs phone number
        @Test
        public void testPhoneNumberValidation(){
            boolean result = login.checkCellPhoneNumber("+27838968976");
            assertTrue(result);
        }
        
        @Test
        public void testPhoneNumberInvalidation(){
            boolean result = login.checkCellPhoneNumber("0838968976");
            assertFalse(result);
            
        }
        
        //Lets test the prgrams login
        @Test
        public void testLoginValidation(){
                String registerUser = login.registerUser("kyl_1", "Ch&&sec@ke99!","+27838968976");
            assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
        }
        
        @Test
        public void testLoginInvalidation(){
                String registerUser = login.registerUser("kyl_1", "Ch&&sec@ke99!","+27838968976");
            assertFalse(login.loginUser("kyl_1", "password"));
        }
        
    
    
}

