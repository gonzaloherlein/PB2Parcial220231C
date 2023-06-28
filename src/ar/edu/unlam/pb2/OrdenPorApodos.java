package ar.edu.unlam.pb2;

import java.util.Comparator;

public class OrdenPorApodos implements Comparator<Persona>{

	@Override
	public int compare(Persona o1, Persona o2) {
		
		return o1.getApodo().compareTo(o2.getApodo());
	}

	
		
}
