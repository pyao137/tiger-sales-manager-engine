package com.tigersalesmanager.engine.datarepo;

import com.tigersalesmanager.engine.datamodel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}
