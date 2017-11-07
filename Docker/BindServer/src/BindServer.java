import java.io.IOException;

public class BindServer {

	public static void main(String[] args) throws IOException, Throwable {
		// remote object
		// obtain instance of Naming Service
		// register reverser in Naming service
		BindInvoker invoker = new BindInvoker();
		
		System.out.println("O Bind Server esta em execucao.");
		invoker.invoke(1316);
	}
}
