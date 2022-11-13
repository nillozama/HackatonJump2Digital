# HackatonJump2Digital

Este reto se basa en generar varios endpoints que ayuden al equipo de front a montar un panel de analíticas sin tener que tratar los datos directamente desde allí.
Para ello hemos creado una base de datos en MySQL cargando los datos proporcionados en el reto  (a través de un POST).
También hemos creados los endpoints requeridos en las tareas.

## Arquitectura

Utilizamos una estructura básica con capa modelo entidad, servicio y controlador. También hemos creado una DTO para transmitir la información de forma independiente a nuestro modelo de datos (entidad). 
La estructura de carpetas es la siguiente:

![image](https://user-images.githubusercontent.com/3678032/201520042-c73d5162-0ff4-4b25-be35-8674d8cbc22f.png)

Hemos configurado ModelMapper para el mapeo entre la clase entidad y la clase DTO y Swagger para documentar los endpoints http://localhost:9001/swagger-ui.html. 


## End-points

Para cargar el archivo utilizamos el método POST en la siguiente dirección:

**http://localhost:9001/companies/addList**

En el body de POSTMAN, añadimos todas las empresas de golpe para cargarlas.
Una vez hecho el POST se guardarán en la base de datos MySQL (tarea 1). 

Para recibir respuesta utilizamos varios  métodos GET, según el tipo de respuesta utilizaremos una de las siguientes direcciones:

Para la tarea 2, crear un endpoint que devuelva las compañías ordenadas por tamaño:

**http:// localhost:9001/companies/orderBySize**

Para poder ordenar la lista, ya que el atributo size es un String para respetar el formato en el que venía, hemos tenido que hacer una conversión a double en la comparación reemplazando guion (-) por punto(.). De este modo comparamos los dígitos antes del guion sin afectar a los datos que se envían.

Para la tarea 3, crear un endpoint que devuelva las compañías ordenadas por fecha de creación:

**http:// localhost:9001/companies/orderByFoundedData**

Para la tarea 4, crear un endpoint que devuelva los siguientes datos: Número de empresas que hay en cada industria, Número de empresas que hay por cada rango de tamaños, Número de empresas que hay en cada año de creación:

**http:// localhost:9001/companies/getData**

Para poder enviar todos los datos solicitados hemos creado HashMaps con cada uno de los campos de industria, rango tamaños y año de creación (hemos creado una lista dinámica con cada uno de estos campos) y hemos generado el valor del número de empresas de cada uno.

A continuación mostramos una captura de Swagger con los enpoints.

![image](https://user-images.githubusercontent.com/3678032/201520095-cf1b4b5b-9ada-42af-a2e2-f5af038e8888.png)

