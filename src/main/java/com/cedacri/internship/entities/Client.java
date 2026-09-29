package com.cedacri.internship.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class Client {

    private final int id;

    private final String fullName;

    private final LocalDate birthDate;

    private final int bankId;
}
