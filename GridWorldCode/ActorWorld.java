/*
* AP(r) Computer Science GridWorld Case Study:
* Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
*
* This code is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation.
*
* This code is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
*
* @author Cay Horstmann
*/

package info.gridworld.actor;

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;

import java.util.ArrayList;

/**
* An <code>ActorWorld</code> is occupied by actors. <br />
* This class is not tested on the AP CS A and AB exams.
*/

public class ActorWorld extends World<Actor>
{
  private static final String DEFAULT_MESSAGE = "Click on a grid location to construct or manipulate an actor.";

  private Location selected;		//new variable to move individual actors


  /**
  * Constructs an actor world with a default grid.
  */
  public ActorWorld()
  {
    selected = null;
  }

  /**
  * Constructs an actor world with a given grid.
  * @param grid the grid for this world.
  */
  public ActorWorld(Grid<Actor> grid)
  {
    super(grid);
    selected = null;
  }

  public void show()
  {
    if (getMessage() == null)
      setMessage(DEFAULT_MESSAGE);
    super.show();
  }



  //NEW method to do something
  public void stuff()
  {
    Grid<Actor> g = getGrid();

    Actor obj;

    ArrayList<Actor> sorted = new ArrayList<Actor>();
    for(int i = 0; i < g.getNumRows(); i++)
    {
      for(int j = 0; j < g.getNumCols(); j++)
      {
        obj = g.get(new Location(i, j));
        if(obj != null)
          sorted.add(obj);
      }
    }


    for (int k = 0; k < sorted.size(); k++)
    {
      Actor a = sorted.get(k);
      Location loc = a.getLocation();
      Location next = new Location(loc.getRow()-1, loc.getCol());
      if(g.isValid(next) && g.get(next) == null)	//check to make sure it's in the grid & not overlapping another Actor
      {
        a.moveTo(next);
        setMessage(""+sorted.size());
      }
    }




  }


  public boolean keyPressed(String description, Location loc)
  {
    setMessage(description);   //Changes the message to display the 'keypressed', use to debug
    int x = 0;
    int y = 0;
    if(description.equals("W"))
      y = -1;
    if(description.equals("A"))
      x = -1;
    if(description.equals("D"))
      x = 1;
    if(description.equals("X"))
      y = 1;
    Grid<Actor> g = getGrid();

    if((x != 0 || y != 0) && selected != null && g.isValid(selected))
    {
      Actor obj = g.get(selected);
      if (obj != null )
      {
        selected = new Location(selected.getRow() + y, selected.getCol() + x);
        obj.moveTo(selected);
      }
    }
    return false;
  }


  public void step()
  {
    Grid<Actor> gr = getGrid();
    ArrayList<Actor> actors = new ArrayList<Actor>();
    for (Location loc : gr.getOccupiedLocations())
      actors.add(gr.get(loc));

    for (Actor a : actors)
    {
      // only act if another actor hasn't removed a
      if (a.getGrid() == gr)
        a.act();
    }
  }

  /**
  * Adds an actor to this world at a given location.
  * @param loc the location at which to add the actor
  * @param occupant the actor to add
  */
  public void add(Location loc, Actor occupant)
  {
    occupant.putSelfInGrid(getGrid(), loc);
  }

  /**
  * Adds an occupant at a random empty location.
  * @param occupant the occupant to add
  */
  public void add(Actor occupant)
  {
    Location loc = getRandomEmptyLocation();
    if (loc != null)
      add(loc, occupant);
  }

  /**
  * Removes an actor from this world.
  * @param loc the location from which to remove an actor
  * @return the removed actor, or null if there was no actor at the given
  * location.
  */
  public Actor remove(Location loc)
  {
    Actor occupant = getGrid().get(loc);
    if (occupant == null)
      return null;
    occupant.removeSelfFromGrid();
    return occupant;
  }

  //overrided
  public boolean locationClicked(Location loc)
  {
    setMessage(loc.toString());     //Changes the message to the 'location' clicked.

    selected = loc;

    return false;
  }

}
