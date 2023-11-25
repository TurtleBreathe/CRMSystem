import java.util.Scanner;

public class ClientManager extends Manager{
    public ClientManager(Service service) {
        this.clientService = service;
    }
    @Override
    public void performAction(String command) {
        Scanner scanner = new Scanner(System.in);
        String[] tokens = command.split(" ");

        switch (tokens[0]) {

            case "Add":
                String[] info = scanner.nextLine().split(", ");
                Client client = new Client(
                        Integer.parseInt(info[0]),
                        info[1],
                        info[2],
                        info[3],
                        Double.parseDouble(info[4]));
                clientService.addClient(client);
                System.out.println("Added Client");
                break;

            case "Update":
                String[] clientToUpdate = scanner.nextLine().split(", ");
                client = new Client(
                        Integer.parseInt(clientToUpdate[0]),
                        clientToUpdate[1],
                        clientToUpdate[2],
                        clientToUpdate[3],
                        Double.parseDouble(clientToUpdate[4]));
                clientService.update(client);
                System.out.println("Updated Client");
                break;

            case "Remove":
                clientService.remove(Integer.parseInt(tokens[2]));
                System.out.println("Removed Client");
                break;

            case "Search":
                StringBuilder sb = new StringBuilder();
                for (int i = 2; i < tokens.length; i++) {
                    sb.append(tokens[i]).append(" ");
                }
                clientService.search(sb.toString().trim(), tokens[1]);
                break;
            case "View":
                clientService.view();
                break;
        }
    }
}
