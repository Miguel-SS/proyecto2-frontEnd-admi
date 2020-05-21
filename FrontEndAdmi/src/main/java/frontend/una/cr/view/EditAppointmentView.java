package frontend.una.cr.view;

import frontend.una.cr.controller.EditAppointmentController;
import frontend.una.cr.model.Admin;
import frontend.una.cr.model.Appointment;
import frontend.una.cr.service.ServiceFacade;

import javax.swing.*;
import java.awt.*;

public class EditAppointmentView extends JFrame {

    Admin admin;
    ServiceFacade service;
    Appointment appointment;

    private JPanel creatorP= new JPanel( new BorderLayout());
    private JPanel downP = new JPanel();
    private JTextField nameTextFild;
    private JTextField hospitalTextFild;
    private JTextField dayTextFild;
    private JTextField hourTextFild;
    private JTextField idAppointmentTextField;
    private JLabel nameLabel = new JLabel("Nombre: ");
    private JLabel idapointmentLabel = new JLabel("Id de la cita: ");
    private  JLabel hospitalLabel = new JLabel("Hospital: ");
    private JLabel dayLabel = new JLabel("Fecha (dd/mm/aa): ");
    private JLabel hourLabel = new JLabel("Hora (HH:MM):");
    private JButton saveButton = new JButton("Guardar");
    private JButton deleteButton = new JButton("Eliminar");


    /**
     *
     * @param ap
     * @param m
     * @param ser
     * @throws ClassNotFoundException
     * @throws UnsupportedLookAndFeelException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */

    public EditAppointmentView(Appointment ap, Admin m, ServiceFacade ser) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        super("Editor de Citas");
        add(creatorP);
        admin = m;
        service = ser;
        appointment = ap;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLookAndFeel();
        creatingAppointment();

        EditAppointmentController controller = new EditAppointmentController(this, service, admin);
        addActionListener(controller);


        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void createText(){
        nameTextFild = new JTextField(appointment.getPatient().getName());
        hospitalTextFild = new JTextField(appointment.getHospital().getName());
        idAppointmentTextField = new JTextField(appointment.getId());
        hourTextFild = new JTextField(appointment.getHour());
        dayTextFild = new JTextField(appointment.getDate());


    }
    private void creatingAppointment(){
        createText();
        JPanel allpanel = new JPanel(new GridLayout(5, 2));
        allpanel.add(nameLabel);
        allpanel.add(nameTextFild);
        allpanel.add(idapointmentLabel);
        allpanel.add(idAppointmentTextField);
        allpanel.add(hospitalLabel);
        allpanel.add(hospitalTextFild);
        allpanel.add(hourLabel);
        allpanel.add(hourTextFild);
        allpanel.add(dayLabel);
        allpanel.add(dayTextFild);

        ////
        JPanel top = new JPanel();
        top.add(new JLabel("Modificador"));
        creatorP.add(top, BorderLayout.PAGE_START);
        creatorP.add(allpanel, BorderLayout.CENTER);
        downP.add(saveButton);
        downP.add(deleteButton);
        creatorP.add(downP, BorderLayout.PAGE_END);

    }

    public String getNameTextFild() { return nameTextFild.getText(); }
    public String getIdAppointmentTextField() {return idAppointmentTextField.getText();}
    public String getHospitalTextFild() { return hospitalTextFild.getText();}
    public String getHourTextFild(){return hourTextFild.getText();}
    public String getDayTextFild(){ return dayTextFild.getText();}
    public JButton getSaveButton() { return saveButton; }
    public JButton getDeleteButton() { return deleteButton; }
    public Appointment getAppointment() { return appointment;}

    public void addActionListener(EditAppointmentController c){
        saveButton.addActionListener(c);
        deleteButton.addActionListener(c);
    }


    private static void setLookAndFeel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
        );
    }

    public void showMessage(String e) {
        JOptionPane.showMessageDialog(this, e);
    }

}
