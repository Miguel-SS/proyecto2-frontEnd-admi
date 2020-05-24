package cr.una.frontend.controller;

import cr.una.frontend.utillities.Constants;
import cr.una.frontend.view.*;
import cr.una.frontend.model.Appointment;
import cr.una.frontend.model.Hospital;
import cr.una.frontend.model.Patient;
import cr.una.frontend.service.ServiceFacade;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

public class AdminController implements ActionListener {

    private ServiceFacade service;
    private AdminView view;
    private Patient patient;
    private Appointment appointment;
    private Object[][] appointments;
    private Object[][] patients;
    private String nameH1; // hospital 1
    private String nameH2; // hospital 2

    //view attributes
    DefaultTableModel tableModelAppointments;
    DefaultTableModel tableModelPatients;


    /**
     * @param admin
     * @param servis
     * @throws IOException
     */
    public AdminController(AdminView admin, ServiceFacade servis) throws IOException {
        nameH1 = "Hospital CYM";
        nameH2 = "Centro Medico del Este";
        service = servis;
        view = admin;
        patient = null;
        appointment = null;

        patients = service.loadPatientsObjWrapper();
        appointments = service.loadAppointmentsObjWrapper();
        tableModelAppointments = view.getTableModelAppointment();
        tableModelPatients = view.getPatientTableModel();

        tableModelPatients.setDataVector(patients, Constants.PATIENTS_HEADER);
        tableModelAppointments.setDataVector(appointments, Constants.APPOINTMENT_HEADER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object ev = e.getSource();
        if (ev == view.getLogOutBtn()) {
            view.dispose();
            try {
                LoginView log = new LoginView(service);
            } catch (IOException ex) { ex.printStackTrace(); }
        }
        ///////////////////// appointment///////////////////////////////
        if (ev == view.getFilterButtonAppointment()) {
            updateAppointmentsSearchTerms(view.getSearchNameTextFieldAppointment());
        }
        if (ev == view.getChoseAppointmentBt()) {
            choseAnAppointment();
        }
        if (ev == view.getEditAppointmentButton()) {
            view.dispose();
            try {
                editAppointment();
            } catch (ClassNotFoundException | IllegalAccessException |
                    InstantiationException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        }

//////////////////////////////////////////////////patient//////////////////////////////////////////////////////////
        if (ev == view.getFilterPatientButton()) {
            updatePatientsSearchTerms(view.getSearchNameTextField());
        }

        if (ev == view.getEditPatientButton()) {
            try {
                editPatient();
            } catch (ClassNotFoundException | IllegalAccessException |
                    InstantiationException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        }

        if (ev == view.getChosePatientBt()) {
            chosenAPatient();
        }

//////////////////////////////////////hospital//////////////////////////////////////////////////////////////////////
        if (ev == view.getHospitalOption1Btn()) {
            try {
                saveHospital1();
            } catch (ClassNotFoundException | IllegalAccessException |
                    InstantiationException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }
        }

        if (ev == view.getHospitalOption2Btn()) {
            try {
                saveHospital2();
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException |
                    InstantiationException | IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        if (ev == view.getSaveButton()) {
            try {
                saveAppointment();
            } catch (NumberFormatException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void saveAppointment() throws NullPointerException,
            NumberFormatException{
        String idPatient = view.getIdTextFild();
        String id = view.getIdAppointmentTextField();
        String hospitalTxt = view.getHospitalTextFild();
        String hour = view.getHourTextFild();
        Date day = view.getDayTextFild();

        if (!"".equals(idPatient) && // checking id patient
                !"".equals(id) && // checking id
                !"".equals(hospitalTxt) && // checking hospital
                !"".equals(day) && // checking day
                !"".equals(hour)
        )  {
            Hospital hospital = service.getHospital(hospitalTxt);
            Patient patient = (Patient) service.searchUser(Integer.parseInt(id));
            if (hospital != null && patient != null) {
                if (service.searchUser(Integer.parseInt(id)) == null) {
                    service.add(new Appointment(Integer.parseInt(id), patient, hospital, day, Integer.parseInt(hour)));
                    appointments = service.loadAppointmentsObjWrapper();
                    tableModelAppointments.setDataVector(appointments, Constants.APPOINTMENT_HEADER);
                    view.setNameTextFild();
                    view.setIdAppointmentTextField();
                    view.setHospitalTextFild();
                    view.setHourTextFild();
                    view.setDayTextFild();

                    JOptionPane.showMessageDialog(null,
                            "Guardado con exito", "Exito",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Id repetido", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null,
                        "Consultorio no encontrado", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Uno o varios cuadros de texto estan vacios", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveHospital1() throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IllegalAccessException {

        if(service.getHospital(nameH1)!=null) {
            view.dispose();
            new HospitalView(service.getHospital(nameH1),service, view.getAdmin());
        } else { view.showMessage("No hay consultorios"); }

    }

    private void saveHospital2() throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IllegalAccessException {

        if (service.getHospital(nameH2) != null) {
            view.dispose();
            new HospitalView(service.getHospital(nameH2), service, view.getAdmin());
        } else { view.showMessage("No hay consultorios"); }

    }

    private void updateAppointmentsSearchTerms(String searchTerm) {
        if (searchTerm != null && !"".equals(searchTerm) &&
                appointments != null && appointments.length > 1) {
            Object[][] newData = new Object[appointments.length][];
            int idx = 0;
            for (Object[] obj: appointments) {
                String fullText = obj[0].toString() + obj[1].toString()
                        + obj[2].toString() + obj[3].toString() + obj[4].toString();

                if (fullText.contains(searchTerm.trim())) {
                    newData[idx++] = obj;
                }
            }
            tableModelAppointments.setDataVector(newData, Constants.APPOINTMENT_HEADER);
        } else {
            tableModelAppointments.setDataVector(appointments, Constants.APPOINTMENT_HEADER);
        }
    }

    private void updatePatientsSearchTerms(String searchTerm) {
        if (searchTerm != null && !"".equals(searchTerm) &&
                patients != null && patients.length > 1) {
            Object[][] newData = new Object[patients.length][];
            int idx = 0;
            for (Object[] obj: patients) {
                String fullText = obj[0].toString() + obj[1].toString()
                        + obj[2].toString() + obj[3].toString();

                if (fullText.contains(searchTerm.trim())) {
                    newData[idx++] = obj;
                }
            }
            tableModelPatients.setDataVector(newData, Constants.PATIENTS_HEADER);
        } else {
            tableModelPatients.setDataVector(patients, Constants.PATIENTS_HEADER);
        }
    }

    private void choseAnAppointment(){
        int id = Integer.parseInt(view.getChosenAppointment());
        appointment  = service.searchAppointment(id);

        if(appointment!=null) {
            view.getChosenAppointmentLabel().setText(
                    view.getChoseAppointmentBt().getText() + " " + appointment.getPatient()
            );
        } else { view.showMessage("No se encontro una cita con ese Id"); }
    }

    private void editPatient() throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IllegalAccessException {
        if(patient != null) {
            view.dispose();
            new EditPatientView(patient, service, view.getAdmin());
        } else { view.showMessage("No se eligio Paciente"); }
    }

    private void chosenAPatient(){
        int id = Integer.parseInt(view.getChosenPatientText());
        patient = (Patient) service.searchUser(id);
        if(patient != null) {
            view.getChosenPatientLabel().setText(view.getChosenPatientText()+" "+patient.getName());
        } else { view.showMessage("No se ha encontrado Paciente con ese id"); }
    }

    private void editAppointment() throws ClassNotFoundException, UnsupportedLookAndFeelException,
            InstantiationException, IllegalAccessException {

        if(appointment != null) {
            new EditAppointmentView(appointment, view.getAdmin(), service);
        } else { view.showMessage("Cita no elegida"); }
    }
}
