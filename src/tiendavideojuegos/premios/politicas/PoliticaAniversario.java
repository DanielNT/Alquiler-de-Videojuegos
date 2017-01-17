package tiendavideojuegos.premios.politicas;

import java.util.Calendar;
import java.util.List;

import tiendavideojuegos.alquileres.Alquiler;
import tiendavideojuegos.premios.PoliticaPremio;
import tiendavideojuegos.tarjetas.ExcepcionTarjeta;
import tiendavideojuegos.usuarios.Socio;

/**
 * Politica de premio que premia al usuario con una determinada cantidad el día de su cumpleaños
 * 
 * @author iss031
 */
public class PoliticaAniversario extends PoliticaPremio {

	//TODO: habría que implementar el cuerpo de esta clase.
	//TODO: **MUY IMPORTANTE**: debe tener un constructor sin parámetros, que será invocado cuando se instance por la factoría.
	
	/**
	 * Constructor sin parámetros
	 */
	public PoliticaAniversario(){
		super();
	}
	
	@Override
	public void aplicarPolitica(Socio s, List<Alquiler> listadoAlquileres){
		
		//Obtiene la fecha actual 
		Calendar fechaActual= Calendar.getInstance();
		
		//Obtenemos la fecha de nacimiento del socio
		Calendar fechaNacimiento= convertirFecha(s.getNacimiento());

		//Obtenemos el día y el mes y comparamos
		if((fechaActual.get(Calendar.MONTH))==(fechaNacimiento.get(Calendar.MONTH))){
					
			//Si el día y el mes coinciden, es su cumpleaños
			if((fechaActual.get(Calendar.DAY_OF_MONTH))==(fechaNacimiento.get(Calendar.DAY_OF_MONTH))){
					
				//Carga 5 euros de saldo
				try {
					s.realizarIngreso("Premio por cumpleaños",5);
				} catch (ExcepcionTarjeta e) {
					//???
				}
								
			}
		}
			
	}
}
	
	
//	@Override
//	public void aplicarPolitica(){
//		
//		//TODO: Está en la superclase??????????
//		//Obtenemos el listado de socios
//		List<Socio> listadoSocios= cu.recuperarListaSocios();
//		
//		//Obtiene la fecha actual
//		Calendar fechaActual= Calendar.getInstance();
//		
//		for(Socio s: listadoSocios){
//			
//			//Obtenemos la fecha de nacimiento del socio
//			Calendar fechaNacimiento= convertirFecha(s.getNacimiento());
//
//			//Obtenemos el día y el mes y comparamos
//			if((fechaActual.get(Calendar.MONTH))==(fechaNacimiento.get(Calendar.MONTH))){
//					
//				//Si el día y el mes coinciden, es su cumpleaños
//				if((fechaActual.get(Calendar.DAY_OF_MONTH))==(fechaNacimiento.get(Calendar.DAY_OF_MONTH))){
//					
//					//Carga 5 euros de saldo
//					try {
//						cu.realizarIngresoEnTarjetaSocio(s.getLogin(), 5);
//					} catch (ExcepcionUsuario | ExcepcionTarjeta e) {
//						//No deberia llegarse aquí puesto que el socio EXISTE
//						//TODO: ¿En algún caso puede fallar?
//					}
//					
//				}
//			}
//			
//		}
//	}

//}
