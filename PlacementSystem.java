import java.util.ArrayList;

public class PlacementSystem {

    // Core data structures
    private StudentHashMap studentMap;
    private ApplicationLinkedList applicationList;
    private InterviewQueue interviewQueue;
    private ActionStack actionStack;

    // Other application data
    private ArrayList<Company> companies;
    private ArrayList<Job> jobs;

    // Authentication service
    private AuthenticationService authenticationService;

    // Constructor
    public PlacementSystem() {

        studentMap = new StudentHashMap();
        applicationList = new ApplicationLinkedList();
        interviewQueue = new InterviewQueue();
        actionStack = new ActionStack();

        companies = new ArrayList<>();
        jobs = new ArrayList<>();

        authenticationService = new AuthenticationService();
    }

    // ==================== STUDENT MANAGEMENT ====================

    // Register a new student
    public boolean registerStudent(Student student) {

        if (student == null) {
            return false;
        }

        if (!ValidationUtil.isValidName(student.getName()) ||
            !ValidationUtil.isValidEmail(student.getEmail()) ||
            !ValidationUtil.isValidPassword(student.getPassword()) ||
            !ValidationUtil.isValidCGPA(student.getCgpa()) ||
            !ValidationUtil.isValidPhone(student.getPhoneNumber())) {

            return false;
        }

        if (studentMap.searchStudent(student.getUserId()) != null) {
            return false;
        }

        studentMap.addStudent(student);
        authenticationService.registerUser(student);

        actionStack.push(
                "Student " + student.getName() + " registered"
        );

        return true;
    }

    // Search student by ID
    public Student searchStudent(int studentId) {

        return studentMap.searchStudent(studentId);
    }

    // ==================== COMPANY MANAGEMENT ====================

    // Add a company
    public boolean addCompany(Company company) {

        if (company == null) {
            return false;
        }

        for (Company existingCompany : companies) {

            if (existingCompany.getCompanyId()
                    == company.getCompanyId()) {

                return false;
            }
        }

        companies.add(company);

        actionStack.push(
                "Company " + company.getCompanyName() + " added"
        );

        return true;
    }

    // Find company by ID
    public Company findCompany(int companyId) {

        for (Company company : companies) {

            if (company.getCompanyId() == companyId) {
                return company;
            }
        }

        return null;
    }

    // Get all companies
    public ArrayList<Company> getCompanies() {

        return companies;
    }

    // ==================== JOB MANAGEMENT ====================

    // Add a job to a company
    public boolean addJob(Job job, int companyId) {

        if (job == null) {
            return false;
        }

        Company company = findCompany(companyId);

        if (company == null) {
            return false;
        }

        for (Job existingJob : jobs) {

            if (existingJob.getJobId() == job.getJobId()) {
                return false;
            }
        }

        jobs.add(job);
        company.addJob(job);

        actionStack.push(
                "Job " + job.getJobTitle()
                        + " added to "
                        + company.getCompanyName()
        );

        return true;
    }

    // Find job by ID
    public Job findJob(int jobId) {

        for (Job job : jobs) {

            if (job.getJobId() == jobId) {
                return job;
            }
        }

        return null;
    }

    // Get all jobs
    public ArrayList<Job> getJobs() {

        return jobs;
    }

    // ==================== APPLICATION MANAGEMENT ====================

    // Student applies for a job
    public boolean applyForJob(int applicationId,
                               int studentId,
                               int jobId) {

        Student student = studentMap.searchStudent(studentId);
        Job job = findJob(jobId);

        if (student == null || job == null) {
            return false;
        }

        // Check eligibility
        if (!job.isStudentEligible(student)) {
            return false;
        }

        // Prevent duplicate applications
        if (findApplicationByStudentAndJob(studentId, jobId)
                != null) {

            return false;
        }

        Application application =
                new Application(applicationId, student, job);

        applicationList.addApplication(application);

        // Add application to interview queue
        interviewQueue.enqueue(application);

        actionStack.push(
                student.getName()
                        + " applied for "
                        + job.getJobTitle()
        );

        return true;
    }

    // Find an application
    public Application findApplication(int applicationId) {

        return applicationList.searchApplication(applicationId);
    }

    // Find application by student and job
    public Application findApplicationByStudentAndJob(
            int studentId, int jobId) {

        Application current =
                applicationList.getHead();

        while (current != null) {

            if (current.getStudent().getUserId() == studentId &&
                current.getJob().getJobId() == jobId) {

                return current;
            }

            current = applicationList.getNext(current);
        }

        return null;
    }

    // Update application status
    public boolean updateApplicationStatus(
            int applicationId, String newStatus) {

        Application application =
                findApplication(applicationId);

        if (application == null ||
            newStatus == null ||
            newStatus.trim().isEmpty()) {

            return false;
        }

        application.setStatus(newStatus);

        actionStack.push(
                "Application " + applicationId
                        + " status changed to "
                        + newStatus
        );

        return true;
    }

    // ==================== INTERVIEW MANAGEMENT ====================

    // Schedule an interview
    public boolean scheduleInterview(
            int applicationId,
            String date,
            String time,
            String mode,
            String interviewer) {

        Application application =
                findApplication(applicationId);

        if (application == null) {
            return false;
        }

        application.scheduleInterview(
                date,
                time,
                mode,
                interviewer
        );

        actionStack.push(
                "Interview scheduled for application "
                        + applicationId
        );

        return true;
    }

    // Get next interview
    public Application getNextInterview() {

        return interviewQueue.peek();
    }

    // Process next interview
    public Application processNextInterview() {

        Application application =
                interviewQueue.dequeue();

        if (application != null) {

            actionStack.push(
                    "Interview processed for application "
                            + application.getApplicationId()
            );
        }

        return application;
    }

    // ==================== AUTHENTICATION ====================

    // Login user
    public User login(int userId, String password) {

        return authenticationService.login(
                userId,
                password
        );
    }

    // ==================== DISPLAY METHODS ====================

    // Display all students
    public void displayStudents() {

        studentMap.getAllStudents();
    }

    // Display all applications
    public void displayApplications() {

        applicationList.displayApplications();
    }

    // Display recent actions
    public void displayRecentActions() {

        actionStack.displayStack();
    }

    // Display next interview
    public void displayNextInterview() {

        Application application =
                interviewQueue.peek();

        if (application == null) {

            System.out.println(
                    "No interviews in the queue."
            );

            return;
        }

        application.displayApplicationDetails();
    }

    // ==================== SORTING ====================

    // Sort students by CGPA
    public void sortStudentsByCGPA() {

        StudentSorting.sortByCGPA(
                studentMap.getAllStudentsList()
        );
    }

    // ==================== SUMMARY ====================

    public void displaySystemSummary() {

        System.out.println("\n===== SYSTEM SUMMARY =====");

        System.out.println(
                "Total Students: "
                        + studentMap.getTotalStudents()
        );

        System.out.println(
                "Total Companies: "
                        + companies.size()
        );

        System.out.println(
                "Total Jobs: "
                        + jobs.size()
        );

        System.out.println(
                "Total Applications: "
                        + applicationList.getSize()
        );

        System.out.println(
                "Interviews Remaining: "
                        + interviewQueue.size()
        );

        System.out.println(
                "Actions Remaining: "
                        + actionStack.size()
        );
    }
}
