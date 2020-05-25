package cr.una.frontend.controller;

import cr.una.frontend.model.Admin;
import cr.una.frontend.model.Patient;
import cr.una.frontend.service.ServiceFacade;
import cr.una.frontend.view.AdminView;
import cr.una.frontend.view.EditPatientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class EditPatientController implements ActionListener {

    private ServiceFacade services;
    private Admin admin;
    private Patient patient;
    private EditPatientView view;


    public EditPatientController(EditPatientView v, ServiceFacade ser, Admin ad, Patient pat) {
        services = ser;
        admin = ad;
        patient = pat;
        view = v;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object ev = e.getSource();

        if (ev == view.getSaveBtn()) {
            try {
                save();
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException |
                    IOException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        if (ev == view.getDeleteBtn()) {
            try {
                delete();
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException |
                    IOException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void save() throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IOException, IllegalAccessException {

        // Obtaining text from txt field...
        String name = view.getFirstNameText().getText();
        String lastName = view.getLastNameText().getText();
        // String id = view.getIdText().getText();
        String birthYear = view.getAgeText().getText();
        String phone = view.getPhoneText().getText();
        String disease = view.getDiseaseText().getText();
        String observation = view.getObservationText().getText();

        // Check if the txt fields are empty
        if (!"".equals(name) && // checking name
                !"".equals(lastName) && // checking last name
                // !"".equals(id) && // checking id
                !"".equals(birthYear) && // checking birth year
                !"".equals(phone) && // checking phone
                !"".equals(disease) &&
                !"".equals(observation)// checking address
        ) {
            patient.setName(name);
            patient.setLastName(lastName);
            patient.setBirthYear(Integer.parseInt(birthYear));
            patient.setPhone(phone);
            patient.setDisease(disease);
            patient.setObservation(observation);
            services.update(patient);
            view.dispose();
            AdminView ad = new AdminView(admin, services);

            //view.updateTopLabels();
        } else {
            JOptionPane.showMessageDialog(null,
                    "Uno o varios cuadros de texto estan vacios", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void delete() throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IOException, IllegalAccessException {
        //PatientModel pa = services.searchUser(patient.getId());
        services.delete(patient);
        view.dispose();
        AdminView ad = new AdminView(admin, services);
    }
}
