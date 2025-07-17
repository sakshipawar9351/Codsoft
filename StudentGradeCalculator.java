
// Student Grade Calculator


import java.util.*;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);

        System.out.println(" -----: STUDENT GRADE CALCULATOR :-----");
        System.out.println("Enter the number of Subjects : ");
        int SubNum = sc.nextInt();
        int TotalMarks = 0;

        for(int i=0;i<SubNum;i++){
            System.out.println("Enter the marks of Subject "+i+" out of 100 :");
            int marks = sc.nextInt();

            if(marks<0 || marks >100){
                System.err.println("Invalid Marks! Please enter valid marks.");
                continue;
            }
            TotalMarks = TotalMarks + marks;   //addtion of marks
        }
        
        //calculate average precentage

        double Average = TotalMarks/SubNum;

        String grade;
        if (Average>=90){
            grade = "A+";
        } else if(Average >= 80) {
            grade = "A";
        } else if(Average >= 70) {
            grade = "B";
        } else if(Average >= 60) {
            grade = "C";
        } else if(Average >= 50) {
            grade = "D";
        } else {
            grade = "F (Fail)";
        }

        //displaying results
        System.out.println(" -----: RESULT :-----");
        System.out.println("Total Marks : "+TotalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", Average);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}
