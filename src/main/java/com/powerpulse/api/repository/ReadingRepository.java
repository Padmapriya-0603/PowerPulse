package com.powerpulse.api.repository;
import com.powerpulse.api.entity.Reading;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findByMeterId(Long meterId);

    List<Reading> findByMeterIdAndReadingDateBetween(
            Long meterId,
            LocalDate startDate,
            LocalDate endDate
    );

    Page<Reading> findByMeterId(Long meterId, Pageable pageable);
}