package tiendavideojuegos.arranque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tiendavideojuegos.alquileres.ControladorAlquileres;
import tiendavideojuegos.alquileres.ExcepcionAlquiler;
import tiendavideojuegos.premios.ControladorPremios;
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
public class PruebasTiendaVideojuegos2 {

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
		//Crea una instancia de controlador de premios
		ControladorPremios cp = new ControladorPremios(cu, ca);

		////////////////////////////////////////////////////////
		// CASOS DE USO PREVIOS
		////////////////////////////////////////////////////////	
		try {
			//Creo un formateador de fechas para crear dos fechas auxiliares con valores concretos
			SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
			Date nacimiento1 = formateador.parse("09/05/2001");
			Date nacimiento2 = formateador.parse("02/02/2002");

			// Caso de uso "crear socio"
			cu.crearSocio("edugom", "miclave", "Eduardo", "Gómez Sánchez", nacimiento1, "00000000A", "666666666", "edugom@tel.uva.es", MetodoMensajeria.SMS);
			cu.crearSocio("mperez", "suclave", "María", "Pérez Juárez", nacimiento2, "11111111B", "666777777", "mperez@tel.uva.es", MetodoMensajeria.CORREO);

			// Caso de uso "ingresar dinero en tarjeta"
			cu.realizarIngresoEnTarjetaSocio("edugom", (float)50.00);
			cu.realizarIngresoEnTarjetaSocio("mperez", (float)5.00);

			// Caso de uso "crear descripción de videojuego"
			cvj.crearDescripcionVideojuego("11111111", "Pro Evolution Soccer 2015", "Konami", "XBox One", Year.of(2014), GeneroVideojuego.DEPORTES, 3, 3);
			cvj.crearDescripcionVideojuego("22222222", "Gran Turismo", "Polyphony Digital", "PlayStation", Year.of(1997), GeneroVideojuego.SIMULACION, 0, 3);
			cvj.crearDescripcionVideojuego("33333333", "Dragon Ball: Raging Blast", "Spike", "XBox 360", Year.of(2010), GeneroVideojuego.ACCION, 12, 5);
			cvj.crearDescripcionVideojuego("44444444A", "Minecraft", "Mojang AB", "Wii U", Year.of(2011), GeneroVideojuego.ESTRATEGIA, 7, 2);
			cvj.crearDescripcionVideojuego("44444444B", "Minecraft", "Mojang AB", "PlayStation 3", Year.of(2011), GeneroVideojuego.ESTRATEGIA, 7, 2);

			//Caso de uso "crear copia de videojuego"
			cvj.crearCopiaVideojuego("11111111"); 				//Dos copias del PES2015
			cvj.crearCopiaVideojuego("11111111"); 				
			cvj.crearCopiaVideojuego("33333333");				//Una copia del Dragon Ball
			cvj.crearCopiaVideojuego("44444444A"); 				//Tres copias del Mincraft para Wii
			cvj.crearCopiaVideojuego("44444444A"); 				
			cvj.crearCopiaVideojuego("44444444A");
			cvj.crearCopiaVideojuego("44444444B");				//Una copia del Mincraft para PS3
			
			//Caso de uso "alquilar videojuego"
			//NOTA: dependiendo del diseño que se haya seguido, puede ser necesario pasar al controlador el id del videojuego y el id de la copia, o sólo uno de los dos. Debe cambiarse la invocación como procedaSystem.out.println("\nEl usuario 'edugom' va a alquilar las copias '11111111-2' y '44444444A-1' durante 2 días");
			ca.crearAlquiler("edugom", "11111111", "11111111-2", 2);
			ca.crearAlquiler("edugom", "44444444A", "44444444A-1", 4);
			ca.crearAlquiler("edugom", "33333333", "33333333-1", 2);
			ca.crearAlquiler("edugom", "44444444B", "44444444B-1", 2);
			ca.crearAlquiler("mperez", "44444444A", "44444444A-2", 2);

		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} catch (ExcepcionTarjeta et) {
			//Si se llega hasta aquí alguna operación con tiendavideojuegos.tarjetas ha ido mal
			System.out.println("Ha fallado una operación sobre al realizar un movimiento con concepto '" + et.getConcepto() + "', por la siguiente causa: " + et.getCausa().toString());
		} catch (ExcepcionVideojuego evj) {
			//Si se llega hasta aquí alguna operación con videojuegos ha ido mal
			System.out.println("Ha fallado una operación sobre el videojuego con identificador '" + evj.getIdentificador() + "', por la siguiente causa: " + evj.getCausa().toString() + evj.getIdCopia()!=null ? ("(copia " + evj.getIdCopia() + ")") : "");
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		} catch (ParseException pe) {
			//Si se llega hasta aquí es que no se han podido leer las cadenas de fechas en la creación de las fechas de nacimiento
			System.out.println("Ha fallado la creación de las fechas de nacimiento");
		}

