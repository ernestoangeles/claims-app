-- Claim attachments table
CREATE TABLE IF NOT EXISTS claims_schema.claim_attachments (
  id UUID NOT NULL DEFAULT gen_random_uuid(),
  claim_id UUID NOT NULL,
  original_name VARCHAR(255) NOT NULL,
  storage_path VARCHAR(500) NOT NULL,
  content_type VARCHAR(100) NOT NULL,
  size_bytes BIGINT NOT NULL,
  creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  creation_user VARCHAR(100) NOT NULL,
  update_date TIMESTAMP,
  update_user VARCHAR(100),
  CONSTRAINT pk_claim_attachments PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_claim_attachments_claim_id
    ON claims_schema.claim_attachments(claim_id);

-- Add table comments
COMMENT ON TABLE claims_schema.claim_attachments IS 'Stores information about files attached to claims';

