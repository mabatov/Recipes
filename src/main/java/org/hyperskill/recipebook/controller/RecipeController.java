package org.hyperskill.recipebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.hyperskill.recipebook.repository.RecipeRepository;
import org.hyperskill.recipebook.dto.ReturnId;
import org.hyperskill.recipebook.entity.Recipe;
import org.hyperskill.recipebook.exception.RecipeNotFoundException;
import org.hyperskill.recipebook.exception.RequestParamException;
import org.hyperskill.recipebook.service.impl.RecipeServiceImpl;
import org.hyperskill.recipebook.service.impl.UserDetailsImpl;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
public class RecipeController {

    private final RecipeRepository recipeRepository;

    private final RecipeServiceImpl recipeService;

    @Autowired
    public RecipeController(RecipeRepository recipeRepository, RecipeServiceImpl recipeService) {
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }


    @PostMapping("/api/recipe/new")
    public ReturnId postRecipe(@AuthenticationPrincipal UserDetailsImpl user,
                               @RequestBody @Valid Recipe recipe) {
        recipeService.save(user, recipe);
        return new ReturnId(recipe.getId());
    }

    @GetMapping("/api/recipe/{id}")
    public Recipe getRecipe(@PathVariable long id) {
        return recipeService.findRecipeById(id);
    }

    @GetMapping("/api/recipe/search")
    public List<Recipe> searchByCategory(@RequestParam @Valid Map<String, String> allParams) {

        if (allParams.size() != 1) {
            throw new RequestParamException("Zero or more than 1 parameter provided.");
        }

        List<Recipe> recipeList;

        if (allParams.containsKey("category")) {
            recipeList = recipeRepository.findByCategoryIgnoreCaseOrderByDateDesc(allParams.get("category"));

        } else if (allParams.containsKey("name")) {
            recipeList = recipeRepository.findAllDistinctByNameContainingIgnoreCaseOrderByDateDesc(allParams.get("name"));
        } else {
            throw new RequestParamException("Incorrect parameter provided");
        }


        return recipeList;
    }

    @PutMapping("/api/recipe/{id}")
    public ResponseEntity<?> updateRecipe(@AuthenticationPrincipal UserDetailsImpl user,
                                          @PathVariable("id") Long id,
                                          @Valid @RequestBody Recipe recipe) {
        return recipeService.updateRecipe(user, id, recipe);
    }

    @DeleteMapping("/api/recipe/{id}")
    public ResponseEntity<?> deleteRecipe(@AuthenticationPrincipal UserDetailsImpl user,
                                          @PathVariable long id) {
        return recipeService.deleteRecipeById(user, id);
    }


    public String getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal().toString();
    }

}
