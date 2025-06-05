-- Find all Dealerships that have a certain car type
USE car_dealership;
SELECT d.* 
FROM dealerships d
JOIN inventory i ON d.dealership_id = i.dealership_id
JOIN vehicles v ON i.vin = v.vin
WHERE v.make = 'Honda' 
  AND v.model = 'Accord' 
  AND v.color = 'Blue'; -- Replace with desired criteria