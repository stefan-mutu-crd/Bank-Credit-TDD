package com.cedacri.internship.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Loan {

    private final int id;

    private final double initialSum;

    private final double refunded;

    private final String purpose;

    private final LocalDate dateOfIssue;

    private final LocalDate deadline;

    private final int percentage;

    Loan(Builder builder) {
        this.id = builder.id;
        this.initialSum = builder.initialSum;
        this.refunded = builder.refunded;
        this.purpose = builder.purpose;
        this.dateOfIssue = builder.dateOfIssue;
        this.deadline = builder.deadline;
        this.percentage = builder.percentage;
    }

    public int getId() {
        return id;
    }


    public double getInitialSum() {
        return initialSum;
    }


    public double getRefunded() {
        return refunded;
    }


    public String getPurpose() {
        return purpose;
    }

    public int getPercentage() {
        return percentage;
    }


    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }


    public LocalDate getDeadline() {
        return deadline;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id
                && Double.compare(initialSum, loan.initialSum) == 0
                && Double.compare(refunded, loan.refunded) == 0
                && percentage == loan.percentage
                && Objects.equals(purpose, loan.purpose)
                && Objects.equals(dateOfIssue, loan.dateOfIssue)
                && Objects.equals(deadline, loan.deadline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                initialSum,
                refunded,
                purpose,
                dateOfIssue,
                deadline,
                percentage);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "id=" + id +
                ", initialSum=" + initialSum +
                ", refunded=" + refunded +
                ", purpose='" + purpose + '\'' +
                ", dateOfIssue=" + dateOfIssue +
                ", deadline=" + deadline +
                ", percentage=" + percentage +
                '}';
    }

    public static class Builder {
        private int id;

        private double initialSum;

        private double refunded;

        private String purpose;

        private LocalDate dateOfIssue;

        private LocalDate deadline;

        private int percentage;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder initialSum(double initialSum) {
            this.initialSum = initialSum;
            return this;
        }

        public Builder refunded(double refunded) {
            this.refunded = refunded;
            return this;
        }

        public Builder purpose(String purpose) {
            this.purpose = purpose;
            return this;
        }

        public Builder dateOfIssue(LocalDate dateOfIssue) {
            this.dateOfIssue = dateOfIssue;
            return this;
        }

        public Builder deadline(LocalDate deadline) {
            this.deadline = deadline;
            return this;
        }

        public Builder percentage(int percentage) {
            this.percentage = percentage;
            return this;
        }

        public Loan build() {
            return new Loan(this);
        }
    }
}

