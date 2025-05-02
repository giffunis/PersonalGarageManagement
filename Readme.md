# APP móvil para gestión de vehículos

Trabajo Final  
Proyecto asignatura Dispositivos Móviles.  
Máster Universitario en Ingeniería Informática - Universidad Pablo de Olavide  

## Descripción general

Aplicación móvil nativa para Android API 28 que permite gestionar vehículos, de forma remota, gracias al uso de un [servicio externo](https://github.com/giffunis/PGM.WebApi) desarrollado para esta aplicación.  
Esta app ha sido diseñada para cumplir con los requisitos del Trabajo Final de la asignatura de Dispositivos Móviles del Master Universitario en Ingeniería Informática de la Universidad Pablo de Olavide.

## Manual de usuario

[Breve manual de usuario](docs/MANUAL.md)

## Requisitos del trabajo final

**Requisitos obligatorios**

| Requisito | Cumplimiento |  
| --------- | ------------ |  
| Varias activities (mas de 3) | Se han implementado 4 Activities: Login Activity, GarageActivity, SettingsActivity y VehicleActivity.  |  
| Variedad de componentes en la vista (Elementos que hereden de View) | Recycle View, Cuadros de diálogo, Fragments, etc. |  
| Al menos un método de Persistencia | Se utilizan 2 métodos de persistencia: La base de datos SQLite y las Shared Preferences. |  
| Notificaciones | Se utilizan dos tipos de notificaciones: Las Toast y las Snackbar. |  
| Acceso a Servicios Web y tratamiento de ficheros de intercambio de datos: XML / Json | Se consumen dos servicios: Las imágenes de las marcas de los vehículos, [Link](https://github.com/giffunis/car-logos-dataset) ; y un servicio externo [Link](https://github.com/giffunis/PGM.WebApi) desarrollado para que esta aplicación. El tipo de fichero de intercambio y procesado es, Json. Se hace uso de librerías externas como [Volley](https://github.com/google/volley) y [Gson](https://github.com/google/gson) |

**Requisitos opcionales**

| Requisito | Cumplimiento |  
| --------- | ------------ |  
| Menús | Existen 2 menú de opciones en dos activities (GarageActivity y VehicleActivity) |  
| Sharing entre apps | Se comparten, los datos del vehículo seleccionado en formato texto, a través de la App de Gmail u otra que acepte dicho formato |  
| Sensores | Cámara (necesaria para sacar fotos al vehículo) |  
