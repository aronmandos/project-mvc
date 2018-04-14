package Framework.HelperClasses;

public interface CommandHandlerListener {

    void receiveChallenge(String challenger, String challengeNumber, String gameType);

    void recieveMove(String player, String details, String move);

}
