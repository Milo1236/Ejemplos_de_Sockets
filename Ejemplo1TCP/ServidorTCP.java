import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javax.jws.soap.SOAPBinding;
public class ServidorTCP {
	

	
	public static void main(String[] args) throws Exception{
		// instanciamos nuestros sockets de Servidor y Cliente, nuestros flujos de entrada,salida y DATE
                ServerSocket socketServidor = null;
		Socket socketCliente=null;
		BufferedReader entrada = null;
		PrintWriter salida= null;
		java.util.Date fecha = new Date();
		System.out.println("---ServidorTCP");
		
		try {
                    // Creamos el socketServidor que escuchara en el pueto 8888
                    socketServidor = new ServerSocket(8888);
		} catch (Exception e) {
                    System.out.println(e);	
		}
		
		try {
			while (true) {
                                // Cuando el servidort acepta la conexion lo pondremos en nuestro socket Cliente
				socketCliente =socketServidor.accept();
                                // Creamos los flujos de entrada y salida
				entrada= new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
				salida= new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
                                
                                boolean sw = true;
				while (sw) {
                                        //Enviamos
					salida.println("MENU:\t -Opcion 1\t -Opcion 2\t -Opcion 3\t -Salir (S)");
                                        // Recibimos la peticion de un cliente
                                        String cad=entrada.readLine();
                                        // Si recibimos "exit" terminamos la conexion con ese cliente
                                    switch (cad) {
                                        case "1":
                                            salida.println("Papel");
                                            break;
                                        case "2":
                                            salida.println("Piedra");
                                            break;
                                        case "3":
                                            salida.println("Tijera");
                                            break;
                                        case "s":
                                            salida.println("s");
                                            sw = false;
                                            break;    
                                        default:
                                            salida.println(" (Escoge solo una de las opciones del Menu!) ");
                                            break;
                                            //throw new AssertionError();
                                    }
                                       /* 
                                        if (cad.equals("1")) {
                                            salida.println("Papel");
                                        }
                                        if (cad.equals("2")) {
                                            salida.println("Piedra");
                                        }
                                        if (cad.equals("3")) {
                                            salida.println("Tijera");
                                        }
                                        if (cad.equalsIgnoreCase("s")){ 
                                            salida.println("s");
                                            System.out.println(cad+"");
                                            break;
                                        }
                                        */
                                        System.out.println(cad+" ");
                                            
                                            
                                        
                                        // Enviamos al Cliente el mensaje en mayuscula mas la fecha
                                            //salida.println("MENU:\t -Opcion 1\t -Opcion 2\t -Opcion 3\t -Salir (S)");
                                        //salida.println(cad.toUpperCase()+" "+fecha);
					//System.out.println (fecha);
                                        
				}	
			}
		} catch (Exception e) {
                    System.out.println(e);
		}
                
                //Lieramos los recursos
		salida.close();
		entrada.close();
		socketServidor.close();
		socketCliente.close();
	}
}
