package msc.pizza4you.data;

/**
 *
 * @author Keshan De Silva
 */
public class CheeseDetails
{
    private LikeLevelEnum cheeseLikeLevel;

    public CheeseDetails()
    {
    }

    public CheeseDetails(LikeLevelEnum cheeseLikeLevel)
    {
        this.cheeseLikeLevel = cheeseLikeLevel;
    }

    public LikeLevelEnum getCheeseLikeLevel()
    {
        return cheeseLikeLevel;
    }

    public void setCheeseLikeLevel(LikeLevelEnum cheeseLikeLevel)
    {
        this.cheeseLikeLevel = cheeseLikeLevel;
    }
}
