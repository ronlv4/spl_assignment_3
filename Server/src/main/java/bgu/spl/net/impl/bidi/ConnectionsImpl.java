package bgu.spl.net.impl.bidi;

import bgu.spl.net.api.bidi.Connections;
import bgu.spl.net.srv.bidi.ConnectionHandler;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionsImpl<T> implements Connections<T> {

    private Map<Integer,ConnectionHandler<T>> activeConnections = new ConcurrentHashMap<>();
    private AtomicInteger connectionId = new AtomicInteger(0);

    @Override
    public boolean send(int connectionId, T msg) {
        ConnectionHandler<T> handler = activeConnections.get(connectionId);
        if (handler == null)
            return false;
        try{
            handler.send(msg);
            return true;
        } catch (Exception e){ // TODO: General Exception
            return false;
        }
    }

    @Override
    public void broadcast(T msg) {
        for (ConnectionHandler<T> handler: activeConnections.values()){
            handler.send(msg);
        }

    }

    @Override
    public void disconnect(int connectionId) {
        activeConnections.remove(connectionId);
    }

    public int addConnection(ConnectionHandler<T> handler){
        int conId = connectionId.getAndIncrement();
        activeConnections.put(conId, handler);
        return conId;
    }

    public ConnectionHandler<T> getHandler(int connectionId){
        return activeConnections.get(connectionId);

    }
}