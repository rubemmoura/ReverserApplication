import java.io.IOException;

public class ReverserServer {

	public static void main(String[] args) throws IOException, Throwable {
		// remote object
		// invoke Invoker
		ReverserInvoker invoker = new ReverserInvoker();
		ReverserProxy reverser = new ReverserProxy("127.0.0.1",1314);
		
		System.out.println("Servidor da aplicacao está pronto.");
		invoker.invoke(reverser);
	}
}
