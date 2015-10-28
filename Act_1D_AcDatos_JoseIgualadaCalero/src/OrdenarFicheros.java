import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrdenarFicheros {
	
	public static void main(String[] args) throws IOException {
		
		ordenar();

	}
	
	public static void ordenar() throws IOException{
		
		Scanner sc = new Scanner(System.in);		
		System.out.println("Inserte el nombre del fichero origen");
		String fOrigen = sc.nextLine();
		File fich1 = new File(fOrigen + ".txt");
		
		while(!fich1.exists()){
			
				System.out.println("El fichero que ha declarado, no existe");
				fOrigen = sc.nextLine();
				fich1 = new File(fOrigen + ".txt");
		}
		
		System.out.println("Inserte el nombre del fichero destino");
		String fDestino = sc.nextLine();
		File fich2 = new File(fDestino + ".txt");
		
		while(fich2.exists()){
			System.out.println("El fichero destino ya existe, indique uno distinto");
			fDestino = sc.nextLine();
			fich2 = new File(fDestino + ".txt");
		}
		
		System.out.println("Inserte un '0' para hacer un orden estandar, o '1' para un orden inverso");
		Integer natural = sc.nextInt();
		
		
		 
	  	while((natural != 1) && (natural != 0)){
		System.out.println("Inserte 0 o 1");
		natural = sc.nextInt();
		
		}
		
		
		
		ordenarFichero(fich1, fich2, natural);
	}
	
	public static void ordenarFichero(File origen, File destino, int tipo_orden) throws IOException{
		
		
		
		//Leemos el contenido de nuestro fichero
		
		BufferedReader br1 = new BufferedReader(new FileReader(origen));
		
		String str1 = "";
		
		//Rellenamos el ArrayList con el contenido del fihero de entrada
		ArrayList<String> entada = new ArrayList<String>();
		
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(destino));
		
		//Mientras quede contenido, se le asignará al Array
		while((str1 = br1.readLine()) != null){
			entada.add(str1);
		}
		
		//Dependiendo de tipo_orden, se hara en orden natural o inverso
		
		if(tipo_orden == 0){
			
			for(int i = 0; i <= entada.size() -1; i++){
				
				bw1.write(entada.get(i)+ "\n");
				
			}
		}
		if(tipo_orden == 1){
			
				for(int i = entada.size()-1; i >= 0;  i--){
				
				bw1.write(entada.get(i)+ "\n");
				
			}
			
		}
		
		br1.close();
		bw1.close();
	}

}
