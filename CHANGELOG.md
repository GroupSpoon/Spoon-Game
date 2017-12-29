# Changelog
All notable changes to this project will be documented in this file.


## [Iteration 6]

### Added
- you can initiateActions over the network
- added a bed. It's an instance of thingImpl
- added an EnterBedActionImpl
- added an action for taking a nap. It's an instance of EnterBedActionImpl, but with a long duration
- added separate files for running the backend and server versions of the game
- Actions now display their end messages when the action completes. 
- Added listeners that get notified when a player's mov state updates
- Added a chair. It's an instance of thingImpl. You can sit in it, which is an action that is an instance of EnterBedActionImpl
- Added a sink. You can look at it and wash your hands at it
- Added walls, demarcating a bathroom area
- Added a toilet. You can look at and use it.
- Added a television. You can watch a few different shows on it.
- Added a table
- Added more TV shows
- Added a background and sprites for most things
- Actions have (optional) descriptions and you can actually read them!
- a very buggy, very rudimentary version of animation sometimes work

### Changed
- made createNewLoc and createThing be methods of WorldImpl instead of World
- createNewLoc no longer takes in an ID
- worldImpl's createThing no longer takes in an ID
- changed EnterBedActionImpl to the more generic MovePCToThingActionImpl
- made action descriptions optrional

### Fixed
- The GUI was running on the kryonet thread in some places, fixed that
### Deprecated

### Removed
- removed startActionMessages. Now there are just end messages. 
- removed the burger

## [Iteration 5]

### Added

