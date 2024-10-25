-- Claim status table
CREATE TABLE IF NOT EXISTS claims_schema.claim_status (
  id UUID NOT NULL DEFAULT gen_random_uuid(),
  claim_id UUID NOT NULL,
  status VARCHAR(50) NOT NULL,
  agent_comment TEXT NOT NULL,
  agent_email VARCHAR(255) NOT NULL,
  creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  creation_user VARCHAR(100) NOT NULL,
  update_date TIMESTAMP,
  update_user VARCHAR(100),
  CONSTRAINT pk_claim_status PRIMARY KEY (id)
);

CREATE INDEX IF NOT EXISTS idx_claim_status_claim_id
    ON claims_schema.claim_status(claim_id);

-- Add table comments
COMMENT ON TABLE claims_schema.claim_status IS 'Stores the history of status changes for claims';
COMMENT ON COLUMN claims_schema.claim_status.claim_id IS 'Reference to the claim';
COMMENT ON COLUMN claims_schema.claim_status.status IS 'Current status of the claim';