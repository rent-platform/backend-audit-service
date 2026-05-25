package ru.rentplatform.auditservice.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.rentplatform.auditservice.api.dto.request.AuditLogRequest;
import ru.rentplatform.auditservice.core.service.AuditService;

@RestController
@RequestMapping("/api/internal/audit")
@RequiredArgsConstructor
@Tag(name = "Внутренние API аудита", description = "Приём логов от других микросервисов")
public class InternalAuditController {

    private final AuditService auditService;

    @PostMapping
    @Operation(summary = "Принять запись аудита",
            description = "Вызывается другими микросервисами для сохранения события в журнале аудита")
    public void receiveLog(@RequestBody AuditLogRequest request) {
        auditService.log(
                request.getService(), request.getUserId(), request.getNickname(),
                request.getAction(), request.getTargetType(), request.getTargetId(),
                request.getDetails()
        );
    }
}
