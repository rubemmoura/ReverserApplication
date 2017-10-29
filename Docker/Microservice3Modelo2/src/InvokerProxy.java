import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class InvokerProxy extends ClientProxy{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvokerProxy(String h, int p){
		this.host = h;
		this.port = p;	
	}
	
	public void inverter(String serviceName) throws UnknownHostException, IOException, Throwable {
		Invocation inv = new Invocation();
		new Termination();
		ArrayList<Object> parameters = new ArrayList<Object>();
		class Local {
		}
		;
		String methodName;
		Requestor requestor = new Requestor();

		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();
		parameters.add(serviceName);

		// information sent to Requestor
		inv.setClientProxy(this); 
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		requestor.invoke(inv);

		// @ Result sent back to Client
		return;
	}
	
}
