package com.gxh.apserver.repository.interfaces;

import com.gxh.apserver.entity.DualMailer;
import com.gxh.apserver.entity.Notification;
import com.gxh.apserver.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
