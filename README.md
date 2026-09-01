# College Placement Management System

## 📌 Project Overview

The **College Placement Management System** is a Java-based application designed to manage the placement activities of a college.

The system provides different modules for managing:

- Students
- Companies
- Jobs
- Job Applications
- Interviews
- Searching
- Student Sorting
- Recent Actions
- System Statistics

The project is developed using **Java** and demonstrates important **Object-Oriented Programming (OOP)** concepts along with **Data Structures and Algorithms (DSA)**.

---

## 🎯 Objectives

The main objectives of this project are:

1. To maintain student placement information.
2. To manage companies participating in campus placements.
3. To manage job opportunities offered by companies.
4. To allow eligible students to apply for jobs.
5. To manage application statuses.
6. To schedule and manage interviews.
7. To demonstrate the practical use of different data structures.
8. To provide searching and sorting functionality.
9. To maintain a record of recent system actions.

---

## 🛠️ Technologies Used

- **Programming Language:** Java
- **GUI:** Java Swing
- **Data Structures:** HashMap, ArrayList, Linked List, Queue, Stack
- **Date Handling:** Java `LocalDate`
- **Development Environment:** Visual Studio Code / GitHub
- **Java Version:** Java 25

---

# 📂 Project Structure

The project contains the following major classes:

| File | Purpose |
|---|---|
| `Main.java` | Main entry point and console-based menu |
| `User.java` | Base class for users |
| `Student.java` | Stores student information |
| `Company.java` | Stores company information |
| `Job.java` | Stores job information |
| `PlacementOfficer.java` | Represents placement officer |
| `Application.java` | Represents a job application |
| `PlacementSystem.java` | Central system managing placement operations |
| `StudentHashMap.java` | Stores students using HashMap |
| `ApplicationLinkedList.java` | Stores applications using Linked List |
| `InterviewQueue.java` | Manages interview queue |
| `ActionStack.java` | Stores recent actions using Stack |
| `StudentSorting.java` | Sorts students according to CGPA |
| `ValidationUtil.java` | Provides validation utilities |
| `AuthenticationService.java` | Handles authentication-related operations |
| `FileManager.java` | Handles file-related operations |
| `LoginGUI.java` | Login interface |
| `DashboardGUI.java` | Main GUI dashboard |
| `StudentGUI.java` | Student management interface |
| `CompanyGUI.java` | Company management interface |
| `JobGUI.java` | Job management interface |
| `ApplicationGUI.java` | Application management interface |
| `InterviewGUI.java` | Interview management interface |
| `SearchGUI.java` | Search interface |
| `SortingGUI.java` | Student sorting interface |
| `ActionsGUI.java` | Recent actions interface |
| `SummaryGUI.java` | System summary interface |

---

# 🧩 Main Features

## 1. Student Management

The system allows the user to:

- Register students
- View all students
- Search students using Student ID
- Delete students
- Store student department
- Store CGPA
- Store skills
- Store phone number

---

## 2. Company Management

The system allows placement officers to:

- Add companies
- Search companies
- View registered companies
- Manage companies participating in placements

---

## 3. Job Management

Companies can have multiple job opportunities.

The system supports:

- Adding jobs
- Searching jobs
- Displaying available jobs
- Associating jobs with companies
- Defining minimum CGPA requirements
- Defining required skills

---

## 4. Student Eligibility

Before a student applies for a job, the system checks eligibility.

A student must satisfy the required conditions such as:

- Minimum CGPA
- Required skill

For example:

```text
Student CGPA = 8.5
Required CGPA = 9.0

Result: Student is not eligible.

5. Application Management

Students can apply for eligible jobs.

Each application contains:

Application ID
Student
Job
Application date
Application status
Interview information

A new application initially receives the status:

Applied
6. Application Status

The application status can be updated according to the placement process.

Examples include:

Applied
Shortlisted
Interview Scheduled
Rejected
Selected
7. Interview Management

The system provides interview management functionality.

It supports:

Adding applications to interview queue
Viewing the next interview
Processing interviews
Scheduling interviews
Cancelling interviews
Displaying interview information

Interview information includes:

Interview date
Interview time
Interview mode
Interviewer
