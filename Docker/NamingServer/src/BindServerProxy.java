import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class BindServerProxy extends ClientProxy implements IBindServer{
	
	private static final long serialVersionUID = 1L;

	// TODO objID
	public BindServerProxy() throws UnknownHostException {
		this.host = InetAddress.getLocalHost().getHostName();
		this.port = Utils.nextPortAvailable();
	}
	
	public BindServerProxy(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public String bind(int applicationType, String serviceName, ReverserProxy applicationProxy) throws Throwable {
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		Requestor requestor = new Requestor();
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(applicationType);
		parameters.add(serviceName);
		parameters.add(applicationProxy);
		
		String methodName;
		class Local {};

		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();

		// information sent to Requestor
		inv.setClientProxy(this);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		// invoke Requestor
		ter = requestor.invoke(inv);

		// @ Result sent back to Client
		return (String) ter.getResult();
	}
	
	@Override
	public StringBuffer inverter(StringBuffer string, int applicationType) throws Throwable {
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		Requestor requestor = new Requestor();
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(string);
		parameters.add(applicationType);
		
		String methodName;
		class Local {};

		// information received from Client
		methodName = Local.class.getEnclosingMethod().getName();

		// information sent to Requestor
		inv.setClientProxy(this);
		inv.setOperationName(methodName);
		inv.setParameters(parameters);

		// invoke Requestor
		ter = requestor.invoke(inv);

		// @ Result sent back to Client
		return (StringBuffer) ter.getResult();
	}

}
