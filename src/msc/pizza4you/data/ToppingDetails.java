package msc.pizza4you.data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Keshan De Silva
 */
public class ToppingDetails
{
    public enum ToppingType { PINEAPPLE, ONION, PEPPER, MUSHROOM, OLIVE,
    BELLPEPPER, CAPSICUM, LEEKS, CARROT, CABBAGE, PANEER, GREENCHILLIE, POTATOE,
    SWEETCORN, GARLIC};
    
    private final ArrayList<ToppingType> toppings;
    
    public ToppingDetails()
    {
        this.toppings = new ArrayList<>();
    }

    public ToppingDetails(ToppingType... toppingTypes)
    {
        this.toppings = new ArrayList<>();
            toppings.addAll(Arrays.asList(toppingTypes));
    }
        
    public ArrayList<ToppingType> getToppings()
    {
        return toppings;
    }
    
    public void addToppingType(ToppingType toppingType)
    {
        toppings.add(toppingType);
    }
    
    public void addToppingTypes(ToppingType... toppingTypes)
    {
        toppings.addAll(Arrays.asList(toppingTypes));
    }
        
    public void removeToppingType(ToppingType toppingType)
    {
        toppings.remove(toppingType);
    }
    
    public void clearToppingTypes()
    {
        toppings.clear();
    }
}
