package com.cedacri.internship.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "loans")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double initialSum;

    private Double refunded;

    private String purpose;

    private LocalDate dateOfIssue;

    private LocalDate deadline;

    private Integer percentage;

    @ManyToOne
    private Client client;
}
