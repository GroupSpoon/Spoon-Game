package edu.swarthmore.cs.spoon.client.network.model;

import edu.swarthmore.cs.spoon.common.messages.IdentifierMessage;
import edu.swarthmore.cs.spoon.common.messages.IdentifierResponse;
import edu.swarthmore.cs.spoon.model.interfaces.*;
import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;
import edu.swarthmore.cs.spoon.common.messages.Message;
import edu.swarthmore.cs.spoon.common.messages.Requests;

import java.util.ArrayList;
import java.util.List;

public class PlayerCharacterClient extends ThingClient implements PlayerCharacter {

    protected String name;
    private Action currentAction;
    private List<ThingEnteredLocListener> locChangeListeners = new ArrayList<>();
    private List<MovStateChangeListener> movStateListeners = new ArrayList<>();

    public PlayerCharacterClient(GameClient client, String name, int id, Location loc, Position position) {
        super(id, position, ThingType.PC, client);
        this.name = name;

    }

    @Override
    public void addPcEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener) {
        locChangeListeners.add(thingEnteredLocListener);
    }

    @Override
    public Boolean removePcEnteredLocListener(ThingEnteredLocListener thingEnteredLocListener) {
        return locChangeListeners.remove(thingEnteredLocListener);
    }

    @Override
    public void addMovStateChangeListener(MovStateChangeListener movStateChangeListener) {
        movStateListeners.add(movStateChangeListener);
    }

    @Override
    public Boolean removeMovStateChangeListener(MovStateChangeListener movStateChangeListener) {
        return movStateListeners.remove(movStateChangeListener);
    }

    @Override
    public int getSpoons() {
        IdentifierMessage.GetSpoons spoons = new IdentifierMessage.GetSpoons();
        spoons.pcid = this.getId();
        Message response = client.sendMessageandGetReply(spoons);
        return ((IdentifierResponse.SendInt) response).intToSend;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void modifySpoons(int spoons) {

    }

    @Override
    public int getMoney() {
        return 0;
    }

    @Override
    public void modifyMoney(int money) {

    }

    @Override
    public int getSocial() {
        return 0;
    }

    @Override
    public void modifySocial(int social) {

    }

    @Override
    public int getMovement() {
        return 0;
    }

    @Override
    public void modifyMovement(int movement) {

    }

    @Override
    public int getHelpfulness() {
        return 0;
    }

    @Override
    public void modifyHelpfulness(int helpfulness) {

    }

    @Override
    public void setMovState(Direction dir) {
        Requests.MovState movState = new Requests.MovState();
        movState.direction = dir;
        client.sendMessage(movState);

    }

    @Override
    public Direction getMovState() {
        IdentifierMessage.GetMovState getMovState = new IdentifierMessage.GetMovState();
        getMovState.pcId = this.id;
        IdentifierResponse.SendDirection direction = (IdentifierResponse.SendDirection) client.sendMessageandGetReply(getMovState);
        return direction.direction;
    }

    @Override
    public void attemptStep() {

    }

    @Override
    public void timestep() {

    }


    @Override
    public void addAction(Action action) {

    }

    @Override
    public Boolean removeAction(Action action) {
        return null;
    }

    @Override
    public Inventory getInventory() {
        return null;
    }

    @Override
    public void initiateAction(Action action, ActionEndListener listener) {
        Requests.InitiateAction initiateAction = new Requests.InitiateAction();
        initiateAction.actionId = action.getActionId();
        initiateAction.thingId = action.getTarget().getId();
        initiateAction.pcid = this.id;
        currentAction = action;
        action.addEndListener(listener);
        client.sendMessage(initiateAction);
    }

    @Override
    public void attemptAct() {

    }

    @Override
    public StateType getPlayerState() {
        return null;
    }

    public void actionEnded(int actionId, boolean success) {
        if (currentAction != null) {
            if (actionId == currentAction.getActionId()) {
                currentAction.notifyListeners(success);
                currentAction = null;
            }
        }
    }

    public void notifyMovStateListeners(Direction direction) {
        for (MovStateChangeListener listener: movStateListeners) {
            listener.thingStateChanged(this.id, direction);
        }
    }
}
