package tiendavideojuegos.alquileres;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tiendavideojuegos.constantes.IConstantesTiendaVideojuegos;
import tiendavideojuegos.tarjetas.ExcepcionTarjeta;
import tiendavideojuegos.usuarios.CausaExcepcionUsuario;
import tiendavideojuegos.usuarios.ControladorUsuarios;
import tiendavideojuegos.usuarios.ExcepcionUsuario;
import tiendavideojuegos.usuarios.Socio;
import tiendavideojuegos.videojuegos.CausaExcepcionVideojuego;
import tiendavideojuegos.videojuegos.ControladorVideojuegos;
import tiendavideojuegos.videojuegos.Copia;
import tiendavideojuegos.videojuegos.EstadoCopia;
import tiendavideojuegos.videojuegos.ExcepcionVideojuego;

/**
 * Clase controladora que recibe los métodos provenientes de la UI relacionados con la gestión de alquileres
 * 
 * @author iss031
 */
public class ControladorAlquileres {

	private ControladorUsuarios cu;
	private ControladorVideojuegos cvj;
	private List<Alquiler> listaAlquileresVigentes;
	private List<Alquiler> listaAlquileresDevueltos;

	/**
	 * Constructor del controlador de alquileres
	 * 
	 * @param cu El controlador de usuarios
	 * @param cvj El controlador de videojuegos
	 */
	public ControladorAlquileres(ControladorUsuarios cu,ControladorVideojuegos cvj) {
		this.cu=cu;
		this.cvj=cvj;
		/**listado de los alquileres registrados en la tienda de videojuegos */
		listaAlquileresVigentes= new ArrayList<Alquiler>();//Inicializamos ambas listas
		listaAlquileresDevueltos= new ArrayList<Alquiler>();
		
	}

	/**
	 * Crea el alquiler de la copia de un videojuego
	 * 
	 * @param login El identificador del usuario
	 * @param idVideojuego El identificador del videojuego
	 * @param idCopia El id de la copia
	 * @param numDias El número de días
	 * @throws ExcepcionAlquiler Si no se ha podido crear el alquiler
	 */
	public void crearAlquiler(String login, String idVideojuego, String idCopia, int numDias) throws ExcepcionAlquiler{

		//Variable local para comprobar si lleva el número de alquileres máximo
		int numAlquileresSocio=0;
		
		//Para coincidir con el main proporcionado, transformamos las excepciones captadas al tipo ExcepcionAlquiler
		try {
			/*Obtención de la copia que se desea alquilar y comprobación del estado de la misma.
			 * En esta iteración ignoramos el estado RESERVADO de la copia. */
			Copia copia = cvj.obtenerCopiaVideojuego(idVideojuego,idCopia);
			
			//Vemos si el estado de copia es disponible
			EstadoCopia estadoCopia = copia.getEstado();
			
			if (!(estadoCopia==EstadoCopia.DISPONIBLE)){
				throw new ExcepcionAlquiler(CausaExcepcionAlquiler.COPIA_NO_DISPONIBLE,login,idCopia);
			}
			//Obtención del socio a partir de su login 
			Socio socio= cu.recuperarSocio(login);
			
			//Si el socio no existe
			if(socio==null){
				throw new ExcepcionAlquiler(CausaExcepcionAlquiler.SOCIO_NO_EXISTE,login,idCopia);
			}
			
			for(Alquiler a: listaAlquileresVigentes){
				
				//si coinciden, es que ha hecho el alquiler
				if(a.getSocio().equals(socio)){
					//Suma 1 al número de alquileres
					numAlquileresSocio++;				
				}
			}	
			
			//Comprueba si se ha superado el máximo de alquileres
			if(numAlquileresSocio >= IConstantesTiendaVideojuegos.MAXIMO_ALQUILERES){
				throw new ExcepcionAlquiler(CausaExcepcionAlquiler.SUPERADO_LIMITE_ALQUILERES,login,idCopia);
			}
			
			//Se crea una instancia de la clase Alquiler(nuevo alquiler) y se modifica el estado de la copia
			Alquiler alquiler = new Alquiler(socio,copia,numDias);
			alquiler.crearAlquilerCopia();
			
			
			//Añadimos el alquiler a la lista
			listaAlquileresVigentes.add(alquiler);
		}
		catch(ExcepcionTarjeta et){//Excepción si no hay suficiente saldo en la tarjeta
			throw new ExcepcionAlquiler(CausaExcepcionAlquiler.SOCIO_SIN_SALDO_PARA_ALQUILER,login,idCopia);
		}
		catch(ExcepcionVideojuego ev){
				
			//Excepción en caso de que el videojuego no existe 
			if (ev.getCausa().equals(CausaExcepcionVideojuego.NO_EXISTE_DESCRIPCION)){
				throw new ExcepcionAlquiler(CausaExcepcionAlquiler.NO_EXISTE_DESCRIPCION,login,idCopia);
			}
			//Excepción en caso de que la copia de videojego no exista 
			else{
				throw new ExcepcionAlquiler(CausaExcepcionAlquiler.COPIA_NO_EXISTE,login,idCopia);
			}	
		}	
	}
	
