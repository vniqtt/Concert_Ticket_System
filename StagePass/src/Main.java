
import controller.StagePassController;
import java.time.LocalDate;
import model.Concert;

public class Main {

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("        WELCOME TO STAGEPASS");
        System.out.println("==================================");
        System.out.println("Concert Seat Reservation System");

        // Create concert information
        Concert concert = new Concert(
                "StagePass Live Concert",
                LocalDate.of(2026, 12, 20),
                "Manila Concert Arena"
        );

        // Create the controller
        StagePassController controller =
                new StagePassController(concert);

        // Start the application
        controller.start();
    }
}
