package tiendavideojuegos.premios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que usa introspecci�n para instanciar todos los derivados de la clase abstracta PoliticaPremio que existan en 
 * un subdirectorio por debajo del directorio donde se encuentra esta clase. No debe modificarse el c�digo de esta clase.
 * NOTA: es importante observar que en el dise�o de esta clase se ha seguido el patr�n "singleton", por lo que no puede
 * instanciarse invocando al constructor, sino que debe obtenerse la �nica instancia a trav�s de un m�todo de clase (est�tico).
 * 
 * @author Eduardo G�mez S�nchez. ETSIT UVa.
 *
 */
public class FactoriaPoliticasPremio {
	/** Directorio en el que est�n las pol�ticas, definido por debajo del paquete actual */
	private static final String subdirectorioPoliticas = "politicas";
	/** Instancia de esta clase, para implementar "singleton" */
	private static FactoriaPoliticasPremio instancia;
	
	/** Constructor declarado privado, para que nadie pueda instanciar la clase */
	private FactoriaPoliticasPremio() {
		super();
	}

	/**
	 * M�todo que devuelve la �nica instancia de esta clase, implementando as� el patr�n 'singleton'
	 * 
	 * @return una instancia de esta clase
	 */
	public static FactoriaPoliticasPremio getInstance() {
		//Inicializa la instancia
		if(instancia == null) {
			instancia = new FactoriaPoliticasPremio();
		}
		
		//Devuelve la instancia
		return instancia;
	}
	
	/**
	 * M�todo que permite cargar todas las pol�ticas de premio existentes en un directorio dado, haciendo reflexi�n
	 * @return Un listado de objetos de la clase <code>PoliticaPremio</code>
	 */
	public List<PoliticaPremio> cargarPoliticas() {
		//Inicializa la lista de pol�ticas, que ser� el valor a devolver, incluso aunque est� vac�o
		List<PoliticaPremio> listaPoliticas = new ArrayList<PoliticaPremio>();
		
		//Compone el nombre del paquete en el que est�n las pol�ticas, como el nombre de este paquete m�s el nombre del dubdirectorio
		String paquetePoliticas = FactoriaPoliticasPremio.class.getPackage().getName() + "." + subdirectorioPoliticas;
		//Compone el nombre del directorio, como el directorio en el que comienza la aplicaci�n, m�s el nombre del paquete cambiando '.' por '/' , m�s el nombre del subdirectorio
		String directorioPoliticas = FactoriaPoliticasPremio.class.getProtectionDomain().getCodeSource().getLocation().getPath() + paquetePoliticas.replace('.', '/') + "/";
				
		//Intenta abrir el directorio
		File directorio = new File(directorioPoliticas);
		
		//Recorre cada uno de los ficheros contenidos en el directorio
		for(String ficheroPolitica : directorio.list()) {
			if(ficheroPolitica.endsWith(".class")) {
				//Elimina el ".class" del nombre y a�ade la parte del paquete, para componer el nombre completo de la clase
				String nombreClasePolitica = paquetePoliticas + "." + ficheroPolitica.substring(0, ficheroPolitica.lastIndexOf('.'));
				//Obtiene el objeto Class que representa a esa clase
				try {
					Class<?> clasePolitica = Class.forName(nombreClasePolitica);
					//Lo instancia
					Object objetoPolitica = clasePolitica.newInstance();
					//Comprueba si es un derivado de la clase PoliticaPremio
					if(objetoPolitica.getClass().getSuperclass() == PoliticaPremio.class) {
						//Y si lo es, hace un cast y lo recuerda en la lista
						listaPoliticas.add((PoliticaPremio)objetoPolitica);
					}
				} catch (ClassNotFoundException e) {
					//Si no se encuentra la clase con ese nombre (no deber�a ocurrir)
					System.err.println("La clase " + nombreClasePolitica + " no existe en el proyecto");
				} catch (InstantiationException e) {
					//Si no se puede instanciar
					System.err.println("La clase " + nombreClasePolitica + " no se puede instanciar");
				} catch (IllegalAccessException e) {
					//Si no se puede acceder
					System.err.println("La clase " + nombreClasePolitica + " no es accesible");
				}
			}
		}
		
		//Retorna la lista de pol�ticas
		return listaPoliticas;
	}
}
