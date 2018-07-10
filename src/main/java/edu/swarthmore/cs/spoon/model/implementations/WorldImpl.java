package edu.swarthmore.cs.spoon.model.implementations;

import edu.swarthmore.cs.spoon.common.model.ActionListenersHolderImpl;
import edu.swarthmore.cs.spoon.common.model.PointImpl;
import edu.swarthmore.cs.spoon.common.model.PositionImpl;
import edu.swarthmore.cs.spoon.common.model.ThingListenersHolderImpl;
import edu.swarthmore.cs.spoon.model.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldImpl implements World {


    //private List<PlayerCharacter>  pcList = new ArrayList<>(); //List of all the playercharacters
    //private List<Location> locList = new ArrayList<>(); //List of all the locations
    private List<NewLocListener> newLocListenerList = new ArrayList<>(); //List of listeners for new Locations being created
    private List<NewPCListener> newPCListenerList = new ArrayList<>();
    private List<NewThingListener> newThingListenerList = new ArrayList<>();
    private Map<Integer, Thing> thingMap = new HashMap<>(); //Map of Ids to Things. IT IS YOUR RESPONSIBILITY TO UPDATE THIS WHEN WORLDIMPL CRAETES NEW THINGS
    private Map<Integer, Location> locMap = new HashMap<>(); //Map of Ids to Locations
    private Map<Integer, PlayerCharacter> pcMap = new HashMap<>();
    private Map<Integer, Action> actionMap = new HashMap<>();
    private Location loc;
    private int time = 0;
    private ThingListenersHolder thingListHold = new ThingListenersHolderImpl();
    private ActionListenersHolder actListHold = new ActionListenersHolderImpl();

    public WorldImpl(){

        /*
        Creating a location
        For now, there is only one
        In the real game, there will be more and we will be reading from a file to figure out what things we should create
        It won't be hard-coded like this
         */
        //THE ONLY TIME THAT LOCATIONS CAN EVER GET CREATED SHOULD BE HERE. NEVER ANYWHERE ELSE EVER
        this.loc = createNewLoc("test", 1023, 1023);
        locMap.put(loc.getLocationID(), loc);
        //Location loc2 = createNewLoc("partytown", 1000, 1000);
        /*
        Populating the location with Things
         */

        /*
        Creating the teleportato
         */
        Thing teleportato = this.createThing("teleportato", loc, new PositionImpl(200, 800, 10, 30), ThingType.ENVOBJ);

        List<String> teleMsg = new ArrayList<>();
        teleMsg.add("It worked! You're in a different place!");
        Action teleport = new TeleportActionImpl("teleport", teleportato, -2, teleMsg, 2);

        List<String> lookAtPotatoMsg = new ArrayList<>();
        lookAtPotatoMsg.add("It looks like a potato. Could it really teleport you?");
        Action lookAtPotato = new LookAtActionImpl("lookAtPotato", teleportato, lookAtPotatoMsg);

        teleportato.addAction(teleport);
        teleportato.addAction(lookAtPotato); //#TODO: Byron commented this out because action buttons weren't loading correctly, comment this back in.


        /*
        Creating the bed
         */
        Thing bed = this.createThing("bed", loc, new PositionImpl(512, 32, 128, 64), ThingType.ENVOBJ);
        bed.setSolidity(false);
        List<String> lookAtBedMsg = new ArrayList<>();
        lookAtBedMsg.add("It's your bed.");
        Action lookAtBed = new LookAtActionImpl("lookAtBed", bed, lookAtBedMsg);

        List<String> enterBedMsg = new ArrayList<>();
        enterBedMsg.add("you snuggle up");
        Action enterBed = new MovePCToThingActionImpl("enter bed", bed, 0, enterBedMsg, 0);
        List<String> napMsg = new ArrayList<>();
        napMsg.add("Zzzz");
        Action nap = new MovePCToThingActionImpl("take a nap", bed, 10, napMsg, "you curl up for some nappy times", 1000);
        bed.addAction(nap);
        bed.addAction(lookAtBed);
        bed.addAction(enterBed);

        /*
        Creating the sink
         */
        Thing sink = this.createThing("sink", loc, new PositionImpl(64, 32*5, 64, 64), ThingType.ENVOBJ);
        List<String> lookAtSinkMsg = new ArrayList<>();
        lookAtSinkMsg.add("It's your sink.");
        Action lookAtSink = new LookAtActionImpl("lookAtSink", sink, lookAtSinkMsg);

        sink.addAction(lookAtSink);

        List<String> washHandsMsg = new ArrayList<>();
        washHandsMsg.add("'...happy birthday to youuuuu' You're all clean!");
        Action washHands = new TextActionImpl("Wash your hands", sink, -5, washHandsMsg, "You wash your hands, humming 'happy birthday' to yourself", 30);
        sink.addAction(washHands);

        ThingFactory furnitureMaker = new ThingFactoryImpl();

        /*
        Creating the comfyChair
         */
        Thing comfyChair = furnitureMaker.createThing("comfyChair", loc, new PositionImpl(600, 32, 64, 60), thingListHold, ThingType.ENVOBJ, "it's for sittin' on!", false);
        this.thingMap.put(comfyChair.getId(), comfyChair);

        List<String> sitDownMsg = new ArrayList<>();
        sitDownMsg.add("you sit down. It's comfy as hell!");
        Action sitDownComfyChair = new MovePCToThingActionImpl("sit down", comfyChair, 0, sitDownMsg, 0);
        comfyChair.addAction(sitDownComfyChair);

        /*
        Creating the toilet
         */
        Thing toilet = furnitureMaker.createThing("toilet", loc, new PositionImpl(135, 32*8, 50, 50), thingListHold, ThingType.ENVOBJ, "For when nature calls", false);
        this.thingMap.put(toilet.getId(), toilet);

        List<String> useToiletMsg = new ArrayList<>();
        useToiletMsg.add("don't forget to flush");
        Action useToilet = new MovePCToThingActionImpl("use toilet", toilet, 0, useToiletMsg, "you sit down", 50);
        toilet.addAction(useToilet);





        /*
        create the walls of the bathroom area
         */
        //Top wall

        Thing topWall = furnitureMaker.createThing("topWall", loc, new PositionImpl(0, 0, 32, (32*10)), thingListHold, ThingType.ENVOBJ, "All in all it's just another piece of the wall", true);

        Thing bottomWall = furnitureMaker.createThing("bottomWall", loc, new PositionImpl(0, (32*10), 32, (32*10)), thingListHold, ThingType.ENVOBJ, "All in all it's just another piece of the wall", true);
        Thing leftWall = furnitureMaker.createThing("leftWall", loc, new PositionImpl(0, 0, 32*10, 32), thingListHold, ThingType.ENVOBJ, "All in all it's just another piece of the wall", true);
        Thing upperRightWall = furnitureMaker.createThing("upperRightWall", loc, new PositionImpl(32*10, 0, (32*4), 32), thingListHold, ThingType.ENVOBJ, "All in all it's just another piece of the wall", true);
        Thing lowerRightWall = furnitureMaker.createThing("lowerRightWall", loc, new PositionImpl(32*10, (32*7), 32*4, 32), thingListHold, ThingType.ENVOBJ, "All in all it's just another piece of the wall", true);
        this.thingMap.put(topWall.getId(), topWall);
        this.thingMap.put(bottomWall.getId(), bottomWall);
        this.thingMap.put(leftWall.getId(), leftWall);
        this.thingMap.put(upperRightWall.getId(), upperRightWall);
        this.thingMap.put(lowerRightWall.getId(), lowerRightWall);

        /*
        Creating the TV
         */
        Thing television = furnitureMaker.createThing("television", loc, new PositionImpl(900, 700, 64, 64), thingListHold, ThingType.ENVOBJ, "TEEEVEEE", true);
        this.thingMap.put(television.getId(), television);

        List<String> watchSitcomMsg = new ArrayList<>();
        watchSitcomMsg.add("You watch a show about a family who run a burger restaurant.");
        Action watchSitcom = new TextActionImpl("watch sitcom", television, 0, watchSitcomMsg, "'One \"I wanna get feta\" burger coming right up!'", 50);
        television.addAction(watchSitcom);

        List<String> watchDetectiveMsg = new ArrayList<>();
        watchDetectiveMsg.add("You watch a show about a zombie who solves crimes. It's better than it sounds.");
        Action watchDetectiveShow = new TextActionImpl("watch detective show", television, 0, watchDetectiveMsg, "'But I'm a zombie!'",50);
        television.addAction(watchDetectiveShow);

        List<String> watchAnimeMsg = new ArrayList<>();
        watchAnimeMsg.add("You watch a show about a small fishing town and some aliens.");
        Action watchAnime = new TextActionImpl("watch anime", television, 0, watchAnimeMsg, "'Haino haino haino yoishi yoishi yoishi'", 50);
        television.addAction(watchAnime);

        List<String> watchSciFiMsg = new ArrayList<>();
        watchSciFiMsg.add("You watch a show about a Doctor traveling through time in a blue box.");
        Action watchSciFi = new TextActionImpl("watch sci fi", television, 0, watchSciFiMsg, "'The Angels have the phone box!'", 50);
        television.addAction(watchSciFi);

//        List<String> watchNatureDocumentaryMsg = new ArrayList<>();
//        watchNatureDocumentaryMsg.add("You watch a documentary about how rad octopuses are.");
//        Action watchNatureDocumentary = new TextActionImpl("watch nature documentary", television, 0, watchNatureDocumentaryMsg, "'And some species can actually change their shape to confuse predators!'", 50);
//        television.addAction(watchNatureDocumentary);

        /*
        Creating the table
         */
        Thing table = furnitureMaker.createThing("table", loc, new PositionImpl(800, 200, 150, 85), thingListHold, ThingType.ENVOBJ, "Why don't we TABLE this matter for now", true);
        this.thingMap.put(table.getId(), table);
        List<String> eatPizzaMsg = new ArrayList<>();
        eatPizzaMsg.add("It's MAGICAL PIZZA! You can eat as much as you want and never run out!");
        Action eatPizza = new TextActionImpl("eat pizza", table, 0, eatPizzaMsg, "You dig in. It looks delicious", 50);
        table.addAction(eatPizza);


                /*
        Creating the chairs that will be next to the table
         */
//        Thing chair = furnitureMaker.createThing("chair", loc, new PositionImpl(600, 32, 64, 60), thingListHold, ThingType.ENVOBJ, "it's for sittin' on!", false);
//        this.thingMap.put(chair.getId(), chair);
//
//        List<String> sitDownMsg = new ArrayList<>();
//        sitDownMsg.add("you sit down. It's comfy as hell!");
//        Action sitDownChair = new MovePCToThingActionImpl("sit down", chair, 0, sitDownMsg, 0);
//        chair.addAction(sitDownChair);


    }

    @Override
    public PlayerCharacter createCharacter(String name, int id) {

        ThingListenersHolder holder = new ThingListenersHolderImpl();


        if(id==2){
            Point ul2 = new PointImpl(600,600);
            Point ur2 = new PointImpl(663,600);
            Point ll2 = new PointImpl(600,664);
            Point lr2 = new PointImpl(663,664);
            Position pos2 = new PositionImpl(ul2, ur2, ll2, lr2);
            PlayerCharacterImpl newPC = new PlayerCharacterImpl(name, id, loc, pos2, holder);
            //pcList.add(newPC);
            pcMap.put(id, newPC);
            thingMap.put(id, newPC);

            return newPC;
        }
        else{
        Point ul1 = new PointImpl(500,500);
        Point ur1 = new PointImpl(563,500);
        Point ll1 = new PointImpl(500,563);
        Point lr1 = new PointImpl(563,563);
        Position pos1 = new PositionImpl(ul1, ur1, ll1, lr1);
        PlayerCharacterImpl newPC = new PlayerCharacterImpl(name, id, loc, pos1, holder);
        //pcList.add(newPC);
        pcMap.put(id, newPC);
        thingMap.put(id, newPC);


        return newPC;}
    }

//    @Override
//    public PlayerCharacter createCharacter() {
//        PlayerCharacterImpl newPC = new PlayerCharacterImpl();
//        pcList.add(newPC);
//        return newPC;
//    }

    @Override
    public Map<Integer, PlayerCharacter> getCharacters() {
        return pcMap;
    }

    @Override
    public PlayerCharacter getCharacter(int id) {
        return pcMap.get(id);
    }

    @Override
    public Map<Integer, Thing> getThings()
    {
        return thingMap;
    }


    public Action createAction(String name, int id, Thing target, int cost, List<String> endMsg, int dur) {
        Action action = new ActionImpl(name, target, cost, endMsg, dur);
        this.actionMap.put(action.getActionId(), action);
        return action;
    }


    public Map<Integer, Action> getActions() {
        return actionMap;
    }


    public Location createNewLoc(String locName, int height, int width) {
        Location newLoc = new LocationImpl(locName, height, width);
        for(NewLocListener nll : this.newLocListenerList){
            nll.newLocCreated(newLoc.getLocationID());
        }
        return newLoc;
    }

    @Override
    public void addGoal(CampaignGoal goal) {

    }

    @Override
    public void startMaze() {

    }

    @Override
    public void startDay() {

    }

    @Override
    public void startNight() {

    }
//
//    @Override
//    public void setup() {
//        locationMap.
//
//
//    }

    @Override
    public Map<Integer, Location> getLocationMap() {
        return this.locMap;
    }

    @Override
    public void addNewLocListener(NewLocListener newLocListener) {
        this.newLocListenerList.add(newLocListener);
    }

    @Override
    public Boolean removeNewLocListener(NewLocListener newLocListener) {
        return this.newLocListenerList.remove(newLocListener);
    }

    @Override
    public void timestep() {
        for(PlayerCharacter pc : pcMap.values()){
            pc.timestep();
        }
        time += 1;

    }


    public Thing createThing(String name, Location loc, Position pos, ThingType type) {
        Thing newThing = new ThingImpl(name, loc, pos, this.thingListHold, type);
        thingMap.put(newThing.getId(), newThing);
        return newThing;
    }



//Instead of the NewThingListeners, either: Just have the listener for when a thing is added to a location be the relevant one
    //or, pass an object that has a bunch of listeners to the Thing's constructor, and in the constructor, notify those listeners
    //of the creation of the Thing.
    @Override
    public void addNewThingListener(NewThingListener newThingListener) {
        this.thingListHold.addNewThingListener((newThingListener));
    }

    @Override
    public Boolean removeNewThingListener(NewThingListener newThingListener) {
        return this.thingListHold.removeNewThingListener(newThingListener);
    }


    @Override
    public void addNewPCListener(NewPCListener newPCListener) {
        this.newPCListenerList.add(newPCListener);
    }

    @Override
    public Boolean removeNewPCListener(NewPCListener newPCListener) {
        return this.newPCListenerList.remove(newPCListener);
    }
}
