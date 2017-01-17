package tiendavideojuegos.videojuegos;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Clase controladora que tiene visibilidad sobre la lista de descripciones de videojuegos del sistema y se encarga de la gesti�n de la misma
 * 
 * @author iss031
 */

public class ControladorVideojuegos {

	
	private Map<String, Videojuego> listaVideojuegos;
	
	/**
	 * Constructor de ControladorVideojuegos
	 */
	public ControladorVideojuegos() {
		listaVideojuegos = new HashMap<String, Videojuego>();
	}

	/**
	 * M�todo que crea una nueva instancia de <code>Videojuego</code> y la colecciona, indexada por el <code>idVideojuego</code>
	 * 
	 * @param idVideojuego El id del videojuego
	 * @param nombre El titulo del videojuego
	 * @param autor El creador del videojuego
	 * @param consola La consola del videojuego
	 * @param year El a�o del videojuego
	 * @param genero El g�nero del videojuego
	 * @param edadMinRecomendada La edad m�nima recomendada para el videojuego
	 * @param costeAlquiler El coste que tiene alquilarlo
	 * @param copiasTotales El n�mero de copias totales
	 * @param copiasDisponibles El n�mero de copias disponibles
	 * @throws ExcepcionVideojuego Si algo ha ido mal al crear la descripci�n
	 */
	public void crearDescripcionVideojuego(String idVideojuego, String nombre,
			String autor, String consola, Year year, GeneroVideojuego genero,
			int edadMinRecomendada, float costeAlquiler) throws ExcepcionVideojuego {
		
		//Comprueba si existia la descripci�n del vidojuego con ese idVideojuego
		if (!listaVideojuegos.containsKey(idVideojuego)) {
			// Si no existe, crea la instancia con 0 copias
			Videojuego nuevoVideojuego = new Videojuego(idVideojuego,nombre,autor,consola,year,genero,edadMinRecomendada,costeAlquiler);
			// Y la colecciona
			listaVideojuegos.put(idVideojuego, nuevoVideojuego);
		} else {
			// Pero si ya exist�a lanza una excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.YA_EXISTE_DESCRIPCION, idVideojuego,null);
		}
		
	}

	
	/**
	 * M�todo que permite obtener una lista de cadenas, cada una con informaci�n breve de cada instancia de <code>Videojuego</code> coleccionada por este
	 * controlador.
	 * 
	 * @return Una lista de cadenas
	 */
	public List<String> listarDescripcionesVideojuego() {
		// Inicializa la lista auxiliar
		List<String> listado = new ArrayList<String>();

		// Recorre la colecci�n de socios
		for (Videojuego v : listaVideojuegos.values()) {
			// Pide informaci�n breve
			String ficha = v.verFichaBreve();
			// Y la a�ade al listado
			listado.add(ficha);
		}
			// Al terminar retorna el listado
			return listado;
	}

	
	/**
	 * Muestra la descripci�n del videojuego con todos sus datos
	 * 
	 * @param idVideojuego El identificador del videojuego
	 * @return la ficha del videojuego
	 * @throws ExcepcionVideojuego Si algo ha salido mal al ver el videojuego
	 */
	public String verDescripcionVideojuego(String idVideojuego) throws ExcepcionVideojuego {
		// Recupera la instancia de la colecci�n
		Videojuego videojuego = listaVideojuegos.get(idVideojuego);
		// Si este videojuego exist�a, no es null
		if (videojuego != null) {
			// Le pide al videojuego que muestre su informaci�n completa
			return videojuego.verFichaCompleta();
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_DESCRIPCION, idVideojuego,null);
		}
	}
	
	/**
	 * Modifica la descripci�n del videojuego con los nuevos par�metros
	 * 
	 * @param idVideojuego El id del videojuego
	 * @param nombre El titulo del videojuego
	 * @param autor El creador del videojuego
	 * @param consola La consola del videojuego
	 * @param year El a�o del videojuego
	 * @param genero El g�nero del videojuego
	 * @param edadMinRecomendada La edad m�nima recomendada para el videojuego
	 * @param costeAlquiler El coste que tiene alquilarlo
	 * @param copiasTotales El n�mero de copias totales
	 * @param copiasDisponibles El n�mero de copias disponibles
	 * @throws ExcepcionVideojuego Si algo ha ido mal al modificar la descripci�n
	 */
	
	public void modificarDescripcionVideojuego(String idVideojuego, String nombre,
			String autor, String consola, Year year, GeneroVideojuego genero,
			int edadMinRecomendada, float costeAlquiler) throws ExcepcionVideojuego {
		
		// Recupera la instancia de la colecci�n a partir de su idVideojuego
		Videojuego videojuego = listaVideojuegos.get(idVideojuego);
		
		// Si este videojuego exist�a, no es null
		if (videojuego != null) {
			// Modifica los datos introducidos
			//videojuego.setIdVideojuego(idVideojuego); No es necesario pues es el mismo
			videojuego.setNombre(nombre);
			videojuego.setAutor(autor);
			videojuego.setConsola(consola);
			videojuego.setYear(year);
			videojuego.setGenero(genero);
			videojuego.setEdadMinRecomendada(edadMinRecomendada);
			videojuego.setCosteAlquiler(costeAlquiler);
			
		} else {
			// Pero si no exist�a lanza una excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_DESCRIPCION, idVideojuego,null);
		}
	}

	/**
	 * M�todo que permite borrar una instancia de <code>Videojuego</code> dada por un determinado <code>idVideojuego</code>
	 * 
	 * @param idVideojuego el identificador �nico del videojuego
	 * @throws ExcepcionVideojuego si no existe un usuario con este <code>idVideojuego</code>
	 */
	public void eliminarDescripcionVideojuego(String idVideojuego) throws ExcepcionVideojuego {
		
		Videojuego videojuego = listaVideojuegos.get(idVideojuego);
		
		//Si existe el videojuego con ese ID, lo elimina de la lista
		 if (listaVideojuegos.containsKey(idVideojuego)){ 
			 listaVideojuegos.remove(idVideojuego,videojuego);
	     }
		 else{//Si no existe, lanza excepci�n
			 throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_DESCRIPCION, idVideojuego,null);
		 }
		 
	}

	/**
	 * M�todo que crea una nueva instancia de <code>Copia</code> y la colecciona, indexada por el <code>idCopia</code> generado
	 * 
	 * @param idVideojuego el identificador �nico de videojuego
	 * @throws ExcepcionVideojuego si no existe un videojuego con este <code>idVideojuego</code>
	 */
	public void crearCopiaVideojuego(String idVideojuego) throws ExcepcionVideojuego {
		
		Videojuego videojuego= listaVideojuegos.get(idVideojuego);
		//obtener el videojuego a partir de su idVideojuego
		if (videojuego != null) {
			//Crea una nueva instancia de copia
			videojuego.crearCopia();
		} else {
			// Pero si no existe el videojuego lanza una excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_DESCRIPCION, idVideojuego,null);
		}
		
	}

	/**
	 * Lista todas las copias del videojuego con identificador <code>idVideojuego</code>.
	 * 
	 * @param idVideojuego El identificador del videojuego
	 * @return la lista de copias asociadas
	 * @throws ExcepcionVideojuego Si no se ha encontrado el videojuego
	 */
	public List<String> listarCopiasVideojuego(String idVideojuego) throws ExcepcionVideojuego{
		
		Videojuego videojuego = listaVideojuegos.get(idVideojuego);
		//Obtenemos el videojuego a partir de su idVideojuego
		if (videojuego==null){//Si el videojuego no existe lanza excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_DESCRIPCION, idVideojuego,null);
		}
		
		return videojuego.listarCopiasVideojuego();
	}

	/**
	 * M�todo que permite mostrar toda la informaci�n de una instancia de <code>Copia</code> dada por un determinado <code>idVideojuego</code> y una determiada instancia de <code>idCopia</code>
	 * 
	 * @param idVideojuego
	 * @param idCopia
	 * @return la ficha completa de la copia
	 * @throws ExcepcionVideojuego Si no se ha encontrado la descripci�n (si es la copia la que no existe la excepci�n se lanzar� en el m�todo de videojuego)
	 */
	public String verCopiaVideojuego(String idVideojuego, String idCopia) throws ExcepcionVideojuego {
		
		Videojuego videojuego = listaVideojuegos.get(idVideojuego);
		//Obtenemos el videojuego a partir de su idVideojuego
		if (videojuego==null){//Si el videojuego no existe lanza excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_DESCRIPCION, idVideojuego,null);
		}
		
		return videojuego.verCopiaVideojuego(idCopia);
	}
	
	
	/**
	 * Devuelve la copia con identifcador <code>idCopia</code> del videojuego con identificador <code>idVideojuego</code>
	 * 
	 * @param idVideojuego el identificador del videojuego
	 * @param idCopia el identificador de la copia
	 * @return la copia con identificador idCopia
	 * @throws ExcepcionVideojuego si la descripcion no existe
	 */
	public Copia obtenerCopiaVideojuego(String idVideojuego, String idCopia) throws ExcepcionVideojuego {
		
		Videojuego videojuego= listaVideojuegos.get(idVideojuego);
		//Obtenemos el videojuego a partir de su idVideojuego
		if (videojuego==null){//Si el videojuego no existe lanza excepci�n
			throw new ExcepcionVideojuego(CausaExcepcionVideojuego.NO_EXISTE_DESCRIPCION, idVideojuego,idCopia);
		}
		
		Copia copia=videojuego.obtenerCopia(idCopia);
		//Obtenemos la copia a partir de su idCopia llamando a obtener copia
		return copia;
	}
	

	/**
	 * Devuelve el precio que cuesta diariamente el alquiler de un videojuego
	 * 
	 * @param idVideojuego el identificador del videojuego
	 * @return el precio diario del videojuego
	 */

	public float obtenerPrecioDiarioVideojuego(String idVideojuego) {
		
		//Tambi�n se podr�a haber optado por calcular ya aqu� directamente el total y devolverlo
		Videojuego videojuego= listaVideojuegos.get(idVideojuego);
		//Obtenemos el videojuego a partir de su idVideojuego
		return videojuego.getCosteAlquiler();//get coste alquiler nos devuelve el precio
		
	}

}
