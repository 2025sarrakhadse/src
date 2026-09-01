import java.util.ArrayList;

public class PlacementSystem {

    // =====================================================
    // SINGLETON INSTANCE
    // =====================================================

    private static final PlacementSystem INSTANCE =
            new PlacementSystem();

    public static PlacementSystem getInstance() {
        return INSTANCE;
    }


    // =====================================================
    // DSA DATA STRUCTURES
    // =====================================================

    // Students stored using HashMap
    private StudentHashMap studentMap;

    // Companies stored using ArrayList
    private ArrayList<Company> companies;

    // Applications stored using Linked List
    private ApplicationLinkedList applicationList;

    // Interview applications stored using Queue
    private InterviewQueue interviewQueue;

    // Recent actions stored using Stack
    private ActionStack actionStack;


    // =====================================================
    // ID GENERATOR
    // =====================================================

    private int nextApplicationId = 1001;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public PlacementSystem() {

        studentMap = new StudentHashMap();

        companies = new ArrayList<>();

        applicationList =
                new ApplicationLinkedList();

        interviewQueue =
                new InterviewQueue();

        actionStack =
                new ActionStack();
    }


    // =====================================================
    // STUDENT MANAGEMENT
    // =====================================================

    // Register student
    public boolean registerStudent(Student student) {

        if (student == null) {
            return false;
        }

        boolean added =
                studentMap.addStudent(student);

        if (added) {

            actionStack.push(
                    "Student registered: "
                    + student.getName()
            );
        }

        return added;
    }


    // Add student - compatibility method
    public void addStudent(Student student) {

        registerStudent(student);
    }


    // Find student
    public Student findStudent(int studentId) {

        return studentMap.searchStudent(studentId);
    }


    // Search student
    public Student searchStudent(int studentId) {

        return findStudent(studentId);
    }


    // Delete student
    public boolean deleteStudent(int studentId) {

        Student student =
                findStudent(studentId);

        if (student == null) {
            return false;
        }

        boolean deleted =
                studentMap.deleteStudent(studentId);

        if (deleted) {

            actionStack.push(
                    "Student deleted: "
                    + student.getName()
            );
        }

        return deleted;
    }


    // Get all students
    public Student[] getAllStudents() {

        return studentMap.getAllStudents();
    }


    // Total students
    public int getTotalStudents() {

        return studentMap.getTotalStudents();
    }


    // Display students
    public void displayAllStudents() {

        studentMap.displayAllStudents();
    }


    // Students sorted by CGPA
    public Student[] getStudentsSortedByCGPA() {

        Student[] students =
                getAllStudents();

        StudentSorting.sortByCGPA(students);

        return students;
    }


    // Display sorted students
    public void displayStudentsSortedByCGPA() {

        Student[] students =
                getStudentsSortedByCGPA();

        StudentSorting.displaySortedStudents(
                students
        );
    }


    // =====================================================
    // COMPANY MANAGEMENT
    // =====================================================

    // Add company
    public boolean addCompany(Company company) {

        if (company == null) {
            return false;
        }

        if (findCompany(
                company.getCompanyId()
        ) != null) {

            return false;
        }

        companies.add(company);

        actionStack.push(
                "Company added: "
                + company.getCompanyName()
        );

        return true;
    }


    // Find company
    public Company findCompany(int companyId) {

        for (Company company : companies) {

            if (company.getCompanyId()
                    == companyId) {

                return company;
            }
        }

        return null;
    }


    // Search company
    public Company searchCompany(int companyId) {

        return findCompany(companyId);
    }


    // Get all companies
    public Company[] getAllCompanies() {

        return companies.toArray(
                new Company[0]
        );
    }


    // Get companies as ArrayList
    public ArrayList<Company> getCompanies() {

        return companies;
    }


    // Total companies
    public int getTotalCompanies() {

        return companies.size();
    }


    // Display companies
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


    // Delete company
    public boolean deleteCompany(int companyId) {

        Company company =
                findCompany(companyId);

        if (company == null) {
            return false;
        }

        companies.remove(company);

        actionStack.push(
                "Company deleted: "
                + company.getCompanyName()
        );

        return true;
    }


    // =====================================================
    // JOB MANAGEMENT
    // =====================================================

    // Add job to company
    public boolean addJob(
            int companyId,
            Job job) {

        Company company =
                findCompany(companyId);

        if (company == null || job == null) {
            return false;
        }

        company.addJob(job);

        actionStack.push(
                "Job added: "
                + job.getJobTitle()
        );

        return true;
    }


    // Add job using Job's company
    public boolean addJob(Job job) {

        if (job == null ||
                job.getCompany() == null) {

            return false;
        }

        int companyId =
                job.getCompany().getCompanyId();

        return addJob(companyId, job);
    }


    // Find job
    public Job findJob(int jobId) {

        for (Company company : companies) {

            Job job =
                    company.findJob(jobId);

            if (job != null) {
                return job;
            }
        }

        return null;
    }


