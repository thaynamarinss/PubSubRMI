import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

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
/* 
   @Override
    public void update(String message) throws RemoteException {
        System.out.println("Mensagem recebida: " + message);
    }
    */
    @Override
    public void update(String message) throws RemoteException {
        System.out.println("BUGBUGMensagem recebida: " + message);
        String[] parts = message.split(":");
        
        System.out.println("vetor bug bug");
        System.out.println(Arrays.toString(parts));

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
    
        
    /* 
    @Override
    public void update(String message) throws RemoteException {
        System.out.println("Nova mensagem recebida: " + message);
        // Verifica se o tópico da mensagem está presente na lista de tópicos inscritos
        for (String topic : subscribedTopics) {
            if (message.startsWith(topic)) {
                // Processa a mensagem
                System.out.println("Mensagem processada: " + message);
                break; // Encerra o loop ao encontrar o tópico correspondente
            }
        }
    }*/
    public List<String> getSubscribedTopics() throws RemoteException {
        return subscribedTopics;
    }

    public void setSubscribedTopics(List<String> topics) throws RemoteException {
        subscribedTopics = topics;
    }
    public static void main(String[] args) {
        try {
            PubSubInterface publisher = (PubSubInterface) Naming.lookup("rmi://localhost/PubSubServer");
            SubscriberInterface subscriber = new Subscriber("Assinante");

            publisher.subscribe(subscriber);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Digite o tópico ao qual deseja se inscrever (ou 'sair' para sair):");
            String topic = scanner.nextLine();
            while (!topic.equalsIgnoreCase("sair")) {
                publisher.subscribeToTopic(topic, subscriber);
                System.out.println("Inscrito no tópico: " + topic);
                topic = scanner.nextLine();
            }
            scanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
