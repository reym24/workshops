-- Find the dealership where a certain car is located, by VIN
USE car_dealership;
SELECT d.* 
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
WHERE i.vin = '1HGCM82633A123456'; -- Replace with desired VIN