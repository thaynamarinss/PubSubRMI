import java.rmi.Naming;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class PubSubClient {
    private static final String SERVER_URL = "rmi://localhost:1099/PubSubServer";
    private static final String EXIT_OPTION = "0";

    private PubSubInterface server;
    private Scanner scanner;
    private String subscriberName;
 

    public PubSubClient() {
        try {
            server = (PubSubInterface) Naming.lookup(SERVER_URL);
            scanner = new Scanner(System.in);
            subscriberName = "";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("=== PubSub Client ===");
        System.out.print("Digite seu nome: ");
        subscriberName = scanner.nextLine();

        boolean exit = false;
        while (!exit) {
            printMenu();
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    subscribeTopic();
                    break;
                case "2":
                    unsubscribeTopic();
                    break;
                case "3":
                    publishMessage();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
                    break;
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Inscrever em um topico");
        System.out.println("2. Desinscrever de um topico");
        System.out.println("3. Publicar uma mensagem");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opcao: \n");
    }


    private void subscribeTopic() {
        System.out.print("Digite o nome do topico que voce quer se inscrever: ");
        String topic = scanner.nextLine();
    
        try {
            SubscriberInterface subscriber = new Subscriber(subscriberName);
            List<String> subscribedTopics = new ArrayList<>();
            subscribedTopics.add(topic); // Adiciona o tópico à lista de tópicos inscritos
            subscriber.setSubscribedTopics(subscribedTopics); // Define a lista de tópicos inscritos do assinante
            server.subscribeToTopic(topic, subscriber);
            System.out.println("Voce foi inscrito no seguinte topico: " + topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        

    private void unsubscribeTopic() {
        System.out.print("Digite o nome do topico para desinscrever: ");
        String topic = scanner.nextLine();

        try {
            SubscriberInterface subscriber = new Subscriber(subscriberName);
            server.unsubscribeFromTopic(topic, subscriber);
            System.out.println("Voce nao participa mais do topico: " + topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void publishMessage() {
        System.out.print("Qual topico vocer publicar?: ");
        String topic = scanner.nextLine();

        System.out.print("Digite a mensagem para publicar: ");
        String message = scanner.nextLine();
        String messagePassa = topic+':'+message;

        try {
            server.publish(messagePassa);
            System.out.println("mensagem publicada no topico: " + topic);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PubSubClient client = new PubSubClient();
        client.run();
    }
}
