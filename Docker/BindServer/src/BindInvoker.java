import java.io.IOException;

public class BindInvoker {
	
	public void invoke(int portNamingServer) throws IOException, Throwable {
		ServerRequestHandler srh = new ServerRequestHandler(portNamingServer);
		byte[] msgToBeUnmarshalled = null;
		byte[] msgMarshalled = null;
		Message msgUnmarshalled = new Message();
		Marshaller mrsh = new Marshaller();
		Termination ter = new Termination();
		ReverserProxy applicationOne = null;
		ReverserProxy applicationTwo = null;
		BindServerProxy bindServer;
		

		while (true) {

			// @ Receive Message
			msgToBeUnmarshalled = srh.receive();

			// @ Unmarshall received message
			msgUnmarshalled = mrsh.unmarshall(msgToBeUnmarshalled);

			switch (msgUnmarshalled.getBody().getRequestHeader().getOperation()) {
			case "bind":
				// @ Invokes the remote object
				NamingProxy namingService = new NamingProxy("172.17.0.2", 1313);
				bindServer = new BindServerProxy("172.17.0.3",1316);
				String serviceName = (String) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1);
				
				System.out.println(" - Executando bind no ServerBind da aplicacao: " + serviceName);

				if((int) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0) == 1) {
					System.out.println("Entrou app 1");
					applicationOne = (ReverserProxy) msgUnmarshalled.getBody().getRequestBody().getParameters().get(2);
				} else {
					System.out.println("Entrou app 0");
					applicationTwo = (ReverserProxy) msgUnmarshalled.getBody().getRequestBody().getParameters().get(2);
				}
				
				namingService.bind(serviceName, bindServer);
				
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
				
			case "inverter":
				// @ Invokes the remote object
				StringBuffer string = (StringBuffer) msgUnmarshalled.getBody().getRequestBody().getParameters().get(0);

				if((int) msgUnmarshalled.getBody().getRequestBody().getParameters().get(1) == 1) {
					ter.setResult(applicationOne.inverter(string));
					System.out.println(" - Invocando Inverter no ServerBind da aplicacao: 1");
				} else {
					ter.setResult(applicationTwo.inverter(string));
					System.out.println(" - Invocando Inverter no ServerBind da aplicacao: 0");
				}
				
				//t1 = QoSObserver.getTime();
//				ter.setResult(serviceName2);
				//t2 = QoSObserver.getTime();
				//QoSObserver.saveTime(t2-t1);

				Message _add_msgToBeMarshalled2 = new Message(new MessageHeader(
						"protocolo", 0, false, 0, 0), new MessageBody(null,
						null, new ReplyHeader("", 0, 0), new ReplyBody(
								ter.getResult())));

				// @ Marshall the response
				msgMarshalled = mrsh.marshall(_add_msgToBeMarshalled2);

				// @ Send response
				srh.send(msgMarshalled);
				break;
		}
	
	}
	
	}
}
