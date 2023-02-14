package com.pe.gidtec.servicedesk.ticket;

import com.pe.gidtec.servicedesk.lib.pdf.builder.PdfGenerator;
import com.pe.gidtec.servicedesk.ticket.tickets.service.TicketsService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SupportGidtecServicedeskTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupportGidtecServicedeskTicketApplication.class, args);
    }

}
