package com.tigersalesmanager.engine.data.repo;

import com.tigersalesmanager.engine.data.model.Business;
import com.tigersalesmanager.engine.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
    List<Business> findByOwner(User owner);
}
