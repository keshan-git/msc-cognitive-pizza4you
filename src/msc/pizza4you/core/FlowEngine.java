package msc.pizza4you.core;

import java.util.ArrayList;
import msc.pizza4you.data.PizzaConfiguration;

/**
 *
 * @author Keshan De Silva
 */
public class FlowEngine
{
    public enum PanelType { START, INGREDIENT, CHEESE, SAUCE, TOPPINGS, RESULT }
        
    private static FlowEngine instance;
    private PanelType currentPanelType;
    private ArrayList<PanelType> panelTypeFlow;
    
    private FlowEngine()
    {
        panelTypeFlow = new ArrayList<>();
        panelTypeFlow.add(PanelType.START);
        panelTypeFlow.add(PanelType.TOPPINGS);
        panelTypeFlow.add(PanelType.RESULT);
    }
    
    public synchronized static FlowEngine getInstance()
    {
        if (instance == null)
        {
            instance = new FlowEngine();
        }
        
        return instance;
    }

    public void updateFlow(PizzaConfiguration pizzaConfiguration)
    {
        panelTypeFlow.clear();
        panelTypeFlow.add(PanelType.START);
        if (pizzaConfiguration != null)
        {
            if (pizzaConfiguration.getIngredientDetails() == null)
            {
                panelTypeFlow.add(PanelType.INGREDIENT);
            }

            if (pizzaConfiguration.getCheeseDetails() == null)
            {
                panelTypeFlow.add(PanelType.CHEESE);
            }

            if (pizzaConfiguration.getSauceDetails() == null)
            {
                panelTypeFlow.add(PanelType.SAUCE);
            }
        }
        else
        {
            panelTypeFlow.add(PanelType.INGREDIENT);
            panelTypeFlow.add(PanelType.CHEESE);
            panelTypeFlow.add(PanelType.SAUCE);
        }

        panelTypeFlow.add(PanelType.TOPPINGS);
        panelTypeFlow.add(PanelType.RESULT);
    }

    public void setCurrentPanel(PanelType panelType)
    {
        this.currentPanelType = panelType;
    }

    public PanelType getCurrentPanelType()
    {
        return currentPanelType;
    }

    public PanelType getPrePanelType()
    {
        int currentIndex = panelTypeFlow.indexOf(currentPanelType);
        return panelTypeFlow.get(currentIndex - 1);
    }

    public PanelType getNextPanelType()
    {
        int currentIndex = panelTypeFlow.indexOf(currentPanelType);
        return panelTypeFlow.get(currentIndex + 1);
    }
    
    public boolean hasMorePanels()
    {
        int currentIndex = panelTypeFlow.indexOf(currentPanelType);
        return currentIndex < panelTypeFlow.size() - 2;
    }

    public boolean hasPrevPanels()
    {
        return currentPanelType.ordinal() > 0;
    }
}
