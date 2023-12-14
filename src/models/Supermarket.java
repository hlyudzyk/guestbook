package models;

import static models.SupermarketServices.writeReview;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import models.businesslogic.Client;
import models.businesslogic.Review;
import models.dataaccesslayer.DataMapper;

/** Represents the app that simulates supermarket*/
public class Supermarket {
    /**Stores a data context*/
    DataMapper<Review> dataMapper;

    /**
     *Constructs a new Supermarket and initializes data mapper
     * @param dataMapper     The data context.
     * */
    public Supermarket(DataMapper<Review> dataMapper) {
        this.dataMapper = dataMapper;
    }

    public void runTest() {
        Scanner scanner = new Scanner(System.in);
        Client client = new Client("John", "Kennedy","hamas");
        //Review review = new Review(client, ReviewType.GRATITUDE,"Thank you very much!");
        //Review review2 = new Review(client, ReviewType.COMPLAIN,"You are assholes!");

        try {
            dataMapper.insert(writeReview(client,scanner));
            dataMapper.insert(writeReview(client,scanner));
        } catch (IOException e){

        }

        Optional<Review> review1 = dataMapper.find("0");
        Optional<Review> review12 = dataMapper.find("1");
        System.out.println(review1);
        System.out.println(review12);

        scanner.close();
    }


}
