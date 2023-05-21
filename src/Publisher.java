import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Publisher extends UnicastRemoteObject implements PubSubInterface {
    private Map<String, List<SubscriberInterface>> subscribersByTopic;
    private Map<String, String> topicData;

    public Publisher() throws RemoteException {
        super();
        subscribersByTopic = new HashMap<>();
        topicData = new HashMap<>();
    }

    @Override
    public void subscribe(SubscriberInterface subscriber) throws RemoteException {
        subscribeToTopic("default", subscriber);
    }


    @Override
    public void publish(String message) throws RemoteException {
        System.out.println("Publicando mensagem: " + message);
        String[] parts = message.split(":");
        String topic = parts[0];
     

        if (subscribersByTopic.containsKey(topic)) {
            List<SubscriberInterface> subscribers = subscribersByTopic.get(topic);
            for (SubscriberInterface subscriber : subscribers) {
                subscriber.update(message);
            }
        }


    }

    @Override
    public void subscribeToTopic(String topic, SubscriberInterface subscriber) throws RemoteException {
        
        List<SubscriberInterface> subscribers = subscribersByTopic.getOrDefault(topic, new ArrayList<>());
        subscribers.add(subscriber);
        subscribersByTopic.put(topic, subscribers);
        System.out.println("Assinante " + subscriber.getName() + " inscrito no tópico: " + topic);
    }

   
    // Método para criar informações para um tópico específico
    public synchronized void createTopicData(String topic, String data) {
        topicData.put(topic, data);
        System.out.println("Informações criadas para o tópico " + topic + ": " + data);
    }

    // Método para publicar informações para um tópico específico
    
    public synchronized void publishTopicData(String topic) throws RemoteException {
        if (subscribersByTopic.containsKey(topic) && topicData.containsKey(topic)) {
            List<SubscriberInterface> subscribers = subscribersByTopic.get(topic);
            String data = topicData.get(topic);
            for (SubscriberInterface subscriber : subscribers) {
                subscriber.update(data);
            }
        }
    }
    

    

}
