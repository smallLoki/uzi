package ru.javabegin.training.objects;

public class Doctor {
	private int id;
	private String login;
	private String password;
	private String fio;
	private boolean admin;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public boolean getAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Doctor(){
		
	}
	
	public Doctor(int id, String login, String password, String fio, boolean admin){
		this.id = id;
		this.login = login;
		this.password = password;
		this.fio = fio;
		this.admin = admin;
	}
}
