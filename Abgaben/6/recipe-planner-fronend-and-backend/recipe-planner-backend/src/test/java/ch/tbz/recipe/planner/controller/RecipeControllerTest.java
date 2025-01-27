package ch.tbz.recipe.planner.controller;

import ch.tbz.recipe.planner.domain.Recipe;
import ch.tbz.recipe.planner.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecipeController.class)
class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecipeService recipeService;

    @Test
    void getRecipes_shouldReturnListOfRecipes() throws Exception {
        // Arrange
        Recipe recipe1 = new Recipe();
        recipe1.setId(UUID.randomUUID());
        recipe1.setName("Recipe 1");

        Recipe recipe2 = new Recipe();
        recipe2.setId(UUID.randomUUID());
        recipe2.setName("Recipe 2");

        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);
        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

        // Act & Assert
        mockMvc.perform(get("/api/recipes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("Recipe 1"))
                .andExpect(jsonPath("$[1].name").value("Recipe 2"));
    }

    @Test
    void getRecipe_shouldReturnSingleRecipe() throws Exception {
        // Arrange
        UUID recipeId = UUID.randomUUID();
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setName("Recipe 1");

        Mockito.when(recipeService.getRecipeById(eq(recipeId))).thenReturn(recipe);

        // Act & Assert
        mockMvc.perform(get("/api/recipes/recipe/{recipeId}", recipeId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Recipe 1"));
    }

    @Test
    void addRecipe_shouldAddAndReturnRecipe() throws Exception {
        // Arrange
        Recipe recipe = new Recipe();
        recipe.setName("New Recipe");

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(UUID.randomUUID());
        savedRecipe.setName("New Recipe");

        Mockito.when(recipeService.addRecipe(any(Recipe.class))).thenReturn(savedRecipe);

        // Act & Assert
        mockMvc.perform(post("/api/recipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recipe)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("New Recipe"));
    }
}
