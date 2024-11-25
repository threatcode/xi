package com.cache.dto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Hello extends Remote {
	Result sayHello(String appid) throws RemoteException;
	
	String hello1 (String name) throws RemoteException;
	
	
	Result getResult(String appid) throws RemoteException;
}