package com.example.util;

import com.example.models.Group;
import com.example.models.User;
import jakarta.xml.soap.*;
import java.net.MalformedURLException;
import java.net.URL;


public class SOAPUtil {


    public SOAPUtil() {
    }

    public static SOAPMessage createSOAPRequestCreateGroup(String urlString,Group group) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        URL url = new URL(urlString);
        String space = url.getProtocol() + "://" + url.getHost() + "/";
        String method = url.getPath().split("/")[2].replace("Request", "");

        String nameSpaceSoap = "soap";
        SOAPPart soapPart = soapMessage.getSOAPPart();
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(nameSpaceSoap, space);
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        SOAPElement actionSoap = soapBody.addChildElement(method, nameSpaceSoap);

        SOAPElement itemSoap = actionSoap.addChildElement("arg0");

        SOAPElement soapBodyElem2 = itemSoap.addChildElement("color");
        soapBodyElem2.addTextNode(group.getColor());
        SOAPElement soapBodyElem3 = itemSoap.addChildElement("teamLeadId");
        soapBodyElem3.addTextNode(String.valueOf(group.getTeamLeadId()));

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", urlString);

        soapMessage.saveChanges();
        return soapMessage;
    }


    public static SOAPMessage createSOAPRequestRemoveGroup(String urlString, Long userId) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        URL url = new URL(urlString);
        String space = url.getProtocol() + "://" + url.getHost() + "/";
        String method = url.getPath().split("/")[2].replace("Request", "");

        String nameSpaceSoap = "soap";
        SOAPPart soapPart = soapMessage.getSOAPPart();
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(nameSpaceSoap, space);
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        SOAPElement actionSoap = soapBody.addChildElement(method, nameSpaceSoap);


        SOAPElement soapBodyElem1 = actionSoap.addChildElement("arg0");
        soapBodyElem1.addTextNode(userId.toString());

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", urlString);

        soapMessage.saveChanges();
        return soapMessage;
    }


    public static SOAPMessage createSOAPRequestAddUserInGroup(String urlString, Long userId,int groupId) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        URL url = new URL(urlString);
        String space = url.getProtocol() + "://" + url.getHost() + "/";
        String method = url.getPath().split("/")[2].replace("Request", "");

        String nameSpaceSoap = "soap";
        SOAPPart soapPart = soapMessage.getSOAPPart();
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(nameSpaceSoap, space);
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        SOAPElement actionSoap = soapBody.addChildElement(method, nameSpaceSoap);


        SOAPElement soapBodyElem1 = actionSoap.addChildElement("arg0");
        soapBodyElem1.addTextNode(userId.toString());
        SOAPElement soapBodyElem2 = actionSoap.addChildElement("arg1");
        soapBodyElem2.addTextNode(String.valueOf(groupId));

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", urlString);

        soapMessage.saveChanges();
        return soapMessage;
    }

    public static SOAPMessage createSOAPRequestGetGroupIdByUserId(String urlString, Long userId) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        URL url = new URL(urlString);
        String space = url.getProtocol() + "://" + url.getHost() + "/";
        String method = url.getPath().split("/")[2].replace("Response", "");

        String nameSpaceSoap = "soap";
        SOAPPart soapPart = soapMessage.getSOAPPart();
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(nameSpaceSoap, space);
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        SOAPElement actionSoap = soapBody.addChildElement(method, nameSpaceSoap);


        SOAPElement soapBodyElem1 = actionSoap.addChildElement("userid");
        soapBodyElem1.addTextNode(userId.toString());

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", urlString);

        soapMessage.saveChanges();
        return soapMessage;
    }

    public static SOAPMessage createSOAPRequestCreateUser(String urlString,User user) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        URL url = new URL(urlString);
        String space = url.getProtocol() + "://" + url.getHost() + "/";
        String method = url.getPath().split("/")[2].replace("Request", "");

        String nameSpaceSoap = "soap";
        SOAPPart soapPart = soapMessage.getSOAPPart();
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(nameSpaceSoap, space);
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        SOAPElement actionSoap = soapBody.addChildElement(method, nameSpaceSoap);

        SOAPElement itemSoap = actionSoap.addChildElement("arg0");

        SOAPElement soapBodyElem1 = itemSoap.addChildElement("telegramId");
        soapBodyElem1.addTextNode(String.valueOf(user.getTelegramId()));
        SOAPElement soapBodyElem2 = itemSoap.addChildElement("firstName");
        soapBodyElem2.addTextNode(user.getFirstName());
        SOAPElement soapBodyElem3 = itemSoap.addChildElement("lastName");
        soapBodyElem3.addTextNode(String.valueOf(user.getLastName()));

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", urlString);

        soapMessage.saveChanges();
        return soapMessage;
    }

    public static SOAPMessage createSOAPRequestGetDevelopers(String urlString, int groupId) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        URL url = new URL(urlString);
        String space = url.getProtocol() + "://" + url.getHost() + "/";
        String method = url.getPath().split("/")[2].replace("Response", "");

        String nameSpaceSoap = "soap";
        SOAPPart soapPart = soapMessage.getSOAPPart();
        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration(nameSpaceSoap, space);
        // SOAP Body
        SOAPBody soapBody = envelope.getBody();

        SOAPElement actionSoap = soapBody.addChildElement(method, nameSpaceSoap);


        SOAPElement soapBodyElem1 = actionSoap.addChildElement("arg0");
        soapBodyElem1.addTextNode(String.valueOf(groupId));

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", urlString);

        soapMessage.saveChanges();
        return soapMessage;
    }
     public static SOAPMessage createSOAPRequestGetUsersNotLeamleads(String urlString) throws SOAPException, MalformedURLException {
         MessageFactory messageFactory = MessageFactory.newInstance();
         SOAPMessage soapMessage = messageFactory.createMessage();


         URL url = new URL(urlString);
         String space = url.getProtocol() + "://" + url.getHost() + "/";
         String method = url.getPath().split("/")[2].replace("Response", "");

         String nameSpaceSoap = "soap";
         SOAPPart soapPart = soapMessage.getSOAPPart();
         // SOAP Envelope
         SOAPEnvelope envelope = soapPart.getEnvelope();
         envelope.addNamespaceDeclaration(nameSpaceSoap, space);
         // SOAP Body
         SOAPBody soapBody = envelope.getBody();

         SOAPElement actionSoap = soapBody.addChildElement(method, nameSpaceSoap);

         MimeHeaders headers = soapMessage.getMimeHeaders();
         headers.addHeader("SOAPAction", urlString);

         soapMessage.saveChanges();
         return soapMessage;
    }

}
