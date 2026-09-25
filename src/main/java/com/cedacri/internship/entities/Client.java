package com.cedacri.internship.entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Client {

    private final int id;

    private final String fullName;

    private final LocalDate birthDate;

    private final List<Loan> creditHistory;

    public Client(Builder builder) {
        this.id = builder.id;
        this.fullName = builder.fullName;
        this.birthDate = builder.birthDate;
        this.creditHistory = builder.creditHistory;
    }

    public int getId() {
        return id;
    }


    public String getFullName() {
        return fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public List<Loan> getCreditHistory() {
        return creditHistory;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id == client.id && Objects.equals(fullName, client.fullName) && Objects.equals(birthDate, client.birthDate) && Objects.equals(creditHistory, client.creditHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, birthDate, creditHistory);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", creditHistory=" + creditHistory +
                '}';
    }

    public static class Builder {
        private int id;

        private String fullName;

        private LocalDate birthDate;

        private List<Loan> creditHistory;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder birtDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder creditHistory(List<Loan> creditHistory) {
            this.creditHistory = creditHistory;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
