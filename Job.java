public class Job {

    // Job data members
    private int jobId;
    private String jobTitle;
    private double salary;
    private double minimumCGPA;
    private String requiredSkill;

    // Constructor
    public Job(int jobId, String jobTitle, double salary,
               double minimumCGPA, String requiredSkill) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.minimumCGPA = minimumCGPA;
        this.requiredSkill = requiredSkill;
    }

    // Getters
    public int getJobId() {
        return jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public double getSalary() {
        return salary;
    }

    public double getMinimumCGPA() {
        return minimumCGPA;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    // Display job details
    public void displayJobDetails() {

        System.out.println("----------------------------");
        System.out.println("Job ID: " + jobId);
        System.out.println("Job Title: " + jobTitle);
        System.out.println("Salary: " + salary + " LPA");
        System.out.println("Minimum CGPA: " + minimumCGPA);
        System.out.println("Required Skill: " + requiredSkill);
        System.out.println("----------------------------");
    }
}