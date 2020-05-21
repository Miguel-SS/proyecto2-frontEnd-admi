package frontend.una.cr.view;

import frontend.una.cr.controller.EditPatientController;
import frontend.una.cr.model.Admin;
import frontend.una.cr.model.Patient;
import frontend.una.cr.service.ServiceFacade;

import javax.swing.*;
import java.awt.*;

public class EditPatientView extends JFrame {

    Admin admin;
    ServiceFacade service;
    Patient patient;


    JPanel root = new JPanel(new BorderLayout());
    JPanel top = new JPanel();
    JPanel center = new JPanel(new GridLayout(8, 2));
    JPanel down = new JPanel();

    JTextField firstNameText = new JTextField();
    JLabel firstNameLabel = new JLabel("Nombre: ");
    JTextField lastNameText = new JTextField();
    JLabel lastNameLabel = new JLabel("Apellidos: ");
    JTextField idText = new JTextField("Id: ");
    JTextField ageText = new JTextField();
    JLabel ageLabel = new JLabel("Edad: ");
    JTextField phoneText = new JTextField();
    JLabel phoneLabel = new JLabel("Telefono: ");
    JTextField diseaseText = new JTextField();
    JLabel diseaseLabel = new JLabel("Enfermedades: ");
    JTextField observationText = new JTextField();
    JLabel observationLabel = new JLabel("Observaciones: ");

    JButton saveBtn = new JButton("Guardad");
    JButton deleteBtn = new JButton("Eliminar");

    public EditPatientView(Patient pat, ServiceFacade ser, Admin ad) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("Edit Patient");
        patient = pat;
        service = ser;
        admin = ad;
        add(root);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLookAndFeel();


        addToRootPanel();

        EditPatientController controller = new EditPatientController(this, service, admin,  patient);
        addActionListener(controller);


        setLocationRelativeTo(null);
        setVisible(true);

    }


    private void createText(){
        firstNameText = new JTextField(patient.getName());
        lastNameText = new  JTextField(patient.getLastName());
        ageText = new  JTextField(Integer.toString(patient.getBirthYear()));
        phoneText = new  JTextField(patient.getPhone());
        diseaseText = new  JTextField(patient.getDisease());
        observationText = new JTextField(patient.getObservation());
    }

    private void addCenter(){
        createText();
        center.add(firstNameLabel);
        center.add(firstNameText);
        center.add(lastNameLabel);
        center.add(lastNameText);
        center.add(ageLabel);
        center.add(ageText);
        center.add(phoneLabel);
        center.add(phoneText);
        center.add(diseaseLabel);
        center.add(diseaseText);
        center.add(observationLabel);
        center.add(observationText);


        root.add(center, BorderLayout.CENTER);
    }

    void addToRootPanel(){
        root.add(top, BorderLayout.PAGE_START);
        addCenter();
        down.add(saveBtn);
        down.add(deleteBtn);
        root.add(down, BorderLayout.PAGE_END);

    }

    public void addActionListener(EditPatientController controller){
        saveBtn.addActionListener(controller);
        deleteBtn.addActionListener(controller);
    }


    ///////////////////////////////////////////getters/////////////////////////////////////////////////////////////////

    public JTextField getFirstNameText() {
        return firstNameText;
    }

    public JTextField getLastNameText() {
        return lastNameText;
    }

    //public JTextField getIdText() { return idText; }

    public JTextField getDiseaseText() {
        return diseaseText;
    }

    public JTextField getPhoneText() {
        return phoneText;
    }

    public JTextField getObservationText() {
        return observationText;
    }

    public JTextField getAgeText() {
        return ageText;
    }

    public JButton getSaveBtn(){ return saveBtn; }

    public JButton getDeleteBtn(){ return deleteBtn;}



    private static void setLookAndFeel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
        );
    }
}
