

import java.io.IOException;

public interface IProxyClient {
	
	public BindServerProxy proxy(NamingProxy namingService, int applicationType) throws  IOException, InterruptedException, Throwable;
}
