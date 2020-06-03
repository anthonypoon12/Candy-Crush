/*
//Filename: Predator.java
//Name: Anthony Poon
//OSIS: 244832390
Description: Predator class extends the Critter class. By default, the predator's color is blue and its lifespan is 200 steps. Color and lifespan can be changed at construction.
A predator eats all actors except for rocks. It eats one random actor adjacent to it. Every time it eats, the predator changes color from blue to green to red to black.
Once the predator reaches black, it sprouts another predator on a random adjacent spot if possible. After becoming black, it will not change color or sprout another predator.
If the color is changed during construction of object, predator will continue to follow the same color sequence until it reaches black.
Predators will not eat other predators. Predators move to a random spot adjacent to it unless it ate an actor, in which it will take the spot previously occupied by the eaten actor.
Known bugs: None so far
*/
import info.gridworld.actor.*;
import java.util.ArrayList;
import java.awt.Color;
import info.gridworld.grid.*;
public class Predator extends Critter
{
  private int life = 0;
  private int lifespan = 200;
  private int kills = 0;
  private int dir=-1;
  public Predator ()
  {

  }
  public Predator(int newlife)
  {
    lifespan = newlife;
  }
  public Predator (Color newcolor)
  {
    if (newcolor==Color.GREEN)
    {
      kills=1;
      setColor(newcolor);
    }
    else if (newcolor==Color.RED)
    {
      kills=2;
      setColor(newcolor);
    }
    else if (newcolor==Color.BLACK)
    {
      kills=4;
      setColor(newcolor);
    }
  }
  public Predator (int newlife, Color newcolor)
  {
    lifespan = newlife;
    if (newcolor==Color.GREEN)
    {
      kills=1;
      setColor(newcolor);
    }
    else if (newcolor==Color.RED)
    {
      kills=2;
      setColor(newcolor);
    }
    else if (newcolor==Color.BLACK)
    {
      kills=4;
      setColor(newcolor);
    }
  }
  public void makeMove(Location loc)
  {
    Grid<Actor> gr = getGrid();
    Location loc1 = getLocation();
    if (life>=lifespan)//kill yourself and make a flower in your place
    {
      removeSelfFromGrid();
      Flower flower = new Flower(getColor());
      flower.putSelfInGrid(gr, loc1);
    }
    else
    {
      if(dir!=-1)//if you JUST ate something move in same direction
      {
        Location newloc = loc1.getAdjacentLocation(dir);
        for(Location dum: getMoveLocations())
        {
          if (dum.equals(newloc))
            moveTo(newloc);
        }
      }
      else
        super.makeMove(loc);
      if (!loc1.equals(getLocation()))
        life++;
    }
  }
  public void processActors(ArrayList<Actor> actors)
  {
    Grid<Actor> gr = getGrid();
    Location loc1 = getLocation();
    if (!(life>=lifespan))
    {
      ArrayList<Actor> dummy = new ArrayList<Actor>();
      for (Actor a : actors)
      {
          if (!(a instanceof Rock) && !(a instanceof Predator))
              dummy.add(a);
      }
      int randomnum = (int)(Math.random() * dummy.size());
      if (dummy.size()>0)
      {
        Location dummyloc = dummy.get(randomnum).getLocation();
        dir = loc1.getDirectionToward(dummyloc);
        dummy.get(randomnum).removeSelfFromGrid();
        kills++;
        if (kills==1)
          setColor(Color.GREEN);
        if (kills==2)
          setColor(Color.RED);
        if (kills==3)
        {
          setColor(Color.BLACK);
          if (getMoveLocations().size()>0)
          {
            int h = (int)(getMoveLocations().size()*Math.random());
            Predator baby = new Predator();
            baby.putSelfInGrid(gr,getMoveLocations().get(h));
          }
        }
      }
      else
        dir=-1;
    }
  }
}
