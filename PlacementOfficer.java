public class PlacementOfficer extends User {

    // Placement Officer-specific data members
    private String employeeId;
    private String department;
    private String phoneNumber;

    // Constructor
    public PlacementOfficer(int userId, String name, String email,
                            String password, String employeeId,
                            String department, String phoneNumber) {

        // Calling parent class constructor
        super(userId, name, email, password);

        this.employeeId = employeeId;
        this.department = department;
        this.phoneNumber = phoneNumber;
    }

    // Getter for employeeId
    public String getEmployeeId() {
        return employeeId;
    }

    // Getter for department
    public String getDepartment() {
        return department;
    }

    // Getter for phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter for employeeId
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    // Setter for department
    public void setDepartment(String department) {
        this.department = department;
    }

    // Setter for phone number
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Method Overriding
    @Override
    public void displayRole() {
        System.out.println("Role: Placement Officer");
    }

    // Display officer details
    public void displayOfficerDetails() {

        System.out.println("\n===== Placement Officer Details =====");

        System.out.println("User ID: " + getUserId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Department: " + department);
        System.out.println("Phone: " + phoneNumber);
    }
}
