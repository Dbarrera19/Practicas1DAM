-- 1. Crear la base de datos
CREATE DATABASE IF NOT EXISTS akihabara_db;
USE akihabara_db;

-- 2. Crear la tabla producto
CREATE TABLE IF NOT EXISTS producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(100) NOT NULL, -- Ej: "Figura", "Manga", "Póster", "Llavero", "Ropa"
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

CREATE USER IF NOT EXISTS 'userAkihabara'@'localhost' IDENTIFIED BY 'userAkihabara';
GRANT ALL PRIVILEGES ON akihabara_db.* TO 'userAkihabara'@'localhost';
FLUSH PRIVILEGES;

-- 4. Insertar productos de prueba
INSERT INTO producto (nombre, categoria, precio, stock) VALUES
('Figura de Luffy', 'Figura', 29.99, 10),
('Manga One Piece Vol. 1', 'Manga', 7.99, 50),
('Póster Attack on Titan', 'Póster', 12.50, 20),
('Llavero Pikachu', 'Llavero', 4.99, 100),
('Camiseta Naruto', 'Ropa', 19.90, 25),
('Figura de Goku', 'Figura', 34.99, 5),
('Manga Naruto Vol. 1', 'Manga', 8.50, 40),
('Póster Demon Slayer', 'Póster', 10.00, 30),
('Llavero Totoro', 'Llavero', 5.50, 80),
('Sudadera One Piece', 'Ropa', 39.99, 15);

