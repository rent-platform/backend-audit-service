package ru.rentplatform.auditservice.core.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "audit_log")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service", nullable = false, length = 50)
    private String service;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "nickname", nullable = false, length = 50)
    private String nickname;

    @Column(name = "action", nullable = false, length = 100)
    private String action;

    @Column(name = "target_type", length = 50)
    private String targetType;

    @Column(name = "target_id")
    private UUID targetId;

    @Column(name = "details", columnDefinition = "jsonb")
    private String details;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;
}
