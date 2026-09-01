public class Application {

    // Application data members
    private int applicationId;
    private Student student;
    private Job job;
    private String status;

    // Constructor
    public Application(int applicationId, Student student, Job job) {
        this.applicationId = applicationId;
        this.student = student;
        this.job = job;
        this.status = "Applied";
    }

    // Getters
    public int getApplicationId() {
        return applicationId;
    }

    public Student getStudent() {
        return student;
    }

    public Job getJob() {
        return job;
    }

    public String getStatus() {
        return status;
    }

    // Update application status
    public void setStatus(String status) {
        this.status = status;
    }

    // Display application details
    public void displayApplicationDetails() {

        System.out.println("\n===== Application Details =====");
        System.out.println("Application ID: " + applicationId);
        System.out.println("Student ID: " + student.getUserId());
        System.out.println("Student Name: " + student.getName());
        System.out.println("Job ID: " + job.getJobId());
        System.out.println("Job Title: " + job.getJobTitle());
        System.out.println("Status: " + status);
    }
}