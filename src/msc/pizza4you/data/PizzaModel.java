package msc.pizza4you.data;

/**
 *
 * @author Keshan De Silva
 */
public class PizzaModel implements Comparable<PizzaModel>
{
    private String name;
    private int certainty;
    private PizzaDetails pizzaDetails;

    public PizzaModel(String name, int certainty, PizzaDetails pizzaDetails)
    {
        this.name = name;
        this.certainty = certainty;
        this.pizzaDetails = pizzaDetails;
    }
    
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getCertainty()
    {
        return certainty;
    }

    public void setCertainty(int certainty)
    {
        this.certainty = certainty;
    }
    
    public String getCertaintyString()
    {
        return certainty + " %";
    }

    public PizzaDetails getPizzaDetails()
    {
        return pizzaDetails;
    }

    @Override
    public int compareTo(PizzaModel pizzaModel)
    {
        return pizzaModel.getCertainty() - this.certainty;
    }
}
