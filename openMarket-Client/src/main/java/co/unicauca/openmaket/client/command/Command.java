
package co.unicauca.openmaket.client.command;

/**
 * Interfaz que define los métodos requeridos para implementar un comando.
 * Un comando representa una operación o acción que puede ser ejecutada y deshecha.
 */
public interface Command {
     /**
     * Ejecuta el comando.
     * @return true si el comando se ejecuta correctamente, false en caso contrario.
     */
    boolean execute();
    
      /**
     * Deshace el comando, revirtiendo los cambios realizados por la ejecución del comando.
     */
    void undo();
}

