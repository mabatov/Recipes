package org.hyperskill.recipebook.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.hyperskill.recipebook.entity.Recipe;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository <Recipe, Long> {


    List<Recipe> findByCategoryIgnoreCaseOrderByDateDesc(String category);

    List<Recipe> findAllDistinctByNameContainingIgnoreCaseOrderByDateDesc(String name);

    @Transactional
    @Query(value = "select r.user.id from Recipe r where r.id = ?1")
    Long findUserIdInRecipe(Long id);

    Optional<Recipe> findById(Long id);

    @Transactional
    void deleteRecipeById(Long id);

}
