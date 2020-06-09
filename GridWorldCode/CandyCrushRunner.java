import info.gridworld.actor.*;
import info.gridworld.grid.*;
import info.gridworld.world.*;
import java.awt.Color;
//check imports
public class CandyCrushRunner
{
  public static void main(String[] args)
  {
      CandyCrushWorld world = new CandyCrushWorld();
      world.fillWorld();
      world.show();
      int MAX_TURNS = 20;
      int score;
      int turns = 0;
      /*while(turns < MAX_TURNS)
      {
        score = Candy.getScore();
        world.setScore(score, turns);
        turns = Candy.getTurns();
      }*/
  }
}
