package frontend.una.cr.controller;

import frontend.una.cr.model.Admin;
import frontend.una.cr.model.Appointment;
import frontend.una.cr.model.Hospital;
import frontend.una.cr.service.ServiceFacade;
import frontend.una.cr.view.AdminView;
import frontend.una.cr.view.EditAppointmentView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

public class EditAppointmentController implements ActionListener {

    ServiceFacade service;
    Admin admin;
    EditAppointmentView view;
    Appointment appointment;


    /**
     *
     * @param v
     * @param ser
     * @param ad
     */
    public EditAppointmentController(EditAppointmentView v, ServiceFacade ser, Admin ad){
        service = ser;
        admin = ad;
        view = v;
        appointment = view.getAppointment();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object ev = e.getSource();

        if (ev == view.getSaveButton()) {
            try {
                save();
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException |
                    InstantiationException | IOException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        if (ev == view.getDeleteButton()) {
            try {
                delete();
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException |
                    InstantiationException | IOException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
            view.dispose();
            try {
                AdminView v = new AdminView(admin, service);
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException |
                    InstantiationException | IllegalAccessException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    private void save() throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IOException, IllegalAccessException {
        String name = view.getNameTextFild();
        String hospName = view.getHospitalTextFild();
        int idApp = Integer.parseInt(view.getIdAppointmentTextField());
        Date day = view.getDayTextFild();
        String hour = view.getHourTextFild();

        // Check if the txt fields are empty
        if (!"".equals(name) &&
                !"".equals(hospName) &&
                !"".equals(idApp) &&
                !"".equals(day) &&
                !"".equals(hour)) {
            Hospital hospital = service.getHospital(hospName);

            if(hospital != null){
                appointment.setHospital(hospital);
                if(service.searchAppointment(idApp)== null) {
                    appointment.setId(idApp);
                    appointment.setPatient(name);
                    appointment.setDate(day);
                } else { view.showMessage("Id repetido"); }
            } else { view.showMessage("No se encontro Consultorios con ese Nombre"); }

            view.dispose();
            AdminView ad = new AdminView(admin, service);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Uno o varios cuadros de texto estan vacios", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void delete() throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IOException, IllegalAccessException {

        service.delete(appointment);
        view.dispose();
        AdminView ad = new AdminView(admin, service);
    }
}
