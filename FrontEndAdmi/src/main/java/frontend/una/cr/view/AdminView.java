package frontend.una.cr.view;

import frontend.una.cr.controller.AdminController;
import frontend.una.cr.model.Admin;
import frontend.una.cr.service.ServiceFacade;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class AdminView extends JFrame{
    // admin
    private Admin admin;
    private ServiceFacade service;

    // for attributes
    private JPanel rootPanel = new JPanel(new BorderLayout());


    // top panel personal info-------------
    private JPanel personalInfoPanel = new JPanel();
    private JLabel idPanelInfo;
    private JLabel namePanelInfoLbl;
    private JButton logOutBtn = new JButton("Cerrar Sesion");

    private JTabbedPane optionsTabPanel = new JTabbedPane(SwingConstants.LEFT);


    // patient tab.........................................................

    private JPanel patientPanel = new JPanel(new BorderLayout());
    private JPanel topPatientPanel = new JPanel();
    private JPanel centerPatientPanel = new JPanel(new BorderLayout());
    private JPanel downPatientPanel = new JPanel();


    private JTextField searchNameTextField = new JTextField(26);
    private JButton filterPatientButton = new JButton("Buscar");


    private JTable patientTable = new JTable();
    private DefaultTableModel tableModel = new DefaultTableModel();
    private JScrollPane tableScrollPane;
    private JTextField chosenPatientText = new JTextField("id", 26);
    private JButton chosePatientBt = new JButton("Elegir");


    JLabel chosenPatientLabel = new JLabel("Paciente Elegido: ");
    JButton editPatientButton = new JButton("Editar");


    // appointment tab ......................................................
    private JPanel appointmentPanel = new JPanel(new BorderLayout());


    private JPanel topAppointmentPanel = new JPanel();
    private JPanel centerAppointmentPanel = new JPanel(new BorderLayout());
    private JPanel downAppointmentPanel = new JPanel();


    private JTextField searchNameTextFieldAppointment = new JTextField(26);
    private JButton filterButtonAppointment = new JButton("Buscar");
    private JButton choseAppointmentBt = new JButton("Elegir");
    private JTextField choseAppointment = new JTextField("Id....", 26);


    private JTable AppointmentTable = new JTable();
    private DefaultTableModel tableModelAppointment = new DefaultTableModel();
    private JScrollPane tableScrollPaneAppointment;


    JLabel chosenAppointmentLabel = new JLabel("Cita Elegida: ");
    JButton editAppointmentButton = new JButton("Editar");


    // hospital tab..........................................................


    private JPanel hospitalPanel = new JPanel(new GridLayout(4, -1));
    private JPanel hospitalEmptyPanel1 = new JPanel();
    private JPanel hospitalEmptyPanel2 = new JPanel();
    private JPanel topHospitalPanel = new JPanel();
    private JPanel downHospitalPanel = new JPanel();
    private JButton hospitalOption1Btn = new JButton("    Hospital CYM    ");
    private JButton hospitalOption2Btn = new JButton("Centro Medico del Este");



    // creation .................................................................
    private JPanel creatorPanel = new JPanel( new BorderLayout());
    private JTextField idTextFild = new JTextField();
    private JTextField hospitalTextFild = new JTextField();
    private JFormattedTextField dayTextFild = new  JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));;
    private JTextField hourTextFild = new JTextField();
    private JTextField idAppointmentTextField = new JTextField();
    private JLabel idLabel = new JLabel("Nombre: ");
    private JLabel idapointmentLabel = new JLabel("Id de la cita: ");
    private  JLabel hospitalLabel = new JLabel("Hospital: ");
    private JLabel dayLabel = new JLabel("Fecha (dd/mm/aa): ");
    private JLabel hourLabel = new JLabel("Hora (HH:MM):");
    private JButton saveButton = new JButton("Guardar");


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * @param admodel
     * @param servis
     * @throws ClassNotFoundException
     * @throws UnsupportedLookAndFeelException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IOException
     */

    public AdminView(Admin admodel, ServiceFacade servis) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException, IOException {
        super("Admin");
        add(rootPanel);
        admin = admodel;
        service = servis;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 450);
        rootPanel.add(optionsTabPanel);
        setLocationRelativeTo(null);
        setVisible(true);
        setLookAndFeel();
        //panel info
        creatingTopPanelInfo();
        //tab panel
        creatingAppointmentTabPane();
        creatingCreatorAppointment();
        creatingPatientTabPane();
        creatingHospitalTabPane();


        //creating Controller
        AdminController controller = new AdminController(this, service);
        addListener(controller);
    }

    ////////////////////////// Info Panel////////////////////////////////////////////////////////////////////

    private void creatingTopPanelInfo() {
        idPanelInfo = new JLabel("ID: "+ admin.getId());
        idPanelInfo.setName("idPanelInfo");
        namePanelInfoLbl = new JLabel("Nombre: "+ admin.getName()+" "+admin.getLastName());
        personalInfoPanel.add(idPanelInfo);
        personalInfoPanel.add(namePanelInfoLbl);
        personalInfoPanel.add(new JSeparator(SwingConstants.VERTICAL));
        logOutBtn.setName("logOutBtn");
        personalInfoPanel.add(logOutBtn);
        rootPanel.add(personalInfoPanel, BorderLayout.PAGE_START);
    }

    ///////////////////////////////////////////// Patient Panel///////////////////////////////////////////////////////////////////
    private void creatingPatientTabPane() {

        // top
        searchNameTextField.setText("Nombre del paciente");
        topPatientPanel.add(searchNameTextField);
        topPatientPanel.add(filterPatientButton);
        patientPanel.add(topPatientPanel, BorderLayout.PAGE_START);


        //center
        patientPanel.add(centerPatientPanel, BorderLayout.CENTER);
        patientTable.setName("Patient Table");
        patientTable.setModel(tableModel);
        tableScrollPane = new JScrollPane(patientTable);
        tableScrollPane.setName("scrollTablePanePatient");
        tableScrollPane.setPreferredSize(new Dimension(400, 250));
        tableScrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Pacientes",
                TitledBorder.CENTER, TitledBorder.TOP));

        JPanel pan1 = new JPanel();
        pan1.add(tableScrollPane);
        centerPatientPanel.add(pan1, BorderLayout.PAGE_START);
        JPanel pan2 = new JPanel();
        pan2.add(chosenPatientText);
        pan2.add(chosePatientBt);
        centerPatientPanel.add(pan2, BorderLayout.PAGE_END);

        //down
        downPatientPanel.add(chosenPatientLabel);
        downPatientPanel.add(editPatientButton);
        patientPanel.add(downPatientPanel, BorderLayout.PAGE_END);

        /////////
        optionsTabPanel.addTab("Pacientes", patientPanel);
    }

    //////////////////////////////////////////// Appointment panel////////////////////////////////////////////////////////////

    private void creatingAppointmentTabPane() {
        // top
        searchNameTextFieldAppointment.setText("Nombre del paciente");
        topAppointmentPanel.add(searchNameTextFieldAppointment);
        topAppointmentPanel.add(filterButtonAppointment);
        appointmentPanel.add(topAppointmentPanel, BorderLayout.PAGE_START);

        //center
        appointmentPanel.add(centerAppointmentPanel, BorderLayout.CENTER);
        AppointmentTable.setName("Appointment Table");
        AppointmentTable.setModel(tableModelAppointment);
        tableScrollPaneAppointment = new JScrollPane(AppointmentTable);
        tableScrollPaneAppointment.setName("scrollTablePaneAppointment");
        tableScrollPaneAppointment.setPreferredSize(new Dimension(400, 250));
        tableScrollPaneAppointment.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Citas",
                TitledBorder.CENTER, TitledBorder.TOP));
        JPanel pan1 = new JPanel();
        pan1.add(tableScrollPaneAppointment);
        centerAppointmentPanel.add(pan1, BorderLayout.PAGE_START);
        JPanel pan2 = new JPanel();
        pan2.add(choseAppointment);
        pan2.add(choseAppointmentBt);
        centerAppointmentPanel.add(pan2, BorderLayout.PAGE_END);

        //down
        downAppointmentPanel.add(chosenAppointmentLabel);
        downAppointmentPanel.add(editAppointmentButton);
        appointmentPanel.add(downAppointmentPanel, BorderLayout.PAGE_END);

        optionsTabPanel.addTab("Citas", appointmentPanel);
    }


    ///////////////////////////    hospital panel //////////////////////////////////////////////////////////////////
    private void creatingHospitalTabPane() {
        hospitalPanel.add(hospitalEmptyPanel1);
        hospitalPanel.add(topHospitalPanel);
        topHospitalPanel.add(hospitalOption1Btn);
        downHospitalPanel.add(hospitalOption2Btn);
        hospitalPanel.add(downHospitalPanel);
        hospitalPanel.add(hospitalEmptyPanel2);
        optionsTabPanel.addTab("Consultorios", hospitalPanel);
    }

    //////////////////////////// create /////////////////////////////////////////////////////////////////////////////////
    private void creatingCreatorAppointment(){

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
        top.add(new JLabel("CREADOR DE CITAS"));
        creatorPanel.add(top, BorderLayout.PAGE_START);
        creatorPanel.add(allpanel, BorderLayout.CENTER);
        creatorPanel.add(downPatientPanel.add(saveButton), BorderLayout.PAGE_END);
        optionsTabPanel.addTab("Crear Cita", creatorPanel);
    }


    //////////////////////////////////////////////// others ///////////////////////////////////////////////////////////////////////////

    public void addListener(AdminController c) {

        //info panel
        logOutBtn.addActionListener(c);

        //patient panel
        filterPatientButton.addActionListener(c);
        editPatientButton.addActionListener(c);
        chosePatientBt.addActionListener(c);

        //appointment  Panel
        filterButtonAppointment.addActionListener(c);
        editAppointmentButton.addActionListener(c);
        choseAppointmentBt.addActionListener(c);

        // hotel panel

        hospitalOption1Btn.addActionListener(c);
        hospitalOption2Btn.addActionListener(c);

        // creator
        saveButton.addActionListener(c);
    }
