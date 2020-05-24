package cr.una.frontend.view;

import cr.una.frontend.controller.LoginController;
import cr.una.frontend.service.ServiceFacade;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginView extends JFrame {

    private JPanel rootPanel;
    private  JTabbedPane tabbedPane;

    // ********************************************** LOGIN ************************************************************
    private JPanel loginPane;

    private JLabel titleLoginLbl;

    // center panel
    private JPanel centerPanel;
    private JLabel id;
    private JLabel password;
    private JTextField textId;
    private JPasswordField textPassword;

    // Buttons panel
    private JPanel btnPanel;
    private JButton okBtn;

    // ******************************************* SIGN IN *************************************************************
    private JPanel signInPane;

    // Top Panel
    private JPanel signInTopPanel;
    private JLabel signInLbl;

    // Info Panel
    private JPanel signInInfoPanel;
    private JLabel nameLbl;
    private JLabel lastNameLbl;
    private JLabel idLbl;
    private JLabel birthYearLbl;
    private JLabel phoneLbl;
    private JLabel addressLbl;
    private JLabel passwordLbl;
    private JTextField nameTxtField;
    private JTextField lastNameTxtField;
    private JTextField idTxtField;
    private JTextField birthYearTxtField;
    private JTextField phoneTxtField;
    private JTextField addressTxtField;
    private JTextField passwordTxtField;

    // Bottom panel
    private JPanel bottomPanel;
    private JButton signInBtn;


    public LoginView(ServiceFacade service) throws IOException {
        super("Login System");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLookAndFeel();

        rootPanel = new JPanel(new GridLayout(1,1));
        tabbedPane = new JTabbedPane();

        createLoginTabPane();
        createSignInTabPane();

        rootPanel.add(tabbedPane);
        add(rootPanel);

        LoginController controller = new LoginController(this, service);
        addListeners(controller);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void cleanTxtField() {
        nameTxtField.setText("");
        lastNameTxtField.setText("");
        idTxtField.setText("");
        birthYearTxtField.setText("");
        phoneTxtField.setText("");
        addressTxtField.setText("");
        passwordTxtField.setText("");
    }

    private void createLoginTabPane() {
        loginPane = new JPanel(new BorderLayout());
        createLoginTopPanel();
        createLoginCenterPanel();
        createLoginBtnPanel();
        loginPane.add(new JPanel(), BorderLayout.LINE_START);
        loginPane.add(new JPanel(), BorderLayout.LINE_END);
        tabbedPane.addTab("Iniciar sesion", loginPane);
    }

    private void createLoginTopPanel() {
        titleLoginLbl = new JLabel("Iniciar sesion");
        titleLoginLbl.setFont(new Font(Font.SERIF,-1,28));
        titleLoginLbl.setHorizontalAlignment(SwingConstants.CENTER);
        loginPane.add(titleLoginLbl, BorderLayout.PAGE_START);
    }

    private void createLoginCenterPanel() {
        centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JPanel idPanel = new JPanel();
        textId = new JTextField();
        id = new JLabel("            Id: ");
        JPanel passPanel = new JPanel();
        textPassword = new JPasswordField(15);
        password = new JLabel("Password: ");

        textId.setColumns(15);

        textId.setName("textName");
        textPassword.setName("textPassword");
        id.setName("name");
        password.setName("Password");

        idPanel.add(id);
        idPanel.add(textId);
        passPanel.add(password);
        passPanel.add(textPassword);
        centerPanel.add(new JPanel());
        centerPanel.add(idPanel);
        centerPanel.add(passPanel);
        centerPanel.add(new JPanel());
        loginPane.add(centerPanel, BorderLayout.CENTER);
    }

    private void createLoginBtnPanel() {
        btnPanel = new JPanel();
        okBtn = new JButton("Ingresar");
        okBtn.setName("Ingresar");
        btnPanel.add(okBtn);
        loginPane.add(btnPanel, BorderLayout.PAGE_END);
    }

    private void createSignInTabPane() {
        signInPane = new JPanel(new BorderLayout());
        createSignInTopPanel();
        createSignInInfoPanel();
        createSignInBottomPanel();
        tabbedPane.addTab("Registrarse", signInPane);
    }

    private void createSignInTopPanel() {
        signInLbl = new JLabel("Ingresa tus datos");
        signInLbl.setFont(new Font(Font.SERIF,-1,28));
        signInTopPanel = new JPanel();
        signInTopPanel.add(signInLbl);
        signInPane.add(signInTopPanel, BorderLayout.PAGE_START);
    }

    private void createSignInInfoPanel() {
        signInInfoPanel = new JPanel(new GridLayout(-1,1));
        // Sorry for this :'(
        nameLbl = new JLabel("       Nombre: ");
        lastNameLbl = new JLabel("       Apellido: ");
        idLbl = new JLabel("                Id: ");
        birthYearLbl = new JLabel("  Nacimiento: ");
        phoneLbl = new JLabel("     Telefono: ");
        addressLbl = new JLabel("    Direccion: ");
        passwordLbl = new JLabel("Contrasena: ");
        nameTxtField = new JTextField();
        lastNameTxtField = new JTextField();
        idTxtField = new JTextField();
        birthYearTxtField = new JTextField();
        phoneTxtField = new JTextField();
        addressTxtField = new JTextField();
        passwordTxtField = new JTextField();

        nameTxtField.setColumns(15);
        lastNameTxtField.setColumns(15);
        idTxtField.setColumns(15);
        birthYearTxtField.setColumns(15);
        phoneTxtField.setColumns(15);
        addressTxtField.setColumns(15);
        passwordTxtField.setColumns(15);

        JPanel namePanel = new JPanel();
        JPanel lastNamePanel = new JPanel();
        JPanel idPanel = new JPanel();
        JPanel birthPanel = new JPanel();
        JPanel phonePanel = new JPanel();
        JPanel addressPanel = new JPanel();
        JPanel passwordPanel = new JPanel();

        namePanel.add(nameLbl);
        namePanel.add(nameTxtField);
        lastNamePanel.add(lastNameLbl);
        lastNamePanel.add(lastNameTxtField);
        idPanel.add(idLbl);
        idPanel.add(idTxtField);
        birthPanel.add(birthYearLbl);
        birthPanel.add(birthYearTxtField);
        phonePanel.add(phoneLbl);
        phonePanel.add(phoneTxtField);
        addressPanel.add(addressLbl);
        addressPanel.add(addressTxtField);
        passwordPanel.add(passwordLbl);
        passwordPanel.add(passwordTxtField);

        signInInfoPanel.add(namePanel);
        signInInfoPanel.add(lastNamePanel);
        signInInfoPanel.add(idPanel);
        signInInfoPanel.add(birthPanel);
        signInInfoPanel.add(phonePanel);
        signInInfoPanel.add(addressPanel);
        signInInfoPanel.add(passwordPanel);
        signInPane.add(signInInfoPanel, BorderLayout.CENTER);
    }

    private void createSignInBottomPanel() {
        signInBtn = new JButton("Registrar");
        signInBtn.setName("Registrar");
        bottomPanel = new JPanel();
        bottomPanel.add(signInBtn);
        signInPane.add(bottomPanel, BorderLayout.PAGE_END);
    }

    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(
                    "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
            );
        } catch (Exception exc) {
            // ignore error
        }
    }

    public void addListeners(ActionListener controller) {
        okBtn.addActionListener(controller);
        signInBtn.addActionListener(controller);
    }

    public String getTextId(){ return textId.getText(); }

    public String getTextPassword() { return String.valueOf(textPassword.getPassword()); }

    public JButton getOkBtn() { return okBtn; }

    public JButton getSignInBtn() { return signInBtn; }

    public JTextField getNameTxtField() { return nameTxtField; }

    public JTextField getLastNameTxtField() { return lastNameTxtField; }

    public JTextField getIdTxtField() { return idTxtField; }

    public JTextField getBirthYearTxtField() { return birthYearTxtField; }

    public JTextField getPhoneTxtField() { return phoneTxtField; }

    public JTextField getAddressTxtField() { return addressTxtField; }

    public JTextField getPasswordTxtField() { return passwordTxtField; }

    public void showError(String e) { JOptionPane.showMessageDialog(this, e); }
}
