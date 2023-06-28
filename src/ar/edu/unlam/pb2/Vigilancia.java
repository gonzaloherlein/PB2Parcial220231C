package ar.edu.unlam.pb2;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Vigilancia {

	private String nombre;

	// Colocar el tipo de coleccion que corresponda
	private Collection<Persona> personas;
	// Colocar el tipo de coleccion que corresponda
	private Collection<Banco> bancos;
	//No se puede cambiar 
	private Map <Integer, Atraco> atracos;

	public Vigilancia(String nombre) {
		this.nombre = nombre;
		this.personas = new HashSet<Persona>();
		this.bancos = new HashSet<Banco>();
		this.atracos = new HashMap<Integer, Atraco>();
	}
	
	
	

	/*
	 * Registra tanto como Vigiladores como atracadores no permite registrar 2
	 * personas con el mismo DNI. Si esto sucede lanza una exception
	 * PersonaDuplicadaException
	 * 
	 */

	public void registrarPersona(Persona persona) throws PersonaDuplicadaException {
		
			if(!(this.personas.add(persona))) {
				throw new PersonaDuplicadaException("La persona está duplicada");
			}
	}

	public void agregarBanco(Banco banco) {
			this.bancos.add(banco);
	}

	/*
	 * Este Metodo lanza las siguientes Excepciones NoSeEncuentraAtracadorException
	 * BancoNoEncontradoExcecption
	 * 
	 */
	
	public void registrarAtraco(Integer dni, Integer idBanco) throws NoSeEncuentraAtracadorException, BancoNoEncontradoException {
		
		Persona atracador = buscarPersona(dni);
		Banco banco = buscarBanco(idBanco);
		
		Atraco atraco = new Atraco (atracador,banco);		
		
		//Se debe agregar un atraco al Mapa
		this.atracos.put(idBanco, atraco);
	}
	

	
	private Banco buscarBanco(Integer idBanco) throws BancoNoEncontradoException {
			for (Banco banco : bancos) {
				if(banco.getIdBanco().equals(idBanco)) {
					return banco;
				}
			}
			throw new BancoNoEncontradoException("El banco no ha sido encontrado");
		
	}




	private Persona buscarPersona(Integer dni) throws NoSeEncuentraAtracadorException {
			for (Persona persona : personas) {
				if(persona.getId().equals(dni)) {
					return persona;
				}
			}
			throw new NoSeEncuentraAtracadorException("No se ha encontrado Atracador/Vigilante");
		
	}




	//Si la clave no existe lanza ClaveInexistenteException
	public Atraco buscarAtracoPorClave(Integer claveAtraco) throws ClaveInexistenteException {
		Atraco atracoABuscar = this.atracos.get(claveAtraco);
		return atracoABuscar;			 
	}
	
	
	
	public Vigilante obtenerElVigiladorDeUnAtraco(Integer claveAtraco) throws ClaveInexistenteException {
		Atraco atraco = buscarAtracoPorClave(claveAtraco);
		
		Vigilante vigilante = (Vigilante) atraco.getPersona();
		
		return vigilante;
	}

	
	public TreeSet<Persona> obtenerAtracadoresOrdenados(){
		
		Comparator<Persona> ordenPorApodo = new OrdenPorApodos();
		
		TreeSet<Persona> atracadoresOrdenadados = new TreeSet<Persona>(ordenPorApodo);
		
		for (Persona persona : personas) {
			if(persona instanceof Atracador) {
				atracadoresOrdenadados.addAll(this.personas);
			}
		}
		
		return atracadoresOrdenadados;
	
	}
	
	public Integer obtenerCantidadDePersonas() {
		return this.personas.size();
	}

}
