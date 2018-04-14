package Framework.HelperClasses;

import Framework.HelperClasses.board2d.Board2dListener;

import java.util.ArrayList;

public class CommandHandler {

    private ArrayList<CommandHandlerListener> listeners;

    public CommandHandler() {
        this.listeners = new ArrayList<>();
    }

    public void handle(String message) {
        if (message == null) {
            System.out.println("null van server");
        } else {
            System.out.println("Client: recieved from server: " + message);
            if(message == "" || message == null){

            } else if(message.contains("Strategic Game Server [")){

                System.out.println("CommandHandler: connection successfull");

            } else if(message.contains("SVR GAMELIST [")){

                System.out.println("CommandHandler: game list recieved");
                parseGameList(message);

            } else if(message.contains("SVR PLAYERLIST [")){

                System.out.println("CommandHandler: PlayerList recieved");
                parsePlayerList(message);

            } else if(message.contains("SVR GAME MATCH {GAMETYPE: ") &&
                    message.contains("PLAYERTOMOVE: \"") &&
                    message.contains("OPPONENT: \"")){

                System.out.println("CommandHandler: Match recieved");
                parseMatch(message);

            } else if(message.contains("SVR GAME CHALLENGE {CHALLENGER: ") &&
                    message.contains("GAMETYPE: \"") &&
                    message.contains("CHALLENGENUMBER: \"")){

                System.out.println("CommandHandler: Challenge recieved");
                parseChallenge(message);

            } else if(message.contains("SVR GAME ") &&
                    message.contains("PLAYERONESCORE: \"") &&
                    message.contains("PLAYERTWOSCORE: \"")){
                System.out.println("CommandHandler: GameResult received");
                parseGameResult(message);

            } else if(message.contains("SVR GAME YOURTURN {TURNMESSAGE:")){
                System.out.println("CommandHandler: Turn received");
                parseTurn(message);

            } else if(message.contains("SVR GAME MOVE {PLAYER: ")){

                System.err.println("CommandHandler: move received");
                parseMove(message);

            } else if(message.contains("SVR GAME CHALLENGE CANCELLED {CHALLENGENUMBER: ")){

                System.out.println("CommandHandler: challenge cancelled");
                parseChallengeCancel(message);
            } else if(message.contains("SVR GAME WIN")){

                System.out.println("CommandHandler: won");
                parseWin(message);
            } else if(message.contains("SVR GAME LOSS")){

                System.out.println("CommandHandler: lost");
                parseLoss(message);
            } else if(message.contains("SVR GAME DRAW")){

                System.out.println("CommandHandler: draw");
                parseDraw(message);
            }
        }

    }

    /**
     * parses a gamelist
     * @param message Message from the server
     */
    private void parseGameList(String message){
//		012345678901234567
//		SVR GAMELIST ["<speltype1>", "<speltype2>", "<speltype3>", "<speltype4>", "<speltype5>"]

        String temp = message.replace("OK","");
        temp = temp.replace("SVR GAMELIST [", "");
        temp = temp.replace("]","");
        temp = temp.replace("\"","");


        String[] parameters = temp.split(", ");

        System.out.println("gamelist aangeroepen");

        //TODO update gamelist of server
    }

    /**
     * parses a playerlist
     * @param message Message from the server
     */
    private void parsePlayerList(String message){
////		012345678901234567
////		SVR PLAYERLIST ["<player1>", "<player2>"]
        String temp = message.replace("OK","");
        temp = temp.replace("SVR PLAYERLIST [","");
        temp = temp.replace("]","");
        temp = temp.replace("\"","");


        String[] parameters = temp.split(", ");

        System.out.println("playerlist aangeroepen");

        //TODO update the list of players on the server
    }

    private void parseMatch(String message) {
//		S: SVR GAME MATCH {GAMETYPE: "<speltype>", PLAYERTOMOVE: "<naam speler1>", OPPONENT: "<naam tegenstander>"}
//		S: SVR GAME MATCH {PLAYERTOMOVE: "Sinterklaas", GAMETYPE: "Tic-tac-toe", OPPONENT: "Sinterklaas"}

        int[] indexes = getQuotationMarkIndexes(message, 6);

        String playerToMove = message.substring(indexes[0]+1, indexes[1]);
        String gameType = message.substring(indexes[2]+1, indexes[3]);
        String opponent = message.substring(indexes[4]+1, indexes[5]);

        //TODO handle match, check if game is supported, start and open game
        for (CommandHandlerListener listener : listeners) {
            listener.receiveMatchStart(playerToMove, gameType, opponent);
        }
    }

