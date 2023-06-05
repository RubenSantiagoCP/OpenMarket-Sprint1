
package co.unicauca.openmarket.server.infra.tcpip;

import co.unicauca.openmarket.server.access.BankAccountImplMySql;
import co.unicauca.openmarket.server.access.BuyRepositoryImplMySql;
import co.unicauca.openmarket.server.access.CategoryRepositoryImplMysql;
import co.unicauca.openmarket.server.access.IBuyRepository;
import co.unicauca.openmarket.server.access.ICategoryRepository;
import co.unicauca.openmarket.server.access.IProductRepository;
import co.unicauca.openmarket.server.access.IUserRepository;
import co.unicauca.openmarket.server.access.ProductRepositoryImplMysql;
import co.unicauca.openmarket.server.access.UserRepositoryImplMySql;
import co.unicauca.openmarket.server.domain.services.BankAccountService;
import co.unicauca.openmarket.server.domain.services.BuyService;
import co.unicauca.openmarket.server.domain.services.CategoryService;
import co.unicauca.openmarket.server.domain.services.ProductService;
import co.unicauca.openmarket.server.domain.services.UserService;
import co.unicauca.strategyserver.infra.ServerSocketMultiThread;

import java.util.Scanner;

public class OpenMarketServer {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese el puerto de escucha");
        int port = teclado.nextInt();
        ServerSocketMultiThread myServer = new ServerSocketMultiThread(port);
        OpenMarketHandler myHandler = new OpenMarketHandler();
       
        myHandler.setCategoryService(new CategoryService(new CategoryRepositoryImplMysql() ));
        myHandler.setProductService(new ProductService(new ProductRepositoryImplMysql()));
        myHandler.setUserService(new UserService(new UserRepositoryImplMySql()));
        myHandler.setBuyService(new BuyService(new BuyRepositoryImplMySql()));
        myHandler.setBankService(new BankAccountService(new BankAccountImplMySql()));
        myServer.setServerHandler(myHandler);
        myServer.startServer();
    }
}