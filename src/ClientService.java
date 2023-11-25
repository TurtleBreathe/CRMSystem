import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientService implements Service {
    private final List<Client> clients;
    private final CSVReader reader;

    public ClientService(CSVReader reader) throws IOException {
        this.reader = reader;
        this.clients = new ArrayList<>();
        loadFromCSV();
        System.out.println(clients);
    }

    private void loadFromCSV() throws IOException {
        try {
            List<String[]> lines = reader.readAll();
            if (lines.isEmpty()) {
                return;
            }
            if (lines.size() == 1 && lines.get(0)[0].isEmpty()) {
                return;
            }
            int startingRow = 0;
            while (lines.get(startingRow)[0].isEmpty()) {
                startingRow++;
            }

            for (int i = startingRow; i < lines.size(); i++) {
                String[] row = lines.get(i)[0].split(";");
                Client client = new Client(
                        Integer.parseInt(row[0]),
                        row[1],
                        row[2],
                        row[3],
                        Double.parseDouble(row[4])
                );
                clients.add(client);
            }
        } catch (CsvException e) {
            throw new RuntimeException(e);
        }
        reader.close();
    }

    @Override
    public void addClient(Client client) {
        clients.add(client);
    }

    @Override
    public void update(Client client) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getID() == client.getID()) {
                clients.set(i, client);
                return;
            }
        }
    }

    @Override
    public void remove(int ID) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getID() == ID) {
                clients.remove(i);
                return;
            }
        }
    }

    @Override
    public void view() {
        System.out.println(clients.toString());
    }

    @Override
    public void saveToFile() throws IOException {
        File file2 = new File("textfile.csv");
        FileWriter outputFile = new FileWriter(file2, false);
        CSVWriter writer = (CSVWriter) new CSVWriterBuilder(outputFile).withSeparator(';').withQuoteChar(CSVWriter.NO_QUOTE_CHARACTER)
                .build();

        List<String[]> toList = clients.stream().map(e -> e.convertToStringArray()).toList();
        writer.writeAll(toList);
        writer.flush();
        writer.close();
    }

    @Override
    public void search(String info, String command) {
        if (command.equals("ID")) {
            int id = Integer.parseInt(info);
            List<Client> search = clients.stream().filter(e -> e.getID() == id).collect(Collectors.toList());
            System.out.println(search);
        } else if (command.equals("Name")) {
            List<Client> search = clients.stream().filter(e -> e.getName().equals(info)).collect(Collectors.toList());
            System.out.println(search);
        } else if (command.equals("Industry")) {
            List<Client> search = clients.stream().filter(e -> e.getIndustry().equals(info)).collect(Collectors.toList());
            System.out.println(search);
        } else {
            System.out.println("Wrong input! Try again.");
        }
    }
}

