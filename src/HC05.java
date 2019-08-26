import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.util.*;
public class HC05 {
	
	//Variables clase HC05
	
	private String envio_datos_a_GUI;
    private boolean scanFinished;
    
    private OutputStream os;
    private StreamConnection streamConnection;
    
    private RemoteDevice hc05device;
    private String hc05Url;     //On that example they were getting the url automaticly, I had to do it manual
    					//hc05Url = "btspp://201503142740:1;authenticate=false;encrypt=false;master=false";

    //Constructor HC05
    
    public HC05() {
    	
    	envio_datos_a_GUI = "";
    	scanFinished = false;
    	
    }


 /**
 * Busqueda dispositivos
 * @throws Exception
 */
    public boolean busqueda() throws Exception {
    	
    	boolean correcto = false;
        //scan for all devices:
        scanFinished = false;
        LocalDevice.getLocalDevice().getDiscoveryAgent().startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {
            @Override
            public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                try {
                	//Printing devices available
                    String name = btDevice.getFriendlyName(false);
                    addDatos(name); //Agregamos datos, todavia pensando como resolverlo 
                   // gui.setDatosRecibidos(name + "\n");
                    System.out.format("%s (%s)\n", name, btDevice.getBluetoothAddress());
                    
                    //Here I pick my device named "RAUL", the one I want to comunicate
                    if (name.matches("RAUL")) {
                        hc05device = btDevice;
                        addDatos("Got it! (RAUL)");
                    //    gui.setDatosRecibidos("Got it!"+ "\n");
                        System.out.println("got it!");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void inquiryCompleted(int discType) {
                scanFinished = true;
            }

            @Override
            public void serviceSearchCompleted(int transID, int respCode) {
            }

            @Override
            public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
            }
        });
        while (!scanFinished) {
            //this is easier to understand (for me) as the thread stuff examples from bluecove
            Thread.sleep(500);
        }

        //search for services:
        //Feels like that part wasn't working, so I had to put the url manually
        UUID uuid = new UUID(0x1101); //scan for btspp://... services (as HC-05 offers it)
        UUID[] searchUuidSet = new UUID[]{uuid};
        int[] attrIDs = new int[]{
            0x0100 // service name
        };
        scanFinished = false;
        LocalDevice.getLocalDevice().getDiscoveryAgent().searchServices(attrIDs, searchUuidSet,
                hc05device, new DiscoveryListener() {
                    @Override
                    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
                    }

                    @Override
                    public void inquiryCompleted(int discType) {
                    }

                    @Override
                    public void serviceSearchCompleted(int transID, int respCode) {
                        scanFinished = true;
                    }

                    @Override
                    public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
                        for (int i = 0; i < servRecord.length; i++) {
                            hc05Url = servRecord[i].getConnectionURL(ServiceRecord.NOAUTHENTICATE_NOENCRYPT, false);
                            if (hc05Url != null) {
                                break; //take the first one
                            }
                        }
                    }
                });

        while (!scanFinished) {
            Thread.sleep(500);
        }
        
        correcto = true;
        
        return correcto;

 //Conexion
        
 //Envio de datos
        
 //Desconexion
   
    }
    /**
     * Metodo que realiza la conexion del stream
     * @throws IOException
     */
    public void conectar() throws IOException {
    	
        System.out.println(hc05device.getBluetoothAddress());  //MAC
        hc05Url = "btspp://201503142740:1;authenticate=false;encrypt=false;master=false";
        System.out.println(hc05Url);

        //if you know your hc05Url this is all you need: !!!
        streamConnection = (StreamConnection) Connector.open(hc05Url);
         os = streamConnection.openOutputStream();
        //InputStream is = streamConnection.openInputStream();   //I won't need Input for my arduino 
        
         System.out.println("Conection succeed!"); 
         addDatos("Conection succeed!");
    	
    }
    /**
     * Desconexion
     * @throws IOException
     */
    
    public void desconectar() throws IOException { 

           os.close();
           streamConnection.close();
           
           addDatos("Connection closed!");
    	   System.out.println("Conection closed!"); 	
    }
    
    /**
     * Envio de una cadena de datos al BT
     * @param datos
     * @throws IOException
     */
    
    public void enviarBT(String datos) throws IOException {
    	    String data = datos;        	    
    	    os.write(data.getBytes()); 
    	    
    	    
    	   // System.out.println(data);
    }
 
    
    /**
     * Metodo para añadir datos al String
     */
    public void addDatos(String datos) {
    	envio_datos_a_GUI = envio_datos_a_GUI + "\n" + datos;
    }
    
}