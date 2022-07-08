package com.example.soap;

import jakarta.activation.DataHandler;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;
import jakarta.xml.bind.annotation.XmlAttachmentRef;

@WebService(name = "RouterWebServiceForSender")
@SOAPBinding(style = SOAPBinding.Style.RPC,
        use = SOAPBinding.Use.ENCODED,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface RouterWebServiceForSender {


    @WebMethod(action = "sendReports")
    void sendReports(@WebParam(name = "file")@XmlAttachmentRef DataHandler handler);
}

