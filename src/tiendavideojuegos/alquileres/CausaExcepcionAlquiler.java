package tiendavideojuegos.alquileres;

/**
 * Tipo enumerado que especifica las causas de la excepci�n.
 * Para concordar con la main de prueba, reutiliza valores del enum de otras Excepciones
 * 
 * @author iss031
 */

/*Incluimos todas las excepciones que puede haber en el alquiler.
 * Se podr�a a�adir directamente los catch en el main, pero en vista de que en la siguiente iteraci�n
 * ser�a necesario volverlo a hacer, se simplifica as�:
 * */

public enum CausaExcepcionAlquiler {
	/** La copia con idCopia introducida para alquilar no existe**/
	COPIA_NO_EXISTE,
	/** El socio no existe**/
	SOCIO_NO_EXISTE, 
	/** La descripci�n no existe**/
	NO_EXISTE_DESCRIPCION,
	/** El socio no ha realizado alquileres**/
	NO_HAY_ALQUILERES_DEL_SOCIO, 
	/** El socio no tiene saldo para alquilar**/
	SOCIO_SIN_SALDO_PARA_ALQUILER, 
	/** La copia no est� disponible**/
	COPIA_NO_DISPONIBLE, 
	/** El socio no tiene saldo para pagar la multa**/
	SOCIO_SIN_SALDO_PARA_MULTA,
	/** El alquiler no est� vigente o no existe**/
	ALQUILER_NO_EXISTE,
	/** Se ha superado el l�mite de alquileres**/
	SUPERADO_LIMITE_ALQUILERES,  
	
}
