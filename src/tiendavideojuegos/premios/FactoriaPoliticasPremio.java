package tiendavideojuegos.premios;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que usa introspección para instanciar todos los derivados de la clase abstracta PoliticaPremio que existan en 
 * un subdirectorio por debajo del directorio donde se encuentra esta clase. No debe modificarse el código de esta clase.
 * NOTA: es importante observar que en el diseño de esta clase se ha seguido el patrón "singleton", por lo que no puede
 * instanciarse invocando al constructor, sino que debe obtenerse la única instancia a través de un método de clase (estático).
 * 
 * @author Eduardo Gómez Sánchez. ETSIT UVa.
 *
 */
public class FactoriaPoliticasPremio {
	/** Directorio en el que están las políticas, definido por debajo del paquete actual */
	private static final String subdirectorioPoliticas = "politicas";
	/** Instancia de esta clase, para implementar "singleton" */
	private static FactoriaPoliticasPremio instancia;
	
	/** Constructor declarado privado, para que nadie pueda instanciar la clase */
	private FactoriaPoliticasPremio() {
		super();
	}

	/**
	 * Método que devuelve la única instancia de esta clase, implementando así el patrón 'singleton'
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
	 * Método que permite cargar todas las políticas de premio existentes en un directorio dado, haciendo reflexión
	 * @return Un listado de objetos de la clase <code>PoliticaPremio</code>
	 */
	public List<PoliticaPremio> cargarPoliticas() {
		//Inicializa la lista de políticas, que será el valor a devolver, incluso aunque esté vacío
		List<PoliticaPremio> listaPoliticas = new ArrayList<PoliticaPremio>();
		
		//Compone el nombre del paquete en el que están las políticas, como el nombre de este paquete más el nombre del dubdirectorio
		String paquetePoliticas = FactoriaPoliticasPremio.class.getPackage().getName() + "." + subdirectorioPoliticas;
		//Compone el nombre del directorio, como el directorio en el que comienza la aplicación, más el nombre del paquete cambiando '.' por '/' , más el nombre del subdirectorio
		String directorioPoliticas = FactoriaPoliticasPremio.class.getProtectionDomain().getCodeSource().getLocation().getPath() + paquetePoliticas.replace('.', '/') + "/";
				
		//Intenta abrir el directorio
		File directorio = new File(directorioPoliticas);
		
		//Recorre cada uno de los ficheros contenidos en el directorio
		for(String ficheroPolitica : directorio.list()) {
			if(ficheroPolitica.endsWith(".class")) {
				//Elimina el ".class" del nombre y añade la parte del paquete, para componer el nombre completo de la clase
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
					//Si no se encuentra la clase con ese nombre (no debería ocurrir)
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
		
		//Retorna la lista de políticas
		return listaPoliticas;
	}
}
