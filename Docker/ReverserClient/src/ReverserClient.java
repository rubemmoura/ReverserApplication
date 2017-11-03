import java.io.IOException;
import java.net.UnknownHostException;

public class ReverserClient {

	public static void main(String[] args) throws UnknownHostException,
			IOException, Throwable {
		
		// create an instance of Naming Service
		NamingProxy namingService = new NamingProxy("172.17.0.2", 1313);

		// check registered services
//		System.out.println(namingService.list());
		
		// look for Calculator in Naming service
		ReverserProxy reverserProxy = (ReverserProxy) namingService.lookup("Reverser");

		// invoke reverser		
		StringBuffer str = new StringBuffer("Programacao concorrente Distribuida - Midlleware");
		for(int i = 0; i<10000; i++) {
			str = reverserProxy.inverter(str);
			System.out.println(i + " " + str);
			Thread.sleep(5);
		}
	}
}