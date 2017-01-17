package tiendavideojuegos.videojuegos;

/**
 * @author iss031
 *
 */
public class ExcepcionVideojuego extends Exception{

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	
	/** Atributos */
	private String idVideojuego;
	private String idCopia;
	
	/** La causa de la excepción */
	private CausaExcepcionVideojuego causa;
	
	/**
	 * Constructor que permite crear una excepción al operar sobre videojuegos
	 * 
	 * @param causa La causa por la cual se ha producido la excepción
	 * @param idVideojuego El identificador del videojuego usado en la operación que ha causado la excepción
	 * @param idCopia El identificador de la copia usada en la operación que ha causado la excepción
	 */
	public ExcepcionVideojuego(CausaExcepcionVideojuego causa, String idVideojuego, String idCopia) {
		super();
		
		this.causa = causa;
		this.idVideojuego = idVideojuego;
		this.idCopia = idCopia;
	}
	
	/**
	 * Devuelve el identificador del videojuego
	 * @return el id del videojuego
	 */
	public String getIdentificador() {
		return idVideojuego;
	}
	
	/**
	 * Devuelve el identificador de la copia
	 * @return el id de la copia
	 */
	public String getIdCopia() {
		return idCopia;
	}

	/**
	 * Devuelve la causa de la excepción
	 * @return la causa
	 */
	public CausaExcepcionVideojuego getCausa() {
		return causa;
	}


}
