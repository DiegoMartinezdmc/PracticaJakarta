package interfaces;

import models.Client;
import java.util.List;

public interface IClient {
    public void save(Client client);

    public List<Client> getClients();

    public void update(Client client);

    public void delete(Client client);
}
