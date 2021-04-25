package Controller.Reader;

import Model.Review;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemReviewController {

    @FXML
    private Label reviewTextLabel;

    @FXML
    private Label readerNameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label starsNumLabel;

    private Review review;

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        //set labels to show review content
        this.review = review;
        readerNameLabel.setText(review.getReader().getReadersCard().getReaderName());
        reviewTextLabel.setText(review.getReviewText());
        dateLabel.setText(review.getDate());
        starsNumLabel.setText(String.valueOf(review.getStarsNum()));
    }
}
