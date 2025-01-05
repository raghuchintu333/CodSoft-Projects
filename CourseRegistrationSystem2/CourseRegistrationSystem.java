import java.util.*;

class Course 
{
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule)
     {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    public String getCourseCode() 
    {
        return courseCode;
    }

    public String getTitle() 
    {
        return title;
    }

    public String getDescription()
     {
        return description;
    }

    public String getSchedule()
     {
        return schedule;
    }

    public int getAvailableSlots()
     {
        return capacity - registeredStudents;
    }

    public boolean registerStudent() 
    {
        if (registeredStudents < capacity)
         {
            registeredStudents++;
            return true;
        }
        return false;
    }

    public boolean deregisterStudent()
     {
        if (registeredStudents > 0)
         {
            registeredStudents--;
            return true;
        }
        return false;
    }
}

class Student
 {
    private String studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(String studentId, String name)
     {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() 
    {
        return studentId;
    }

    public String getName()
     {
        return name;
    }

    public List<Course> getRegisteredCourses()
     {
        return registeredCourses;
    }

    public boolean registerCourse(Course course) 
    {
        if (!registeredCourses.contains(course) && course.registerStudent())
         {
            registeredCourses.add(course);
            return true;
        }
        return false;
    }

    public boolean dropCourse(Course course) 
    {
        if (registeredCourses.contains(course)) 
        {
            registeredCourses.remove(course);
            course.deregisterStudent();
            return true;
        }
        return false;
    }
}

public class CourseRegistrationSystem 
{
    private Map<String, Course> courses;
    private Map<String, Student> students;

    public CourseRegistrationSystem() 
    {
        courses = new HashMap<>();
        students = new HashMap<>();
    }

    public void addCourse(Course course)
     {
        courses.put(course.getCourseCode(), course);
    }

    public void addStudent(Student student)
     {
        students.put(student.getStudentId(), student);
    }

    public void listCourses() 
    {
        System.out.println("\nAvailable Courses:");
        for (Course course : courses.values()) 
        {
            System.out.println("Code: " + course.getCourseCode() + ", Title: " + course.getTitle() +
                    ", Description: " + course.getDescription() + ", Slots: " + course.getAvailableSlots() +
                    ", Schedule: " + course.getSchedule());
        }
    }

    public void registerStudentForCourse(String studentId, String courseCode)
     {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && course != null) 
        {
            if (student.registerCourse(course))
             {
                System.out.println(student.getName() + " successfully registered for " + course.getTitle());
            } else 
            {
                System.out.println("Registration failed. Course might be full or already registered.");
            }
        } else
         {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public void removeStudentFromCourse(String studentId, String courseCode)
     {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if (student != null && course != null) 
        {
            if (student.dropCourse(course)) 
            {
                System.out.println(student.getName() + " successfully dropped " + course.getTitle());
            } else
             {
                System.out.println("Failed to drop the course. The student is not registered for this course.");
            }
        } else 
        {
            System.out.println("Invalid student ID or course code.");
        }
    }

    public static void main(String[] args)
     {
        Scanner scanner = new Scanner(System.in);
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        system.addCourse(new Course("CS101", "Introduction to Programming", "Learn the basics of programming", 30, "MWF 9-10AM"));
        system.addCourse(new Course("MTH101", "Calculus I", "Introduction to Calculus", 40, "TTh 11-12:30PM"));
        system.addCourse(new Course("PHY101", "Physics I", "Fundamentals of Physics", 35, "MWF 2-3PM"));

        system.addStudent(new Student("S001", "Alice"));
        system.addStudent(new Student("S002", "Bob"));

        int choice;
        do {
            System.out.println("\nStudent Course Registration System");
            System.out.println("1. List Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    system.listCourses();
                    break;
                case 2:
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.next();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.next();
                    system.registerStudentForCourse(studentId, courseCode);
                    break;
                case 3:
                    System.out.print("Enter Student ID: ");
                    studentId = scanner.next();
                    System.out.print("Enter Course Code: ");
                    courseCode = scanner.next();
                    system.removeStudentFromCourse(studentId, courseCode);
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);

        scanner.close();
    }
}