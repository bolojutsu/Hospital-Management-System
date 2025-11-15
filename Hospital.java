import java.util.Scanner;

public class Hospital {

    private final GenericLinkedList<Doctor> listOfDoctors;
    private final GenericLinkedList<Patient> listOfPatients;
    private final Scanner scanner;

    public Hospital() {
        listOfDoctors = new GenericLinkedList<>();
        listOfPatients = new GenericLinkedList<>();
        scanner = new Scanner(System.in);
    }

    public void DoctorOrPatient() {
        System.out.println("Enter your Name: ");
        String name = scanner.nextLine().toLowerCase();

        System.out.println("Enter your Age: ");
        int age = scanner.nextInt();

        System.out.println("Enter your gender(M/F)");
        char gender = scanner.next().charAt(0);

        System.out.println("Enter your Date Of birth: ");
        String dateOfBirth = scanner.nextLine();

        System.out.println("Are you a Doctor or Patient?");
        String answer = scanner.nextLine().toLowerCase();

        try {
            if (answer.equals("doctor")) {
                System.out.println("What is your Profession");
                String profession = scanner.nextLine();
                Doctor newDoctor = new Doctor(name, age, gender, dateOfBirth, profession);
                listOfDoctors.add(newDoctor);
            } else if (answer.equals("patient")) {
                System.out.println("What is your illness");
                String illness = scanner.nextLine();
                Patient newPatient = new Patient(name, age, gender, dateOfBirth, illness);
                listOfPatients.add(newPatient);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private Doctor findDoctorByID(int employeeId) {
        for (int i = 0; i < listOfDoctors.size(); i++) {
            Doctor doctor = listOfDoctors.get(i);
            if (doctor.getEmployeeId() == employeeId) {
                return doctor;
            }
        }
        return null;
    }

    private Patient findPatientByName(String name) {
        for (int i = 0; i < listOfPatients.size(); i++) {
            Patient patient = listOfPatients.get(i);
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }

    public void displayAllDoctors() {
        if (listOfDoctors.isEmpty()) {
            System.out.println("No doctors in list");
        }
        System.out.println("\n--- List Of Doctors ---");
        for (int i = 0; i < listOfDoctors.size(); i++) {
            listOfDoctors.get(i).toString();
        }
        System.out.println("-----------------------\n");
    }

    public void displayAllPatients() {
        if (listOfPatients.isEmpty()) {
            System.out.println("No Patients in list");
        }

        System.out.println("List of Patients");
        for (int i = 0; i < listOfPatients.size(); i++) {
            listOfPatients.get(i).toString();
        }
        System.out.println("-----------------------\n");
    }

    public void removeDoctor() {
        System.out.println("Which doctor do you want to remove? Enter their employee ID");
        int id = scanner.nextInt();
        Doctor removedDoctor = findDoctorByID(id);

        if (removedDoctor == null) {
            throw new NullPointerException();
        }

        int indexToRemove = -1;
        for (int i = 0; i < listOfDoctors.size(); i++) {
            Doctor doctors = listOfDoctors.get(i);
            if (doctors == removedDoctor) {
                indexToRemove = i;
                break;
            }
        }
        try {
            if (indexToRemove != -1) {
                listOfDoctors.remove(indexToRemove);
                System.out.println("Succsessfully removed Doctor");
            } else {
                System.out.println("Was able to find Doctor but index was not valid");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public void removePatient() {
        System.out.println("Which Patient do you want to remove? Enter their Name: ");
        String name = scanner.nextLine();
        Patient removedPatient = findPatientByName(name);

        int indexToRemove = -1;
        for (int i = 0; i < listOfPatients.size(); i++) {
            Patient patients = listOfPatients.get(i);
            if (patients == removedPatient) {
                indexToRemove = i;
            }
        }

        try {
            if (indexToRemove != -1) {
                listOfPatients.remove(indexToRemove);
                System.out.println("Successfully removed patient");
            } else {
                System.out.println("Was able to find patient but index was not valid");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void setAppointment() {

    }
}
