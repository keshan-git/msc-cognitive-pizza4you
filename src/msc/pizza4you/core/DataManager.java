package msc.pizza4you.core;

import java.util.HashMap;
import msc.pizza4you.data.PizzaConfiguration;

/**
 *
 * @author Keshan De Silva
 */
public class DataManager
{
    private static DataManager instance;
    
    private HashMap<String, PizzaConfiguration> pizzaMap;
    
    private DataManager()
    {
        pizzaMap = new HashMap<>();
        
    }
    
    public synchronized static DataManager getInstance()
    {
        if (instance == null)
        {
            instance = new DataManager();
        }
        
        return instance;
    }
    
    public PizzaConfiguration getPizzaByName(String pizzaName)
    {
        return pizzaMap.get(pizzaName);
    }
    
    private void generatePizzaDataSet()
    {
        
    }
}
