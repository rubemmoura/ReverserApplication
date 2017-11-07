import java.io.IOException;
import java.net.UnknownHostException;

public class ReverserClient {

	public static void main(String[] args) throws UnknownHostException, IOException, Throwable {
		
		// create an instance of Naming Service
		NamingProxy namingService = new NamingProxy("172.17.0.2", 1313);
		
		// look for Reverser in Naming service
		ProxyClientProxy clientProxy = new ProxyClientProxy("172.17.0.6", 1315);
		ReverserProxy reverserProxy = clientProxy.proxy(namingService,1);
		
		// invoke reverser		
		StringBuffer str = new StringBuffer("Programacao concorrente Distribuida - Midlleware");
		for(int i = 0; i<100; i++) {
			str = reverserProxy.inverter(str);
			System.out.println(i + " " + str);
			Thread.sleep(5);
		}
	}
}