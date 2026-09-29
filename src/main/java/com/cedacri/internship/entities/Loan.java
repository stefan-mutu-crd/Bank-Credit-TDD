package com.cedacri.internship.entities;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Loan {

    private final int id;

    private final double initialSum;

    private final double refunded;

    private final String purpose;

    private final LocalDate dateOfIssue;

    private final LocalDate deadline;

    private final int percentage;

    private final int clientId;
}
