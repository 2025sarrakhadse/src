public class Student extends User {

    // Student-specific data members
    private String department;
    private double cgpa;
    private String[] skills;
    private String phoneNumber;

    // Constructor
    public Student(int userId, String name, String email, String password,
                   String department, double cgpa, String[] skills,
                   String phoneNumber) {

        // Calling parent class constructor
        super(userId, name, email, password);

        this.department = department;
        this.cgpa = cgpa;
        this.skills = skills;
        this.phoneNumber = phoneNumber;
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

    // Getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter for department
    public void setDepartment(String department) {
        this.department = department;
    }

    // Setter for CGPA
    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }

    // Setter for skills
    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    // Setter for phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Department: " + department);
        System.out.println("CGPA: " + cgpa);

        System.out.print("Skills: ");

        if (skills != null) {
            for (String skill : skills) {
                System.out.print(skill + " ");
            }
        }

        System.out.println();
    }

    // Check whether student has a particular skill
    public boolean hasSkill(String requiredSkill) {

        if (skills == null || requiredSkill == null) {
            return false;
        }

        for (String skill : skills) {

            if (skill.equalsIgnoreCase(requiredSkill)) {
                return true;
            }
        }

        return false;
    }

    // Check whether student is eligible for a job
    public boolean isEligible(double minimumCGPA, String requiredSkill) {

        return cgpa >= minimumCGPA && hasSkill(requiredSkill);
    }
}
