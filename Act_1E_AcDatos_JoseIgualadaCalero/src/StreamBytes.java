//Jose Igualada Calero

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class StreamBytes {
	
	public static void main(String[] args) throws IOException {
			abrirFichero();
	}
		
	//Abrimos un fichero cualquiera
	
	public static void abrirFichero() throws IOException{
		
		Scanner sc = new Scanner(System.in);		
		System.out.println("Inserte el nombre del fichero origen");
		String fOrigen = sc.nextLine();
		File fich1 = new File(fOrigen);
		
		while(!fich1.exists()){
			
				System.out.println("El fichero que ha declarado, no existe");
				fOrigen = sc.nextLine();
				fich1 = new File(fOrigen);
		}
		
		System.out.println("Inserte el nombre del fichero destino");
		String fDestino = sc.nextLine();
		File fich2 = new File(fDestino);
		
		while(fich2.exists()){
			System.out.println("El fichero destino ya existe, indique uno distinto");
			fDestino = sc.nextLine();
			fich2 = new File(fDestino);
		}
		cerrar(sc);
		copiaBytes(fich1, fich2);
		
	}	
	

	//Copiamos los bytes de un archivo a otro
	public static void copiaBytes(File fich1, File fich2) throws IOException{
		
		
		FileInputStream origen = null;
		FileOutputStream destino = null;
		
		try {
			origen = new FileInputStream(fich1);
			destino = new FileOutputStream(fich2);
			
			byte[] buffer = new byte[1];
			int cont = 0;
			
			//Se leen los bytes
			while (origen.read(buffer) != -1){
				cont++;
				destino.write(buffer, 0, 1);
			}
		}finally{
			cerrar(origen);
			cerrar(destino);
		}
	}
	
	public static void cerrar(Closeable c){
		try {
			c.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	

