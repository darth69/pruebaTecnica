# Prueba Tecnica

Prueba Tecnica basada en Spring Boot y Java 1.8

Se aplican patron Factory para dejarlisto para poder implementar distintas formas de procesar los datos.

El fichero de configuracion tiene las siguiente variables.

-logger.type = Tipo de Looger a usar para el registros de eventos
-detector.type = Tipo de detector a utilizar para el procesamiento de los datos
-detector.ip.storage.path = Ruta donde se almacenaran los ficheros de apoyo para el proceso en el caso de usar DETECTORFROMFILE
-detector.ip.max.limit = Limite superior en segundos para dar alerta de intrusion
-detector.ip.min.limit = Limite inferior en segundos para dar alerta de intrusion
-detector.ip.retry.limit = Limite de fallos para validar como Intrusión
