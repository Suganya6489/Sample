/**
 * WebQAWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.net.webqa;

public class WebQAWSLocator extends org.apache.axis.client.Service implements com.net.webqa.WebQAWS {

    public WebQAWSLocator() {
    }


    public WebQAWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WebQAWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WebQAWSSoap
    private java.lang.String WebQAWSSoap_address = "http://dev.mycusthelp.com/ADDISONTXTEST/Admin/_ws/WebQAWS.asmx";

    public java.lang.String getWebQAWSSoapAddress() {
        return WebQAWSSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WebQAWSSoapWSDDServiceName = "WebQAWSSoap";

    public java.lang.String getWebQAWSSoapWSDDServiceName() {
        return WebQAWSSoapWSDDServiceName;
    }

    public void setWebQAWSSoapWSDDServiceName(java.lang.String name) {
        WebQAWSSoapWSDDServiceName = name;
    }

    public com.net.webqa.WebQAWSSoap getWebQAWSSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WebQAWSSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWebQAWSSoap(endpoint);
    }

    public com.net.webqa.WebQAWSSoap getWebQAWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.net.webqa.WebQAWSSoapStub _stub = new com.net.webqa.WebQAWSSoapStub(portAddress, this);
            _stub.setPortName(getWebQAWSSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWebQAWSSoapEndpointAddress(java.lang.String address) {
        WebQAWSSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.net.webqa.WebQAWSSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.net.webqa.WebQAWSSoapStub _stub = new com.net.webqa.WebQAWSSoapStub(new java.net.URL(WebQAWSSoap_address), this);
                _stub.setPortName(getWebQAWSSoapWSDDServiceName());
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
        if ("WebQAWSSoap".equals(inputPortName)) {
            return getWebQAWSSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webqa.net/", "WebQAWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webqa.net/", "WebQAWSSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WebQAWSSoap".equals(portName)) {
            setWebQAWSSoapEndpointAddress(address);
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
