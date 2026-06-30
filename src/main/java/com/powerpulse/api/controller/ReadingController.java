package com.powerpulse.api.controller;
import com.powerpulse.api.dto.ReadingRequest;
import com.powerpulse.api.dto.ReadingResponse;
import com.powerpulse.api.service.ReadingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/readings")
@RequiredArgsConstructor
public class ReadingController {
    private final ReadingService readingService;
    @PostMapping
    public ReadingResponse createReading(
           @Valid @RequestBody ReadingRequest request) {
        return readingService.createReading(request);
    }
    @GetMapping
    public List<ReadingResponse> getAllReadings() {
        return readingService.getAllReadings();
    }
    @GetMapping("/{id}")
    public ReadingResponse getReadingById(
            @PathVariable Long id) {
        return readingService.getReadingById(id);
    }
    @GetMapping("/meter/{meterId}")
    public List<ReadingResponse> getReadingsByMeter(
            @PathVariable Long meterId) {
        return readingService.getReadingsByMeter(meterId);
    }

    @GetMapping("/filter")
    public List<ReadingResponse> getReadingsByDateRange(
            @RequestParam Long meterId,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end) {
        return readingService.getReadingsByDateRange(meterId, start, end);
    }
    @GetMapping("/page")
    public Page<ReadingResponse> getPagedReadings(
            @RequestParam Long meterId,
            @RequestParam int page,
            @RequestParam int size) {
        return readingService.getPagedReadings(meterId, page, size);
    }
}