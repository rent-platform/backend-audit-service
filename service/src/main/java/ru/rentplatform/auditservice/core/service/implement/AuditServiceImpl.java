package ru.rentplatform.auditservice.core.service.implement;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rentplatform.auditservice.core.dao.entity.AuditLog;
import ru.rentplatform.auditservice.core.dao.repository.AuditLogRepository;
import ru.rentplatform.auditservice.core.service.AuditService;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;

    @Override
    @Transactional
    public void log(String service, UUID userId, String nickname, String action,
                    String targetType, UUID targetId, String details) {
        AuditLog log = AuditLog.builder()
                .service(service).userId(userId).nickname(nickname)
                .action(action).targetType(targetType).targetId(targetId)
                .details(details).createdAt(OffsetDateTime.now()).build();
        auditLogRepository.save(log);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuditLog> getToday(int page, int size) {
        OffsetDateTime todayStart = OffsetDateTime.now().truncatedTo(ChronoUnit.DAYS);
        return auditLogRepository.findToday(todayStart, PageRequest.of(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuditLog> getAll(int page, int size) {
        return auditLogRepository.findAllPaged(PageRequest.of(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuditLog> getByUserId(UUID userId, int page, int size) {
        return auditLogRepository.findByUserId(userId, PageRequest.of(page, size));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuditLog> getByActions(int page, int size, List<String> actions) {
        return auditLogRepository.findByActionIn(actions, PageRequest.of(page, size));
    }
}

