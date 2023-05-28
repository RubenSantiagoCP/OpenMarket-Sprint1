package co.unicauca.openmaket.client.command;

import co.unicauca.openmarket.client.domain.service.BuyService;
import co.unicauca.openmarket.commons.domain.Buy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nimbachi
 */
public class AddBuyCommand implements Command{
    //Revisar por que estan como private final
    private BuyService buyService;
    private Buy buy;

    public AddBuyCommand(BuyService buyService, Buy buy) {
        this.buyService = buyService;
        this.buy = buy;
    }
    
    @Override
    public boolean execute() {
        Long compraId = buy.getId();
        Long compradorId = buy.getCompradorId();
        Long productoId = buy.getProductoId();
        String estadoCompra = buy.getEstado();
        String fechaCompra = buy.getFechaCompra();
        
        try {
            return buyService.saveBuy(compraId, estadoCompra, fechaCompra, compradorId, productoId);
        } catch (Exception e) {
            Logger.getLogger(AddBuyCommand.class.getName()).log(Level.SEVERE, null, e);
            return false;
        }
    }

    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
