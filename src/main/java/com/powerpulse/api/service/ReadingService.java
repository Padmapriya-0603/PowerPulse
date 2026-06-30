package com.powerpulse.api.service;
import com.powerpulse.api.dto.ReadingRequest;
import com.powerpulse.api.dto.ReadingResponse;
import org.springframework.data.domain.Page;
import java.time.LocalDate;
import java.util.List;
public interface ReadingService {
    ReadingResponse createReading(ReadingRequest request);

    List<ReadingResponse> getAllReadings();

    ReadingResponse getReadingById(Long id);

    List<ReadingResponse> getReadingsByMeter(Long meterId);

    List<ReadingResponse> getReadingsByDateRange(
            Long meterId,
            LocalDate start,
            LocalDate end);

    Page<ReadingResponse> getPagedReadings(
            Long meterId,
            int page,
            int size
    );
}