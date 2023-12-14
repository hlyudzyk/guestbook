package models;

import java.security.SecureRandom;
import java.util.Scanner;
import models.businesslogic.Client;
import models.businesslogic.Review;
import models.businesslogic.ReviewType;

public class SupermarketServices {
    public static String generateNDigitCombination(int length) {
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int digit = secureRandom.nextInt(10);
            stringBuilder.append(digit);
        }

        return stringBuilder.toString();
    }

    public static Review writeReview(Client clientFrom, Scanner scanner) {
        System.out.println("Write a context:");
        String context = scanner.nextLine();
        ReviewType reviewType = selectReviewType(scanner);
        return new Review(clientFrom,reviewType,context);
    }
    public static ReviewType selectReviewType(Scanner scanner) {
        ReviewType reviewType = ReviewType.OTHER;

        System.out.println("Select review type\n1-COMPLAIN 2-RECOMMENDATION 3-GRATITUDE 4-COOPERATION 5-OTHER\n");

        int n;
        do {
            System.out.print("Enter the index: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Input error. Enter the number > 0");
                System.out.print("Enter the index: ");
                scanner.next();
            }
            n = scanner.nextInt();
            switch (n){
                case 1:
                    reviewType = ReviewType.COMPLAIN;
                    break;
                case 2:
                    reviewType = ReviewType.RECOMMENDATION;
                    break;
                case 3:
                    reviewType = ReviewType.GRATITUDE;
                case 4:
                    reviewType = ReviewType.COOPERATION;
                case 5:
                    reviewType = ReviewType.OTHER;
                    break;
            }

        } while (n < 1||n>4);

        return reviewType;
    }


}
