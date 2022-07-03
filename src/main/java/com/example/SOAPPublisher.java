package com.example;

import com.example.soap.RouterWebServiceSoapImpl;
import jakarta.xml.ws.Endpoint;

public class SOAPPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8086/ws/router", new RouterWebServiceSoapImpl());
    }
}
