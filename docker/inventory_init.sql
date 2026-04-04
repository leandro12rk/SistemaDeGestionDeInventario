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


-- 100 Órdenes de Compra
INSERT INTO purchase_orders (supplier_id, order_date, status, total_amount)
SELECT (floor(random() * 10) + 1),
       NOW() - (random() * interval '60 days'), -- Fechas de los últimos 2 meses
       (ARRAY['PENDING', 'APPROVED', 'RECEIVED', 'CANCELLED'])[floor(random() * 4) + 1],
    0
FROM generate_series(1, 100) s(i);

-- 100 Detalles de Órdenes
INSERT INTO purchase_order_details (purchase_order_id, product_id, quantity_ordered, unit_price)
SELECT (floor(random() * 100) + 1), (floor(random() * 100) + 1), (ARRAY[5, 10, 20, 50])[floor(random() * 4) + 1], -- Cantidades realistas
    (random() * 400 + 20)::numeric(10,2)
FROM generate_series(1, 100) s(i);
