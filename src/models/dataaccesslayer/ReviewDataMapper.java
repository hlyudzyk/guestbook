package models.dataaccesslayer;

import static models.dataaccesslayer.ReviewSerializer.REVIEWS_DIRECTORY;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import models.businesslogic.Review;

/**
 * DataMapper implementation for managing Review objects.
 * This class handles CRUD operations for reviews and interacts with the ReviewSerializer
 * for serialization and deserialization of review objects.
 */
public final class ReviewDataMapper implements DataMapper<Review> {

    /** The list of reviews managed by this DataMapper. */
    private List<Review> reviews = new ArrayList<>();

    /**
     * Constructs a new ReviewDataMapper and loads reviews from the serialized files.
     */
    public ReviewDataMapper() {
        loadReviews();
    }

    /**
     * Gets the list of reviews managed by this DataMapper.
     *
     * @return The list of reviews.
     */
    public List<Review> getReviews() {
        return this.reviews;
    }

    /**
     * Loads reviews from the serialized files in the reviews directory.
     */
    private void loadReviews() {
        File dir = new File(REVIEWS_DIRECTORY);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                try {
                    String filename = file.getName();
                    String fileNameWithoutExtension = filename.substring(0, filename.lastIndexOf('.'));
                    Review review = ReviewSerializer.deserializeReview(fileNameWithoutExtension);
                    this.getReviews().add(review);
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Finds a review by its ID.
     *
     * @param id The ID of the review to find.
     * @return An Optional containing the found review, or empty if not found.
     * @see Optional
     */
    @Override
    public Optional<Review> find(String id) {
        for (final Review review : this.getReviews()) {
            if (Objects.equals(review.getId(), id)) {
                return Optional.of(review);
            }
        }
        return Optional.empty();
    }

    /**
     * Updates a review and serializes the updated review.
     *
     * @param reviewToUpdate The review to update.
     * @throws RuntimeException If the review is not found.
     * @throws IOException      If an IO error occurs during serialization.
     * @see ReviewSerializer#serializeReview(Review)
     */
    @Override
    public void update(Review reviewToUpdate) throws RuntimeException, IOException {
        if (this.getReviews().contains(reviewToUpdate)) {
            final int index = this.getReviews().indexOf(reviewToUpdate);
            this.getReviews().set(index, reviewToUpdate);
            ReviewSerializer.serializeReview(reviewToUpdate);

        } else {
            throw new RuntimeException("Review [" + reviewToUpdate.getId() + "] is not found");
        }
    }

    /**
     * Inserts a new review and serializes it.
     *
     * @param reviewToInsert The review to insert.
     * @throws RuntimeException If the review already exists.
     * @throws IOException      If an IO error occurs during serialization.
     * @see ReviewSerializer#serializeReview(Review)
     */
    @Override
    public void insert(Review reviewToInsert) throws RuntimeException, IOException {
        if (!this.getReviews().contains(reviewToInsert)) {
            this.getReviews().add(reviewToInsert);
            ReviewSerializer.serializeReview(reviewToInsert);

        } else {
            throw new RuntimeException("Review already [" + reviewToInsert.getId() + "] exists");
        }
    }

    /**
     * Deletes a review and removes its serialized file.
     *
     * @param reviewToDelete The review to delete.
     * @throws RuntimeException If the review is not found.
     */
    @Override
    public void delete(Review reviewToDelete) throws RuntimeException {
        if (this.getReviews().contains(reviewToDelete)) {
            this.getReviews().remove(reviewToDelete);

            File reviewFile = new File(REVIEWS_DIRECTORY + File.separator + reviewToDelete.getId() + ".ser");

            try {
                Files.deleteIfExists(reviewFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new RuntimeException("Review [" + reviewToDelete.getId() + "] is not found");
        }
    }
}
