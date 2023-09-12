public class teacher {
    private String firstName;
    private String lastName;
    private String id;
    private int yearOfBirth;

    public teacher() {
    }

    public teacher(String firstName, String lastName, String id, int yearOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.yearOfBirth = yearOfBirth;
    }

    public void setFullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getAge() {
        int currentYear = java.time.Year.now().getValue();
        return currentYear - yearOfBirth;
    }

    @Override
    public String toString() {
        return "(" + getFullName() + "," + id + "," + yearOfBirth + ")";
    }

    public static void main(String[] args) {
        teacher teacher = new teacher("John", "Doe", "123456", 2002);

        System.out.println("Full Name: " + teacher.getFullName());
        System.out.println("Age: " + teacher.getAge());
        System.out.println("toString: " + teacher.toString());
    }
}
