public class ValidationUtil {

    // Private constructor prevents creating objects of this utility class
    private ValidationUtil() {
    }

    // Validate name
    public static boolean isValidName(String name) {

        return name != null &&
               !name.trim().isEmpty();
    }

    // Validate email
    public static boolean isValidEmail(String email) {

        return email != null &&
               email.contains("@") &&
               email.contains(".");
    }

    // Validate password
    public static boolean isValidPassword(String password) {

        return password != null &&
               password.length() >= 6;
    }

    // Validate CGPA
    public static boolean isValidCGPA(double cgpa) {

        return cgpa >= 0.0 && cgpa <= 10.0;
    }

    // Validate phone number
    public static boolean isValidPhone(String phone) {

        if (phone == null || phone.length() != 10) {
            return false;
        }

        for (char ch : phone.toCharArray()) {

            if (!Character.isDigit(ch)) {
                return false;
            }
        }

        return true;
    }

    // Validate required text field
    public static boolean isNotEmpty(String value) {

        return value != null &&
               !value.trim().isEmpty();
    }
}