    // Search job
    public Job searchJob(int jobId) {

        return findJob(jobId);
    }


    // Get all jobs
    public Job[] getAllJobs() {

        ArrayList<Job> allJobs =
                new ArrayList<>();

        for (Company company : companies) {

            if (company.getJobs() != null) {

                allJobs.addAll(
                        company.getJobs()
                );
            }
        }

        return allJobs.toArray(
                new Job[0]
        );
    }


    // Total jobs
    public int getTotalJobs() {

        return getAllJobs().length;
    }


    // Display all jobs
    public void displayAllJobs() {

        Job[] jobs =
                getAllJobs();

        if (jobs.length == 0) {

            System.out.println(
                    "No jobs available."
            );

            return;
        }

        System.out.println(
                "\n===== AVAILABLE JOBS ====="
        );

        for (Job job : jobs) {

            job.displayJobDetails();
        }
    }


    // Display jobs compatibility method
    public void displayJobs() {

        displayAllJobs();
    }


    // Delete job
    public boolean deleteJob(int jobId) {

        for (Company company : companies) {

            Job job =
                    company.findJob(jobId);

            if (job != null) {

                company.removeJob(jobId);

                actionStack.push(
                        "Job deleted: "
                        + job.getJobTitle()
                );

                return true;
            }
        }

        return false;
    }


    // =====================================================
    // APPLICATION MANAGEMENT
    // =====================================================

    // Student applies for job
    public Application applyForJob(
            int studentId,
            int jobId) {

        Student student =
                findStudent(studentId);

        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return null;
        }

        Job job =
                findJob(jobId);

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


        // Prevent duplicate application
        if (hasApplied(studentId, jobId)) {

            System.out.println(
                    "Student has already applied."
            );

            return null;
        }


        Application application =
                new Application(
                        nextApplicationId++,
                        student,
                        job
                );


        applicationList.addApplication(
                application
        );


        actionStack.push(
                student.getName()
                + " applied for "
                + job.getJobTitle()
        );


