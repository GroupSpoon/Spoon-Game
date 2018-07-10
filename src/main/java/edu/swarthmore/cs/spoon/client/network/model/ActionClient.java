package edu.swarthmore.cs.spoon.client.network.model;

import edu.swarthmore.cs.spoon.client.network.interfaces.GameClient;
import edu.swarthmore.cs.spoon.common.NotYetImplementedException;
import edu.swarthmore.cs.spoon.common.messages.IdentifierMessage;
import edu.swarthmore.cs.spoon.common.messages.IdentifierResponse;
import edu.swarthmore.cs.spoon.model.interfaces.Action;
import edu.swarthmore.cs.spoon.model.interfaces.ActionEndListener;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;
import edu.swarthmore.cs.spoon.model.interfaces.Thing;

import java.util.ArrayList;
import java.util.List;

public class ActionClient implements Action {
    int id;
    String name;
    GameClient client;
    Thing thing;
    List<ActionEndListener> listeners = new ArrayList<>();

    public ActionClient(int id, String name, Thing thing, GameClient client) {
        this.name = name;
        this.id = id;
        this.thing = thing;
        this.client = client;
    }

    @Override
    public String getActionName() {
        return name;
    }

    @Override
    public void setActor(PlayerCharacter actor) {
        throw new NotYetImplementedException();
    }

    @Override
    public void addEndListener(ActionEndListener listener) {
        listeners.add(listener);
    }

    @Override
    public void notifyListeners(boolean success) {
        for (ActionEndListener listener : listeners) {
            listener.actionEnded(this.id, success);
        }
        listeners.clear();
    }

    @Override
    public void removeEndListener(ActionEndListener listener) {

    }

//    @Override
//    public List<String> getActionDescription() {
//        throw new NotYetImplementedException();
//    }

    @Override
    public int getActionDuration() {
        IdentifierMessage.GetDuration getDuration = new IdentifierMessage.GetDuration();
        getDuration.actionId = id;
        getDuration.thingId = thing.getId();
        IdentifierResponse.SendInt response = (IdentifierResponse.SendInt) client.sendMessageandGetReply(getDuration);
        return response.intToSend;
    }

    @Override
    public int getActionSpoonCost() {

        IdentifierMessage.GetSpoonCost getSpoonCost = new IdentifierMessage.GetSpoonCost();
        getSpoonCost.actionId = id;
        getSpoonCost.thingId = thing.getId();
        IdentifierResponse.SendInt response = (IdentifierResponse.SendInt) client.sendMessageandGetReply(getSpoonCost);
        return response.intToSend;
    }

    @Override
    public List<String> getActionStatusEffects() {
        throw new NotYetImplementedException();
    }

    @Override
    public boolean canPerformAction() {
        IdentifierMessage.CanPerformAction canPerformAction = new IdentifierMessage.CanPerformAction();
        canPerformAction.actionId = id;
        canPerformAction.thingId = thing.getId();
        IdentifierResponse.SendBool response = (IdentifierResponse.SendBool) client.sendMessageandGetReply(canPerformAction);
        return response.boolToSend;
    }

    @Override
    public void setSpoonCost(int num) {
        throw new NotYetImplementedException();
    }

    @Override
    public boolean canGetHelpForAction() {
        throw new NotYetImplementedException();
    }

//    @Override
//    public List<String> startActionMessage() {
//        IdentifierMessage.GetStartMsg getStartMsg = new IdentifierMessage.GetStartMsg();
//        getStartMsg.actionId = this.id;
//        getStartMsg.thingId = thing.getId();
//        IdentifierResponse.SendStringList stringList = (IdentifierResponse.SendStringList) client.sendMessageandGetReply(getStartMsg);
//        return stringList.strings;
//    }

    @Override
    public List<String> endActionMessage() {
        IdentifierMessage.GetEndMsg gem = new IdentifierMessage.GetEndMsg();
        gem.actionId = this.id;
        gem.thingId = thing.getId();
        IdentifierResponse.SendStringList stringList = (IdentifierResponse.SendStringList) client.sendMessageandGetReply(gem);
        return stringList.strings;
    }

    @Override
    public String getActionDescription() {
        IdentifierMessage.GetDescription gd = new IdentifierMessage.GetDescription();
        gd.actionId = this.id;
        gd.thingId = thing.getId();
        IdentifierResponse.SendString string = (IdentifierResponse.SendString) client.sendMessageandGetReply(gd);
        return string.stringToSend;
    }

    @Override
    public boolean hasDescription() {
        IdentifierMessage.HasDescription hasDescription = new IdentifierMessage.HasDescription();
        hasDescription.actionId = this.id;
        hasDescription.thingId = thing.getId();
        IdentifierResponse.SendBool bool = (IdentifierResponse.SendBool) client.sendMessageandGetReply(hasDescription);
        return bool.boolToSend;
    }

    @Override
    public void startAction() {
        throw new NotYetImplementedException();
    }

    @Override
    public int getActionId() {
        return id;
    }

    @Override
    public Thing getTarget() {
        return thing;
    }

    @Override
    public PlayerCharacter getActor() {
        IdentifierMessage.GetActor getActor = new IdentifierMessage.GetActor();
        getActor.actionId = this.id;
        getActor.thingId = thing.getId();
        IdentifierResponse.SendInt playerId = (IdentifierResponse.SendInt) client.sendMessageandGetReply(getActor);
        PlayerCharacter actor = client.getWorld().getCharacter(playerId.intToSend);
        return actor;
    }


}
