import info.gridworld.actor.*;
import java.util.ArrayList;
import info.gridworld.grid.*;
import java.awt.Color;
public class CandyCrushWorld extends ActorWorld //ActorWorld edited by Chew
{
  public CandyCrushWorld()//We need to decide dimensions of the grid
  {

  }
  public void fillWorld()//needs to detect for combos
  {
    Grid<Actor> gr = getGrid();
    int rows = gr.getNumRows();
    int cols = gr.getNumCols();
    if (rows > 0 && cols > 0) // bounded grid
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
  public void Gravity()
    { //invoking once will make all actors with an empty space below drop one space down
    Grid<Actor> g = getGrid();
    Actor obj;
    ArrayList<Actor> sorted = new ArrayList<Actor>();
    for(int i = g.getNumRows()-1; i >0; i--){
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
}
