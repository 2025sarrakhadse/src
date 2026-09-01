public class Student extends User {

    // Student-specific data members
    private String department;
    private double cgpa;
    private String[] skills;

    // Constructor
    public Student(int userId, String name, String email,
                   String department, double cgpa, String[] skills) {

        // Calling parent class constructor
        super(userId, name, email);

        this.department = department;
        this.cgpa = cgpa;
        this.skills = skills;
    }

    // Getter for department
    public String getDepartment() {
        return department;
    }

    // Getter for CGPA
    public double getCgpa() {
        return cgpa;
    }

    // Getter for skills
    public String[] getSkills() {
        return skills;
    }

    // Setter for department
    public void setDepartment(String department) {
        this.department = department;
    }

    // Setter for CGPA
    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    // Method Overriding
    @Override
    public void displayRole() {
        System.out.println("Role: Student");
    }

    // Display student details
    public void displayStudentDetails() {

        System.out.println("\n===== Student Details =====");
        System.out.println("Student ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Department: " + department);
        System.out.println("CGPA: " + cgpa);

        System.out.print("Skills: ");

        for (String skill : skills) {
            System.out.print(skill + " ");
        }

        System.out.println();
    }
}