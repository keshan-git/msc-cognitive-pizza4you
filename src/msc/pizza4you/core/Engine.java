package msc.pizza4you.core;

import CLIPSJNI.Environment;
import CLIPSJNI.PrimitiveValue;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import msc.pizza4you.data.IngredientDetails;
import msc.pizza4you.data.IngredientDetails.IngredientType;
import msc.pizza4you.data.PizzaConfiguration;
import msc.pizza4you.data.PizzaDetails;
import msc.pizza4you.data.PizzaModel;
import msc.pizza4you.data.SauceDetails.SauceType;
import msc.pizza4you.data.ToppingDetails;
import msc.pizza4you.data.ToppingDetails.ToppingType;

/**
 *
 * @author Keshan De Silva
 */
public class Engine
{
    private static Engine instance;
    
    private PizzaConfiguration pizzaConfiguration;
    private Environment environment;
    private ArrayList<PizzaModel> result;
    
    private HashMap<String, PizzaDetails> localDB;
    
    private Engine()
    {
        pizzaConfiguration = new PizzaConfiguration();
        localDB = new HashMap<>();
        initIE();
        generateLocalDB();
    }
    
    public synchronized static Engine getInstance()
    {
        if (instance == null)
        {
            instance = new Engine();
        }
        
        return instance;
    }
    
    private void reset()
    {
        pizzaConfiguration = new PizzaConfiguration();
        result = null;
    }

    public void setPizzaConfiguration(PizzaConfiguration pizzaConfiguration)
    {
        this.pizzaConfiguration = pizzaConfiguration;
    }

    public PizzaConfiguration getPizzaConfiguration()
    {
        return pizzaConfiguration;
    }

    public synchronized ArrayList<PizzaModel> getResult()
    {
        return result;
    }
    
    public synchronized void execute()
    {
        updateAssertStrings();
        result = generateResult();
    }
    
    private void initIE()
    {
        environment = new Environment();
        environment.load("D:\\Pizza4You KB.clp");    
    }
   
    private synchronized void updateAssertStrings()
    {
        environment.reset();
        
        ArrayList<String> assertStringList = generateAssertStringList();
        for (String assertString : assertStringList)
        {
            environment.assertString(assertString); 
        }
        
        environment.run();
       
   }
   
    private synchronized ArrayList<PizzaModel> generateResult()
    { 
        String evalStr = "(PIZZA::get-pizza-list)";                                 
        PrimitiveValue pv = environment.eval(evalStr);
        ArrayList<PizzaModel> pizzaModels = new ArrayList<>();
        
        try
        {
            for (int i = 0; i < pv.size(); i++) 
            {
                PrimitiveValue fv = pv.get(i);
                int certainty = fv.getFactSlot("certainty").numberValue().intValue(); 
                String pizzaName = fv.getFactSlot("value").stringValue(); 

                pizzaModels.add(new PizzaModel(pizzaName, certainty, localDB.get(pizzaName)));
                System.out.println(pizzaName + " : " + certainty);
            }  
        }
        catch(Exception ex){}
        
        return postProcessList(pizzaModels);
    } 

    private ArrayList<String> generateAssertStringList()
    {
        ArrayList<String> assertList = new ArrayList<>();
        
        //(attribute (name has-nonvegi) (value yes)) 
        //(attribute (name main-ingredient) (value mutton))
        if (pizzaConfiguration.getIngredientDetails().isVegetarian())
        {
            assertList.add("(attribute (name has-nonvegi) (value no))");
        }
        else
        {
            assertList.add("(attribute (name has-nonvegi) (value yes))");
            for (IngredientType ingredientType : pizzaConfiguration.getIngredientDetails().getIngredients())
            {
                assertList.add("(attribute (name main-ingredient) (value " +  
                                                                ingredientType.toString().toLowerCase() + "))");
            }
        }
                
        //(attribute (name has-cheese) (value yes))
        //(attribute (name cheese) (value mozzarella))
        switch (pizzaConfiguration.getCheeseDetails().getCheeseLikeLevel())
        {
            case LOVE : 
            {
                assertList.add("(attribute (name has-cheese) (value yes))");
                assertList.add("(attribute (name cheese) (value extra))");
                break;
            }
            case LIKE : 
            {
                assertList.add("(attribute (name has-cheese) (value yes))");
                assertList.add("(attribute (name cheese) (value mozzarella))");
                break;
            }
            case DONT_LIKE : 
            {
                assertList.add("(attribute (name has-cheese) (value no))");
                break;
            }
            case NO_MATTER : 
            {
                assertList.add("(attribute (name has-cheese) (value unknown))");
                assertList.add("(attribute (name cheese) (value unknown))");
                break;
            }
        }
        
        //(attribute (name has-sauce) (value yes))
        //(attribute (name sauce) (value tomato))
        switch (pizzaConfiguration.getSauceDetails().getSauceLikeLevel())
        {
            case LIKE : 
            {
                assertList.add("(attribute (name has-sauce) (value yes))");
                for (SauceType sauceType : pizzaConfiguration.getSauceDetails().getSauceTypes())
                {
                    assertList.add("(attribute (name sauce) (value " +  
                                                sauceType.toString().toLowerCase() + "))");
                }
                break;
            }
            case DONT_LIKE : 
            {
                assertList.add("(attribute (name has-sauce) (value no))");
                break;
            }
            case NO_MATTER : 
            {
                assertList.add("(attribute (name has-sauce) (value unknown))");
                break;
            }
        }
                
        return assertList;
    }

