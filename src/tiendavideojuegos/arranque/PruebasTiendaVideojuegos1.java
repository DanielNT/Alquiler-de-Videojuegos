package tiendavideojuegos.arranque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.List;

import tiendavideojuegos.alquileres.ControladorAlquileres;
import tiendavideojuegos.alquileres.ExcepcionAlquiler;
import tiendavideojuegos.tarjetas.ExcepcionTarjeta;
import tiendavideojuegos.usuarios.ControladorUsuarios;
import tiendavideojuegos.usuarios.ExcepcionUsuario;
import tiendavideojuegos.usuarios.MetodoMensajeria;
import tiendavideojuegos.videojuegos.ControladorVideojuegos;
import tiendavideojuegos.videojuegos.ExcepcionVideojuego;
import tiendavideojuegos.videojuegos.GeneroVideojuego;


/**
 * Clase con un main() de pruebas para la iteración 0, entregada por el profesor.
 *
 */
public class PruebasTiendaVideojuegos1 {

	/**
	 * Método main(). No se esperan parámetros.
	 * @param args parámetros en línea de comandos, pero se ignoran.
	 */
	public static void main(String[] args) {
		//Crea una instancia de controlador de usuarios
		ControladorUsuarios cu = new ControladorUsuarios();
		//Crea una instancia de controlador de videojuegos
		ControladorVideojuegos cvj = new ControladorVideojuegos();
		//Crea una instancia de controlador de alquileres
		ControladorAlquileres ca = new ControladorAlquileres(cu, cvj);

		////////////////////////////////////////////////////////
		// CASOS DE USO PREVIOS
		////////////////////////////////////////////////////////	
		try {
			//Creo un formateador de fechas para crear dos fechas auxiliares con valores concretos
			SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yy");
			Date nacimiento1 = formateador.parse("01/01/01");
			Date nacimiento2 = formateador.parse("02/02/02");

			// Caso de uso "crear socio"
			cu.crearSocio("edugom", "miclave", "Eduardo", "Gómez Sánchez", nacimiento1, "00000000A", "666666666", "edugom@tel.uva.es", MetodoMensajeria.SMS);
			cu.crearSocio("mperez", "suclave", "María", "Pérez Juárez", nacimiento2, "11111111B", "666777777", "mperez@tel.uva.es", MetodoMensajeria.CORREO);

			// Caso de uso "ingresar dinero en tarjeta"
			cu.realizarIngresoEnTarjetaSocio("edugom", (float)50.00);
			cu.realizarIngresoEnTarjetaSocio("mperez", (float)80.00);

		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} catch (ExcepcionTarjeta et) {
			//Si se llega hasta aquí alguna operación con tiendavideojuegos.tarjetas ha ido mal
			System.out.println("Ha fallado una operación sobre al realizar un movimiento con concepto '" + et.getConcepto() + "', por la siguiente causa: " + et.getCausa().toString());
		} catch (ParseException pe) {
			//Si se llega hasta aquí es que no se han podido leer las cadenas de fechas en la creación de las fechas de nacimiento
			System.out.println("Ha fallado la creación de las fechas de nacimiento");
		}

		////////////////////////////////////////////////////////
		// CASOS DE USO EN ESCENARIOS DE ÉXITO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 1 - ESCENARIOS DE ÉXITO");
		System.out.println("===============================================");

		//Variables auxiliares para tener listados y fichas
		List<String> listado;
		String ficha;

		try {
			// Caso de uso "crear descripción de videojuego"
			System.out.println("\nCreo seis descripciones de videojuegos");
			cvj.crearDescripcionVideojuego("00000000", "God of War 3", "SCE Santa Monica Studio", "PlayStation 3", Year.of(2010), GeneroVideojuego.AVENTURA, 18, 50);
			cvj.crearDescripcionVideojuego("11111111", "Pro Evolution Soccer 2015", "Konami", "XBox One", Year.of(2014), GeneroVideojuego.DEPORTES, 3, 3);
			cvj.crearDescripcionVideojuego("22222222", "Gran Turismo", "Polyphony Digital", "PlayStation", Year.of(1997), GeneroVideojuego.SIMULACION, 0, 3);
			cvj.crearDescripcionVideojuego("33333333", "Dragon Ball: Raging Blast", "Spike", "XBox 360", Year.of(2010), GeneroVideojuego.ACCION, 12, 5);
			cvj.crearDescripcionVideojuego("44444444A", "Minecraft", "Mojang AB", "Wii U", Year.of(2011), GeneroVideojuego.ESTRATEGIA, 7, 2);
			cvj.crearDescripcionVideojuego("44444444B", "Minecraft", "Mojang AB", "PlayStation 3", Year.of(2011), GeneroVideojuego.ESTRATEGIA, 7, 2);

			//Función de listar, que forma parte de los casos de uso "ver descripción de videojuego", "modificar descripción de videojuego" o "borrar descripción de videojuego"
			System.out.println("\nListo las descripciones de videojuego existentes");
			listado = cvj.listarDescripcionesVideojuego();
			for(String s : listado) {
				System.out.println(s);
			}

			// Caso de uso "ver descripción de videojuego"
			System.out.println("\nMuestro los datos completos de la descripción de videojuego con identificador '00000000'");
			ficha = cvj.verDescripcionVideojuego("00000000");
			System.out.println(ficha);

			//Caso de uso "modificar descripción de videojuego"
			System.out.println("\nModifico el género y el precio de la descripción de videojuego con identificador '00000000'");
			cvj.modificarDescripcionVideojuego("00000000", "God of War 3", "SCE Santa Monica Studio", "PlayStation 3", Year.of(2010), GeneroVideojuego.ACCION, 18, 5);

			// Caso de uso "ver descripción de videojuego" otra vez, para ver que la modificación ha tenido éxito
			System.out.println("\nMuestro de nuevo los datos completos de la descripción de videojuego con identificador '00000000'");
			ficha = cvj.verDescripcionVideojuego("00000000");
			System.out.println(ficha);

			//Caso de uso "eliminar descripción de videojuego"
			System.out.println("\nElimino la descripción de videojuego con identificador '00000000'");
			cvj.eliminarDescripcionVideojuego("00000000");

			//Listo de nuevo, para comprobar que la eliminación ha sido efectiva
			System.out.println("\nListo de nuevo las descripciones de videojuego existentes");
			listado = cvj.listarDescripcionesVideojuego();
			for(String s : listado) {
				System.out.println(s);
			}

			//Caso de uso "crear copia de videojuego"
			System.out.println("\nCreo algunas copias de videojuegos");
			cvj.crearCopiaVideojuego("11111111"); 				//Dos copias del PES2015
			cvj.crearCopiaVideojuego("11111111"); 				
			cvj.crearCopiaVideojuego("33333333");				//Una copia del Dragon Ball
			cvj.crearCopiaVideojuego("44444444A"); 				//Tres copias del Mincraft para Wii
			cvj.crearCopiaVideojuego("44444444A"); 				
			cvj.crearCopiaVideojuego("44444444A");
			cvj.crearCopiaVideojuego("44444444B");				//Una copia del Mincraft para PS3

			//Función de listar, que forma parte de los casos de uso "ver copia de videojuego", "modificar copia de videojuego" o 
			//"borrar copia de videojuego", además de casos de uso de alquilar
			System.out.println("\nListo las copias de videojuego existentes para la descripción con identificador '44444444A'");
			listado = cvj.listarCopiasVideojuego("44444444A");
			for(String s : listado) {
				System.out.println(s);
			}

			//Caso de uso "ver copia de videojuego"
			//NOTA: dependiendo del diseño que se haya seguido, puede ser necesario pasar al controlador el id del videojuego y el id de la copia, o sólo uno de los dos. Debe cambiarse la invocación como proceda
			System.out.println("\nMuestro los datos de la copia de videojuego con identificador '44444444A-1'");
			ficha = cvj.verCopiaVideojuego("44444444A", "44444444A-1");
			System.out.println(ficha);
			
			//Caso de uso "alquilar videojuego"
			//NOTA: dependiendo del diseño que se haya seguido, puede ser necesario pasar al controlador el id del videojuego y el id de la copia, o sólo uno de los dos. 
			//NOTA: También puede ocurrir que este mensaje se diriga a otro controlador
			//NOTA: Debe cambiarse esta invocación como proceda (y todas las relacionadas)
			System.out.println("\nEl usuario 'edugom' va a alquilar las copias '11111111-2' y '44444444A-1' durante 2 días");
			ca.crearAlquiler("edugom", "11111111", "11111111-2", 2);
			ca.crearAlquiler("edugom", "44444444A", "44444444A-1", 2);
			System.out.println("\nLa usuaria 'mperez' va a alquilar la copia '44444444A-2' durante 4 días");
			ca.crearAlquiler("mperez", "44444444A", "44444444A-2", 4);
			
			//Caso de uso "listar alquileres socio"
			System.out.println("\nListo los alquileres del usuario 'edugom'");
			listado = ca.listarAlquileresSocio("edugom");
			for(String s : listado) {
				System.out.println(s);
			}


		} catch (ExcepcionVideojuego evj) {
			//Si se llega hasta aquí alguna operación con videojuegos ha ido mal
			System.out.println("Ha fallado una operación sobre el videojuego con identificador '" + evj.getIdentificador() + "', por la siguiente causa: " + evj.getCausa().toString() + evj.getIdCopia()!=null ? ("(copia " + evj.getIdCopia() + ")") : "");
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} 

		////////////////////////////////////////////////////////
		// CASOS DE USO EN ESCENARIOS DE FALLO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 1 - ESCENARIOS DE FALLO");
		System.out.println("===============================================");


		try {
			// Caso de uso "crear descripción de videojuego": se intenta crear una descripción con un identificador que ya existe
			System.out.println("\nSe intenta crear una descripción con un identificador que ya existe");
			cvj.crearDescripcionVideojuego("11111111", "La nada", "", "Nadie", Year.of(2000), GeneroVideojuego.AVENTURA, 0, 10);
		} catch (ExcepcionVideojuego evj) {
			//Si se llega hasta aquí alguna operación con videojuegos ha ido mal
			System.out.println("Ha fallado una operación sobre el videojuego con identificador '" + evj.getIdentificador() + "', por la siguiente causa: " + evj.getCausa().toString() + (evj.getIdCopia()!=null ? (" (copia " + evj.getIdCopia() + ")") : ""));
		}

		try {
			// Caso de uso "ver descripción de videojuego": se intenta mostrar una descripción con un identificador que no existe
			System.out.println("\nSe intenta mostrar una descripción que no existe");
			ficha = cvj.verDescripcionVideojuego("00000000");
			System.out.println(ficha);
		} catch (ExcepcionVideojuego evj) {
			//Si se llega hasta aquí alguna operación con videojuegos ha ido mal
			System.out.println("Ha fallado una operación sobre el videojuego con identificador '" + evj.getIdentificador() + "', por la siguiente causa: " + evj.getCausa().toString() + (evj.getIdCopia()!=null ? (" (copia " + evj.getIdCopia() + ")") : ""));
		}

		try {
			// Caso de uso "modificar descripción de videojuego": se intenta mostrar una descripción con un identificador que no existe
			System.out.println("\nSe intenta modificar una descripción que no existe");
			cvj.modificarDescripcionVideojuego("00000000", "God of War 3", "SCE Santa Monica Studio", "PlayStation 3", Year.of(2010), GeneroVideojuego.ACCION, 18, 5);
		} catch (ExcepcionVideojuego evj) {
			//Si se llega hasta aquí alguna operación con videojuegos ha ido mal
			System.out.println("Ha fallado una operación sobre el videojuego con identificador '" + evj.getIdentificador() + "', por la siguiente causa: " + evj.getCausa().toString() + (evj.getIdCopia()!=null ? (" (copia " + evj.getIdCopia() + ")") : ""));
		}

		try {
			// Caso de uso "eliminar descripción de videojuego": se intenta mostrar una descripción con un identificador que no existe
			System.out.println("\nSe intenta eliminar una descripción que no existe");
			cvj.eliminarDescripcionVideojuego("00000000");
		} catch (ExcepcionVideojuego evj) {
			//Si se llega hasta aquí alguna operación con videojuegos ha ido mal
			System.out.println("Ha fallado una operación sobre el videojuego con identificador '" + evj.getIdentificador() + "', por la siguiente causa: " + evj.getCausa().toString() + (evj.getIdCopia()!=null ? (" (copia " + evj.getIdCopia() + ")") : ""));
		}

		try {
			// Caso de uso "crear copia de videojuego": se intenta crear una copia sobre una descripción que no existe
			System.out.println("\nSe intenta crear una copia de una descripción que no existe");
			cvj.crearCopiaVideojuego("00000000"); 				
		} catch (ExcepcionVideojuego evj) {
			//Si se llega hasta aquí alguna operación con videojuegos ha ido mal
			System.out.println("Ha fallado una operación sobre el videojuego con identificador '" + evj.getIdentificador() + "', por la siguiente causa: " + evj.getCausa().toString() + (evj.getIdCopia()!=null ? (" (copia " + evj.getIdCopia() + ")") : ""));
		}

		try {
			// Caso de uso "ver copia de videojuego": se intenta mostrar una copia, pero no existe la descripción asociada
			System.out.println("\nSe intenta mostrar una copia de una descripción que no existe");
			ficha = cvj.verCopiaVideojuego("00000000", "00000000-1");
			System.out.println(ficha);
		} catch (ExcepcionVideojuego evj) {
			//Si se llega hasta aquí alguna operación con videojuegos ha ido mal
			System.out.println("Ha fallado una operación sobre el videojuego con identificador '" + evj.getIdentificador() + "', por la siguiente causa: " + evj.getCausa().toString() + (evj.getIdCopia()!=null ? (" (copia " + evj.getIdCopia() + ")") : ""));
		}

		try {
			// Caso de uso "ver copia de videojuego": se intenta mostrar una copia sobre una descripción que sí existe, pero no existe la copia
			System.out.println("\nSe intenta mostrar una copia que no existe de una descripción que sí existe");
			ficha = cvj.verCopiaVideojuego("22222222", "22222222-1");
			System.out.println(ficha);
		} catch (ExcepcionVideojuego evj) {
			//Si se llega hasta aquí alguna operación con videojuegos ha ido mal
			System.out.println("Ha fallado una operación sobre el videojuego con identificador '" + evj.getIdentificador() + "', por la siguiente causa: " + evj.getCausa().toString() + (evj.getIdCopia()!=null ? (" (copia " + evj.getIdCopia() + ")") : ""));
		}
		
		try {		
			// Caso de uso "alquilar videojuego": intenta alquilar un usuario que no existe
			System.out.println("\nEl usuario 'nadie' va a intentar alquilar");
			ca.crearAlquiler("nadie", "11111111", "11111111-1", 1);
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		}

		try {		
			// Caso de uso "alquilar videojuego": un usuario válido intenta alquilar un videojuego que no existe
			System.out.println("\nEl usuario 'edugom' va a alquilar la copia '99999999-1' de un videojuego que no existe");
			ca.crearAlquiler("edugom", "99999999", "99999999-1", 1);
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		}

		try {		
			// Caso de uso "alquilar videojuego": un usuario válido intenta alquilar una copia que no existe de un videojuego que sí
			System.out.println("\nEl usuario 'edugom' va a alquilar la copia '11111111-9', que no existe");
			ca.crearAlquiler("edugom", "11111111", "11111111-9", 1);
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		}

		try {		
			// Caso de uso "alquilar videojuego": se intenta alquilar una copia ya alquilada
			System.out.println("\nEl usuario 'edugom' va a alquilar la copia '44444444A-2', que ya está alquilada");
			ca.crearAlquiler("edugom", "44444444A", "44444444A-2", 1);
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		}
		
		try {		
			// Caso de uso "alquilar videojuego": se intenta alquilar por un importe superior al saldo del socio
			System.out.println("\nEl usuario 'edugom' va a alquilar la copia '33333333-1' durante 10 días, para lo que no tiene saldo suficiente");
			ca.crearAlquiler("edugom", "33333333", "33333333-1", 10);
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		}
		
	}
}
