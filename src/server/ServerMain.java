package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import server.services.BlackjackService;
import server.services.BlackjackServiceImpl;

public class ServerMain {
    public static void main(String[] args) {
        System.out.println("BlackjackServer is starting...");

        try {
            Registry registry = LocateRegistry.createRegistry(1099);

            BlackjackService service = new BlackjackServiceImpl();

            registry.rebind(BlackjackService.SERVICE_NAME, service);

            while (true) {
                try {
                    Thread.sleep(100000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }

            registry.unbind(BlackjackService.SERVICE_NAME);
            UnicastRemoteObject.unexportObject(service, true);
            UnicastRemoteObject.unexportObject(registry, true);
            System.out.println("BlackjackServer has stopped.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
