public class PlacementOfficer extends User {

    // Placement Officer-specific data member
    private String employeeId;

    // Constructor
    public PlacementOfficer(int userId, String name, String email,
                            String employeeId) {

        // Calling parent class constructor
        super(userId, name, email);

        this.employeeId = employeeId;
    }

    // Getter for employeeId
    public String getEmployeeId() {
        return employeeId;
    }

    // Setter for employeeId
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
    }
}