    private void parseChallenge(String message){
//		S: SVR GAME CHALLENGE {CHALLENGER: "Sjors", GAMETYPE: "Guess Game", CHALLENGENUMBER: "1"}

        int[] indexes = getQuotationMarkIndexes(message, 6);

        String challenger = message.substring(indexes[0]+1, indexes[1]);
        // Sjors
        String challengenumber = message.substring(indexes[2]+1, indexes[3]);
        // 1
        String gametype = message.substring(indexes[4]+1, indexes[5]);
        // Guess Game

        for (CommandHandlerListener listener : listeners) {
            listener.receiveChallenge(challenger, challengenumber, gametype);
        }

        //TODO: ask user to accept or decline

    }
    private void parseChallengeCancel(String message){
//		S: SVR GAME CHALLENGE CANCELLED {CHALLENGENUMBER: "<uitdaging nummer>"}
//		S: SVR GAME CHALLENGE CANCELLED {CHALLENGENUMBER: "1"}

        int[] indexes = getQuotationMarkIndexes(message, 2);

        String challengenumber = message.substring(indexes[0]+1, indexes[1]);
        // <uitdaging nummer>
        // 1

        //TODO: meld user dat uitdaging afgewezen is.
    }

    private void parseGameResult(String message){
//		S: SVR GAME <speler resultaat> {PLAYERONESCORE: "<score speler1>", PLAYERTWOSCORE: "<score speler2>", COMMENT: "<commentaar op resultaat>"}

        int[] indexes = getQuotationMarkIndexes(message, 6);

        String playerOneScore = message.substring(indexes[0]+1, indexes[1]);
        String playerTwoScore = message.substring(indexes[2]+1, indexes[3]);
        String comment = message.substring(indexes[4]+1, indexes[5]);

        //TODO: meld user het resultaat, sluit game af
    }
    private void parseTurn(String message){
//		S: SVR GAME YOURTURN {TURNMESSAGE: "<bericht voor deze beurt>"}

        int[] indexes = getQuotationMarkIndexes(message, 2);

        String turnMessage = message.substring(indexes[0]+1, indexes[1]);

        //TODO handle getting the turn
    }


    private void parseMove(String message){
//		S: SVR GAME MOVE {PLAYER: "<speler>", DETAILS: "<reactie spel op zet>", MOVE: "<zet>"}

        int[] indexes = getQuotationMarkIndexes(message, 6);

        String player = message.substring(indexes[0]+1, indexes[1]);
        String move = message.substring(indexes[2]+1, indexes[3]);
        String details = message.substring(indexes[4]+1, indexes[5]);

        for (CommandHandlerListener listener : listeners) {
            listener.recieveMove(player, move, details);
        }
        //TODO handle player move
    }

    private void parseWin(String message) {
        //TODO handle win
    }

    private void parseLoss(String message) {
        //TODO handle loss
    }

    private void parseDraw(String message) {
        //TODO handle draw
    }

    /**
     * Adds a listener to this handler
     * @param listener A listener to be added.
     */
    public void addListener(CommandHandlerListener listener) {
        this.listeners.add(listener);
    }

    /**
     * Removes a listener from this handler
     * @param listener A listener to be removed.
     */
    public void removeListener(CommandHandlerListener listener) {
        this.listeners.remove(listener);
    }

    /**
     * clears all listeners
     */
    public void clearListeners() {
        this.listeners.clear();
    }


    /**
     * returns an array with indexes of quotation marks in the given string
     * if there are more than the maximum indexes array[maxMarks] will be -1
     * @param string
     * @param maxMarks
     * @return
     */
    private int[] getQuotationMarkIndexes(String string, int maxMarks) {
        int[] indexes = new int[maxMarks];
        for (int i = 0; i<maxMarks+1;i++){
            int index = 0;
            if (i>0){
                index = string.indexOf("\"", indexes[i-1]+1);
            } else {
                index = string.indexOf("\"");
            }

            if (index >= 0) {
                if (i == maxMarks) {
                    //too many
                    indexes[maxMarks] = -1;
                } else {
                    indexes[i] = index;
                }
            } else {
                i = maxMarks+1;
            }
        }
        return indexes;
    }

}
