package ru.rentplatform.auditservice.api.controller;

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
public class InternalAuditController {

    private final AuditService auditService;

    @PostMapping
    public void receiveLog(@RequestBody AuditLogRequest request) {
        auditService.log(
                request.getService(), request.getUserId(), request.getNickname(),
                request.getAction(), request.getTargetType(), request.getTargetId(),
                request.getDetails()
        );
    }
}
