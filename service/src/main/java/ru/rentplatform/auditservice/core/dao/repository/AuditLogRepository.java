package ru.rentplatform.auditservice.core.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.rentplatform.auditservice.core.dao.entity.AuditLog;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    @Query("SELECT a " +
            "FROM AuditLog a " +
            "WHERE a.createdAt >= :since " +
            "ORDER BY a.createdAt DESC")
    Page<AuditLog> findToday(@Param("since") OffsetDateTime since, Pageable pageable);

    @Query("SELECT a " +
            "FROM AuditLog a " +
            "ORDER BY a.createdAt DESC")
    Page<AuditLog> findAllPaged(Pageable pageable);

    @Query("SELECT a " +
            "FROM AuditLog a " +
            "WHERE a.userId = :userId " +
            "ORDER BY a.createdAt DESC")
    Page<AuditLog> findByUserId(@Param("userId") UUID userId, Pageable pageable);

    @Query("SELECT a " +
            "FROM AuditLog a " +
            "WHERE a.action " +
            "IN :actions " +
            "ORDER BY a.createdAt DESC")
    Page<AuditLog> findByActionIn(@Param("actions") List<String> actions, Pageable pageable);
}
