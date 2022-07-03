package com.example.util;

import com.example.models.Group;
import com.example.models.User;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.soap.*;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SOAPUtil {
    private static String nameSpace = "soap";
    private static String teamSoapEndpointUrl;
    private static String spaceTeamUri;
    private static String uriCreateGroup;
    private static String uriCreateUser;
    private static String usersNotInGroup;
    private static String addUser;
    private static String removeGroupByTeamLeadId;

    public SOAPUtil() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("properties\\connection.properties")) {
            Properties props = new Properties();
            props.load(is);
            teamSoapEndpointUrl = props.getProperty("team.url");
            spaceTeamUri = props.getProperty("team.space.uri");
            uriCreateGroup = props.getProperty("team.action.creategroup");
            uriCreateUser = props.getProperty("team.action.createUser");
            usersNotInGroup = props.getProperty("team.action.usersNotInGroup");
            addUser= props.getProperty("team.action.addUser");
            removeGroupByTeamLeadId = props.getProperty("team.action.removeByTeamLeadId");

        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file");
        }
    }

    private static DateFormat dateToSoap = new SimpleDateFormat("yyyy-dd-MM'T'hh:mm:ss");

    public static List<User> parserToUserArray(SOAPMessage soapResponse) throws Exception {
        DOMSource source = new DOMSource(soapResponse.getSOAPBody().getChildNodes().item(0).getChildNodes().item(0));
        StringWriter stringResult = new StringWriter();
        TransformerFactory.newInstance().newTransformer().transform(source, new StreamResult(stringResult));
        String message = stringResult.toString();
        System.out.println(message);
        //todo change classes
        JAXBContext context = JAXBContext.newInstance(User.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        //Users users = (Users) unmarshaller.unmarshal(new StringReader(message));
        return Collections.emptyList();
    }


    public static SOAPMessage createSOAPRequestCreateGroup(Group group) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        createSoapEnvelopeWithGroup(soapMessage, uriCreateGroup, group);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", uriCreateGroup);

        soapMessage.saveChanges();
        return soapMessage;
    }


    private static void createSoapEnvelopeWithGroup(SOAPMessage soapMessage, String urlString, Group group) throws Exception {
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


    }
    public static SOAPMessage createSOAPRequestRemoveGroup(String uriSoapAction, Long userId) throws Exception {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();


        createSoapEnvelopeRemoveGroup(soapMessage, uriSoapAction, userId);

        MimeHeaders headers = soapMessage.getMimeHeaders();
        headers.addHeader("SOAPAction", uriSoapAction);

        soapMessage.saveChanges();
        return soapMessage;
    }
    private static void createSoapEnvelopeRemoveGroup(SOAPMessage soapMessage, String urlString, Long userId) throws Exception {
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

        SOAPElement soapBodyElem1 = itemSoap.addChildElement("arg0");
        soapBodyElem1.addTextNode(userId.toString());



    }
}
