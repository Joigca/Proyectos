//Jose Igualada Calero

import java.util.List;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FlujoDatos {
	
	public static void main(String[] args) throws IOException {
		
		//Declaramos los dos ficheros y lo enlazamos a los nuestros
		File fichero1 = new File("fichero1.txt");
		File fichero2 = new File("fichero2.txt");
		
		//Ej.1
		
		compararContenido(fichero1, fichero2);	
		
		//Ej. 2
		
		buscarPalabra(fichero1, false);
	}

	
	//Se compararán ambos ficheros, devolvera true en caso de que el contenido sea el mismo
	//y falso, en caso de que no lo sean
	
	public static  boolean compararContenido (File fichero1, File fichero2) throws IOException{
		
		//Leeemos el contenido de nuestros ficheros.
		
		BufferedReader br1 = new BufferedReader(new FileReader(fichero1));
		BufferedReader br2 = new BufferedReader(new FileReader(fichero2));
		
		String str1, str2 = null;
		
		//Almacenamos en contenido de los ficheros en ArrayList, porque no sabemos el tamaño que tendran.
		
		List<String> cont1 = new ArrayList<String>();
		List<String> cont2 = new ArrayList<String>();
		
		//Rellenamos los dos String
		
		while((str1 = br1.readLine()) != null){
			cont1.add(str1);
		}
		
		while((str2 = br2.readLine()) != null){
			cont2.add(str2);
		}
		
		//Cerramos ficheros
		
		br1.close();
		br2.close();
		
		//Comprobacion si son iguales.
		
		if(cont1.equals(cont2)){
			
			System.out.println("El contenido de los ambos ficheros coincide");
			return true;
			
		}else{
			
			System.out.println("El contenido de los ficheros no coincide");
			return false;
			
		}
		
	}
	
	//Buscará una palabra en el fichero y en caso de encontrarla, devolvera en que linea se
	//encuentra la primera o ultima letra, dependiendo del segundo parametro
	
	public static int buscarPalabra (File fichero1, boolean primera){
		
		int cont = 0;//Contador de linea
		int numLinea = 0;//Linea donde nos encontramos
		String linea = ""; //Contenido de cada linea
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Indique la palabra que desea buscar");
		String palabra = sc.nextLine();
		
		//Leemos el contenido de nuestro fichero
		
		try {
			
			BufferedReader br1 = new BufferedReader(new FileReader(fichero1));
			
			try {
				//Mientras quede contenido en el fichero, se lo asignamos al String
				
				while((linea = br1.readLine()) != null){
					cont++;
					
					//En el momento que coinicida el texto, se parara el contador de lineas.
					
					if(linea.equals(palabra) == true){
						numLinea = cont;
						
						//Detallaremos si cogemos la linea en la que empieza, o acaba.
						
						if(primera == true){
							break;
						}
					}
				}
				br1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(palabra + " se encuentra en la linea "+numLinea);
				
		return numLinea;
	}
}