	/**
	 * Devuelve la lista de alquileres de un solo socio
	 * 
	 * @param login El identificador del usuario
	 * @return el listado de alquileres de un socio
	 * @throws ExcepcionUsuario Si el usuario con ese login no existe o no tiene alquileres
	 * @throws ExcepcionAlquiler Si no se ha podido listar
	 */
	public List<String> listarAlquileresSocio(String login) throws ExcepcionUsuario {
		
		List<String> listado = new ArrayList<String>();
		
		//Obtiene el socio del controlador de usuarios
		Socio s= cu.recuperarSocio(login);
		
		//Si existe el socio
		if(s!=null){
			
			for(Alquiler a: listaAlquileresVigentes){
				if(a.getSocio().equals(s)){
					String ficha=a.verFichaAlquiler();
					listado.add(ficha);
					
				}
			}
					
			for(Alquiler a: listaAlquileresDevueltos){
			//Si el objeto socio coincide con el del alquiler
				if(a.getSocio().equals(s)){
					String ficha= a.verFichaAlquiler();
					listado.add(ficha);
				}
			}
			
			//Comprueba si hay algo en la lista después del bucle
			if(listado.isEmpty()){
				//solución menos agresiva
				listado.add("No hay copias alquiladas actualmente o en el pasado");
			
			}
			
			//Devuelve el listado si todo ha ido bien
			return listado;
			
		}
		//Si el socio no existía, es null y lanza una excepción
		else{
			throw new ExcepcionUsuario(CausaExcepcionUsuario.NO_EXISTE, login);
		}
	}

