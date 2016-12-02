package ru.javabegin.training.interfaces;

import java.util.List;
import ru.javabegin.training.objects.Doctor;

public interface doctorDao {

	void insert(Doctor doctor);
	
	void delete(Doctor doctor);
	
	Doctor getDoctorById(int id);
	
	List<Doctor> getDoctorListByLogin(String login);
	
	List<Doctor> getDoctorListByFIO(String fio);
}
