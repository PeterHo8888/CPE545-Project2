package server.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface BlackjackService extends Remote {
    static final String SERVICE_NAME = "BlackjackService";

    public BlackjackClientState new_game() throws RemoteException;

    public void end_game(int id) throws RemoteException;

    public BlackjackClientState do_action(int id, int action) throws RemoteException;
}
