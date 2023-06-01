
package co.unicauca.openmarket.commons.domain;

public class User {

    private String login;
    private String password;
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
    }

    public User() {
    }
   
     /**
     * Constructor de la clase User.
     * @param login Nombre de inicio de sesión del usuario.
     * @param password Contraseña del usuario.
     * @param username Nombres y apellidos completos del usuario.
     * @param id Identificador único del usuario.
     * @param tipo Tipo de usuario.
     */
    public User(String login, String password, String username, long id, String tipo) {
        this.login = login;
        this.password = password;
        this.username = username;
        this.id = id;
        this.tipo = tipo;
    }

      /**
     * Obtiene el identificador único del usuario.
     * @return El identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

     /**
     * Establece el identificador único del usuario.
     * @param id El identificador único del usuario.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Obtiene el tipo de usuario.
     * @return El tipo de usuario.
     */
    public String getTipo() {
        return tipo;
    }

     /**
     * Establece el tipo de usuario.
     * @param tipo El tipo de usuario.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

     /**
     * Obtiene el nombre de inicio de sesión del usuario.
     * @return El nombre de inicio de sesión del usuario.
     */
    public String getLogin() {
        return login;
    }

    /**
     * Establece el nombre de inicio de sesión del usuario.
     * @param login El nombre de inicio de sesión del usuario.
     */
    public void setLogin(String login) {
        this.login = login;
    }

      /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }

     /**
     * Obtiene los nombres y apellidos completos del usuario.
     * @return Los nombres y apellidos completos del usuario.
     */
    public String getUsername() {
        return username;
    }

     /**
     * Establece los nombres y apellidos completos del usuario.
     * @param username Los nombres y apellidos completos del usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
