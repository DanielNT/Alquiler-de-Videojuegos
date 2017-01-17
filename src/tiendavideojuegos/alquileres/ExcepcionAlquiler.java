package tiendavideojuegos.alquileres;

/**
 * @author iss031
 *
 */
public class ExcepcionAlquiler extends Exception{

	/** Las clases que derivan de Exception deben tener un atributo como este. */
	private static final long serialVersionUID = 1L;
	/** El concepto usado en la operaci�n que ha dado lugar a la excepci�n */
	private String loginSocio;
	private String idCopia;
	/** La causa de la excecpci�n */
	private CausaExcepcionAlquiler causa;

	/**
	 * Constructor que permite crear una excepci�n al operar sobre tiendavideojuegos.tarjetas
	 * 
	 * @param causa la causa de la excepci�n
	 * @param loginSocio el identificador del socio usado en la operaci�n que ha causado la excepci�n
	 * @param idCopia el identificador de la copia usada en la operaci�n que ha causado la excepci�n
	 */
	public ExcepcionAlquiler(CausaExcepcionAlquiler causa, String loginSocio,String idCopia) {
		super();//Constructor de Excepcionalquiler
		
		this.causa = causa;
		this.loginSocio = loginSocio;
		this.idCopia = idCopia;
	}
    //getters para elaborar las excepciones
	/**
	 * Devuelve el login del socio
	 * @return el loginSocio
	 */
	public String getLoginSocio() {
		return loginSocio;
	}

	/**
	 * Devuelve el identificador de la copia
	 * @return el idCopia
	 */
	public String getIdCopia() {
		return idCopia;
	}

	/**
	 * Devuelve la causa de la excepci�n en el alquiler
	 * @return la causa
	 */
	public CausaExcepcionAlquiler getCausa() {
		return causa;
	}
	
	

	

}
