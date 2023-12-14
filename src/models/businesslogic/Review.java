package models.businesslogic;

import java.io.Serializable;
import java.time.LocalDateTime;
import models.SupermarketServices;

/**
 * Represents a review entity with details such as ID, author, review type, creation time, modification time,
 * and review context.
 */
public class Review implements Serializable {
    private static final int ID_LENGTH=10;
    /**id of the review */
    private final String id;
    /**stores who wrote the review*/
    private final Client author;
    /**type of the review
     * @see ReviewType
     */
    private final ReviewType reviewType;
    /**when the review was created*/
    private final LocalDateTime createdAt;
    /**last time when review was changed*/
    private LocalDateTime changedAt;
    /**context of the review*/
    private String context;

    /**
     *Constructs a new Review and initializes fields
     * @param author     The author of the review.
     * @param reviewType The type of the review.
     * @param context    The content of the review.
     * */
    public Review(Client author, ReviewType reviewType, String context) {
        this.id = SupermarketServices.generateNDigitCombination(ID_LENGTH);
        this.author = author;
        this.reviewType = reviewType;
        this.createdAt = LocalDateTime.now();
        this.changedAt = this.createdAt;
        this.context = context;
    }

    /**
     * Sets the context of the review and updates the modification time.
     *
     * @param context The new context of the review.
     */
    public void setContext(String context) {
        this.context = context;
        this.changedAt = LocalDateTime.now();
    }

    /**
     * Gets the ID of the review.
     *
     * @return The ID of the review.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the author of the review.
     *
     * @return The author of the review.
     */
    public Client getAuthor() {
        return author;
    }

    /**
     * Gets the type of the review.
     *
     * @return The type of the review.
     */
    public ReviewType getReviewType() {
        return reviewType;
    }

    /**
     * Gets the creation time of the review.
     *
     * @return The creation time of the review.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Gets the modification time of the review.
     *
     * @return The modification time of the review.
     */
    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    /**
     * Gets the context of the review.
     *
     * @return The context of the review.
     */
    public String getContext() {
        return context;
    }

    /**
     * Returns a string representation of the review.
     *
     * @return A string representation of the review.
     */
    @Override
    public String toString() {
        return "Review{" +
            "id='" + id + '\'' +
            ", author=" + author +
            ", reviewType=" + reviewType +
            ", createdAt=" + createdAt +
            ", changedAt=" + changedAt +
            ", context='" + context + '\'' +
            '}';
    }
}
