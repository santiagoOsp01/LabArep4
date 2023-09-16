# Laboratorio 4 AREP
construir un servidor Web (tipo Apache) en Java. El servidor debe ser capaz de entregar páginas html e imágenes tipo PNG. Igualmente el servidor debe proveer un framework IoC para la construcción de aplicaciones web 
a partir de POJOS. Usando el servidor se debe construir una aplicación Web de ejemplo.

en este caso se realiso una aplicacion donde se puede consultar y poner notas dado el nombre

el git que se trabajo en clase es en el siguiente link https://github.com/santiagoOsp01/minispring

## Iniciando

### Prerrequisitos

* Git
* Java
* Maven
* 1 IDE (en este proyecto se trabajo en intellij)

### Instalando el proyecto

Lo primero será traer del repositorio remoto que encontramos en git
a nuestro entorno local para eso ejecutamos el siguiente comando en
la terminal donde funcionen los comandos git

```
git clone https://github.com/santiagoOsp01/labArep3.git
```

Esto creará un repositorio localdonde accederemos y ejecutaremos 
con nuestro IDE escogido y el siguiente comando.

```
mvn package
```

luego de ejecutar el comando si estamos en intellij vamos a construir el proyecto presionando 
este boton de martillo verde que podemos ver en la siguiente imagen:

![](photos/img_8.png)

luego de que ya lo tengamos vamos a ejecutar la clase principal en este caso es serverHttp que esta 
en edu.eci.arep.Front y en esta clase corremos el metodo main, 
para que corra el laboratorio

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/a48d74d7-fb10-43f9-9791-4d2b58fbb74b)

Eso hará que ya este disponible nuestra aplicacion. Para corroborar pega y copia o presiona
la siguiente url http://localhost:35000/home.html.

entrando al link podemos ver a continuacion la siguiente pagina
con la cual podemos llamar a la funciones del endpoint /hello
con post y get:

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/fe46fd83-d21a-4f93-aaee-a981be693f8d)

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/a00e943c-0136-4250-84dd-b8b834b4f588)

aqui en la terminal vemos como hacen los pedidos post y get

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/b89c1256-dc4a-4ca6-92b3-d52a10bc5069)

tambien lo podemos ejecutar directamente las funciones lambda en la siguiente url
http://localhost:35000/hello?v=John

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/3da7f449-24ad-49ba-b74f-7848ab024bc7)

## Corriendo los tests

en este laboratorio se realizaron dos tipos de prueba funcionales y unitarias

con el siguiente comnando ejecutamos la pruebas unitarias

```
mvn test
```
Al pasar todos los test debe verse alto parecido a lo siguiente.

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/e53e28e5-a64d-4d52-8b43-3626e5dc1875)

luego realisamos las pruebas funcionales para ver si esta cumple con el requisito del laboratorio

vamos a primero probar con html:

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/5fd4254a-5031-4076-85ca-8ad679a8e649)

css:

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/3f3c5bc5-48a2-4213-90f8-33731508bcdf)

js:

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/797f6f10-d55b-4960-a98e-7b9eb040c038)

imagenes jpg:

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/ba887c7f-9aeb-4621-b934-649b59e020e8)

imagenes png:

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/d8205915-31ed-4933-b15e-d43a1613eca4)



tambien cuando un archivo o endpoint no existe nos direcciona a la siguiente pagina:

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/e7fa9c7c-53b3-47b8-9344-412136fa3595)

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/375424fe-db26-4d90-b015-0da8ceb680da)

y si no manejamos un formato nos lleva a lo siguiente:

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/e7c53bd4-08b3-4332-96ef-1f8d9d2ea08a)

## Documentación
Primero debemos ejecutar el siguiente comando para crear la documentación.
```
mvn javadoc:javadoc
```
En la siguiente ruta desde nuestra carpeta del proyecto podemos encontrar la documentación.

```
./target/site/apidocs/
```
Si ingresamos a esta podemos ver que hay un index.html que al abrir nos mostrara la siguiente pagina.

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/65f60700-dbda-4bae-900c-0fbb7996895b)

### Extensibilidad

como podemos ver en este proyecto tenemos una clase abstracta que es file
y despues la implementamos ya sea si son imagenes o texto, poreso si queremos
agregar un nuevo tipo de archivo solo debemos de crear la clase para estos archivos
sin la necesidad de tener que cambiar gran cosa en nuestro codigo, incluso el agregar
un nuevo formato de imagenes

tambien como ya tenemos un anotation de componentes cuando queramos agregar nuesvas funcionalidad
solo utilizamos esta, y podemos poner mas anotacion que podemos tener getMapping y Postmapping, podemos crear con
facilidad un putMapping

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/334b0d18-b48c-434a-9969-461e47e34439)

### Arquitectura

* front: en este contenemos nuestro servidor http que es el controlador
de toda nuestra arquitectura la que usa al fileController y las funciones lambdas
* fileController: es el encargador de manejar todos los tipos de archivos
que pueda manejar nuestro servidor
* miniSpring: es el encargado de hacer la reflexion y manejar las anotacion, para que funcione correctamente
el esta encargado de los postMapping y getMapping, tambien es el encargado de cargar los componentes y sus metodos
* ejemploAppSpring: es una implementacion simple donde se manejan un post y get de las calificaciones que puede tener un alumno


### Modularización
como ya lo mencionamos anteriormente tenemos dos clases que son responsable
de los diferentes formatos para imagenes y para texto, la cual cada una
tiene una responsabilidad unica:

* textController: contiene todo lo necesario para manejar los formatos de
texto que nos pidieron html css js

* imgController: contiene todo lo necesario para manejar los formatos de
  imagenes que codificamos en nuestro servidor que en este caso son 
  jpg, png, jpeg y gif


tambien tenemos el miniSpring que maneja diferentes notaciones dependiendo de lo que queramos

*getMapping
*PostMapping

## Patrones

* El patrón de diseño corresponde al patrón Singleton, el cual se implementó, ya que dentro de nuestra app solo tiene una instancia y una sola instacia de persistencia

### Apilication

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/ad71bb27-a3a1-499f-a3e3-55b8cb30c4b8)

como podemos observar es una aplicacion muy sencilla para demostrar el uso de nuestro miniSpring

cuando entramos en el link( http://localhost:35000/home.html ) nos sale lo siguiente:

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/ad30d3f7-44ec-40f0-9650-68e50a845d47)

ahorra si no le hemos echo un post si le damos el get nos sale lo siguiente

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/933322e2-baa7-41e0-86cb-5a7da0070d07)

para que tenga una nota tenemos que hacer el post primero

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/ba7f2a76-f990-49ec-98ac-8957547bdac5)

y ahorra si podemos ver como jorge ya tiene su calificacion

![image](https://github.com/santiagoOsp01/LabArep4/assets/111186366/0d3f5f74-8371-41aa-b6c2-2222a3ee726c)

## Construido con

* [Maven](https://maven.apache.org/) - Administrador de dependencias
* [OMDAPI](https://www.omdbapi.com) - API externa de consulta

## Version

1.0-SNAPSHOT

## Autores

Santiago Ospina Mejia

## Licencia

GNU General Public License family

## Agradecimientos

* Luis Daniel Benavides profesor de AREP
