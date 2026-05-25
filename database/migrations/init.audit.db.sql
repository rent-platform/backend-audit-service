CREATE TABLE audit_log (
    id          BIGSERIAL    PRIMARY KEY,
    service     VARCHAR(50)  NOT NULL,
    user_id     UUID         NOT NULL,
    nickname    VARCHAR(50)  NOT NULL,
    action      VARCHAR(100) NOT NULL,
    target_type VARCHAR(50),
    target_id   UUID,
    details     JSONB,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT NOW()
);

CREATE INDEX audit_log_user_id_idx
    ON audit_log(user_id);

CREATE INDEX audit_log_service_idx
    ON audit_log(service);

CREATE INDEX audit_log_created_at_idx
    ON audit_log(created_at);