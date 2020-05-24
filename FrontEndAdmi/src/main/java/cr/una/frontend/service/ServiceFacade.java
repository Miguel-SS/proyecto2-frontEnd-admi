package cr.una.frontend.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import cr.una.frontend.utillities.Constants;
import cr.una.frontend.model.Admin;
import cr.una.frontend.model.Appointment;
import cr.una.frontend.model.Hospital;
import cr.una.frontend.model.Patient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceFacade {
    // List
    private List<Patient> patients;
    private List<Admin> admins;
    private List<Appointment> appointments;
    private List<Hospital> hospitals;

    // Hospitals
    private Hospital CYMHospital;
    private Hospital esteHospital;

    public ServiceFacade() throws JsonGenerationException,
            JsonMappingException, IOException {
        patients = new ArrayList<Patient>();
        admins = new ArrayList<Admin>();
        appointments = new ArrayList<Appointment>();
        hospitals = new ArrayList<Hospital>();

        createHospitals();
        createAdmins();
        createPatients();
    }

    public void add(Object o) {
        if(o.getClass() == Patient.class) {
            patients.add((Patient) o);
        }
        if(o.getClass() == Admin.class) {
            admins.add((Admin) o);
        }
        if(o.getClass() == Appointment.class) {
            appointments.add((Appointment) o);
        }
        if(o.getClass() == Hospital.class) {
            hospitals.add((Hospital) o);
        }
    }

    public void delete(Object o) {
        if(o.getClass() == Patient.class) {
            patients.remove(o);
        }
        if(o.getClass() == Admin.class) {
            admins.remove(o);
        }
        if(o.getClass() == Appointment.class) {
            appointments.remove(o);
        }
        if(o.getClass() == Hospital.class) {
            hospitals.remove(o);
        }
    }

    /**
     * @param id
     * @return
     */
    public Object searchUser(int id) {
        if (admins.size() > 0) {
            for (Admin admin : admins) {
                if (id == admin.getId()) {
                    return admin;
                }
            }
        }
        if (patients.size() > 0) {
            for (Patient patient : patients) {
                if (id == patient.getId()) {
                    return patient;
                }
            }
        }
        return null;
    }

    /**
     * @param id
     * @return
     */
    public Appointment searchAppointment(int id) {
        if (appointments.size() > 0) {
            for (Appointment appointment : appointments) {
                if (appointment.getId() == id) {
                    return appointment;
                }
            }
        }
        return  null;
    }

    // Load the patients in a matrix for a table
    public Object[][] loadPatientsObjWrapper() {
        Object[][] data = null;

        if(patients != null && patients.size() > 0) {
            data = new Object[patients.size()][Constants.PATIENTS_HEADER.length];
            int i = 0;
            for(Patient patient : patients) {
                data[i][0] = checkIfNull(patient.getId());
                data[i][1] = checkIfNull(patient.getName());
                data[i][2] = checkIfNull(patient.getLastName());
                data[i][3] = checkIfNull(patient.getDisease());
                i++;
            }
        }
        return data;
    }

    // Load the appointments in a matrix for a table
    public Object[][] loadAppointmentsObjWrapper() {
        Object[][] data = null;

        if(appointments != null && appointments.size() > 0) {
            data = new Object[appointments.size()][Constants.APPOINTMENT_HEADER.length];
            int i = 0;
            for(Appointment appointment : appointments) {
                data[i][0] = checkIfNull(appointment.getId());
                data[i][1] = checkIfNull(appointment.getPatient().getName());
                data[i][4] = checkIfNull(appointment.getHospital().getName());
                i++;
            }
        }
        return data;
    }

    /**
     * @param id
     * @return
     */
    public Object[][] loadAppointmentsFromPatientObjWrapper(int id) {
        Patient auxPatient = null;
        for (Patient patient : patients) {
            if (id == patient.getId()) {
                auxPatient = patient;
                break;
            }
        }

        Object[][] data = null;

        List<Appointment> patientAppointments = auxPatient.getAppointments();

        if(patientAppointments != null && patientAppointments.size() > 0) {
            data = new Object[patientAppointments.size()][Constants.PATIENT_APPOINTMENTS_HEADER.length];
            int i = 0;
            for(Appointment appointment : patientAppointments) {
                data[i][0] = checkIfNull(appointment.getId());
                data[i][3] = checkIfNull(appointment.getHospital());
                i++;
            }
        }
        return data;
    }

    /**
     * @param obj
     * @return
     */
    private String checkIfNull(Object obj) {
        String text;
        if(obj == null) {
            text = "";
        } else {
            text = obj.toString();
        }
        return text;
    }

    private void createAdmins() {
        // Creating the default admins (could be more)
        // Admins
        Admin docAdmin = new Admin(12345678, "Juan",
                "Perez","87415003", "karaoke88"
        );
        Admin secretariatAdmin = new Admin(98765432, "Karol",
                "Gomez", "88889352", "morenito92"
        );

        // Adding the admins to the list
        admins.add(docAdmin);
        admins.add(secretariatAdmin);
    }

    private void createHospitals() {
        // Creating the default hospitals
        CYMHospital = new Hospital(16497325, "Hospital CYM", "22498812",
                "Lunes", "Martes", 7, 0
        );
        esteHospital = new Hospital(23465792,"Centro Medico del Este", "22634179",
                "Jueves", "Viernes", 7,0
        );

        // Adding the hospitals to the list
        hospitals.add(CYMHospital);
        hospitals.add(esteHospital);
    }

    public Hospital getHospital(String name) {
        if (hospitals.size() > 0) {
            for (Hospital hospital : hospitals) {
                if (hospital.getName().equals(name)) {
                    return hospital;
                }
            }
        }
        return null;
    }

    private void createPatients() {
        Patient miguelPatient = new Patient(402360294, "Miguel", "Sanchez",
                1997, "88094192", "Heredia", "1234"
        );
        Patient alePatient = new Patient(117050590, "Alejandro", "Rodriguez",
                1998, "87415630", "Tibas", "9876"
        );

        Appointment appointment1 = new Appointment(1, miguelPatient, esteHospital, new Date(20,5,8), 7);
        Appointment appointment2 = new Appointment(2, miguelPatient, CYMHospital, new Date(20,03,30), 7);
        Appointment appointment3 = new Appointment(3, alePatient, esteHospital, new Date(20,11,24), 7);
        Appointment appointment4 = new Appointment(4, alePatient, CYMHospital, new Date(20,8,21), 7);

        miguelPatient.add(appointment1);
        miguelPatient.add(appointment2);
        alePatient.add(appointment3);
        alePatient.add(appointment4);

        appointments.add(appointment1);
        appointments.add(appointment2);
        appointments.add(appointment3);
        appointments.add(appointment4);

        patients.add(miguelPatient);
        patients.add(alePatient);
    }
}
