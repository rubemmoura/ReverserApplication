import java.io.IOException;

public class InvokerService {

	public static void main(String[] args) throws IOException, Throwable {
		ReverserInvoker invoker = new ReverserInvoker();
		
		System.out.println("Invoker Service esta pronto...");
		invoker.invoke(8081);
	}
}
