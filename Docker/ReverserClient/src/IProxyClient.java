

import java.io.IOException;

public interface IProxyClient {
	
	public ReverserProxy proxy(NamingProxy namingService, int applicationType) throws  IOException, InterruptedException, Throwable;
}
