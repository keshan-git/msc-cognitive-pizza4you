package msc.pizza4you.data;

/**
 *
 * @author Keshan De Silva
 */
public class PizzaDetails
{
    private IngredientDetails ingredientDetails;
    private ToppingDetails toppingDetails;

    public PizzaDetails(IngredientDetails ingredientDetails,ToppingDetails toppingDetails)
    {
        this.ingredientDetails = ingredientDetails;
        this.toppingDetails = toppingDetails;
    }

    public IngredientDetails getIngredientDetails()
    {
        return ingredientDetails;
    }

    public void setIngredientDetails(IngredientDetails ingredientDetails)
    {
        this.ingredientDetails = ingredientDetails;
    }
    
    public ToppingDetails getToppingDetails()
    {
        return toppingDetails;
    }

    public void setToppingDetails(ToppingDetails toppingDetails)
    {
        this.toppingDetails = toppingDetails;
    } 
}
