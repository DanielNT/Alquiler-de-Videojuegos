package tiendavideojuegos.videojuegos;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase Videojuego, forma abreviada de la clase del DCD Descripci�nVideojuego
 * 
 * @author iss031
 */

public class Videojuego {

	private String idVideojuego;
	private String nombre;
	private String autor;
	private String consola;
	private Year year;
	private GeneroVideojuego genero;
	private int edadMinRecomendada;
	private float costeAlquiler;
	private int copiasTotales=0;
	private int copiasDisponibles=0;
	
	//Puesto que los videojuegos (descripciones) contienen las copias, es necesario una 
	//lista de objetos tipo Copia asociados
	private Map<String,Copia> listaCopias;
	//Definimos un mapa de las copias
	
	/**
	 * Constructor que crea una instancia de esta clase recibiendo como par�metros todos los atributos
	 * 
	 * @param idVideojuego El id del videojuego
	 * @param nombre El titulo del videojuego
	 * @param autor El creador del videojuego
	 * @param consola La consola del videojuego
	 * @param year El a�o del videojuego
	 * @param genero El g�nero del videojuego
	 * @param edadMinRecomendada La edad m�nima recomendada para el videojuego
	 * @param costeAlquiler El coste que tiene alquilarlo
	 */
	
	public Videojuego(String idVideojuego, String nombre, String autor,String consola, Year year, GeneroVideojuego genero,int edadMinRecomendada, float costeAlquiler) {
		super();
		this.idVideojuego = idVideojuego;
		this.nombre = nombre;
		this.autor = autor;
		this.consola = consola;
		this.year = year;
		this.genero = genero;
		this.edadMinRecomendada = edadMinRecomendada;
		this.costeAlquiler = costeAlquiler;
		
		//Un HashMap para la lista de las copias
		listaCopias = new HashMap<String, Copia>();
	}

	
	//GETTERS y SETTERS
	/**
	 * Devuelve el n�mero de copias disponibles
	 * @return el n�mero de copias con estado DISPONIBLE
	 */
	public int getCopiasDisponibles() {
		return copiasDisponibles;
	}

	/**
	 * Cambia el n�mero de copias disponibles
	 * @param copiasDisponibles El nuevo n�mero de copias disponibles
	 */
	public void setCopiasDisponibles(int copiasDisponibles) {
		this.copiasDisponibles = copiasDisponibles;
	}

	/**
	 * Devuelve el n�mero de copias totales
	 * @return el n�mero de copias totales
	 */
	public int getCopiasTotales() {
		return copiasTotales;
	}

	
	/**
	 * Cambia el n�mero de copias disponibles
	 * @param copiasTotales El nuevo n�mero de copias totales
	 */
	public void setCopiasTotales(int copiasTotales) {
		this.copiasTotales = copiasTotales;
	}

	/**
	 * Devuelve el id del videojuego
	 * @return el id del videojuego
	 */
	public String getIdVideojuego() {
		return idVideojuego;
	}

	/**
	 * Cambia el id del videojuego
	 * @param idVideojuego
	 */
	public void setIdVideojuego(String idVideojuego) {
		this.idVideojuego = idVideojuego;
	}

	/**
	 * Devuelve el t�tulo del videojuego
	 * @return el nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Cambia el titulo del videojuego
	 * @param nombre El nuevo nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Devuelve la compa��a que ha realizado el videojuego
	 * @return el autor
	 */
	public String getAutor() {
		return autor;
	}

	/**
	 * Cambia el autor por el introducido
	 * @param autor El nuevo autor
	 */
	public void setAutor(String autor) {
		this.autor = autor;
	}

	/**
	 * Devuelve la plataforma del videojuego
	 * @return la consola
	 */
	public String getConsola() {
		return consola;
	}

	/**
	 * Cambia la consola para la cual es compatible el videojuego
	 * @param consola La nueva consola
	 */
	public void setConsola(String consola) {
		this.consola = consola;
	}

	/**
	 * Devuelve el a�o de salida al mercado
	 * @return el a�o de salida
	 */
	public Year getYear() {
		return year;
	}

	/**
	 * Cambia el a�o de salida del videojuego
	 * @param year El nuevo a�o de aparici�n del videojuego
	 */
	public void setYear(Year year) {
		this.year = year;
	}

	/**
	 * Devuelve el tipo de g�nero del videojuego
	 * @return el g�nero
	 */
	public GeneroVideojuego getGenero() {
		return genero;
	}

	/**
	 * Cambia el tipo de g�nero del videojuego
	 * @param genero El nuevo g�nero del videojuego
	 */
	public void setGenero(GeneroVideojuego genero) {
		this.genero = genero;
	}

	/**
	 * Devuelve la edad m�nima recomendada del videojuego
	 * @return la edad m�nima recomendada
	 */
	public int getEdadMinRecomendada() {
		return edadMinRecomendada;
	}

