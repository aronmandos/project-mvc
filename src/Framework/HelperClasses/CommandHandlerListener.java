package Framework.HelperClasses;

public interface CommandHandlerListener {

    void receiveChallenge(String challenger, String challengeNumber, String gameType);

    void receiveMove(String player, String details, String move);

    void receiveMatchStart(String playerToMove, String gameType, String opponent);

    void getTurn(String turnMessage);
}
