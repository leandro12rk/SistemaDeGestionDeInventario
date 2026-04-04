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


INSERT INTO inventory (product_id, quantity)
VALUES
    (1, 15),  -- MacBook Air M2
    (2, 8),   -- Dell XPS 13
    (11, 20), -- Monitores LG 4K
    (21, 50), -- SSD Samsung 980 Pro
    (31, 12), -- Procesadores Ryzen 7
    (41, 30), -- Mouse Logitech G Pro
    (51, 25), -- Routers TP-Link
    (61, 10), -- Impresoras Epson L3210
    (71, 3),  -- Servidor Dell R750
    (81, 100);-- Licencias Windows 11


INSERT INTO goods_receipts (purchase_order_id, received_by)
VALUES
    (1, 'Leandro R.'), -- Recepción de la orden de Intcomex
    (4, 'Carlos M.');  -- Recepción de la orden de Logitech

INSERT INTO stock_movements (product_id, type, amount, reason)
VALUES
    (1, 'IN', 15, 'Carga inicial de inventario'),
    (2, 'IN', 10, 'Entrada por Orden de Compra #2'),
    (11, 'OUT', 2, 'Venta directa a cliente corporativo'),
    (21, 'ADJUSTMENT', -1, 'Unidad dañada en bodega'),
    (41, 'IN', 30, 'Abastecimiento mensual Logitech'),
    (51, 'OUT', 5, 'Despacho a sucursal vía España');