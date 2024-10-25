DO $$
BEGIN
    IF NOT EXISTS (SELECT FROM pg_namespace WHERE nspname = 'claims_schema') THEN
        CREATE SCHEMA claims_schema;
    END IF;
END $$;

-- Set default permissions
GRANT USAGE ON SCHEMA claims_schema TO public;