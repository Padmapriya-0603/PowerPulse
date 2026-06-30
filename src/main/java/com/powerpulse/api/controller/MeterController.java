package com.powerpulse.api.controller;
import com.powerpulse.api.dto.MeterRequest;
import com.powerpulse.api.dto.MeterResponse;
import com.powerpulse.api.service.MeterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/meters")
@RequiredArgsConstructor
public class MeterController {
    private final MeterService meterService;
    @PostMapping
    public MeterResponse createMeter(
           @Valid @RequestBody MeterRequest request) {
        return meterService.createMeter(request);
    }
    @GetMapping
    public List<MeterResponse> getAllMeters() {
        return meterService.getAllMeters();
    }
    @GetMapping("/{id}")
    public MeterResponse getMeterById(
            @PathVariable Long id) {
        return meterService.getMeterById(id);
    }
    @PutMapping("/{id}")
    public MeterResponse updateMeter(
            @PathVariable Long id,
            @RequestBody MeterRequest request) {
        return meterService.updateMeter(id, request);
    }
    @DeleteMapping("/{id}")
    public void deleteMeter(
            @PathVariable Long id) {
        meterService.deleteMeter(id);
    }
}