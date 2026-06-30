package com.powerpulse.api.entity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@Table(name = "readings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate readingDate;

    private LocalTime readingTime;

    private Double currentReading;

    private Double previousReading;

    private Double voltage;

    private Double current;

    private Double consumption;

    @ManyToOne
    @JoinColumn(name = "meter_id")
    private Meter meter;
}