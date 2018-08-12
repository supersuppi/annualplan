package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.Manager;
import com.gxh.apserver.entity.Supplier;
import com.gxh.apserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
    @Query("select m from Manager m where m.managerAppUser = ?1")
    Optional<Manager> findManagerByUserID(User userID);
}
