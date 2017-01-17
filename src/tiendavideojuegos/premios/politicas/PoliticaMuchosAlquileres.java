package tiendavideojuegos.premios.politicas;

import java.util.Calendar;
import java.util.List;

import tiendavideojuegos.alquileres.Alquiler;
import tiendavideojuegos.constantes.IConstantesPremios;
import tiendavideojuegos.premios.PoliticaPremio;
import tiendavideojuegos.usuarios.Socio;

/**
 * Politica de premio que premia al usuario con una determinada cantidad si tiene al menos un
 * número de alquileres el mes anterior
 * 
 * @author iss031
 */
public class PoliticaMuchosAlquileres extends PoliticaPremio {

	
	/**
	 * Constructor de la politica sin parámetros
	 */
	public PoliticaMuchosAlquileres(){
		super();
	}
	

	@Override
	public void aplicarPolitica(Socio s, List<Alquiler> listadoAlquileres) {
		
		//Obtiene la fecha actual
		Calendar fechaActual= Calendar.getInstance();
		
		//Trucamos el sistema para que piense que estamos al día uno del mes siguiente
		fechaActual.add(Calendar.MONTH,1);
		fechaActual.set(Calendar.DAY_OF_MONTH,1);
		//Fin del código para trucar
		
		//Quitamos un mes a la fecha actual
		fechaActual.add(Calendar.MONTH, -1);
		
		//Obtenemos el día del mes. Si es el día 1
		if((fechaActual.get(Calendar.DAY_OF_MONTH))==1){
			
			int numAlquileresSocio=0;
			
			for(Alquiler a: listadoAlquileres){
				
				//Si el alquiler es del socio, incrementa la variable
				if(a.getSocio().equals(s)){
					
					//Cambiamos a Calendar
					Calendar fechaAlquiler= convertirFecha(a.getFechaInicio());
					
					//Obtenemos el mes de la fecha de Inicio del alquiler.
					int mes= fechaAlquiler.get(Calendar.MONTH);
					
					if(fechaActual.get(Calendar.MONTH)== mes){
						numAlquileresSocio++;
					}
				}
			}
			
			//Si el número de alquileres del socio en el mes anterior es mayor o igual a 3, se hace ingreso de 5 euros
			if(numAlquileresSocio>= IConstantesPremios.ALQUILERES_PARA_PREMIO){

				//Suponemos que se pueden pasar de los 100 euros si es premiado
				s.realizarIngresoSinLimite("Premio por más de " +  IConstantesPremios.ALQUILERES_PARA_PREMIO + " alquileres en un mes", 10);
			}
		}
	}
}

	