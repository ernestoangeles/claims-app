-- Crear la secuencia si no existe
CREATE SEQUENCE IF NOT EXISTS claims_schema.claim_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Comentario para documentación
COMMENT ON SEQUENCE claims_schema.claim_sequence 
    IS 'Secuencia para generar códigos únicos de reclamos en formato REC000123';