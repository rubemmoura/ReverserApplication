

import java.io.IOException;

public interface IProxyClient {
	
	public ReverserProxy proxy(NamingProxy namingService) throws  IOException, InterruptedException, Throwable;
}
