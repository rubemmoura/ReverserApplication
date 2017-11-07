

import java.io.IOException;

public interface IBindServer {
	
	public String bind(int applicationType, String serviceName, ReverserProxy applicationProxy) throws  IOException, InterruptedException, Throwable;

	String inverter(StringBuffer string, int applicationType) throws Throwable;

}
