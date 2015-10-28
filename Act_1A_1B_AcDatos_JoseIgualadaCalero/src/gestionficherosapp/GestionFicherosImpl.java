package gestionficherosapp;

import java.io.File;
import java.util.Date;

import gestionficheros.FormatoVistas;
import gestionficheros.GestionFicheros;
import gestionficheros.GestionFicherosException;
import gestionficheros.TipoOrden;

public class GestionFicherosImpl implements GestionFicheros {
	private File carpetaDeTrabajo = null;
	private Object[][] contenido;
	private int filas = 0;
	private int columnas = 3;
	private FormatoVistas formatoVistas = FormatoVistas.NOMBRES;
	private TipoOrden ordenado = TipoOrden.DESORDENADO;

	public GestionFicherosImpl() {
		carpetaDeTrabajo = File.listRoots()[0];
		actualiza();
	}

	private void actualiza() {

		String[] ficheros = carpetaDeTrabajo.list(); // obtener los nombres
		// calcular el número de filas necesario
		filas = ficheros.length / columnas;
		if (filas * columnas < ficheros.length) {
			filas++; // si hay resto necesitamos una fila más
		}

		// dimensionar la matriz contenido según los resultados

		contenido = new String[filas][columnas];
		// Rellenar contenido con los nombres obtenidos
		for (int i = 0; i < columnas; i++) {
			for (int j = 0; j < filas; j++) {
				int ind = j * columnas + i;
				if (ind < ficheros.length) {
					contenido[j][i] = ficheros[ind];
				} else {
					contenido[j][i] = "";
				}
			}
		}
	}

	@Override
	public void arriba() {

		if (carpetaDeTrabajo.getParentFile() != null) {
			carpetaDeTrabajo = carpetaDeTrabajo.getParentFile();
			actualiza();
		}

	}

	@Override
	public void creaCarpeta(String arg0) throws GestionFicherosException {
		
		File file = new File(carpetaDeTrabajo,arg0);
		
		//que no se pueda escribir -> lanzará una excepción
		
		if(!carpetaDeTrabajo.canWrite()){
			
			throw new GestionFicherosException("Su directorio no se puede modificar.");
			
		}
		
		//que exista -> lanzará una excepción
		
		if(file.exists()){
			
			throw new GestionFicherosException("Su directorio ya esta creado.");
			
		}
		
		//no puedes crear la carpeta -> lanzará una excepción
		
		try{
			file.mkdir();
		}
		catch(Exception e){			
			throw new GestionFicherosException("No puede crear su directorio.");			
		}
		
		actualiza();
	}

	@Override
	public void creaFichero(String arg0) throws GestionFicherosException {
		
		File file = new File(carpetaDeTrabajo,arg0);
		
		//que no se pueda escribir -> lanzará una excepción
		if(arg0.length() != 0){
			
		
			if(!carpetaDeTrabajo.canWrite()){
				
				throw new GestionFicherosException("Su fichero no se puede modificar.");
				
			}
			
			//que exista -> lanzará una excepción
			
			if(file.exists()){
				
				throw new GestionFicherosException("Su fichero ya existe.");
				
			}
			
			//no puedes crear el fichero -> lanzará una excepción
			
			try{
				
				file.createNewFile();
				
			}catch(Exception e){
				
				throw new GestionFicherosException("No se puede crear su fichero.");
				
			}
		}
		
		actualiza();

	}

	@Override
	public void elimina(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo,arg0);
		
		if(file.delete()){
			file.delete();
		}else{
			throw new GestionFicherosException("No se ha podido borrar su archivo");
		}
		
