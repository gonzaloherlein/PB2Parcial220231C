package ar.edu.unlam.pb2;

public class Atracador extends Persona {
	
	private Banda banda;

	public Atracador(Integer dni, String nombre, String apellido, String apodo, Banda banda) {
		super(dni, nombre, apellido, apodo);
		this.banda = banda;
	}

}
