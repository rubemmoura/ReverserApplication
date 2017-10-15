import java.io.IOException;

public class NamingServer {
	
	public static void main(String[] args) throws IOException, Throwable {
		NamingInvoker invoker = new NamingInvoker();
		
		System.out.println("Servidor de nomes está pronto...");
		invoker.invoke(1313);
	}

}
