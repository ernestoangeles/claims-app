CREATE SCHEMA IF NOT EXISTS claims_schema;

-- Crear la secuencia dentro del schema
CREATE SEQUENCE IF NOT EXISTS claims_schema.claim_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;