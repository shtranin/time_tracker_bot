package com.example.soap;

import com.example.soap.model.ByteArray;
import com.example.soap.model.ExpiredUsers;
import jakarta.activation.DataHandler;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlAttachmentRef;
import jakarta.xml.soap.SOAPMessage;

@WebService(name = "RouterWebServiceForSender")
@SOAPBinding(style = SOAPBinding.Style.RPC,
        use = SOAPBinding.Use.ENCODED,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface RouterWebServiceForSender {


    @WebMethod(action = "sendReports")
    void sendReports(@WebParam(name = "file")@XmlAttachmentRef DataHandler handler);
}

