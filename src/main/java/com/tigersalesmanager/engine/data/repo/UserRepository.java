package com.tigersalesmanager.engine.data.repo;

import com.tigersalesmanager.engine.data.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
