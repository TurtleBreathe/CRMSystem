import java.io.IOException;

public interface Service {

    void addClient(Client client);
    void update(Client client);
    void view();
    void remove(int ID);
    void saveToFile() throws IOException;

    void search(String info, String command);

}
