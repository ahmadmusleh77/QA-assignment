package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import main.najah.code.Product;



@DisplayName("productTest Tests")
public class ProductTest {
    Product p;

@BeforeEach
void setUp() throws Exception {
          p = new Product("Book", 50);
		 }


	
@Test
@DisplayName("test valid product creation")
     void testValidProductCreation() {
	     assertNotNull(p);
	     assertEquals("Book", p.getName());
	     assertEquals(50, p.getPrice());
	     assertEquals(0, p.getDiscount());
	    }

@Test
@DisplayName("test invalid product creation with negative price")
	 void testInvalidProductCreationNegativePrice() {
	      assertThrows(IllegalArgumentException.class, () -> new Product("Book", -10),"price must be non-negative");
	    }
	  
@ParameterizedTest
@CsvSource({
	        "10.0, 45.0",
	        "25.0, 37.5",
	        "50.0, 25.0"
	    })
	  
@DisplayName("test aply discount and get final price")
	  void testApplyDiscountAndGetFinalPrice(double discount, double expectedFinalPrice) {
	       p.applyDiscount(discount);
	       assertEquals(expectedFinalPrice, p.getFinalPrice(), 0.001);
	    }
	  

	  
@Test
@DisplayName("test invalid discount throws exception")
	 void testInvalidDiscountThrowsException() {
	      assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(60.0),"invalid discount");
	      assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-10.0),"invalid discount");
	    }


@Test
@DisplayName("test get name")
	 void testGetName() {
	      assertEquals("Book", p.getName());
	    }



@Test
@DisplayName("test get price")
	 void testGetPrice() {
	      assertEquals(50, p.getPrice());
	    }

	    
@Test
@DisplayName("test get discount")
     void testGetDiscount() {
	      p.applyDiscount(20);
	      assertEquals(20, p.getDiscount());
	    }
	    


@Test
@Timeout(100)
	void timeoutFinalPrice() {
	     p.applyDiscount(10);
	     assertTrue(p.getFinalPrice() > 0);
	    }
	  
	  
}