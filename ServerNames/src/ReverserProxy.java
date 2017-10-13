import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class ReverserProxy extends ClientProxy implements IReverser{
	
	private static final long serialVersionUID = 1L;

	// TODO objID
	public ReverserProxy() throws UnknownHostException {
		this.host = InetAddress.getLocalHost().getHostName();
		this.port = UtilsConf.nextPortAvailable();
	}
	
	public ReverserProxy(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public StringBuffer inverter(StringBuffer string) throws Throwable {
		Invocation inv = new Invocation();
		Termination ter = new Termination();
		Requestor requestor = new Requestor();
		
		ArrayList<Object> parameters = new ArrayList<Object>();
		parameters.add(string);
		
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
