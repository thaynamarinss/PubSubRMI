import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Subscriber extends UnicastRemoteObject implements SubscriberInterface {
    private String name;
 
    private List<String> subscribedTopics;

    public Subscriber(String name) throws RemoteException {
        super();
        this.name = name;
        this.subscribedTopics = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String message) throws RemoteException {       
        String[] parts = message.split(":");
        if (parts.length >= 2) {
            String topic = parts[0];
            String data = parts[1];
    
            if (subscribedTopics.contains(topic)) {
                System.out.println("Mensagem recebida: " + data);
            }
        } else {
            System.out.println("Mensagem inválida: " + message);
        }
    }

    public void setSubscribedTopics(List<String> topics) throws RemoteException {
        subscribedTopics = topics;
    }
    
    public static void main(String[] args) {
        try {
            // PubSubInterface publisher = (PubSubInterface) Naming.lookup("rmi://localhost/PubSubServer");
            // SubscriberInterface subscriber = new Subscriber("Assinante");

            // publisher.subscribe(subscriber);

            // Scanner scanner = new Scanner(System.in);
            // System.out.println("Digite o tópico ao qual deseja se inscrever (ou 'sair' para sair):");
            // String topic = scanner.nextLine();
            // while (!topic.equalsIgnoreCase("sair")) {
            //     publisher.subscribeToTopic(topic, subscriber);
            //     System.out.println("Inscrito no tópico: " + topic);
            //     topic = scanner.nextLine();
            // }
            // scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
