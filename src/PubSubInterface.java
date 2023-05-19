import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PubSubInterface extends Remote {
    void subscribe(SubscriberInterface subscriber) throws RemoteException;
    void unsubscribe(SubscriberInterface subscriber) throws RemoteException;
    void publish(String message) throws RemoteException;
    void subscribeToTopic(String topic, SubscriberInterface subscriber) throws RemoteException;
    void unsubscribeFromTopic(String topic, SubscriberInterface subscriber) throws RemoteException;
}
