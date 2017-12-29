package edu.swarthmore.cs.spoon.common.messages;

public class IdentifierMessage {
    public static class GetSpoons extends Requests.IdentifierMessage {
        public int pcid;
    }
    public static class LookAt extends Requests.IdentifierMessage {
        public int thingId;
    }
    public static class TalkTo extends Requests.IdentifierMessage {
        public int thingId;
    }
    public static class GetName extends Requests.IdentifierMessage {
        public int thingId;
    }
    public static class GetPrice extends Requests.IdentifierMessage {
        public int thingId;
    }
    public static class IsSolid extends Requests.IdentifierMessage {
        public int thingId;
    }
    public static class GetId extends Requests.IdentifierMessage{
    }
    public static class GetPosition extends Requests.IdentifierMessage{
        public int thingId;
    }
    public static class GetLocation extends Requests.IdentifierMessage{
        public int thingId;
    }
    public static class GetThings extends Requests.IdentifierMessage{
        public int locId;
    }
    public static class GetThingsWorld extends Requests.IdentifierMessage{
    }

    public static class GetEntrances extends Requests.IdentifierMessage{
        public int locId;
    }

    public static class ExamineThing extends Requests.IdentifierMessage{
        public int pcId;
        public int thingId;
    }

    public static class GetDuration extends Requests.IdentifierMessage{
        public int actionId;
        public int thingId;
    }

    public static class GetSpoonCost extends Requests.IdentifierMessage{
        public int actionId;
        public int thingId;
    }

    public static class CanPerformAction extends Requests.IdentifierMessage{
        public int actionId;
        public int thingId;
    }

    public static class GetActor extends Requests.IdentifierMessage{
        public int actionId;
        public int thingId;
    }
    public static class GetDescription extends Requests.IdentifierMessage{
        public int actionId;
        public int thingId;
    }
    public static class GetEndMsg extends Requests.IdentifierMessage{
        public int actionId;
        public int thingId;
    }
    public static class HasDescription extends Requests.IdentifierMessage {
        public int actionId;
        public int thingId;
    }
    public static class GetMovState extends Requests.IdentifierMessage {
        public int pcId;
    }


}
