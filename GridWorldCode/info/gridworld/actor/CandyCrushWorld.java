package info.gridworld.actor;
import info.gridworld.actor.*;
import java.util.ArrayList;
import info.gridworld.grid.*;
import java.awt.Color;
import info.gridworld.world.*;
import info.gridworld.gui.*;
public class CandyCrushWorld extends ActorWorld //ActorWorld edited by Chew
{
  public void fillWorld()//needs to detect for combos
  {
    boolean restart=true;
    Grid<Actor> gr = getGrid();
    int rows = gr.getNumRows();
    int cols = gr.getNumCols();
    if (rows > 0 && cols > 0) // bounded grid
    {
        while (restart)
        {
          // get all valid empty locations (Copied from World.java in the getRandomEmptyLocation())
          ArrayList<Location> emptyLocs = new ArrayList<Location>();
          for (int i = 0; i < rows; i++)
              for (int j = 0; j < cols; j++)
              {
                  Location loc = new Location(i, j);
                  if (gr.isValid(loc) && gr.get(loc) == null)
                      emptyLocs.add(loc);
              }
            for (Location emptyloc: emptyLocs)
            {
              add(emptyloc, randomCandy());
            }
            gridDetect();
            if (getRandomEmptyLocation()==null)
              restart=false;
        }
        Candy.score=0;
    }
  }
  public void gridDetect()
  {
    boolean stop=false;
    Grid<Actor> gr = getGrid();
    int rows = gr.getNumRows();
    int cols = gr.getNumCols();
    for (int i = 0; (i < rows)&&(!stop); i++)
      for (int j = 2-(i%3); (j < cols)&&(!stop); j+=3)
      {
        Location loc = new Location(i, j);
        if (gr.isValid(loc))
        {
          if (gr.get(loc) instanceof Candy)
          {
            Candy candy = (Candy)gr.get(loc);
            gridDetect2(candy);
            if (getRandomEmptyLocation()!=null)
              stop=true;
          }
        }
      }
  }
  public void gridDetect2(Candy candy)
  {
    ArrayList<Location> combolist;
    combolist = candy.detect();
    if (combolist.size()>=3)
    {
      candy.destroy(combolist);
    }
  }

  public Candy randomCandy()//returns a random candy
  {
    int x =(int)(Math.random()*6);
    if(x==1)
      return (new RedCandy());
    else if(x==2)
      return (new OrangeCandy());
    else if(x==3)
      return (new YellowCandy());
    else if(x==4)
      return (new GreenCandy());
    else if(x==5)
      return (new BlueCandy());
    else
      return (new PurpleCandy());
  }

  public void setScore(int score, int turns)
  {
    setMessage("Score: " + score  + "\t Turns: " + turns + "     \b!Candy Crush!\b");
  }

  public void Gravity()
    { //invoking once will make all actors with an empty space below drop one space down
    Grid<Actor> g = getGrid();
    Actor obj;
    ArrayList<Actor> sorted = new ArrayList<Actor>();
    for(int i = g.getNumRows()-1; i >=0; i--){
      for(int j = 0; j < g.getNumCols(); j++){
        obj = g.get(new Location(i, j));
        if(obj != null)
          sorted.add(obj);
      }
    }
    for (int k = 0; k < sorted.size(); k++){
      Actor a = sorted.get(k);
      Location loc = a.getLocation();
	    Location next = new Location(loc.getRow()+1, loc.getCol());
	    if(g.isValid(next) && g.get(next) == null)
        a.moveTo(next);
    }
  }
    public void refill(){  //only refills the top row
      Grid<Actor> g = getGrid();
      for(int j = 0; j < g.getNumCols(); j++){
	  Location filling= new Location(0, j);
	  Candy creating= randomCandy();
	  if(g.isValid(filling) && g.get(filling)==null)
	      creating.putSelfInGrid(g,filling);
      }
    }

}
