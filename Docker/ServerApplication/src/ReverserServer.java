import java.io.IOException;

public class ReverserServer {

	public static void main(String[] args) throws IOException, Throwable {
		// remote object
		// invoke Invoker
		ReverserInvoker invoker = new ReverserInvoker();
		ReverserProxy reverser = new ReverserProxy("172.17.0.4",1314);
		
		BindServerProxy bindServiceProxy = new BindServerProxy("172.17.0.3",1316);
		
		float time1, time2 = 0;
		String serviceName = "";
		time1 = System.nanoTime();

		for(int i=0; i<1000; i++) {
			serviceName = bindServiceProxy.bind(1, "Reverser" + i, reverser);
			System.out.println("Servidor da aplicacao " + serviceName + " esta pronto.");
			Thread.sleep(2);
		}
		
		time2 = System.nanoTime();
		System.out.println("Tempo de execucao: " + ((time2-time1)));
		invoker.invoke(reverser);
	}
}
