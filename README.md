# TFG - Motor de físicas basado en Java - GII_O_MC_23.01

[![Maintainability](https://api.codeclimate.com/v1/badges/c1b194d066f24fcd4a6a/maintainability)](https://codeclimate.com/github/dmm1005/PhysicsEngine/maintainability)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/a5e2610aeed14440b8dc4b68a5134c52)](https://app.codacy.com/gh/dmm1005/PhysicsEngine/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)

Trabajo de Fin de Grado - Motor de físicas basando en Java (Spring + React)
## Autor: 
Daniel Meruelo Monzón
## Tutor: 
Dr. Alejandro Merino Gómez
## Universidad:
Universidad de Burgos (UBU) - España

## Descripción:

Motor de físicas basado en Java que ofrece una representación visual en un cliente Web (utilizando React) de la 
simulación calculada por el backend, que está basado en Java/Spring.

En la simulación, existen varios tipos de "cuerpos" (entidades) las cuales interactúan antes sí basándose en principios 
tales como la atracción gravitacional, resolución de colisiones, cálculo de trayectorias...

Actualmente, existen tres tipos de cuerpos:

    * Planetas: entidades inicialmente estáticas, dotadas de gran masa y que ejercen grandes
                atracciones gravitacionales sobre el resto de cuerpos.
    * Asteroides: entidades dotadas de una velocidad inicial aleatoria, de menor masa que los 
                    planetas, que provocan una multitud de colisiones sobre el resto de los cuerpos.
    * Aeronaves: agentes que se mueven autonomámente, aplicando comportamientos y estrategias de guiado
                    con el objetivo de esquivar el resto de cuerpos.

Se pueden seleccionar cuerpos desde la lista de cuerpos en la derecha y se mostrarán en amarillo en la simulación para una más sencilla localización.
Si se seleccionan Aeronaves, cuando el usuario haga click en un punto de la simulación, estas interrumpirán sus rutinas de movimiento para dirigirse hacia ese punto. Este proceso
de "búsqueda" solo se activa mientras se mantenga el click en la simulación. 

## Proceso de despliegue

### Docker (recomendado)

Para facilitar el despligue y probado de la aplicación, se ha provisto un fichero Dockerfile con el que se puede generar la imagen para desplegar un contenedor en el que se ejecutan tanto el motor (parte servidor) como el cliente web.

Para poder hacer uso de ello, es necesario tener instalado Docker y tener acceso a un intérprete de comandos (Powershell/Bash/CMD...).


Despues de clonar el repositorio o descargarse el Dockerfile (es independiente, hará otro clonado del repositorio dentro de la imagen, luego habrá ficheros redundantes y podréis borrar la carpeta del repositorio en vuestra máquina anfitrión.), navegar hasta el directorio que contiene el Dockerfile y ejecutar:

`docker build -t <nombre de la imagen> .` 

Donde 'nombre de la imagen' es el tag que le queréis dar a vuestra imagen.

Una vez tengáis la imagen generada, tenéis que ejecutar este comando para crear un contenedor y ejecutar el proyecto:

`docker run -P <puerto>:3100 <nombre de la imagen>`

Donde 'puerto' es el puerto de vuestra máquina anfitrión donde queréis publicar el proyecto.'nombre de la imagen' es el nombre (tag) que habéis usado en el comando anterior y 'nombre del contenedor' es el nombre que le queréis dar.

### Despliegue tradicional

En caso de que queráis hacer un despliegue tradicional, donde vais a desplegar todo en vuestra máquina anfitrión, se ha provisto de scripts (tanto .sh como .ps1).

Requerimientos:
- JDK 17 o superior.
- No es necesario tener instalado Gradle, se utiliza el wrapper.
- Node 20 LTS o superior.

Es necesario ejecutar los scripts en la raíz del proyecto.

_AVISO: Debido a un bug con Lombok, no se puede compilar el proyecto en Windows. He probado a compilar (gradle build) en Linux y todo funciona correctamente. También he comprobado que puedo compilar el proyecto con Windows si lo hago a través de IntelliJ IDEA. Cuando se solucione el bug, subiré una versión actualizada del script._

En cualquier caso, los pasos para una instalación y despliegue manual serían:
- npm install y npm build de la aplicación web.
- Copiar la build al directorio src/main/resources/static/
- gradle build de la aplicación servidor
- Arrancar la aplicación ejecutando el java -jar build/libs/TFG-1.0.0-RELEASE.jar