package ru.javabegin.training.impls;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ru.javabegin.training.controllers.LoginController;
import ru.javabegin.training.interfaces.requestUser;
import ru.javabegin.training.objects.Diagnostic;
import ru.javabegin.training.objects.Doctor;
import ru.javabegin.training.objects.Sick;


public class SQLRequestDialog implements requestUser {

	@SuppressWarnings("unused")
	private static final Logger Logger = LoggerFactory.getLogger(LoginController.class);
	
    public String ret(String a) {
        return a;
    }
    
    public Connection connectDB(){
        Connection conn = null;
    	try {
       	 	String userName = "root";
            String password = "";
            String url = "jdbc:mysql://localhost/kabinet";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = DriverManager.getConnection(url, userName, password);
            
            //Logger.info("—оединение установлено!");
        } catch (Exception e) {
            Logger.info("Ќет соединени€!");
            e.printStackTrace();
        }
    	return conn;
    }

	@Override
    public Doctor loginDoctor(Doctor doc/*String login, String password*/) throws SQLException {
    	Connection conn = connectDB();

        Doctor doctor = new Doctor();
        Statement stmt;
        ResultSet rs;
        String query = "SELECT * FROM `doctors` "
        		+ "WHERE login='"+doc.getLogin()+"' AND password='"+doc.getPassword()+"'";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
            	int adm = Integer.valueOf(rs.getString("admin"));
            	boolean admin;

            	if (adm == 1){
            		admin = true;
            	}
            	else{
            		admin = false;
            	}
        		doctor.setId(rs.getInt("doc_id"));
        		doctor.setPassword(rs.getString("password"));
        		doctor.setAdmin(admin);
        		doctor.setFio(rs.getString("fio"));
        		break;
              
            }
        } catch (SQLException sqlEx) {
            Logger.info("Ќе получаетс€ соединитьс€ с базой данных!");
        } finally {
        	conn.close ();
        }       
        return doctor;
    }

	@Override
	public boolean updatePass(Doctor doc, String password) throws SQLException {
		Connection conn = connectDB();

        Statement stmt;
        String query = "UPDATE `doctors` "
        		+ "SET password = '"+password+"' "
        		+ "WHERE doc_id = '"+Integer.toString(doc.getId())+"' AND password='"+doc.getPassword()+"'";
        int i = 0; 
    	//Logger.info(query);
        try {
            stmt = conn.createStatement();
            i = stmt.executeUpdate(query);
            
        } catch (SQLException sqlEx) {
            Logger.info("Ќе получаетс€ соединитьс€ с базой данных!");
        } finally {
        	conn.close ();
        }       
        if (i>0) return true; 
        else
		return false;
	}

	@Override
	public ArrayList<Sick> getSick() throws SQLException {
		Connection conn = connectDB();

		ArrayList<Sick> sick = new ArrayList<Sick>();
        ResultSet rs;
        String query = "SELECT * FROM `sicks`";

        try {
        	Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {

            	Sick sk = new Sick(rs.getInt("sick_id"), 
            					   rs.getString("fio"), 
            					   rs.getDate("hb"));
            	sick.add(sk);     
            }
        } catch (SQLException sqlEx) {
        	sick = null;
            Logger.info("Ќе получаетс€ соединитьс€ с базой данных!");
        } finally {
        	conn.close ();
        }       
        return sick;
	}

	@Override
	public ArrayList<Diagnostic> getDiagn(int id) throws SQLException {
		ArrayList<Doctor> doctor = new ArrayList<Doctor>();
		doctor =  getDoctor();
		ArrayList<Sick> sick = new ArrayList<Sick>();
		Sick sick_ = new Sick();

		if (id == 0){
			sick = getSick();
		} else {
			sick_ = getSick(id);
		}
		
		Connection conn = connectDB();

		ArrayList<Diagnostic> diagn = new ArrayList<Diagnostic>();
        try {

            Statement stmt;
            ResultSet rs;
            String query;
            if (id == 0){			
            	query = "SELECT * FROM `diagnos`";
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery(query);

	            while (rs.next()) {
	            	
	                int indexDoc = -1;
	            	for(Doctor doc : doctor){
	               	 	indexDoc++; 
	                    if (doc.getId() == rs.getInt("doctor")){
	                    break;}
	                }
	                int indexSk = -1;
	            	for(Sick sk : sick){
	            		indexSk++; 
	                    if (sk.getId() == rs.getInt("sick")){
	                    break;}
	                }

	            	Diagnostic sk = new Diagnostic(rs.getInt("id"),
							   					   rs.getInt("doctor"), 
	            								   rs.getInt("sick"), 
	            								   doctor.get(indexDoc).getFio(), 
	            								   sick.get(indexSk).getFio(), 
	            								   rs.getString("diagnosis"), 
	            								   rs.getDate("date"));
	            	diagn.add(sk);
	            }

            } else {
            	query = "SELECT * FROM `diagnostiс` WHERE sick="+Integer.toString(id);
	            stmt = conn.createStatement();
	            rs = stmt.executeQuery(query);
	            while (rs.next()) {
	
	                int indexDoc = -1;
	            	for(Doctor doc : doctor){
	               	 	indexDoc++; 
	                    if (doc.getId() == rs.getInt("doctor")){
	                    break;}
	                }
	            	
	            	Diagnostic sk = new Diagnostic(rs.getInt("id"), 
							   					   rs.getInt("doctor"), 
												   rs.getInt("sick"), 
	            								   doctor.get(indexDoc).getFio(), 
	            								   sick_.getFio(), 
	            								   rs.getString("diagnosis"), 
	            								   rs.getDate("date"));
	            	diagn.add(sk);
	            }
            }
        } catch (SQLException sqlEx) {
        	diagn = null;
            Logger.info("Ќе получаетс€ соединитьс€ с базой данных!");
        } finally {
        	conn.close();
        }       
		return diagn;
	}

	@Override
	public boolean setSick(Sick sick) throws SQLException {
		Connection conn = connectDB();

        Statement stmt;
        String query = "INSERT INTO  `sicks` (fio, hb) "
        		+ "VALUES(Т"+sick.getFio()+"Т, '"+sick.getHb()+"Т)"; 
        int i = 0; 
    	//Logger.info(query);
        try {
            stmt = conn.createStatement();
            i = stmt.executeUpdate(query);
            
        } catch (SQLException sqlEx) {
            Logger.info("Ќе получаетс€ соединитьс€ с базой данных!");
        } finally {
        	conn.close ();
        }       
        if (i>0) return true; 
        else
		return false;
	}

	@Override
	public boolean newDiagn(Diagnostic daign) throws SQLException {
		Connection conn = connectDB();

        Statement stmt;
        String query = "INSERT INTO  `diagnos` (doctor, sick, diagnosis, date) "
        		+ "VALUES(Т"+Integer.toString(daign.getDoctorId()) +"Т, '"
        					+daign.getSickId()+"Т, '"
        					+daign.getDiagnosis()+"Т, '"
        					+daign.getDate()+"Т)"; 
        int i = 0;
    	//Logger.info(query);
        try {
            stmt = conn.createStatement();
            i = stmt.executeUpdate(query);
            
        } catch (SQLException sqlEx) {
            Logger.info("Ќе получаетс€ соединитьс€ с базой данных!");
        } finally {
        	conn.close ();
        }       
        if (i>0) return true; 
        else
		return false;
	}

	@Override
	public boolean newDoctor(Doctor doc) throws SQLException {
		Connection conn = connectDB();

        Statement stmt;
        String query = "INSERT INTO  `doctors` (login, password, fio, admin) "
        		+ "VALUES(Т"+doc.getLogin() +"Т, '"
        					+doc.getPassword()+"Т, '"
        					+doc.getFio()+"Т, '"
        					+doc.getAdmin()+"Т)"; 
        int i = 0;
    	//Logger.info(query);
        try {
            stmt = conn.createStatement();
            i = stmt.executeUpdate(query);
            
        } catch (SQLException sqlEx) {
            Logger.info("Ќе получаетс€ соединитьс€ с базой данных!");
        } finally {
        	conn.close ();
        }       
        if (i>0) return true; 
        else
		return false;
	}

	@Override
	public ArrayList<Doctor> getDoctor() throws SQLException {
		Connection conn = connectDB();

		ArrayList<Doctor> doc = new ArrayList<Doctor>();
        Statement stmt;
        ResultSet rs;
        String query = "SELECT * FROM `doctors` WHERE admin=0";

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
            	Doctor d = new Doctor(rs.getInt("doc_id"), 
            						  rs.getString("login"), 
            						  rs.getString("password"), 
            						  rs.getString("fio"), 
            						  rs.getBoolean("admin"));
            	doc.add(d);     
            }
        } catch (SQLException sqlEx) {
        	doc = null;
            Logger.info("Ќе получаетс€ соединитьс€ с базой данных!");
        } finally {
        	conn.close ();
        }       
        return doc;
	}

	@Override
	public Sick getSick(int id) throws SQLException {
		Connection conn = connectDB();

		Sick sick = null;
        Statement stmt;
        ResultSet rs;
        String query = "SELECT * FROM `sicks` WHERE sick_id="+Integer.toString(id);

        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
            	sick = new Sick(rs.getInt("sick_id"), 
            					   rs.getString("fio"), 
            					   rs.getDate("hb")); 
            	break;
            }
        } catch (SQLException sqlEx) {
        	sick = null;
            Logger.info("Ќе получаетс€ соединитьс€ с базой данных!");
        } finally {
        	conn.close ();
        }       
        return sick;
	}

}
