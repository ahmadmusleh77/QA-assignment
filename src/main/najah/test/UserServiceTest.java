package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.UserService;


@DisplayName("UserService Tests")
class UserServiceTest {

	  UserService userService;
	  
@BeforeEach
	 void setUp() {
	      userService = new UserService();
	      System.out.println("userService initialized");
	    }
	    
@Test
@DisplayName("test valid email addresses")
     void testValidEmail() {
         assertTrue(userService.isValidEmail("user@example.com"), "valid email should return true");
         assertTrue(userService.isValidEmail("test.user@mail.co"), "valid email should return true");
    }
    
    
@Test
@DisplayName("test invalid email addresses")
     void testInvalidEmail() {
          assertFalse(userService.isValidEmail("userexample.com"), "invalid email should return false");
          assertFalse(userService.isValidEmail(""), "empty string should return false");
          assertFalse(userService.isValidEmail(null), "null should return false");
    }
    
    
    
    
@ParameterizedTest
@CsvSource({
            "user@example.com",
            "test.user@mail.co"
          })

@DisplayName("test valid emails with parameterized test using CSV")
     void testValidEmailsParameterized(String email) {
          assertTrue(userService.isValidEmail(email), "should be valid email: " + email);
    }
    
    
@Test
@DisplayName("test authentication with valid credentials")
     void testAuthenticateValid() {
          assertTrue(userService.authenticate("admin", "1234"), "admin should be authenticated");
    }
    
    
@Test
@DisplayName("test authentication with invalid credentials")
     void testAuthenticateInvalid() {
          assertFalse(userService.authenticate("admin", "wrongpassword"), "should not authenticate with wrong password");
          assertFalse(userService.authenticate("user", "1234"), "should not authenticate with wrong username");
    }

@Test
@Timeout(1) 
@DisplayName("test authentication timeout")
     void testAuthenticateTimeout() {
          assertTrue(userService.authenticate("admin", "1234"));
    }
    
}
