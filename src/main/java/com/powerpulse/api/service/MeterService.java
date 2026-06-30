package com.powerpulse.api.service;
import com.powerpulse.api.dto.MeterRequest;
import com.powerpulse.api.dto.MeterResponse;
import java.util.List;
public interface MeterService {
    MeterResponse createMeter(MeterRequest request);

    List<MeterResponse> getAllMeters();

    MeterResponse getMeterById(Long id);

    MeterResponse updateMeter(Long id, MeterRequest request);

    void deleteMeter(Long id);
}