/////////////////////////////////////////////////////////// getters /////////////////////////////////////////////////////////////////

    public DefaultTableModel getTableModelAppointment() { return tableModelAppointment; }
    public DefaultTableModel getPatientTableModel() { return tableModel; }

    //buttons
    public JButton getLogOutBtn() {
        return logOutBtn;
    }
    public JButton getFilterPatientButton() {
        return filterPatientButton;
    }
    public JButton getFilterButtonAppointment() {
        return filterButtonAppointment;
    }
    public JButton getSaveButton() { return saveButton; }
    public JButton getHospitalOption1Btn(){return hospitalOption1Btn;}
    public JButton getHospitalOption2Btn(){return hospitalOption2Btn;}
    public JButton getChoseAppointmentBt(){return choseAppointmentBt;}
    public JButton getEditPatientButton(){return editPatientButton;}
    public JButton getEditAppointmentButton(){return editAppointmentButton;}
    public JButton getChosePatientBt(){return chosePatientBt;}


    // text Fields
    public String getIdTextFild() { return idTextFild.getText(); }
    public String getIdAppointmentTextField() {return idAppointmentTextField.getText();}
    public String getHospitalTextFild() { return hospitalTextFild.getText();}
    public String getHourTextFild(){return hourTextFild.getText();}
    public Date getDayTextFild(){ return (Date)dayTextFild.getValue();}
    public String getSearchNameTextFieldAppointment() { return searchNameTextFieldAppointment.getText(); }
    public String getSearchNameTextField() { return searchNameTextField.getText(); }
    public String getChosenAppointment(){return choseAppointment.getText();}
    public String getChosenPatientText(){return chosenPatientText.getText();}


    public void setNameTextFild() {  idTextFild.setText(""); }
    public void setIdAppointmentTextField() { idAppointmentTextField.setText("");}
    public void setHospitalTextFild() {  hospitalTextFild.setText("");}
    public void setHourTextFild(){ hourTextFild.setText("");}
    public void setDayTextFild(){  dayTextFild.setText("");}

    // Others
    public Admin getAdmin(){return admin;}
    public JLabel getChosenAppointmentLabel(){return chosenAppointmentLabel;}
    public JLabel getChosenPatientLabel(){return  chosenPatientLabel;}



    /////////////////////////////////////////////////////////////////////////////////////

    public void showMessage(String e) {
        JOptionPane.showMessageDialog(this, e);
    }



    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static void setLookAndFeel() throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(
                "com.sun.java.swing.plaf.windows.WindowsLookAndFeel"
        );
    }
}
