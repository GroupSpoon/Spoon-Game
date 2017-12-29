package edu.swarthmore.cs.spoon.model.implementations;
import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;

import java.util.ArrayList;
import java.util.List;


public class LocationImpl implements Location {
    private int locID;
    private String locName = null;
    private int width;
    private int height;
    private List<LocCreatedThingListener> locCreatedThingListenerList = new ArrayList<>();
    private List<Thing> thingList; //List of all the Things in this Location
    private List<Position> entrances = new ArrayList<>(); //List of the Positions that can move you in or out of this location



    public LocationImpl(String name, int height, int width){
        this.locID = IDManager.assignId();
        this.thingList = new ArrayList<>();
        this.locName = name;
        this.width = width;
        this.height = height;
        //THE BELOW POSITION IS A TEST
        //TODO: MAKE ENTRANCES WORK FOR REAL
        Position testEntrance = new PositionImpl(511, 511, 32, 32);
        this.entrances.add(testEntrance);



    }

    @Override
    public int getLocationID() {
        return locID;
    }

    @Override
    public void setLocationName(String string) {
        locName = string;
    }

    @Override
    public String getLocationName() {
        return locName;
    }

    @Override
    public int getLocationHeight() {
        return this.height;
    }

    @Override
    public int getLocationWidth() {
        return this.width;
    }

    @Override
    public Boolean isValidPosition(Position position) {

        for(Point pt : position.getPoints()){
            int ptX = pt.getX();
            int ptY = pt.getY();
            if(ptX > this.width || ptX < 0 || ptY > this.height || ptY < 0){
                return false;
            }
        }
        return true;
    }

    @Override
    public Boolean isColliding(Thing collider, Position pos1) {
        for(Thing thing : thingList) {
            if(!(thing.getId() == collider.getId()) && thing.getId() > 99 && thing.isSolid()){ //If this Thing is not a pc and is solid
            Position pos2 = thing.getPosition();
            //    var rect1 = {x: 5, y: 5, width: 50, height: 50}
            //    var rect2 = {x: 20, y: 10, width: 10, height: 10}
            //
//        if (rect1.x < rect2.x + rect2.width &&
//        rect1.x + rect1.width > rect2.x &&
//        rect1.y < rect2.y + rect2.height &&
//        rect1.height + rect1.y > rect2.y) {
//        // collision detected!
//        }
// The above code is from https://developer.mozilla.org/en-US/docs/Games/Techniques/2D_collision_detection
            if (pos1.getULX() < pos2.getULX() + pos2.getWidth() &&
                    pos1.getULX() + pos1.getWidth() > pos2.getULX() &&
                    pos1.getULY() < pos2.getULY() + pos2.getHeight() &&
                    pos1.getHeight() + pos1.getULY() > pos2.getULY()) {
                System.out.println(collider.getName() + "bumped into" + thing.getName());
                System.out.println("You bumped into me! Why would you do that?");
                return true;
            }
        }
        }
        return false;
    }

    @Override
    public Boolean addThing(Thing thing) {
        if(this.thingList.contains(thing)){
            //the Thing we want to add is already here
            return false;
        }
        else{
            this.thingList.add(thing);
            //Now that we have added the Thing to this location, we should make sure the Thing knows
            //that it is here.
            //if(thing.getLocation() != null){
                //If the thing already has a location, does it already have *this* location?
                //TODO: figure out whether this will ever actually happen, considering we already checked if the location contains this thing

            //}
            thing.setLocation(this);
            thing.setPosition(this.entrances.get(0));
            return true;
        }
    }

    @Override
    public Boolean removeThing(Thing thing) {
        return this.thingList.remove(thing);
    }

    @Override
    public List<Thing> getThings() {
        return this.thingList;
    }

    @Override
    public List<Position> getEntrances() {
        return this.entrances;
    }

    @Override
    public void addLocCreatedThingListener(LocCreatedThingListener locCreatedThingListener) {
        this.locCreatedThingListenerList.add(locCreatedThingListener);
    }

    @Override
    public Boolean removeLocCreatedThingListener(LocCreatedThingListener locCreatedThingListener) {
        return this.locCreatedThingListenerList.remove(locCreatedThingListener);
    }


    @Override
    public Boolean locationActive() {
        return null;
    }

    @Override
    public List<Location> accessibleLocations() {
        return null;
    }





}
