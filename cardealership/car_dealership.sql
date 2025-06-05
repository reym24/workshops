-- Dealership Database Setup Script


DROP DATABASE IF EXISTS car_dealership;
CREATE DATABASE car_dealership;
USE car_dealership;


-- Dealerships table
CREATE TABLE dealerships (
    dealership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    phone VARCHAR(12) NOT NULL
);

-- Vehicles table
CREATE TABLE vehicles (
    vin VARCHAR(17) PRIMARY KEY,
    year INT NOT NULL,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    vehicle_type VARCHAR(20) NOT NULL,
    color VARCHAR(20) NOT NULL,
    odometer INT NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    sold BOOLEAN DEFAULT FALSE
);

-- Inventory table (junction table for dealerships and vehicles)
CREATE TABLE inventory (
    dealership_id INT NOT NULL,
    vin VARCHAR(17) NOT NULL,
    PRIMARY KEY (dealership_id, vin),
    FOREIGN KEY (dealership_id) REFERENCES dealerships(dealership_id),
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

-- Sales contracts table
CREATE TABLE sales_contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(17) NOT NULL,
    contract_date DATE NOT NULL,
    customer_name VARCHAR(50) NOT NULL,
    customer_email VARCHAR(50) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    monthly_payment DECIMAL(10,2) NOT NULL,
    sales_tax DECIMAL(10,2) NOT NULL,
    recording_fee DECIMAL(10,2) NOT NULL,
    processing_fee DECIMAL(10,2) NOT NULL,
    finance_option BOOLEAN NOT NULL,
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);

-- Lease contracts table (Optional)
CREATE TABLE lease_contracts (
    contract_id INT AUTO_INCREMENT PRIMARY KEY,
    vin VARCHAR(17) NOT NULL,
    contract_date DATE NOT NULL,
    customer_name VARCHAR(50) NOT NULL,
    customer_email VARCHAR(50) NOT NULL,
    total_price DECIMAL(10,2) NOT NULL,
    monthly_payment DECIMAL(10,2) NOT NULL,
    expected_ending_value DECIMAL(10,2) NOT NULL,
    lease_fee DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (vin) REFERENCES vehicles(vin)
);


-- Insert dealerships
INSERT INTO dealerships (name, address, phone) VALUES
('Premier Auto Sales', '123 Main St, Anytown, USA', '555-123-4567'),
('Elite Motors', '456 Oak Ave, Somewhere, USA', '555-234-5678'),
('City Auto Group', '789 Pine Rd, Nowhere, USA', '555-345-6789');

-- Insert vehicles
INSERT INTO vehicles (vin, year, make, model, vehicle_type, color, odometer, price) VALUES
('1HGCM82633A123456', 2020, 'Honda', 'Accord', 'Sedan', 'Blue', 25000, 22500.00),
('5XYZU3LBXKG123789', 2022, 'Toyota', 'Camry', 'Sedan', 'Red', 15000, 24500.00),
('2C3CDXBG9KH567890', 2021, 'Ford', 'F-150', 'Truck', 'Black', 18000, 38500.00),
('1G1FB1RS0M0123456', 2023, 'Chevrolet', 'Corvette', 'Sports', 'Yellow', 500, 68900.00),
('WAUZZZ4G6BN123456', 2019, 'Audi', 'A4', 'Sedan', 'Silver', 32000, 28900.00),
('5FNRL5H97CB123456', 2020, 'Honda', 'Pilot', 'SUV', 'White', 28000, 31500.00);

-- Insert inventory
INSERT INTO inventory (dealership_id, vin) VALUES
(1, '1HGCM82633A123456'),
(1, '5XYZU3LBXKG123789'),
(2, '2C3CDXBG9KH567890'),
(2, '1G1FB1RS0M0123456'),
(3, 'WAUZZZ4G6BN123456'),
(3, '5FNRL5H97CB123456');

-- Insert sales contracts
INSERT INTO sales_contracts (vin, contract_date, customer_name, customer_email, total_price, monthly_payment, sales_tax, recording_fee, processing_fee, finance_option) VALUES
('1HGCM82633A123456', '2023-05-15', 'John Smith', 'john.smith@email.com', 23862.50, 542.39, 1125.00, 100.00, 295.00, TRUE),
('5XYZU3LBXKG123789', '2023-06-20', 'Sarah Johnson', 'sarah.j@email.com', 25925.00, 0.00, 1225.00, 100.00, 495.00, FALSE);

-- Insert lease contracts 
INSERT INTO lease_contracts (vin, contract_date, customer_name, customer_email, total_price, monthly_payment, expected_ending_value, lease_fee) VALUES
('WAUZZZ4G6BN123456', '2023-04-10', 'Michael Brown', 'michael.b@email.com', 30923.00, 859.23, 14450.00, 2023.00),
('5FNRL5H97CB123456', '2023-07-05', 'Emily Davis', 'emily.d@email.com', 33705.00, 936.25, 15750.00, 2205.00);

-- Mark sold vehicles
UPDATE vehicles SET sold = TRUE WHERE vin IN (
    '1HGCM82633A123456', 
    '5XYZU3LBXKG123789',
    'WAUZZZ4G6BN123456',
    '5FNRL5H97CB123456'
);