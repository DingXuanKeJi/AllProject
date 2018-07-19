/**
 * SxlServiceImplServiceSoapBindingSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.studentcard.ws.server;

public class SxlServiceImplServiceSoapBindingSkeleton implements com.studentcard.ws.server.SxlService, org.apache.axis.wsdl.Skeleton {
    private com.studentcard.ws.server.SxlService impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushDeviceLowbat", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushDeviceLowbat"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushDeviceLowbat") == null) {
            _myOperations.put("pushDeviceLowbat", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushDeviceLowbat")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushDeviceCallnote", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushDeviceCallnote"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushDeviceCallnote") == null) {
            _myOperations.put("pushDeviceCallnote", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushDeviceCallnote")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushDeviceFence", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushDeviceFence"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushDeviceFence") == null) {
            _myOperations.put("pushDeviceFence", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushDeviceFence")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushDeviceSOS", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushDeviceSOS"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushDeviceSOS") == null) {
            _myOperations.put("pushDeviceSOS", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushDeviceSOS")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushDeviceCHECK", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushDeviceCHECK"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushDeviceCHECK") == null) {
            _myOperations.put("pushDeviceCHECK", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushDeviceCHECK")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushDeviceOverdue", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushDeviceOverdue"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushDeviceOverdue") == null) {
            _myOperations.put("pushDeviceOverdue", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushDeviceOverdue")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushDeviceStep", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushDeviceStep"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushDeviceStep") == null) {
            _myOperations.put("pushDeviceStep", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushDeviceStep")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushDeviceStatic", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushDeviceStatic"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushDeviceStatic") == null) {
            _myOperations.put("pushDeviceStatic", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushDeviceStatic")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushDeviceCurpos", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushDeviceCurpos"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushDeviceCurpos") == null) {
            _myOperations.put("pushDeviceCurpos", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushDeviceCurpos")).add(_oper);
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "arg0"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("pushWriteDeviceResult", _params, new javax.xml.namespace.QName("", "return"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://server.ws.studentcard.com/", "PushWriteDeviceResult"));
        _oper.setSoapAction("");
        _myOperationsList.add(_oper);
        if (_myOperations.get("pushWriteDeviceResult") == null) {
            _myOperations.put("pushWriteDeviceResult", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("pushWriteDeviceResult")).add(_oper);
    }

    public SxlServiceImplServiceSoapBindingSkeleton() {
        this.impl = new com.studentcard.ws.server.SxlServiceImplServiceSoapBindingImpl();
    }

    public SxlServiceImplServiceSoapBindingSkeleton(com.studentcard.ws.server.SxlService impl) {
        this.impl = impl;
    }
    public java.lang.String pushDeviceLowbat(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushDeviceLowbat(arg0);
        return ret;
    }

    public java.lang.String pushDeviceCallnote(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushDeviceCallnote(arg0);
        return ret;
    }

    public java.lang.String pushDeviceFence(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushDeviceFence(arg0);
        return ret;
    }

    public java.lang.String pushDeviceSOS(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushDeviceSOS(arg0);
        return ret;
    }

    public java.lang.String pushDeviceCHECK(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushDeviceCHECK(arg0);
        return ret;
    }

    public java.lang.String pushDeviceOverdue(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushDeviceOverdue(arg0);
        return ret;
    }

    public java.lang.String pushDeviceStep(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushDeviceStep(arg0);
        return ret;
    }

    public java.lang.String pushDeviceStatic(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushDeviceStatic(arg0);
        return ret;
    }

    public java.lang.String pushDeviceCurpos(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushDeviceCurpos(arg0);
        return ret;
    }

    public java.lang.String pushWriteDeviceResult(java.lang.String arg0) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.pushWriteDeviceResult(arg0);
        return ret;
    }

}
