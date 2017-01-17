package tiendavideojuegos.usuarios;

import java.util.Date;
import java.util.List;

import tiendavideojuegos.tarjetas.ExcepcionTarjeta;
import tiendavideojuegos.tarjetas.Tarjeta;



/**
 * Clase que representa a un Socio en la casa de apuestas. Deriva de <code>Usuario</code>, añadiendo la posesión de una tarjeta y métodos relacionados con las
 * apuestas.
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public class Socio extends Usuario {
	/** Tarjeta de dinero del socio ante la tienda de alquileres */
	private Tarjeta tarjeta;

	/**
	 * Constructor que recibe como parámetros todos los campos necesarios para crear una instancia de <code>Usuario</code>
	 * 
	 * @param login el login, o identificador único de usuario
	 * @param clave la clave del usuario (en claro)
	 * @param nombre el nombre del usuario
	 * @param apellidos los apellidos del usuario
	 * @param nacimiento la fecha de nacimiento del usuario
	 * @param nif el NIF del usuario
	 * @param movil el número de teléfono del usuario
	 * @param correo la dirección de correo del usuario
	 * @param metodo el método de mensajería preferido por el usuario
	 */
	public Socio(String login, String clave, String nombre, String apellidos, Date nacimiento, String nif, String movil, String correo, MetodoMensajeria metodo) {
		// Invoca al constructor de la superclase
		super(login, clave, nombre, apellidos, nacimiento, nif, movil, correo, metodo);
		// Y crea una nueva tarjeta
		tarjeta = new Tarjeta();
	}

	/**
	 * Método que permite que se realice un ingreso en la tarjeta de este usuario
	 * @param concepto el concepto por el que se ingresa
	 * @param cantidad la cantidad que se ingresa
	 * @throws ExcepcionTarjeta si no se acepta el ingreso porque se supera el límite de saldo
	 */
	public void realizarIngreso(String concepto, float cantidad) throws ExcepcionTarjeta {
		// Pasa la petición a la tarjeta
		tarjeta.realizarIngreso(concepto, cantidad);
	}
	
	/**
	 * Método que permite que se realice un ingreso en la tarjeta de este usuario sin respetar los límites máximos
	 * @param concepto el concepto por el que se ingresa
	 * @param cantidad la cantidad que se ingresa
	 */
	public void realizarIngresoSinLimite(String concepto, float cantidad) {
		// Pasa la petición a la tarjeta
		tarjeta.realizarIngresoSinLimite(concepto, cantidad);
	}
	
	/**
	 * Método que permite que se realice un reintegro desde la tarjeta de este usuario
	 * @param concepto el concepto por el que se reintegra
	 * 
	 * @param cantidad la cantidad que se reintegra
	 * @throws ExcepcionTarjeta si el saldo de la tarjeta no es suficiente para reintegrar la <code>cantidad</code>
	 */
	public void realizarReintegro(String concepto, float cantidad) throws ExcepcionTarjeta {
		// Pasa la petición a la tarjeta
		tarjeta.realizarReintegro(concepto, cantidad);
	}

	/**
	 * Método que permite que un socio informe de su saldo
	 * @return el saldo en la tarjeta del socio
	 */
	public float verSaldo() {
		//Pasa la petición a la tarjeta
		return tarjeta.getSaldo();
	}

	/**
	 * Método que permite que un socio informe de los movimientos de su tarjeta
	 * @return una lista de cadenas, donde cada elemento corresponde a un movimiento
	 */
	public List<String> listarMovimientosTarjeta() {
		//Pasa la petición a la tarjeta
		return tarjeta.listarMovimientos();
	}

}
