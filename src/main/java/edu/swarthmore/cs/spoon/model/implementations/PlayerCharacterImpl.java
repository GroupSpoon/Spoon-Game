package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.model.interfaces.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static edu.swarthmore.cs.spoon.model.interfaces.Direction.*;


public class PlayerCharacterImpl extends ThingImpl implements PlayerCharacter{
    //private String pcName;
    private int pcMaxSpoons = 9999;
    //private List<MovementListener> movementListenerList = new ArrayList<>();
    private List<MovStateChangeListener> movStateChangeListenerList = new ArrayList<>();
    private List<ThingEnteredLocListener> thingEnteredLocListenerList = new ArrayList<>();
    //Stats
    private int pcCurrSpoons = 0;
    private int pcCurrMoney = 0;
    private int pcCurrSoc = 0;
    private int pcCurrMove = 0;
    private int pcCurrHelp = 0;
    private Inventory pcInventory = null;

    //Game info
    //private Position pcCurrPos = null;
    //private Location pcCurrLoc = null;
    //private List<Action> pcActions = null;
    private Direction pcMovState = NONE;
    private PlayerState pcState;



    private String pcAppearance = null;


    //Info that does not apply to playercharacters
    //TODO: Figure out what to do about this
    private int pcCost = -1;
    private boolean pcSolidity = true;

    public PlayerCharacterImpl(String name, int id, Location loc, Position pos, ThingListenersHolder holder) {
        super(name, loc, pos, holder, ThingType.PC);
        this.thingId = id;

        this.pcState = new IdleStateImpl(this);
        //pcCurrLoc = loc;
        //this.getLocation().addThing(this);
        //pcCurrPos = pos;

    }


    @Override
    public int getSpoons() {
        return pcCurrSpoons;
    }

    @Override
    public void modifySpoons(int spoons) {
        pcCurrSpoons += spoons;
    }

    @Override
    public int getMoney() {
        return pcCurrMoney;
    }

    @Override
    public void modifyMoney(int money) {
        pcCurrMoney += money;
    }

    @Override
    public int getSocial() {
        return pcCurrSoc;
    }

    @Override
    public void modifySocial(int social) {
        pcCurrSoc += social;
    }

    @Override
    public int getMovement() {
        return pcCurrMove;
    }

    @Override
    public void modifyMovement(int movement) {
        pcCurrMove += movement;
    }

    @Override
    public int getHelpfulness() {
        return pcCurrHelp;
    }

    @Override
    public void modifyHelpfulness(int helpfulness) {
        pcCurrHelp += helpfulness;
    }

    @Override
    public void timestep() {
        if(this.pcState.timestep()){
         this.pcState = new IdleStateImpl(this);
        }


    }

    @Override
    public void setMovState(Direction dir) {
        if(pcMovState != dir){
            //The movstate is being set to something new, so we need to notify our listeners
            for(MovStateChangeListener listener : this.movStateChangeListenerList){
                listener.thingStateChanged(this.thingId, dir);
            }
        }
        pcMovState = dir;

        if(dir != Direction.NONE && this.getPlayerState() == StateType.IDLE){
            this.pcState = new MovingStateImpl(this);
        }
    }

    @Override
    public Direction getMovState(){
        return this.pcMovState;
    }


    @Override
    public StateType getPlayerState() {
        return pcState.getState();
    }

    @Override
    public void addPcEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener) {
        this.thingEnteredLocListenerList.add(thingEnteredLocListener);
    }

    @Override
    public Boolean removePcEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener) {
        return this.thingEnteredLocListenerList.remove(thingEnteredLocListener);
    }

    @Override
    public void addMovStateChangeListener(MovStateChangeListener movStateChangeListener) {
        this.movStateChangeListenerList.add(movStateChangeListener);
    }

    @Override
    public Boolean removeMovStateChangeListener(MovStateChangeListener movStateChangeListener) {
        return this.movStateChangeListenerList.remove(movStateChangeListener);
    }

    @Override
    public void attemptStep() {
        Direction currentDir = this.pcMovState;
        Position currentPos = this.thingCurrPos;
        Location currentLoc = this.thingCurrLoc;
        Position newPos = null;
        Optional<Position> maybePos;
        switch(currentDir){
            case UP:
                maybePos = this.thingCurrPos.move(5, currentDir); //Move the position up by 1
                if(!(maybePos.isPresent())){break;} //If the moveup method's return was null, break
                newPos = maybePos.get(); //The return was not null, so get the new position from it
                if(!(currentLoc.isValidPosition(newPos))){break;}//The new position is outside the bounds of the location, so we break
                if((currentLoc.isColliding(this, newPos))){break;}//The new position is outside the bounds of the location, so we break
                //currentLoc.removeThingFromPosition(this, currentPos);//Take the pc out of its old position in this location
                //currentLoc.setThingAtPosition(this, newPos); //Put the pc in the new position in this location
                this.setPosition(newPos); //update the pc's own pos
                break;
            case DOWN:
                maybePos = this.thingCurrPos.move(5, currentDir);
                if(!(maybePos.isPresent())){ System.out.println("maybePos is not present"); break;}
                newPos = maybePos.get();
                if(!(currentLoc.isValidPosition(newPos))){ System.out.println("newPos is not valid");
                    break;}//The new position is outside the bounds of the location
                if((currentLoc.isColliding(this, newPos))){break;}//The new position is outside the bounds of the location, so we break
                //currentLoc.removeThingFromPosition(this, currentPos);
                //currentLoc.setThingAtPosition(this, newPos);
                this.setPosition(newPos); //update the pc's own pos
                break;
            case LEFT:
                maybePos = this.thingCurrPos.move(5, currentDir);
                if(!(maybePos.isPresent())){break;}
                newPos = maybePos.get();
                if(!(currentLoc.isValidPosition(newPos))){break;}//The new position is outside the bounds of the location
                if((currentLoc.isColliding(this, newPos))){break;}//The new position is outside the bounds of the location, so we break
                //currentLoc.removeThingFromPosition(this, currentPos);
                //currentLoc.setThingAtPosition(this, newPos);
                this.setPosition(newPos); //update the pc's own pos
                break;
            case RIGHT:
                maybePos = this.thingCurrPos.move(5, currentDir);
                if(!(maybePos.isPresent())){break;}
                newPos = maybePos.get();
                if(!(currentLoc.isValidPosition(newPos))){break;}//The new position is outside the bounds of the location
                if((currentLoc.isColliding(this, newPos))){break;}//The new position is outside the bounds of the location, so we break
                //currentLoc.removeThingFromPosition(this, currentPos);
                //currentLoc.setThingAtPosition(this, newPos);
                this.setPosition(newPos); //update the pc's own pos
                break;
            case NONE:
                break;
        }
    }



    @Override
    public Inventory getInventory() {
        return pcInventory;
    }

    @Override
    public int getPrice() {
        return pcCost;
    }

    @Override
    public void setPrice(int price) {

    }

    @Override
    public Boolean isSolid() {
        return pcSolidity;
    }

    @Override
    public void setSolidity(Boolean solidity) {

    }

    @Override
    public void initiateAction(Action action, ActionEndListener listener) {
        action.setActor(this);
        action.addEndListener(listener);
        action.addEndListener(new ActionEndListener() {
            @Override
            public void actionEnded(int actionId, boolean success) {
                if (success) {
                    pcCurrSpoons += action.getActionSpoonCost();
                }
            }
        });
        this.pcState = new ActingStateImpl(action, this);
        System.out.println(this.pcCurrSpoons);
    }

    @Override
    public void attemptAct() {

    }
}
