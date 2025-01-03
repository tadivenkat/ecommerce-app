package com.venkat.ecommerce.notificationservice.notification;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.venkat.ecommerce.productservice.dto.ProductDTO;

@Service
public class NotificationService {

    Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final JavaMailSender mailSender;
    @Value("${mail.from}")
    private String from;

    public NotificationService(JavaMailSender javaMailSender) {
        this.mailSender = javaMailSender;
    }
    
    @KafkaListener(topics = "new-products")
    public void newProductCreated(ConsumerRecord<String, ProductDTO> record) {
        String key = record.key();
        ProductDTO productDTO = record.value();
        String subject = "New product created with id: " + key;
        logger.info(subject);
        sendEmail(
            "tadivenkat@gmail.com", 
            subject, 
            "Product name: " + productDTO.name() 
                + ", Product description: " + productDTO.description() 
                + ", Product price: " + productDTO.price() 
                + ", Available quantity: " + productDTO.availableQuantity()); 
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(from);
        
        mailSender.send(message);
        logger.info("Mail sent successfully to {} with subject: {}", to, subject);
    }
}
