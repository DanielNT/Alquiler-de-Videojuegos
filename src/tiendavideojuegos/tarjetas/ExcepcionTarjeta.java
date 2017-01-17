package tiendavideojuegos.tarjetas;

/**
 * Clase de excepción que se lanzará cuando se intente realizar una operación no permitida con tiendavideojuegos.tarjetas
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public class ExcepcionTarjeta extends Exception {

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El concepto usado en la operación que ha dado lugar a la excecpción */
	private String concepto;
	/** La causa de la excecpción */
	private CausaExcepcionTarjeta causa;

	/**
	 * Constructor que permite crear una excepción al operar sobre tiendavideojuegos.tarjetas
	 * 
	 * @param causa la causa de la excepción
	 * @param concepto el concepto usado en la operación que ha causado la excepción
	 */
	public ExcepcionTarjeta(CausaExcepcionTarjeta causa, String concepto) {
		super();
		// Asigna los parámetros
		this.causa = causa;
		this.concepto = concepto;
	}

	/**
	 * Método que devuelve el concepto que dio lugar a esta excepción
	 * 
	 * @return el concepto
	 */
	public String getConcepto() {
		return concepto;
	}

	/**
	 * Método que devuelve la causa que dio lugar a esta excepción
	 * 
	 * @return la causa
	 */
	public CausaExcepcionTarjeta getCausa() {
		return causa;
	}

}
