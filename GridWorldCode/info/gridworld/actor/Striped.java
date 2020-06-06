public class Striped extends Candy
{
  private boolean horizontal = false;
  private boolean vertical = false;
  public Striped()
  {
    super();
    changePowerUp(1);
  }
  public boolean isHorizontal()
  {
    return horizontal;
  }
  public boolean isVertical()
  {
    return vertical;
  }
  public void changeHorizontal(boolean x)
  {
    horizontal=x;
  }
  public void changeVertical(boolean x)
  {
    vertical=x;
  }
}
