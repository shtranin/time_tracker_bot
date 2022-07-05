package com.example.bot;

import com.example.file.FileManagerImpl;
import com.example.soap.RouterWebServiceSoapImpl;
import jakarta.xml.ws.Endpoint;

public class SOAPPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8086/ws/router", new RouterWebServiceSoapImpl());
       // Endpoint.publish("http://localhost:8086/ws/router",new FileManagerImpl());
    }
}
