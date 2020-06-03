import info.gridworld.actor.*;
import java.util.ArrayList;
import info.gridworld.grid.*;
public class Candy extends Critter
{
  public Candy()
  {
    super();
    candynumber = 0;//refers to the type of candy
  }
  public ArrayList<Location> detect()
  //returns an arraylist of all locations of candies in a possible combination
  {
    ArrayList<Location> combolist = new ArrayList<Location>();
    combolist=detecthor(combolist, getLocation(),false);
    if (combolist.size()>0)
    {
      if (combolist.size()<5)
      {
        for (Location l: combolist)
        {
          combolist = detectvert(combolist,l,true);
        }
      }
      return combolist;
    }
    combolist=detectvert(combolist, getLocation(),false);
    if (combolist.size()>0)
    {
      if (combolist.size()<5)
      {
        for (Location l: combolist)
        {
          combolist = detecthor(combolist,l,true);
        }
      }
    }
    return combolist;
  }
  public void fullswitch(Candy candy1)
  //switches candy with one next to it, detects if either has combo. If either combolists are larger than 3 elements, candies from that list are destroyed and other candy stays in place. Otherwise, they switch back.
  {
    switchCandy(candy1);
    ArrayList<Location> combolist;
    ArrayList<Location> combolist2;
    combolist = detect();
    combolist2 = candy1.detect();
    if (combolist.size()>=3)
      destroy(combolist);
    if (combolist2.size()>=3)
      destroy(combolist2);
    if ((combolist.size()>=3)&&(combolist2.size()>=3))
      switchCandy(candy1);
  }
  public void switchCandy(Candy candy)
  //switches this candy with one next to it
  {
    Grid<Actor> gr = getGrid();
    ArrayList<Location> savedlocs = new ArrayList<Location>();
    savedlocs.add(getLocation());
    savedlocs.add(candy.getLocation());
    candy.removeSelfFromGrid();
    removeSelfFromGrid();
    candy.putSelfInGrid(gr,savedlocs.get(0));
    putSelfInGrid(gr,savedlocs.get(1));
  }
  public ArrayList<Location> detecthor(ArrayList<Location> list, Location x, boolean secondary)
  //checks horizontally for 3 or more in a row. if total matches are less than 3, it returns original list. else, it returns the list PLUS all new candies horizontally. If this method is used as a secondary method and the total matches are 3 or more, the original location will not be added to the output.
  {
    Location loc = x;
    ArrayList<Location> dummyhor = list;
    if (!secondary)
      dummyhor.add(loc);
    dummyhor = detectright(dummyhor, loc);
    dummyhor=detectleft(dummyhor, loc);
    if (dummyhor.size()>=3)
      return dummyhor;
    else
      return list;
  }
  public ArrayList<Location> detectright(ArrayList<Location> list, Location x)
  //adds all locations to the right that match in a row to the list and returns the list. (up to four additions)
  {
    ArrayList<Location> dummyright = list;
    Grid<Actor> gr = getGrid();
    Location loc = x;
    Location oneright = loc.getAdjacentLocation(90);
    if ((oneright!=null) && (gr.get(oneright).getType()==candynumber))//if candy directly to right is same type
    {
      dummyright.add(oneright);
      Location tworight = loc.getAdjacentLocation(90).getAdjacentLocation(90);
      if ((tworight!=null)&&(gr.get(tworight).getType()==candynumber))//if candy 2 to the right is same type
      {
        dummyright.add(tworight);
        Location threeright = loc.getAdjacentLocation(90).getAdjacentLocation(90).getAdjacentLocation(90);
        if ((threeright!=null)&&(gr.get(threeright).getType()==candynumber))
        {
          dummyright.add(threeright);
          Location fourright = loc.getAdjacentLocation(90).getAdjacentLocation(90).getAdjacentLocation(90).getAdjacentLocation(90);
          if ((fourright!=null)&&(gr.get(fourright).getType()==candynumber))
            dummyright.add(fourright);
        }
      }
    }
    return dummyright;
  }
  public ArrayList<Location> detectleft(ArrayList<Location> list, Location x)//adds all locations to the left that match in a row to the list and returns the list. (up to four additions)
  {
    ArrayList<Location> dummyleft = list;
    Grid<Actor> gr = getGrid();
    Location loc = x;
    Location oneleft = loc.getAdjacentLocation(270);
    if ((oneleft!=null) && (gr.get(oneleft).getType()==candynumber))//if candy directly to left is same type
    {
      dummyleft.add(oneleft);
      Location twoleft = loc.getAdjacentLocation(270).getAdjacentLocation(270);
      if ((twoleft!=null)&&(gr.get(twoleft).getType()==candynumber))//if candy 2 to the left is same type
      {
        dummyleft.add(twoleft);
        Location threeleft = loc.getAdjacentLocation(270).getAdjacentLocation(270).getAdjacentLocation(270);
        if ((threeleft!=null)&&(gr.get(threeleft).getType()==candynumber))
        {
          dummyleft.add(threeleft);
          Location fourleft = loc.getAdjacentLocation(270).getAdjacentLocation(270).getAdjacentLocation(270).getAdjacentLocation(270);
          if ((fourleft!=null)&&(gr.get(fourleft).getType()==candynumber))
            dummyleft.add(fourleft);
        }
      }
    }
    return dummyleft;
  }
  public ArrayList<Location> detectvert(ArrayList<Location> list, Location x, boolean secondary)
  //checks vertically for 3 or more in a row. if total matches are less than 3, it returns original list. else, it returns the list PLUS all new candies vertically. If this method is used as a secondary method and the total matches are 3 or more, the original location will not be added to the output.
  {
    Location loc = x;
    ArrayList<Location> dummyvert = list;
    if (!secondary)
      dummyvert.add(loc);
    dummyvert = detectup(dummyvert, loc);
    dummyvert=detectdown(dummyvert, loc);
    if (dummyvert.size()>=3)
      return dummyvert;
    else
      return list;
  }
  public ArrayList<Location> detectup(ArrayList<Location> list, Location x)//adds all locations above that match in a row to the list and returns the list. (up to four additions)
  {
    ArrayList<Location> dummyup = list;
    Grid<Actor> gr = getGrid();
    Location loc = x;
    Location oneup = loc.getAdjacentLocation(0);
    if ((oneup!=null) && (gr.get(oneup).getType()==candynumber))//if candy directly to up is same type
    {
      dummyup.add(oneup);
      Location twoup = loc.getAdjacentLocation(0).getAdjacentLocation(0);
      if ((twoup!=null)&&(gr.get(twoup).getType()==candynumber))//if candy 2 to the up is same type
      {
        dummyup.add(twoup);
        Location threeup = loc.getAdjacentLocation(0).getAdjacentLocation(0).getAdjacentLocation(0);
        if ((threeup!=null)&&(gr.get(threeup).getType()==candynumber))
        {
          dummyup.add(threeup);
          Location fourup = loc.getAdjacentLocation(0).getAdjacentLocation(0).getAdjacentLocation(0).getAdjacentLocation(0);
          if ((fourup!=null)&&(gr.get(fourup).getType()==candynumber))
            dummyup.add(fourup);
        }
      }
    }
    return dummyup;
  }
  public ArrayList<Location> detectdown(ArrayList<Location> list, Location x)//adds all locations below that match in a row to the list and returns the list. (up to four additions)
  {
    ArrayList<Location> dummydown = list;
    Grid<Actor> gr = getGrid();
    Location loc = x;
    Location onedown = loc.getAdjacentLocation(180);
    if ((onedown!=null) && (gr.get(onedown).getType()==candynumber))//if candy directly to down is same type
    {
      dummydown.add(onedown);
      Location twodown = loc.getAdjacentLocation(180).getAdjacentLocation(180);
      if ((twodown!=null)&&(gr.get(twodown).getType()==candynumber))//if candy 2 to the down is same type
      {
        dummydown.add(twodown);
        Location threedown = loc.getAdjacentLocation(180).getAdjacentLocation(180).getAdjacentLocation(180);
        if ((threedown!=null)&&(gr.get(threedown).getType()==candynumber))
        {
          dummydown.add(threedown);
          Location fourdown = loc.getAdjacentLocation(180).getAdjacentLocation(180).getAdjacentLocation(180).getAdjacentLocation(180);
          if ((fourdown!=null)&&(gr.get(fourdown).getType()==candynumber))
            dummydown.add(fourdown);
        }
      }
    }
    return dummydown;
  }
  public void createSameTypeHere(Location x)
  //A candy will produce the same type candy at a given location
  {
    Grid<Actor> gr = getGrid();
    Candy newcandy=null;
    if (candynumber==1)
      newcandy = new BlueCandy();
    if (candynumber==2)
      newcandy= new GreenCandy();
    if (candynumber==3)
      newcandy= new OrangeCandy();
    if (candynumber==4)
      newcandy= new PurpleCandy();
    if (candynumber==5)
      newcandy= new RedCandy();
    if (candynumber==6)
      newcandy= new YellowCandy();
    newcandy.putSelfInGrid(gr,x);
  }
  public void createThisTypeHere(Location x, int number)
  //A candy will produce the type of candy that it specifies at a given location
  {
    Grid<Actor> gr = getGrid();
    Candy newcandy=null;
    if (number==1)
      newcandy= new BlueCandy();
    if (number==2)
      newcandy= new GreenCandy();
    if (number==3)
      newcandy= new OrangeCandy();
    if (number==4)
      newcandy= new PurpleCandy();
    if (number==5)
      newcandy= new RedCandy();
    if (number==6)
      newcandy= new YellowCandy();
    newcandy.putSelfInGrid(gr,x);
  }
  public Candy createSameType()
  //returns a new candy of the same type
  {
    Candy newcandy=null;
    if (candynumber==1)
      newcandy= new BlueCandy();
    if (candynumber==2)
      newcandy= new GreenCandy();
    if (candynumber==3)
      newcandy= new OrangeCandy();
    if (candynumber==4)
      newcandy= new PurpleCandy();
    if (candynumber==5)
      newcandy= new RedCandy();
    if (candynumber==6)
      newcandy= new YellowCandy();
    return newcandy;
  }
  public void destroy(ArrayList<Location> list)
  {
    Grid<Actor> gr = getGrid();
    for (Location l: list)
    {
      gr.remove(l);
    }
  }
}