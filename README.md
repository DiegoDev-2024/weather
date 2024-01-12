# Weather Controller API

Este proyecto expone una API REST para obtener datos climatológicos utilizando el controlador `WeatherController`.

## Descripción

El controlador `WeatherController` proporciona dos endpoints para obtener información climatológica:

1. **Obtener Clima**
    - **Endpoint:** `POST /api/v1/weather`
    - **Descripción:** Devuelve datos climatológicos de una ubicación basada en latitud y longitud.
    - **Parámetros de entrada:** Se espera un objeto JSON en el cuerpo de la solicitud que contiene información de latitud y longitud (`WeatherRequest`).
    - **Respuesta exitosa (código 200):** Devuelve un objeto JSON con los datos climatológicos (`WeatherResponse`).
    - **Posibles códigos de respuesta:**
        - 400: Bad Request - Parámetros incorrectos
        - 401: Unauthorized - Solicitud sin autorización
        - 404: Not Found - Datos o recursos no encontrados
        - 503: Service Unavailable - Excepciones no controladas

2. **Obtener Lista de datos en base de datos**
    - **Endpoint:** `GET /api/v1/weather/history`
    - **Descripción:** Devuelve datos climatológicos consultados y guardados en la Base de Datos.
    - **Respuesta exitosa (código 200):** Devuelve una lista de objetos JSON con datos climatológicos (`List<WeatherResponse>`).
    - **Posibles códigos de respuesta:**
        - 400: Bad Request - Parámetros incorrectos
        - 401: Unauthorized - Solicitud sin autorización
        - 404: Not Found - Datos o recursos no encontrados
        - 503: Service Unavailable - Excepciones no controladas

## Tecnologías Utilizadas

- Java 17
- Spring Boot 3
- Swagger/OpenAPI para documentación de la API

## Ejecución del Proyecto

1. Asegúrate de tener instalado Java y Gradle.
2. Clona este repositorio.
3. Abre el proyecto en tu IDE preferido.
4. Ejecuta la aplicación.

## Documentación de la API

La documentación de la API se genera automáticamente y está disponible en Swagger UI. Puedes acceder a la documentación en `http://localhost:8080/swagger-ui.html` después de ejecutar la aplicación.


## Uso de la Imagen Docker

### Descargar la Imagen

La imagen Docker para esta aplicación está disponible en Docker Hub. Puedes descargarla utilizando el siguiente comando:

```bash
docker pull diegobsc/weather-app
```

Para levantar la imagen docker con el siguiente comando
```bash
docker run -p 8080:8080 diegobsc/weather-app:latest
```


## Obtener Token de Acceso

Para interactuar con la API de Weather, necesitas obtener un token de acceso mediante el servidor de autorización de Auth0.

### Pasos:

1. Realiza una solicitud POST al siguiente endpoint del servidor de autorización de Auth0:

   ```bash
   curl --request POST \
     --url https://dev-j5vfj6e0b62cd0tm.us.auth0.com/oauth/token \
     --header 'content-type: application/json' \
     --data '{
       "client_id": "nMW5y4SXzmz4FRty054dBrzesN8mxJMD",
       "client_secret": "8yp3E2LoS57Ti02HkJEYEpnnAOZlhyEVNUTCOl-rT7gYkjegHwL-3azDhl9i9kpg",
       "audience": "https://weatherApi",
       "grant_type": "client_credentials"
     }'
   ```
2. La respuesta incluirá un token de acceso. Asegúrate de guardar este token de manera segura, ya que lo necesitarás para autenticar tus solicitudes a la API.

3. En la raíz del proyecto, encontrarás el archivo `Test-Backend.postman_collection.json`. Puedes importar este archivo en Postman para acceder a un conjunto predefinido de solicitudes y realizar pruebas en la API de manera fácil y rápida.

   Para importar la colección en Postman, sigue estos pasos:

   - Abre Postman y haz clic en el botón "Import".
   - Selecciona la opción "Import File" y elige el archivo `Test-Backend.postman_collection.json` desde la ubicación donde descargaste el proyecto.
   - La colección ahora estará disponible en tu interfaz de Postman, permitiéndote explorar y ejecutar diferentes solicitudes directamente.

   Este conjunto de solicitudes predefinidas facilitará tu interacción con la API durante el proceso de desarrollo y pruebas.

**Nota:** Asegúrate de tener instalado cURL o utiliza tu herramienta preferida para realizar solicitudes HTTP **(Postman)**.

¡Gracias por utilizar la API de Weather Controller! Si tienes alguna pregunta o problema, no dudes en contactarnos.