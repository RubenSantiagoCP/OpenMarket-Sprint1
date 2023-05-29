
package co.unicauca.openmarket.commons.domain;

public class User {
     /**
     *
     */
    private String login;
    /**
     *
     */
    private String password;
    /**
     * Nombres y apellidos completos
     */
    private String username;
    
    private long id;
    
    private String tipo;

    /**
     * Constructor que inicializa un usuario
     *
     * @param login nombre del usuario
     * @param password contraseña secreta
     * @param username nombre del usuario
     */
    public User(String login, String password, String username) {
        this.login = login;
        this.password = password;
        this.username = username;

        //Vuelo vuelo = new Vuelo();
    }

    public User() {
    }
    
    

    public User(String login, String password, String username, long id, String tipo) {
        this.login = login;
        this.password = password;
        this.username = username;
        this.id = id;
        this.tipo = tipo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contrasenia) {
        this.password = contrasenia;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String nombres) {
        this.username = nombres;
    }

}
