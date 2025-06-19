import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String id;
    private String name;
    private double marks;

    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

class StudentRecordSystem {
    private Map<String, Student> students;

    public StudentRecordSystem() {
        students = new HashMap<>();
    }

    public void addStudent(String id, String name, double marks) {
        try {
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }
            if (students.containsKey(id)) {
                System.out.println("Student ID already exists!");
            } else {
                students.put(id, new Student(id, name, marks));
                System.out.println("Student added successfully.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void updateStudent(String id, String name, double marks) {
        try {
            if (marks < 0 || marks > 100) {
                throw new IllegalArgumentException("Marks must be between 0 and 100.");
            }
            Student student = students.get(id);
            if (student == null) {
                System.out.println("Student ID not found!");
            } else {
                student.setName(name);
                student.setMarks(marks);
                System.out.println("Student updated successfully.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void deleteStudent(String id) {
        if (students.remove(id) != null) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student ID not found!");
        }
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("Student Records:");
            for (Student student : students.values()) {
                System.out.println(student);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentRecordSystem system = new StudentRecordSystem();

        while (true) {
            System.out.println("\nStudent Record System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Students");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Marks: ");
                    double marks;
                    try {
                        marks = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid marks. Please enter a number.");
                        break;
                    }
                    system.addStudent(id, name, marks);
                    break;

                case 2:
                    System.out.print("Enter Student ID: ");
                    id = scanner.nextLine();
                    System.out.print("Enter New Name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter New Marks: ");
                    try {
                        marks = Double.parseDouble(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid marks. Please enter a number.");
                        break;
                    }
                    system.updateStudent(id, name, marks);
                    break;

                case 3:
                    System.out.print("Enter Student ID: ");
                    id = scanner.nextLine();
                    system.deleteStudent(id);
                    break;

                case 4:
                    system.displayStudents();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}