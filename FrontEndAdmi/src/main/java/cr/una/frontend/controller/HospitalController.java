package cr.una.frontend.controller;

import cr.una.frontend.view.HospitalView;
import cr.una.frontend.model.Admin;
import cr.una.frontend.model.Hospital;
import cr.una.frontend.service.ServiceFacade;
import cr.una.frontend.view.AdminView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class HospitalController implements ActionListener {

    private HospitalView view;
    private Hospital hospital;
    private ServiceFacade service;
    private Admin admin;


    /**
     *
     * @param v
     * @param m
     * @param ser
     * @param ad
     */
    public HospitalController(HospitalView v, Hospital m, ServiceFacade ser, Admin ad){
        view = v;
        hospital = m;
        service = ser;
        admin = ad;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = view.getNameTextField();
        String day1 = view.getDay1TextField();
        String day2 =view.getDay2TextFild();
        String phone = view.getPhoneText();
        String hour = view.hourText();

        // Check if the txt fields are empty
        if(!"".equals(name) && // checking name
                !"".equals(day1) && // checking last name
                !"".equals(day2) && // checkin
                !"".equals(phone)&&
                !"".equals(hour)
        ) {
            hospital.setName(name);
            hospital.setDay1(day1);
            hospital.setDay2(day2);
            hospital.setPhone(phone);
            // hospital.setTimeAtention();
            //view.updateTopLabels();
            view.dispose();
            try {
                AdminView adV = new AdminView(admin, service);
            } catch (ClassNotFoundException | UnsupportedLookAndFeelException | InstantiationException
                    | IllegalAccessException | IOException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Uno o varios cuadros de texto estan vacios", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }


    }
}
