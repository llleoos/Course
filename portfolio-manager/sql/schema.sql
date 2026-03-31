-- Create database
CREATE DATABASE IF NOT EXISTS portfolio_db;
USE portfolio_db;

-- Create portfolio_item table
CREATE TABLE IF NOT EXISTS portfolio_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    ticker VARCHAR(20) NOT NULL,
    asset_type VARCHAR(20),
    quantity INT NOT NULL,
    purchase_price DECIMAL(10,2),
    current_price DECIMAL(10,2),
    purchase_date DATE,
    created_at DATETIME,
    updated_at DATETIME
);

-- Insert sample data
INSERT INTO portfolio_item (ticker, asset_type, quantity, purchase_price, current_price, purchase_date, created_at, updated_at) VALUES
('AAPL', 'STOCK', 10, 150.00, 175.00, '2024-01-15', NOW(), NOW()),
('GOOGL', 'STOCK', 5, 140.00, 155.00, '2024-02-01', NOW(), NOW()),
('BND', 'BOND', 20, 75.00, 76.50, '2024-01-20', NOW(), NOW()),
('CASH', 'CASH', 1000, 1.00, 1.00, '2024-01-01', NOW(), NOW());
