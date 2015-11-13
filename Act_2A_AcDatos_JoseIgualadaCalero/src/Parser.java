import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Parser {

	private Document dom = null;
	private ArrayList<Libro> libros = null;
	
	public Parser(){
		
		//Constructor
		dom = null;
		libros = new ArrayList<Libro>();
	} 
	
	public void parseFicheroXml(String fichero){
		
		//Creación de un factory
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
		
		try {
			
			//Creamos del DocumentBuilder
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			//Parseamos el archivo XML y lo añadimos al Document
			dom = documentBuilder.parse (fichero);			
			
			
		} catch (ParserConfigurationException e) {			
			e.printStackTrace();
			
		} catch (SAXException e) {			
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void parseDocument(){
		
		//Recuperamos el Element
		Element element = dom.getDocumentElement();
		
		//Se obtiene el NodeList de elementos
		NodeList nodelist = element.getElementsByTagName("libro");
		if(nodelist != null && nodelist.getLength() > 0){
			for(int i = 0; i < nodelist.getLength(); i++){
				
				//Obtenemos todo el Elemento Libro
				Element el = (Element) nodelist.item(i);
				//Obtenemos el Libro
				Libro lib = getLibro(el);
				//Añadimos al arrayi
				libros.add(lib);		
				
			}
			
		}
		
	}
	
	private Libro getLibro(Element elementLibro){
	
		//Recuperamos los datos de cada Elemento Libro
		String titulo = getTextValue(elementLibro, "titulo");
		String autor = getSubtextValue(elementLibro, "autor");
		String anyo = getAtrValue(elementLibro, "titulo");
		String editor = getTextValue(elementLibro, "editor");
		String numPagina = getTextValue(elementLibro, "numPagina");
		
		//Creamos el libro con los datos recogidos
		Libro l = new Libro(titulo, autor, anyo, editor, numPagina);
		return l;
		
	}
	
	private String getTextValue(Element ele, String tagName){
		
		String txtVal = null;
		NodeList list = ele.getElementsByTagName(tagName);
		if(list != null && list.getLength() > 0){
			
			Element el = (Element) list.item(0);
			txtVal = el.getFirstChild().getNodeValue();
			
		}		
		return txtVal;		
	}
	
	//METODO PARA COGER TODOS LOS VALORES DENTRO DE UNA ETIQUETA
	private String getSubtextValue(Element ele, String tag){
		String textVal = null;
		NodeList nodeList = ele.getElementsByTagName(tag);
		if(nodeList != null && nodeList.getLength() > 0){
			Element element = (Element) nodeList.item(0);
			textVal = ( (Node) element.getChildNodes()).getTextContent();
		}
		
		return textVal;
		
	}
	
	//METODOO PARA COGER EL ATRIBUTO DENTRO DE UNA ETIQUETA
	private String getAtrValue(Element element, String atrTag){
		
		NodeList list = element.getElementsByTagName(atrTag);
		//Mapa de nodos
		NamedNodeMap namedNodeMap = list.item(0).getAttributes();
		String anyo = namedNodeMap.item(0).getTextContent();
		return anyo;
	}
	
	public void print(){		
		Iterator<Libro> iterator = libros.iterator();
		while(iterator.hasNext()){
			
			Libro l = (Libro) iterator.next();
			//l.print();
			
			System.out.println("");
			System.out.println("****************Libro****************");
			System.out.println("\tTítulo --> " + l.getTitulo());
			System.out.println("\tAutor --> " + l.getAutor());
			System.out.println("\tAño --> " + l.getAnyo());
			System.out.println("\tEditor --> " + l.getEditor());
			System.out.println("\tNúmero de páginas --> " + l.getNumPagina());
			System.out.println("*************************************");
			
		}		
	}	
}