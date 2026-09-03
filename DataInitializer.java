/**
 * DataInitializer - Auto-populates the Placement Management System with realistic
 * mock seed data across all modules and custom data structures:
 * - StudentHashMap (15 Diverse Student records)
 * - ArrayList<Company> (8 Recruiters)
 * - ArrayList<Job> (10 Job postings)
 * - ApplicationLinkedList (10 Applications)
 * - InterviewQueue (5 Scheduled Interviews)
 * - ActionStack (6 Pre-pushed audit logs)
 */
public class DataInitializer {

    public static void initializeSeedData(PlacementSystem system) {
        if (system == null) {
            return;
        }

        // Only seed if system total students is less than 12
        if (system.getTotalStudents() >= 12) {
            System.out.println("Existing student records found. Skipping initial seeding.");
            return;
        }

        System.out.println("Initializing Placement System with realistic seed dataset...");

        // =========================================================================
        // 1. STUDENTS SEED DATA (15 Diverse Students) -> StudentHashMap & students.txt
        // =========================================================================
        Student[] seedStudents = new Student[]{
                new Student(101, "Aarav Sharma", "aarav.sharma@example.com", "pass1234", "Computer Engineering", 9.5, new String[]{"Java", "Data Structures", "Python"}, "9876543201"),
                new Student(102, "Priya Patel", "priya.patel@example.com", "pass1234", "Information Technology", 8.8, new String[]{"React", "JavaScript", "SQL"}, "9876543202"),
                new Student(103, "Rohan Verma", "rohan.verma@example.com", "pass1234", "AI & Data Science", 9.2, new String[]{"Python", "Machine Learning", "SQL"}, "9876543203"),
                new Student(104, "Ananya Iyer", "ananya.iyer@example.com", "pass1234", "Computer Engineering", 9.8, new String[]{"Java", "Spring Boot", "Microservices"}, "9876543204"),
                new Student(105, "Rahul Deshmukh", "rahul.deshmukh@example.com", "pass1234", "Electronics & Telecommunication", 7.2, new String[]{"C++", "Embedded Systems", "Python"}, "9876543205"),
                new Student(106, "Sneha Kulkarni", "sneha.kulkarni@example.com", "pass1234", "Information Technology", 8.4, new String[]{"SQL", "Database", "Java"}, "9876543206"),
                new Student(107, "Tanvi Mehta", "tanvi.mehta@example.com", "pass1234", "AI & Data Science", 8.9, new String[]{"Python", "Data Science", "AWS", "React"}, "9876543207"),
                new Student(108, "Vikramaditya Rao", "vikram.rao@example.com", "pass1234", "Computer Engineering", 6.8, new String[]{"C++", "Data Structures"}, "9876543208"),
                new Student(109, "Pooja Hegde", "pooja.hegde@example.com", "pass1234", "Electronics & Telecommunication", 7.9, new String[]{"Python", "IoT", "C++"}, "9876543209"),
                new Student(110, "Siddharth Nair", "siddharth.nair@example.com", "pass1234", "Information Technology", 9.0, new String[]{"Java", "AWS", "Kubernetes"}, "9876543210"),
                new Student(111, "Ishita Joshi", "ishita.joshi@example.com", "pass1234", "Computer Engineering", 8.1, new String[]{"React", "Node.js", "JavaScript"}, "9876543211"),
                new Student(112, "Aditya Malhotra", "aditya.m@example.com", "pass1234", "AI & Data Science", 7.6, new String[]{"Python", "Deep Learning"}, "9876543212"),
                new Student(113, "Neha Gupta", "neha.gupta@example.com", "pass1234", "Computer Engineering", 8.6, new String[]{"Java", "SQL", "Selenium"}, "9876543213"),
                new Student(114, "Devansh Kulkarni", "devansh.k@example.com", "pass1234", "Information Technology", 6.5, new String[]{"HTML", "CSS", "JavaScript"}, "9876543214"),
                new Student(115, "Riya Kapoor", "riya.kapoor@example.com", "pass1234", "Electronics & Telecommunication", 9.1, new String[]{"C++", "System Design", "Java"}, "9876543215")
        };

        for (Student s : seedStudents) {
            system.registerStudent(s);
        }

        // =========================================================================
        // 2. COMPANIES SEED DATA (8 Recruiters) -> ArrayList<Company>
        // =========================================================================
        Company c1 = new Company(201, "TechCorp Solutions", "Bengaluru", "Rajesh Kumar", "rajesh@techcorp.com", "9812345601");
        Company c2 = new Company(202, "CloudScale Inc", "Pune", "Meera Singhania", "meera@cloudscale.com", "9812345602");
        Company c3 = new Company(203, "CyberEdge Systems", "Mumbai", "Vikram Shroff", "vikram@cyberedge.com", "9812345603");
        Company c4 = new Company(204, "InnovateX", "Hyderabad", "Sunita Rao", "sunita@innovatex.io", "9812345604");
        Company c5 = new Company(205, "DataDynamics", "Bengaluru", "Amitabh Bhatt", "amit@datadynamics.ai", "9812345605");
        Company c6 = new Company(206, "Apex Financial", "Mumbai", "Kavita Nambiar", "kavita@apexfin.com", "9812345606");
        Company c7 = new Company(207, "NextGen AI", "Pune", "Sameer Roy", "sameer@nextgenai.com", "9812345607");
        Company c8 = new Company(208, "Infosys Systems", "Bengaluru", "Nidhi Sharma", "nidhi@infosys.com", "9812345608");

        system.addCompany(c1);
        system.addCompany(c2);
        system.addCompany(c3);
        system.addCompany(c4);
        system.addCompany(c5);
        system.addCompany(c6);
        system.addCompany(c7);
        system.addCompany(c8);

        // =========================================================================
        // 3. JOBS SEED DATA (10 Job Openings) -> ArrayList<Job>
        // =========================================================================
        Job j1 = new Job(301, "Software Development Engineer (SDE-1)", 14.5, 8.0, "Java", "Bengaluru", "Full-time", "2026-10-15", c1);
        Job j2 = new Job(302, "Full Stack Developer", 12.0, 7.5, "React", "Bengaluru", "Full-time", "2026-10-20", c1);
        Job j3 = new Job(303, "Cloud DevOps Engineer", 15.0, 8.0, "AWS", "Pune", "Full-time", "2026-11-01", c2);
        Job j4 = new Job(304, "Cybersecurity Analyst", 11.5, 7.0, "C++", "Mumbai", "Full-time", "2026-10-30", c3);
        Job j5 = new Job(305, "Data Analyst", 9.5, 7.5, "Python", "Bengaluru", "Full-time", "2026-10-10", c5);
        Job j6 = new Job(306, "AI Research Intern", 8.0, 8.5, "Machine Learning", "Pune", "Internship", "2026-11-15", c7);
        Job j7 = new Job(307, "FinTech Backend Developer", 16.5, 8.5, "Java", "Mumbai", "Full-time", "2026-12-01", c6);
        Job j8 = new Job(308, "QA Automation Engineer", 8.5, 6.5, "SQL", "Hyderabad", "Full-time", "2026-10-25", c4);
        Job j9 = new Job(309, "Associate Systems Engineer", 6.5, 6.5, "Data Structures", "Bengaluru", "Full-time", "2026-12-15", c8);
        Job j10 = new Job(310, "Data Science Associate", 13.0, 8.0, "Python", "Bengaluru", "Full-time", "2026-11-20", c5);

        system.addJob(201, j1);
        system.addJob(201, j2);
        system.addJob(202, j3);
        system.addJob(203, j4);
        system.addJob(205, j5);
        system.addJob(207, j6);
        system.addJob(206, j7);
        system.addJob(204, j8);
        system.addJob(208, j9);
        system.addJob(205, j10);

        // =========================================================================
        // 4. APPLICATIONS SEED DATA (10 Applications) -> ApplicationLinkedList
        // =========================================================================
        Application app1 = system.applyForJob(101, 301); // Aarav Sharma -> SDE-1
        Application app2 = system.applyForJob(104, 307); // Ananya Iyer -> FinTech Backend
        Application app3 = system.applyForJob(103, 305); // Rohan Verma -> Data Analyst
        Application app4 = system.applyForJob(107, 303); // Tanvi Mehta -> Cloud DevOps
        Application app5 = system.applyForJob(102, 302); // Priya Patel -> Full Stack
        Application app6 = system.applyForJob(110, 303); // Siddharth Nair -> Cloud DevOps
        Application app7 = system.applyForJob(106, 308); // Sneha Kulkarni -> QA Automation
        Application app8 = system.applyForJob(113, 308); // Neha Gupta -> QA Automation
        Application app9 = system.applyForJob(115, 301); // Riya Kapoor -> SDE-1
        Application app10 = system.applyForJob(105, 304); // Rahul Deshmukh -> Cybersecurity Analyst

        // Update specific application statuses
        if (app2 != null) app2.setStatus("Selected");
        if (app5 != null) app5.setStatus("Shortlisted");
        if (app7 != null) app7.setStatus("Selected");
        if (app8 != null) app8.setStatus("Shortlisted");
        if (app10 != null) app10.setStatus("Rejected");

        // =========================================================================
        // 5. INTERVIEWS SEED DATA (5 Scheduled Interviews) -> InterviewQueue (FIFO)
        // =========================================================================
        if (app1 != null) {
            system.scheduleInterview(app1.getApplicationId(), "2026-09-12", "10:00 AM", "Online (Google Meet)", "TechCorp Tech Lead");
            system.addToInterviewQueue(app1.getApplicationId());
        }
        if (app3 != null) {
            system.scheduleInterview(app3.getApplicationId(), "2026-09-12", "02:00 PM", "Online (Zoom)", "DataDynamics Manager");
            system.addToInterviewQueue(app3.getApplicationId());
        }
        if (app4 != null) {
            system.scheduleInterview(app4.getApplicationId(), "2026-09-13", "11:30 AM", "Offline (Campus)", "CloudScale Architect");
            system.addToInterviewQueue(app4.getApplicationId());
        }
        if (app6 != null) {
            system.scheduleInterview(app6.getApplicationId(), "2026-09-13", "03:30 PM", "Online (Google Meet)", "CloudScale HR Team");
            system.addToInterviewQueue(app6.getApplicationId());
        }
        if (app9 != null) {
            system.scheduleInterview(app9.getApplicationId(), "2026-09-14", "10:00 AM", "Offline (Campus)", "TechCorp VP Engineering");
            system.addToInterviewQueue(app9.getApplicationId());
        }

        // =========================================================================
        // 6. ACTION STACK SEED LOGS -> ActionStack (LIFO)
        // =========================================================================
        system.addAction("System initialized with mock seed dataset");
        system.addAction("Registered 15 candidates into StudentHashMap");
        system.addAction("Added 8 corporate partners to ArrayList");
        system.addAction("Posted 10 job openings across companies");
        system.addAction("Created 10 applications in ApplicationLinkedList");
        system.addAction("Enqueued 5 interview slots into InterviewQueue");

        // Save all seeded entities to text files
        system.saveStudentsToFile();
        system.saveCompaniesToFile();
        system.saveJobsToFile();
        system.saveApplicationsToFile();
        system.saveInterviewsToFile();

        System.out.println("Seed dataset successfully populated across all modules and saved to text files!");
    }
}