		actualiza();
	}

	@Override
	public void entraA(String arg0) throws GestionFicherosException {
		File file = new File(carpetaDeTrabajo, arg0);
		// se controla que el nombre corresponda a una carpeta existente
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se ha encontrado "
					+ file.getAbsolutePath()
					+ " pero se esperaba un directorio");
		}
		// se controla que se tengan permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException("Alerta. No se puede acceder a "
					+ file.getAbsolutePath() + ". No hay permiso");
		}
		// nueva asignación de la carpeta de trabajo
		carpetaDeTrabajo = file;
		// se requiere actualizar contenido
		actualiza();

	}

	@Override
	public int getColumnas() {
		return columnas;
	}

	@Override
	public Object[][] getContenido() {
		return contenido;
	}

	@Override
	public String getDireccionCarpeta() {
		return carpetaDeTrabajo.getAbsolutePath();
	}

	@Override
	public String getEspacioDisponibleCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEspacioTotalCarpetaTrabajo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getFilas() {
		return filas;
	}

	@Override
	public FormatoVistas getFormatoContenido() {
		return formatoVistas;
	}

	@Override
	public String getInformacion(String arg0) throws GestionFicherosException {
		
		StringBuilder strBuilder=new StringBuilder();
		File file = new File(carpetaDeTrabajo,arg0);
		
		//Controlar que existe. Si no, se lanzará una excepción
		//Controlar que haya permisos de lectura. Si no, se lanzará una excepción
		
		if(!file.exists()){
			throw new GestionFicherosException("No se puede localizar su archivo.");
		}
		
		if(!file.canWrite()){
			throw new GestionFicherosException("El archivo seleccionado no se puede leer.");
		}
		
		
		//Título
		strBuilder.append("INFORMACIÓN DEL SISTEMA");
		strBuilder.append("\n\n");
		
		//Nombre
		strBuilder.append("Nombre: ");
		strBuilder.append(file.getName());
		strBuilder.append("\n");
		
		//Tipo: fichero o directorio		
		
		if(file.isDirectory()){
			
			strBuilder.append("Tipo: ");
			strBuilder.append("Directorio");
			strBuilder.append("\n");
			
		}else{
			
			strBuilder.append("Tipo: ");
			strBuilder.append("Fichero");
			strBuilder.append("\n");
		}
		
		//Tamaño del fichero
		
			if(!file.isDirectory()){
				
				strBuilder.append("Tamaño: ");
				strBuilder.append(file.length() + " bytes");
				strBuilder.append("\n");
			}
		
		//Archivos dentro del directorio
			
			String[] archivos = file.list();
			if(archivos == null){
				strBuilder.append("Archivos de este directorio: ");
				strBuilder.append("No existe ningún archivo en este directorio");
				strBuilder.append("\n");
			}else{
				strBuilder.append("Archivos de este directorio: ");
				strBuilder.append(archivos.length);
				strBuilder.append("\n\n");
				
				for(int x = 0; x < archivos.length; x++){
					strBuilder.append("      " + archivos[x]);
					strBuilder.append("\n");
				}
				strBuilder.append("\n");
			}
			
		//Ubicación
		
		strBuilder.append("Ubicación: ");
		strBuilder.append(file.getAbsolutePath());
		strBuilder.append("\n");
		
		//Fecha de última modificación
		
		strBuilder.append("Fecha última modificación: ");
		
		java.util.Date myDate = new java.util.Date(file.lastModified());		
		strBuilder.append(myDate);
		strBuilder.append("\n");
		
		//Si es un fichero oculto o no
		
		if(file.isHidden()){
			
			strBuilder.append("Fichero Oculto: ");
			strBuilder.append("Si");
			strBuilder.append("\n");
			
		}else{
			
			strBuilder.append("Fichero Oculto: ");
			strBuilder.append("No");
			strBuilder.append("\n");
		}
		
		//Si es directorio: Espacio libre, espacio disponible, espacio total
		//bytes
		if(file.isDirectory()){
			
			strBuilder.append("Espacio libre: ");
			strBuilder.append(file.getFreeSpace() + " bytes");
			strBuilder.append("\n");
			
			strBuilder.append("Espacio disponible: ");
			strBuilder.append(file.getUsableSpace() + " bytes");
			strBuilder.append("\n");
			
			strBuilder.append("Espacio total: ");
			strBuilder.append(file.getTotalSpace() + " bytes");
			strBuilder.append("\n");
			
		}
		
		return strBuilder.toString();
	}

	@Override
	public boolean getMostrarOcultos() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombreCarpeta() {
		return carpetaDeTrabajo.getName();
	}

	@Override
	public TipoOrden getOrdenado() {
		return ordenado;
	}

	@Override
	public String[] getTituloColumnas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getUltimaModificacion(String arg0)
			throws GestionFicherosException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String nomRaiz(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int numRaices() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void renombra(String arg0, String arg1)
			throws GestionFicherosException {
		File file1 = new File(carpetaDeTrabajo,arg0);
		File file2 = new File(carpetaDeTrabajo,arg1);
		
		if(!file1.canWrite())			
			throw new GestionFicherosException("Su archivo no se puede modificar.");			
		
		
		//que exista -> lanzará una excepción
		
		if(file2.exists())			
			throw new GestionFicherosException("Ya existe un archivo con ese nombre.");			
		
		
		boolean renombrar =  file1.renameTo(file2);
		
		if(!renombrar){
			
			throw new GestionFicherosException("Su archivo no se puede renombrar.");
		}
		
		actualiza();
	}

	@Override
	public boolean sePuedeEjecutar(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeEscribir(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean sePuedeLeer(String arg0) throws GestionFicherosException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setColumnas(int arg0) {
		columnas = arg0;

	}

	@Override
	public void setDirCarpeta(String arg0) throws GestionFicherosException {
		File file = new File(arg0);

		// se controla que la dirección exista y sea directorio
		if (!file.isDirectory()) {
			throw new GestionFicherosException("Error. Se esperaba "
					+ "un directorio, pero " + file.getAbsolutePath()
					+ " no es un directorio.");
		}

		// se controla que haya permisos para leer carpeta
		if (!file.canRead()) {
			throw new GestionFicherosException(
					"Alerta. No se puede acceder a  " + file.getAbsolutePath()
							+ ". No hay permisos");
		}

		// actualizar la carpeta de trabajo
		carpetaDeTrabajo = file;

		// actualizar el contenido
		actualiza();

	}

	@Override
	public void setFormatoContenido(FormatoVistas arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMostrarOcultos(boolean arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrdenado(TipoOrden arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEjecutar(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeEscribir(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSePuedeLeer(String arg0, boolean arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setUltimaModificacion(String arg0, long arg1)
			throws GestionFicherosException {
		// TODO Auto-generated method stub

	}

}
