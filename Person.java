public class Person {
    private String name;
    private int age;
    private char gender;
    private String dateOfBirth;

    public Person() {
        this.name = "Unkown";
        this.age = 0;
        this.gender = 'U';
        this.dateOfBirth = "0";
    }

    public Person(String name, int age, char gender, String dateOfBirth) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public char getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(char gender) {
        if (gender == 'f' || gender == 'm') {
            this.gender = gender;
        } else {
            this.gender = 'u';
        }
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        String string = String.format("Name: %s | Age: %d | Gender: %b | Date Of Birth: %s", name, age, gender,
                dateOfBirth);
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }

        if (obj instanceof Person) {
            Person newPerson = (Person) obj;
            if (this.name.equals(newPerson.name)) {
                if (this.age == newPerson.age) {
                    if (this.gender == newPerson.gender) {
                        if (this.dateOfBirth.equals(newPerson.dateOfBirth)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

}
