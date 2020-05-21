package frontend.una.cr.view;

import frontend.una.cr.controller.HospitalController;
import frontend.una.cr.model.Admin;
import frontend.una.cr.model.Hospital;
import frontend.una.cr.service.ServiceFacade;

import javax.swing.*;
import java.awt.*;

public class HospitalView extends JFrame{
    Hospital hospital;
    ServiceFacade service;
    Admin admin;


    JPanel root = new JPanel(new BorderLayout());
    JPanel topPanel = new JPanel();
    JPanel centerPanel = new JPanel(new GridLayout(5,2));
    JPanel downPanel = new JPanel();
    JLabel nameHospJLabel = new JLabel("Nombre del Consultorio:");
    JLabel day1Jlabel = new JLabel("Dia 1 de atencion: ");
    JLabel day2Jlabel = new JLabel("Dia 2 de atencion: ");
    JLabel hourJlabel = new JLabel("Hora de antencion: ");
    JLabel phoneJLabel = new JLabel("Telefono: ");

    JLabel nameHospitalTextField;
    JTextField day1TextField;
    JTextField day2TextField;
    JTextField hourTextField;
    JTextField phoneTextField;

    JButton saveButton = new JButton("Guardar");

    /**
     *
     * @param hosp
     * @param ser
     * @param ad
     * @throws ClassNotFoundException
     * @throws UnsupportedLookAndFeelException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */

    public HospitalView(Hospital hosp, ServiceFacade ser, Admin ad) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("Consultorio");
        hospital = hosp;
        service = ser;
        admin = ad;
        add(root);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLookAndFeel();
        creatingTextField();
        root.add(topPanel, BorderLayout.PAGE_START);
        addToCenterPanel();
        downPanel.add(saveButton);
        root.add(downPanel, BorderLayout.PAGE_END);

        HospitalController controller = new HospitalController(this, hospital, service, admin);
        addListener(controller);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addToCenterPanel(){
        centerPanel.add(nameHospJLabel);
        centerPanel.add(nameHospitalTextField);
        centerPanel.add(day1Jlabel);
        centerPanel.add(day1TextField);
        centerPanel.add(day2Jlabel);
        centerPanel.add(day2TextField);
        centerPanel.add(hourJlabel);
        centerPanel.add(hourTextField);
        centerPanel.add(phoneJLabel);
        centerPanel.add(phoneTextField);
        root.add(centerPanel, BorderLayout.CENTER);
    }

    private void creatingTextField(){
        nameHospitalTextField = new JLabel(hospital.getNameH());
        day1TextField = new JTextField(hospital.getDay1());
        day2TextField = new JTextField(hospital.getDay2());
        hourTextField = new JTextField(hospital.getTime());
        phoneTextField = new JTextField(hospital.getPhone());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void addListener(HospitalController c){
        saveButton.addActionListener(c);
    }

    //////////////////////////////////////// setters y getters ////////////////////////////////////////////////////////////

    public String getNameTextField(){ return nameHospitalTextField.getText();}
    public void setNameHospJLabel(String hosp){ nameHospitalTextField.setText(hosp);}

    public String getDay1TextField() { return day1TextField.getText(); }
    public void setDay1TextField(String day1Text) { this.day1TextField.setText(day1Text); }

    public String getDay2TextFild(){return day2TextField.getText();}
    public void setDay2TextField(String day2Text){ this.day2TextField.setText(day2Text);}

    public String getPhoneText(){return phoneTextField.getText();}
    public void setPhoneText( String phoneText){phoneTextField.setText(phoneText);}

    public String hourText(){return hourTextField.getText();}






    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void setLookAndFeel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
        );


    }
}
