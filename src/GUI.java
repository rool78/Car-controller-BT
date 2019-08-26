
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;



class GUI extends JFrame implements ActionListener , KeyListener{
	
	//Variables envio y recepcion de datos
	
	private String datos_botones;   //Esta cadeana la escriben segun apretamos los diferentes botones
	private String datos_recibidos; //La usaremos para escribir en la JTextArea los datos recibidos
	private boolean envio;          //Booleana que pondremos a true cuando enviemos algo (pulsemos un boton)
	
	private Controladora controladora;
	
	//Botones
	
	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;
	private JButton quit;
	
	//Etiquetas de texto y el area de texto
	
	private JLabel texto;
	private JLabel texto_box;
	
	private JTextArea caja; 
	
	
	//Constructor de la clase inferfaz
	
	public GUI() throws Exception {
		
		
		datos_botones = " ";
		datos_recibidos = " ";
		envio = false;
		controladora = new Controladora();
		configuracion();
		controladora.inicio();
		caja.setText("Connected!!");
		
	}
	
	public static void main(String[] args) throws Exception {
		
		new GUI();
	}
	
	/**
	 * Metodo actionPerformed (eventos botones)
	 */

public void actionPerformed(ActionEvent e) {
	
	Object action = e.getSource();
	
	 try {
	
	 if (action==boton1) {
            controladora.escribir("A");
			} 
			
        
	 if (action==boton2) {
            controladora.escribir("B");

        }
	 if (action==boton3) {
            controladora.escribir("R");

        }

	 if (action==boton4) {
            controladora.escribir("L");

        }

	 if (action==boton5) { 
            controladora.escribir("a");

        }
	 if (action==quit) {
         controladora.escribir("QUIT");

     }
	 }catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
	 }

}

//Getters y setters 

public boolean getEnvio() {
	return envio;
}

public String getDatosBotones() {
	return datos_botones;
}

public void setDatosRecibidos(String datos) {
	datos_recibidos = datos;
	caja.append(datos);
	//caja.inside(x, y)
}

public void setEnvio(boolean env) {
	envio = env;
}

/**
 * Configuracion interfaz
 */
public void configuracion() {
	
	//Vamos a descubrir la resolucion de la pantalla donde se ejecuta el programa
	
	//Toolkit mipantalla=Toolkit.getDefaultToolkit();
	//Dimension sizeScreen = mipantalla.getScreenSize();
	
	int height = 500;   //sizeScreen.height;
	int width =  700;	//sizeScreen.width;

	//Añadimo un layout nulo y añadimos los botones
	
	setLayout(null);
	
	texto=new JLabel("BT Control panel");
	texto.setFont(new java.awt.Font("Tahoma", 1, 18));
	texto.setBounds(110,25,220,40);
	add(texto);
	
	
	
	texto=new JLabel("Just type w,a,s or r to move when you are connected");
	texto.setFont(new java.awt.Font("Tahoma", 1, 12));
	texto.setBounds(20,100,400,40);
	add(texto);
	
	//Añado la ventana para implementar el key listener y controlar el coche con las teclas
	
	JTextField textField = new JTextField();
	textField.setBounds(130, 80, 100, 25);
	textField.addKeyListener(this);
	add(textField);
	
	texto=new JLabel("Info box...");
	texto.setFont(new java.awt.Font("Tahoma", 1, 18));
	texto.setBounds(400,25,220,40);
	add(texto);
	
	caja=new JTextArea();
	caja.setBounds(400, 100, 200, 200); 
	add(caja);
	
	
	//Botones
	
	boton1=new JButton("GAS");
	boton1.setBounds(150, 100, 80, 50);
	//add(boton1);
	boton1.addActionListener(this);
	
	boton2=new JButton("BACK");
	boton2.setBounds(150, 250, 80, 50);
	//add(boton2);
	boton2.addActionListener(this);
	
	boton3=new JButton("RIGHT");
	boton3.setBounds(250, 175, 80, 50);
	//add(boton3);
	boton3.addActionListener(this);
	
	boton4=new JButton("LEFT");
	boton4.setBounds(50, 175, 80, 50);
	//add(boton4);
	boton4.addActionListener(this);

	boton5=new JButton("STOP");
	boton5.setBounds(150, 175, 80, 50);
    //add(boton5);
	boton5.addActionListener(this);
	
	quit=new JButton("END COMMUNICATION");
	quit.setBounds(400, 320, 200, 30);
	add(quit);
	quit.addActionListener(this);
	
	
	
	//Configuracion ventana
	
	setSize(width,height);
	setLocation(400,400);
	//setSize(width/2, height/2);
	//setLocation(width/4,height/4);
	setVisible(true);
	setTitle("BT Comunication panel by Raul Maza");
	
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
 }

//MATODOS KEY LISTENER

@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	char letra_pulsada = e.getKeyChar();
	
	try {
		
	if (letra_pulsada == 'w' || letra_pulsada == 'W' )
		controladora.escribir("A");
	
	if (letra_pulsada == 's'|| letra_pulsada == 'S'  )
		controladora.escribir("B");
	
	if (letra_pulsada == 'a' || letra_pulsada == 'A' )
		controladora.escribir("L");
	
	if (letra_pulsada == 'd' || letra_pulsada == 'D')
		controladora.escribir("R");
	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

}

@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stu
	char letra_soltada = e.getKeyChar();
	try {
		
		if (letra_soltada == 'w' || letra_soltada == 'W' )
				controladora.escribir("a");
		
		if (letra_soltada == 's' || letra_soltada == 'S')
			controladora.escribir("b");
		
		if (letra_soltada == 'a' || letra_soltada == 'A')
			controladora.escribir("l");
		
		if (letra_soltada == 'd' || letra_soltada == 'D')
			controladora.escribir("r");
		
		
		
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
	
}

@Override
public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
	
}