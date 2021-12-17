package ru.sfedu.model;

import com.opencsv.bean.CsvBindByName;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import ru.sfedu.Constants;

import java.io.Serializable;
import java.util.Objects;

@Root(name = "Feedback")
public class Feedback implements Serializable {

    @Element(name = Constants.FEEDBACK_FEEDBACK_ID)
    @CsvBindByName(column = Constants.FEEDBACK_FEEDBACK_ID)
    private long feedbackId;

    @Element(name = Constants.FEEDBACK_USER_ID_TO)
    @CsvBindByName(column = Constants.FEEDBACK_USER_ID_TO)
    private long userIdTo;

    @Element(name = Constants.FEEDBACK_USER_ID_FROM)
    @CsvBindByName(column = Constants.FEEDBACK_USER_ID_FROM)
    private long userIdFrom;

    @Element(name = Constants.FEEDBACK_TEXT)
    @CsvBindByName(column = Constants.FEEDBACK_TEXT)
    private String text;

    @Element(name = Constants.FEEDBACK_ESTIMATION)
    @CsvBindByName(column = Constants.FEEDBACK_ESTIMATION)
    private int estimation;

    @Element(name = Constants.FEEDBACK_DATE)
    @CsvBindByName(column = Constants.FEEDBACK_DATE)
    private String date;

    public Feedback(){};

    public Feedback(long feedbackId, long userIdTo, long userIdFrom, String text, int estimation, String date) {
        this.feedbackId = feedbackId;
        this.userIdTo = userIdTo;
        this.userIdFrom = userIdFrom;
        this.text = text;
        this.estimation = estimation;
        this.date = date;
    }

    public long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public long getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(long userIdTo) {
        this.userIdTo = userIdTo;
    }

    public long getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(long userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getEstimation() {
        return estimation;
    }

    public void setEstimation(int estimation) {
        this.estimation = estimation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feedback)) return false;
        Feedback feedback = (Feedback) o;
        return getFeedbackId() == feedback.getFeedbackId() &&
                getUserIdTo() == feedback.getUserIdTo() &&
                getUserIdFrom() == feedback.getUserIdFrom() &&
                getEstimation() == feedback.getEstimation() &&
                getText().equals(feedback.getText()) &&
                getDate().equals(feedback.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFeedbackId(), getUserIdTo(), getUserIdFrom(), getText(), getEstimation(), getDate());
    }
}
