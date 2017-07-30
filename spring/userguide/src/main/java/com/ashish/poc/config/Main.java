package com.ashish.poc.config;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		org.hsqldb.util.DatabaseManagerSwing.main(new String[] { "--url",
				"jdbc:h2:D:\\Ashish\\Delete\\testdb;MV_STORE=FALSE;MVCC=FALSE", "--noexit", "--user", "sa", "--password",
				"" });
	}

}
