import java.util.Scanner;

public class HospitalApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();
        String fileName = "Data.txt";
        boolean running = true;

        while (running) {
            System.out.println("Welcome To The Hosptal Management System");
            System.out.println("Enter your choice:");
            System.out.println("1. Enter Doctor or Patient");
            System.out.println("2. Remove Doctor");
            System.out.println("3. Remove Patient");
            System.out.println("4. Set Appointment");
            System.out.println("5. Display Doctors With patients");
            System.out.println("6. Display all Doctors");
            System.out.println("7. Display all Patients");
            System.out.println("8. Save to file");
            System.out.println("9. Load to file");
            System.out.println("10. Exit");

            System.out.println("Choose an option:");
            int option = -1;

            try {
                option = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input please enter a number");
                scanner.nextLine();
            }

            switch (option) {
                case 1:
                    hospital.DoctorOrPatient();
                    break;
                case 2:
                    hospital.removeDoctor();
                    break;
                case 3:
                    hospital.removePatient();
                    break;
                case 4:
                    hospital.setAppointment();
                    break;
                case 5:
                    hospital.displayPatientsWithDoctors();
                    break;
                case 6:
                    hospital.displayAllDoctors();
                    break;
                case 7:
                    hospital.displayAllPatients();
                    break;
                case 8:
                    hospital.saveToFile(fileName);
                    break;
                case 9:
                    hospital.loadFromFile(fileName);
                    break;
                case 10:
                    running = false;
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid option try again");

            }
        }
        scanner.close();
    }
}
