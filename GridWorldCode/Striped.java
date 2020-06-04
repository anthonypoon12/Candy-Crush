public class Striped extends Candy
{
  private horizontal = false;
  private vertical = false;
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
  public changeHorizontal(boolean x)
  {
    horizontal=x;
  }
  public changeVertical(boolean x)
  {
    vertical=x;
  }
}
