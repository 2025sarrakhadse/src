public class Job {

    // Job data members
    private int jobId;
    private String jobTitle;
    private double salary;
    private double minimumCGPA;
    private String requiredSkill;
    private String location;
    private String jobType;
    private String applicationDeadline;
    private Company company;

    // Constructor
    public Job(int jobId, String jobTitle, double salary,
               double minimumCGPA, String requiredSkill,
               String location, String jobType,
               String applicationDeadline, Company company) {

        this.jobId = jobId;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.minimumCGPA = minimumCGPA;
        this.requiredSkill = requiredSkill;
        this.location = location;
        this.jobType = jobType;
        this.applicationDeadline = applicationDeadline;
        this.company = company;
    }

    // Getter for job ID
    public int getJobId() {
        return jobId;
    }

    // Getter for job title
    public String getJobTitle() {
        return jobTitle;
    }

    // Getter for salary
    public double getSalary() {
        return salary;
    }

    public double getSalaryLPA() {
        return salary;
    }

    // Getter for minimum CGPA
    public double getMinimumCGPA() {
        return minimumCGPA;
    }

    // Getter for required skill
    public String getRequiredSkill() {
        return requiredSkill;
    }

    // Getter for location
    public String getLocation() {
        return location;
    }

    // Getter for job type
    public String getJobType() {
        return jobType;
    }

    // Getter for application deadline
    public String getApplicationDeadline() {
        return applicationDeadline;
    }

    // Getter for company
    public Company getCompany() {
        return company;
    }

    // Setters
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setSalary(double salary) {

        if (salary < 0) {
            throw new IllegalArgumentException(
                    "Salary cannot be negative."
            );
        }

        this.salary = salary;
    }

    public void setMinimumCGPA(double minimumCGPA) {

        if (minimumCGPA < 0 || minimumCGPA > 10) {
            throw new IllegalArgumentException(
                    "Minimum CGPA must be between 0 and 10."
            );
        }

        this.minimumCGPA = minimumCGPA;
    }

    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public void setApplicationDeadline(String applicationDeadline) {
        this.applicationDeadline = applicationDeadline;
    }

    // Check whether a student is eligible for this job
    public boolean isStudentEligible(Student student) {

        if (student == null) {
            return false;
        }

        double studentCgpa = student.getCgpa();
        double reqCgpa = getMinimumCGPA();

        return studentCgpa >= reqCgpa && student.hasSkill(requiredSkill);
    }

    // Get detailed eligibility reason message for GUI feedback
    public String getEligibilityReason(Student student) {

        if (student == null) {
            return "Ineligible: Student record not found.";
        }

        double studentCgpa = student.getCgpa();
        double reqCgpa = getMinimumCGPA();

        if (studentCgpa < reqCgpa) {
            return String.format("Ineligible: Student CGPA (%.2f) < Required CGPA (%.2f)", studentCgpa, reqCgpa);
        }

        if (!student.hasSkill(requiredSkill)) {
            String req = (requiredSkill == null || requiredSkill.trim().isEmpty()) ? "None" : requiredSkill.trim();
            return "Ineligible: Missing required skill '" + req + "'";
        }

        String req = (requiredSkill == null || requiredSkill.trim().isEmpty()) ? "None" : requiredSkill.trim();
        return String.format("ELIGIBLE! CGPA (%.2f >= %.2f) & Skill '%s' matched", studentCgpa, reqCgpa, req);
    }

    // Display job details
    public void displayJobDetails() {

        System.out.println("\n----------------------------");

        System.out.println("Job ID: " + jobId);
        System.out.println("Job Title: " + jobTitle);

        System.out.println(
                "Company: " +
                (company != null
                        ? company.getCompanyName()
                        : "Not specified")
        );

        System.out.println("Salary: " + salary + " LPA");
        System.out.println("Minimum CGPA: " + minimumCGPA);
        System.out.println("Required Skill: " + requiredSkill);
        System.out.println("Location: " + location);
        System.out.println("Job Type: " + jobType);
        System.out.println(
                "Application Deadline: " + applicationDeadline
        );

        System.out.println("----------------------------");
    }
}
