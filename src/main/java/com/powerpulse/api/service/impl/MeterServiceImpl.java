package com.powerpulse.api.service.impl;
import com.powerpulse.api.dto.MeterRequest;
import com.powerpulse.api.dto.MeterResponse;
import com.powerpulse.api.entity.Meter;
import com.powerpulse.api.exception.ResourceNotFoundException;
import com.powerpulse.api.repository.MeterRepository;
import com.powerpulse.api.service.MeterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class MeterServiceImpl implements MeterService {
    private final MeterRepository meterRepository;
    @Override
    public MeterResponse createMeter(MeterRequest request) {
        if (meterRepository.existsByMeterNumber(
                request.getMeterNumber())) {
            throw new RuntimeException(
                    "Meter number already exists");
        }
        Meter meter = Meter.builder()
                .meterNumber(request.getMeterNumber())
                .consumerName(request.getConsumerName())
                .location(request.getLocation())
                .meterType(request.getMeterType())
                .status("ACTIVE")
                .build();

        Meter savedMeter = meterRepository.save(meter);

        return mapToResponse(savedMeter);
    }

    @Override
    public List<MeterResponse> getAllMeters() {
        return meterRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public MeterResponse getMeterById(Long id) {

        Meter meter = meterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Meter not found"));

        return mapToResponse(meter);
    }

    @Override
    public MeterResponse updateMeter(
            Long id,
            MeterRequest request) {

        Meter meter = meterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Meter not found"));

        meter.setMeterNumber(request.getMeterNumber());
        meter.setConsumerName(request.getConsumerName());
        meter.setLocation(request.getLocation());
        meter.setMeterType(request.getMeterType());

        Meter updatedMeter =
                meterRepository.save(meter);

        return mapToResponse(updatedMeter);
    }

    @Override
    public void deleteMeter(Long id) {

        Meter meter = meterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Meter not found"));

        meterRepository.delete(meter);
    }

    private MeterResponse mapToResponse(Meter meter) {

        return MeterResponse.builder()
                .id(meter.getId())
                .meterNumber(meter.getMeterNumber())
                .consumerName(meter.getConsumerName())
                .location(meter.getLocation())
                .meterType(meter.getMeterType())
                .status(meter.getStatus())
                .build();
    }
}