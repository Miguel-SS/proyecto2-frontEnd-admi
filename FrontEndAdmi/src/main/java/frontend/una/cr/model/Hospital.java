package frontend.una.cr.model;

import java.time.LocalTime;

public class Hospital {

    // Attributes
    private int id;
    private String name;
    private String phone;
    private String day1;
    private String day2;
    private LocalTime timeAtention;


    /**
     * @param id
     * @param name
     * @param phone
     * @param day1
     * @param day2
     * @param hour
     * @param min
     */
    public Hospital(int id, String name, String phone, String day1, String day2, int hour, int min){
        this.name = name;
        this.phone = phone;
        this.timeAtention = LocalTime.of(hour, min);
        this.day1 = day1;
        this.day2 = day2;
    }


    public int getHour(){
        return timeAtention.getHour();
    }

    public int getMinutes(){
        return timeAtention.getMinute();
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getNameH() {
        return name;
    }

    public void setNameH(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getTimeAtention() {
        return timeAtention;
    }

    public void setTimeAtention(LocalTime timeAtention) {
        this.timeAtention = timeAtention;
    }

    /*
    public String getTime(){
        int hour = timeAtention.getHour();
        int min = timeAtention.getMinute();
        return Integer.toString(hour)+":"+Integer.toString(min);
    }
     */
}
