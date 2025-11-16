public class Patient extends Person {

    private String illness;
    private Doctor assignedDoctor;

    public Patient(String name, int age, char gender, String dateOfBirth, String illness) {
        super(name, age, gender, dateOfBirth);
        this.illness = illness;
        this.assignedDoctor = null;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }

    public void setAssignedDoctor(Doctor doctor) {
        this.assignedDoctor = doctor;
    }

    public boolean hasDoctorAssigned() {
        return this.assignedDoctor != null;
    }

    @Override
    public String toString() {
        String doctorInfo = (assignedDoctor != null)
                ? String.format(" | Assigned Doctor: %s (ID: %d)",
                        assignedDoctor.getName(), assignedDoctor.getEmployeeId())
                : " | Assigned Doctor: None";
        return super.toString() + String.format(" | Illness: %s%s", illness, doctorInfo);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        if (obj instanceof Patient) {
            Patient newPaitent = (Patient) obj;
            if (this.illness.equals(newPaitent.illness)) {
                if (this.assignedDoctor == newPaitent.assignedDoctor) {
                    return true;
                }
            }
        }
        return false;
    }
}
