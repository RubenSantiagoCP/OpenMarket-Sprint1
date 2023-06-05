package co.unicauca.openmaket.client.command;

import co.unicauca.openmarket.client.domain.service.BuyService;
import co.unicauca.openmarket.commons.domain.Buy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Comando concreto que representa la operación de agregar una compra.
 * Implementa la interfaz Command.
 */
public class AddBuyCommand implements Command{
   
    private final BuyService buyService;
    private final Buy buy;

     /**
     * Constructor de la clase AddBuyCommand.
     * 
     * @param buyService Servicio de compra que se utilizará para agregar la compra.
     * @param buy       Compra a ser agregada.
     */
    public AddBuyCommand(BuyService buyService, Buy buy) {
        this.buyService = buyService;
        this.buy = buy;
    }
    
     /**
     * Ejecuta el comando para agregar una compra.
     * 
     * @return true si la compra se agregó correctamente, false en caso contrario.
     */
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

      /**
     * Método no soportado en este comando.
     * Lanza una excepción UnsupportedOperationException si se intenta ejecutar.
     */
    @Override
    public void undo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
