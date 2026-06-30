package com.powerpulse.api.service.impl;
import com.powerpulse.api.dto.ReadingRequest;
import com.powerpulse.api.dto.ReadingResponse;
import com.powerpulse.api.entity.Meter;
import com.powerpulse.api.entity.Reading;
import com.powerpulse.api.exception.ResourceNotFoundException;
import com.powerpulse.api.repository.MeterRepository;
import com.powerpulse.api.repository.ReadingRepository;
import com.powerpulse.api.service.ReadingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReadingServiceImpl implements ReadingService {
    private final ReadingRepository readingRepository;
    private final MeterRepository meterRepository;
    @Override
    public ReadingResponse createReading(ReadingRequest request) {
        Meter meter = meterRepository.findById(request.getMeterId())
                .orElseThrow(() -> new ResourceNotFoundException("Meter not found"));
        double consumption = request.getCurrentReading() - request.getPreviousReading();
        Reading reading = Reading.builder()
                .meter(meter)
                .readingDate(request.getReadingDate())
                .readingTime(request.getReadingTime())
                .currentReading(request.getCurrentReading())
                .previousReading(request.getPreviousReading())
                .voltage(request.getVoltage())
                .current(request.getCurrent())
                .consumption(consumption)
                .build();
        Reading savedReading = readingRepository.save(reading);
        return mapToResponse(savedReading);
    }
    @Override
    public List<ReadingResponse> getAllReadings() {
        return readingRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public ReadingResponse getReadingById(Long id) {

        Reading reading = readingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reading not found"));

        return mapToResponse(reading);
    }
    @Override
    public List<ReadingResponse> getReadingsByMeter(Long meterId) {

        return readingRepository.findByMeterId(meterId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    @Override
    public List<ReadingResponse> getReadingsByDateRange(
            Long meterId,
            LocalDate start,
            LocalDate end
    ) {
        return readingRepository.findByMeterIdAndReadingDateBetween(
                        meterId,
                        start,
                        end
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public Page<ReadingResponse> getPagedReadings(
            Long meterId,
            int page,
            int size
    ) {
        return readingRepository.findByMeterId(
                        meterId,
                        PageRequest.of(page, size)
                )
                .map(this::mapToResponse);
    }

    private ReadingResponse mapToResponse(Reading reading) {
        return ReadingResponse.builder()
                .id(reading.getId())
                .meterNumber(reading.getMeter().getMeterNumber())
                .readingDate(reading.getReadingDate())
                .readingTime(reading.getReadingTime())
                .currentReading(reading.getCurrentReading())
                .previousReading(reading.getPreviousReading())
                .voltage(reading.getVoltage())
                .current(reading.getCurrent())
                .consumption(reading.getConsumption())
                .build();
    }
}