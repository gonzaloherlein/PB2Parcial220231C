package ar.edu.unlam.pb2;

import static org.junit.Assert.*;
import java.util.TreeSet;

import org.junit.Test;

public class TestSistema {


	@Test
	public void testQueRegistroUnBanco() {
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");
			
		Integer idbanco=1;
		Banco banco = new Banco(idbanco, "la Casa de Papel");
		vigilancia.agregarBanco (banco);
		
		
	}
	
	@Test
	public void quePuedaRegistrarUnAtracador() throws PersonaDuplicadaException {
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");
		Integer idbanda = 1;
		String nombre="La Casa de Papel";
		Banda banda = new Banda (idbanda,nombre);
		
		Integer dni= 1;
		nombre="Ursula";
		String apellido= "Corbero";
		String apodo="Tokio";
		
		//No Cambiar esta Linea
		Persona atracador = new Atracador (dni, nombre, apellido,apodo,banda );
		vigilancia.registrarPersona (atracador);
		Integer valorEsperado = 1;
		
		//Compleatar el Assert
		assertEquals(valorEsperado,vigilancia.obtenerCantidadDePersonas());
		
	}
	
	
	@Test(expected=PersonaDuplicadaException.class)
	public void queCuandoSeRegistre2PersonasConElMismoDNiLanceUnaExpcionPersonaDuplicadaException() throws PersonaDuplicadaException {
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");
		//Creo Vigilante
		
		
		Persona vigilante = new Vigilante (1, "Pepito", "Pistolero", new Banco(1, "LA Casa De Papel") );
		vigilancia.registrarPersona (vigilante);
		//Creo Banda
		Integer idBanda = 1;
		String nombre="La Casa de Papel";
		Banda banda = new Banda (idBanda,nombre);
		//Creo Atracador
		Integer dni= 1;
		nombre="Ursula";
		String apellido= "Corbero";
		String apodo="Tokio";
		//No Cambiar esta Linea
		Persona atracador = new Atracador (dni, nombre, apellido,apodo,banda );
		vigilancia.registrarPersona (atracador);
		 
	}
	

	@Test
	public void queSePuedaRegistraUnAtraco () throws NoSeEncuentraAtracadorException, BancoNoEncontradoException, PersonaDuplicadaException, ClaveInexistenteException {
		
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");
		Integer idBanda = 1;
		String nombre="La Casa de Papel";
		Banda banda = new Banda (idBanda,nombre);
		
		Integer dni= 1;
		nombre="Ursula";
		String apellido= "Corbero";
		String apodo="Tokio";
		
		//No Cambiar esta Linea
		Persona atracador = new Atracador (dni, nombre, apellido,apodo,banda);
		vigilancia.registrarPersona (atracador);
		
		Integer idBanco=1;
		Banco banco = new Banco(idBanco, "la Casa de Papel");
		vigilancia.agregarBanco (banco);
		
		vigilancia.registrarAtraco(dni, idBanco);
		
		Integer claveAtraco=1;
		Atraco atraco = vigilancia.buscarAtracoPorClave(claveAtraco);
		
		//No cambiar
		assertTrue(banco.equals(atraco.getBanco()));
		
		
	}
	
	@Test
	public void queSePuedaObtenerLosAtracadoresOrdenadosPorApodos() throws PersonaDuplicadaException {
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");
		Integer idBanda = 1;
		String nombre="La Casa de Papel";
		Banda banda = new Banda (idBanda,nombre);
		
		Integer dni= 1;
		nombre="Ursula";
		String apellido= "Corbero";
		String apodo="Tokio";
		
		Persona atracador = new Atracador (dni, nombre, apellido,apodo,banda);
		Persona atracador2 = new Atracador (2, nombre, apellido,"Andres",banda);
		Persona atracador3 = new Atracador (3, nombre, apellido,"Bolivia",banda);
		
		vigilancia.registrarPersona(atracador);
		vigilancia.registrarPersona(atracador2);
		vigilancia.registrarPersona(atracador3);
		
		TreeSet<Persona> atracadoresOrdenados = vigilancia.obtenerAtracadoresOrdenados();
		
		assertEquals(atracadoresOrdenados.first(), atracador2);
		assertEquals(atracadoresOrdenados.last(), atracador);
	}
	
	
	// Cree un 3 test a su eleccion para que pruebe el resto de las funcionalidades
	@Test
	public void queSePuedaObtenerUnVigilanteDeUnAtraco() throws PersonaDuplicadaException, NoSeEncuentraAtracadorException, BancoNoEncontradoException, ClaveInexistenteException {
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");

		
		Integer idBanco=1;
		Banco banco = new Banco(idBanco, "la Casa de Papel");
		vigilancia.agregarBanco (banco);
		
		Integer dni= 1;
		String nombre="Ursula";
		String apellido= "Corbero";
		
		Persona vigilador = new Vigilante(dni,nombre,apellido,banco);
		Persona vigilador2 = new Vigilante(2,nombre,apellido,banco);
		
		vigilancia.registrarPersona(vigilador);
		vigilancia.registrarPersona(vigilador2);
		
		vigilancia.registrarAtraco(dni, idBanco);
		
		Integer claveAtraco = 1;
		Persona vigilanteABuscar = vigilancia.obtenerElVigiladorDeUnAtraco(claveAtraco);
		
		assertEquals(vigilanteABuscar, vigilador);
	}
	
	@Test (expected = NoSeEncuentraAtracadorException.class)
	public void queNoSePuedaAtracarUnBanco() throws PersonaDuplicadaException, NoSeEncuentraAtracadorException, BancoNoEncontradoException {
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");

		Integer idBanda = 1;
		String nombreDeLaBanda="La Casa de Papel";
		Banda banda = new Banda (idBanda,nombreDeLaBanda);
		
		Integer idBanco=1;
		Banco banco = new Banco(idBanco, "la Casa de Papel");
		vigilancia.agregarBanco (banco);
		
		Integer dni= 1;
		String nombre="Ursula";
		String apellido= "Corbero";
		String apodo = "Tokyo";
		
		Persona atracador = new Atracador(dni,nombre,apellido,apodo,banda);
		
		vigilancia.registrarPersona(atracador);
		
		vigilancia.registrarAtraco(32, idBanco);
		
	}
	
	@Test (expected = BancoNoEncontradoException.class)
	public void queNoSeEncuentreBancoParaAtracar() throws PersonaDuplicadaException, NoSeEncuentraAtracadorException, BancoNoEncontradoException {
		Vigilancia vigilancia = new Vigilancia ("Nombre De la Compania de Vigilancia");

		Integer idBanda = 1;
		String nombreDeLaBanda="La Casa de Papel";
		Banda banda = new Banda (idBanda,nombreDeLaBanda);
		
		Integer idBanco=1;
		Banco banco = new Banco(idBanco, "la Casa de Papel");
		vigilancia.agregarBanco (banco);
		
		Integer dni= 1;
		String nombre="Ursula";
		String apellido= "Corbero";
		String apodo = "Tokyo";
		
		Persona atracador = new Atracador(dni,nombre,apellido,apodo,banda);
		
		vigilancia.registrarPersona(atracador);
		
		vigilancia.registrarAtraco(dni, 24);
	}
}

