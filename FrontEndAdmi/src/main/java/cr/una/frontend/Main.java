package cr.una.frontend;

import cr.una.frontend.view.LoginView;
import cr.una.frontend.service.ServiceFacade;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            LoginView view = new LoginView(new ServiceFacade());
        }  catch (Exception ex) {
            JOptionPane.showMessageDialog(null,"ERROR", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
