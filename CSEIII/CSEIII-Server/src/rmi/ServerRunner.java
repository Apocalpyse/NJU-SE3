
package rmi;

import data.*;
import dataSer.FindStockInfoDataSer;
import rmi.RemoteHelper;

import java.rmi.RemoteException;
import java.util.ArrayList;

public class ServerRunner {

	public ServerRunner() {
		new RemoteHelper();
	}


	public static void main(String[] args) {
			new ServerRunner();
			System.out.println("Successful");

	}
}
