package edu.swarthmore.cs.spoon.model.interfaces;

/**
 * An engine for sending and receiving requests for help between a particular player and all other players
 */
public interface Phone {

    /**
     * Sends a message from the owner of the phone to the recipient asking for help with the task. Creates a message based on the passed-in Action
     *  and the owner's location, then sends that message to the recipient's phone.
     * @param recipient the PlayerCharacter to send the request to
     * @param task the Action that the sender needs help with
     */
    public void sendHelpRequest(PlayerCharacter recipient, Action task);




    /**
     * Receives a request for help from another PlayerCharacter, the sender, gets the receiving player's response, and sends that back
     *
     * @param sender the PlayerCharacter who sent the original message
     * @param task the Action that the sender needs help with
     * @return true if the receiver will help the sender, false if the opposite
     */
    public Boolean receiveAndRespondToHelpRequest(PlayerCharacter sender, Action task);



    /**
     * Receives the response to a request for help sent to another PlayerCharacter
     * @return true if the responder has chosen to help, false if the opposite
     */
    public Boolean getResponse();

}
