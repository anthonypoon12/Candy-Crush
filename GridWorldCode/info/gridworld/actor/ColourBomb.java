package info.gridworld.actor;
import java.awt.Color;
public class ColourBomb extends Candy
{
  public ColourBomb()
  {
    super();
    changePowerUp(3);
    setColor(new Color(210,105,30));
    changeType(0);
  }
}
