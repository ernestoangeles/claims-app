package com.example.claims.service;

import com.example.claims.service.util.ClaimCodeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ClaimCodeServiceTest {

    @Autowired
    private ClaimCodeService claimCodeService;

    @Test
    void shouldGenerateCodeWithCorrectFormat() {
        // When
        String code = claimCodeService.generateCode();

        // Then
        assertNotNull(code, "El código no debe ser null");
        assertTrue(code.startsWith("REC"), "El código debe empezar con 'REC'");
        assertEquals(9, code.length(), "El código debe tener longitud 9");
        assertTrue(code.substring(3).matches("\\d{6}"),
                "El código debe tener 8 dígitos después de 'REC'");
    }

    @Test
    void shouldGenerateUniqueCodesSequentially() {
        // When
        String code1 = claimCodeService.generateCode();
        String code2 = claimCodeService.generateCode();
        String code3 = claimCodeService.generateCode();

        // Then
        Set<String> uniqueCodes = new HashSet<>();
        uniqueCodes.add(code1);
        uniqueCodes.add(code2);
        uniqueCodes.add(code3);

        assertEquals(3, uniqueCodes.size(), "Todos los códigos deben ser únicos");

        // Verificar secuencia
        int num1 = Integer.parseInt(code1.substring(3));
        int num2 = Integer.parseInt(code2.substring(3));
        int num3 = Integer.parseInt(code3.substring(3));

        assertEquals(1, num2 - num1, "Los códigos deben ser secuenciales");
        assertEquals(1, num3 - num2, "Los códigos deben ser secuenciales");
    }
}