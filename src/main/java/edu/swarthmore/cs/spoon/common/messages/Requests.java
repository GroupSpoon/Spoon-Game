package edu.swarthmore.cs.spoon.common.messages;

import edu.swarthmore.cs.spoon.model.interfaces.Direction;
import edu.swarthmore.cs.spoon.model.interfaces.Location;
import edu.swarthmore.cs.spoon.model.interfaces.Position;
import edu.swarthmore.cs.spoon.model.interfaces.ThingType;

import java.util.List;

public class Requests {
    public static class JoinGame implements Message{
    }
    public static class NewCharacter implements Message{
        public String name;
        public int id;
    }
    public static class Character implements Message{
        public String name;
        public int id;
        public edu.swarthmore.cs.spoon.model.interfaces.Position position;
    }
    public static class Success implements Message{
        public int id;
    }

    public static class MovState implements Message{
        public Direction direction;
    }

    public static class GetPosition implements Message{
        public int id;
    }
    public static class Position implements Message{
        public int id;
        public edu.swarthmore.cs.spoon.model.interfaces.Position position;
    }

    public static class LocationList implements Message{
        public List<Location> locations;
    }
    public static class Movement implements Message{
        public int id;
        public edu.swarthmore.cs.spoon.model.interfaces.Position position;
    }


    public abstract static class IdentifierMessage implements Message{
        public int id;
    }

    public abstract static class IdentifierResponse implements Message{
        public int id;
    }

    public static class NewLocation implements Message {
        public int id;
        public String name;
        public int height;
        public int width;
    }

    public static class StartPlay implements Message {
        public int numPlayers;
    }

    public static class NewThing implements Message {
        public int thingId;
        public String name;
        public int locId;
        public ThingType type;
        public edu.swarthmore.cs.spoon.model.interfaces.Position pos;
    }
    public static class InitiateAction implements Message {
        public int pcid;
        public int actionId;
        public int thingId;
    }
    public static class ActionEnded implements Message {
        public int pcId;
        public int actionId;
        public boolean success;
    }

    public static class MovStateUpdate implements Message {
        public Direction dir;
        public int thingId;
    }







}
