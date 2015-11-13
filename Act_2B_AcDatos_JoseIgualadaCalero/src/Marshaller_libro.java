import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;

public class Marshaller_libro {

	public static void main(String[] args){
		
		ArrayList<Libro> libros = new ArrayList<Libro>();
		
		Libro libro1 = new Libro("Titulo1", "Autor1", "Autor11" , "2001", "Editor1", "201");
		Libro libro2 = new Libro("Titulo2", "Autor2", "Autor22", "2002", "Editor2", "202");
		Libro libro3 = new Libro("Titulo3", "Autor3", "Autor33", "2003", "Editor3", "203");
		
		
		libros.add(libro1);
		libros.add(libro2);
		libros.add(libro3);
		
		//Creamos el objeto Marshsaller
		Marshaller marshaller = new Marshaller(libros);
		
		marshaller.crearDocumento();
		marshaller.crearAlbolDOM();
		
		File file = new File("biblioteca.xml");
		marshaller.escribirAXML(file);
		
	}
	
}
