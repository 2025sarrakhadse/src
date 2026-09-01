import java.util.ArrayList;

public class Company {

    // Company data members
    private int companyId;
    private String companyName;
    private String location;
    private String hrName;
    private String hrEmail;
    private String contactNumber;

    // ArrayList stores jobs offered by the company
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

        jobs = new ArrayList<>();
    }

    // Getter for company ID
    public int getCompanyId() {
        return companyId;
    }

    // Getter for company name
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

    // Setters
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public void setHrEmail(String hrEmail) {
        this.hrEmail = hrEmail;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    // Add a job to the company
    public boolean addJob(Job job) {

        if (job == null) {
            return false;
        }

        // Prevent duplicate Job IDs
        if (findJob(job.getJobId()) != null) {

            System.out.println(
                    "Error: Job ID already exists in this company."
            );

            return false;
        }

        jobs.add(job);

        System.out.println(
                "Job added successfully to " + companyName
        );

        return true;
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

    // Find a job using Job ID
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
