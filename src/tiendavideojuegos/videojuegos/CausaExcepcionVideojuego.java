package tiendavideojuegos.videojuegos;

/**
 * Tipo enumerado que se puede usar para especificar las causas de la excepci�n
 * 
 * @author iss031
 *
 */

public enum CausaExcepcionVideojuego {
	/** La descripci�n que se intenta crear tiene un idVideojuego ya en uso **/
	YA_EXISTE_DESCRIPCION,
	/** No existe esa descripci�n**/
	NO_EXISTE_DESCRIPCION,
	/** No existe esa copia**/
	NO_EXISTE_COPIA,
	/** La copia no est� disponible**/
	COPIA_NO_DISPONIBLE,
	/** No hay copias**/
	NO_HAY_COPIAS,
}