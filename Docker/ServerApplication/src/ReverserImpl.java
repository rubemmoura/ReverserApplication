

import java.io.IOException;

public class ReverserImpl implements IReverser{

	@Override
	public StringBuffer inverter(StringBuffer string) throws IOException, InterruptedException, Throwable {
		return string.reverse();
	}

}
