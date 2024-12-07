INSERT INTO member (type, name, email, phone, address, delete_flag) VALUES
-- Individual Member
(1, 'John Doe', 'john.doe@example.com', '1234567890', '123 Main St, City, Country', 0),
(1, 'Jane Smith', 'jane.smith@example.com', '0987654321', '456 Oak St, Town, Country', 0),

-- Business Member
(2, 'ABC Corp', 'contact@abccorp.com', '5678901234', '789 Pine St, Village, Country', 0),
(2, 'XYZ Ltd', 'info@xyzltd.com', NULL, '321 Elm St, City, Country', 1),

-- Premium Member
(3, 'Alice Premium', 'alice.premium@example.com', '4561237890', '987 Maple St, City, Country', 0),
(3, 'Charlie Davis', 'charlie.davis@example.com', '1112223333', NULL, 0),

-- Corporate Member
(4, 'Premium Corp', 'premium@corporate.com', '8889997777', '555 High St, City, Country', 0),
(4, 'Elite Ltd', 'elite@elitecorp.com', '4445556666', '777 Sky Ave, Town, Country', 0),

-- Guest User
(5, 'Guest User1', 'guest1@example.com', NULL, 'Guest Address 1, City, Country', 0),
(5, 'Guest User2', 'guest2@example.com', '9998887777', NULL, 0);