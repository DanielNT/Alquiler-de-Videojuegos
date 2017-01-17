package tiendavideojuegos.tarjetas;

/**
 * @author Daniel
 *
 */
public class Multa {
	
	float cantidad;
	
	/**
	 * Constructor de la clase multa
	 * @param cantidad La cantidad de la multa
	 */
	public Multa(float cantidad){
		this.cantidad=cantidad;
	}

	/**
	 * Devuelve la cantidad de la multa
	 * @return the cantidad
	 */
	public float getCantidad() {
		return cantidad;
	}

	/**
	 * Modifica la cantidad a la introducida como parámetro
	 * @param cantidad La nueva cantidad
	 */
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}
}
