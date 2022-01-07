package com.revature;

import java.io.IOException;
import java.util.Scanner;
import java.lang.System;
import com.revature.controllers.*;
import com.revature.exceptions.LoginException;

public class Driver {

	public static void main(String[] args) throws IOException, LoginException {	
		Scanner sc = new Scanner(System.in);
		FrontController.welcome(sc);
//		System.out.println(System.getProperty("log4j2.configurationFile"));
	}
}
