package tiendavideojuegos.videojuegos;

/**
 * Tipo enumerado que se puede usar para especificar las causas de la excepción
 * 
 * @author iss031
 *
 */

public enum CausaExcepcionVideojuego {
	/** La descripción que se intenta crear tiene un idVideojuego ya en uso **/
	YA_EXISTE_DESCRIPCION,
	/** No existe esa descripción**/
	NO_EXISTE_DESCRIPCION,
	/** No existe esa copia**/
	NO_EXISTE_COPIA,
	/** La copia no está disponible**/
	COPIA_NO_DISPONIBLE,
	/** No hay copias**/
	NO_HAY_COPIAS,
}