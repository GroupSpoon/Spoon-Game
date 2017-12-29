package edu.swarthmore.cs.spoon.common.messages;

import edu.swarthmore.cs.spoon.model.interfaces.Direction;
import edu.swarthmore.cs.spoon.model.interfaces.Position;
import org.intellij.lang.annotations.Identifier;

import java.util.List;

public class IdentifierResponse {
    public static class SendInt extends Requests.IdentifierResponse {
        public int intToSend;
        private SendInt(){}
        public SendInt(int intToSend) {
            this.intToSend = intToSend;
        }
    }

    public static class SendString extends Requests.IdentifierResponse {
        public String stringToSend;
        private SendString(){}
        public SendString(String stringToSend) {
            this.stringToSend = stringToSend;
        }
    }

    public static class SendBool extends Requests.IdentifierResponse {
        public boolean boolToSend;
        private SendBool(){}
        public SendBool(boolean boolToSend) {
            this.boolToSend = boolToSend;
        }
    }

    public static class SendDirection extends Requests.IdentifierResponse {
        public Direction direction;
        private SendDirection(){}
        public SendDirection(Direction direction) {
            this.direction = direction;
        }
    }
    public static class SendPosition extends Requests.IdentifierResponse {
        public Position position;
        private SendPosition(){}
        public SendPosition(Position position){this.position = position;}
    }
    public static class SendLocation extends Requests.IdentifierResponse {
        public int locationId;
        private SendLocation(){}
        public SendLocation(int locationId){ this.locationId = locationId;};
    }
    public static class SendIdList extends Requests.IdentifierResponse {
        public List<Integer> idList;
        private SendIdList(){}
        public SendIdList(List<Integer> idList) {
            this.idList = idList;
        }
    }

    public static class SendPosList extends Requests.IdentifierResponse {
        public List<Position> posList;
        private SendPosList(){}
        public SendPosList(List<Position> posList){
            this.posList = posList;
        }
    }
    public static class SendStringList extends Requests.IdentifierResponse {
        public List<String> strings;
        private SendStringList(){}
        public SendStringList(List<String> strings){
            this.strings = strings;
        }
    }
    public static class SendIntAndString extends Requests.IdentifierResponse {
        public  List<Integer> ints;
        public List<String> strings;
        private SendIntAndString(){}
        public SendIntAndString(List<Integer> ints, List<String> strings) {
            this.ints = ints;
            this.strings = strings;
        }
    }
}
