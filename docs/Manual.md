 # Personal Garage Management
 
 ## Índice
 
- [1. Acceso y Autenticación](#1-acceso-y-autenticacion).
- [2. Visualización de su garaje](#visualizacion-de-su-garaje).
- [3. Visualización, creación, modificación y borrado de un vehículo]()

 ### 1. Acceso y Autenticación
 
 Para acceder a la aplicación, se nos solicitará las credenciales de acceso. En esta primera versión, se ha almacenado un usuario local en una tabla de SQlite:

 - Username: usuario
 - Password: 123456

***Nota**: Este método de autenticación local, se ha escogido solo por motivos académicos. La autenticación en una aplicación real sería mediante JWT tokens y el almacenamiento en las SharePreferences.

En el caso de que se introduzcan credenciales erroneas, aparecerá una notificación mediante snackbar describiendo el problema.

 <img src="screenshots/login.png" width="300" height="667" alt="Login Activity"/>

### 2. Configuración de la aplicación

En el menú de la pantalla principal, en la opción `Configuración`, accederemos a la pantalla de configuración, dónde se muestran las SharedPreferences almacenadas de la aplicación:

<img src="screenshots/Menu.png" width="300" height="667" alt="Garage Activity"/> <img src="screenshots/Settings.png" width="300" height="667" alt="Settings Activity"/>

Actualmente, aparecen:

- Activación de Https.
- La Url del [servicio externo](https://github.com/giffunis/PGM.WebApi) desarrollado para esta aplicación.

<img src="screenshots/HostSettings.png" width="300" height="667" alt="Settings Activity"/>

 ### 3. Visualización de su garaje

En la pantalla principal se cargarán los vehículos que el usuario tenga registrados. El origen de dicha información es el [servicio externo](https://github.com/giffunis/PGM.WebApi) desarrollado para esta aplicación y configurado en la sección anterior.

<img src="screenshots/Garage.png" width="300" height="667" alt="Garage Activity"/>

En ella, vemos los siguientes elementos:

- Recicle View - Muestra el listado de vehículos registrados.
- BAF (Floatin Action Button) - Botón para añadir nuevos vehículos.
- Menu:
  - Refresh icon - Para actualizar el listado.
  - Settings icon - Para modificar parámetros de ajuste de la apliación.

### 4. Visualización, creación, modificación y borrado de un vehículo

Para ver, editar o eliminar un vehículo, pulsamos sobre el mismo. Para añadir uno nuevo, pulsaremos sobre el BAF correspondiente. Esto nos abrirá la pantalla de detalle del propio vehículo (Vehicle Activity), vacía en el caso de la creación.

<img src="screenshots/NewVehicle.png" width="300" height="667" alt="Vehicle Activity"/> <img src="screenshots/Vehicle.png" width="300" height="667" alt="Vehicle Activity"/>

En ella, vemos los siguientes datos:

- Imagen del vehículo.
- BAF para sacar una nueva captura.
- Matrícula.
- Marca.
- Modelo.
- Año de matriculación.

***Nota:** No se han puesto encabezados en los campos porque se han configurado los textos de sugerencia (hint text).

Como se aprecia en la imagen, existen dos botones: el botón de guardado, que almacena los cambios realizados, y el botón para eliminar. En ambos casos, aparecerá una notificación mediante Toast, con el resultado de la acción.

#### 3.1 Captura del vehículo

Si queremos sacar una foto a nuestro vehículo, pulsamos sobre el BAF correspondiente y se abrirá la cámara:

<img src="screenshots/Camera.png" width="300" height="667" alt="Camera"/>

#### 3.2 Fecha de matriculación

Para facilitar el registro de la fecha de matriculación, se ha usado un cuadro de diálogo (DatePicker).

<img src="screenshots/Calendar.png" width="300" height="667" alt="Calendar"/>

