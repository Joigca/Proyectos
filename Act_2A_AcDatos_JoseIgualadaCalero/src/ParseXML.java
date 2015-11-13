
public class ParseXML {
	
	public static void main(String[] args){
		
		//Creamos el Lector
		Parser parser = new Parser();
		//Asignamos el fichero de lectura
		parser.parseFicheroXml("biblioteca.xml");
		//Transformamos
		parser.parseDocument();
		parser.print();
		
	}
}
