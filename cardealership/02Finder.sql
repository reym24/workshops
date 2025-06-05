-- Find all vehicles for a specific dealership
USE car_dealership;
SELECT v.* 
FROM vehicles v
JOIN inventory i ON v.vin = i.vin
WHERE i.dealership_id = 1; -- Replace with desired dealership ID