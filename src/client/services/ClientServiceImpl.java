package client.services;

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ClientServiceImpl extends UnicastRemoteObject implements ClientService {
	private static final long serialVersionUID = 1L;

	public ClientServiceImpl() throws RemoteException {
		super();
	}

	public void print() throws RemoteException {
		System.out.println("client");
	}
}