	/**
	 * Registra la devolución, si se puede
	 * 
	 * @param idCopia El identificador de la copia
	 * @param fechaActual La fecha que a la que se ha mandado la orden de terminar el alquiler
	 * @return Si se ha multado o no (booleano)
	 * @throws ExcepcionAlquiler Si no se podido terminar el alquiler
	 */
	public boolean terminarAlquiler(String idCopia, Calendar fechaActual) throws ExcepcionAlquiler {
		
		boolean multado = false;
		Alquiler alquiler=null;

		//Busca que alquiler de la lista coincide
		for(Alquiler alq: listaAlquileresVigentes){
			
			Copia c= alq.getCopia();
			
			//La condición if de abajo está puesta de una forma compacta
			if(c.getIdCopia().equals(idCopia)){
				
				if(listaAlquileresVigentes.contains(alq)){
					//No operamos dentro del iterador foreach para evitar excepción de modificación concurrente
					alquiler=alq;
				}
				else{
					throw new ExcepcionAlquiler(CausaExcepcionAlquiler.ALQUILER_NO_EXISTE,"(desconocido)",idCopia);//login desconocido
				}
				break;
			}				
		}
		
		//Si no se ha encontrado nada
		if(alquiler==null){
				
			/* Como el socio no se utiliza (si no existe la copia no está asociada a ningún socio
			 * pues el socio es null o "" */
			throw new ExcepcionAlquiler(CausaExcepcionAlquiler.ALQUILER_NO_EXISTE,"(desconocido)",idCopia);
		}
		else{
			
			//Obtenemos el socio
			Socio s= alquiler.getSocio();
		
			/* Hay que tener cuidado al manejar las fechas Date, pues al hacer new Date(); devuelve en ms,
		 	* por lo que si se entrega posteriormente a la fecha fin calculada, aunque sea el mismo día,
		 	* daría error de devolución. Habría que hacer un getMonth, Day, Year...
		 	* 
		 	* Pondría multa si han pasado exactamente los días, aunque se devuelva el mismo día de fin de plazo
		 	* if(a.getFechaFin().compareTo(fechaActual)<=0)
			*	a.devolverCopia();
		 	*/
		
			//En ese caso hemos encontrado el alquiler y gestionamos la devolución
			Calendar fechaEntrega = Calendar.getInstance();
			fechaEntrega.setTime(alquiler.getFechaFin()); // La fecha de inicio.
		
			//System.out.println("La fecha de entrega es" + fechaEntrega.getTime());
			
			/* Por lo que se ha mencionado antes, no usaremos la opcion compareTo, before o after,
			 * pues en una tienda de videojuegos debería poder devolverse el mismo día de final de 
			 * plazo, aunque hayan pasado horas que suponen el número de días*/
		
			if(fechaEntrega.compareTo(fechaActual)>=0){
			
				//Pide al alquiler que establezca la copia como devuelta si la fecha de entrega es anterior a la fecha de fin de plazo
				alquiler.devolverCopia();
				alquiler.setFechaDevolucion(fechaActual.getTime());
				listaAlquileresVigentes.remove(alquiler);
				listaAlquileresDevueltos.add(alquiler);
			}
			//Se puede hacer en varios if si se considera muy largo
			else if((fechaEntrega.get(Calendar.YEAR)== fechaActual.get(Calendar.YEAR)) && (fechaEntrega.get(Calendar.MONTH)== fechaActual.get(Calendar.MONTH)) && (fechaEntrega.get(Calendar.DAY_OF_MONTH)== fechaActual.get(Calendar.DAY_OF_MONTH))){

				//Pide al alquiler que establezca la copia como devuelta
				alquiler.devolverCopia();
				alquiler.setFechaDevolucion(fechaActual.getTime());
				listaAlquileresVigentes.remove(alquiler);
				listaAlquileresDevueltos.add(alquiler);

			}
			//Si no se cumple, es que se ha pasado el plazo
			else{
				multado = true;
			
				
				//Actualiza la cantidad de la multa
				try {
					alquiler.devolverCopiaMultada();
					listaAlquileresVigentes.remove(alquiler);
					listaAlquileresDevueltos.add(alquiler);
				} catch (ExcepcionTarjeta e) {//Si el scio no puede pagar la multa
					throw new ExcepcionAlquiler(CausaExcepcionAlquiler.SOCIO_SIN_SALDO_PARA_MULTA,s.getLogin(),idCopia);
				}
			}
		}
		
		
	return multado;
		
	}
	
	/**
	 * Recupera la lista de todos los alquileres de todos los socios
	 * @return la lista de alquileres
	 */
	public List<Alquiler> recuperarListaAlquileres() {
		
		List<Alquiler> listaAlquileresTotales= new ArrayList<Alquiler>();
		//Rastrea la lista de alquileres vigentes
		for(Alquiler a: listaAlquileresVigentes)
		{
			listaAlquileresTotales.add(a);
		}
		//Rastrea la lista de alquileres devueltos
		for(Alquiler a: listaAlquileresDevueltos)
		{
			listaAlquileresTotales.add(a);
		}
		//Los alquileres totales son los alquileres vigentes mas los devueltos
		return listaAlquileresTotales;
	}
}
