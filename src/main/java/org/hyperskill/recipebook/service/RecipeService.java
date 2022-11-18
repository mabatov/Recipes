package org.hyperskill.recipebook.service;

import org.springframework.http.ResponseEntity;
import org.hyperskill.recipebook.entity.Recipe;
import org.hyperskill.recipebook.service.impl.UserDetailsImpl;

public interface RecipeService {

    void save(UserDetailsImpl user, Recipe recipe);

    ResponseEntity<?> deleteRecipeById(UserDetailsImpl user, Long id);

    ResponseEntity<?> updateRecipe(UserDetailsImpl user, Long id, Recipe recipe);

//    ResponseEntity<?> searchRecipeByName(String name);
//
//    ResponseEntity<?> searchRecipeByCategory(String category);
}
