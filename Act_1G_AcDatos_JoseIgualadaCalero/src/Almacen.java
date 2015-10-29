import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Almacen {

	public void Almacen(){
		
	}
	
	public void guardarLibro(Libro l, String f){
	
		//Guardaremos los datos del libro en un fichero
		
		ObjectOutputStream out = null;
		
		try {
			
			out = new ObjectOutputStream(new FileOutputStream(f));
			out.writeObject(l);
			
		} catch (IOException e) {			
			e.printStackTrace();			
		} finally{			
			intentarCerrar(out);			
		}
	}
	
	public Libro recuperarLibro(String f){
		
		//Recuperamos el libro
		
		Libro l = null;
		ObjectInputStream in = null;
		
		try{
			
			in = new ObjectInputStream(new FileInputStream(f));
			l = (Libro) in.readObject();
			
		}catch(ClassNotFoundException ex){
			System.err.println("Error de fichero");
		}catch (IOException ex) {
			System.err.println("Error IO");
		}finally{
			intentarCerrar(in);
		}
		
		return l;
	}
	
	public void guardarLista(ArrayList<Libro> libro, String f){
		
		//Guardaremos todos los los datos del libro en un array, y a su vez en un fichero.
		
		ObjectOutputStream out = null;
		
		try{
			
			File file = new File(f);
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(libro);
			
		} catch (IOException e) {			
			e.printStackTrace();			
		} finally{			
			intentarCerrar(out);			
		}
	}
	
	public ArrayList<Libro> recuperarLista(String f){
		
		//Introuciremos todos los libros que recuperemos en un ArrayList, para poder tener acceso a ellos.		
		ArrayList<Libro> listaDeLibros = new ArrayList<Libro>();
		ObjectInputStream in = null;
		
		try{
			
			in = new ObjectInputStream(new FileInputStream(f));
			listaDeLibros = (ArrayList<Libro>) in.readObject();
			
		}catch(ClassNotFoundException ex){
			System.err.println("Error de fichero");
		}catch (IOException ex) {
			System.err.println("Error IO");
		}finally{
			intentarCerrar(in);
		}
		
		return listaDeLibros;
	}
	
	public void intentarCerrar(Closeable c){		
		try {			
			if(c != null){
				c.close();			
			}			
		} catch (IOException e) {			
			e.printStackTrace();				
		}			
	}
	
}
