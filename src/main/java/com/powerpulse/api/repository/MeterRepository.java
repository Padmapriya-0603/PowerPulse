package com.powerpulse.api.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.powerpulse.api.entity.Meter;
import java.util.Optional;
@Repository
public interface MeterRepository extends JpaRepository<Meter, Long> {
    Optional<Meter> findByMeterNumber(String meterNumber);
    boolean existsByMeterNumber(String meterNumber);
}