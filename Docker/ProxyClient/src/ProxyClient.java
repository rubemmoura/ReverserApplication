import java.io.IOException;
import java.net.UnknownHostException;

public class ProxyClient {

	public static void main(String[] args) throws UnknownHostException, IOException, Throwable {
		ProxyInvoker invoker = new ProxyInvoker();
		
		System.out.println("Servico de Proxy esta pronto...");
		invoker.invoke(1315);
	}
}