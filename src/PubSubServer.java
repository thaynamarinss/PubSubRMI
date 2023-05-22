import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class PubSubServer {
    public static void main(String[] args) {
        try {//cria uma instância da classe Publisher e a registra no registro RMI
            PublisherInterface publisher = new Publisher(); 
            Registry registry = LocateRegistry.getRegistry(); // Obter referência para o registro RMI existente
            registry.rebind("PubSubServer", publisher);
            
            System.out.println("Servidor pronto.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
