package server.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

import client.services.ClientService;

public interface HelloService extends Remote {
	static final String SERVICE_NAME = "HelloService";

	Hello get_hello() throws RemoteException;
	void connect(ClientService client) throws RemoteException;
	void call_poop() throws RemoteException;
}
