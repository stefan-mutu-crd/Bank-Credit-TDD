package com.cedacri.internship.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {

    private double initial;

    private double refunded;

    private String purpose;

    private LocalDate dateOfIssue;

    private LocalDate deadline;

    private int percentage;

    public Loan(double initial,
                double refunded,
                String purpose,
                LocalDate dateOfIssue,
                LocalDate deadline,
                int percentage) {
        this.initial = initial;
        this.refunded = refunded;
        this.purpose = purpose;
        this.dateOfIssue = dateOfIssue;
        this.deadline = deadline;
        this.percentage = percentage;
    }

    public double getInitial() {
        return initial;
    }

    public void setInitial(double initial) {
        this.initial = initial;
    }

    public double getRefunded() {
        return refunded;
    }

    public void setRefunded(double refunded) {
        this.refunded = refunded;
    }

    public String getPurpose() {
        return purpose;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Double.compare(initial, loan.initial) == 0
                && Double.compare(refunded, loan.refunded) == 0
                && percentage == loan.percentage
                && Objects.equals(purpose, loan.purpose)
                && Objects.equals(dateOfIssue, loan.dateOfIssue)
                && Objects.equals(deadline, loan.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(initial, refunded, purpose, dateOfIssue, deadline, percentage);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "initial=" + initial +
                ", refunded=" + refunded +
                ", purpose='" + purpose + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", deadline=" + deadline +
                ", percentage=" + percentage +
                '}';
    }
}

