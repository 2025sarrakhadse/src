import java.util.ArrayList;

public class PlacementSystem {

    // ==============================
    // DSA DATA STRUCTURES
    // ==============================

    // Stores students using Student ID
    private StudentHashMap studentMap;

    // Stores all job applications
    private ApplicationLinkedList applicationList;

    // Stores applications waiting for interviews
    private InterviewQueue interviewQueue;

    // Stores recent actions
    private ActionStack actionStack;

    // Stores companies
    private ArrayList<Company> companies;


    // ==============================
    // ID GENERATORS
    // ==============================

    private int nextApplicationId = 1001;


    // ==============================
    // CONSTRUCTOR
    // ==============================

    public PlacementSystem() {

        studentMap = new StudentHashMap();
        applicationList = new ApplicationLinkedList();
        interviewQueue = new InterviewQueue();
        actionStack = new ActionStack();
        companies = new ArrayList<>();
    }


    // ==============================
    // STUDENT MANAGEMENT
    // ==============================

    // Register a new student
    public boolean registerStudent(Student student) {

        if (student == null) {
            return false;
        }

        boolean added = studentMap.addStudent(student);

        if (added) {

            actionStack.push(
                    "Student " + student.getName()
                    + " registered"
            );
        }

        return added;
    }


    // Search student
    public Student findStudent(int studentId) {

        return studentMap.searchStudent(studentId);
    }


    // Delete student
    public boolean deleteStudent(int studentId) {

        Student student = studentMap.searchStudent(studentId);

        if (student == null) {
            return false;
        }

        boolean deleted = studentMap.deleteStudent(studentId);

        if (deleted) {

            actionStack.push(
                    "Student " + student.getName()
                    + " deleted"
            );
        }

        return deleted;
    }


    // Display all students
    public void displayAllStudents() {

        studentMap.displayAllStudents();
    }


    // Get students sorted by CGPA
    public Student[] getStudentsSortedByCGPA() {

        Student[] students = studentMap.getAllStudents();

        StudentSorting.sortByCGPA(students);

        return students;
    }


    // ==============================
    // COMPANY MANAGEMENT
    // ==============================

