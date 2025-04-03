package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import main.najah.code.RecipeBook;
import main.najah.code.Recipe;

@DisplayName("recipeBook Tests")
class RecipeBookTest {

      RecipeBook recipeBook;

@BeforeEach
     void setUp() {
          recipeBook = new RecipeBook();
       }

@Test
@DisplayName("test adding valid recipes")
   void testAddValidRecipe() {
        Recipe recipe1 = new Recipe();
        recipe1.setName("Coffee");
        recipeBook.addRecipe(recipe1);
        
        Recipe recipe2 = new Recipe();
        recipe2.setName("Tea");
        recipeBook.addRecipe(recipe2);
        
        assertEquals("Coffee", recipeBook.getRecipes()[0].getName());
        assertEquals("Tea", recipeBook.getRecipes()[1].getName());
       }

@Test
@DisplayName("test adding duplicate recipes")
     void testAddDuplicateRecipe() {
          Recipe recipe = new Recipe();
          recipe.setName("Coffee");
          recipeBook.addRecipe(recipe);
        
          assertFalse(recipeBook.addRecipe(recipe), "duplicate recipe should not be added.");
    }

@Test
@DisplayName("test deleting existing recipe")
     void testDeleteExistingRecipe() {
          Recipe recipe = new Recipe();
          recipe.setName("Coffee");
          recipeBook.addRecipe(recipe);
        
          String deletedName = recipeBook.deleteRecipe(0);
          assertEquals("Coffee", deletedName, "deleted recipe name should match.");
          assertNull(recipeBook.getRecipes()[0], "the recipe should be null after deletion.");
    }

@Test
@DisplayName("test deleting non-existing recipe")
     void  testDeleteNonExistingRecipe() {
           assertNull(recipeBook.deleteRecipe(0), "deleting non-existing recipe should return null.");
      }

@Test
@DisplayName("test editing existing recipe")
     void testEditExistingRecipe() {
          Recipe originalRecipe = new Recipe();
          originalRecipe.setName("Coffee");
          recipeBook.addRecipe(originalRecipe);
        
          Recipe newRecipe = new Recipe();
          newRecipe.setName("Latte");
          String editedName = recipeBook.editRecipe(0, newRecipe);
        
          assertEquals("Coffee", editedName, "edited recipe name should match the original.");
          assertEquals("Latte", recipeBook.getRecipes()[0].getName(), "recipe should be updated correctly.");
      }

@Test
@DisplayName("test editing non-existing recipe")
     void testEditNonExistingRecipe() {
          Recipe newRecipe = new Recipe();
          newRecipe.setName("Latte");
          assertNull(recipeBook.editRecipe(0, newRecipe), "editing non-existing recipe should return null.");
      }
}