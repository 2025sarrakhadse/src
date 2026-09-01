import java.util.ArrayList;

public class AuthenticationService {

    // Stores all registered users
    private ArrayList<User> users;

    // Constructor
    public AuthenticationService() {
        users = new ArrayList<>();
    }

    // Register a new user
    public boolean registerUser(User user) {

        if (user == null) {
            return false;
        }

        // Check whether user ID already exists
        for (User existingUser : users) {

            if (existingUser.getUserId() == user.getUserId()) {
                return false;
            }
        }

        users.add(user);
        return true;
    }

    // Login using user ID and password
    public User login(int userId, String password) {

        if (password == null) {
            return null;
        }

        for (User user : users) {

            if (user.getUserId() == userId &&
                user.getPassword().equals(password)) {

                return user;
            }
        }

        return null;
    }

    // Find a user by ID
    public User findUserById(int userId) {

        for (User user : users) {

            if (user.getUserId() == userId) {
                return user;
            }
        }

        return null;
    }

    // Remove a user
    public boolean removeUser(int userId) {

        for (int i = 0; i < users.size(); i++) {

            if (users.get(i).getUserId() == userId) {
                users.remove(i);
                return true;
            }
        }

        return false;
    }

    // Get total number of registered users
    public int getTotalUsers() {
        return users.size();
    }

    // Display all registered users
    public void displayAllUsers() {

        System.out.println("\n===== REGISTERED USERS =====");

        if (users.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        for (User user : users) {

            System.out.println("----------------------------");
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());

            user.displayRole();
        }
    }
}
