package models.dataaccesslayer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import models.businesslogic.Review;

public class ReviewSerializer {
    public static final String REVIEWS_DIRECTORY = "Reviews";
    public static void serializeReview(Review review) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(
            REVIEWS_DIRECTORY + File.separator + review.getId() + ".ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(review);
        objectOutputStream.close();
    }

    public static Review deserializeReview(String id) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(REVIEWS_DIRECTORY +File.separator + id + ".ser");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Review review = (Review) objectInputStream.readObject();
        objectInputStream.close();
        return review;
    }
}
