public class PlacementOfficer extends User {

    private String employeeId;
    private String department;
    private String phoneNumber;

    public PlacementOfficer(int userId, String name, String email,
                            String password, String employeeId,
                            String department, String phoneNumber) {

        super(userId, name, email, password);

        this.employeeId = employeeId;
        this.department = department;
        this.phoneNumber = phoneNumber;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void displayRole() {
        System.out.println("Role: Placement Officer");
    }

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