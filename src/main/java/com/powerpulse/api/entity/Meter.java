package com.powerpulse.api.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "meters")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String meterNumber;
    private String consumerName;
    private String location;
    private String meterType;
    private String status;
}
