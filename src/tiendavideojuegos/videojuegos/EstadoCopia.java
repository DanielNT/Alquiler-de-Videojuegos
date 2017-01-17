package tiendavideojuegos.videojuegos;

/**
 * Tipo enumerado que lista los 3 estados de la copia
 * 
 * @author iss031
 */
public enum EstadoCopia{
	
	/** La copia está disponible **/
	DISPONIBLE,
	/** La copia está alquilada y no se puede alquilar hasta que esté en DISPONIBLE **/
	ALQUILADO,
	/** La copia está reservada **/
	RESERVADO
}

