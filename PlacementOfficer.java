import java.util.ArrayList;

public class PlacementSystem {

    // =====================================================
    // DATA STRUCTURES
    // =====================================================

    // Students are stored using HashMap
    private StudentHashMap studentHashMap;

    // Companies are stored using ArrayList
    private ArrayList<Company> companies;

    // Jobs are stored using ArrayList
    private ArrayList<Job> jobs;

    // Applications are stored using Linked List
    private ApplicationLinkedList applicationList;

    // Interviews are managed using Queue
    private InterviewQueue interviewQueue;

    // Recent actions are stored using Stack
    private ActionStack actionStack;


    // =====================================================
    // ID GENERATORS
    // =====================================================

    private int nextApplicationId = 1;


    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public PlacementSystem() {

        studentHashMap = new StudentHashMap();

        companies = new ArrayList<>();

        jobs = new ArrayList<>();

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

    // Add student
    public void addStudent(Student student) {

        if (student == null) {
            return;
        }

        studentHashMap.addStudent(student);

        actionStack.push(
                "Student added: "
                + student.getName()
        );
    }


    // Find student by ID
    public Student findStudent(int studentId) {

        return studentHashMap.searchStudent(studentId);
    }


    // Search student by ID
    public Student searchStudent(int studentId) {

        return findStudent(studentId);
    }


    // Delete student
    public void deleteStudent(int studentId) {

        Student student =
                studentHashMap.searchStudent(studentId);

        if (student != null) {

            studentHashMap.deleteStudent(studentId);

            actionStack.push(
                    "Student deleted: "
                    + student.getName()
            );
        }
    }


    // Get all students
    public Student[] getAllStudents() {

        return studentHashMap.getAllStudents();
    }


    // Get total students
    public int getTotalStudents() {

        return studentHashMap.getTotalStudents();
    }


    // Display all students
    public void displayAllStudents() {

        studentHashMap.displayAllStudents();
    }


    // =====================================================
    // COMPANY MANAGEMENT
    // =====================================================

    // Add company
    public void addCompany(Company company) {

        if (company == null) {
            return;
        }

        // Prevent duplicate company IDs
        if (findCompany(company.getCompanyId()) != null) {

            System.out.println(
                    "Error: Company ID already exists!"
            );

            return;
        }

        companies.add(company);

        actionStack.push(
                "Company added: "
                + company.getCompanyName()
        );

        System.out.println(
                "Company added successfully."
        );
    }


    // Find company by ID
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


    // Get total companies
    public int getTotalCompanies() {

        return companies.size();
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


    // Display companies
    public void displayAllCompanies() {

        if (companies.isEmpty()) {

            System.out.println(
                    "No companies registered."
            );

            return;
        }

        for (Company company : companies) {

            company.displayCompanyDetails();
        }
    }


    // =====================================================
    // JOB MANAGEMENT
    // =====================================================

    // Add job
    public void addJob(Job job) {

        if (job == null) {
            return;
        }

        // Prevent duplicate Job IDs
        if (findJob(job.getJobId()) != null) {

            System.out.println(
                    "Error: Job ID already exists!"
            );

            return;
        }

        jobs.add(job);

        actionStack.push(
                "Job added: "
                + job.getJobTitle()
        );

        System.out.println(
                "Job added successfully."
        );
    }


    // Find job by ID
    public Job findJob(int jobId) {

        for (Job job : jobs) {

            if (job.getJobId()
                    == jobId) {

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

        return jobs.toArray(
                new Job[0]
        );
    }


    // Get total jobs
    public int getTotalJobs() {

        return jobs.size();
    }


    // Delete job
    public boolean deleteJob(int jobId) {

        Job job =
                findJob(jobId);

        if (job == null) {
            return false;
        }

        jobs.remove(job);

        // Also remove from company
        if (job.getCompany() != null) {

            job.getCompany().removeJob(
                    jobId
            );
        }

        actionStack.push(
                "Job deleted: "
                + job.getJobTitle()
        );

        return true;
    }


    // Display all jobs
    public void displayAllJobs() {

        if (jobs.isEmpty()) {

            System.out.println(
                    "No jobs available."
            );

            return;
        }

        for (Job job : jobs) {

            job.displayJobDetails();
        }
    }


    // =====================================================
    // APPLICATION MANAGEMENT
    // =====================================================

    // Student applies for a job
    public Application applyForJob(
            int studentId,
            int jobId) {

        Student student =
                findStudent(studentId);

        Job job =
                findJob(jobId);


        // Check student
        if (student == null) {

            System.out.println(
                    "Student not found."
            );

            return null;
        }


        // Check job
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
                    "Student has already applied for this job."
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


        // Add to linked list
        applicationList.addApplication(
                application
        );


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


    // Check whether student already applied
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


    // Get all applications
    public Application[] getAllApplications() {

        ArrayList<Application> result =
                new ArrayList<>();

        /*
         * ApplicationLinkedList currently provides
         * display/search/count operations.
         *
         * We retrieve applications by walking
         * through possible IDs.
         */

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

                result.add(application);
            }

            if (result.size() == total) {
                break;
            }
        }


        return result.toArray(
                new Application[0]
        );
    }


    // Get applications of one student
    public Application[] getStudentApplications(
            int studentId) {

        Application[] all =
                getAllApplications();

        ArrayList<Application> result =
                new ArrayList<>();


        for (Application application :
                all) {

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


    // Get applications for one job
    public Application[] getJobApplications(
            int jobId) {

        Application[] all =
                getAllApplications();

        ArrayList<Application> result =
                new ArrayList<>();


        for (Application application :
                all) {

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


    // Get total applications
    public int getTotalApplications() {

        return applicationList
                .getTotalApplications();
    }


    // Delete application
    public boolean deleteApplication(
            int applicationId) {

        Application application =
                findApplication(
                        applicationId
                );


        if (application == null) {
            return false;
        }


        boolean deleted =
                applicationList
                        .deleteApplication(
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


    // Display all applications
    public void displayAllApplications() {

        applicationList
                .displayApplications();
    }


    // =====================================================
    // APPLICATION STATUS
    // =====================================================

    // Update application status
    public boolean updateApplicationStatus(
            int applicationId,
            String status) {

        Application application =
                findApplication(
                        applicationId
                );


        if (application == null) {

            return false;
        }


        if (status == null
                || status.trim().isEmpty()) {

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


    // Shortlist student
    public boolean shortlistApplication(
            int applicationId) {

        return updateApplicationStatus(
                applicationId,
                "Shortlisted"
        );
    }


    // Reject application
    public boolean rejectApplication(
            int applicationId) {

        return updateApplicationStatus(
                applicationId,
                "Rejected"
        );
    }


    // Select student
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

    // Add application to interview queue
    public boolean addToInterviewQueue(
            int applicationId) {

        Application application =
                findApplication(
                        applicationId
                );


        if (application == null) {

            return false;
        }


        interviewQueue.enqueue(
                application
        );


        actionStack.push(
                "Application "
                + applicationId
                + " added to interview queue"
        );


        return true;
    }


    // Get next interview
    public Application peekNextInterview() {

        return interviewQueue.peek();
    }


    // Process next interview
    public Application processNextInterview() {

        Application application =
                interviewQueue.dequeue();


        if (application != null) {

            actionStack.push(
                    "Interview processed for application "
                    + application
                            .getApplicationId()
            );
        }


        return application;
    }


    // Display interview queue
    public void displayInterviewQueue() {

        interviewQueue.displayQueue();
    }


    // Check queue
    public boolean isInterviewQueueEmpty() {

        return interviewQueue.isEmpty();
    }


    // Get queue size
    public int getInterviewQueueSize() {

        return interviewQueue.size();
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
                findApplication(
                        applicationId
                );


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
                    "Interview scheduled for application "
                    + applicationId
            );
        }


        return scheduled;
    }


    // Cancel interview
    public boolean cancelInterview(
            int applicationId) {

        Application application =
                findApplication(
                        applicationId
                );


        if (application == null) {

            return false;
        }


        application.cancelInterview();


        actionStack.push(
                "Interview cancelled for application "
                + applicationId
        );


        return true;
    }


    // =====================================================
    // STUDENT SORTING
    // =====================================================

    public Student[] getStudentsSortedByCGPA() {

        Student[] students =
                getAllStudents();


        StudentSorting.sortByCGPA(
                students
        );


        return students;
    }


    public void displayStudentsSortedByCGPA() {

        Student[] students =
                getStudentsSortedByCGPA();


        StudentSorting
                .displaySortedStudents(
                        students
                );
    }


    // =====================================================
    // ACTION STACK
    // =====================================================

    // Add custom action
    public void addAction(String action) {

        if (action != null
                && !action.trim().isEmpty()) {

            actionStack.push(action);
        }
    }


    // View latest action
    public String getLastAction() {

        return actionStack.peek();
    }


    // Remove latest action
    public String undoLastAction() {

        return actionStack.pop();
    }


    // Display recent actions
    public void displayRecentActions() {

        actionStack.displayActions();
    }


    // Number of actions
    public int getActionCount() {

        return actionStack.size();
    }


    // =====================================================
    // LOGIN
    // =====================================================

    // Student login
    public Student studentLogin(
            String email,
            String password) {

        Student[] students =
                getAllStudents();


        if (email == null
                || password == null) {

            return null;
        }


        for (Student student :
                students) {

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


    // Placement officer login
    private PlacementOfficer placementOfficer;


    public void setPlacementOfficer(
            PlacementOfficer officer) {

        this.placementOfficer = officer;
    }


    public PlacementOfficer getPlacementOfficer() {

        return placementOfficer;
    }


    public PlacementOfficer officerLogin(
            String email,
            String password) {

        if (placementOfficer == null
                || email == null
                || password == null) {

            return null;
        }


        if (placementOfficer
                .getEmail()
                .equalsIgnoreCase(email)
                &&
                placementOfficer
                .getPassword()
                .equals(password)) {

            return placementOfficer;
        }


        return null;
    }


    // =====================================================
    // STATISTICS
    // =====================================================

    public String getSystemStatistics() {

        int students =
                getTotalStudents();

        int companies =
                getTotalCompanies();

        int jobs =
                getTotalJobs();

        int applications =
                getTotalApplications();

        int interviews =
                getInterviewQueueSize();


        return
                "===== PLACEMENT SYSTEM STATISTICS =====\n\n"
                + "Total Students: "
                + students
                + "\n"
                + "Total Companies: "
                + companies
                + "\n"
                + "Total Jobs: "
                + jobs
                + "\n"
                + "Total Applications: "
                + applications
                + "\n"
                + "Interview Queue: "
                + interviews;
    }


    // =====================================================
    // DISPLAY SYSTEM SUMMARY
    // =====================================================

    public void displaySystemSummary() {

        System.out.println(
                getSystemStatistics()
        );
    }
}
