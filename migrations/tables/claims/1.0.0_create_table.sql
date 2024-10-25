-- Claim table
CREATE TABLE IF NOT EXISTS claims_schema.claims (
  id UUID NOT NULL DEFAULT gen_random_uuid(),
  code VARCHAR(20) NOT NULL,
  company VARCHAR(50) NOT NULL,
  reason VARCHAR(100) NOT NULL,
  description TEXT NOT NULL,
  customer_email VARCHAR(255) NOT NULL,
  status VARCHAR(50) NOT NULL,
  creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  creation_user VARCHAR(100) NOT NULL,
  update_date TIMESTAMP,
  update_user VARCHAR(100),
  CONSTRAINT pk_claims PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_claims_creation_date
    ON claims_schema.claims(creation_date DESC);

-- Add table comments
COMMENT ON TABLE claims_schema.claims IS 'Stores the main claim information';
COMMENT ON COLUMN claims_schema.claims.id IS 'Unique identifier for the claim';
COMMENT ON COLUMN claims_schema.claims.code IS 'Code auto-generated REC000123';
COMMENT ON COLUMN claims_schema.claims.company IS 'Company where the claim was filed';
COMMENT ON COLUMN claims_schema.claims.reason IS 'Main reason for the claim';
COMMENT ON COLUMN claims_schema.claims.status IS 'Latest status for the claim';
COMMENT ON COLUMN claims_schema.claims.customer_email IS 'Email of the customer who filed the claim';