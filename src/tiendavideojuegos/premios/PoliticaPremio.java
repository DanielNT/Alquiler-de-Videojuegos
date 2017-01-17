package tiendavideojuegos.premios;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tiendavideojuegos.alquileres.Alquiler;
import tiendavideojuegos.usuarios.ControladorUsuarios;
import tiendavideojuegos.usuarios.Socio;

/**
 * Clase abstracta para implementar el patrón 'política' y aplicar distintas políticas de premio a los socios. Los premios se deben calcular
 * a partir de la información del socio (por ejemplo, premios relacionados con su edad, su cumpleaños) o con los alquileres que ha completado.
 * Las políticas se intentarán aplicar cada día, por lo que las propias políticas deben comprobar si son aplicables en ese día en cuestión (por ejemplo,
 * las políticas mensuales deben comprobar si es el día 1 del mes).
 * 
 * @author Eduardo Gómez Sánchez. ETSIT UVa.
 */
public abstract class PoliticaPremio {


	protected ControladorUsuarios cu;
	

	/**
	 * Método abstracto que se especifica en cada subclase
	 * @param listadoAlquileres El listado de todos los alquileres
	 * @param s El socio sobre el que se aplica la política
	 */
	public abstract void aplicarPolitica(Socio s, List<Alquiler> listadoAlquileres);
	
	
	/**
	 * Transforma las fechas Date a Calendar (para no tener que cambiar el resto del código)
	 * 
	 * @param date La fecha en instancia Date
	 * @return la fecha en instancia de Calendar
	 */
	public Calendar convertirFecha(Date date){ 
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}
	
}
