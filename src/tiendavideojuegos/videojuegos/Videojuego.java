package tiendavideojuegos.videojuegos;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase Videojuego, forma abreviada de la clase del DCD DescripciónVideojuego
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
	 * Constructor que crea una instancia de esta clase recibiendo como parámetros todos los atributos
	 * 
	 * @param idVideojuego El id del videojuego
	 * @param nombre El titulo del videojuego
	 * @param autor El creador del videojuego
	 * @param consola La consola del videojuego
	 * @param year El año del videojuego
	 * @param genero El género del videojuego
	 * @param edadMinRecomendada La edad mínima recomendada para el videojuego
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
	 * Devuelve el número de copias disponibles
	 * @return el número de copias con estado DISPONIBLE
	 */
	public int getCopiasDisponibles() {
		return copiasDisponibles;
	}

	/**
	 * Cambia el número de copias disponibles
	 * @param copiasDisponibles El nuevo número de copias disponibles
	 */
	public void setCopiasDisponibles(int copiasDisponibles) {
		this.copiasDisponibles = copiasDisponibles;
	}

	/**
	 * Devuelve el número de copias totales
	 * @return el número de copias totales
	 */
	public int getCopiasTotales() {
		return copiasTotales;
	}

	
	/**
	 * Cambia el número de copias disponibles
	 * @param copiasTotales El nuevo número de copias totales
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
	 * Devuelve el título del videojuego
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
	 * Devuelve la compañía que ha realizado el videojuego
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
	 * Devuelve el año de salida al mercado
	 * @return el año de salida
	 */
	public Year getYear() {
		return year;
	}

	/**
	 * Cambia el año de salida del videojuego
	 * @param year El nuevo año de aparición del videojuego
	 */
	public void setYear(Year year) {
		this.year = year;
	}

	/**
	 * Devuelve el tipo de género del videojuego
	 * @return el género
	 */
	public GeneroVideojuego getGenero() {
		return genero;
	}

	/**
	 * Cambia el tipo de género del videojuego
	 * @param genero El nuevo género del videojuego
	 */
	public void setGenero(GeneroVideojuego genero) {
		this.genero = genero;
	}

	/**
	 * Devuelve la edad mínima recomendada del videojuego
	 * @return la edad mínima recomendada
	 */
	public int getEdadMinRecomendada() {
		return edadMinRecomendada;
	}

	/**
	 * Cambia la edad mínima recomendada
	 * @param edadMinRecomendada La nueva edad mínima recomendada
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
		
		if(listaCopias.size()==0){//Si no hay ninguna copia, se lanza excepción
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
		String tipo= "\nGénero: " + genero.toString();
		String edadMin= "\nEdad mínima: " + edadMinRecomendada;
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
	 * Devuelve algunos detalles del videojuego. Más reducido que verFichaBreve
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
		else{//Si no se encuentra la copia  se añade una excepción
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_COPIA,idVideojuego,idCopia);
		}
	}

	/**
	 * Crea una copia y la añade a la lista de copias
	 */
	public void crearCopia() {//Añade una copia a una descripción de un videojuego
		/* Se obtiene el número de copias existentes incrementando en una unidad a las que ya habían*/
		copiasTotales= copiasTotales +1;
		copiasDisponibles= copiasDisponibles +1;
		// se añade el numero de copia para formar el idCopia
		String idCopia= idVideojuego + "-" + copiasTotales;
		// se crea una instancia copia y se le añade el estado(una nueva copia siempre tiene el estado DISPONIBLE)
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
		else{// Si la copia no se detecta, se envía una excepción
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_COPIA, idVideojuego,idCopia);
		}
	}

}
