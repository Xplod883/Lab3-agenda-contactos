Desarrollar una aplicación de escritorio que permita gestionar una lista de contactos. La aplicación debe permitir agregar, eliminar, modificar y mostrar contactos, así como guardar y cargar la lista de contactos desde un archivo.

Cómo ejecutar la aplicación

Requisitos del sistema:
Java Development Kit (JDK) 8 o superior instalado

Sistema operativo: Windows, macOS o Linux

Pasos para compilar y ejecutar:
1. Guardar los archivos Contacto.java y GestorContactos.java en la misma carpeta
2. Abrir una terminal o línea de comandos en esa carpeta

Compilar los archivos Java ejecutando:
AgendaContactos.java, Contacto.java, GestorContactos.java

Ejecutar la aplicación con:
java GestorContactos

Uso de la aplicación:
-Agregar contacto: Complete los campos "Nombre" y "Teléfono" y haga clic en "Agregar"
-Modificar contacto: Seleccione un contacto de la lista, modifique los campos y haga clic en "Modificar"
-Eliminar contacto: Seleccione un contacto de la lista y haga clic en "Eliminar"
-Los contactos se guardan automáticamente al cerrar la aplicación

Características de la implementación
-Interfaz intuitiva: Diseño limpio con agrupación lógica de componentes.
-Validación de datos: Verifica que los campos no estén vacíos antes de agregar o modificar.
-Confirmación de eliminación: Pide confirmación antes de eliminar un contacto.
-Persistencia de datos: Los contactos se guardan en un archivo de texto y se cargan al iniciar la aplicación.
-Manejo de errores: Captura y muestra errores de forma amigable al usuario.
