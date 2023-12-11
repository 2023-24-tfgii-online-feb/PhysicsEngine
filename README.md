# TFG - Motor de físicas basado en Java

Trabajo de Fin de Grado - Motor de físicas basando en Java (Spring + React)
## Autor: 
Daniel Meruelo Monzón
## Tutor: 
Dr. Alejandro Merino Gómez
## Universidad:
Universidad de Burgos (UBU) - España

## Descripción:

Motor de físicas basado en Java que ofrece una representación visual en un cliente Web utilizando Vite+React de la 
simulación calculada por el backend, que está basado en Java/Spring.

En la simulación, existen varios tipos de "cuerpos" (entidades) las cuales interactúan antes sí basándose en principios 
tales como la atracción gravitacional, resolución de colisiones, cálculo de trayectorias...

Actualmente, existen dos tipos de cuerpos:

    * Planetas: entidades inicialmente estáticas, dotadas de gran masa y que ejercen grandes
                atracciones gravitacionales sobre el resto de cuerpos.
    * Asteroides: entidades dotadas de una velocidad inicial aleatoria, de menor masa que los 
                    planetas, que provocan una multitud de colisiones sobre el resto de los cuerpos.
    * Aeronaves: agentes que se mueven autonomámente, aplicando comportamientos y estrategias de guiado
                    con el objetivo de esquivar el resto de cuerpos.



