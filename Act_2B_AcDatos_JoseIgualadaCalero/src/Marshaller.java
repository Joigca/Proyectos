import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

public class Marshaller {
	
	private Document dom = null;
	private ArrayList<Libro> libros = null;
	
	public Marshaller(ArrayList<Libro> l){
		this.libros = l;
	}
	
	//Creamos documento
	public void crearDocumento(){
		//Creamos Factory
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		try {
			//Creamos DocumentBuilder
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			dom = documentBuilder.newDocument();
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void crearAlbolDOM(){
	
		//Elemento raiz de Libros
		Element element = dom.createElement("biblioteca");
		dom.appendChild(element);
		
			//Iteramos el ArrayList
			Iterator iterator = libros.iterator();
			while(iterator.hasNext()){
				//Por cada Libro, se creara uno y se añadira
				Libro lib = (Libro) iterator.next();
				Element libroEle = setLibro(lib);
				element.appendChild(libroEle);
			}
		}
		
		private Element setLibro(Libro l){
			
			Element LibroEle = dom.createElement("libro");
					
			//CREAMOS ELEMENTOS PARA CADA UNO DE LOS ATRIBUTOS DE LIBRO
			//LO GUARDAMOS EN TEXT PARA PODER UTILIZARLO
			
			Element tituloEle = dom.createElement("titulo");		
			Text tituloTexto = dom.createTextNode(l.getTitulo());
			tituloEle.appendChild(tituloTexto);
			LibroEle.appendChild(tituloEle);
			
			Element autorEle = dom.createElement("autor");		
			Text autorTexto = dom.createTextNode(l.getAutor());
			autorEle.appendChild(autorTexto);
			LibroEle.appendChild(autorEle);
			
			Element anyoEle = dom.createElement("anyo");
			Text anyoTexto = dom.createTextNode(l.getAnyo());
			autorEle.appendChild(autorTexto);
			LibroEle.appendChild(autorEle);
			
			Element editorEle = dom.createElement("editor");
			Text editorTexto = dom.createTextNode(l.getEditor());
			editorEle.appendChild(editorTexto);
			LibroEle.appendChild(editorEle);
			
			Element numPaginaEle = dom.createElement("numPagina");
			Text numPaginaTexto = dom.createTextNode(l.getNumPagina());
			numPaginaEle.appendChild(numPaginaTexto);
			LibroEle.appendChild(numPaginaEle);
			
			return LibroEle;
		}
		
		public void escribirAXML(File f){
			
			try {
				//Instancia para escribir el resultado
				Transformer trans = TransformerFactory.newInstance().newTransformer();
				trans.setOutputProperty(OutputKeys.INDENT, "yes");
				
				//Donde escibimos y fuente de datos
				StreamResult result = new StreamResult(f);
				DOMSource domSource = new DOMSource(dom);
				trans.transform(domSource, result);
				
			} catch (TransformerFactoryConfigurationError | TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
}
