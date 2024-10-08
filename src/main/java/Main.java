import dao.ClientDAO;
import models.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ClientDAO dao = new ClientDAO();
        List<Client> clients;
        List<Integer> existingIDs = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean pass;
        int counter;
        char op = '0';

        while (op != '5') {
            System.out.println("Client Manager\n");
            System.out.println("1. Add new client");
            System.out.println("2. See current clients");
            System.out.println("3. Update client");
            System.out.println("4. Delete client");
            System.out.println("5. Exit\n");
            op = sc.next().charAt(0);

            switch (op) {
                case '1':
                    System.out.println("\n1. Add new client\n");
                    clients = dao.getClients();
                    counter = 1;
                    existingIDs.clear();
                    if (!clients.isEmpty()) {
                        System.out.println("List of existing Id's:");
                        for (Client client : clients) {
                            System.out.print("{ " + client.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            counter++;
                            existingIDs.add(client.getId());
                        }
                    }
                    Client newClient = new Client();
                    pass = false;
                    while (!pass) {
                        System.out.println("Enter the client’s unique ID: ");
                        newClient.setId(Integer.parseInt(sc.next()));
                        sc.nextLine();
                        pass = true;
                        for (int id : existingIDs) {
                            if (newClient.getId() == id) {
                                pass = false;
                                System.out.println("You cannot use an already existing id\n");
                            }
                        }
                    }
                    System.out.println("Enter the client’s name: ");
                    newClient.setName(sc.nextLine());
                    System.out.println("Enter the client’s SSN: ");
                    newClient.setSSN(sc.nextLine());
                    System.out.println("Enter the client’s department: ");
                    newClient.setDepartment(sc.nextLine());
                    System.out.println("Enter the client’s city: ");
                    newClient.setCity(sc.nextLine());
                    System.out.println("Enter the client’s website: ");
                    newClient.setWebsite(sc.nextLine());
                    newClient.setClientState(true);
                    dao.save(newClient);
                    System.out.println("\nThe client was successfully added\n");
                    break;

                case '2':
                    clients = dao.getClients();
                    System.out.println("\n2. See current clients");
                    if (clients.isEmpty()) {
                        System.out.println("There are no clients.\n");
                    } else {
                        for (Client client : clients) {
                            System.out.println(client);
                        }
                    }
                    break;

                case '3':
                    System.out.println("\n3. Update client register\n");
                    clients = dao.getClients();
                    counter = 1;
                    existingIDs.clear();
                    if (!clients.isEmpty()) {
                        System.out.println("List of existing Id's:");
                        for (Client client : clients) {
                            System.out.print("{ " + client.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            counter++;
                            existingIDs.add(client.getId());
                        }
                    } else {
                        System.out.println("There are no clients.\n");
                        break;
                    }
                    Client updatedClient = new Client();
                    pass = false;
                    while (!pass) {
                        System.out.println("Enter the client’s unique ID: ");
                        updatedClient.setId(Integer.parseInt(sc.next()));
                        sc.nextLine();
                        pass = existingIDs.contains(updatedClient.getId());
                        if (!pass) {
                            System.out.println("This ID does not exist. Please enter a valid ID.\n");
                        }
                    }
                    System.out.println("Insert the client’s name: ");
                    updatedClient.setName(sc.nextLine());
                    System.out.println("Insert the client’s SSN number: ");
                    updatedClient.setSSN(sc.nextLine());
                    System.out.println("Insert the client’s department: ");
                    updatedClient.setDepartment(sc.nextLine());
                    System.out.println("Insert the client’s city: ");
                    updatedClient.setCity(sc.nextLine());
                    System.out.println("Insert the client’s website URL: ");
                    updatedClient.setWebsite(sc.nextLine());
                    System.out.println("Insert the client’s state (true/false): ");
                    updatedClient.setClientState(sc.nextBoolean());
                    dao.update(updatedClient);
                    System.out.println("\nThe client was successfully updated in the database.\n");
                    break;

                case '4':
                    System.out.println("\n4. Delete a client register\n");
                    clients = dao.getClients();
                    counter = 1;
                    if (!clients.isEmpty()) {
                        System.out.println("List of existing Id's:");
                        existingIDs.clear();
                        for (Client client : clients) {
                            System.out.print("{ " + client.getId() + "} ");
                            if (counter % 5 == 0) {
                                System.out.println();
                            }
                            counter++;
                            existingIDs.add(client.getId());
                        }
                    } else {
                        System.out.println("There are no clients.\n");
                        break;
                    }
                    int removedId = 0;
                    pass = false;
                    while (!pass) {
                        System.out.println("Enter the ID of the client that you want to delete: ");
                        removedId = Integer.parseInt(sc.next());
                        sc.nextLine();
                        pass = existingIDs.contains(removedId);
                        if (!pass) {
                            System.out.println("This ID does not exist. Please enter a valid ID.\n");
                        }
                    }
                    Client deletedClient = dao.findById(removedId);
                    dao.delete(deletedClient);
                    System.out.println("\nThe client was successfully deleted\n");
                    break;

                case '5':
                    System.out.println("\nThanks for using the program.\n");
                    break;

                default:
                    System.out.println("You have entered an invalid option, Please choose an option between 1 and 5.\n");
                    break;
            }
        }
    }
}

