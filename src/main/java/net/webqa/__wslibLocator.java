/**
 * __wslibLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package net.webqa;

import com.hubciti.common.utility.PropertiesReader;

public class __wslibLocator extends org.apache.axis.client.Service implements net.webqa.__wslib {
	

    public __wslibLocator() {
    }


    public __wslibLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public __wslibLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for __wslibSoap
    private java.lang.String __wslibSoap_address = "http://mygovhelpadmin.com/ADDISONTX/ZAdmin/_ws/_wslib.asmx";

    public java.lang.String get__wslibSoapAddress() {
        return __wslibSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String __wslibSoapWSDDServiceName = "__wslibSoap";

    public java.lang.String get__wslibSoapWSDDServiceName() {
        return __wslibSoapWSDDServiceName;
    }

    public void set__wslibSoapWSDDServiceName(java.lang.String name) {
        __wslibSoapWSDDServiceName = name;
    }

    public net.webqa.__wslibSoap get__wslibSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(__wslibSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return get__wslibSoap(endpoint);
    }

    public net.webqa.__wslibSoap get__wslibSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            net.webqa.__wslibSoapStub _stub = new net.webqa.__wslibSoapStub(portAddress, this);
            _stub.setPortName(get__wslibSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void set__wslibSoapEndpointAddress(java.lang.String address) {
        __wslibSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (net.webqa.__wslibSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                net.webqa.__wslibSoapStub _stub = new net.webqa.__wslibSoapStub(new java.net.URL(__wslibSoap_address), this);
                _stub.setPortName(get__wslibSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("__wslibSoap".equals(inputPortName)) {
            return get__wslibSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webqa.net/", "__wslib");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webqa.net/", "__wslibSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("__wslibSoap".equals(portName)) {
            set__wslibSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
