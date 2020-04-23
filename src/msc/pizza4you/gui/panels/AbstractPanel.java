package msc.pizza4you.gui.panels;

import java.util.ArrayList;
import javax.swing.JPanel;
import msc.pizza4you.data.IngredientDetails.IngredientType;
import msc.pizza4you.data.PizzaConfiguration;
import msc.pizza4you.data.ToppingDetails.ToppingType;
import msc.pizza4you.gui.components.KeywordLabel;

/**
 *
 * @author Keshan De Silva
 */
public abstract class AbstractPanel extends JPanel
{
    public abstract String getPanelTitle();
    public abstract PizzaConfiguration updatePizzaConfigurations();
    public abstract JPanel getKeywordPanel();
    
    public void updateKeywords()
    {
        ArrayList<KeywordLabel> keywordPanels = new ArrayList<>();
        PizzaConfiguration pizzaConfiguration = updatePizzaConfigurations();
        
        if (pizzaConfiguration.getSauceDetails() != null)
        {
            keywordPanels.add(new KeywordLabel("SAUCE " + pizzaConfiguration.getSauceDetails().getSauceLikeLevel().toString()));
        }
        
        if (pizzaConfiguration.getCheeseDetails() != null)
        {
            keywordPanels.add(new KeywordLabel("CHEESE " + pizzaConfiguration.getCheeseDetails().getCheeseLikeLevel().toString()));
        }

        if (pizzaConfiguration.getIngredientDetails() != null)
        {
            for (IngredientType ingredientType : pizzaConfiguration.getIngredientDetails().getIngredients())
            {
                keywordPanels.add(new KeywordLabel(ingredientType.toString()));
            }
        }
        
        if (pizzaConfiguration.getToppingDetails() != null)
        {
            for (ToppingType toppingType : pizzaConfiguration.getToppingDetails().getToppings())
            {
                if (toppingType != null)
                {
                    keywordPanels.add(new KeywordLabel(toppingType.toString()));
                }
            }
        }
        
        JPanel keywordPanel = getKeywordPanel();
        keywordPanel.removeAll();
        
        for (KeywordLabel keywordLabel : keywordPanels)
        {
            keywordPanel.add(keywordLabel);
        }
        
        keywordPanel.revalidate();
        keywordPanel.updateUI();
    }
}
