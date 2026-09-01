import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    // Data Structures
    static StudentHashMap studentMap = new StudentHashMap();

    static ApplicationLinkedList applicationList =
            new ApplicationLinkedList();

    static InterviewQueue interviewQueue =
            new InterviewQueue();

    static ActionStack actionStack =
            new ActionStack();

    // Company and Jobs
    static Company company;
    static Job job1;
    static Job job2;

    // ID Counters
    static int nextStudentId = 101;
    static int nextApplicationId = 1001;


    public static void main(String[] args) {

        initializeCompanyAndJobs();

        boolean running = true;

        System.out.println("========================================");
        System.out.println("       PLACEMENT MANAGEMENT SYSTEM");
        System.out.println("========================================");

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    registerStudent();
                    break;

                case 2:
                    searchStudent();
                    break;

                case 3:
                    displayStudents();
                    break;

                case 4:
                    applyForJob();
                    break;

                case 5:
                    displayApplications();
                    break;

                case 6:
                    searchApplication();
                    break;

                case 7:
                    scheduleInterview();
                    break;

                case 8:
                    viewNextInterview();
                    break;

                case 9:
                    processInterview();
                    break;

                case 10:
                    viewRecentActions();
                    break;

                case 11:
                    undoLastAction();
                    break;

                case 12:
                    displaySystemSummary();
                    break;

                case 13:
                    sortStudentsByCGPA();
                    break;

                case 14:
                    saveStudentsToFile();
                    break;

                case 15:
                    viewSavedStudents();
                    break;

                case 16:
                    running = false;

                    System.out.println(
                            "\nThank you for using the Placement Management System!"
                    );

                    break;

                default:
                    System.out.println(
                            "\nInvalid choice. Please enter a number between 1 and 16."
                    );
            }
        }

        scanner.close();
    }


    // ========================================
    // INITIALIZE COMPANY AND JOBS
    // ========================================

    static void initializeCompanyAndJobs() {

        company = new Company(
                201,
                "Tech Solutions",
                "techsolutions@gmail.com"
        );

        job1 = new Job(
                301,
                "Java Developer",
                8.5,
                7.5,
                "Java"
        );

        job2 = new Job(
                302,
                "Data Analyst",
                7.0,
                7.0,
                "Python"
        );

        company.addJob(job1);
        company.addJob(job2);
    }


    // ========================================
    // DISPLAY MENU
    // ========================================

    static void displayMenu() {

        System.out.println("\n========================================");
        System.out.println("       PLACEMENT MANAGEMENT SYSTEM");
        System.out.println("========================================");

        System.out.println("1. Register Student");
        System.out.println("2. Search Student");
        System.out.println("3. Display All Students");
        System.out.println("4. Apply for Job");
        System.out.println("5. Display Applications");
        System.out.println("6. Search Application");
        System.out.println("7. Schedule Interview");
        System.out.println("8. View Next Interview");
        System.out.println("9. Process Interview");
        System.out.println("10. View Recent Actions");
        System.out.println("11. Undo Last Action");
        System.out.println("12. System Summary");
        System.out.println("13. Sort Students by CGPA");
        System.out.println("14. Save Students to File");
        System.out.println("15. View Saved Students");
        System.out.println("16. Exit");

        System.out.println("========================================");
    }


    // ========================================
    // 1. REGISTER STUDENT
    // ========================================

    static void registerStudent() {

        System.out.println("\n===== REGISTER STUDENT =====");

        String name =
                readString("Enter student name: ");

        String email =
                readString("Enter email: ");

        String department =
                readString("Enter department: ");

        double cgpa =
                readDouble("Enter CGPA: ");

        String skillsInput =
                readString(
                        "Enter skills separated by spaces: "
                );

        String[] skills =
                skillsInput.trim().split("\\s+");

        Student student = new Student(
                nextStudentId,
                name,
                email,
                department,
                cgpa,
                skills
        );

        studentMap.addStudent(student);

        actionStack.push(
                "Student registered: " + name
        );

        System.out.println(
                "Student registered successfully!"
        );

        System.out.println(
                "Student ID: " + nextStudentId
        );

        nextStudentId++;
    }


    // ========================================
    // 2. SEARCH STUDENT
    // ========================================

    static void searchStudent() {

        System.out.println(
                "\n===== SEARCH STUDENT ====="
        );

        int id =
                readInt("Enter Student ID: ");

        Student student =
                studentMap.searchStudent(id);

        if (student != null) {

            student.displayStudentDetails();

        } else {

            System.out.println(
                    "Student not found."
            );
        }
    }


    // ========================================
    // 3. DISPLAY STUDENTS
    // ========================================

    static void displayStudents() {

        System.out.println(
                "\n===== ALL STUDENTS ====="
        );

        studentMap.displayAllStudents();
    }


    // ========================================
    // 4. APPLY FOR JOB
    // ========================================

    static void applyForJob() {

        System.out.println(
                "\n===== APPLY FOR JOB ====="
        );

        int studentId =
                readInt("Enter Student ID: ");

        Student student =
                studentMap.searchStudent(studentId);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return;
        }

        System.out.println("\nAvailable Jobs:");

        System.out.println(
                "301. Java Developer"
        );

        System.out.println(
                "302. Data Analyst"
        );

        int jobId =
                readInt("Enter Job ID: ");

        Job selectedJob;

        if (jobId == 301) {

            selectedJob = job1;

        } else if (jobId == 302) {

            selectedJob = job2;

        } else {

            System.out.println(
                    "Invalid Job ID."
            );

            return;
        }

        Application application =
                new Application(
                        nextApplicationId,
                        student,
                        selectedJob
                );

        applicationList.addApplication(
                application
        );

        actionStack.push(
        student.getName()
                + " applied for a job"
        );
       
        System.out.println(
                "Application submitted successfully!"
        );

        System.out.println(
                "Application ID: "
                        + nextApplicationId
        );

        nextApplicationId++;
    }


    // ========================================
    // 5. DISPLAY APPLICATIONS
    // ========================================

    static void displayApplications() {

        System.out.println(
                "\n===== ALL APPLICATIONS ====="
        );

        applicationList.displayApplications();
    }


    // ========================================
    // 6. SEARCH APPLICATION
    // ========================================

    static void searchApplication() {

        System.out.println(
                "\n===== SEARCH APPLICATION ====="
        );

        int id =
                readInt("Enter Application ID: ");

        Application application =
                applicationList.searchApplication(id);

        if (application != null) {

            application.displayApplicationDetails();

        } else {

            System.out.println(
                    "Application not found."
            );
        }
    }


    // ========================================
    // 7. SCHEDULE INTERVIEW
    // ========================================

    static void scheduleInterview() {

        System.out.println(
                "\n===== SCHEDULE INTERVIEW ====="
        );

        int applicationId =
                readInt(
                        "Enter Application ID: "
                );

        Application application =
                applicationList.searchApplication(
                        applicationId
                );

        if (application == null) {

            System.out.println(
                    "Application not found."
            );

            return;
        }

        interviewQueue.enqueue(application);

        actionStack.push(
                "Interview scheduled for Application "
                        + applicationId
        );

        System.out.println(
                "Interview scheduled successfully!"
        );
    }


    // ========================================
    // 8. VIEW NEXT INTERVIEW
    // ========================================

    static void viewNextInterview() {

        System.out.println(
                "\n===== NEXT INTERVIEW ====="
        );

        Application application =
                interviewQueue.peek();

        if (application != null) {

            application.displayApplicationDetails();

        } else {

            System.out.println(
                    "No interviews in the queue."
            );
        }
    }


    // ========================================
    // 9. PROCESS INTERVIEW
    // ========================================

    static void processInterview() {

        System.out.println(
                "\n===== PROCESS INTERVIEW ====="
        );

        Application application =
                interviewQueue.dequeue();

        if (application != null) {

            System.out.println(
                    "Processing interview:"
            );

            application.displayApplicationDetails();

            actionStack.push(
                    "Interview processed"
            );

        } else {

            System.out.println(
                    "No interviews available."
            );
        }
    }


    // ========================================
    // 10. VIEW RECENT ACTIONS
    // ========================================

    static void viewRecentActions() {

        System.out.println(
                "\n===== RECENT ACTIONS ====="
        );

        actionStack.displayActions();
    }


    // ========================================
    // 11. UNDO LAST ACTION
    // ========================================

    static void undoLastAction() {

        System.out.println(
                "\n===== UNDO LAST ACTION ====="
        );

        String action =
                actionStack.pop();

        if (action != null) {

            System.out.println(
                    "Last action removed:"
            );

            System.out.println(action);

        } else {

            System.out.println(
                    "No actions available to undo."
            );
        }
    }


    // ========================================
    // 12. SYSTEM SUMMARY
    // ========================================

    static void displaySystemSummary() {

        System.out.println(
                "\n===== SYSTEM SUMMARY ====="
        );

        System.out.println(
                "Total Students: "
                        + studentMap.getTotalStudents()
        );

        System.out.println(
                "Total Applications: "
                        + applicationList.getTotalApplications()
        );

        System.out.println(
                "Interviews in Queue: "
                        + interviewQueue.size()
        );

        System.out.println(
                "Recorded Actions: "
                        + actionStack.size()
        );
    }


    // ========================================
    // 13. SORT STUDENTS BY CGPA
    // ========================================

    static void sortStudentsByCGPA() {

        System.out.println(
                "\n===== SORT STUDENTS BY CGPA ====="
        );

        if (studentMap.getTotalStudents() == 0) {

            System.out.println(
                    "No students available for sorting."
            );

            return;
        }

        Student[] students =
                studentMap.getAllStudents();

        StudentSorting.sortByCGPA(students);

        StudentSorting.displaySortedStudents(
                students
        );
    }


    // ========================================
    // 14. SAVE STUDENTS TO FILE
    // ========================================

    static void saveStudentsToFile() {

        System.out.println(
                "\n===== SAVE STUDENTS ====="
        );

        if (studentMap.getTotalStudents() == 0) {

            System.out.println(
                    "No students available to save."
            );

            return;
        }

        Student[] students =
                studentMap.getAllStudents();

        FileManager.saveStudents(students);
    }


    // ========================================
    // 15. VIEW SAVED STUDENTS
    // ========================================

    static void viewSavedStudents() {

        System.out.println(
                "\n===== STUDENTS STORED IN FILE ====="
        );

        FileManager.displaySavedStudents();
    }


    // ========================================
    // INPUT METHODS
    // ========================================

    static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a number."
                );
            }
        }
    }


    static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        scanner.nextLine()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number."
                );
            }
        }
    }


    static String readString(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    scanner.nextLine().trim();

            if (!input.isEmpty()) {

                return input;

            } else {

                System.out.println(
                        "Input cannot be empty."
                );
            }
        }
    }
}