package ru.rentplatform.auditservice.core.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

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

    private String service;

    private UUID userId;

    private String nickname;

    private String action;

    private String targetType;

    private UUID targetId;

    @Column(columnDefinition = "jsonb")
    private String details;

    private OffsetDateTime createdAt;
}
