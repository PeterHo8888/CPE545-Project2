package server;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import server.services.HelloService;
import server.services.HelloServiceImpl;

public class ServerMain {
    public static void main(String[] args) {
        System.out.println("HelloServer is starting...");
        
        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            HelloService service = new HelloServiceImpl();
            
            registry.rebind(HelloService.SERVICE_NAME, service);
            
            Thread.sleep(10000);
            
            registry.unbind(HelloService.SERVICE_NAME);
            UnicastRemoteObject.unexportObject(service,  true);
            UnicastRemoteObject.unexportObject(registry,  true);
            System.out.println("HelloServer has stopped.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
