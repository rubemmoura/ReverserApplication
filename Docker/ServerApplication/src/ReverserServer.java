import java.io.IOException;

public class ReverserServer {

	public static void main(String[] args) throws IOException, Throwable {
		// remote object
		// invoke Invoker
		ReverserInvoker invoker = new ReverserInvoker();
		ReverserProxy reverser = new ReverserProxy("127.0.0.1",1314);
		
		BindServerProxy bindServiceProxy = new BindServerProxy("127.0.0.1",1316);
		String serviceName = bindServiceProxy.bind(1, "Reverser", reverser);
		
		
		System.out.println("Servidor da aplicacao " + serviceName + "esta pronto.");
		invoker.invoke(reverser);
	}
}
