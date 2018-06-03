import java.io.IOException;
import java.net.UnknownHostException;

public class ReverserClient {

	public static void main(String[] args) throws UnknownHostException, IOException, Throwable {
		
		// create an instance of Naming Service
		NamingProxy namingService = new NamingProxy("172.17.0.2", 1313);
		
		// look for Reverser in Naming service
		ProxyClientProxy clientProxy = new ProxyClientProxy("172.17.0.6", 1315);
		BindServerProxy reverserProxy =null;
		float time1, time2, proxyTime;
		
//		time1 = System.nanoTime();
//		for(int i = 0; i<40000; i++) {
			reverserProxy = clientProxy.proxy(namingService,1);
//			Thread.sleep(9);
//		}
//		time2 = System.nanoTime();
//		System.out.println("Tempo de execucao do inverter: " + ((time2-time1)));
//		proxyTime = (time2-time1);
		
		// invoke reverser

		time1 = System.nanoTime();
		StringBuffer str = new StringBuffer("Programacao concorrente Distribuida - Midlleware");
		for(int i = 0; i<10000; i++) {
			str = reverserProxy.inverter(str,1);
			System.out.println(i + " " + str);
			Thread.sleep(5);
		}

		// reverserProxy = clientProxy.proxy(namingService,1);

		// time1 = System.nanoTime();
		// for(int i = 0; i<100; i++) {
		// 	str = reverserProxy.inverter(str,1);
		// 	System.out.println(i + " " + str);
		// 	Thread.sleep(5);
		// }

		time2 = System.nanoTime();
//		System.out.println("Tempo de execucao do proxy: " + proxyTime);
		System.out.println("Tempo de execucao do inverter: " + ((time2-time1)));
	}
}