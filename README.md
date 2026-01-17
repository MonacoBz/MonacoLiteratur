# 📚 MonacoLibrary - Catálogo de Libros y Autores

Bienvenido a **MonacoLibrary**, una aplicación de consola construida con **Java y Spring Boot** que permite consultar, almacenar y filtrar información sobre libros y autores clásicos consumiendo la API pública de [Gutendex](https://gutendex.com/).

## 🚀 Características Principales

Esta aplicación permite realizar las siguientes operaciones mediante un menú interactivo:

- **Búsqueda en Línea:** Consulta libros por título directamente desde la API de Gutendex.
- **Persistencia de Datos:** Almacena automáticamente los resultados de búsqueda en una base de datos local.
- **Filtrado Avanzado:**
    - Listar todos los libros y autores registrados.
    - 🕵️‍♂️ Buscar autores vivos en un año determinado (Lógica compleja de fechas).
    - 🌐 Filtrar libros por idioma (ES, EN, FR, etc.).
- **Prevención de Redundancia:** Verifica si un libro ya existe antes de guardarlo.

## 🛠️ Tecnologías y Herramientas

- **Java 17+**: Uso extensivo de *Records* y *Streams*.
- **Spring Boot 3**: Framework principal.
- **Spring Data JPA**: Para la persistencia de datos y Repositorios.
- **PostgreSQL**: Base de datos relacional (configurable).
- **Jackson**: Deserialización de datos JSON de la API.
- **Maven**: Gestión de dependencias.

## 🏗️ Arquitectura y Diseño

El proyecto sigue una arquitectura en capas, destacando el uso de **Genéricos y Polimorfismo** para mantener el código limpio y escalable.

### 1. Interfaces Genéricas (`Service Pattern`)
Se implementó un patrón de herencia de interfaces para reutilizar lógica común:
```java
// Interfaz base
public interface Service<T> {
    List<T> obtenTodo();
}

// Interfaces específicas
public interface IAutor<T> extends Service<T> {
    List<T> autoresVivos(Integer anio);
}
```
### 2. Dtos con Java Records.  
Se utilizaron records para la transferencia de datos, aprovechando su inmutabilidad y métodos toString automáticos.  
### 3. Mapeo.
Se diseñó una estrategia de mapeo manual para evitar referencias circulares entre Libro y Autor al convertirlos a DTOs.  
 - Libro contiene Autor.
 - Autor contiene una lista de Libros.
Solución: Se rompe el ciclo infinito implementando DTOs simplificados o controlando la recursión en el Mapper.

### 4. Ejemplo de uso.
Al iniciar la aplicación veras el siguiente menú.
```bash  
Bienvenido a Monaco Library, elija una opción:
1)Buscar un libro por titulo
2)Listar libros registrados
3)Listar autores registrados
4)Listar autores vivos en un determinado año
5)Listar libros por idioma
0)Salir de la aplicación
```
Solo sigue las opciones.