import org.apache.logging.log4j.*;

import java.util.Scanner;

import static org.apache.logging.log4j.Level.WARN;


public class Main {
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

   // private static final Logger root = LogManager.getRootLogger();
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Marker ERROR = MarkerManager.getMarker("ERROR");
    private static final Marker QUERY = MarkerManager.getMarker("QUERY");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            String command = scanner.nextLine();
            logger.log(WARN, QUERY, command);
            String[] tokens = command.split("\\s+", 2);
            try {
                if (tokens[0].equals("add")) {
                    executor.addCustomer(tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove")) {
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                } else {
                    System.out.println(COMMAND_ERROR);
                }
            }catch (IndexOutOfBoundsException ex){
                logger.warn(ERROR, ex.getMessage());
            }
            catch (IllegalArgumentException ex){
                logger.log(WARN, ERROR, ex.getMessage());
            }
        }
    }
}
