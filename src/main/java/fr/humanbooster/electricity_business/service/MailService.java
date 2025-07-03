package fr.humanbooster.electricity_business.service;

public interface MailService {
    void sendEmail(String to, String subject, String body);
}