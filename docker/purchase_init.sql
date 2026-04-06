DROP TABLE IF EXISTS purchase_orders;
DROP TABLE IF EXISTS purchase_order_details;

-- Estructura
CREATE TABLE purchase_orders
(
    id           SERIAL PRIMARY KEY,
    supplier_id  INTEGER, -- Referencia al ID del otro servicio
    order_date   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status       VARCHAR(20) CHECK (status IN ('PENDING', 'APPROVED', 'RECEIVED', 'CANCELLED')),
    total_amount NUMERIC(12, 2)
);

CREATE TABLE purchase_order_details
(
    id                SERIAL PRIMARY KEY,
    purchase_order_id INTEGER REFERENCES purchase_orders (id),
    product_id        INTEGER, -- Referencia al ID del servicio de productos
    quantity_ordered  INTEGER        NOT NULL,
    unit_price        NUMERIC(10, 2) NOT NULL
);

-- Órdenes de Compra (Basadas en proveedores anteriores 1 al 10)
INSERT INTO purchase_orders (supplier_id, status, total_amount)
VALUES (1, 'RECEIVED', 5495.00),  -- Orden a Intcomex
       (3, 'APPROVED', 12500.00), -- Orden a Dell
       (10, 'PENDING', 550.00),   -- Orden a TP-Link
       (5, 'RECEIVED', 1250.00),  -- Orden a Logitech
       (9, 'CANCELLED', 0.00);
-- Orden cancelada

-- Detalles de la Orden 1 (Intcomex - Laptops y Monitores)
INSERT INTO purchase_order_details (purchase_order_id, product_id, quantity_ordered, unit_price)
VALUES (1, 1, 5, 1099.00);
-- 5 MacBook Air M2

-- Detalles de la Orden 2 (Dell - Laptops empresariales)
INSERT INTO purchase_order_details (purchase_order_id, product_id, quantity_ordered, unit_price)
VALUES (2, 2, 10, 1250.00);
-- 10 Dell XPS 13

-- Detalles de la Orden 3 (TP-Link - Redes)
INSERT INTO purchase_order_details (purchase_order_id, product_id, quantity_ordered, unit_price)
VALUES (3, 51, 5, 110.00);
-- 5 Routers Archer AX55

-- Detalles de la Orden 4 (Logitech - Periféricos)
INSERT INTO purchase_order_details (purchase_order_id, product_id, quantity_ordered, unit_price)
VALUES (4, 41, 10, 125.00); -- 10 Mouse G Pro Wireless