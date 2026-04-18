package com.tigersalesmanager.engine.datarepo;

import com.tigersalesmanager.engine.datamodel.Business;
import com.tigersalesmanager.engine.datamodel.SalesData;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SalesDataRepository extends JpaRepository<SalesData, UUID> {
    Optional<SalesData> findByBusinessAndYearAndMonth(Business business, Integer year, Integer month);
    List<SalesData> findByBusinessAndYearBetween(Business business, Integer startYear, Integer endYear);
    List<SalesData> findByBusiness(Business business);
}
