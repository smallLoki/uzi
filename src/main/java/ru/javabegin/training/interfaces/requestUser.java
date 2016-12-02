package ru.javabegin.training.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import ru.javabegin.training.objects.Diagnostic;
import ru.javabegin.training.objects.Doctor;
import ru.javabegin.training.objects.Sick;

public interface requestUser {

	// Общие
	Doctor loginDoctor(Doctor doc) throws SQLException;
	boolean updatePass(Doctor doc, String password) throws SQLException;
	ArrayList<Sick> getSick() throws SQLException;
	ArrayList<Diagnostic> getDiagn(int id) throws SQLException;
	boolean setSick(Sick sick) throws SQLException;
	//ArrayList<StatDoc> getStatDoc();
	
	// Врач
	boolean newDiagn(Diagnostic daign) throws SQLException;
	
	// админ
	ArrayList<Doctor> getDoctor() throws SQLException;
	boolean newDoctor(Doctor doc) throws SQLException;
	Sick getSick(int id) throws SQLException;
	
}
