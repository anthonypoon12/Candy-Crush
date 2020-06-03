
import info.gridworld.actor.*;
import info.gridworld.grid.*;

public class PredatorRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 4), new Predator());
        world.show();
        world.add(new Rock());
    }
}
