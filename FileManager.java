import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Multi-Entity Data Persistence Manager.
 * Handles reading and writing pipe-delimited text files for all core entities:
 * - students.txt
 * - companies.txt
 * - jobs.txt
 * - applications.txt
 * - interviews.txt
 */
public class FileManager {

    private static final String STUDENT_FILE = "students.txt";
    private static final String COMPANY_FILE = "companies.txt";
    private static final String JOB_FILE = "jobs.txt";
    private static final String APPLICATION_FILE = "applications.txt";
    private static final String INTERVIEW_FILE = "interviews.txt";

    // =====================================================
    // 1. STUDENTS PERSISTENCE (students.txt)
    // =====================================================

    public static void saveStudents(Student[] students) {
        if (students == null) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENT_FILE))) {
            for (Student s : students) {
                if (s == null) continue;

                String skills = "";
                if (s.getSkills() != null) {
                    skills = String.join(",", s.getSkills());
                }

                writer.println(
                        s.getUserId() + "|" +
                        s.getName() + "|" +
                        s.getEmail() + "|" +
                        s.getPassword() + "|" +
                        s.getPhoneNumber() + "|" +
                        s.getDepartment() + "|" +
                        s.getCgpa() + "|" +
                        skills
                );
            }
            System.out.println("Students saved to " + STUDENT_FILE);
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    public static Student[] loadStudents() {
        List<Student> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENT_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split("\\|", -1);
                if (data.length < 8) continue;

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String email = data[2].trim();
                    String pass = data[3].trim();
                    String phone = data[4].trim();
                    String dept = data[5].trim();
                    double cgpa = Double.parseDouble(data[6].trim());

                    String[] skills = data[7].trim().isEmpty() ? new String[0] : data[7].split(",");
                    for (int i = 0; i < skills.length; i++) {
                        skills[i] = skills[i].trim();
                    }

                    list.add(new Student(id, name, email, pass, dept, cgpa, skills, phone));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping malformed student line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("No student file found.");
        }
        return list.toArray(new Student[0]);
    }

    // =====================================================
    // 2. COMPANIES PERSISTENCE (companies.txt)
    // =====================================================

    public static void saveCompanies(Company[] companies) {
        if (companies == null) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(COMPANY_FILE))) {
            for (Company c : companies) {
                if (c == null) continue;
                writer.println(
                        c.getCompanyId() + "|" +
                        c.getCompanyName() + "|" +
                        c.getLocation() + "|" +
                        c.getHrName() + "|" +
                        c.getHrEmail() + "|" +
                        c.getContactNumber()
                );
            }
            System.out.println("Companies saved to " + COMPANY_FILE);
        } catch (IOException e) {
            System.out.println("Error saving companies: " + e.getMessage());
        }
    }

    public static Company[] loadCompanies() {
        List<Company> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(COMPANY_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split("\\|", -1);
                if (data.length < 6) continue;

                try {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String loc = data[2].trim();
                    String hrName = data[3].trim();
                    String hrEmail = data[4].trim();
                    String contact = data[5].trim();

                    list.add(new Company(id, name, loc, hrName, hrEmail, contact));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping malformed company line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("No company file found.");
        }
        return list.toArray(new Company[0]);
    }

    // =====================================================
    // 3. JOBS PERSISTENCE (jobs.txt)
    // =====================================================

    public static void saveJobs(Job[] jobs) {
        if (jobs == null) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(JOB_FILE))) {
            for (Job j : jobs) {
                if (j == null) continue;
                int companyId = j.getCompany() != null ? j.getCompany().getCompanyId() : 0;
                writer.println(
                        j.getJobId() + "|" +
                        j.getJobTitle() + "|" +
                        j.getSalaryLPA() + "|" +
                        j.getMinimumCGPA() + "|" +
                        j.getRequiredSkill() + "|" +
                        j.getLocation() + "|" +
                        j.getJobType() + "|" +
                        j.getApplicationDeadline() + "|" +
                        companyId
                );
            }
            System.out.println("Jobs saved to " + JOB_FILE);
        } catch (IOException e) {
            System.out.println("Error saving jobs: " + e.getMessage());
        }
    }

    public static Job[] loadJobs(PlacementSystem system) {
        List<Job> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(JOB_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split("\\|", -1);
                if (data.length < 9) continue;

                try {
                    int jobId = Integer.parseInt(data[0].trim());
                    String title = data[1].trim();
                    double salary = Double.parseDouble(data[2].trim());
                    double minCgpa = Double.parseDouble(data[3].trim());
                    String skill = data[4].trim();
                    String loc = data[5].trim();
                    String type = data[6].trim();
                    String deadline = data[7].trim();
                    int companyId = Integer.parseInt(data[8].trim());

                    Company company = system != null ? system.findCompany(companyId) : null;
                    list.add(new Job(jobId, title, salary, minCgpa, skill, loc, type, deadline, company));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping malformed job line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("No job file found.");
        }
        return list.toArray(new Job[0]);
    }

    // =====================================================
    // 4. APPLICATIONS PERSISTENCE (applications.txt)
    // =====================================================

    public static void saveApplications(Application[] apps) {
        if (apps == null) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(APPLICATION_FILE))) {
            for (Application a : apps) {
                if (a == null) continue;
                int studentId = a.getStudent() != null ? a.getStudent().getUserId() : 0;
                int jobId = a.getJob() != null ? a.getJob().getJobId() : 0;
                writer.println(
                        a.getApplicationId() + "|" +
                        studentId + "|" +
                        jobId + "|" +
                        a.getStatus() + "|" +
                        a.getApplicationDate()
                );
            }
            System.out.println("Applications saved to " + APPLICATION_FILE);
        } catch (IOException e) {
            System.out.println("Error saving applications: " + e.getMessage());
        }
    }

    public static Application[] loadApplications(PlacementSystem system) {
        List<Application> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(APPLICATION_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split("\\|", -1);
                if (data.length < 4) continue;

                try {
                    int appId = Integer.parseInt(data[0].trim());
                    int studentId = Integer.parseInt(data[1].trim());
                    int jobId = Integer.parseInt(data[2].trim());
                    String status = data[3].trim();

                    Student student = system != null ? system.findStudent(studentId) : null;
                    Job job = system != null ? system.findJob(jobId) : null;

                    if (student != null && job != null) {
                        Application app = new Application(appId, student, job);
                        app.setStatus(status);
                        list.add(app);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping malformed application line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("No application file found.");
        }
        return list.toArray(new Application[0]);
    }

    // =====================================================
    // 5. INTERVIEWS PERSISTENCE (interviews.txt)
    // =====================================================

    public static void saveInterviews(Application[] apps) {
        if (apps == null) return;

        try (PrintWriter writer = new PrintWriter(new FileWriter(INTERVIEW_FILE))) {
            for (Application a : apps) {
                if (a == null || !a.isInterviewScheduled()) continue;
                int studentId = a.getStudent() != null ? a.getStudent().getUserId() : 0;
                writer.println(
                        a.getApplicationId() + "|" +
                        studentId + "|" +
                        a.getInterviewDate() + "|" +
                        a.getInterviewTime() + "|" +
                        a.getInterviewMode() + "|" +
                        a.getInterviewerName()
                );
            }
            System.out.println("Interviews saved to " + INTERVIEW_FILE);
        } catch (IOException e) {
            System.out.println("Error saving interviews: " + e.getMessage());
        }
    }

    public static void loadInterviews(PlacementSystem system) {
        try (BufferedReader reader = new BufferedReader(new FileReader(INTERVIEW_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] data = line.split("\\|", -1);
                if (data.length < 6) continue;

                try {
                    int appId = Integer.parseInt(data[0].trim());
                    String date = data[2].trim();
                    String time = data[3].trim();
                    String mode = data[4].trim();
                    String interviewer = data[5].trim();

                    if (system != null) {
                        system.scheduleInterview(appId, date, time, mode, interviewer);
                        system.addToInterviewQueue(appId);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Skipping malformed interview line: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("No interview file found.");
        }
    }

    public static void displaySavedStudents() {
        Student[] students = loadStudents();
        if (students.length == 0) {
            System.out.println("\n===== SAVED STUDENTS =====\nNo student records found.");
            return;
        }
        System.out.println("\n===== SAVED STUDENTS =====");
        for (Student s : students) {
            System.out.println("ID: " + s.getUserId() + " | Name: " + s.getName() + " | CGPA: " + s.getCgpa());
        }
    }
}