    // Add company
    public boolean addCompany(Company company) {

        if (company == null) {
            return false;
        }

        companies.add(company);

        actionStack.push(
                "Company " + company.getCompanyName()
                + " added"
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


    // Display all companies
    public void displayCompanies() {

        if (companies.isEmpty()) {

            System.out.println(
                    "No companies registered."
            );

            return;
        }

        System.out.println(
                "\n===== REGISTERED COMPANIES ====="
        );

        for (Company company : companies) {

            company.displayCompanyDetails();
        }
    }


    // Get company list
    public ArrayList<Company> getCompanies() {

        return companies;
    }


    // ==============================
    // JOB MANAGEMENT
    // ==============================

    // Add job to a company
    public boolean addJob(int companyId, Job job) {

        Company company = findCompany(companyId);

        if (company == null || job == null) {
            return false;
        }

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

        for (Company company : companies) {

            Job job = company.findJob(jobId);

            if (job != null) {
                return job;
            }
        }

        return null;
    }


    // Display all available jobs
    public void displayAllJobs() {

        boolean found = false;

        System.out.println(
                "\n===== AVAILABLE JOBS ====="
        );

        for (Company company : companies) {

            for (Job job : company.getJobs()) {

                job.displayJobDetails();

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No jobs available."
            );
        }
    }


    // ==============================
    // APPLICATION MANAGEMENT
    // ==============================

    // Student applies for a job
    public Application applyForJob(
            int studentId,
            int jobId) {

        Student student =
                studentMap.searchStudent(studentId);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return null;
        }

        Job job = findJob(jobId);

        if (job == null) {

            System.out.println(
                    "Job not found."
            );

            return null;
        }


        // Check eligibility
        if (!job.isStudentEligible(student)) {

            System.out.println(
                    "Student is not eligible for this job."
            );

            return null;
        }


        // Create application
        Application application =
                new Application(
                        nextApplicationId++,
                        student,
                        job
                );


        // Add application to linked list
        applicationList.addApplication(application);


        // Add application to interview queue
        interviewQueue.enqueue(application);


        // Record action
        actionStack.push(
                student.getName()
                + " applied for "
                + job.getJobTitle()
        );


        System.out.println(
                "Application submitted successfully."
        );

        return application;
    }


    // Search application
    public Application findApplication(
            int applicationId) {

        return applicationList
                .searchApplication(applicationId);
    }


    // Display all applications
    public void displayApplications() {

        applicationList.displayApplications();
    }


    // ==============================
    // INTERVIEW MANAGEMENT
    // ==============================

    // View next interview
    public Application getNextInterview() {

        return interviewQueue.peek();
    }


    // Process next interview
    public Application processNextInterview() {

        Application application =
                interviewQueue.dequeue();

        if (application != null) {

            System.out.println(
                    "\nInterview processed for:"
            );

            application.displayApplicationDetails();

            actionStack.push(
                    "Interview processed for "
                    + application.getStudent().getName()
            );
        }

        return application;
    }


    // Display interview queue
    public void displayInterviewQueue() {

        interviewQueue.displayQueue();
    }


    // Schedule interview
    public boolean scheduleInterview(
            int applicationId,
            String date,
            String time,
            String mode,
            String interviewer) {

        Application application =
                findApplication(applicationId);

        if (application == null) {

            System.out.println(
                    "Application not found."
            );

            return false;
        }

        boolean scheduled =
                application.scheduleInterview(
                        date,
                        time,
                        mode,
                        interviewer
                );

        if (scheduled) {

            actionStack.push(
                    "Interview scheduled for "
                    + application.getStudent().getName()
            );
        }

        return scheduled;
    }


    // Cancel interview
    public boolean cancelInterview(
            int applicationId) {

        Application application =
                findApplication(applicationId);

        if (application == null) {
            return false;
        }

        application.cancelInterview();

        actionStack.push(
                "Interview cancelled for "
                + application.getStudent().getName()
        );

        return true;
    }


    // ==============================
    // APPLICATION STATUS
    // ==============================

    // Update application status
    public boolean updateApplicationStatus(
            int applicationId,
            String status) {

        Application application =
                findApplication(applicationId);

        if (application == null) {
            return false;
        }

        application.setStatus(status);

        actionStack.push(
                "Application "
                + applicationId
                + " status changed to "
                + status
        );

        return true;
    }


    // ==============================
    // ACTION STACK
    // ==============================

    // View recent actions
    public void displayRecentActions() {

        actionStack.displayActions();
    }


    // Undo latest action
    public String undoLastAction() {

        return actionStack.pop();
    }


    // ==============================
    // SEARCH
    // ==============================

    // Search student by ID
    public Student searchStudent(int studentId) {

        return studentMap.searchStudent(studentId);
    }


    // Search application by ID
    public Application searchApplication(
            int applicationId) {

        return applicationList
                .searchApplication(applicationId);
    }


    // ==============================
    // SYSTEM STATISTICS
    // ==============================

    public int getTotalStudents() {

        return studentMap.getTotalStudents();
    }


    public int getTotalApplications() {

        return applicationList
                .getTotalApplications();
    }


    public int getInterviewsRemaining() {

        return interviewQueue.size();
    }


    public int getActionsRemaining() {

        return actionStack.size();
    }


    public int getTotalCompanies() {

        return companies.size();
    }


    // ==============================
    // SYSTEM SUMMARY
    // ==============================

    public void displaySystemSummary() {

        System.out.println(
                "\n===== SYSTEM SUMMARY ====="
        );

        System.out.println(
                "Total Students: "
                + getTotalStudents()
        );

        System.out.println(
                "Total Companies: "
                + getTotalCompanies()
        );

        System.out.println(
                "Total Applications: "
                + getTotalApplications()
        );

        System.out.println(
                "Interviews Remaining: "
                + getInterviewsRemaining()
        );

        System.out.println(
                "Actions Remaining: "
                + getActionsRemaining()
        );
    }
}
