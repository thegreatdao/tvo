package com.tvo.telescope;


import java.util.HashMap;
import com.northplains.soapclient.stubs.*;

public class TelescopeConnection {
	
	private String loginUrl = "http://dam.tvo.org/Scripts/WebObjects.dll/TSWeb.woa/wa/services/Login";
	private String telescopeConnectionName = "damdb";
	private String login = "ibuser";
	private String password = "ibuser";
	
	private String serviceUrl;
	private String endPointUrl;
	
	public String getServiceUrl() {
		return serviceUrl;
	}
	
	public String getEndPointUrl() {
		return endPointUrl;
	}
	
	@SuppressWarnings("unchecked")
	public void connect() throws Throwable {
		LoginServiceLocator service = new LoginServiceLocator();
		com.northplains.soapclient.stubs.Login _stub = service.getLogin(new java.net.URL(loginUrl));
		
		NPSMap tNPSMap = _stub.login(telescopeConnectionName, login, password);
		HashMap tReturnValue = com.northplains.soapclient.TSWebSOAPUtils.NPSMapToJavaMap(tNPSMap);
		
		serviceUrl = tReturnValue.get("UI_SERVICE").toString();
		endPointUrl = tReturnValue.get("SOAP_SERVICE").toString();
	}
	
	public void disconnect() throws Throwable {
		TSWebServiceService service = new TSWebServiceServiceLocator();
        TSWebService _stub = service.getsoapservice(new java.net.URL(this.endPointUrl));
        _stub.logout();
	}
}