package com.tigersalesmanager.engine.datarepo;

import com.tigersalesmanager.engine.datamodel.Business;
import com.tigersalesmanager.engine.datamodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
    List<Business> findByOwner(User owner);
}
