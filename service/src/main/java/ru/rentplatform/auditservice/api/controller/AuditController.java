package ru.rentplatform.auditservice.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.rentplatform.auditservice.core.dao.entity.AuditLog;
import ru.rentplatform.auditservice.core.service.AuditService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Аудит", description = "Журнал действий пользователей")
public class AuditController {

    private final AuditService auditService;

    @GetMapping("/today")
    @PreAuthorize("hasAnyRole('moderator', 'admin', 'super_admin')")
    public Page<AuditLog> getToday(@RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "50") int size) {
        return auditService.getToday(page, size);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('moderator', 'admin', 'super_admin')")
    public Page<AuditLog> getAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "50") int size) {
        return auditService.getAll(page, size);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAnyRole('moderator', 'admin', 'super_admin')")
    public Page<AuditLog> getByUserId(@PathVariable UUID userId,
                                      @RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "50") int size) {
        return auditService.getByUserId(userId, page, size);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyRole('admin', 'super_admin')")
    @Operation(summary = "Глобальные логи",
            description = "Создание/удаление категорий, назначение ролей. Только админ и супер-админ")
    public Page<AuditLog> getGlobalLogs(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "50") int size) {
        return auditService.getByActions(page, size, List.of(
                "CREATE_CATEGORY", "UPDATE_CATEGORY", "DELETE_CATEGORY",
                "CHANGE_ROLE"
        ));
    }
}
