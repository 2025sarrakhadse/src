# College Placement Management System

A Java-based College Placement Management System developed using
Object-Oriented Programming (OOP) concepts and Data Structures.

The system helps manage students, companies, jobs, applications,
interviews, searching, sorting, and recent system activities.

---

## 1. Project Overview

The College Placement Management System is designed to automate
and simplify the college placement process.

The system provides separate modules for:

- Student Management
- Company Management
- Job Management
- Application Management
- Interview Management
- Searching
- Student Sorting
- Recent Actions
- System Summary

The project is implemented in Java and uses different data
structures according to the requirements of each module.

---

## 2. Technologies Used

- Java
- Java Swing
- Object-Oriented Programming
- Java Collections
- Custom Data Structures
- GitHub
- VS Code

### Java Concepts Used

- Classes and Objects
- Encapsulation
- Inheritance
- Method Overriding
- Constructors
- Arrays
- Exception Handling
- Access Modifiers
- Static Methods
- GUI Programming using Swing

---

## 3. Data Structures Used

Different data structures are used to demonstrate their practical
application in the placement management system.

### HashMap

Students are stored using a custom `StudentHashMap`.

It allows students to be searched using their Student ID.

### ArrayList

Companies and their related information are managed using
`ArrayList`.

### Linked List

Applications are stored using the custom
`ApplicationLinkedList`.

### Queue

Interview-related applications are managed using
`InterviewQueue`.

The queue follows the FIFO (First In First Out) principle.

### Stack

Recent system activities are stored using the custom
`ActionStack`.

The stack follows the LIFO (Last In First Out) principle.

### Bubble Sort

Students can be sorted according to CGPA using Bubble Sort.

The students are arranged in descending order of CGPA.

---

## 4. Main Features

### Student Management

The system allows the user to:

- Register a student
- View all students
- Search students using Student ID
- Delete students
- Store student details such as:
  - Student ID
  - Name
  - Email
  - Phone Number
  - Department
  - CGPA
  - Skills

### Company Management

The system allows the user to:

- Add companies
- Search companies
- View registered companies
- Manage company information

### Job Management

The system allows companies to have job opportunities.

The system supports:

- Adding jobs
- Searching jobs
- Viewing available jobs
- Setting minimum CGPA requirements
- Setting required skills

### Application Management

Students can apply for available jobs.

The system:

- Checks whether the student exists
- Checks whether the job exists
- Checks student eligibility
- Creates an application
- Stores the application in a Linked List

### Interview Management

The system provides an interview queue.

It supports:

- Adding applications to the interview queue
- Viewing the next interview
- Processing interviews
- Scheduling interviews
- Cancelling interviews

### Search

The system supports searching for:

- Students
- Companies
- Jobs
- Applications

### Sorting

Students can be sorted according to CGPA.

The system uses Bubble Sort to arrange students in
descending order of CGPA.

### Recent Actions

The system records important activities such as:

- Student registration
- Company addition
- Job addition
- Application submission
- Interview scheduling
- Interview processing
- Deletion operations

These activities are maintained using a Stack.

### System Summary

The system provides statistics such as:

- Total Students
- Total Companies
- Total Applications
- Interviews Remaining
- Recent Actions

---

## 5. Project Structure

The project is divided into multiple Java classes.

### Core Classes

| File | Purpose |
|------|---------|
| `User.java` | Base class for users |
| `Student.java` | Stores student information |
| `Company.java` | Stores company information |
| `Job.java` | Stores job information |
| `Application.java` | Represents a job application |
| `PlacementOfficer.java` | Represents placement officer functionality |
| `PlacementSystem.java` | Main system logic |

### Data Structure Classes

| File | Data Structure |
|------|----------------|
| `StudentHashMap.java` | HashMap |
| `ApplicationLinkedList.java` | Linked List |
| `InterviewQueue.java` | Queue |
| `ActionStack.java` | Stack |
| `StudentSorting.java` | Bubble Sort |

### GUI Classes

| File | Purpose |
|------|---------|
| `LoginGUI.java` | Login screen |
| `DashboardGUI.java` | Main dashboard |
| `StudentGUI.java` | Student management |
| `CompanyGUI.java` | Company management |
| `JobGUI.java` | Job management |
| `ApplicationGUI.java` | Application management |
| `InterviewGUI.java` | Interview management |
| `SearchGUI.java` | Search functionality |
| `SortingGUI.java` | Student sorting |
| `ActionsGUI.java` | Recent actions |
| `SummaryGUI.java` | System summary |

### Supporting Classes

| File | Purpose |
|------|---------|
| `AuthenticationService.java` | Authentication-related operations |
| `ValidationUtil.java` | Input validation |
| `FileManager.java` | File-related operations |
| `Main.java` | Console-based application entry point |

---
