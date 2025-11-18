import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;

public class Hospital {

    private final GenericLinkedList<Doctor> listOfDoctors;
    private final GenericLinkedList<Patient> listOfPatients;
    private final HashSet<Doctor> assignedDoctors = new HashSet<>();
    private final Scanner scanner;

    public Hospital() {
        listOfDoctors = new GenericLinkedList<>();
        listOfPatients = new GenericLinkedList<>();
        scanner = new Scanner(System.in);
    }

    public void DoctorOrPatient() {
        System.out.println("Welcome to the Hospital Management System");
        try {
            System.out.println("Are you a Doctor or Patient?");
            String answer = scanner.nextLine().toLowerCase();
            if (answer.equals("doctor")) {
                System.out.println("Enter your name: ");
                String name = scanner.nextLine();

                System.out.println("Enter your gender: ");
                char gender = scanner.nextLine().charAt(0);

                System.out.println("Enter your Date Of Birth: ");
                String dateOfBirth = scanner.nextLine();

                System.out.println("Enter your profession: ");
                String profession = scanner.nextLine();

                System.out.println("Enter your age: ");
                int age = scanner.nextInt();

                Doctor newDoctor = new Doctor(name, age, gender, dateOfBirth, profession);
                listOfDoctors.add(newDoctor);
                System.out.println("New Doctor added.");
            } else {
                System.out.println("Enter your name: ");
                String name = scanner.nextLine();
                System.out.println("Enter your age: ");
                int age = scanner.nextInt();
                System.out.println("Enter your gender: ");
                char gender = scanner.next().charAt(0);
                System.out.println("Enter your Date Of Birth: ");
                String dateOfBirth = scanner.nextLine();
                System.out.println("Enter your Illness");
                String illness = scanner.nextLine();

                Patient newPatient = new Patient(name, age, gender, dateOfBirth, illness);
                listOfPatients.add(newPatient);
                System.out.println("New Patient added.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error invalid Argument: " + e.getMessage());
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
            System.out.println(listOfDoctors.get(i).toString());
        }
        System.out.println("-----------------------\n");
    }

    public void displayAllPatients() {
        if (listOfPatients.isEmpty()) {
            System.out.println("No Patients in list");
        }

        System.out.println("List of Patients");
        for (int i = 0; i < listOfPatients.size(); i++) {
            System.out.println(listOfPatients.get(i).toString());
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
        if (listOfPatients.isEmpty()) {
            System.out.println("No patients available. Add patient first");
            return;
        }
        if (listOfDoctors.isEmpty()) {
            System.out.println("No doctors availble. Add a doctor first");
            return;
        }

        displayAllPatients();
        System.out.println("Enter a patient name for appointment");
        String patientName = scanner.nextLine().toLowerCase();
        Patient selectedPatient = findPatientByName(patientName);
        if (selectedPatient == null) {
            throw new NullPointerException();
        }

        if (selectedPatient.hasDoctorAssigned()) {
            System.out.println("This patient already has a doctor " + selectedPatient.getAssignedDoctor().getName());
            return;
        }

        displayAllDoctors();
        System.out.println("Enter doctor emplyoee ID for assignment: ");
        try {
            int doctorId = scanner.nextInt();
            Doctor selectedDoctor = findDoctorByID(doctorId);
            if (selectedDoctor == null) {
                throw new NullPointerException();
            }

            selectedPatient.setAssignedDoctor(selectedDoctor);
            assignedDoctors.add(selectedDoctor);
            System.out.println("Appointment set!" + patientName + " assigned to Dr. " + selectedDoctor.getName());

        } catch (NumberFormatException e) {
            System.out.println("Invalied ID. Please enter a number.");
        }
    }

    public void displayPatientsWithDoctors() {
        System.out.println("\n - - - Patients and Their Doctors - - -");
        for (int i = 0; i < listOfPatients.size(); i++) {
            System.out.println(listOfPatients.get(i).toString());
        }
        System.out.println("-----------------------------------\n");
    }

    public void saveToFile(String fileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileName));
            writer.println("Hospital Management System");
            writer.println();
            writer.println("List of Doctors");
            for (int i = 0; i < listOfDoctors.size(); i++) {
                Doctor doctor = listOfDoctors.get(i);
                writer.println(doctor.getName() + ", " + doctor.getAge() + ", " + doctor.getGender() + ", "
                        + doctor.getDateOfBirth() + ", " + doctor.getProfession());
            }
            writer.println();
            writer.println("List of Patients");
            for (int i = 0; i < listOfPatients.size(); i++) {
                Patient patient = listOfPatients.get(i);
                writer.println(patient.getName() + ", " + patient.getAge() + ", " + patient.getGender() + ", "
                        + patient.getDateOfBirth() + ", "
                        + patient.getIllness());
            }

            writer.println();
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }

    }

    public void loadFromFile(String filename) {
        listOfDoctors.removeAll();
        listOfPatients.removeAll();
        assignedDoctors.clear();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filename)))) {
            String line = reader.readLine();

            // Used for skiping empty lines in file
            while ((line = reader.readLine()) != null && !line.startsWith("List Of Doctors")
                    && !line.trim().isEmpty()) {
                // Skip empty lines
            }

            // used for loading doctors
            while ((line = reader.readLine()) != null && !line.startsWith("List Of Doctors")
                    && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    try {
                        String name = parts[0].trim();
                        int age = Integer.parseInt(parts[1].trim());
                        char gender = parts[2].charAt(0);
                        String dateOfBirth = parts[3].trim();
                        String profession = parts[4].trim();
                        Doctor newDoctor = new Doctor(name, age, gender, dateOfBirth, profession);
                        listOfDoctors.add(newDoctor);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping malformed doctor record: " + line);
                    }
                }
            }
            // used for loadt patients
            while ((line = reader.readLine()) != null && !line.startsWith("List Of Doctors")
                    && !line.trim().isEmpty()) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    try {
                        String name = parts[0].trim();
                        int age = Integer.parseInt(parts[1].trim());
                        char gender = parts[2].charAt(0);
                        String dateOfBirth = parts[3].trim();
                        String illness = parts[4].trim();
                        Patient newPatient = new Patient(name, age, gender, dateOfBirth, illness);
                        listOfPatients.add(newPatient);
                    } catch (NumberFormatException e) {
                        System.out.println("Skipping malformed patient record: " + line);
                    }
                }
            }

            System.out.println("Data loaded successfully from " + filename);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
