package com.cedacri.internship.entities;

import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

public class Client {

    private String fullName;

    private LocalTime birthDate;

    private List<Loan> creditHistory;

    public Client(String fullName, LocalTime birthDate, List<Loan> creditHistory) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.creditHistory = creditHistory;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalTime birthDate) {
        this.birthDate = birthDate;
    }

    public List<Loan> getCreditHistory() {
        return creditHistory;
    }

    public void setCreditHistory(List<Loan> creditHistory) {
        this.creditHistory = creditHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(fullName, client.fullName) && Objects.equals(birthDate, client.birthDate) && Objects.equals(creditHistory, client.creditHistory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, birthDate, creditHistory);
    }

    @Override
    public String toString() {
        return "Client{" +
                "fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", creditHistory=" + creditHistory +
                '}';
    }
}
