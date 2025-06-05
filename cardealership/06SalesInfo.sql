-- Get all sales information for a specific dealer for a specific date range
USE car_dealership;
SELECT sc.* 
FROM sales_contracts sc
JOIN inventory i ON sc.vin = i.vin
WHERE i.dealership_id = 1 -- Replace with desired dealership ID
  AND sc.contract_date BETWEEN '2023-01-01' AND '2023-12-31'; -- Replace with desired date range