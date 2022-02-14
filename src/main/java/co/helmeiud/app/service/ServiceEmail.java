package co.helmeiud.app.service;

public interface ServiceEmail {

	public boolean sendEmail(String message, String to, String subject);
	
}
