
package co.unicauca.openmaket.client.command;

import co.unicauca.openmarket.commons.observer.Observer;
import co.unicauca.openmarket.commons.observer.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Clase Invoker que actúa como invocador de comandos. Mantiene un historial de comandos
 * ejecutados y deshechos, y permite ejecutar, deshacer y rehacer comandos.
 */

public class Invoker implements Subject {

    private final Stack<Command> commandHistory;
    private final Stack<Command> undoneCommands;
    private List<Observer> observers = new ArrayList<>();

    public Invoker() {
        commandHistory = new Stack<>();
        undoneCommands = new Stack<>();
    }
    
       /**
     * Ejecuta un comando y lo agrega al historial de comandos ejecutados.
     * Si la ejecución es exitosa, se notifica a los observadores.
     *
     * @param command el comando a ejecutar
     * @return true si el comando se ejecutó correctamente, false en caso contrario
     */

    public boolean executeCommand(Command command) {
        if (command.execute()) {
            commandHistory.push(command);
            if (!undoneCommands.isEmpty()) {
                undoneCommands.pop();
            }
            notifyObservers();
            return true;
        }
        return false;
    }

        /**
     * Deshace el último comando ejecutado y lo agrega al historial de comandos deshechos.
     * Se notifica a los observadores después de deshacer el comando.
     */
    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
            undoneCommands.push(lastCommand);
            notifyObservers();
        }
    }

     /**
     * Rehace el último comando deshecho y lo agrega al historial de comandos ejecutados.
     * Se notifica a los observadores después de rehacer el comando.
     */
    public void redoLastCommand() {
        if (!undoneCommands.isEmpty()) {
            Command lastCommand = undoneCommands.pop();
            lastCommand.execute();
            commandHistory.push(lastCommand);
            notifyObservers();
        }
    }

       /**
     * Verifica si hay más comandos que se pueden deshacer.
     *
     * @return true si hay más comandos para deshacer, false en caso contrario
     */
    public boolean hasMoreUndoCommands() {
        return !commandHistory.isEmpty();
    }

      /**
     * Verifica si hay más comandos que se pueden rehacer.
     *
     * @return true si hay más comandos para rehacer, false en caso contrario
     */
    public boolean hasMoreRedoCommands() {
        return !undoneCommands.isEmpty();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

}
