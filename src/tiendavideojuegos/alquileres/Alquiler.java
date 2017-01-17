package tiendavideojuegos.alquileres;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tiendavideojuegos.constantes.IConstantesTiendaVideojuegos;
import tiendavideojuegos.tarjetas.ExcepcionTarjeta;
import tiendavideojuegos.tarjetas.Multa;
import tiendavideojuegos.usuarios.Socio;
import tiendavideojuegos.videojuegos.Copia;
import tiendavideojuegos.videojuegos.EstadoCopia;


/**
 * Clase que representa un alquiler.
 * 
 * @author iss031
 */
public class Alquiler {
	//Atributos de alquiler
	private Date fechaInicio;
	private Date fechaFin;
	private Date fechaDevolucion;
	private Socio socio;
	private Copia copia;
	private Multa multa;
	private int numDias;
	
	/**
	 * Constructor de la clase Alquiler
	 * 
	 * @param socio El socio al que est� asociado el alquiler
	 * @param copia La copia a la que est� asociada el alquiler
	 * @param numDias El n�mero de d�as que se realiza el alquiler
	 */
	
	public Alquiler(Socio socio, Copia copia, int numDias) {
		this.socio=socio;
		this.copia=copia;
		this.numDias=numDias;
		this.multa= new Multa(0); //inicialmente no hay multa
		this.fechaInicio= new Date();
		this.fechaFin= obtenerFechaFin(fechaInicio,numDias); 
		//Inicialmente la fecha de devoluci�n es null
		this.fechaDevolucion=null; 
	}
	//GETTERS Y SETTERS
	/**
	 * Devuelve el socio que ha realizado el alquiler
	 * @return el socio
	 */
	public Socio getSocio() {
		return socio;
	}

	/**
	 * Devuelve una ficha del alquiler con datos del socio y del videojuego
	 * @return fichaAlquiler
	 */
	public String verFichaAlquiler() {//Muestra por pantalla informaci�n detallada sobre un alquiler
		
		/*Se utiliza la clase SimpleDateFormat para mostrar las fechas con el formato deseado o */
		SimpleDateFormat formateador = new SimpleDateFormat("dd-MMM-yyyy");
		String fInicio = formateador.format(fechaInicio);
		String estado="";
		String fecha=" desde " + fInicio + " durante " + numDias + " d�as";
		String nombreCompletoSocio= socio.getNombre() + " " + socio.getApellidos();
		
		/*Condici�n que indica el estado de una copia. Cuando una copia est� alquilada, est� en estado VIGENTE,
		 *en caso contrario, en estado FINALIZADO*/
		if(copia.getEstado().equals(EstadoCopia.ALQUILADO)){
			estado = "VIGENTE";
		}
		else{
			estado = "TERMINADO";
		}
		//Se invocan m�todos sobre objetos que a su vez devuelven objetos sobre los que se vuelven a invocar otros m�todos
		return "El socio " + nombreCompletoSocio + " alquila el videojuego " + copia.getVideojuego().getIdVideojuego() + ": '" + copia.getVideojuego().getNombre() + "' para " + copia.getVideojuego().getConsola() + fecha + " (" + estado + ")";
	}

	/**
	 * Devuelve la fecha en la que se inicializa un nuevo alquiler
	 * @return la fecha de inicio
	 */
	public Date getFechaInicio() {
		return fechaInicio;
	}


	/**
	 * Cambia la fecha de inicializaci�n de un alquiler por la proporcionada 
	 * @param fechaInicio la fecha de inicio del alquiler
	 */
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	/**
	 * Devuelve la fecha de finalizaci�n del alquiler
	 * @return la fecha de finalizaci�n
	 */
	public Date getFechaFin() {
		return fechaFin;
	}


