public class Candy extends Critter
{
  //returns an arraylist of all locations of candies in a possible combination
  private int candynumber = 0;//refers to the type of candy
  public ArrayList<Location> detect()
  {
    ArrayList<Location> combolist = new ArrayList<Location>();
    combolist=detecthor(combolist, getLocation(),false);
    if (combolist.size()>=0)
    {
      for (Location l: combolisthor)
      {
        combolist = detectorvert(combolist,l,true);
      }
      return combolist;
    }
    combolist=detectvert(combolist, getLocation(),false);
    if (combolist.size()>=0)
    {
      for (Location l: combolistvert)
      {
        combolist = detectorhor(combolist,l,true);
      }
    }
    return combolist;
  }
  public void act()//needs to be changed
  {

  }
  public void switch(Candy candy)//switches this candy with one next to it
  {

  }
  public int getType()//gets the candy number
  {
    return candynumber;
  }
  public ArrayList<Location> detecthor(ArrayList<Location> list, Location x, boolean secondary) //checks horizontally. if total matches are less than 3, it returns original list. else, it adds to it.
  {
    Location loc = x;
    ArrayList<Location> dummyhor = list;
    if (secondary)
      dummyhor.add(loc);
    dummyhor = detectright(dummyhor, loc);
    dummyhor=detectleft(dummyhor, loc);
    if (dummyhor.size()>=3)
      return dummyhor;
    else
      return list;
  }
  public ArrayList<Location> detectright(ArrayList<Location> list, Location x)
  {
    ArrayList<Location> dummyrightleft = list;
    Grid<Actor> gr = getGrid();
    Location loc = x;
    Location oneright = loc.getAdjacentLocation(Location.RIGHT);
    if ((oneright!=null) && (gr.get(oneright).getType()==candynumber))//if candy directly to right is same type
    {
      dummyrightleft.add(oneright);
      Location tworight = loc.getAdjacentLocation(Location.RIGHT).getAdjacentLocation(Location.RIGHT);
      if ((tworight!=null)&&(gr.get(tworight).getType()==candynumber))//if candy 2 to the right is same type
      {
        dummyrightleft.add(tworight);
        Location threeright = loc.getAdjacentLocation(Location.RIGHT).getAdjacentLocation(Location.RIGHT).getAdjacentLocation(Location.RIGHT);
        if ((threeright!=null)&&(gr.get(threeright).getType()==candynumber))
        {
          dummyrightleft.add(threeright);
          Location fourright = loc.getAdjacentLocation(Location.RIGHT).getAdjacentLocation(Location.RIGHT).getAdjacentLocation(Location.RIGHT).getAdjacentLocation(Location.RIGHT);
          if ((fourright!=null)&&(gr.get(fourright).getType()==candynumber))
            dummyrightleft.add(fourright);
        }
      }
    }
    return dummyright;
  }
  public ArrayList<Location> detectleft(ArrayList<Location> list, Location x)
  {
    ArrayList<Location> dummyleft = list;
    Grid<Actor> gr = getGrid();
    Location loc = x;
    Location oneleft = loc.getAdjacentLocation(Location.LEFT);
    if ((oneleft!=null) && (gr.get(oneleft).getType()==candynumber))//if candy directly to left is same type
    {
      dummyleft.add(oneleft);
      Location twoleft = loc.getAdjacentLocation(Location.LEFT).getAdjacentLocation(Location.LEFT);
      if ((twoleft!=null)&&(gr.get(twoleft).getType()==candynumber))//if candy 2 to the left is same type
      {
        dummyleft.add(twoleft);
        Location threeleft = loc.getAdjacentLocation(Location.LEFT).getAdjacentLocation(Location.LEFT).getAdjacentLocation(Location.LEFT);
        if ((threeleft!=null)&&(gr.get(threeleft).getType()==candynumber))
        {
          dummyleft.add(threeleft);
          Location fourleft = loc.getAdjacentLocation(Location.LEFT).getAdjacentLocation(Location.LEFT).getAdjacentLocation(Location.LEFT).getAdjacentLocation(Location.LEFT);
          if ((fourleft!=null)&&(gr.get(fourleft).getType()==candynumber))
            dummyleft.add(fourleft);
        }
      }
    }
    return dummyleft;
  }
  public ArrayList<Location> detectvert(ArrayList<Location> list, Location x, boolean secondary)
  {
    Location loc = x;
    ArrayList<Location> dummyvert = list;
    if (secondary)
      dummyvert.add(loc);
    dummyvert = detectup(dummyvert, loc);
    dummyvert=detectdown(dummyvert, loc);
    if (dummyvert.size()>=3)
      return dummyvert;
    else
      return list;
  }
  public ArrayList<Location> detectup(ArrayList<Location> list, Location x)
  {
    ArrayList<Location> dummyup = list;
    Grid<Actor> gr = getGrid();
    Location loc = x;
    Location oneup = loc.getAdjacentLocation(Location.UP);
    if ((oneup!=null) && (gr.get(oneup).getType()==candynumber))//if candy directly to up is same type
    {
      dummyup.add(oneup);
      Location twoup = loc.getAdjacentLocation(Location.UP).getAdjacentLocation(Location.UP);
      if ((twoup!=null)&&(gr.get(twoup).getType()==candynumber))//if candy 2 to the up is same type
      {
        dummyup.add(twoup);
        Location threeup = loc.getAdjacentLocation(Location.UP).getAdjacentLocation(Location.UP).getAdjacentLocation(Location.UP);
        if ((threeup!=null)&&(gr.get(threeup).getType()==candynumber))
        {
          dummyup.add(threeup);
          Location fourup = loc.getAdjacentLocation(Location.UP).getAdjacentLocation(Location.UP).getAdjacentLocation(Location.UP).getAdjacentLocation(Location.UP);
          if ((fourup!=null)&&(gr.get(fourup).getType()==candynumber))
            dummyup.add(fourup);
        }
      }
    }
    return dummyup;
  }
  public ArrayList<Location> detectdown(ArrayList<Location> list, Location x)
  {
    ArrayList<Location> down = list;
    Grid<Actor> gr = getGrid();
    Location loc = x;
    Location onedown = loc.getAdjacentLocation(Location.DOWN);
    if ((onedown!=null) && (gr.get(onedown).getType()==candynumber))//if candy directly to down is same type
    {
      dummydown.add(onedown);
      Location twodown = loc.getAdjacentLocation(Location.DOWN).getAdjacentLocation(Location.DOWN);
      if ((twodown!=null)&&(gr.get(twodown).getType()==candynumber))//if candy 2 to the down is same type
      {
        dummydown.add(twodown);
        Location threedown = loc.getAdjacentLocation(Location.DOWN).getAdjacentLocation(Location.DOWN).getAdjacentLocation(Location.DOWN);
        if ((threedown!=null)&&(gr.get(threedown).getType()==candynumber))
        {
          dummydown.add(threedown);
          Location fourdown = loc.getAdjacentLocation(Location.DOWN).getAdjacentLocation(Location.DOWN).getAdjacentLocation(Location.DOWN).getAdjacentLocation(Location.DOWN);
          if ((fourdown!=null)&&(gr.get(fourdown).getType()==candynumber))
            dummydown.add(fourdown);
        }
      }
    }
    return dummydown;
  }
}
