package com.example.sbtickets.service;

import com.example.sbtickets.entity.Customer;
import com.example.sbtickets.entity.TripBus;
import com.example.sbtickets.service.impl.SendToEmailImplement;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SendToEmailService implements SendToEmailImplement {

    private static final Logger logger = Logger.getLogger(SendToEmailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendToEmail(int roleCar, TripBus tripBus, Customer customer) {
        try {
            String from = "sbtickets2021@gmail.com";
            String to = customer.getEmail();

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper setMess = new MimeMessageHelper(message);

            setMess.setSubject("Booked in Sbtickets");
            setMess.setFrom(from);
            setMess.setTo(to);

            setMess.setText("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                            "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                            " <head>\n" +
                            "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
                            "  <title>Demystifying Email Design</title>\n" +
                            "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\n" +
                            "</head>\n" +
                            "</html>" +
                            "<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\n" +
                    " <tr>\n" +
                    "  <td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0;\">" +
                    "    <img src=\"http://uberbinhduong.com/uploads/news/2017/12/xe-giuong-mam-my-phuoc.jpg\" alt=\"Creating Email Magic\" width=\"300\" height=\"230\" style=\"display: block;\" />" +
                    "  </td>\n" +
                    " </tr>\n" +
                    " <tr>\n" +
                    "  <td bgcolor=\"#ffffff\">\n" +
                    "   <b>Dear " + customer.getFullName() + "</b> \n" +
                    "  </td>\n" +
                    " </tr>\n" +
                    " <tr>\n" +
                    "  <td style=\"padding: 20px 0 30px 0;\" \n" +
                    "   <br><i>Thank you. You have successfully booked your ticket</i><br>" +
                    "<br> TripCode: " + tripBus.getId() + "<br> TimeTrip: " +  tripBus.getTimeTrip() + "<br> CarNumber: " + tripBus.getBus().getCarNumber()
                            + " - Color: " + tripBus.getBus().getColor() + " " + "<br> Number Seat: " + roleCar + "<br> Please arrive 30 minutes in advance. Thanks" + "<br> \n" +
                    "  </td>\n" +
                    " </tr>\n" +
                    "</table>", true);


            javaMailSender.send(message);
        }
        catch (Exception ex){
            logger.error(ex.getMessage());
        }
    }
}
