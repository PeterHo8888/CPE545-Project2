package client;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

import client.services.ClientService;
import client.services.ClientServiceImpl;
import server.services.Hello;
import server.services.HelloService;

public class ClientMain {
	public static void main(String[] args) {
		try {
			ClientService client = new ClientServiceImpl();
			HelloService service = (HelloService) Naming.lookup(HelloService.SERVICE_NAME);
			service.connect(client);
			Hello hello = service.get_hello();
			System.out.println(hello.get_id());
			UnicastRemoteObject.unexportObject(client, true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}
}
