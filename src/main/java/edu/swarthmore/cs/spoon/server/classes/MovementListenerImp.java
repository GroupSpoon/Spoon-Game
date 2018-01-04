package edu.swarthmore.cs.spoon.server.classes;

import com.esotericsoftware.kryonet.Server;
import edu.swarthmore.cs.spoon.model.interfaces.MovementListener;
import edu.swarthmore.cs.spoon.model.interfaces.Position;
import edu.swarthmore.cs.spoon.common.messages.Requests;

public class MovementListenerImp implements MovementListener{
    Server server;
    int pcid;
    public MovementListenerImp(Server server, int pcid) {
        this.server = server;
        this.pcid = pcid;
    }
    @Override
    public void thingMoved(int pcid, Position position) {
        if (this.pcid == pcid) {
            Requests.Movement movement = new Requests.Movement();
            movement.id = pcid;
            movement.position = position;
            server.sendToAllTCP(movement);
        }
    }
}
