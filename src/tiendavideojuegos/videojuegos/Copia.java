package tiendavideojuegos.videojuegos;

import tiendavideojuegos.constantes.IConstantesTiendaVideojuegos;

/**
 * 
 * @author iss031
 */

public class Copia {
	
	private String idCopia;
	/* Inicializamos el estado de la copia*/
	private EstadoCopia estado = EstadoCopia.DISPONIBLE;
	private Videojuego videojuego;

	
	/**
	 * Constructor que crea una instancia de esta clase recibiendo como parámetros todos los atributos
	 * 
	 * @param idCopia El identificador de la copia
	 * @param videojuego El videojuego al que corresponde la copia
	 */
	public Copia(String idCopia,Videojuego videojuego){//Constructor de la clase copia
		this.idCopia= idCopia;
		this.videojuego=videojuego;
	}
	
	/**
	 * Devuelve la descripción/videojuego que corresponde a la copia
	 * @return el videojuego al que corresponde la copia
	 */
	public Videojuego getVideojuego() {
		return videojuego;
	}

	/**
	 * Cambia el estado de la copia
	 * @param estado El nuevo estado de la copia
	 */
	public void setEstado(EstadoCopia estado) {
		this.estado = estado;
	}


	/**
	 * Devuelve la ficha breve de la copia
	 * @return la ficha breve de la copia
	 */
	public String verFichaBreve() {
		return "Copia " + idCopia + " en estado " + estado;
	}

	/**
	 * Devuelve el estado de la copia
	 * @return el estado de la copia
	 */
	public EstadoCopia getEstado() {
		return estado;
	}

	/**
	 * Devuelve el identificador de la copia
	 * @return el identificador de la copia
	 */
	public String getIdCopia() {
		return idCopia;
	}

	/**
	 * Visualiza la ficha completa de la copia
	 * @return la ficha completa de la copia
	 */
	public String verFichaCopiaCompleta() {
		
		//Según el main, la ficha completa y breve son iguales, luego devuelve lo mismo
		return "Copia " + idCopia + " en estado " + estado;
	}

	/**
	 * Modifica el estado de la copia y crea el concepto
	 * @param numDias El número de días del alquiler
	 * @return el concepto
	 */
	public String crearAlquilerCopia(int numDias) {
		estado = EstadoCopia.ALQUILADO;
		return "Alquiler de " +idCopia + ": '" + videojuego.verInfo() + " por " + numDias + " días" ;
	}

	/**
	 * Calcula la multa del alquiler
	 * @param difDias La diferencia entre la fecha de devolución establecida y la actual
	 * @return la cantidad que con la cual hay que multar
	 */
	public float calcularMulta(long difDias) {
		// TODO Auto-generated method stub
		return difDias*videojuego.getCosteAlquiler()*IConstantesTiendaVideojuegos.RATIO_MULTA;
	}
	
}
