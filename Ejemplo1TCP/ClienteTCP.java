import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteTCP {
	public static void main(String[] args) throws IOException{
            // Instanciamos el socket Cliente y los flujos de entrada y salida
            Socket socketCliente =null;
            BufferedReader entrada =null;
            PrintWriter salida =null;
            System.out.println("---ClienteTCP---");
            try {
                socketCliente = new Socket("localhost",8888);
                entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
            } catch (Exception e) {
                System.out.println(e);
            }
            //Un buffer para meter datos
            BufferedReader sc= new BufferedReader(new InputStreamReader(System.in));
            
            try {
                while (true) {
                    // Ingresamos por teclado la cadena
                    String cad=entrada.readLine();
                    System.out.println(" "+cad);
                    // Si la cadena es: "exit" el cliente se sale y termina
                    
                    
                    // enviamos por nuestro flujo de salida la cadena para enviarle al servidor
                    cad=sc.readLine();
                    salida.println(cad);
                                  
                         //Recibimos el mensaje del Servidor
			cad=entrada.readLine();
                        
                        if (cad.equalsIgnoreCase("s")){ 
                            //System.out.println("Fin: "+cad);
                            break;
                        }
                        
                        System.out.println("La respuesta es: "+cad);
                        
                        
                        
                    }
		} catch (Exception e) {
                    System.out.println(e);
		}
		
                // Cerramos para liberar recursos
		salida.close();
		entrada.close();
		sc.close();
		socketCliente.close();
	}//fin main
}// fin clase
