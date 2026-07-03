import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] studentId = new int[10];
        String[] fullName = new String[10];
        int[] age = new int[10];
        String[] course = new String[10];
        double[] grade = new double[10];
        boolean[] enrolled = new boolean[10];

        int studentCount = 0;
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search by ID");
            System.out.println("4. View Statistics");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:

                    if (studentCount == 10) {
                        System.out.println("Student list is already full.");
                    } else {

                        System.out.print("Enter Student ID: ");
                        studentId[studentCount] = sc.nextInt();
                        sc.nextLine();

                        System.out.print("Enter Full Name: ");
                        fullName[studentCount] = sc.nextLine();

                        do {
                            System.out.print("Enter Age: ");
                            age[studentCount] = sc.nextInt();

                            if (age[studentCount] <= 0) {
                                System.out.println("Age must be positive.");
                            }

                        } while (age[studentCount] <= 0);

                        sc.nextLine();

                        System.out.print("Enter Course: ");
                        course[studentCount] = sc.nextLine();

                        do {
                            System.out.print("Enter Grade: ");
                            grade[studentCount] = sc.nextDouble();

                            if (grade[studentCount] < 0 || grade[studentCount] > 100) {
                                System.out.println("Grade must be between 0 and 100.");
                            }

                        } while (grade[studentCount] < 0 || grade[studentCount] > 100);

                        System.out.print("Is Enrolled? (true/false): ");
                        enrolled[studentCount] = sc.nextBoolean();

                        studentCount++;

                        System.out.println("Student added successfully!");
                    }

                    break;

                case 2:

                    if (studentCount == 0) {
                        System.out.println("No students found.");
                    } else {

                        System.out.println("\n===== STUDENT LIST =====");

                        for (int i = 0; i < studentCount; i++) {

                            System.out.println("----------------------------");
                            System.out.println("Student ID: " + studentId[i]);
                            System.out.println("Name      : " + fullName[i]);
                            System.out.println("Age       : " + age[i]);
                            System.out.println("Course    : " + course[i]);
                            System.out.println("Grade     : " + grade[i]);
                            System.out.println("Enrolled  : " + enrolled[i]);

                            if (grade[i] >= 90) {
                                System.out.println("Standing  : Dean's Lister");
                            } else if (grade[i] >= 75) {
                                System.out.println("Standing  : Passed");
                            } else {
                                System.out.println("Standing  : Failed");
                            }
                        }
                    }

                    break;

                case 3:

                    if (studentCount == 0) {
                        System.out.println("No students available.");
                    } else {

                        System.out.print("Enter Student ID to search: ");
                        int searchID = sc.nextInt();

                        boolean found = false;

                        for (int i = 0; i < studentCount; i++) {

                            if (studentId[i] == searchID) {

                                System.out.println("\nStudent Found!");
                                System.out.println("ID       : " + studentId[i]);
                                System.out.println("Name     : " + fullName[i]);
                                System.out.println("Age      : " + age[i]);
                                System.out.println("Course   : " + course[i]);
                                System.out.println("Grade    : " + grade[i]);
                                System.out.println("Enrolled : " + enrolled[i]);

                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            System.out.println("Student not found.");
                        }
                    }

                    break;

                case 4:

                    if (studentCount == 0) {
                        System.out.println("No students available.");
                    } else {

                        double totalGrades = 0;
                        double highestGrade = grade[0];
                        String topStudent = fullName[0];

                        for (int i = 0; i < studentCount; i++) {

                            totalGrades += grade[i];

                            if (grade[i] > highestGrade) {
                                highestGrade = grade[i];
                                topStudent = fullName[i];
                            }
                        }

                        double averageGrade = totalGrades / studentCount;

                        System.out.println("\n===== STATISTICS =====");
                        System.out.println("Total Students : " + studentCount);
                        System.out.println("Average Grade  : " + averageGrade);
                        System.out.println("Top Student    : " + topStudent);
                        System.out.println("Highest Grade  : " + highestGrade);
                    }

                    break;

                case 5:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}