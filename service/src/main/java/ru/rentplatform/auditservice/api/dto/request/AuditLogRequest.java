package ru.rentplatform.auditservice.api.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogRequest {

    private String service;

    private UUID userId;

    private String nickname;

    private String action;

    private String targetType;

    private UUID targetId;

    private String details;
}