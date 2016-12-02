package ru.javabegin.training.objects;

public class newPass {
	private String oldpass;
	private String newpass1;
	private String newpass2;
	public String getOldPass() {
		return oldpass;
	}
	public void setOldPass(String oldPass) {
		this.oldpass = oldPass;
	}
	public String getNewPass1() {
		return newpass1;
	}
	public void setNewPass1(String newPass1) {
		this.newpass1 = newPass1;
	}
	public String getNewPass2() {
		return newpass2;
	}
	public void setNewPass2(String newPass2) {
		this.newpass2 = newPass2;
	} 
	
	public newPass(){
		this.oldpass = "";
		this.newpass1 = "";
		this.newpass2 = "";
	}
	
	public newPass(String oldPass, String newPass1, String newPass2){
		this.oldpass = oldPass;
		this.newpass1 = newPass1;
		this.newpass2 = newPass2;
	}
}
