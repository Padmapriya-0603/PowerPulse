package com.powerpulse.api.dto;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
@Builder
public class ReadingResponse {

    private Long id;

    private String meterNumber;

    private LocalDate readingDate;

    private LocalTime readingTime;

    private Double currentReading;

    private Double previousReading;

    private Double voltage;

    private Double current;

    private Double consumption;
}