		////////////////////////////////////////////////////////
		// CASOS DE USO EN ESCENARIOS DE ÉXITO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 2 - ESCENARIOS DE ÉXITO");
		System.out.println("===============================================");

		//Variables auxiliares para tener listados y fichas
		List<String> listado;
		String ficha;
		
		/*Añadimos las fechas personalizadas*/	
		Calendar fecha = Calendar.getInstance();
		Calendar fechaTrucada = Calendar.getInstance();
		
		try {
			// Caso de uso "terminar alquiler"
			//NOTA1: dependiendo del diseño que se haya seguido, puede ser necesario pasar al controlador el id del videojuego y el id de la copia, o sólo uno de los dos. Debe cambiarse la invocación como procedaSystem.out.println("\nEl usuario 'edugom' va a alquilar las copias '11111111-2' y '44444444A-1' durante 2 días");
			
			//Añade 3 días
			fechaTrucada.add(Calendar.DAY_OF_MONTH, 3);
			
			//NOTA2: si se "C que piense que es tres días más tarde que hoy, este alquiler debería terminar en multa
			System.out.println("\nTermino el alquiler que el usuario 'edugom' tiene sobre la copia '11111111-2'");
			boolean multa = ca.terminarAlquiler("11111111-2", fechaTrucada);
			System.out.println("La terminación del alquiler ha concluido " + (multa ? "con" : "sin") + " multa");

			//Y en cambio este otro alquiler debería terminar devuelto con éxito y sin multa
			System.out.println("\nTermino el alquiler que el usuario 'edugom' tiene sobre la copia '44444444A-1'");
			multa = ca.terminarAlquiler("44444444A-1",fecha);
			System.out.println("La terminación del alquiler ha concluido " + (multa ? "con" : "sin") + " multa");

			//Caso de uso "listar alquileres socio": repetido para comprobar la terminación efectiva
			System.out.println("\nListo los alquileres del usuario 'edugom'");
			listado = ca.listarAlquileresSocio("edugom");
			for(String s : listado) {
				System.out.println(s);
			}

			//Caso de uso "aplicar políticas de premio"
			System.out.println("\nAplico las políticas de premio");
			cp.aplicarPoliticasPremio();
			
			// Caso de uso "listar movimientos de tarjeta de socio"
			System.out.println("\nListo los movimientos de la tarjeta del socio con identificador 'edugom' para ver si ha tenido premios");
			listado = cu.listarMovimientosTarjetaSocio("edugom");
			for(String s : listado) {
				System.out.println(s);
			}

			// Caso de uso "listar movimientos de tarjeta de socio"
			System.out.println("\nListo los movimientos de la tarjeta del socio con identificador 'mperez' para ver si ha tenido premios");
			listado = cu.listarMovimientosTarjetaSocio("mperez");
			for(String s : listado) {
				System.out.println(s);
			}
			
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
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
		
		
		////////////////////////////////////////////////////////
		// CASOS DE USO EN ESCENARIOS DE FALLO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 2 - ESCENARIOS DE FALLO");
		System.out.println("===============================================");

		try {
			// Caso de uso "terminar alquiler"
			System.out.println("\nIntento terminar un alquiler ya terminado");
			boolean multa = ca.terminarAlquiler("11111111-2", fecha);
			System.out.println("\nLa terminación del alquiler ha concluido " + (multa ? "con" : "sin") + " multa");
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		} 

		try {
			// Caso de uso "terminar alquiler"
			System.out.println("\nIntento terminar un alquiler de una copia que no existe");
			boolean multa = ca.terminarAlquiler("99999999-1", fecha);
			System.out.println("\nLa terminación del alquiler ha concluido " + (multa ? "con" : "sin") + " multa");
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		} 

		try {
			//Recordemos que el alquiler es por dos días, luego para trucar añadimos 3 sobre la actual
			Calendar fechaTrucada2 = Calendar.getInstance();
			fechaTrucada2.add(Calendar.DAY_OF_MONTH, 3);
			
			
			// Caso de uso "terminar alquiler"
			System.out.println("\nIntento terminar un alquiler dando lugar a una multa que no se puede pagar (trucando al sistema para que piense que llevo un día de retraso)");
			boolean multa = ca.terminarAlquiler("44444444A-2", fechaTrucada2);
			System.out.println("\nLa terminación del alquiler ha concluido " + (multa ? "con" : "sin") + " multa");
		} catch (ExcepcionAlquiler ea) {
			//Si se llega hasta aquí alguna operación con alquileres ha ido mal
			System.out.println("Ha fallado una operación sobre un alquiler del usuario '" + ea.getLoginSocio() + "' para la copia '" + ea.getIdCopia() + "', por la siguiente causa: " + ea.getCausa().toString());
		} 

		try {
			//Caso de uso "listar alquileres socio"
			System.out.println("\nListo los alquileres de un usuario que no existe");
			listado = ca.listarAlquileresSocio("nadie");
			for(String s : listado) {
				System.out.println(s);
			}
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}
	}
}
