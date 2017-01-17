package tiendavideojuegos.arranque;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import tiendavideojuegos.tarjetas.ExcepcionTarjeta;
import tiendavideojuegos.usuarios.ControladorUsuarios;
import tiendavideojuegos.usuarios.ExcepcionUsuario;
import tiendavideojuegos.usuarios.MetodoMensajeria;


/**
 * Clase con un main() de pruebas para la iteración 0, entregada por el profesor.
 *
 */
public class PruebasTiendaVideojuegos0 {

	/**
	 * Método main(). No se esperan parámetros.
	 * @param args parámetros en línea de comandos, pero se ignoran.
	 */
	public static void main(String[] args) {
		//Crea una instancia de controlador de usuarios
		ControladorUsuarios cu = new ControladorUsuarios();

		////////////////////////////////////////////////////////
		// CASOS DE USO EN ESCENARIOS DE ÉXITO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 0 - ESCENARIOS DE ÉXITO");
		System.out.println("===============================================");

		try {
			//Creo un formateador de fechas para crear dos fechas auxiliares con valores concretos
			SimpleDateFormat formateador = new SimpleDateFormat("dd/mm/yy");
			Date nacimiento1 = formateador.parse("01/01/01");
			Date nacimiento2 = formateador.parse("02/02/02");
			Date nacimiento3 = formateador.parse("03/03/03");
			
			// Caso de uso "crear socio"
			System.out.println("\nCreo tres socios, con identificadores 'edugom', 'mperez' y 'nadie'");
			cu.crearSocio("edugom", "miclave", "Eduardo", "Gómez Sánchez", nacimiento1, "00000000A", "666666666", "edugom@tel.uva.es", MetodoMensajeria.SMS);
			cu.crearSocio("mperez", "suclave", "María", "Pérez Juárez", nacimiento2, "11111111B", "000000000", "mperez@tel.uva.es", MetodoMensajeria.CORREO);
			cu.crearSocio("nadie", "ooooooo", "Ninguna", "Persona", nacimiento3, "22222222C", "666888888", "nobody@tel.uva.es", MetodoMensajeria.SMS);

			// Función de listar, que forma parte de los casos de uso "ver socio", "modificar socio" y "borrar socio"
			System.out.println("\nListo los socios");
			List<String> listado = cu.listarSocios();
			for(String s : listado) {
				System.out.println(s);
			}

			//Caso de uso "ver socio"
			System.out.println("\nMuestro los datos completos del socio con identificador 'mperez'");
			String ficha = cu.mostrarSocio("mperez");
			System.out.println(ficha);

			//Caso de uso "modificar socio"
			System.out.println("\nModifico el nombre y el teléfono del socio con identificador 'mperez'");
			cu.modificarSocio("mperez", "suclave", "María Ángeles", "Pérez Juárez", nacimiento2, "11111111B", "666777777", "mperez@tel.uva.es", MetodoMensajeria.CORREO);

			//Caso de uso "ver socio"
			System.out.println("\nVuelvo a mostrar los datos completos del socio con identificador 'mperez', para comprobar la modificación");
			ficha = cu.mostrarSocio("mperez");
			System.out.println(ficha);

			//Caso de uso "borrar socio"
			System.out.println("\nElimino al socio con identificador 'nadie'");
			cu.eliminarSocio("nadie");

			// Función de listar, que forma parte de los casos de uso "ver socio", "modificar socio" y "borrar socio"
			System.out.println("\nListo los socios de nuevo, para comprobar la eliminación");
			listado = cu.listarSocios();
			for(String s : listado) {
				System.out.println(s);
			}

			// Caso de uso "ingresar dinero en tarjeta"
			System.out.println("\nIngreso diversas cantidades en las tarjetas de los usuarios");
			cu.realizarIngresoEnTarjetaSocio("edugom", (float)50.00);
			cu.realizarIngresoEnTarjetaSocio("mperez", (float)80.00);

			// Caso de uso "listar movimientos de tarjeta de socio"
			System.out.println("\nListo los movimientos de la tarjeta del socio con identificador 'edugom'");
			listado = cu.listarMovimientosTarjetaSocio("edugom");
			for(String s : listado) {
				System.out.println(s);
			}

			//También mostramos el saldo (podría ser parte del CU anterior, o un CU distinto)
			System.out.println("\nMuestro el saldo de la tarjeta del socio con identificador 'edugom'");
			float saldo = cu.verSaldoTarjetaSocio("edugom");
			System.out.println("Saldo:" + String.format("%.2f", saldo));
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
		// CASOS DE USO EN ESCENARIOS DE FALLO
		////////////////////////////////////////////////////////	
		System.out.println("===============================================");
		System.out.println("PRUEBAS DE LA ITERACIÓN 0 - ESCENARIOS DE FALLO");
		System.out.println("===============================================");

		try {
			// Caso de uso "crear socio": se intenta crear un usuario con un identificador que ya existe
			System.out.println("\nIntento crear un socio con identificador 'edugom'");
			cu.crearSocio("edugom", "cni", "Francisco Nicolás", "Gómez Iglesias", new Date(), "33333333D", "666999999", "nicolas@cni.es", MetodoMensajeria.SMS);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}
		
		try {
			//Caso de uso "ver socio": se intenta mostrar un socio que no existe
			System.out.println("\nIntento mostrar los datos del socio identificador 'nadie'");
			String ficha = cu.mostrarSocio("nadie");
			System.out.println(ficha);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}
		
		try {
			//Caso de uso "modificar socio": se intenta modificar un socio que no existe
			System.out.println("\nIntento modificar los datos del socio identificador 'nadie'");
			cu.modificarSocio("nadie", "xxx", "yyy", "zzz", new Date(), "01234567Z", "666123456", "secreto@tel.uva.es", MetodoMensajeria.SMS);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}

		try {
			//Caso de uso "borrar socio": se intenta borrar un socio que no existe
			System.out.println("\nIntento eliminar al socio con identificador 'nadie'");
			cu.eliminarSocio("nadie");
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}

		try {
			// Caso de uso "ingresar dinero en tarjeta": se intenta ingresar en la tarjeta de alquien que no existe
			System.out.println("\nIntento ingresar una cantidad al socio con identificador 'nadie'");
			cu.realizarIngresoEnTarjetaSocio("nadie", (float)10.00);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} catch (ExcepcionTarjeta et) {
			//Si se llega hasta aquí alguna operación con tiendavideojuegos.tarjetas ha ido mal
			System.out.println("Ha fallado una operación sobre al realizar un movimiento con concepto '" + et.getConcepto() + "', por la siguiente causa: " + et.getCausa().toString());
		}


		try {
			// Caso de uso "ingresar dinero en tarjeta": se intenta ingresar tal cantidad de dinero que se supera el límite
			System.out.println("\nIntento ingresar 200 euros al socio con identificador 'edugom'");
			cu.realizarIngresoEnTarjetaSocio("edugom", (float)200.00);
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		} catch (ExcepcionTarjeta et) {
			//Si se llega hasta aquí alguna operación con tiendavideojuegos.tarjetas ha ido mal
			System.out.println("Ha fallado una operación sobre al realizar un movimiento con concepto '" + et.getConcepto() + "', por la siguiente causa: " + et.getCausa().toString());
		}

		try {
			// Caso de uso "listar movimientos de tarjeta de socio": se intentan listar los movimientos de alguien que no existe
			System.out.println("\nIntento listar los movimientos de la tarjeta del socio con identificador 'nadie'");
			List<String> listado = cu.listarMovimientosTarjetaSocio("nadie");
			for(String s : listado) {
				System.out.println(s);
			}
		} catch (ExcepcionUsuario eu) {
			//Si se llega hasta aquí alguna operación con usuarios ha ido mal
			System.out.println("Ha fallado una operación sobre el usuario con identificador '" + eu.getLogin() + "', por la siguiente causa: " + eu.getCausa().toString());
		}
	}
}
