package tiendavideojuegos.videojuegos;

/**
 * Tipo enumerado que lista los 3 estados de la copia
 * 
 * @author iss031
 */
public enum EstadoCopia{
	
	/** La copia est� disponible **/
	DISPONIBLE,
	/** La copia est� alquilada y no se puede alquilar hasta que est� en DISPONIBLE **/
	ALQUILADO,
	/** La copia est� reservada **/
	RESERVADO
}

