/**
 * SxlServiceImplServiceSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.studentcard.ws.server;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.studentcard.ws.util.HttpRequestUtil;

public class SxlServiceImplServiceSoapBindingImpl implements com.studentcard.ws.server.SxlService{
	
	//String url = "http://127.0.0.1:8080/ZhiXiaoManager/device/";
	String url = "http://101.201.114.147:8081/ZhiXiaoManager/device/";
	
    public java.lang.String pushDeviceLowbat(java.lang.String arg0) throws java.rmi.RemoteException {
    	String res = "";
    	try {
    		String param = url + "PushDeviceLowbat.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
    }

    public java.lang.String pushDeviceFence(java.lang.String arg0) throws java.rmi.RemoteException {
    	String res = "";
    	try {
    		String param = url + "PushDeviceFence.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
    }

    public java.lang.String pushDeviceSOS(java.lang.String arg0) throws java.rmi.RemoteException {
    	String res = "";
    	try {
    		String param = url + "PushDeviceSOS.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
    }

    public java.lang.String pushDeviceCHECK(java.lang.String arg0) throws java.rmi.RemoteException {
    	String res = "";
    	try {
    		String param = url + "PushDeviceCHECK.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
    }

    public java.lang.String pushDeviceStep(java.lang.String arg0) throws java.rmi.RemoteException {
    	String res = "";
    	try {
    		String param = url + "PushDeviceStep.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
    }

    public java.lang.String pushDeviceStatic(java.lang.String arg0) throws java.rmi.RemoteException {
    	String res = "";
    	try {
    		String param = url + "PushDeviceStatic.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
    }

    public java.lang.String pushDeviceCurpos(java.lang.String arg0) throws java.rmi.RemoteException {
    	String res = "";
    	try {
    		String param = url + "PushDeviceCurpos.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
    }
    
    
    public java.lang.String pushDeviceCallnote(java.lang.String arg0) throws java.rmi.RemoteException {
    	String res = "";
    	try {
    		String param = url + "PushDeviceCallnote.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
    }

    
	public java.lang.String pushDeviceOverdue(java.lang.String arg0) throws java.rmi.RemoteException {
		String res = "";
    	try {
    		String param = url + "PushDeviceOverdue.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
	    }
	
	public java.lang.String pushWriteDeviceResult(java.lang.String arg0) throws java.rmi.RemoteException {
		String res = "";
    	try {
    		String param = url + "PushWriteDeviceResult.shtml?jsonStr=" + URLEncoder.encode(arg0,"utf-8");
    		res = HttpRequestUtil.sendGet(param);
		} catch (UnsupportedEncodingException e) {
			res = "{\"RESULT\":\"1\",\"ERRORCODE\":\"255\",\"ERRORDESC\":\"服务异常\"}";
		}
        return res;
    }
}
