public abstract class User {

    // Private data members
    private int userId;
    private String name;
    private String email;

    // Constructor
    public User(int userId, String name, String email) {
        this.userId = userId;
        this.name = name;
        this.email = email;
    }

    // Getter for userId
    public int getUserId() {
        return userId;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Abstract method
    public abstract void displayRole();
}