	/**
	 * Cambia la fecha de finalizaci�n del alquiler por la proporcionada
	 * @param fechaFin la fecha de finalizaci�n del alquiler
	 */
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	/**
	 * Devuelve la fecha de devoluci�n de un alquiler
	 * @return  fechaDevolucion
	 */
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}


	/**
	 * Cambia la fecha de devoluci�n por la proporcionada
	 * @param fechaDevolucion la fechaDevolucion a introducir
	 */
	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	
	/**
	 * Devuelve la copia del alquiler
	 * @return la copia
	 */
	public Copia getCopia() {
		return copia;
	}

	/**
	 * M�todo para sumar un n�mero de d�as a la fecha de inicio del alquiler
	 * 
	 * @param fecha la fecha de inicio del alquiler
	 * @param dias el n�mero de d�as de duraci�n del alquiler
	 * @return la nueva fecha
	 */
	public Date obtenerFechaFin(Date fecha,int dias){
		/*Desde de la versi�n Java 8 se utiliza la clase Calendar para el manejo de fechas y tiempo*/
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // La fecha de inicio.
		calendar.add(Calendar.DAY_OF_YEAR, dias);  // n�mero de d�as a a�adir
		return calendar.getTime(); // Devuelve el objeto Date con los nuevos d�as a�adidos(la fechaFin calculada)
	
	 }

	/**
	 * Devuelve la multa asociada al alquiler
	 * @return la instancia de multa
	 */
	public Multa getMulta() {
		return multa;
	}


	/**
	 * Se encarga de cambiar el estado de la copia (por el patr�n "no hables con extra�os")
	 * @param costeTotal El coste total del alquiler
	 * @throws ExcepcionTarjeta Si ha fallado la creaci�n del alquiler
	 */
	public void crearAlquilerCopia() throws ExcepcionTarjeta {
		
		//Obtenemos el coste diario desde la copia, que habla con el videojuego
		float costeTotal= copia.getVideojuego().getCosteAlquiler() * numDias;
				
		socio.realizarReintegro(copia.crearAlquilerCopia(numDias), costeTotal);
	 
	}
	
	/**
	 * Se encarga de establecer la fecha de devoluci�n y de poner en disponible la copia
	 * en el caso de que no haya ninguna multa
	 */
	public void devolverCopia() {
	
		fechaDevolucion= new Date();
		copia.setEstado(EstadoCopia.DISPONIBLE);
	}

	/**
	 * Calcula la multa del alquiler
	 * @param diferenciaDias La diferencia de d�as desde el fin del plazo
	 * @return la cantidad de la multa
	 */
	public float calcularMulta(long diferenciaDias) {
		
		float cantidad = copia.calcularMulta(diferenciaDias);
		multa.setCantidad(cantidad);
		
		//Devuelve la cantidad
		return cantidad;
	}

	/**
	 * M�todo que se invoca si se ha pasado el plazo
	 * @throws ExcepcionTarjeta Si el socio no tiene saldo
	 */
	public void devolverCopiaMultada() throws ExcepcionTarjeta {
		
		Calendar fechaActual = Calendar.getInstance();
		
		//Observemos que al transcurrir la ejecuci�n del programa es muy probable que calcule 
		//mal la diferencia de d�as (si se calculan dentro del mismo m�todo no hay problema).
		//Con a�adir 1 minuto basta
		fechaActual.add(Calendar.MINUTE, 1);
	
		
		/* Calculamos la diferencia como la diferencia de ms entre las dos fechas, dividido entre los 
		 * milisegundos que tiene cada d�a */
		long diferenciaDias = Math.abs((fechaActual.getTimeInMillis() - fechaFin.getTime()) /IConstantesTiendaVideojuegos.MILLSECS_PER_DAY);
		
		//El concepto de la multa
		String concepto = "Multa en el alquiler de " + copia.getIdCopia() + ": '" + copia.getVideojuego().getNombre() + "' para " + copia.getVideojuego().getConsola() + " por " + (int)diferenciaDias + " d�as de exceso";
		socio.realizarReintegro(concepto, calcularMulta(diferenciaDias));
		
		fechaDevolucion= new Date();
		copia.setEstado(EstadoCopia.DISPONIBLE);
		
	}

}
