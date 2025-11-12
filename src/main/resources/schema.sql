-- client 테이블
CREATE TABLE client (
                        client_id VARCHAR(100) PRIMARY KEY,
                        created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- memo 테이블
CREATE TABLE memo (
                      memo_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      client_id VARCHAR(100) NOT NULL,
                      date VARCHAR(8) NOT NULL,  -- YYYYMMDD
                      emoji VARCHAR(8) NOT NULL,
                      emotion_score INT NOT NULL,
                      title VARCHAR(100) NOT NULL,
                      content TEXT NOT NULL,
                      created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

                      FOREIGN KEY (client_id) REFERENCES client(client_id),
                      UNIQUE KEY unique_client_date (client_id, date),
                      INDEX idx_client_date (client_id, date)
);