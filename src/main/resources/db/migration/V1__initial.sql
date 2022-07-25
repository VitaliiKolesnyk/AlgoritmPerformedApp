CREATE SEQUENCE algoritm_result_sequence
    START 1
    INCREMENT 1
;

CREATE TABLE algoritm_results(
id BIGSERIAL PRIMARY KEY,
algoritm_type VARCHAR(255) NOT NULL,
inFile VARCHAR(255) NOT NULL,
values_quantity INT NOT NULL,
outFile VARCHAR(255),
startTime TIMESTAMP NOT NULL,
stopTime TIMESTAMP,
duration DECIMAL,
result VARCHAR(255) NOT NULL
);