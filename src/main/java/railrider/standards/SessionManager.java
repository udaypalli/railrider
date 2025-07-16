/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package railrider.standards;

/**
 *
 * @author MSI
 */
public class SessionManager {
    private static boolean loggedIn = false;
    private static int user_id;
    private static String email;
    
    public static boolean isLoggedIn() {
        return loggedIn;
    }
    
    public static void setLoggedIn(boolean loggedIn) {
        SessionManager.loggedIn = loggedIn;
    }
    
    public static int getSno() {
        return user_id;
    }
    
    public static void setSno(int user_id) {
        SessionManager.user_id = user_id;
    }
    
    public static String getEmail() {
        return email;
    }
    
    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    
}
