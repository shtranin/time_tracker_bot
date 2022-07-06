package com.example.soap;


import com.example.soap.model.ExpiredUsers;
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



    @WebMethod(action = "sendExpiredUsersToLector")
    @WebResult(name = "users", partName = "users")
    void sendExpiredUsersToLector(@WebParam(name = "expiredUsersWithOwner")ExpiredUsers users);

    @WebMethod(action = "sendExpiredUsersToTeamLead")
    @WebResult(name = "users", partName = "users")
    void sendExpiredUsersToTeamLead(@WebParam(name = "expiredUsersWithOwner")ExpiredUsers users);


}
