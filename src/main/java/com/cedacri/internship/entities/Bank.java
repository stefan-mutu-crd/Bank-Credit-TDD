package com.cedacri.internship.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Bank {

    private final int id;

    private final String branch;

    private final String address;
}
