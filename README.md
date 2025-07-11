# Calculadora de Pedidos

Este proyecto es un ejercicio guiado para aprender pruebas unitarias con JUnit 5, uso de mocks con Mockito, y automatización de pruebas con GitHub Actions.

## 📋 Descripción

La aplicación simula una calculadora de pedidos que puede:
- Calcular el total de un pedido aplicando descuentos
- Aplicar diferentes tarifas de envío (normal o express)
- Manejar códigos de descuento desde un repositorio simulado

## 🛠️ Tecnologías Utilizadas

- **Java 17** (compatible con versiones superiores)
- **Maven** - Gestión de dependencias y construcción
- **JUnit 5** - Framework de pruebas unitarias
- **Mockito** - Framework para crear mocks y simular dependencias
- **GitHub Actions** - CI/CD para automatización de pruebas

## 📁 Estructura del Proyecto

```
calculadora-pedidos/
├── src/
│   ├── main/java/com/ejemplo/
│   │   ├── PedidoService.java           # Servicio principal
│   │   └── DescuentoRepository.java     # Repositorio de descuentos
│   └── test/java/com/ejemplo/
│       ├── PedidoServiceTest.java       # Pruebas básicas
│       ├── PedidoServiceMockTest.java   # Pruebas con mocks
│       └── DescuentoRepositoryTest.java # Pruebas del repositorio
├── .github/workflows/
│   └── ci.yml                          # Configuración CI/CD
├── pom.xml                             # Configuración Maven
└── README.md                           # Este archivo
```

## 🚀 Cómo Ejecutar

### Prerrequisitos
- Java 17 o superior
- Maven 3.6 o superior

### Comandos Principales

```bash
# Compilar el proyecto
mvn compile

# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas de una clase específica
mvn test -Dtest=PedidoServiceTest

# Generar el JAR
mvn package

# Limpiar y compilar
mvn clean compile
```

## 🧪 Tipos de Pruebas

### Pruebas Unitarias Básicas (`PedidoServiceTest`)
- Validación de cálculos sin dependencias externas
- Pruebas de diferentes escenarios de descuentos y envío

### Pruebas con Mocks (`PedidoServiceMockTest`)
- Simulación del repositorio de descuentos
- Verificación de interacciones con dependencias
- Manejo de casos edge (valores null, negativos)

### Pruebas de Integración (`DescuentoRepositoryTest`)
- Verificación del comportamiento del repositorio
- Validación de códigos de descuento válidos e inválidos

## 📊 Ejemplos de Uso

### Cálculo Básico
```java
PedidoService service = new PedidoService();
double total = service.calcularTotal(100.0, false, false);
// Resultado: 110.0 (100 + 10 de envío normal)
```

### Con Código de Descuento
```java
PedidoService service = new PedidoService();
double total = service.calcularTotal(100.0, "PROMO10", true);
// Resultado: 110.0 (100 - 10% + 20 de envío express)
```

## 🔧 CI/CD con GitHub Actions

El proyecto incluye automatización con GitHub Actions que:

- ✅ Ejecuta pruebas en Java 17 y 21
- ✅ Cachea dependencias Maven para acelerar builds
- ✅ Genera reportes de cobertura
- ✅ Compila y empaqueta la aplicación
- ✅ Guarda artefactos (JAR files)

### Badges de Estado

Agrega estos badges a tu README después de subir a GitHub:

```markdown
![CI Status](https://github.com/tu-usuario/calculadora-pedidos/workflows/CI%20-%20Pruebas%20Unitarias/badge.svg)
![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue)
```

## 📚 Conceptos Aprendidos

1. **Pruebas Unitarias**: Validación de unidades individuales de código
2. **Mocking**: Simulación de dependencias externas
3. **Test-Driven Development**: Escribir pruebas antes que el código
4. **Integración Continua**: Automatización de pruebas en cada commit
5. **Cobertura de Código**: Medición de qué porcentaje del código está probado

## 🤝 Contribuir

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agregar nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para detalles.

## 👨‍💻 Autor

Ejercicio guiado creado para el curso de DevOps - Módulo 4, Clase 3.
