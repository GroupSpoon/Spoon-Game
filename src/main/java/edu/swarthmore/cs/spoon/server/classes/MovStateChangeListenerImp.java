package edu.swarthmore.cs.spoon.server.classes;

import com.esotericsoftware.kryonet.Server;
import edu.swarthmore.cs.spoon.model.interfaces.Direction;
import edu.swarthmore.cs.spoon.model.interfaces.MovStateChangeListener;
import edu.swarthmore.cs.spoon.model.interfaces.PlayerCharacter;

public class MovStateChangeListenerImp implements MovStateChangeListener{
    private Server server;
    public MovStateChangeListenerImp(Server server) {
        this.server = server;
    }
    @Override
    public void thingStateChanged(int id, Direction direction) {

    }
}
