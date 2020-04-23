package msc.pizza4you.core;

import msc.pizza4you.data.CheeseDetails;
import msc.pizza4you.data.IngredientDetails;
import msc.pizza4you.data.IngredientDetails.IngredientType;
import msc.pizza4you.data.LikeLevelEnum;
import msc.pizza4you.data.PizzaConfiguration;
import msc.pizza4you.data.SauceDetails;
import msc.pizza4you.data.SauceDetails.SauceType;
import msc.pizza4you.data.ToppingDetails;
import msc.pizza4you.data.ToppingDetails.ToppingType;

/**
 *
 * @author Keshan De Silva
 */
public class NLPEngine
{
    private static NLPEngine instance;
    private NLPEngine()
    {

    }
    
    public synchronized static NLPEngine getInstance()
    {
        if (instance == null)
        {
            instance = new NLPEngine();
        }
        
        return instance;
    }
    
    public PizzaConfiguration generatePizzaConfiguration(String inputText)
    {
        PizzaConfiguration pizzaConfiguration = new PizzaConfiguration();
        
        inputText = inputText.trim().toLowerCase().replaceAll(",", " ");
        for (String input : inputText.split(" "))
        {
            // Ingredient
            IngredientType ingredientType = getIngredientType(input);
            if (ingredientType != null)
            {
                if (pizzaConfiguration.getIngredientDetails() == null)
                {
                    pizzaConfiguration.setIngredientDetails(new IngredientDetails());
                }
                
                pizzaConfiguration.getIngredientDetails().addIngredientType(ingredientType);
                continue;
            }
            
            // Toppings
            ToppingType toppingType = getToppinType(input);
            if (toppingType != null)
            {
                if (pizzaConfiguration.getToppingDetails() == null)
                {
                    pizzaConfiguration.setToppingDetails(new ToppingDetails());
                }
                
                pizzaConfiguration.getToppingDetails().addToppingType(toppingType);
                continue;
            }
            
            // Sauce
            SauceType sauceType = getSauceType(input);
            if (sauceType != null)
            {
                if (pizzaConfiguration.getSauceDetails() == null)
                {
                    pizzaConfiguration.setSauceDetails(new SauceDetails());
                    pizzaConfiguration.getSauceDetails().setSauceLikeLevel(LikeLevelEnum.LIKE);
                }

                pizzaConfiguration.getSauceDetails().addSauceType(sauceType);
            }       
        }
        
        // Cheese
        CheeseDetails cheeseDetails = getCheeseDetails(inputText);
        pizzaConfiguration.setCheeseDetails(cheeseDetails);
        
        return pizzaConfiguration;
    }
        
    public void updateSauceDetails(SauceDetails sauceDetails, String sauceNames)
    {
        sauceNames = sauceNames.trim().toUpperCase();
        
        for (String sauceName : sauceNames.split(" "))
        {
            SauceType sauceType = getSauceType(sauceName);
            
            if (sauceType != null)
            {
                sauceDetails.addSauceType(sauceType);
            }
        }
    }
    
    public void updateToppingDetails(ToppingDetails toppingDetails, String toppings)
    {
        toppings = toppings.trim().toLowerCase().replaceAll(",", " ");
        for (String topping : toppings.split(" "))
        {
            toppingDetails.addToppingType(getToppinType(topping));
        }
    }
        
    private IngredientType getIngredientType(String keyword)
    {
        keyword = keyword.toUpperCase();
        if (keyword.endsWith("S"))
        {
            keyword = keyword.substring(0, keyword.length() - 1);
        }
        
        IngredientType ingredientType = null;
        try
        {
            ingredientType = IngredientType.valueOf(keyword);
        }
        catch (IllegalArgumentException ex){}
        return ingredientType;
    }
     
    private SauceType getSauceType(String keyword)
    {
        keyword = keyword.toUpperCase();
        SauceType sauceType = null;
        try
        {
            sauceType = SauceType.valueOf(keyword);
        }
        catch (IllegalArgumentException ex){}
        
        return sauceType;
    }
    
    private ToppingType getToppinType(String keyword)
    {
        keyword = keyword.toLowerCase();
        if (keyword.endsWith("s"))
        {
            keyword = keyword.substring(0, keyword.length() - 1);
        }
        
        switch (keyword)
        {
            case "pineapple" : return ToppingType.PINEAPPLE;
            case "onion" : 
            case "crispy onion" : return ToppingType.ONION;
            case "pepper" : return ToppingType.PEPPER;
            case "mushroom" : return ToppingType.MUSHROOM;
            case "olive" :
            case "black olive" : return ToppingType.OLIVE;
            case "blackolive" : return ToppingType.OLIVE;
            case "bell pepper" :
            case "bellpepper" : return ToppingType.BELLPEPPER;
            case "capsicum" : return ToppingType.CAPSICUM;
            case "leek" : return ToppingType.LEEKS;
            case "carrot" : return ToppingType.CARROT;
            case "cabbage" : return ToppingType.CABBAGE;
            case "paneer" : return ToppingType.PANEER;
            case "green chillie" :
            case "greenchillie" :
            case "chillie" : return ToppingType.GREENCHILLIE;
            case "potatoe" : return ToppingType.POTATOE;
            case "corn" :
            case "sweet corn" :
            case "sweetcorn" : return ToppingType.SWEETCORN;
            case "garlic" : return ToppingType.GARLIC;
        }
        
        return null;
    }

    private CheeseDetails getCheeseDetails(String inputText)
    {
        CheeseDetails cheeseDetails = null;
        if (inputText.contains("extra cheese") || inputText.contains("extra mozzarella"))
        {
            cheeseDetails = new CheeseDetails();
            cheeseDetails.setCheeseLikeLevel(LikeLevelEnum.LOVE);
        }
        else if (inputText.contains("cheese") || inputText.contains("mozzarella"))
        {
            cheeseDetails = new CheeseDetails();
            cheeseDetails.setCheeseLikeLevel(LikeLevelEnum.LIKE);
        }
        else if (inputText.contains("no cheese") || inputText.contains("with out cheese") ||
                inputText.contains("no mozzarella") || inputText.contains("with out mozzarella"))
        {
            cheeseDetails = new CheeseDetails();
            cheeseDetails.setCheeseLikeLevel(LikeLevelEnum.DONT_LIKE);
        }
        
        return cheeseDetails;
    }
}
