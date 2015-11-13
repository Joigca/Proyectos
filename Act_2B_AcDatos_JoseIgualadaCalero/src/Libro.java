import java.io.Serializable;

public class Libro implements Serializable{
	private String titulo;
	private String autor;
	private String anyo;
	private String editor;
	private String numPagina;
	
	public Libro(){
		
	}
	
	public Libro(String titulo, String autor, String anyo, String editor, String numPagina){
		
		this.titulo = titulo;
		this.autor = autor;
		this.anyo = anyo;
		this.editor = editor;
		this.numPagina = numPagina;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		autor = autor;
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
	
	public void print(){
		
		System.out.println("Título: " + getTitulo() + ", escrito por " + getAutor() + " en " + getAnyo());
		
	}
	
}