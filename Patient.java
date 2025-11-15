public class Patient extends Person {

    private String illness;

    public Patient(String name, int age, char gender, String dateOfBirth, String illness) {
        super(name, age, gender, dateOfBirth);
        this.illness = illness;
    }

    public String getIllness() {
        return illness;
    }

    public void setIllness(String illness) {
        this.illness = illness;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("Illness: %s", illness);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }

        if (obj instanceof Patient) {
            Patient newPaitent = (Patient) obj;
            if (this.illness.equals(newPaitent.illness)) {
                return true;
            }
        }
        return false;
    }
}
