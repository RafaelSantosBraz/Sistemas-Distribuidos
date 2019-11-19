/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetocorba;

import HelloApp.Hello;
import HelloApp.HelloHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 *
 * @author Fábio Moreno
 */
public class HelloServer {
    public static void main(String args[]) {
    try{
      
      //criação e inicialização do ORB
      ORB orb = ORB.init(args, null);

      //pega as referencias para rootpoa e ativa a POAManager
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      //  cria um servidor e registra no ORB
      HelloImpl helloImpl = new HelloImpl();
      helloImpl.setORB(orb); 
     
      // obtém a referência do objeto do HelloImpl
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
      Hello href = HelloHelper.narrow(ref);
          
      // Use NamingContextExt, que faz parte do Interoperável
      // Especificação do Serviço de Nomenclatura (INS).
      org.omg.CORBA.Object objRef =
          orb.resolve_initial_references("NameService");
      // Use NamingContextExt, que faz parte do Interoperável
      // Especificação do Serviço de Nomenclatura (INS).
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // liga a referência do objeto na nomeação
      String name = "Hello";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("HelloServer pronto e esperando ...");

      // Espera a invocação de clientes
      orb.run();
    } 
        
      catch (Exception e) {
        System.err.println("ERRO: " + e);
        e.printStackTrace(System.out);
      }
          
      System.out.println("HelloServer Saindo ...");
        
  }
}
