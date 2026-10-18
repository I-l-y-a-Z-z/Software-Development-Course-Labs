package problem2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int questionCount;
            do {
                System.out.print("How many questions are in the quiz? ");
                questionCount = scanner.nextInt();
                if (questionCount <= 0) {
                    System.out.println("The number of questions must be positive.");
                }
            } while (questionCount <= 0);

            int[] key = new int[questionCount];
            System.out.println("Enter the correct answers (" + questionCount + " integers):");
            for (int i = 0; i < questionCount; i++) {
                key[i] = scanner.nextInt();
            }

            String gradeAnother;
            do {
                int correct = 0;
                System.out.println("Enter the quiz answers (" + questionCount + " integers):");
                for (int i = 0; i < questionCount; i++) {
                    int answer = scanner.nextInt();
                    if (answer == key[i]) {
                        correct++;
                    }
                }

                double percentCorrect = correct * 100.0 / questionCount;
                System.out.println("Number correct: " + correct + " out of " + questionCount);
                String out = String.format("Percent Correct : %.2f%%", percentCorrect);
                System.out.println(out);
                System.out.print("Grade another quiz? (y/n): ");
                gradeAnother = scanner.next();
            } while (gradeAnother.equals("y"));
        }
    }
}
