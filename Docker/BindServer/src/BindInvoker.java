import java.io.IOException;

public class BindInvoker {
	
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
			case "bind":
				// @ Invokes the remote object
				NamingProxy namingService = new NamingProxy("127.0.0.1", 1313);
				String serviceName = (String) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
				
				System.out.println(" - Executando bind no ServerBind da aplicação: " + serviceName);

				if((int) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0) == 1) {
					namingService.bind(serviceName, (ReverserProxy) msgUnmarshalled.getBody().getRequestBody().getParameters().get(2));
				} else {
					namingService.bind(serviceName, (ReverserProxy) msgUnmarshalled.getBody().getRequestBody().getParameters().get(2));
				}
				
				//t1 = QoSObserver.getTime();
				ter.setResult(serviceName);
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
