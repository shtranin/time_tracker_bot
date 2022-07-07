package com.example.soap;

import com.example.bot.BotInitializator;
import com.example.soap.model.ByteArray;
import jakarta.activation.DataHandler;
import jakarta.jws.WebService;
import jakarta.xml.soap.SOAPMessage;

import java.io.*;

@WebService(endpointInterface = "com.example.soap.RouterWebServiceForSender",
        portName = "routerPort",
        serviceName = "routerService")
public class RouterWebServiceForSenderImpl implements RouterWebServiceForSender {


    @Override
    public void sendReports(DataHandler handler) {
        File tempReportFile = null;

        try {
            tempReportFile = File.createTempFile("reports", ".pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (InputStream input = handler.getInputStream();
             OutputStream output = new FileOutputStream(tempReportFile)) {

            byte[] b = new byte[100000];
            int bytesRead = 0;

            while ((bytesRead = input.read(b)) != -1) {
                output.write(b, 0, bytesRead);
            }
            BotInitializator.getSendReportsCommand().execute(tempReportFile);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
