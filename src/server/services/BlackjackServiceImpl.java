package server.services;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

import server.core.Game;

public class BlackjackServiceImpl extends UnicastRemoteObject implements BlackjackService {
    private static final long serialVersionUID = 1L;

    static int id = 0;

    Hashtable<Integer, Game> games;

    public BlackjackServiceImpl() throws RemoteException {
        super(1100);
        games = new Hashtable<>();
    }

    @Override
    public BlackjackClientState new_game() throws RemoteException {
        Game g = new Game(id);
        games.put(id, g);
        ++id;
        return g.get_state();
    }

    @Override
    public void end_game(int id) throws RemoteException {
        games.remove(id);
    }

    @Override
    public BlackjackClientState do_action(int id, int action) throws RemoteException {
        if (games.containsKey(id))
            return games.get(id).do_action(action);
        return null;
    }
}
