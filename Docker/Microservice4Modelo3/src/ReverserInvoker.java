
import java.io.IOException;

public class ReverserInvoker {
	
	public void invoke(int portInvokerService) throws IOException, Throwable {
		ServerRequestHandler srh = new ServerRequestHandler(portInvokerService);
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = new Message();
		Marshaller mrsh = new Marshaller();
		Termination ter = new Termination();

		// create remote object
		ReverserImpl reverserObj = new ReverserImpl();
		
		while (true) {

			// @ Receive Message
			msgToBeUnmarshalled = srh.receive();

			// @ Unmarshall received message
			msgUnmarshalled = mrsh.unmarshall(msgToBeUnmarshalled);

			switch (msgUnmarshalled.getBody().getRequestHeader().getOperation()) {
			case "inverter":
				// @ Invokes the remote object
				StringBuffer string_p1 = (StringBuffer) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
				
				//t1 = QoSObserver.getTime();
				ter.setResult(reverserObj.inverter(string_p1));
				//t2 = QoSObserver.getTime();
				//QoSObserver.saveTime(t2-t1);

				Message _add_msgToBeMarshalled = new Message(new MessageHeader(
						"protocolo", 0, false, 0, 0), new MessageBody(null,
						null, new ReplyHeader("", 0, 0), new ReplyBody(
								ter.getResult())));

				// @ Marshall the response
				msgMarshalled = mrsh.marshall(_add_msgToBeMarshalled);

				// @ Send response
				srh.send(msgMarshalled);
				break;
		}
	
	}
	
	}
}
