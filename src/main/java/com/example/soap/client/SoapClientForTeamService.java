package com.example.soap.client;

import com.example.models.Group;
import com.example.util.SOAPUtil;
import jakarta.xml.soap.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SoapClientForTeamService {


    private static String nameSpace = "soap";
    private static String teamSoapEndpointUrl;
    private static String spaceTeamUri;
    private static String uriCreateGroup;
    private static String uriCreateUser;
    private static String usersNotInGroup;
    private static String addUser;
    private static String uriRemoveGroupByTeamLeadId;



    public SoapClientForTeamService() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("properties\\connection.properties")) {
            Properties props = new Properties();
            props.load(is);
            teamSoapEndpointUrl = props.getProperty("team.url");
            spaceTeamUri = props.getProperty("team.space.uri");
            uriCreateGroup = props.getProperty("team.action.creategroup");
            uriCreateUser = props.getProperty("team.action.createUser");
            usersNotInGroup = props.getProperty("team.action.usersNotInGroup");
            addUser= props.getProperty("team.action.addUser");
            uriRemoveGroupByTeamLeadId = props.getProperty("team.action.removeByTeamLeadId");

        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file");
        }
    }
    public void createGroup(Group group) {

        try {
            SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
            SOAPConnection soapConnection = soapConnectionFactory.createConnection();
            SOAPMessage soapRequest = SOAPUtil.createSOAPRequestCreateGroup(group);

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
            SOAPMessage soapRequest = SOAPUtil.createSOAPRequestRemoveGroup(uriRemoveGroupByTeamLeadId, userId);

            System.out.println("Response SOAP Message:");
            soapRequest.writeTo(System.out);

            soapConnection.call(soapRequest, teamSoapEndpointUrl);
            soapConnection.close();
        } catch (Exception e) {
            System.err.println("\nError occurred while sending SOAP Request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
            e.printStackTrace();
        }
    }
}
