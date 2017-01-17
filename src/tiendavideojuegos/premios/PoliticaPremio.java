package tiendavideojuegos.premios;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import tiendavideojuegos.alquileres.Alquiler;
import tiendavideojuegos.usuarios.ControladorUsuarios;
import tiendavideojuegos.usuarios.Socio;

/**
 * Clase abstracta para implementar el patr�n 'pol�tica' y aplicar distintas pol�ticas de premio a los socios. Los premios se deben calcular
 * a partir de la informaci�n del socio (por ejemplo, premios relacionados con su edad, su cumplea�os) o con los alquileres que ha completado.
 * Las pol�ticas se intentar�n aplicar cada d�a, por lo que las propias pol�ticas deben comprobar si son aplicables en ese d�a en cuesti�n (por ejemplo,
 * las pol�ticas mensuales deben comprobar si es el d�a 1 del mes).
 * 
 * @author Eduardo G�mez S�nchez. ETSIT UVa.
 */
public abstract class PoliticaPremio {


	protected ControladorUsuarios cu;
	

	/**
	 * M�todo abstracto que se especifica en cada subclase
	 * @param listadoAlquileres El listado de todos los alquileres
	 * @param s El socio sobre el que se aplica la pol�tica
	 */
	public abstract void aplicarPolitica(Socio s, List<Alquiler> listadoAlquileres);
	
	
	/**
	 * Transforma las fechas Date a Calendar (para no tener que cambiar el resto del c�digo)
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
