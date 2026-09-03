import java.time.LocalDate;

public class Application {

    // Application data members
    private int applicationId;
    private Student student;
    private Job job;
    private String status;
    private LocalDate applicationDate;

    // Interview details
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

        // Store current date automatically
        this.applicationDate = LocalDate.now();

        // Interview details initially empty
        this.interviewDate = "";
        this.interviewTime = "";
        this.interviewMode = "";
        this.interviewer = "";
    }

    // Getter for application ID
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

    public String getInterviewerName() {
        return interviewer;
    }

    // Update application status
    public void setStatus(String status) {

        if (status != null && !status.trim().isEmpty()) {
            this.status = status;
        }
    }

    // Schedule an interview
    public boolean scheduleInterview(String date,
                                     String time,
                                     String mode,
                                     String interviewer) {

        if (date == null || date.trim().isEmpty()
                || time == null || time.trim().isEmpty()
                || mode == null || mode.trim().isEmpty()
                || interviewer == null || interviewer.trim().isEmpty()) {

            return false;
        }

        this.interviewDate = date;
        this.interviewTime = time;
        this.interviewMode = mode;
        this.interviewer = interviewer;

        this.status = "Interview Scheduled";

        return true;
    }

    // Cancel interview
    public void cancelInterview() {

        this.interviewDate = "";
        this.interviewTime = "";
        this.interviewMode = "";
        this.interviewer = "";

        this.status = "Shortlisted";
    }

    // Check whether interview is scheduled
    public boolean isInterviewScheduled() {

        return interviewDate != null
                && !interviewDate.trim().isEmpty();
    }

    // Display application details
    public void displayApplicationDetails() {

        System.out.println("\n===== Application Details =====");

        System.out.println(
                "Application ID: " + applicationId
        );

        if (student != null) {

            System.out.println(
                    "Student ID: " + student.getUserId()
            );

            System.out.println(
                    "Student Name: " + student.getName()
            );
        }

        if (job != null) {

            System.out.println(
                    "Job ID: " + job.getJobId()
            );

            System.out.println(
                    "Job Title: " + job.getJobTitle()
            );

            System.out.println(
                    "Company: " +
                    (job.getCompany() != null
                            ? job.getCompany().getCompanyName()
                            : "Not specified")
            );
        }

        System.out.println(
                "Application Date: " + applicationDate
        );

        System.out.println(
                "Status: " + status
        );

        // Display interview details only if scheduled
        if (isInterviewScheduled()) {

            System.out.println("\n===== Interview Details =====");

            System.out.println(
                    "Interview Date: " + interviewDate
            );

            System.out.println(
                    "Interview Time: " + interviewTime
            );

            System.out.println(
                    "Interview Mode: " + interviewMode
            );

            System.out.println(
                    "Interviewer: " + interviewer
            );
        }
    }
}
