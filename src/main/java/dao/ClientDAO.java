package dao;

import interfaces.IClient;
import jakarta.persistence.EntityManager;
import models.Client;

import java.util.List;

public class ClientDAO implements IClient {
    @Override
    public void save (Client client){
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();
    }

    @Override
    public List<Client> getClients(){
        EntityManager em = EntityManagerAdmin.getInstance();
        List<Client> clients = em.createQuery("from Client", Client.class).getResultList();
        return clients;
    }

    @Override
    public void update (Client client){
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.merge(client);
        em.getTransaction().commit();
    }

    @Override
    public void delete (Client client){
        EntityManager em = EntityManagerAdmin.getInstance();
        em.getTransaction().begin();
        em.remove(client);
        em.getTransaction().commit();
    }

    public Client findById(int id){
        EntityManager em = EntityManagerAdmin.getInstance();
        return em.find(Client.class, id);
    }
}
