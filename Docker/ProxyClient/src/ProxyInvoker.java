import java.io.IOException;

public class ProxyInvoker {
	
	public void invoke(int portNamingServer) throws IOException, Throwable {
		ServerRequestHandler srh = new ServerRequestHandler(portNamingServer);
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = new Message();
		Marshaller mrsh = new Marshaller();
		Termination ter = new Termination();

		while (true) {

			// @ Receive Message
			msgToBeUnmarshalled = srh.receive();

			// @ Unmarshall received message
			msgUnmarshalled = mrsh.unmarshall(msgToBeUnmarshalled);

			switch (msgUnmarshalled.getBody().getRequestHeader().getOperation()) {
			case "proxy":
				System.out.println(" - Executando proxy client.");
				// @ Invokes the remote object
				NamingProxy namingProxy = (NamingProxy) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);
				ReverserProxy reverserProxy = (ReverserProxy) namingProxy.lookup("Reverser");

				//t1 = QoSObserver.getTime();
				ter.setResult(reverserProxy);
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
