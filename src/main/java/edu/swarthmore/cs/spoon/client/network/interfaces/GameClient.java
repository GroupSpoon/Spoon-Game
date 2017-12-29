package edu.swarthmore.cs.spoon.client.network.interfaces;

import edu.swarthmore.cs.spoon.common.messages.Requests;
import edu.swarthmore.cs.spoon.model.interfaces.MovementListener;
import edu.swarthmore.cs.spoon.model.interfaces.ThingEnteredLocListener;
import edu.swarthmore.cs.spoon.model.interfaces.ThingListenersHolder;
import edu.swarthmore.cs.spoon.model.interfaces.World;
import edu.swarthmore.cs.spoon.common.messages.Message;

public interface GameClient {

    public World getWorld();

    public Requests.IdentifierResponse sendMessageandGetReply(Requests.IdentifierMessage message);

    public void sendMessage(Message message);

    public int getOwnId();

    public void stopGame();

    public void addStartGameListener(StartGameListener startGameListener);

    public void startGame(int numPlayers);

    void addMovementListener(MovementListener movementListener);

    void waitForNPlayers(int n);

    void addLocationListener(ThingEnteredLocListener locListener);

    ThingListenersHolder getThingListenersHoler();
}
