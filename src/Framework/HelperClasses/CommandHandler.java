package Framework.HelperClasses;

public class CommandHandler {


    public static void handle(String message) {
        if (message == null) {
            System.out.println("null van server");
        } else {
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




}
