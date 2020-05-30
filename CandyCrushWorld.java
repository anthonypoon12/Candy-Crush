public class CandyCrushWorld extends ActorWorld //ActorWorld edited by Chew
{
  public CandyCrushWorld//We need to decide dimensions of the grid
  {

  }
  public void fillWorld()//needs to detect for combos
  {
    Grid<T> gr = getGrid();
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
    if(x==0)
      return (new RedCandy());
    else if(x==1)
      return (new OrangeCandy());
    else if(x==2)
      return (new YellowCandy());
    else if(x==3)
      return (new GreenCandy());
    else if(x==4)
      return (new BlueCandy());
    else
      return (new PurpleCandy());
  }
}
