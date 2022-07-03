package com.example.soap;


import com.example.models.User;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.jws.soap.SOAPBinding;

@WebService(name = "RouterWebServiceSoap")
@SOAPBinding(style = SOAPBinding.Style.RPC,
        use = SOAPBinding.Use.ENCODED,
        parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface RouterWebServiceSoap {

    @WebMethod(action = "add_unchecked_members")
    @WebResult(name = "membersArray", partName = "membersArray")
    User[] addUncheckedMembers(@WebParam(name = "members", partName = "members") User[] members);
}
