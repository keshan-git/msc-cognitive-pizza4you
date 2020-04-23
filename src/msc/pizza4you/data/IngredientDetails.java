package msc.pizza4you.data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Keshan De Silva
 */
public class IngredientDetails
{
    public enum IngredientType { CHICKEN, BEEF, MUTTON, FISH, PRAWN };
    private final ArrayList<IngredientType> ingredients;
    
    public IngredientDetails()
    {
        this.ingredients = new ArrayList<>();
    }

    public IngredientDetails(IngredientType... ingredientTypes)
    {
        this.ingredients = new ArrayList<>();
        ingredients.addAll(Arrays.asList(ingredientTypes));
    }
        
    public ArrayList<IngredientType> getIngredients()
    {
        return ingredients;
    }
    
    public void addIngredientType(IngredientType ingredientType)
    {
        ingredients.add(ingredientType);
    }
    
    public void addIngredientTypes(IngredientType... ingredientTypes)
    {
        ingredients.addAll(Arrays.asList(ingredientTypes));
    }
        
    public void removeIngredientType(IngredientType ingredientType)
    {
        ingredients.remove(ingredientType);
    }
    
    public void clearIngredientTypes()
    {
        ingredients.clear();
    }
    
    public boolean isVegetarian()
    {
        return ingredients.isEmpty();
    }
}
