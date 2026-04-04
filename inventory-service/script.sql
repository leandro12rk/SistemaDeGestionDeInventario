create database inventory_service_db ;

-- Estructura
CREATE TABLE inventory (
                           id SERIAL PRIMARY KEY,
                           product_id INTEGER NOT NULL UNIQUE,
                           quantity INTEGER NOT NULL DEFAULT 0,
                           last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE goods_receipts (
                                id SERIAL PRIMARY KEY,
                                purchase_order_id INTEGER,
                                received_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                received_by VARCHAR(100)
);

-- Movimientos para auditoría
CREATE TABLE stock_movements (
                                 id SERIAL PRIMARY KEY,
                                 product_id INTEGER NOT NULL,
                                 type VARCHAR(10) CHECK (type IN ('IN', 'OUT', 'ADJUSTMENT')),
                                 amount INTEGER NOT NULL,
                                 reason TEXT,
                                 created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- 100 Registros de Inventario (Sincronizados con los productos)
INSERT INTO inventory (product_id, quantity, last_updated)
SELECT
    i,
    (floor(random() * 150) + 5), -- Stock entre 5 y 155 unidades
    NOW() - (random() * interval '7 days')
FROM generate_series(1, 100) s(i);

-- 100 Movimientos de Stock (Auditoría)
INSERT INTO stock_movements (product_id, type, amount, reason)
SELECT
    (floor(random() * 100) + 1),
    (ARRAY['IN', 'OUT', 'ADJUSTMENT'])[floor(random() * 3) + 1],
    (floor(random() * 15) + 1),
    (ARRAY['Venta directa', 'Recepción de OC', 'Ajuste por daño', 'Devolución cliente'])[floor(random() * 4) + 1]
FROM generate_series(1, 100) s(i);