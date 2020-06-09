
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
   public static void main(String[] args){
     
       System.out.println("---ClienteUDP---");
       try {
           DatagramSocket socketUDP = new DatagramSocket();
           
           int puerto = 8888;
           InetAddress host = InetAddress.getByName("localhost");
           
           BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
           
           String cad; 
           
           while (true) {               
           cad = sc.readLine();
               if (cad.equalsIgnoreCase("0")) break; 
               
           byte [] men = cad.getBytes();
           DatagramPacket peticion = new DatagramPacket(men, cad.length(), host, puerto);
           socketUDP.send(peticion);
           
           byte [] buffer = new byte[10000];
           DatagramPacket mensaje = new DatagramPacket(buffer, buffer.length);
           socketUDP.receive(mensaje);
           
           String sms =new String(mensaje.getData());
           //System.out.println(mensaje.getLength());
           String x = conv(sms, mensaje.getLength());
               //System.out.println(x);
           System.out.println("La Respuesta del Servidor es: "+x);
           }
           
       } catch (Exception e) {
           System.out.println(e);
       }
       
       
               
   }//fin main
   
   public static String conv(String x, int ta) {
       String res = "";
       for (int i = 0; i < ta; i++) {
           res += x.charAt(i);
       }
       return res;
   }
   
}// fin clase