	/**
	 * Cambia la edad m�nima recomendada
	 * @param edadMinRecomendada La nueva edad m�nima recomendada
	 */
	public void setEdadMinRecomendada(int edadMinRecomendada) {
		this.edadMinRecomendada = edadMinRecomendada;
	}

	/**
	 * Devuelve el coste del alquiler
	 * @return el coste del alquiler
	 */
	public float getCosteAlquiler() {
		return costeAlquiler;
	}

	/**
	 * Cambia el coste del alquiler al dado
	 * @param costeAlquiler El nuevo coste que cuesta alquilarlo
	 */
	public void setCosteAlquiler(float costeAlquiler) {
		this.costeAlquiler = costeAlquiler;
	}

	
	/**
	 * Lista las copias del videojuego
	 * @return la lista de copias de videojuego
	 * @throws ExcepcionVideojuego Si no se han podido listar las copias
	 */
	public List<String> listarCopiasVideojuego() throws ExcepcionVideojuego{
		//Creamos una lista auxiliar para listar las copias de un videojuego
		List<String> listado = new ArrayList<String>();
		
		if(listaCopias.size()==0){//Si no hay ninguna copia, se lanza excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_HAY_COPIAS, idVideojuego,null);
		}
		/* Se recorre la lista de copias de un videojuego y se devuelve una lista de todas 
		 * las copias disponibles de un determinado videojuego*/
		for (Copia copia : listaCopias.values()) {
			String ficha= copia.verFichaBreve();
			listado.add(ficha);
		}
		
		return listado;
	}
	

	/**
	 * Devuelve la ficha completa del Videojuego
	 * @return la ficha completa
	 */
	public String verFichaCompleta() {
		
		//Muestra por pantalla la ficha completa
		String id= "\nIdentificador: " + idVideojuego;
		String titulo = "\nTitulo: " + nombre;
		String creador= "\nCreador: " + autor;
		String plataforma= "\nConsola: " + consola;
		String tipo= "\nG�nero: " + genero.toString();
		String edadMin= "\nEdad m�nima: " + edadMinRecomendada;
		String precioDia= "\nPrecio diario: " + costeAlquiler;
		String cpTotales= "\nCopias totales: " + copiasTotales;
		String cpDisponibles= "\nCopias disponibles: " + copiasDisponibles;
		// se devuelve los elementos de la ficha
		return id + titulo + creador + plataforma + tipo + edadMin + precioDia + cpTotales + cpDisponibles;
	}


	/**
	 * Devuelve la ficha breve del videojuego
	 * @return la ficha breve
	 */
	public String verFichaBreve() {//Muestra por pantalla la ficha breve (a la hora de listar)
		return idVideojuego + ": '" + nombre + "' para " + consola;
	}
	
	/**
	 * Devuelve algunos detalles del videojuego. M�s reducido que verFichaBreve
	 * @return la ficha breve
	 */
	public String verInfo() {//Muestra por pantalla la ficha breve (a la hora de listar)
		return nombre + "' para " + consola;
	}

	/**
	 * Devuelve una ficha de una copia de un videojuego
	 * @param idCopia El identificador de la copia
	 * @return La ficha del videojuego
	 * @throws ExcepcionVideojuego Si la copia no existe
	 */
	public String verCopiaVideojuego(String idCopia) throws ExcepcionVideojuego {
		
		Copia copia= listaCopias.get(idCopia);//Buscamos en el mapa el objeto copia con idCopia
		
		if (copia!=null){//Si no existe la copia, invocar a ver ficha completa
			return copia.verFichaCopiaCompleta();
		}
		else{//Si no se encuentra la copia  se a�ade una excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_COPIA,idVideojuego,idCopia);
		}
	}

	/**
	 * Crea una copia y la a�ade a la lista de copias
	 */
	public void crearCopia() {//A�ade una copia a una descripci�n de un videojuego
		/* Se obtiene el n�mero de copias existentes incrementando en una unidad a las que ya hab�an*/
		copiasTotales= copiasTotales +1;
		copiasDisponibles= copiasDisponibles +1;
		// se a�ade el numero de copia para formar el idCopia
		String idCopia= idVideojuego + "-" + copiasTotales;
		// se crea una instancia copia y se le a�ade el estado(una nueva copia siempre tiene el estado DISPONIBLE)
		Copia copia= new Copia(idCopia,this);
		listaCopias.put(idCopia, copia);//Invocamos al costructor para crear la copia
		
	}


	/**
	 * Obtiene la copia con el identificado <code>idCopia</code>
	 * 
	 * @param idCopia El identificador de la copia
	 * @return la copia
	 * @throws ExcepcionVideojuego Si la copia no existe
	 */
	public Copia obtenerCopia(String idCopia) throws ExcepcionVideojuego {
		//obtener una copia de la lista a partir de su idCopia
		Copia copia= listaCopias.get(idCopia);
		
		if (copia!=null){
			//Si la copia se ha detectado, no hay problema
			return copia;
		}
		else{// Si la copia no se detecta, se env�a una excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_COPIA, idVideojuego,idCopia);
		}
	}

}
