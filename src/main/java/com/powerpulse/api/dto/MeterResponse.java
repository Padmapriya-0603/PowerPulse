package com.powerpulse.api.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
@AllArgsConstructor
public class MeterResponse {

    private Long id;

    private String meterNumber;

    private String consumerName;

    private String location;

    private String meterType;

    private String status;
}