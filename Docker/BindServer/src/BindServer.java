import java.io.IOException;

public class BindServer {

	public static void main(String[] args) throws IOException, Throwable {
		// remote object
		// obtain instance of Naming Service
		// register reverser in Naming service
//		ReverserProxy reverser = new ReverserProxy("172.17.0.3",1314);
		
//		NamingProxy namingService = new NamingProxy("172.17.0.2", 1313);
//		namingService.bind("Reverser", reverser);
		
		BindInvoker invoker = new BindInvoker();
		
		System.out.println("O Bind Server esta em execucao.");
		invoker.invoke(1316);
	}
}
