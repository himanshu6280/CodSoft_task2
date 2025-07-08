import java.util.Scanner;

// Main class
public class StudentMarksCalculator {

    // Inner class for individual Subject
    static class Subject {
        private String name;
        private int marks;

        public Subject(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        public String getName() {
            return name;
        }

        public int getMarks() {
            return marks;
        }
    }

    // Inner class for Student
    static class Student {
        private String name;
        private Subject[] subjects;

        public Student(String name, int subjectCount) {
            this.name = name;
            this.subjects = new Subject[subjectCount];
        }

        public void inputSubjects(Scanner scanner) {
            for (int i = 0; i < subjects.length; i++) {
                System.out.print("Enter subject name " + (i + 1) + ": ");
                String subjectName = scanner.nextLine();

                int marks;
                while (true) {
                    System.out.print("Enter marks for " + subjectName + " (0-100): ");
                    marks = Integer.parseInt(scanner.nextLine());
                    if (marks >= 0 && marks <= 100) {
                        break;
                    } else {
                        System.out.println("Invalid input. Please enter a number between 0 and 100.");
                    }
                }

                subjects[i] = new Subject(subjectName, marks);
            }
        }

        public int getTotalMarks() {
            int total = 0;
            for (Subject subject : subjects) {
                total += subject.getMarks();
            }
            return total;
        }

        public double getAveragePercentage() {
            return (double) getTotalMarks() / subjects.length;
        }

        public String getGrade() {
            double avg = getAveragePercentage();
            if (avg >= 90) return "A+ (Outstanding)";
            else if (avg >= 80) return "A (Excellent)";
            else if (avg >= 70) return "B (Very Good)";
            else if (avg >= 60) return "C (Good)";
            else if (avg >= 50) return "D (Satisfactory)";
            else return "F (Fail)";
        }

        public void displayReport() {
            System.out.println("\n========== STUDENT REPORT ==========");
            System.out.println("Student Name: " + name);
            System.out.println("------------------------------------");
            System.out.println("Subject\t\tMarks");
            for (Subject subject : subjects) {
                System.out.printf("%-15s %3d\n", subject.getName(), subject.getMarks());
            }
            System.out.println("------------------------------------");
            System.out.println("Total Marks: " + getTotalMarks() + "/" + (subjects.length * 100));
            System.out.printf("Average Percentage: %.2f%%\n", getAveragePercentage());
            System.out.println("Grade: " + getGrade());
            System.out.println("====================================");
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== STUDENT MARKS CALCULATOR ===");

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter number of subjects: ");
        int subjectCount = Integer.parseInt(scanner.nextLine());

        Student student = new Student(name, subjectCount);
        student.inputSubjects(scanner);
        student.displayReport();

        scanner.close();
    }
}
