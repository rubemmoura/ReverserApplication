import java.io.IOException;

public class ReverserServer {

	public static void main(String[] args) throws IOException, Throwable {
		String serviceName = "inverter";
//		ReverserInvoker invoker = new ReverserInvoker();
		InvokerProxy invoker = new InvokerProxy("127.0.0.1", 8081);
		
		// remote object
		ReverserProxy reverser = new ReverserProxy();

		// obtain instance of Naming Service
		NamingProxy namingService = new NamingProxy("127.0.0.1", 1313);

		// register reverser in Naming service
		namingService.bind("Reverser", reverser);
		System.out.println("Servidor da applicacao Reverser esta pronto...");
		
		// invoke Invoker
		invoker.inverter(serviceName);
	}
}
