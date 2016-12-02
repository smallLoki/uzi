package ru.javabegin.training.objects;

import java.sql.Date;


public class Sick {
    private int id;
    private String fio;
    private Date hb;
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void setFio(String fio){
        this.fio = fio;
    }
    
    public String getFio(){
        return fio;
    }
    
    public void setHb(Date hb){
        this.hb = hb;
    }
    
    public Date getHb(){
        return hb;
    }
    
    public Sick(){}
    
    public Sick(int id, String fio, Date hb){
        this.id = id;
        this.fio = fio;
        this.hb = hb;
    }
}
