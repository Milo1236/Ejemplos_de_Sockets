
import java.net.DatagramPacket;
import java.net.DatagramSocket;



public class ServidorUDP {
   public static void main(String[] args){
       System.out.println("---ServidorUDP---");
       try {
           DatagramSocket socketUDP = new DatagramSocket(8888);
           byte [] buffer = new byte[1024];
           
           while (true) {               
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
                socketUDP.receive(peticion);
                
                String cadena = new String(peticion.getData());
                String y = conv(cadena, peticion.getLength());
                int c = 0;
                String s = "";
                for (int i = 0; i < peticion.getLength(); i++) {
                    s = s + y.substring(i, i+1);
                    System.out.println(s);
                    if (y.substring(i, i+1).equals(" ")) {
                        c++;System.out.println(c);
                    }
                }
                c++;
                String numCad = c + " Cadenas";
                byte [] respuesta = new byte[1024];
                respuesta = numCad.getBytes();
                
                DatagramPacket mensaje = new DatagramPacket(respuesta, respuesta.length, peticion.getAddress(), peticion.getPort());
                socketUDP.send(mensaje);
                
                String sms =new String(peticion.getData());
                String x = conv(sms, peticion.getLength());
                
                /*///
                String res = new String(peticion.getData());
                String x2 = conv(res,peticion.getLength() );
                //System.out.println(x2);
                
                String env = "Primo";
                if (!esPrimo(x2)) {
                   env = "No es Primo";
                }
                byte [] enviar = env.getBytes();
                DatagramPacket mensaje2 = new DatagramPacket(enviar, env.length(), peticion.getAddress(), peticion.getPort());
                socketUDP.send(mensaje2);
                ///*/
                System.out.println("Datos: "+x);
                System.out.println(peticion.getLength());
                System.out.println("El Puerto del Cliente es: "+peticion.getPort());
           }
           
           
           
       } catch (Exception e) {
           System.out.println(e);
       }
       
   }// fin main
   public static boolean esPrimo(String cad){
       int n = Integer.parseInt(cad);
       for (int x = 2; x*x <= n; x++) {
           if (n%x==0) return false;
       }
       return true;
   }
   
   public static String conv(String x, int ta) {
       String res = "";
       for (int i = 0; i < ta; i++) {
           res += x.charAt(i);
       }
       return res;
   }
   
    public static String recorte(String s){
		String m="";
		char c[]=s.toCharArray();
		boolean sw=true;
		for(int i=0;i<s.length()&&sw;i++){
			if(c[i]!=' '){
				m=m+c[i]; //System.out.println(m);
			}else{
				sw=false;
			}
		}
		return m;
	}
}// fin clase
