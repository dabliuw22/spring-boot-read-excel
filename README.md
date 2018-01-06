# Spring Boot - Multiples Insert con Fichero xlsx Subido al Servidor

Se creara un proyecto spring-boot, donde podremos subir ficheros en los diferentes formatos de excel, los cuales deberan llevar un formato de dos columnas con encabezado, de este fichero se realizaran multiples insert a la base
de datos, los aspectos a tener en cuenta son los siguientes:

1. Uso de la dependencia de apache poi:
```[xml]
<dependency>
  <groupId>org.apache.poi</groupId>
  <artifactId>poi-ooxml</artifactId>
  <version>3.17</version>
</dependency>
```
2. Crear un fichero con extensión .xlsx (Excel 2007 en adelante) con el siguiente formato:
| Nombre   | Codigo |
|----------|--------|
| Iphone X | XXXX   |
| PS4      | JUYT   |

3. La logica para el recorrido se encuentra en el Servicio *ReadExcelFileService*.
4. Correr el proyecto y verificar la base de datos.