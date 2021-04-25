package Controller.Reader;

import Model.Review;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ItemReviewController {

    @FXML
    private Label labelReviewText;

    @FXML
    private Label labelReaderName;

    private Review review;

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
        labelReaderName.setText(review.getReader().getReadersCard().getReaderName());
        labelReviewText.setText(review.getReviewText());
    }
}
