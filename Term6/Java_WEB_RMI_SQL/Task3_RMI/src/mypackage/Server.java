package mypackage;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String [] args){
        try{
            String localhost = "10.160.9.90";
            String RMI_HOSTNAME = "java.rmi.server.hostname";
            System.setProperty(RMI_HOSTNAME,localhost);

            RemoteService obj =  new NotebookDatabase();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("Anna",obj); //прокси(заглушка)
            System.out.println("zapustilsia");

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());

        }


    }
}
