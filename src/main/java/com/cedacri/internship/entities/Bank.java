package com.cedacri.internship.entities;

import java.util.List;
import java.util.Objects;

public class Bank {

    private String bankBranch;

    private String address;

    private List<Client> clients;

    public Bank(String bankBranch, String address, List<Client> clients) {
        this.bankBranch = bankBranch;
        this.address = address;
        this.clients = clients;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(bankBranch, bank.bankBranch) && Objects.equals(address, bank.address) && Objects.equals(clients, bank.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankBranch, address, clients);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "bankBranch='" + bankBranch + '\'' +
                ", address='" + address + '\'' +
                ", clients=" + clients +
                '}';
    }
}
