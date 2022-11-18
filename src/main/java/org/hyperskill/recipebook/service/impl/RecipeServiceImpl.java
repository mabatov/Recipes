package org.hyperskill.recipebook.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.hyperskill.recipebook.entity.User;
import org.hyperskill.recipebook.repository.RecipeRepository;
import org.hyperskill.recipebook.entity.Recipe;
import org.hyperskill.recipebook.exception.RecipeNotFoundException;
import org.hyperskill.recipebook.service.RecipeService;

import java.time.LocalDateTime;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe findRecipeById(Long id) {
        return recipeRepository.findById(id)
                .orElseThrow(() ->
                        new RecipeNotFoundException(
                                "Recipe not found for id = " + id));
    }

    @Override
    public ResponseEntity<?> deleteRecipeById(UserDetailsImpl user, Long id) {
        if (recipeRepository.findById(id).isPresent()) {
            if (recipeRepository.findUserIdInRecipe(id).equals(user.getId())) {
                recipeRepository.deleteRecipeById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    public ResponseEntity<?> updateRecipe(UserDetailsImpl user, Long id, Recipe recipe) {
        if (recipeRepository.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if (recipeRepository.findUserIdInRecipe(id).equals(user.getId())) {
            recipe.setId(id);
            save(user, recipe);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    public void checkValidRequest(Recipe recipe) {
        if (recipe.getDirections() == null ||
                recipe.getIngredients() == null ||
                recipe.getDescription() == null ||
                recipe.getName() == null ||
                recipe.getCategory() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void save(UserDetailsImpl user, Recipe recipe) {
        checkValidRequest(recipe);
        recipe.setUser(new User(user));
        recipeRepository.save(recipe);
    }
}
