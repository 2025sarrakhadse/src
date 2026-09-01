import java.util.ArrayList;

public class Company {

    // Company data members
    private int companyId;
    private String companyName;
    private String location;
    private String hrName;
    private String hrEmail;
    private String contactNumber;

    // ArrayList to store jobs offered by the company
    private ArrayList<Job> jobs;

    // Constructor
    public Company(int companyId, String companyName, String location,
                   String hrName, String hrEmail, String contactNumber) {

        this.companyId = companyId;
        this.companyName = companyName;
        this.location = location;
        this.hrName = hrName;
        this.hrEmail = hrEmail;
        this.contactNumber = contactNumber;

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

    // Getter for HR name
    public String getHrName() {
        return hrName;
    }

    // Getter for HR email
    public String getHrEmail() {
        return hrEmail;
    }

    // Getter for contact number
    public String getContactNumber() {
        return contactNumber;
    }

    // Getter for jobs
    public ArrayList<Job> getJobs() {
        return jobs;
    }

    // Setter for company name
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    // Setter for location
    public void setLocation(String location) {
        this.location = location;
    }

    // Setter for HR name
    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    // Setter for HR email
    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }

    // Setter for contact number
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // Add a job to the company
    public void addJob(Job job) {

        if (job != null) {
            jobs.add(job);
            System.out.println("Job added successfully to " + companyName);
        }
    }

    // Remove a job from the company
    public boolean removeJob(int jobId) {

        for (int i = 0; i < jobs.size(); i++) {

            if (jobs.get(i).getJobId() == jobId) {
                jobs.remove(i);
                return true;
            }
        }

        return false;
    }

    // Find a job using job ID
    public Job findJob(int jobId) {

        for (Job job : jobs) {

            if (job.getJobId() == jobId) {
                return job;
            }
        }

        return null;
    }

    // Display company details
    public void displayCompanyDetails() {

        System.out.println("\n===== Company Details =====");

        System.out.println("Company ID: " + companyId);
        System.out.println("Company Name: " + companyName);
        System.out.println("Location: " + location);
        System.out.println("HR Name: " + hrName);
        System.out.println("HR Email: " + hrEmail);
        System.out.println("Contact Number: " + contactNumber);
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
