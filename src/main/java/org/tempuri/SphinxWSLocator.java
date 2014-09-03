/**
 * SphinxWSLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package org.tempuri;

public class SphinxWSLocator extends org.apache.axis.client.Service implements org.tempuri.SphinxWS {

    public SphinxWSLocator() {
    }


    public SphinxWSLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SphinxWSLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SphinxWSSoap
    private java.lang.String SphinxWSSoap_address = "http://172.16.0.120:7001/WebService/SphinxWS.asmx";

    public java.lang.String getSphinxWSSoapAddress() {
        return SphinxWSSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SphinxWSSoapWSDDServiceName = "SphinxWSSoap";

    public java.lang.String getSphinxWSSoapWSDDServiceName() {
        return SphinxWSSoapWSDDServiceName;
    }

    public void setSphinxWSSoapWSDDServiceName(java.lang.String name) {
        SphinxWSSoapWSDDServiceName = name;
    }

    public org.tempuri.SphinxWSSoap getSphinxWSSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SphinxWSSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSphinxWSSoap(endpoint);
    }

    public org.tempuri.SphinxWSSoap getSphinxWSSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            org.tempuri.SphinxWSSoapStub _stub = new org.tempuri.SphinxWSSoapStub(portAddress, this);
            _stub.setPortName(getSphinxWSSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSphinxWSSoapEndpointAddress(java.lang.String address) {
        SphinxWSSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (org.tempuri.SphinxWSSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                org.tempuri.SphinxWSSoapStub _stub = new org.tempuri.SphinxWSSoapStub(new java.net.URL(SphinxWSSoap_address), this);
                _stub.setPortName(getSphinxWSSoapWSDDServiceName());
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
        if ("SphinxWSSoap".equals(inputPortName)) {
            return getSphinxWSSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "SphinxWS");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "SphinxWSSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SphinxWSSoap".equals(portName)) {
            setSphinxWSSoapEndpointAddress(address);
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
