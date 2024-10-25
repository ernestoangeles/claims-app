package com.example.claims.constants;

import java.util.List;

public final class ClaimConstants {
    // Companies
    public static final String COMPANY_PROMART = "Promart";
    public static final String COMPANY_OECHSLE = "Oechsle";
    public static final String COMPANY_PLAZA_VEA = "plazaVea";
    public static final String COMPANY_VIVANDA = "Vivanda";

    public static final List<String> VALID_COMPANIES = List.of(
            COMPANY_PROMART,
            COMPANY_OECHSLE,
            COMPANY_PLAZA_VEA,
            COMPANY_VIVANDA
    );

    // Reasons
    public static final String REASON_MISSING_DELIVERY = "Fecha de Entrega Incumplida";
    public static final String REASON_DEFECTIVE_PRODUCT = "Producto con Defectos";
    public static final String REASON_STAFF_ISSUES = "Problemas con Personal";

    public static final List<String> VALID_REASONS = List.of(
            REASON_MISSING_DELIVERY,
            REASON_DEFECTIVE_PRODUCT,
            REASON_STAFF_ISSUES
    );

    // Status
    public static final String STATUS_PENDING = "Pendiente";
    public static final String STATUS_IN_REVIEW = "En revisión";
    public static final String STATUS_CLOSED = "Cerrada";
    public static final String STATUS_CANCELLED = "Anulada";

    public static final List<String> VALID_STATUSES = List.of(
            STATUS_PENDING,
            STATUS_IN_REVIEW,
            STATUS_CLOSED,
            STATUS_CANCELLED
    );

    private ClaimConstants() {
        // Prevenir instanciación
    }
}