import java.io.IOException;

public class ReverserServer {

	public static void main(String[] args) throws IOException, Throwable {
		ReverserInvoker invoker = new ReverserInvoker();

		// remote object
		ReverserProxy reverser = new ReverserProxy();

		// obtain instance of Naming Service
		NamingProxy namingService = new NamingProxy("localhost", 1313);

		// register reverser in Naming service
		namingService.bind("Reverser", reverser);
		
		// invoke Invoker
		invoker.invoke(reverser);
	}
}
