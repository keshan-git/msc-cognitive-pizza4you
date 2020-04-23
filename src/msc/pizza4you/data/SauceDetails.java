package msc.pizza4you.data;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Keshan De Silva
 */
public class SauceDetails
{  
    public enum SauceType { SPICY, GARLIC, TOMATO};
    
    private LikeLevelEnum sauceLikeLevel;
    private final ArrayList<SauceType> sauces;

    public SauceDetails()
    {
        this.sauces = new ArrayList<>();
    }

    public LikeLevelEnum getSauceLikeLevel()
    {
        return sauceLikeLevel;
    }

    public void setSauceLikeLevel(LikeLevelEnum sauceLikeLevel)
    {
        this.sauceLikeLevel = sauceLikeLevel;
    }

    public ArrayList<SauceType> getSauceTypes()
    {
        return sauces;
    }
    
    public void addSauceType(SauceType sauceType)
    {
        sauces.add(sauceType);
    }
    
    public void addSauceTypes(SauceType... sauceTypes)
    {
        sauces.addAll(Arrays.asList(sauceTypes));
    }
    
    public void removeSauceType(SauceType sauceType)
    {
        sauces.remove(sauceType);
    }
    
    public void clearSauceTypes()
    {
        sauces.clear();
    }
}
