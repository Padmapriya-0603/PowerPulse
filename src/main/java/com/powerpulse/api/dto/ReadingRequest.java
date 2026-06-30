package com.powerpulse.api.dto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
@Data
public class ReadingRequest {
    @NotNull(message = "Meter ID is required")
    private Long meterId;
    @NotNull(message = "Reading date is required")
    private LocalDate readingDate;
    @NotNull(message = "Reading time is required")
    private LocalTime readingTime;
    @NotNull(message = "Current reading is required")
    @Positive
    private Double currentReading;
    @NotNull(message = "Previous reading is required")
    @PositiveOrZero
    private Double previousReading;
    @NotNull(message = "Voltage is required")
    private Double voltage;
    @NotNull(message = "Current is required")
    private Double current;
}