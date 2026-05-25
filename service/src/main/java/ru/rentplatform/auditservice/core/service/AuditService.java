package ru.rentplatform.auditservice.core.service;

import org.springframework.data.domain.Page;
import ru.rentplatform.auditservice.core.dao.entity.AuditLog;

import java.util.List;
import java.util.UUID;

public interface AuditService {

    void log(String service, UUID userId, String nickname, String action,
             String targetType, UUID targetId, String details);

    Page<AuditLog> getToday(int page, int size);

    Page<AuditLog> getAll(int page, int size);

    Page<AuditLog> getByUserId(UUID userId, int page, int size);

    Page<AuditLog> getByActions(int page, int size, List<String> actions);
}
