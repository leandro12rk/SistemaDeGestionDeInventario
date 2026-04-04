create database product_service_db;

       -- Estructura
CREATE TABLE suppliers (
                           id SERIAL PRIMARY KEY,
                           company_name VARCHAR(150) NOT NULL,
                           contact_name VARCHAR(100),
                           email VARCHAR(100),
                           phone VARCHAR(20),
                           address TEXT,
                           created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE categories (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(50) NOT NULL UNIQUE,
                            description TEXT
);

CREATE TABLE products (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(100) NOT NULL,
                          description TEXT,
                          price NUMERIC(10, 2) NOT NULL,
                          category_id INTEGER REFERENCES categories(id),
                          supplier_id INTEGER REFERENCES suppliers(id),
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- 10 Categorías Reales
INSERT INTO categories (name, description) VALUES
                                               ('Laptops', 'Equipos portátiles para oficina y gaming'),
                                               ('Monitores', 'Pantallas LED, IPS y 4K'),
                                               ('Almacenamiento', 'Discos SSD, HDD y memorias USB'),
                                               ('Componentes', 'Procesadores, RAM y tarjetas de video'),
                                               ('Periféricos', 'Teclados, mouses y diademas'),
                                               ('Redes', 'Routers, switches y cableado'),
                                               ('Impresión', 'Impresoras láser y tinta continua'),
                                               ('Servidores', 'Equipos de rack y torres de datos'),
                                               ('Software', 'Licencias de SO y suites de oficina'),
                                               ('Accesorios', 'Cables, mochilas y adaptadores');

-- 10 Proveedores con presencia en Panamá
INSERT INTO suppliers (company_name, contact_name, email, phone, address) VALUES
                                                                              ('Intcomex Panamá', 'Ricardo Pérez', 'ventas@intcomex.com.pa', '+507 300-1234', 'Costa del Este'),
                                                                              ('Solutek Panamá', 'María Castro', 'soporte@solutek.com.pa', '+507 200-5678', 'Vía España'),
                                                                              ('Dell Technologies', 'Carlos Ruiz', 'panama_sales@dell.com', '+507 800-DELL', 'Panamá Pacífico'),
                                                                              ('HP Inc Panamá', 'Elena Gómez', 'contacto@hp.pa', '+507 260-1122', 'Torre de las Américas'),
                                                                              ('Logitech Latam', 'Luis Méndez', 'distribucion@logitech.com', '+507 6543-2100', 'Zona Libre Colón'),
                                                                              ('Kingston Technology', 'Ana Ríos', 'latam_orders@kingston.com', '+507 321-0987', 'Área Bancaria'),
                                                                              ('Cisco Systems PA', 'Jorge Vega', 'partners@cisco.pa', '+507 210-4455', 'Oceania Business Plaza'),
                                                                              ('Microsoft Panamá', 'Sofía Alba', 'licenciamiento@microsoft.pa', '+507 301-9900', 'Calle 50'),
                                                                              ('Samsung Electronics', 'Kevin Chan', 'b2b.pa@samsung.com', '+507 205-1000', 'Multiplaza'),
                                                                              ('TP-Link Panamá', 'Raúl Díaz', 'sales.pa@tp-link.com', '+507 390-6677', 'Obarrio');

-- Generación de 100 Productos Reales (Simulados por marcas)
INSERT INTO products (name, description, price, category_id, supplier_id)
SELECT
    (ARRAY['MacBook Air M2', 'Dell XPS 13', 'Monitor LG 27"', 'SSD Samsung 980 Pro', 'Teclado Logitech G Pro', 'Router TP-Link Archer', 'Impresora Epson L3210', 'RAM Corsair 16GB', 'Procesador Ryzen 7', 'Mouse Razer Deathadder'])[floor(random() * 10) + 1] || ' - v' || i,
    'Especificación técnica modelo 2026 serie ' || i,
    (random() * (1500 - 50) + 50)::numeric(10,2),
    (floor(random() * 10) + 1), -- Referencia a categorías
    (floor(random() * 10) + 1)  -- Referencia a proveedores
FROM generate_series(1, 100) s(i);