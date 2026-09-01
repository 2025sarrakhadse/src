import java.util.ArrayList;

public class Company {

    // Company data members
    private int companyId;
    private String companyName;
    private String location;

    // ArrayList to store jobs offered by the company
    private ArrayList<Job> jobs;

    // Constructor
    public Company(int companyId, String companyName, String location) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.location = location;
        this.jobs = new ArrayList<>();
    }

    // Getter for companyId
    public int getCompanyId() {
        return companyId;
    }

    // Getter for companyName
    public String getCompanyName() {
        return companyName;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Getter for jobs
    public ArrayList<Job> getJobs() {
        return jobs;
    }

    // Add a job to the company
    public void addJob(Job job) {
        jobs.add(job);
        System.out.println("Job added successfully to " + companyName);
    }

    // Display company details
    public void displayCompanyDetails() {

        System.out.println("\n===== Company Details =====");
        System.out.println("Company ID: " + companyId);
        System.out.println("Company Name: " + companyName);
        System.out.println("Location: " + location);

        System.out.println("Number of Jobs: " + jobs.size());

        if (jobs.isEmpty()) {
            System.out.println("No jobs available.");
        } else {
            System.out.println("\nAvailable Jobs:");

            for (Job job : jobs) {
                job.displayJobDetails();
            }
        }
    }
}