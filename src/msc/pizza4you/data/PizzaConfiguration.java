package msc.pizza4you.data;

/**
 *
 * @author Keshan De Silva
 */
public class PizzaConfiguration
{
    private IngredientDetails ingredientDetails;
    private SauceDetails sauceDetails;
    private CheeseDetails cheeseDetails;
    private ToppingDetails toppingDetails;
    
    public boolean isVegetarian()
    {
        return ingredientDetails.isVegetarian();
    }

    public void setVegetarian(boolean vegetarian)
    {
        if (vegetarian)
        {
            this.ingredientDetails.clearIngredientTypes();
        }
    }

    public IngredientDetails getIngredientDetails()
    {
        return ingredientDetails;
    }

    public SauceDetails getSauceDetails()
    {
        return sauceDetails;
    }

    public CheeseDetails getCheeseDetails()
    {
        return cheeseDetails;
    }

    public ToppingDetails getToppingDetails()
    {
        return toppingDetails;
    }

    public void setIngredientDetails(IngredientDetails ingredientDetails)
    {
        this.ingredientDetails = ingredientDetails;
    }

    public void setSauceDetails(SauceDetails sauceDetails)
    {
        this.sauceDetails = sauceDetails;
    }

    public void setCheeseDetails(CheeseDetails cheeseDetails)
    {
        this.cheeseDetails = cheeseDetails;
    }

    public void setToppingDetails(ToppingDetails toppingDetails)
    {
        this.toppingDetails = toppingDetails;
    }

}
