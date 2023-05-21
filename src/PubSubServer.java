import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class PubSubServer {
    public static void main(String[] args) {
        try {
            PubSubInterface publisher = new Publisher();
            Registry registry = LocateRegistry.getRegistry(); // Obter referência para o registro RMI existente
            registry.rebind("PubSubServer", publisher);

            
            System.out.println("Servidor pronto.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