- Added a constructor for GameClientImpl that takes an ip addess and a startGamelistener
- Added WaitableCounter, a class that lets you wait foe its increment method to be called a specified number of times
- Added a waitForNPlayers function to GameClient, allows callers to be notified when n players have joined the game
- Added slf4j logging to the dependencies
- Added and registered new ID Message getEntrances
- Added and registered new ID Response to send lists of positions, SendPosList
- Added PlayerState interface
- Added a ThingType enumeration, all Things now know their type
- Added a getThingType method on Thing
- Finished implementation for ThingClient
- Added an Action interface
- Added a generic implementation of Action, ActionImpl. Currently it makes its target Thing be at position (10,10) with height and width both also 10
- pc now has a method to initiate an Action
- added PlayerState, an interface with three different implementations for the different states a pc can be in (idle, moving, and acting)
- implemented ActingStateImpl, which currently, when a player is in that state, will decrease the duration of the action by 1 (it ends when the action is at duration 0, and this is also when the action's effect is carried out)
- added an enum for the different states a player can be in
- added a method to playercharacter to get its current state
- added listeners on Thing that listen for when a new Action is added to Thing and also listeners for when an action is removed from Thing
- World now has a method to create actions and get all the actions in its actionmap. Note that this will have to change as soon as we have more than one kind of action
- added a second ThingImpl to worldImpl's constructor. It's a teleportato! It's a potato and it teleports you!
- added a whole bunch of new kinds of actionimpls. They all extend ActionImpl

### Changed
- changed references to pcList to pcMap
- WorldImpl's getThings method now returns a map that will include playercharacters
- examine now takes in a playercharacter, the one doing the examining
- The different impls of PlayerState now all extend PlayerStateImpl
- the different PlayerStates now all take in a player
- Actions no longer take in an actor, but instead the actor must be set when the action is initiated 
- renamed renderWorld on startGameListener startGame
- made thinglistenersholder actually useful! There's only one now and it is a field of WorldImpl


### Fixed
- Fixed race condition that was causing gui to crash
- Fixed race condition in network tests setUp that made it so that clients didn't always have all the characters in their world before tests ran
- Fixed race condition that caused player 2 to sometimes never see anything
### Deprecated
- The get PlayerCharacters method in World
- actionAdded and actionRemoved listeners are no longer going to exist soon
### Removed
- Removed the state enum
- removed setLocation and setPosition from Thing
- removed addAction and getActions from World

## [Iteration 4] 
### added
- started writing unit tests
- wrote a functional server unit test for adding characters to a world
- added a getMovState method to playerCharacter
- added getId to thing interface, need to implement thing to always have ids 
- added getMovState to PlayerCharacter
- added a SendMessageAndGetReply
- added methods for adding a Thing to a Location, removing a Thing from a Location, and getting all the Things in a Location (because we removed the methods that did that that cared about a Thing's Position)
- added a field to LocationImpl, entrances, that has a List of Positions that you can use to enter or exit the Location
- added a method get all the entrances of a Location
- added an alternate constructor for PositionImpl that takes an x and a y (corresponding to the upper left corner of the Position and a width and a height
- added a new kind of listener - LocCreatedThingListener, for when a location creates a thing
- added a new kind of listener - NewLocListener, for when the world creates a new location
- added a new kind of listener - PCEnteredLocListener, for when a pc enters a new location
- added relevant methods to world, location, and playercharacter and their model implementations for adding and removing these listeners
- added code to notify the above listeners when it is appropriate
- added a new class - idManager, that keeps track of the last id assigned and assigns ids
- made it so Locations get their ids from idManager
- added a new method to World and WorldImpl - createNewLoc. It creates a new location and notifies the listeners
- added a new test, pcMovementTestWithAlternatePositionConstructor, to test whether my alternate constructor for PositionImpl works right... AND IT DOES
- added fields to PositionImpl to keep track of the upper left corner's x and y values and the width and height, which will be useful for collision detection
- added getters to Position for the above values
- added rudimentary collision detection
- added listeners for when a thing entered a location (thingEnteredLocListener)
- added listener updates for when ThingImpls change position or location
- added methods for Things to add and remove MovementListeners and ThingEnteredLocListeners
- added interfaces for LocationAddThingListener and LocationRemoveThingListener
- Added server fields that are maps keeping track of all the things and locations in the world
- Created a ThingClient - clientside implementation of thing
- Began implementing LocationClient


### Changed
- created a new package, common, for immutable data used by both server and client.
- made position and point immutable, moved them to common
- refactored server and client paackages so they are separate - clientside network stuff is now in client with the gui, server side interfaces and classes are still in server
- changed the way Positions are kept track of. Now, Locations do not have a map of Things to Positions, but just a list of Things, and Things keep track of their own Positions (as they always have). Invariants about the same Thing not being able to be at multiple Positions in the same Location, or in multiple different Locations, have to be maintained at runtime (unfortunately)
- PlayerCharacterImpl's setPosition method now notifies the MovementListeners when the pc's position is changed this way
- PlayerCharacterImpl's constructor now also takes a location and a position
- All the model tests are in their own package now
- Got rid of LocationList in World. Now it is LocationMap. It maps Ids to Locations
- PlayerCharacterImpl now extends ThingImpl on top of implementing PlayerCharacter

### Fixed
- fixed bug in position that caused it to change when making new positions
- fixed bug in position's move methods where they weren't actually doing anything with the new positions they created
- Made it so the server doesn't crash as soon as a client connects by giving position and point no  arg constructors
- fixed bug in LocationImpl where we were not adding anything to Entrances but then we were trying to get something from Entrances
- Fixed PlayercharacterClient so it actually intialised player with the position and locxation they are meant to have

### Deprecated
- pcEnteredLocListener is being phased out in favor of the more general thingEnteredLocListener

### Removed
- removed the methods of Location that pertained to the Position of a Thing (except isValidPosition. That's staying)
- removed setters for Location size
- removed the method exitPositions from Location, as the method getEntrances does the same thing but is actually implemented
- REMOVED THE CODE FROM WORLDIMPL'S CREATE CHARACTER THAT WAS DOING SPECIFIC THINGS FOR PCS WITH IDS 1 AND 2. 


## [Iteration 3]
### Added
- Beginning of implementation of World interface
- Created client package for client-side models
- Created Direction enum for handling player movement
- Made it so the server can receive and track connection
- Each clients world now knows about all the other player characters