        return application;
    }


    // Add existing application
    public boolean addApplication(
            Application application) {

        if (application == null) {
            return false;
        }

        if (findApplication(
                application.getApplicationId()
        ) != null) {

            return false;
        }

        applicationList.addApplication(
                application
        );

        actionStack.push(
                "Application added: "
                + application.getApplicationId()
        );

        return true;
    }


    // Check duplicate application
    public boolean hasApplied(
            int studentId,
            int jobId) {

        Application[] applications =
                getAllApplications();

        for (Application application :
                applications) {

            if (application.getStudent()
                    .getUserId()
                    == studentId
                    &&
                    application.getJob()
                    .getJobId()
                    == jobId) {

                return true;
            }
        }

        return false;
    }


    // Find application
    public Application findApplication(
            int applicationId) {

        return applicationList
                .searchApplication(
                        applicationId
                );
    }


    // Search application
    public Application searchApplication(
            int applicationId) {

        return findApplication(applicationId);
    }


    // Get all applications
    public Application[] getAllApplications() {

        ArrayList<Application> applications =
                new ArrayList<>();

        int total =
                applicationList
                        .getTotalApplications();


        for (int id = 1;
             id <= nextApplicationId;
             id++) {

            Application application =
                    applicationList
                            .searchApplication(id);

            if (application != null) {

                applications.add(application);
            }

            if (applications.size() == total) {
                break;
            }
        }


        return applications.toArray(
                new Application[0]
        );
    }


    // Get student's applications
    public Application[] getStudentApplications(
            int studentId) {

        ArrayList<Application> result =
                new ArrayList<>();

        for (Application application :
                getAllApplications()) {

            if (application.getStudent()
                    .getUserId()
                    == studentId) {

                result.add(application);
            }
        }

        return result.toArray(
                new Application[0]
        );
    }


    // Get job applications
    public Application[] getJobApplications(
            int jobId) {

        ArrayList<Application> result =
                new ArrayList<>();

        for (Application application :
                getAllApplications()) {

            if (application.getJob()
                    .getJobId()
                    == jobId) {

                result.add(application);
            }
        }

        return result.toArray(
                new Application[0]
        );
    }


    // Total applications
    public int getTotalApplications() {

        return applicationList
                .getTotalApplications();
    }


    // Delete application
    public boolean deleteApplication(
            int applicationId) {

        Application application =
                findApplication(applicationId);

        if (application == null) {
            return false;
        }

        boolean deleted =
                applicationList.deleteApplication(
                        applicationId
                );

        if (deleted) {

            actionStack.push(
                    "Application deleted: "
                    + applicationId
            );
        }

        return deleted;
    }


    // Display applications
    public void displayApplications() {

        applicationList.displayApplications();
    }


    // Display all applications compatibility method
    public void displayAllApplications() {

        displayApplications();
    }


    // =====================================================
    // APPLICATION STATUS
    // =====================================================

    public boolean updateApplicationStatus(
            int applicationId,
            String status) {

        Application application =
                findApplication(applicationId);

        if (application == null ||
                status == null ||
                status.trim().isEmpty()) {

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


    public boolean shortlistApplication(
            int applicationId) {

        return updateApplicationStatus(
                applicationId,
                "Shortlisted"
        );
    }


    public boolean rejectApplication(
            int applicationId) {

        return updateApplicationStatus(
                applicationId,
                "Rejected"
        );
    }


    public boolean selectApplication(
            int applicationId) {

        return updateApplicationStatus(
                applicationId,
                "Selected"
        );
    }


    // =====================================================
    // INTERVIEW QUEUE
    // =====================================================

    // Add application to queue
    public boolean addToInterviewQueue(
            int applicationId) {

        Application application =
                findApplication(applicationId);

        if (application == null) {
            return false;
        }

        interviewQueue.enqueue(application);

        actionStack.push(
                "Application "
                + applicationId
                + " added to interview queue"
        );

        return true;
    }


    // Get next interview
    public Application getNextInterview() {

        return interviewQueue.peek();
    }


    // Compatibility method
    public Application peekNextInterview() {

        return getNextInterview();
    }


    // Process next interview
    public Application processNextInterview() {

        Application application =
                interviewQueue.dequeue();

        if (application != null) {

            actionStack.push(
                    "Interview processed for "
                    + application
                    .getStudent()
                    .getName()
            );
        }

        return application;
    }


    // Display interview queue
    public void displayInterviewQueue() {

        interviewQueue.displayQueue();
    }


    // Queue empty
    public boolean isInterviewQueueEmpty() {

        return interviewQueue.isEmpty();
    }


    // Queue size
    public int getInterviewQueueSize() {

        return interviewQueue.size();
    }


    // Compatibility method
    public int getInterviewsRemaining() {

        return getInterviewQueueSize();
    }


    // =====================================================
    // INTERVIEW SCHEDULING
    // =====================================================

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
                    + application
                    .getStudent()
                    .getName()
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
                + application
                .getStudent()
                .getName()
        );

        return true;
    }


    // =====================================================
    // ACTION STACK
    // =====================================================

    // Get action stack
    public ActionStack getActionStack() {

        return actionStack;
    }


    // Add action
    public void addAction(String action) {

        if (action != null &&
                !action.trim().isEmpty()) {

            actionStack.push(action);
        }
    }


    // Get latest action
    public String getLastAction() {

        return actionStack.peek();
    }


    // Remove latest action
    public String undoLastAction() {

        return actionStack.pop();
    }


    // Display actions
    public void displayRecentActions() {

        actionStack.displayActions();
    }


    // Action count
    public int getActionCount() {

        return actionStack.size();
    }


    // Compatibility method
    public int getActionStackSize() {

        return actionStack.size();
    }


    // Compatibility method
    public int getActionsRemaining() {

        return actionStack.size();
    }


    // =====================================================
    // LOGIN
    // =====================================================

    // Student login
    public Student studentLogin(
            String email,
            String password) {

        if (email == null ||
                password == null) {

            return null;
        }

        for (Student student :
                getAllStudents()) {

            if (student.getEmail()
                    .equalsIgnoreCase(email)
                    &&
                    student.getPassword()
                    .equals(password)) {

                return student;
            }
        }

        return null;
    }


    // Placement officer
    private PlacementOfficer placementOfficer;


    public void setPlacementOfficer(
            PlacementOfficer officer) {

        this.placementOfficer = officer;
    }


    public PlacementOfficer getPlacementOfficer() {

        return placementOfficer;
    }


    // Officer login
    public PlacementOfficer officerLogin(
            String email,
            String password) {

        if (placementOfficer == null ||
                email == null ||
                password == null) {

            return null;
        }

        if (placementOfficer.getEmail()
                .equalsIgnoreCase(email)
                &&
                placementOfficer.getPassword()
                .equals(password)) {

            return placementOfficer;
        }

        return null;
    }


    // =====================================================
    // STATISTICS
    // =====================================================

    public String getSystemStatistics() {

        return
                "===== PLACEMENT SYSTEM STATISTICS =====\n\n"
                + "Total Students: "
                + getTotalStudents()
                + "\n"
                + "Total Companies: "
                + getTotalCompanies()
                + "\n"
                + "Total Jobs: "
                + getTotalJobs()
                + "\n"
                + "Total Applications: "
                + getTotalApplications()
                + "\n"
                + "Interview Queue: "
                + getInterviewQueueSize()
                + "\n"
                + "Recent Actions: "
                + getActionStackSize();
    }


    // =====================================================
    // SYSTEM SUMMARY
    // =====================================================

    public void displaySystemSummary() {

        System.out.println(
                getSystemStatistics()
        );
    }
}