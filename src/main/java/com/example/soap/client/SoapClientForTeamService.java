package com.example.soap.client;

import com.example.models.Group;
import com.example.models.User;
import com.example.soap.model.UserArray;
import com.example.soap.model.Users;
import com.example.util.SOAPUtil;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.soap.*;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class SoapClientForTeamService {

    private static String teamSoapEndpointUrl;
    private static String uriCreateGroup;
    private static String uriCreateUser;
    private static String uriUsersNotInGroup;
    private static String uriAddUser;
    private static String uriRemoveGroupByTeamLeadId;
    private static String uriGetGroupIdByUserId;
    private static String uriUsersNotTeamleads;



    public SoapClientForTeamService() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("properties\\connection.properties")) {
            Properties props = new Properties();
            props.load(is);
            teamSoapEndpointUrl = props.getProperty("team.url");
            uriCreateGroup = props.getProperty("team.action.createGroup");
            uriCreateUser = props.getProperty("team.action.createUser");
            uriUsersNotInGroup = props.getProperty("team.action.usersNotInGroup");
            uriAddUser = props.getProperty("team.action.addUser");
            uriRemoveGroupByTeamLeadId = props.getProperty("team.action.removeByTeamLeadId");
            uriGetGroupIdByUserId = props.getProperty("team.action.getGroupByByUserId");
            uriUsersNotTeamleads = props.getProperty("team.action.usersNotTeamleads");

        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file");
        }
    }
    public void createGroup(Group group) {

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = SOAPUtil.createSOAPRequestCreateGroup(uriCreateGroup,group);

            System.out.println("Response SOAP Message:");
            soapRequest.writeTo(System.out);

            soapConnection.call(soapRequest, teamSoapEndpointUrl);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }
    public void removeGroup(Long userId){
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = SOAPUtil.createSOAPRequestRemoveGroup(uriRemoveGroupByTeamLeadId,userId);

            System.out.println("Response SOAP Message:");
            soapRequest.writeTo(System.out);

            soapConnection.call(soapRequest, teamSoapEndpointUrl);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }
    public void addUserInGroup(Long userId,int groupId){
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = SOAPUtil.createSOAPRequestAddUserInGroup(uriAddUser,userId,groupId);

            System.out.println("Response SOAP Message:");
            soapRequest.writeTo(System.out);

            soapConnection.call(soapRequest, teamSoapEndpointUrl);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }
    public Group getGroupIdByUserId(Long userId){
        Group group = null;
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = SOAPUtil.createSOAPRequestGetGroupIdByUserId(uriGetGroupIdByUserId,userId);

          //  System.out.println("Response SOAP Message:");
           // soapRequest.writeTo(System.out);

           SOAPMessage soapResponse = soapConnection.call(soapRequest, "http://82.146.35.247:8085/team-service-1.0/service/router");
            System.out.println("Request SOAP Message");
            soapResponse.writeTo(System.out);
           group = parseGroup(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
        return group;
    }
    private Group parseGroup(SOAPMessage soapResponse) throws Exception {
        DOMSource source = new DOMSource(soapResponse.getSOAPBody().getChildNodes().item(0).getChildNodes().item(0));
        StringWriter stringResult = new StringWriter();
        TransformerFactory.newInstance().newTransformer().transform(source, new StreamResult(stringResult));
        String message = stringResult.toString();
        System.out.println(message);
        JAXBContext context = JAXBContext.newInstance(Group.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Group group = (Group) unmarshaller.unmarshal(new StringReader(message));
        return group;
    }

    private List<User> parseUsers(SOAPMessage soapResponse) throws Exception{
        DOMSource source = new DOMSource(soapResponse.getSOAPBody().getChildNodes().item(0).getChildNodes().item(0));
        StringWriter stringResult = new StringWriter();
        TransformerFactory.newInstance().newTransformer().transform(source, new StreamResult(stringResult));
        String message = stringResult.toString();
        System.out.println(message);
        JAXBContext context = JAXBContext.newInstance(Users.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Users users = (Users) unmarshaller.unmarshal(new StringReader(message));
        return users.getUserArray();

    }
    public void createUser(User user){
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = SOAPUtil.createSOAPRequestCreateUser(uriCreateUser,user);

            System.out.println("Response SOAP Message:");
            soapRequest.writeTo(System.out);

            soapConnection.call(soapRequest, teamSoapEndpointUrl);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }

    }
    public List<User> getDevelopersList(int groupId){
        List<User> users = Collections.emptyList();
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = SOAPUtil.createSOAPRequestGetDevelopers(uriUsersNotInGroup,groupId);

            System.out.println("Response SOAP Message:");
            soapRequest.writeTo(System.out);
            System.out.println("\n\n");
            SOAPMessage soapResponse = soapConnection.call(soapRequest, "http://82.146.35.247:8085/team-service-1.0/service/router");

            soapResponse.writeTo(System.out);
            System.out.println("\n\n");
            users = parseUsers(soapResponse);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }

        return users;
    }
    public List<User> getUsersNotLeamleads(){
        List<User> users = Collections.emptyList();
        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = SOAPUtil.createSOAPRequestGetUsersNotLeamleads(uriUsersNotTeamleads);

            System.out.println("Response SOAP Message:");
            soapRequest.writeTo(System.out);

            SOAPMessage soapResponse = soapConnection.call(soapRequest, teamSoapEndpointUrl);
            users = parseUsers(soapResponse);

            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }

        return users;
    }
}
