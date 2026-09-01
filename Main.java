import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static PlacementSystem system = new PlacementSystem();

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n====================================");
            System.out.println("     PLACEMENT MANAGEMENT SYSTEM");
            System.out.println("====================================");

            System.out.println("1. Student Management");
            System.out.println("2. Company Management");
            System.out.println("3. Job Management");
            System.out.println("4. Application Management");
            System.out.println("5. Interview Management");
            System.out.println("6. Search");
            System.out.println("7. Sort Students by CGPA");
            System.out.println("8. Recent Actions");
            System.out.println("9. System Summary");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    studentMenu();
                    break;

                case 2:
                    companyMenu();
                    break;

                case 3:
                    jobMenu();
                    break;

                case 4:
                    applicationMenu();
                    break;

                case 5:
                    interviewMenu();
                    break;

                case 6:
                    searchMenu();
                    break;

                case 7:
                    sortStudents();
                    break;

                case 8:
                    system.displayRecentActions();
                    break;

                case 9:
                    system.displaySystemSummary();
                    break;

                case 0:
                    System.out.println(
                            "\nThank you for using Placement Management System!"
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    // =====================================================
    // STUDENT MANAGEMENT
    // =====================================================

    private static void studentMenu() {

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT =====");

            System.out.println("1. Register Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Delete Student");
            System.out.println("0. Back");

            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    registerStudent();
                    break;

                case 2:
                    system.displayAllStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    deleteStudent();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void registerStudent() {

        System.out.println("\n===== REGISTER STUDENT =====");

        System.out.print("Student ID: ");
        int id = readInt();

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        System.out.print("CGPA: ");
        double cgpa = readDouble();

        System.out.print("Phone Number: ");
        String phone = scanner.nextLine();

        System.out.print("Skills (comma separated): ");
        String skillInput = scanner.nextLine();

        String[] skills = skillInput.split(",");

        for (int i = 0; i < skills.length; i++) {
            skills[i] = skills[i].trim();
        }

        Student student = new Student(
                id,
                name,
                email,
                password,
                department,
                cgpa,
                skills,
                phone
        );

        system.registerStudent(student);
    }


    private static void searchStudent() {

        System.out.print("Enter Student ID: ");

        int id = readInt();

        Student student = system.findStudent(id);

        if (student != null) {
            student.displayStudentDetails();
        }
    }


    private static void deleteStudent() {

        System.out.print("Enter Student ID: ");

        int id = readInt();

        system.deleteStudent(id);
    }


    // =====================================================
    // COMPANY MANAGEMENT
    // =====================================================

    private static void companyMenu() {

        while (true) {

            System.out.println("\n===== COMPANY MANAGEMENT =====");

            System.out.println("1. Add Company");
            System.out.println("2. View Companies");
            System.out.println("0. Back");

            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    addCompany();
                    break;

                case 2:
                    system.displayCompanies();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void addCompany() {

        System.out.println("\n===== ADD COMPANY =====");

        System.out.print("Company ID: ");
        int id = readInt();

        System.out.print("Company Name: ");
        String name = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("HR Name: ");
        String hrName = scanner.nextLine();

        System.out.print("HR Email: ");
        String hrEmail = scanner.nextLine();

        System.out.print("Contact Number: ");
        String contact = scanner.nextLine();

        Company company = new Company(
                id,
                name,
                location,
                hrName,
                hrEmail,
                contact
        );

        system.addCompany(company);

        System.out.println("Company added successfully.");
    }


    // =====================================================
    // JOB MANAGEMENT
    // =====================================================

    private static void jobMenu() {

        while (true) {

            System.out.println("\n===== JOB MANAGEMENT =====");

            System.out.println("1. Add Job");
            System.out.println("2. View All Jobs");
            System.out.println("0. Back");

            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    addJob();
                    break;

                case 2:
                    system.displayAllJobs();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void addJob() {

        System.out.println("\n===== ADD JOB =====");

        System.out.print("Company ID: ");
        int companyId = readInt();

        Company company = system.findCompany(companyId);

        if (company == null) {

            System.out.println("Company not found.");
            return;
        }

        System.out.print("Job ID: ");
        int jobId = readInt();

        System.out.print("Job Title: ");
        String title = scanner.nextLine();

        System.out.print("Salary (LPA): ");
        double salary = readDouble();

        System.out.print("Minimum CGPA: ");
        double cgpa = readDouble();

        System.out.print("Required Skill: ");
        String skill = scanner.nextLine();

        System.out.print("Location: ");
        String location = scanner.nextLine();

        System.out.print("Job Type: ");
        String jobType = scanner.nextLine();

        System.out.print("Application Deadline: ");
        String deadline = scanner.nextLine();

        Job job = new Job(
                jobId,
                title,
                salary,
                cgpa,
                skill,
                location,
                jobType,
                deadline,
                company
        );

        system.addJob(companyId, job);
    }


    // =====================================================
    // APPLICATION MANAGEMENT
    // =====================================================

    private static void applicationMenu() {

        while (true) {

            System.out.println("\n===== APPLICATION MANAGEMENT =====");

            System.out.println("1. Apply for Job");
            System.out.println("2. View Applications");
            System.out.println("3. Search Application");
            System.out.println("4. Update Application Status");
            System.out.println("0. Back");

            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    applyForJob();
                    break;

                case 2:
                    system.displayApplications();
                    break;

                case 3:
                    searchApplication();
                    break;

                case 4:
                    updateApplicationStatus();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void applyForJob() {

        System.out.print("Student ID: ");
        int studentId = readInt();

        System.out.print("Job ID: ");
        int jobId = readInt();

        system.applyForJob(studentId, jobId);
    }


    private static void searchApplication() {

        System.out.print("Application ID: ");

        int id = readInt();

        Application application = system.findApplication(id);

        if (application != null) {
            application.displayApplicationDetails();
        } else {
            System.out.println("Application not found.");
        }
    }


    private static void updateApplicationStatus() {

        System.out.print("Application ID: ");

        int id = readInt();

        System.out.print("New Status: ");

        String status = scanner.nextLine();

        if (system.updateApplicationStatus(id, status)) {

            System.out.println("Application status updated.");

        } else {

            System.out.println("Application not found.");
        }
    }


    // =====================================================
    // INTERVIEW MANAGEMENT
    // =====================================================

    private static void interviewMenu() {

        while (true) {

            System.out.println("\n===== INTERVIEW MANAGEMENT =====");

            System.out.println("1. View Interview Queue");
            System.out.println("2. View Next Interview");
            System.out.println("3. Process Next Interview");
            System.out.println("4. Schedule Interview");
            System.out.println("5. Cancel Interview");
            System.out.println("0. Back");

            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    system.displayInterviewQueue();
                    break;

                case 2:

                    Application next =
                            system.getNextInterview();

                    if (next != null) {
                        next.displayApplicationDetails();
                    }

                    break;

                case 3:
                    system.processNextInterview();
                    break;

                case 4:
                    scheduleInterview();
                    break;

                case 5:
                    cancelInterview();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void scheduleInterview() {

        System.out.print("Application ID: ");

        int id = readInt();

        System.out.print("Interview Date: ");
        String date = scanner.nextLine();

        System.out.print("Interview Time: ");
        String time = scanner.nextLine();

        System.out.print("Interview Mode: ");
        String mode = scanner.nextLine();

        System.out.print("Interviewer Name: ");
        String interviewer = scanner.nextLine();

        boolean success =
                system.scheduleInterview(
                        id,
                        date,
                        time,
                        mode,
                        interviewer
                );

        if (success) {

            System.out.println(
                    "Interview scheduled successfully."
            );

        } else {

            System.out.println(
                    "Unable to schedule interview."
            );
        }
    }


    private static void cancelInterview() {

        System.out.print("Application ID: ");

        int id = readInt();

        if (system.cancelInterview(id)) {

            System.out.println(
                    "Interview cancelled successfully."
            );

        } else {

            System.out.println(
                    "Application not found."
            );
        }
    }


    // =====================================================
    // SEARCH
    // =====================================================

    private static void searchMenu() {

        while (true) {

            System.out.println("\n===== SEARCH =====");

            System.out.println("1. Search Student");
            System.out.println("2. Search Application");
            System.out.println("3. Search Job");
            System.out.println("0. Back");

            System.out.print("Enter choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    searchStudent();
                    break;

                case 2:
                    searchApplication();
                    break;

                case 3:
                    searchJob();
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }


    private static void searchJob() {

        System.out.print("Enter Job ID: ");

        int id = readInt();

        Job job = system.findJob(id);

        if (job != null) {

            job.displayJobDetails();

        } else {

            System.out.println("Job not found.");
        }
    }


    // =====================================================
    // SORTING
    // =====================================================

    private static void sortStudents() {

        Student[] students =
                system.getStudentsSortedByCGPA();

        StudentSorting.displaySortedStudents(students);
    }


    // =====================================================
    // INPUT METHODS
    // =====================================================

    private static int readInt() {

        while (true) {

            try {

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Please enter a valid number: "
                );
            }
        }
    }


    private static double readDouble() {

        while (true) {

            try {

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.print(
                        "Please enter a valid number: "
                );
            }
        }
    }
}
