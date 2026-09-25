package com.cedacri.internship.entities;

import java.util.List;
import java.util.Objects;

public class Bank {

    private final int id;

    private final String branch;

    private final String address;

    private final List<Client> clients;


    Bank(Builder builder) {
        this.id = builder.id;
        this.branch = builder.branch;
        this.address = builder.address;
        this.clients = builder.clients;
    }

    public int getId() {
        return id;
    }

    public String getBranch() {
        return branch;
    }


    public String getAddress() {
        return address;
    }

    public List<Client> getClients() {
        return clients;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return id == bank.id && Objects.equals(branch, bank.branch) && Objects.equals(address, bank.address) && Objects.equals(clients, bank.clients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, branch, address, clients);
    }

    @Override
    public String toString() {
        return "Bank{" +
                "id='" + id + '\'' +
                ", bankBranch='" + branch + '\'' +
                ", address='" + address + '\'' +
                ", clients=" + clients +
                '}';
    }

    public static class Builder {

        private int id;

        private String branch;

        private String address;

        private List<Client> clients;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder branch(String branch) {
            this.branch=branch;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder clients(List<Client> clients) {
            this.clients = clients;
            return this;
        }

        public Bank build() {
            return new Bank(this);
        }
    }
}
