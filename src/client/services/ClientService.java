package client.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientService extends Remote {
	public void print() throws RemoteException;
}
