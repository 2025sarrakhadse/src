import java.time.LocalDate;

public class Application {

    // Application data members
    private int applicationId;
    private Student student;
    private Job job;
    private String status;
    private LocalDate applicationDate;
    private String interviewDate;
    private String interviewTime;
    private String interviewMode;
    private String interviewer;

    // Constructor
    public Application(int applicationId, Student student, Job job) {

        this.applicationId = applicationId;
        this.student = student;
        this.job = job;

        // Every new application starts with Applied status
        this.status = "Applied";

        // Automatically store the current date
        this.applicationDate = LocalDate.now();

        // Interview details are initially empty
        this.interviewDate = "";
        this.interviewTime = "";
        this.interviewMode = "";
        this.interviewer = "";
    }

    // Getter for applicationId
    public int getApplicationId() {
        return applicationId;
    }

    // Getter for student
    public Student getStudent() {
        return student;
    }

    // Getter for job
    public Job getJob() {
        return job;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Getter for application date
    public LocalDate getApplicationDate() {
        return applicationDate;
    }

    // Getter for interview date
    public String getInterviewDate() {
        return interviewDate;
    }

    // Getter for interview time
    public String getInterviewTime() {
        return interviewTime;
    }

    // Getter for interview mode
    public String getInterviewMode() {
        return interviewMode;
    }

    // Getter for interviewer
    public String getInterviewer() {
        return interviewer;
    }

    // Update application status
    public void setStatus(String status) {

        if (status != null && !status.trim().isEmpty()) {
            this.status = status;
        }
    }

    // Schedule an interview
    public void scheduleInterview(String date, String time,
                                   String mode, String interviewer) {

        this.interviewDate = date;
        this.interviewTime = time;
        this.interviewMode = mode;
        this.interviewer = interviewer;

        this.status = "Interview Scheduled";
    }

    // Clear interview details
    public void cancelInterview() {

        this.interviewDate = "";
        this.interviewTime = "";
        this.interviewMode = "";
        this.interviewer = "";

        this.status = "Shortlisted";
    }

    // Check whether interview is scheduled
    public boolean isInterviewScheduled() {

        return !interviewDate.isEmpty();
    }

    // Display application details
    public void displayApplicationDetails() {

        System.out.println("\n===== Application Details =====");

        System.out.println("Application ID: " + applicationId);

        System.out.println("Student ID: " +
                student.getUserId());

        System.out.println("Student Name: " +
                student.getName());

        System.out.println("Job ID: " +
                job.getJobId());

        System.out.println("Job Title: " +
                job.getJobTitle());

        System.out.println("Company: " +
                (job.getCompany() != null
                        ? job.getCompany().getCompanyName()
                        : "Not specified"));

        System.out.println("Application Date: " +
                applicationDate);

        System.out.println("Status: " +
                status);

        // Display interview information only if scheduled
        if (isInterviewScheduled()) {

            System.out.println("\n===== Interview Details =====");

            System.out.println("Interview Date: " +
                    interviewDate);

            System.out.println("Interview Time: " +
                    interviewTime);

            System.out.println("Interview Mode: " +
                    interviewMode);

            System.out.println("Interviewer: " +
                    interviewer);
        }
    }
}
