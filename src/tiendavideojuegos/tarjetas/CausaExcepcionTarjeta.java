package tiendavideojuegos.tarjetas;

/**
 * Tipo enumerado que se puede usar para especificar las causas de la excepción
 * 
 * @author Eduardo Gómez Sánchez, ETSIT UVa.
 */
public enum CausaExcepcionTarjeta {
	/** No tiene saldo suficiente para una operación de reintegro */
	SALDO_INSUFICIENTE,
	/** Un ingreso daría lugar a un saldo de más de 100 euros */
	SALDO_EXCESIVO
}