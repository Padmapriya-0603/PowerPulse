package com.powerpulse.api.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class MeterRequest {
    @NotBlank(message = "Meter number is required")
    private String meterNumber;
    @NotBlank(message = "Consumer name is required")
    private String consumerName;
    @NotBlank(message = "Location is required")
    private String location;
    @NotBlank(message = "Meter type is required")
    private String meterType;
}