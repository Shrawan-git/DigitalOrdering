package com.digitalorder.np.model;

public class Rating {
    String rate, feedback;

    public Rating(String rate, String feedback) {
        this.rate = rate;
        this.feedback = feedback;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
