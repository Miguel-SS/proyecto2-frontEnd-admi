package cr.una.frontend.view;

import cr.una.frontend.controller.EditAppointmentController;
import cr.una.frontend.model.Admin;
import cr.una.frontend.model.Appointment;
import cr.una.frontend.service.ServiceFacade;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

public class EditAppointmentView extends JFrame {

    Admin admin;
    ServiceFacade service;
    Appointment appointment;

    private JPanel creatorP= new JPanel( new BorderLayout());
    private JPanel downP = new JPanel();
    private JTextField idTextFild;
    private JTextField hospitalTextFild;
    private JFormattedTextField dayTextFild;
    private JTextField hourTextFild;
    private JTextField idAppointmentTextField;
    private JLabel idLabel = new JLabel("ID: ");
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
        idTextFild = new JTextField(appointment.getPatient().getName());
        hospitalTextFild = new JTextField(appointment.getHospital().getName());
        idAppointmentTextField = new JTextField(appointment.getId());
        hourTextFild = new JTextField(appointment.getHour());
        //dayTextFild = new JTextField(appointment.getDate());
        dayTextFild = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
        dayTextFild.setText(appointment.getDate().toString());


    }

    private void creatingAppointment(){
        createText();
        JPanel allpanel = new JPanel(new GridLayout(5, 2));
        allpanel.add(idLabel);
        allpanel.add(idTextFild);
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

    public String getIdTextFild() { return idTextFild.getText(); }
    public String getIdAppointmentTextField() {return idAppointmentTextField.getText();}
    public String getHospitalTextFild() { return hospitalTextFild.getText();}
    public String getHourTextFild(){return hourTextFild.getText();}
    public Date getDayTextFild(){ return (Date) dayTextFild.getValue();}
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
