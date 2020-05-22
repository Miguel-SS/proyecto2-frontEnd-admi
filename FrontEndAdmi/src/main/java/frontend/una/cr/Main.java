package frontend.una.cr;

import frontend.una.cr.service.ServiceFacade;
import frontend.una.cr.view.LoginView;

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
