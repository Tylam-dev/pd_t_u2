# Ambiensa S.A. – Persistencia con JPA (Tarea 2, Unidad 2)

Aplicación de ejemplo de **Jakarta Persistence (JPA) con Hibernate** para una inmobiliaria (Ambiensa S.A.).
Usa Spring Boot, SQLite y un frontend sencillo con Thymeleaf. Materia: Persistencia de Datos – Universidad Politécnica Salesiana.

## Modelo
Siete entidades: `Proyecto`, `TipoVilla`, `Villa`, `Servicio`, `Cliente`, `Vendedor` y `Reserva`.

| Relación | Tipo |
|---|---|
| Proyecto – Villa | 1 a N |
| TipoVilla – Villa | 1 a N |
| Villa – Servicio | N a N (tabla `villa_servicio`) |
| Villa – Reserva | 1 a N |
| Cliente – Reserva | 1 a N |
| Vendedor – Reserva | 1 a N |

## Requisitos
- JDK 21
- Maven 3.9 o superior
- Conexión a Internet la primera vez (descarga de dependencias)

No hay que instalar ninguna base de datos: SQLite crea el archivo `ambiensa.db` en la carpeta del proyecto.

## Cómo ejecutarlo
```bash
git clone git@github.com:Tylam-dev/pd_t_u2.git
cd pd_t_u2
mvn spring-boot:run
```
Luego abre <http://localhost:8080>.

También se puede generar el JAR y ejecutarlo:
```bash
mvn -DskipTests package
java -jar target/ambiensa-jpa-1.0.0.jar
```

Al iniciar, la base se **recrea** (`spring.jpa.hibernate.ddl-auto=create`) y `DataLoader` inserta datos de ejemplo: 2 proyectos, 2 tipos de villa, 5 villas, 3 servicios, 2 clientes, 2 vendedores y 3 reservas.

## Qué muestra la página
1. **SELECT con parámetros nombrados:** villas de un proyecto con precio máximo (formulario editable).
2. **JOIN FETCH:** villas disponibles con tipo, proyecto y servicios en una sola consulta (evita el problema N+1).
3. **UPDATE masivo:** reajuste porcentual de precios de un proyecto (formulario; muestra las filas actualizadas).
4. **JOIN FETCH de reservas:** reserva con villa, cliente y vendedor.
5. **Agregación GROUP BY:** número de reservas y monto total por vendedor.
6. **Ciclo de vida:** `CicloVidaDemo` recorre los estados *new*, *managed*, *detached* y *removed*.

Las consultas JPQL están en `VillaRepository` y `ReservaRepository`. En la consola se imprime el SQL que genera Hibernate (`spring.jpa.show-sql=true`).

## Estructura
```
src/main/java/ec/ambiensa/
  model/   entidades JPA
  repo/    repositorios con consultas JPQL (@Query)
  web/     controlador y demostración del ciclo de vida
  DataLoader.java   datos de ejemplo
src/main/resources/
  application.properties
  templates/index.html
```
