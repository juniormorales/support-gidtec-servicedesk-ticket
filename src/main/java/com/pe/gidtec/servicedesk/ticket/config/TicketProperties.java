package com.pe.gidtec.servicedesk.ticket.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "ticket-properties")
@Getter
@Setter
@Builder
public class TicketProperties {
    private Map<String, String> typeCode;
    private Map<String, String> categoryCode;
    private Map<String, String> statusCode;
}
