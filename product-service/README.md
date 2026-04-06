### Microservicio de Producto
Guarda este archivo en: `/sistema-de-gestion-de-inventario/product-service/README.md`

```markdown
# Product Service 📦

Este microservicio se encarga de administrar el núcleo del inventario: Productos, Categorías y Proveedores. Es el servicio "maestro" que alimenta la información básica a los demás módulos.

## ⚙️ Configuración Técnica
* **Puerto por defecto:** `8081`
* **Base de Datos:** `db-products` (PostgreSQL)
* **Context Path:** `/api/v1`

## 🗃️ Modelo de Datos (PostgreSQL)
El servicio gestiona las siguientes entidades:
1.  **Suppliers:** Datos de proveedores (Panamá y regionales).
2.  **Categories:** Clasificación de equipos (Laptops, Redes, etc.).
3.  **Products:** Detalle técnico, SKU único y precios.

## 🔌 Endpoints Principales

### Productos
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| GET | `/api/products` | Lista todos los productos activos. |
| POST | `/api/products` | Registra un nuevo producto con SKU. |
| GET | `/api/products/{id}` | Detalle completo de un producto. |

### Proveedores & Categorías
| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| GET | `/api/suppliers` | Obtiene lista de proveedores. |
| GET | `/api/categories` | Obtiene categorías para filtros. |

## 🚀 Ejecución Individual
Asegúrate de tener el contenedor de PostgreSQL activo y ejecuta:
```bash
mvn spring-boot:run
🛠️ Variables de Entorno Requeridas
No olvides configurar tu application.properties o variables de entorno para la conexión:

DB_URL: jdbc:postgresql://localhost:5433/db_products

DB_USER: leand

DB_PASS: **********