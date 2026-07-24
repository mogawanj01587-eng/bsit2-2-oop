import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Enrollment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Course> courses = new ArrayList<>();
        HashMap<String, ArrayList<String>> enrollments = new HashMap<>();
        String[] validPrograms = {"BSIT", "BSCS"};

        int choice = -1;
        while (choice != 0) {
            printMenu();
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("\n [ERROR!] Invalid input. Please enter a number.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("\n--- REGISTER STUDENT ---");
                    System.out.print("Student ID   : ");
                    String id = sc.nextLine().trim();

                    System.out.print("Full Name    : ");
                    String name = sc.nextLine().trim();

                    System.out.print("Program      : ");
                    String prog = sc.nextLine().trim();

                    boolean isValidProg = false;
                    for (String vp : validPrograms) {
                        if (vp.equalsIgnoreCase(prog)) {
                            isValidProg = true;
                            prog = vp;
                            break;
                        }
                    }

                    if (!isValidProg) {
                        System.out.println("\n [ERROR!] Invalid program! Allowed programs: BSIT, BSCS\n");
                        break;
                    }

                    System.out.print("Year Level   : ");
                    int yr;
                    try {
                        yr = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("\n [ERROR!] Year level must be a number.\n");
                        break;
                    }

                    if (yr < 1 || yr > 4) {
                        System.out.println("\n [ERROR!] Year level must be between 1 and 4.\n");
                        break;
                    }

                    students.add(new Student(id, name, prog, yr));
                    System.out.println("[OK] Student registered successfully!\n");
                    break;

                case 2:
                    System.out.println("\n--- ADD COURSE OFFERING ---");
                    System.out.print("Course Code  : ");
                    String cCode = sc.nextLine().trim();

                    System.out.print("Title        : ");
                    String title = sc.nextLine().trim();

                    System.out.print("Units        : ");
                    int units = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("Capacity     : ");
                    int capacity = Integer.parseInt(sc.nextLine().trim());

                    courses.add(new Course(cCode, title, units, capacity));
                    System.out.println("[OK] Course offering added successfully!\n");
                    break;

                case 3:
                    System.out.println("\n--- ENROLL STUDENT ---");
                    System.out.print("Student ID   : ");
                    String sId = sc.nextLine().trim();

                    System.out.print("Course Code  : ");
                    String crsCode = sc.nextLine().trim();

                    Student student = findStudent(students, sId);
                    Course course = findCourse(courses, crsCode);

                    if (student == null) {
                        System.out.println("\n [ERROR!] Student with ID '" + sId + "' does not exist.\n");
                        break;
                    }

                    if (course == null) {
                        System.out.println("\n [ERROR!] Course with code '" + crsCode + "' does not exist.\n");
                        break;
                    }

                    if (course.isFull()) {
                        System.out.println("\n [ERROR!] Course " + crsCode + " is already full!\n");
                        break;
                    }

                    ArrayList<String> studentEnrolledCourses = enrollments.computeIfAbsent(sId, k -> new ArrayList<>());

                    if (studentEnrolledCourses.contains(crsCode)) {
                        System.out.println("\n [ERROR!] Student is already enrolled in " + crsCode + ".\n");
                        break;
                    }

                    studentEnrolledCourses.add(crsCode);
                    course.addOneEnrollee();
                    System.out.println("[OK] " + student.getFullName() + " enrolled in " + course.getCourseCode() + " (" + course.getTitle() + ").\n");
                    break;

                case 4:
                    System.out.println("\n--- ALL REGISTERED STUDENTS ---");
                    if (students.isEmpty()) {
                        System.out.println("No students yet.\n");
                    } else {
                        for (Student s : students) {
                            System.out.println(s.describe());
                        }
                        System.out.println();
                    }
                    break;

                case 5:
                    System.out.println("\n--- ALL COURSE OFFERINGS ---");
                    if (courses.isEmpty()) {
                        System.out.println("No courses available.\n");
                    } else {
                        for (Course c : courses) {
                            System.out.println(c.getCourseCode() + " | " + c.getTitle() + " | "
                                    + c.getUnits() + " units | " + c.getEnrolledCount() + "/" + c.getCapacity());
                        }
                        System.out.println();
                    }
                    break;

                case 6:
                    System.out.println("\n--- VIEW STUDENT LOAD ---");
                    System.out.print("Student ID   : ");
                    String searchId = sc.nextLine().trim();

                    Student targetStudent = findStudent(students, searchId);
                    if (targetStudent == null) {
                        System.out.println("\n [ERROR!] Student not found.\n");
                        break;
                    }

                    System.out.println("--- STUDENT LOAD: " + targetStudent.getFullName() + " ---");
                    ArrayList<String> enrolledCodes = enrollments.get(searchId);

                    if (enrolledCodes == null || enrolledCodes.isEmpty()) {
                        System.out.println("No courses enrolled yet.");
                        System.out.println("----------------------------------------");
                        System.out.println("Total Units: 0\n");
                    } else {
                        int totalUnits = 0;
                        for (String code : enrolledCodes) {
                            Course c = findCourse(courses, code);
                            if (c != null) {
                                System.out.printf("%-8s %-25s %d units\n", c.getCourseCode(), c.getTitle(), c.getUnits());
                                totalUnits += c.getUnits();
                            }
                        }
                        System.out.println("----------------------------------------");
                        System.out.println("Total Units: " + totalUnits + "\n");
                    }
                    break;

                case 0:
                    System.out.println("Thank you for using the Liceo Enrollment System!");
                    break;

                default:
                    System.out.println("\n [ERROR!] Invalid choice. Please try again.\n");
            }
        }
        sc.close();
    }

    static void printMenu() {
        System.out.println("========================================");
        System.out.println("      LICEO ENROLLMENT SYSTEM (CLI)");
        System.out.println("========================================");
        System.out.println("[1] Register Student");
        System.out.println("[2] Add Course Offering");
        System.out.println("[3] Enroll Student to Course");
        System.out.println("[4] View All Students");
        System.out.println("[5] View All Courses");
        System.out.println("[6] View Student Load (Courses + Total Units)");
        System.out.println("[0] Exit");
        System.out.println("----------------------------------------");
        System.out.print("Enter choice: ");
    }

    static Student findStudent(ArrayList<Student> list, String id) {
        for (Student s : list) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    static Course findCourse(ArrayList<Course> list, String code) {
        for (Course c : list) {
            if (c.getCourseCode().equalsIgnoreCase(code)) {
                return c;
            }
        }
        return null;
    }
}