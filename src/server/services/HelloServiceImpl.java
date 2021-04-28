package server.services;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import client.services.ClientService;

public class HelloServiceImpl extends UnicastRemoteObject implements HelloService {
    private static final long serialVersionUID = 1L;
    
    static int id = 0;
    
    ClientService client = null;
    
    public HelloServiceImpl() throws RemoteException {
        super();
    }
    
    @Override
	public Hello get_hello() throws RemoteException {
    	call_poop();
    	return new Hello(id++);
    }

    @Override
	public void call_poop() throws RemoteException {
    	client.print();
    }
    
    @Override
	public void connect(ClientService client) throws RemoteException {
    	this.client = client;
    }
}
