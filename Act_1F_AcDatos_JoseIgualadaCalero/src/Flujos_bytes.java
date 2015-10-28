import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Flujos_bytes {

	public static void main(String[] args) {
		
		try {
			//Recogemos los datos del tratamiento de la Fotografía
			Scanner sc = new Scanner(System.in);		
			System.out.println("Tratamiento de Fotografía");
			System.out.println("Inserte el nombre de la imagen");
			String fOrigen = sc.nextLine();
			
			File f = new File(fOrigen);
			
			//Comprobamos que existe el fichero de la imagen
			while(!f.exists()){
				
				System.out.println("Vuelva a insertar el nombre de su imagen.");
				fOrigen = sc.nextLine();
				f = new File(fOrigen);
				
			}
			
			System.out.println("Inserte la opción que prefiera");
			System.out.println(" r --> Rotar imagen 90º ");
			System.out.println(" e --> Efecto espejo ");
			String seleccion = sc.nextLine();
			
			System.out.println();
			
			while(seleccion == "r"  || seleccion == "e"){
				System.out.println("Inserte un valor correcto, gracias.");
				seleccion = sc.nextLine();
				
			}
			
				switch(seleccion){
				
					case "r": rotarImagen(fOrigen); 
					break;
					
					case "e": espejo(fOrigen);
					break;	
					
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
	//ESTE MÉTODO SE ENCARGARÁ DE ROTAR LA IMAGEN 90º.
	public static void rotarImagen(String img) throws IOException{
		
		BufferedImage imagen;
		File f = new File(img);
		
		//Input, Output de la imagen.
		imagen = ImageIO.read(f);
		
		//Para poder rotar la imagen, copiaremos la imagen en otro BufferedImage.
		BufferedImage rotada = new BufferedImage(imagen.getWidth(), imagen.getHeight(), imagen.getType());
		
		//Llamaremos a Graphics2D, con nuestra copia de la imagen, para poder trabajar con los ejes x, y
		Graphics2D g = rotada.createGraphics();
		g.rotate(Math.toRadians(90.0));
		//Guardamos la nueva imagen rotada
		g.drawImage(imagen, 0, -rotada.getWidth(), null);
		
		
		//Creamos nuevo fichero con la imagen rotada
		ImageIO.write(rotada, "JPG", new File("imgRotada.jpg"));
		
		System.out.println("Proceso finalizado.");
	}
	
	//ESTE MÉTODO SE ENCARGARA DE CREAR UNA MISMA IMAGEN CON EFECTO ESPEJO.
	public static void espejo(String img) throws IOException {
		
		BufferedImage imagen;
		File f = new File(img);
		
		//Input, Output de la imagen.
		imagen = ImageIO.read(f);
		
		//Para poder rotar la imagen, copiaremos la imagen en otro BufferedImage.
		BufferedImage espejo = new BufferedImage(imagen.getHeight(), imagen.getWidth(),imagen.getType());
		
		//Utilizamos createFlipped para poder ejecutar el efecto espejo en la imagen.
		espejo = createFlipped(imagen);
		
		//Creamos nuevo fichero con la imagen rotada
		ImageIO.write(espejo, "JPG", new File("espejo.jpg"));
		
		System.out.println("Proceso finalizado.");
	}
		
		
	
	
	
	//AfineTransform "Clase importada al proyecto para poder ejecutar el efecto espejo en la imagen"
	private static BufferedImage createFlipped(BufferedImage image){
		
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(-1, 1));
        at.concatenate(AffineTransform.getTranslateInstance(-image.getWidth(), 0));
        return createTransformed(image, at);
        
    }
	//Llamaremos a Graphics2D, con nuestra copia de la imagen, para poder trabajar con los ejes x, y
	private static BufferedImage createTransformed(BufferedImage image, AffineTransform at){
	        
		BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = newImage.createGraphics();
	        g.transform(at);
	        g.drawImage(image, 0, 0, null);
	        g.dispose();
	        return newImage;
	     
	    }	
	}

