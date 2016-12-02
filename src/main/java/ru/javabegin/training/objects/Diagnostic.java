package ru.javabegin.training.objects;

import java.sql.Date;


public class Diagnostic {
    private int id;
    private int doctor_id;
    private int sick_id;
    private String doctor;
    private String sick;
    private String diagnosis;
    private Date date;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setDoctorId(int doctor){
        this.doctor_id = doctor;
    }
    
    public int getDoctorId(){
        return doctor_id;
    }
    
    public void setSickId(int sick){
        this.sick_id = sick;
    }
    
    public int getSickId(){
        return sick_id;
    }
    
    public void setDoctor(String doctor){
        this.doctor = doctor;
    }
    
    public String getDoctor(){
        return doctor;
    }
    
    public void setSick(String sick){
        this.sick = sick;
    }
    
    public String getSick(){
        return sick;
    }
    
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }
    
    public String getDiagnosis(){
        return diagnosis;
    }
    
    public void setDate(Date date){
        this.date = date;
    }
    
    public Date getDate(){
        return date;
    }
    
    public Diagnostic(int id, int doctor_id, int sick_id, String doctor, String sick, String diagnosis, Date date){
        this.id = id;
        this.doctor = doctor;
        this.sick = sick;
        this.diagnosis = diagnosis;
        this.date = date;
        this.doctor_id = doctor_id;
        this.sick_id = sick_id;
    }
}
