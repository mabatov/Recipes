/*
package org.hyperskill.recipebook;

import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.*;

@SpringBootTest
class RecipeBookApplicationTests {

    static class Recipe {
        final String name;
        final String category;
        final String description;
        final String[] ingredients;
        final String[] directions;

        Recipe(String name, String category, String description, String[] ingredients, String[] directions) {
            this.name = name;
            this.category = category;
            this.description = description;
            this.ingredients = ingredients;
            this.directions = directions;
        }
    }

    final Recipe[] RECIPES = {
            // 0
            new Recipe(
                    "Fresh Mint Tea /Test",
                    "beverage /Test",
                    "Light, aromatic and refreshing beverage, ... /Test",
                    new String[]{"boiled water", "honey", "fresh mint leaves /Test"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again /Test"}
            ),
            // 1
            new Recipe(
                    "Warming Ginger Tea /Test",
                    "beverage /Test",
                    "Ginger tea is a warming drink for cool weather, ... /Test",
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey /Test"},
                    new String[]{"Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy /Test"}
            ),
            // 2
            new Recipe(
                    "ice-cream",
                    "Dessert",
                    "-",
                    new String[]{"--", "---", "-"},
                    new String[]{"----", "---"}
            ),
            // 3
            new Recipe(
                    "tea r 4",
                    "DesserT",
                    "---",
                    new String[]{"-", "----", "-"},
                    new String[]{"----", "--", "--"}
            ),
            // 4
            new Recipe(
                    "recipe ice-cream 5",
                    "Desser",
                    "---",
                    new String[]{"-", "--", "-"},
                    new String[]{"-", "--", "---"}
            ),
            // 5
            new Recipe(
                    "--",
                    "DeSSert",
                    "---",
                    new String[]{"---", "-", "--"},
                    new String[]{"---", "-"}
            ),
            // 6
            new Recipe(
                    "ICE-CREAM",
                    "desserT",
                    "----",
                    new String[]{"-", "-", "--"},
                    new String[]{"---", "--", "--"}
            ),
            // 7
            new Recipe(
                    "---",
                    "dessert",
                    "--",
                    new String[]{"-", "----"},
                    new String[]{"-----", "-", "---"}
            ),
            // 8
            new Recipe(
                    "9 recipe Tea test",
                    "-",
                    "----",
                    new String[]{"-", "-", "----"},
                    new String[]{"-----", "-", "--"}
            ),
            // 9
            new Recipe(
                    "10 ice recipe test",
                    "-",
                    "--",
                    new String[]{"----", "--;", "---"},
                    new String[]{"--", "-"}
            ),
            // 10
            new Recipe(
                    "11 ice-creamrecipe test",
                    "veryDessert",
                    "-",
                    new String[]{"-", "--"},
                    new String[]{"-----", "-", "---"}
            ),
            // 11
            new Recipe(
                    "cream",
                    "BEVerage",
                    "--",
                    new String[]{"---", "-", "-"},
                    new String[]{"-", "--"}
            ),
            // 12
            new Recipe(
                    "ice-cre",
                    "---Dessert",
                    "-",
                    new String[]{"---", "-"},
                    new String[]{"----", "-", "-"}
            ),

            // 13
            new Recipe(
                    "ice-cream",
                    "DESSERT",
                    "-",
                    new String[]{"----", "--"},
                    new String[]{"-", "--", "-"}
            ),
            // 14
            new Recipe(
                    "15 recipe test ice-CREAM",
                    "Dessert",
                    "-",
                    new String[]{"-", "---'", "-----"},
                    new String[]{"---", "-"}
            )
    };

    final Recipe[] INCORRECT_RECIPES = {
            //0
            new Recipe(
                    null,
                    "beverage",
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{"boiled water", "honey", "fresh mint leaves"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //1
            new Recipe(
                    "Fresh Mint Tea",
                    null,
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{"boiled water", "honey", "fresh mint leaves"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //2
            new Recipe(
                    "Warming Ginger Tea",
                    "beverage",
                    null,
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
                    new String[]{"Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"}
            ),
            //3
            new Recipe(
                    "Fresh Mint Tea",
                    "beverage",
                    "Light, aromatic and refreshing beverage, ...",
                    null,
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //4
            new Recipe(
                    "Warming Ginger Tea",
                    "beverage",
                    "Ginger tea is a warming drink for cool weather, ...",
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
                    null
            ),
            //5
            new Recipe(
                    "  ",
                    "beverage",
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{"boiled water", "honey", "fresh mint leaves"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //6
            new Recipe(
                    "Fresh Mint Tea",
                    "  ",
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{"boiled water", "honey", "fresh mint leaves"},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //7
            new Recipe(
                    "Warming Ginger Tea",
                    "beverage",
                    "  ",
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
                    new String[]{"Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"}
            ),
            //8
            new Recipe(
                    "Fresh Mint Tea",
                    "beverage",
                    "Light, aromatic and refreshing beverage, ...",
                    new String[]{},
                    new String[]{"Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"}
            ),
            //9
            new Recipe(
                    "Warming Ginger Tea",
                    "beverage",
                    "Ginger tea is a warming drink for cool weather, ...",
                    new String[]{"1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"},
                    new String[]{}
            )
    };

    @Test
    void addNewRecipe() {

    }
}
*/
