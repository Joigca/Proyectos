import java.io.Serializable;
import java.util.ArrayList;

public class Libro implements Serializable{
	private String titulo;
	private ArrayList<String> autor;
	private String anyo;
	private String editor;
	private String numPagina;
	
	public Libro(){
		
	}
	
	public Libro(String titulo, String nombre, String apellido, String anyo, String editor, String numPagina){
		
		this.autor = new ArrayList<String>();
		this.titulo = titulo;
		this.anyo = anyo;
		this.editor = editor;
		this.numPagina = numPagina;
		this.autor.add(nombre);
		this.autor.add(apellido);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public ArrayList<String> getAutor() {
		return autor;
	}

	public void setAutor(ArrayList<String> autor) {
		this.autor = autor;
	}

	public String getAnyo() {
		return anyo;
	}

	public void setAnyo(String anyo) {
		anyo = anyo;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		editor = editor;
	}

	public String getNumPagina() {
		return numPagina;
	}

	public void setNumPagina(String numPagina) {
		numPagina = numPagina;
	}
	
}