    private void generateLocalDB()
    {
        localDB.put("Devilled Chicken", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails()));
        localDB.put("Corned Mutton Sensation", new PizzaDetails(new IngredientDetails(IngredientType.MUTTON), 
                new ToppingDetails(ToppingType.ONION, ToppingType.GREENCHILLIE)));
        localDB.put("Moghul Chicken Tandoori", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails()));
        localDB.put("Hot & Spicy Chicken", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails(ToppingType.ONION)));
        localDB.put("Sausage Delight", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails(ToppingType.ONION)));
        localDB.put("Tandoori Chicken", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails(ToppingType.ONION)));
        localDB.put("Devilled Fish", new PizzaDetails(new IngredientDetails(IngredientType.FISH), 
                new ToppingDetails(ToppingType.ONION)));
        localDB.put("Spicy Seafood", new PizzaDetails(new IngredientDetails(IngredientType.FISH, IngredientType.PRAWN), 
                new ToppingDetails(ToppingType.ONION, ToppingType.OLIVE, ToppingType.BELLPEPPER)));
        localDB.put("Chillie Chicken", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails()));
        localDB.put("BBQ Chicken", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails(ToppingType.ONION, ToppingType.PINEAPPLE, ToppingType.PEPPER)));
        localDB.put("Hot Garlic Prawns", new PizzaDetails(new IngredientDetails(IngredientType.PRAWN), 
                new ToppingDetails(ToppingType.ONION, ToppingType.PEPPER)));
        localDB.put("Cheese Lovers", new PizzaDetails(new IngredientDetails(), 
                new ToppingDetails()));
        localDB.put("Chicken Hawaiian", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails(ToppingType.PINEAPPLE)));
        localDB.put("Meat Lovers", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails()));
        localDB.put("Veggie Supreme", new PizzaDetails(new IngredientDetails(), 
                new ToppingDetails(ToppingType.MUSHROOM, ToppingType.ONION, ToppingType.OLIVE, ToppingType.BELLPEPPER)));
        localDB.put("Cheesy Onion", new PizzaDetails(new IngredientDetails(), 
                new ToppingDetails(ToppingType.ONION)));
        localDB.put("Beef Pepperoni", new PizzaDetails(new IngredientDetails(IngredientType.BEEF), 
                new ToppingDetails()));
        localDB.put("Ultimate Cheese Treat", new PizzaDetails(new IngredientDetails(), 
                new ToppingDetails()));
        localDB.put("Spicy Veggie with Paneer", new PizzaDetails(new IngredientDetails(), 
                new ToppingDetails(ToppingType.PINEAPPLE, ToppingType.LEEKS, ToppingType.CARROT, ToppingType.CABBAGE, ToppingType.PANEER)));
        localDB.put("Super Supreme", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN, IngredientType.BEEF), 
                new ToppingDetails(ToppingType.PINEAPPLE, ToppingType.PEPPER, ToppingType.MUSHROOM, ToppingType.OLIVE)));
        localDB.put("Chicken Bacon", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails(ToppingType.ONION)));
        localDB.put("Cheese & Tomato", new PizzaDetails(new IngredientDetails(), 
                new ToppingDetails()));
        localDB.put("Chicken Bacon & Potato", new PizzaDetails(new IngredientDetails(IngredientType.CHICKEN), 
                new ToppingDetails(ToppingType.POTATOE, ToppingType.ONION)));
        localDB.put("Veggie Stravaganza", new PizzaDetails(new IngredientDetails(), 
                new ToppingDetails(ToppingType.ONION, ToppingType.OLIVE)));
    }

    private ArrayList<PizzaModel> postProcessList(ArrayList<PizzaModel> pizzaModels)
    {
        ArrayList<ToppingType> selectedToppings = pizzaConfiguration.getToppingDetails().getToppings();
        
        if (!selectedToppings.isEmpty())
        {
            for (PizzaModel pizzaModel : pizzaModels)
            {
                pizzaModel.setCertainty(pizzaModel.getCertainty() - (5 * selectedToppings.size()));
                
                for (ToppingType toppingType : selectedToppings)
                {
                    if (pizzaModel.getPizzaDetails().getToppingDetails().getToppings().contains(toppingType))
                    {
                        pizzaModel.setCertainty(pizzaModel.getCertainty() + 5);
                    }
                }  
            }
            Collections.sort(pizzaModels);
        }
        
        for (int i = 2; i < pizzaModels.size(); i++)
        {
            PizzaModel currentPizza = pizzaModels.get(i);
            currentPizza.setCertainty(currentPizza.getCertainty() - (int)(Math.random() * 5 * (i - 2)));
        }
        
        Collections.sort(pizzaModels);
        return pizzaModels;
    }
}
