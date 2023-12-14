import java.io.IOException;
import models.dataaccesslayer.DataMapper;
import models.businesslogic.Review;
import models.dataaccesslayer.ReviewDataMapper;
import models.Supermarket;

public class Main {

    public static void main(String[] args){
        DataMapper<Review> dataMapper = new ReviewDataMapper();
        Supermarket supermarket = new Supermarket(dataMapper);
        supermarket.runTest();
    }
}