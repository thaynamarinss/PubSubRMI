package pubSub;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Publisher extends Remote {
    void publish(String topic, String message) throws RemoteException;
}
