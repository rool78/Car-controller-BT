import java.io.IOException;

/**
 * Clase controladora que intentara orquestar la interfaz gráfica y la aplicacion bluetooth 
 * @author raul_maza
 *
 */
public class Controladora {

	private HC05 hc05;
	private GUI gui;
	private String datos_a_enviar;
	
	
	public Controladora(){
	//	hc05 = new HC05();
	//	gui = new GUI();
		datos_a_enviar = " ";
	}
	//Metodo para conectarse
	public void conexion() throws Exception {
		
		hc05 = new HC05();
		
		boolean conexion = false;
		
		conexion = hc05.busqueda();
		
		if (conexion) {
			
			hc05.conectar();
			
		}
	}
	//Metodo para desconectarse
	public void desconexion() throws IOException {
		
		hc05.desconectar();
		
	}
	
	//Metodo  para iniciar la GUI
	public void inicio() throws Exception {
		
		conexion();
	}
	
	public void escribir(String datos) throws IOException {
		
		datos_a_enviar = datos;
		
		if (datos_a_enviar.equals("QUIT")){
			desconexion();
			}

	    System.out.println(datos_a_enviar);

	    hc05.enviarBT(datos_a_enviar);

		
	
		
}
	
	///////////INNECESARIO//////////
	
	
	//Lectura botones 
	
	public String lecturaBotones() {
		
		return gui.getDatosBotones();
		
	}
	
	//Lectura estado de envio boolean
	
	public boolean lecturaOrden() {
		return gui.getEnvio();
	}
	
	//Asignar el envio como leido
	
	public void setLeido() {
		
		gui.setEnvio(true);
		
	}
	
	
	
	public void lectura() {
		
	String lectura = lecturaBotones();
	boolean orden_envio = false;
	
	while (!lectura.equals("QUIT")) {
		
		lectura = lecturaBotones();
		orden_envio = lecturaOrden();
		
		if(orden_envio) {
		System.out.println(lectura);
		setLeido();
		}
	  }		
 }
}
