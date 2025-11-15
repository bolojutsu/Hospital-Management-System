public class Doctor extends Person {
    private String profession;
    private static int nextEmployeeId = 1000;
    private final int employeeId;

    public Doctor(String name, int age, char gender, String dateOfBirth, String profession) {
        super(name, age, gender, dateOfBirth);
        this.profession = profession;
        this.employeeId = nextEmployeeId++;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Doctor:  " + super.toString() + String.format("Profession: %s | Employee ID: ", profession, employeeId);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (obj instanceof Doctor) {
            Doctor newDoctor = (Doctor) obj;
            if (this.profession.equals(newDoctor.profession)) {
                return true;
            }
        }
        return false;
    }

}