
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface SubscriberInterface extends Remote {
    void update(String message) throws RemoteException;
    String getName() throws RemoteException;
    List<String> getSubscribedTopics() throws RemoteException;
    void setSubscribedTopics(List<String> topics) throws RemoteException;
}
