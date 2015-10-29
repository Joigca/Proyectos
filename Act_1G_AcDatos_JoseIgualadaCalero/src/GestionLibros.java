import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

//Jose Igualada Calero

public class GestionLibros {
	
	private static Almacen almacen;
	
	public static void main(String[] args) {
		
		almacen = new Almacen();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("GESTION DE LIBROS");
		System.out.println("*******************************************************");
		System.out.println("A continuación, le mostraremos las opciones disponibles");
		System.out.println("-------------------------------------------------------");
		System.out.println("1 --> Guardar libro");
		System.out.println("2 --> Guardar lista de libros");
		System.out.println("3 --> Recuperar datos del libro");
		System.out.println("4 --> Recuperar datos de la lista");
		System.out.println("5 --> Modificar título de libro");
		System.out.println("6 --> Modficiar autor de libro");
		System.out.println("-------------------------------------------------------");
		
		String seleccion = sc.nextLine();
		while(!isNumeric(seleccion)){
			System.out.println("Inserte un valor correcto, gracias.");
			seleccion = sc.nextLine();
			
		}
		
		switch(seleccion){
			case "1":guardarLibro();
			break;
			
			case "2":guardarLista();
			break;
			
			case "3":recuperarLibro();
			break;
			
			case "4":recuperarLista();
			break;
			
			case "5":modificarTitulo();
			break;
			
			case "6":modificarAutor();
			break;
			
			default: System.err.println("Se ha ocurrido un error.");
		}
		intentarCerrar(sc);
	}
	
	//Este método guardara un libro nuevo.
	public static void guardarLibro(){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Ingrese los datos necesarios para guardar un libro nuevo");
		System.out.println("Nombre del fichero donde desea guardar el libro");
		String fichero = sc.nextLine();
		
		File fich = new File(fichero);
		while(fich.exists()){
			System.err.println("El fichero ya existe, modifique el nombre del fichero");
			fichero = sc.nextLine();
			fich = new File(fichero);
		}
		
		System.out.print("Indique el título: ");
		String titulo = sc.nextLine();
		
		System.out.print("Indique el autor: ");
		String autor = sc.nextLine();
		
		while(isNumeric(autor)){
			System.err.println("Indique un valor correcto");
			autor = sc.nextLine();
		}
		
		System.out.print("Indique el año de publicación: ");
		String anyo = sc.nextLine();
		
		while(!isNumeric(anyo)){
			System.err.println("Indique un valor correcto");
			anyo = sc.nextLine();
		}
				
		System.out.print("Indique editor: ");
		String editor = sc.nextLine();
		
		while(isNumeric(editor)){
			System.err.println("Indique un valor correcto");
			editor = sc.nextLine();
		}
		
		System.out.print("Indique el número de páginas: ");
		String numPagina = sc.nextLine();
		
		while(isNumeric(numPagina)){
			System.err.println("Indique un valor correcto");
			numPagina = sc.nextLine();
		}
		
		
		Libro libro = new Libro(titulo, autor, anyo, editor, numPagina);
		
		intentarCerrar(sc);
		almacen.guardarLibro(libro, fichero);
	}
	
	public static void guardarLista(){
		
	}
	
	public static void recuperarLibro(){
		
	}
	
	public static void recuperarLista(){
		
	}
	
	public static void modificarTitulo(){
		
	}
	
	public static void modificarAutor(){
		
	}
	
	public static void intentarCerrar(Closeable c){		
		try {			
			if(c != null){
				c.close();			
			}			
		} catch (IOException e) {			
			e.printStackTrace();				
		}			
	}
	
    private static boolean isNumeric(String cadena){
    	try {
    		Integer.parseInt(cadena);
    		return true;
    	} catch (NumberFormatException nfe){
    		return false;
    	}
    }
	
}
