package cr.una.frontend.service;

import cr.una.frontend.model.Patient;
import cr.una.frontend.utillities.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PatientService {


    // Using logger for project
    private static final Logger logger = LogManager.getLogger(PatientService.class);

    private static final String REST_URI = Constants.WS_ENDPOINT.concat("patients");
    private Client client = null;

    /**
     * Empty Constructor
     */
    public PatientService() {
        client = ClientBuilder.newClient();
    }

    /**
     * This method will load the information from JSON depending if the filter text
     *
     * @param searchTerm filter term
     * @return the list of Students
     */
    public List<Patient> searchPatientByTerm(String searchTerm) {

        logger.debug("Obteniendo la lista de pacientes que coinciden con ["+searchTerm+"]");

        List<Patient> patientList = loadAllPatients();
        List<Patient> updatedPatientList = new ArrayList<Patient>();

        if (patientList != null && patientList.size() > 0) {
            for (Patient patient : patientList) {
                if (searchTerm != null && patient.getName().equals(searchTerm)) {
                    updatedPatientList.add(patient);
                }
            }
        }

        return updatedPatientList;
    }

    /**
     * This method will load all the data from the WS
     *
     * @return the list of Students
     */
    public List<Patient> loadAllPatients() {

        logger.debug("Obteniendo toda la lista de pacientes");

        // Library Jackson parse JSon
        List<Patient> patientList = null;

        patientList = Arrays.asList(client.target(REST_URI).request(MediaType.APPLICATION_JSON)
                .get(Patient[].class));

        return patientList;
    }

    public Patient savePatient(Patient patient) {
        Patient patientSaved;

        patientSaved = client.target(REST_URI).request(MediaType.APPLICATION_JSON).post(Entity.entity(patient,
                MediaType.APPLICATION_JSON), Patient.class);

        return patientSaved;
    }

}
