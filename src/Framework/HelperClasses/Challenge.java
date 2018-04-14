package Framework.HelperClasses;

public class Challenge {
    private String challenger;
    private String challengeNumber;
    private String gameType;

    public Challenge(String challenger, String challengeNumber, String gameType) {
        this.challenger = challenger;
        this.challengeNumber = challengeNumber;
        this.gameType = gameType;
    }

    public String getChallenger() {
        return challenger;
    }

    public String getChallengeNumber() {
        return challengeNumber;
    }

    public String getGameType() {
        return gameType;
    }
}
