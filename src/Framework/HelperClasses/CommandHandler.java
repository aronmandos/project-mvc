package Framework.HelperClasses;

public class CommandHandler {

    String message = "niks";

    public void handle(String message) {

        this.message = message;

        switch (message) {
            case "OK":
                System.out.println("!!!!!!!!!!    OK ontvangen");
                break;

            case "ERR":
                System.out.println("!!!!!!!!   ERR ontvangen");

            case "SVR PLAYERLIST":
                System.out.println("!!!!!!!!!!! Playerlist ontvangen");

            case "niks":
                System.out.println("Het ging helemaal mis");

        }
    }




}
