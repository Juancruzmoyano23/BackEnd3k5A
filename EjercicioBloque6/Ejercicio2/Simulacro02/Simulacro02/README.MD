# Guía de Ejercicios Semana 6 - Simulacro de Parcial 2

## Enunciado

A continuación se presenta un enunciado Tipo Parcial para práctica y entrenamiento. Tratamos de ser lo más fieles posible a lo que será el enunciado de parcial más allá que evidentemente cambiará el dominio de problema y eso puede generar algún cambio o situación diferente a las que aquí se presentan.

### Datos:

Usted dispondrá del archivo `Tracks_Data.txt` que puede descargar desde el enunciado. Dicho archivo es un archivo de texto separado por pipes `|` (se utilizan pipes debido a que hay columnas que tienen atributos separados por comas), dicho archivo contiene los datos del convenio de Tracks adquiridos por una radio en forma de lista de Tracks con sus datos y tiene la siguiente estructura:

TRACK_ID (INT): Número de identificación del track, podría obviarse para generar una nueva clave. 
NAME(TEXT): Nombre del track. 
ALBUM(TEXT): Álbum al que pertenece el track. 
ARTIST_NAME(TEXT): Artista / Artistas intérpretes del Track, es una lista separada por comas de los artistas identificados por nombre. 
COMPOSER(TEXT): Compositor / Compositores del track, es una lista separada por comas de los compositores identificados por nombre. 
MILLISECONDS(INT): duración del track. 
GENRE(TEXT): Género del track, una cadena con la identificación de un Género asociado al track. 
MEDIA_TYPE(TEXT): tipo de codificación del track, una cadena con la identificación del tipo de codificación del track.

A partir de los datos mencionados usted debe importar estos a un modelo de objetos en memoria que debería responder al siguiente diagrama de clases:

![Diagrama de clases](https://yuml.me/37ae39f6.png)

Estas clases además deberían responder a los siguientes requisitos mínimos:

1. Todas las clases deben implementar los atributos para manejar las relaciones que se presentan en el diagrama.
2. Todas las clases deben implementar constructores y métodos de acceso a los atributos teniendo en cuenta lo que puede o no puede cambiarse en las entidades de dominio.
3. Todas las clases deben implementar algún mecanismo de comparación de acuerdo con las necesidades del modelo.
4. Todas las clases deben implementar el método `toString` para mostrar los elementos básicos de la clase.
5. Los métodos de acceso a los milisegundos en la clase `Track` deben permitir interactuar hacia afuera de la clase en términos de minutos, segundos y fracciones de segundo.
6. La clase `Album` debe gestionar y mantener la duración total del álbum y debe exponer la misma en términos de horas, minutos, segundos y fracciones de segundo.

### Requerimientos:

1. Cargar todos los datos de todos los tracks en la colección de tracks, asociando cada track a los objetos necesarios y teniendo en cuenta que cada uno de los objetos asociados debe existir una y solo una vez en la memoria.
   En este punto lo ideal sería utilizar varias estructuras de datos para mantener el esquema de objetos en memoria de forma tal de no repetir instancias de los objetos relacionados como autores, compositores, etc.
2. Determinar e informar la cantidad de tracks importados y la duración total en horas, minutos y segundos de la lista completa de tracks importada.
3. Generar un archivo de texto, separado por comas con la lista de géneros y la cantidad de tracks de cada uno de los géneros.
4. Mostrar por pantalla la lista de Álbumes ordenados alfabéticamente incluyendo la cantidad de tracks y la duración total del álbum.
5. Cargar todos los datos montados en memoria en una base de datos utilizando JPA.

### Base de datos:

- Usted puede organizar la estructura de la base de datos según crea pertinente, teniendo en cuenta que las tablas deben representar el modelo de objetos y que todos los datos importados deben estar cargados y accesibles en la base de datos.
- El modelo de datos montado debe permitir al menos las consultas de tracks, tracks por Álbum y tracks por Género.
- También debería ser posible consultar la lista de tracks interpretados o compuestos por un Artista.
