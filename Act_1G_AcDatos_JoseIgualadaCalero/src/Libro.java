import java.io.Serializable;

public class Libro implements Serializable{
	static String titulo;
	static String autor;
	static String anyo;
	static String editor;
	static String numPagina;
	
	public Libro(){
		
	}
	
	public Libro(String titulo, String autor, String anyo, String editor, String numPagina){
		
		this.titulo = titulo;
		this.autor = autor;
		this.anyo = anyo;
		this.editor = editor;
		this.numPagina = numPagina;
	}

	public static String getTitulo() {
		return titulo;
	}

	public static void setTitulo(String titulo) {
		Libro.titulo = titulo;
	}

	public static String getAutor() {
		return autor;
	}

	public static void setAutor(String autor) {
		Libro.autor = autor;
	}

	public static String getAnyo() {
		return anyo;
	}

	public static void setAnyo(String anyo) {
		Libro.anyo = anyo;
	}

	public static String getEditor() {
		return editor;
	}

	public static void setEditor(String editor) {
		Libro.editor = editor;
	}

	public static String getNumPagina() {
		return numPagina;
	}

	public static void setNumPagina(String numPagina) {
		Libro.numPagina = numPagina;
	}
	
	public void print(){
		
		System.out.println("Título: " + getTitulo() + ", escrito por " + getAutor() + " en " + getAnyo());
		
	}
	
}
