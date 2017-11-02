import java.io.IOException;

public class BindServer {

	public static void main(String[] args) throws IOException, Throwable {
		// remote object
		// obtain instance of Naming Service
		// register reverser in Naming service
		ReverserProxy reverser = new ReverserProxy("127.0.0.1",1314);
		
		NamingProxy namingService = new NamingProxy("127.0.0.1", 1313);
		namingService.bind("Reverser", reverser);
		System.out.println("Servidor da aplicacao foi adicionado ao serviço de nomes.");
	}
}
