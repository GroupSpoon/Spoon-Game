package edu.swarthmore.cs.spoon.common.messages;

import com.esotericsoftware.kryo.Kryo;
import edu.swarthmore.cs.spoon.common.model.PointImpl;
import edu.swarthmore.cs.spoon.common.model.PositionImpl;

public class Utils {

    public static void register(Kryo kryo) {
        kryo.register(Requests.JoinGame.class);
        kryo.register(Requests.Success.class);
        kryo.register(Requests.NewCharacter.class);
        kryo.register(Requests.Success.class);
        kryo.register(Requests.MovState.class);
        kryo.register(Requests.GetPosition.class);
        kryo.register(Requests.Position.class);
        kryo.register(Requests.Movement.class);
        kryo.register(Requests.Character.class);
        kryo.register(PositionImpl.class);
        kryo.register(PointImpl.class);
        kryo.register(Requests.MovState.class);
        kryo.register(edu.swarthmore.cs.spoon.model.interfaces.Direction.class);
        kryo.register(java.util.ArrayList.class);
        kryo.register(Requests.IdentifierMessage.class);
        kryo.register(Requests.IdentifierResponse.class);
        kryo.register(IdentifierResponse.SendInt.class);
        kryo.register(IdentifierResponse.SendBool.class);
        kryo.register(IdentifierResponse.SendDirection.class);
        kryo.register(IdentifierResponse.SendString.class);
        kryo.register(IdentifierMessage.IsSolid.class);
        kryo.register(IdentifierMessage.GetPrice.class);
        kryo.register(IdentifierMessage.LookAt.class);
        kryo.register(IdentifierMessage.TalkTo.class);
        kryo.register(IdentifierMessage.GetName.class);
        kryo.register(IdentifierMessage.GetSpoons.class);
        kryo.register(IdentifierMessage.GetId.class);
        kryo.register(IdentifierMessage.GetLocation.class);
        kryo.register(IdentifierMessage.GetPosition.class);
        kryo.register(IdentifierResponse.SendLocation.class);
        kryo.register(IdentifierResponse.SendPosition.class);
        kryo.register(Requests.NewLocation.class);
        kryo.register(Requests.StartPlay.class);
        kryo.register(IdentifierMessage.GetEntrances.class);
        kryo.register(IdentifierResponse.SendPosList.class);
        kryo.register(IdentifierMessage.GetThings.class);
        kryo.register(Requests.NewThing.class);
        kryo.register(IdentifierMessage.GetThingsWorld.class);
        kryo.register(IdentifierResponse.SendIdList.class);
        kryo.register(IdentifierMessage.ExamineThing.class);
        kryo.register(edu.swarthmore.cs.spoon.model.interfaces.ThingType.class);
        kryo.register(IdentifierMessage.GetDuration.class);
        kryo.register(IdentifierResponse.SendIntAndString.class);
        kryo.register(IdentifierMessage.CanPerformAction.class);
        kryo.register(IdentifierMessage.GetActor.class);
        kryo.register(IdentifierMessage.GetDescription.class);
        kryo.register(IdentifierMessage.GetEndMsg.class);
        kryo.register(IdentifierResponse.SendStringList.class);
        kryo.register(Requests.InitiateAction.class);
        kryo.register(Requests.ActionEnded.class);
        kryo.register(Requests.MovStateUpdate.class);
        kryo.register(IdentifierMessage.HasDescription.class);
        kryo.register(IdentifierMessage.GetMovState.class);
    }


}
