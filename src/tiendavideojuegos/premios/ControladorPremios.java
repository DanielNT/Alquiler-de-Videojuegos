package tiendavideojuegos.premios;

import java.util.List;

import tiendavideojuegos.alquileres.Alquiler;
import tiendavideojuegos.alquileres.ControladorAlquileres;
import tiendavideojuegos.usuarios.ControladorUsuarios;
import tiendavideojuegos.usuarios.Socio;

/**
 * @author Daniel
 *
 */ 

/**
 * Clase de la UI que tiene visibilidad sobre los premios a la hora de aplicarlos
 */
public class ControladorPremios {

	private ControladorUsuarios cu;
	private ControladorAlquileres ca;
	private List<PoliticaPremio> listaPoliticasPremio;
	
	/**
	 * Constructor del controlador de premios
	 * 
	 * @param cu El controlador de usuarios
	 * @param ca El controlador de alquileres
	 */
	public ControladorPremios(ControladorUsuarios cu, ControladorAlquileres ca) {
		this.cu=cu;
		this.ca=ca;
		
		/*Para obtener la lista de politicas de premio recurrimos a la instancia de Factoría que obtenemos 
		al realizar un getInstance, en consonancia con el patrón singleton*/
		listaPoliticasPremio = FactoriaPoliticasPremio.getInstance().cargarPoliticas();
	}

	/**
	 * Aplica todas las politicas de premio de la lista
	 */
	public void aplicarPoliticasPremio() {
		
		//Recorre la lista de políticas, y las aplica
		for(PoliticaPremio politica: listaPoliticasPremio){
						
			
			//Recuperamos la lista de socios y de alquileres
			List<Socio> listadoSocios= cu.recuperarListaSocios();
			List<Alquiler> listadoAlquileres= ca.recuperarListaAlquileres();
			
			for (Socio s: listadoSocios){
				politica.aplicarPolitica(s,listadoAlquileres);
			}
		}
			
	}
}
