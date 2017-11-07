import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ProxyClientProxy extends ClientProxy implements IProxyClient{
	
	private static final long serialVersionUID = 1L;

	// TODO objID
	public ProxyClientProxy() throws UnknownHostException {
		this.host = InetAddress.getLocalHost().getHostName();
		this.port = Utils.nextPortAvailable();
	}
	
	public ProxyClientProxy(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public BindServerProxy proxy(NamingProxy namingService, int applicationType) throws Throwable {
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		Requestor requestor = new Requestor();
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(namingService);
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
		return (BindServerProxy) ter.getResult();
	}

}
