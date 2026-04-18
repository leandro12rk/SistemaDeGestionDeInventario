--Data Base Inventory

DROP TABLE IF EXISTS stock_movements;
DROP TABLE IF EXISTS goods_receipts;
DROP TABLE IF EXISTS inventory;

DROP TABLE IF EXISTS stock_movements;
DROP TABLE IF EXISTS goods_receipts;
DROP TABLE IF EXISTS inventory;

-- Tabla maestra de inventario
CREATE TABLE inventory
(
    id           SERIAL PRIMARY KEY,
    product_id   INTEGER NOT NULL UNIQUE, -- ID que viene del microservicio de productos
    sku          VARCHAR(50) NOT NULL UNIQUE, -- COLUMNA PARA EL FRONTEND (Ej: LAP-MAC-M2-001)
    quantity     INTEGER NOT NULL DEFAULT 0 CHECK (quantity >= 0),
    location     VARCHAR(100), -- Opcional: Para saber en qué estante/bodega está
    last_updated TIMESTAMP        DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de recepción de mercancía
CREATE TABLE goods_receipts
(
    id                SERIAL PRIMARY KEY,
    purchase_order_id INTEGER      NOT NULL,
    received_date     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    received_by       VARCHAR(100) NOT NULL
);

/*
El campo type (La lógica de movimiento)
IN (Entrada): Representa mercancía que ingresa a tu bodega. Aumenta la cantidad total en tu tabla inventory.

OUT (Salida): Representa mercancía que sale de tu bodega (ventas, despachos a sucursales, devoluciones a proveedores). Disminuye la cantidad total.

ADJUSTMENT (Ajuste): Se usa para corregir discrepancias. Si el sistema dice que tienes 10, pero físicamente cuentas 9, haces un ajuste de -1.
 */


-- Movimientos detallados (Kardex)
CREATE TABLE stock_movements
(
    id               SERIAL PRIMARY KEY,
    -- RELACIÓN: Vinculamos el movimiento directamente al registro de inventario
    inventory_id     INTEGER NOT NULL REFERENCES inventory (id) ON DELETE CASCADE,
    goods_receipt_id INTEGER REFERENCES goods_receipts (id),
    type             VARCHAR(10) CHECK (type IN ('IN', 'OUT', 'ADJUSTMENT')),
    amount           INTEGER NOT NULL,
    reason           TEXT,
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO inventory (product_id, sku, quantity, location)
VALUES
    (1,  'LAP-MAC-M2-001', 15,  'Rack A-10'),
    (2,  'LAP-DELL-XPS-13', 8,   'Rack A-12'),
    (11, 'MON-LG-4K-27', 20,  'Bodega Central'),
    (21, 'SSD-SAM-980P-1T', 50,  'Gaveta Electrónicos'),
    (31, 'CPU-RYZ-7-5800', 12,  'Caja Fuerte'),
    (41, 'MOU-LOG-GPRO', 30,  'Rack B-05'),
    (51, 'NET-TPL-AX50', 25,  'Rack C-01'),
    (61, 'PRN-EPS-L3210', 10,  'Pasillo 4'),
    (71, 'SRV-DELL-R750', 3,   'Cuarto de Servidores'),
    (81, 'SW-WIN-11-PRO', 100, 'Digital / Oficina'),
    -- Nueva Data
    (91, 'GPU-RTX-4080', 5,    'Caja Fuerte'),
    (101,'RAM-COR-32GB', 40,   'Gaveta Electrónicos'),
    (111,'KEY-RAZ-BW-V4', 15,  'Rack B-06');




-- Registramos las recepciones primero
INSERT INTO goods_receipts (purchase_order_id, received_by)
VALUES
    (1, 'Leandro R.'), -- Recepción inicial
    (4, 'Carlos M.'),  -- Pedido Logitech
    (5, 'Ana L.'),     -- Pedido Componentes varios
    (6, 'Leandro R.'); -- Pedido urgente GPUs




INSERT INTO stock_movements (inventory_id, goods_receipt_id, type, amount, reason)
VALUES
    -- Entradas iniciales (Relacionadas a Inventory ID)
    (1, 1, 'IN', 15, 'Carga inicial - MacBook'),
    (6, 2, 'IN', 30, 'Abastecimiento mensual Logitech'),

    -- Movimientos de ventas y despachos (OUT)
    (3, NULL, 'OUT', 2, 'Venta Factura #501'),
    (7, NULL, 'OUT', 5, 'Transferencia a Sucursal Vía España'),
    (11, 4, 'IN', 5, 'Compra de reposición - Stock crítico'),

    -- Ajustes de inventario (ADJUSTMENT)
    (4, NULL, 'ADJUSTMENT', -1, 'Unidad dañada en manejo de bodega'),
    (12, NULL, 'ADJUSTMENT', 2, 'Encontrado en inventario cíclico'),

    -- Más movimientos variados
    (2, 3, 'IN', 10, 'Recepción parcial Orden #4'),
    (9, NULL, 'OUT', 1, 'Uso interno para desarrollo'),
    (13, 3, 'IN', 15, 'Entrada de teclados Razer');