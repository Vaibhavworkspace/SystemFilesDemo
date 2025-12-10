package com.controller;

import com.service.FileSystemService;

public class Main {

	public static void main(String[] args) {
		FileSystemService fs = new FileSystemService();

		// scenario 1st
		System.out.println(
				"##########Demo For Drive, folder, text file creation , writing file and Size Calculation ##########");
		fs.createDrive("D");
		System.out.println("Folder created :  " + fs.create("D\\", "WorkDir", "folder"));
		System.out.println("Text file created :  " + fs.create("D\\WorkDir\\", "report.txt", "text"));
		fs.write("D\\WorkDir\\report.txt\\", "This is a test file");
		System.out.println("Size of Driver D: " + fs.getSize("D\\"));

		// Scenario 2nd
		System.out.println("##########Demo For Zip folder and Size Calculation ##########");
		fs.createDrive("ZIPDrive");
		System.out.println(fs.create("ZIPDrive\\", "Work", "folder"));
		System.out.println(fs.create("ZIPDrive\\Work\\", "Info.txt", "text"));
		System.out.println(fs.write("ZIPDrive\\Work\\Info.txt\\", "Secret message"));
		System.out.println("Size Info.txt file:  " + fs.getSize("ZIPDrive\\Work\\Info.txt\\"));
		System.out.println(fs.create("ZIPDrive\\Work\\", "archive.zip", "zip"));
		System.out.println(fs.create("ZIPDrive\\Work\\archive.zip\\", "confidential.txt", "text"));
		System.out.println(fs.write("ZIPDrive\\Work\\archive.zip\\confidential.txt\\", "Secret message"));
		System.out.println("Size of zip file: " + fs.getSize("ZIPDrive\\Work\\archive.zip\\"));
		System.out.println("Size for Work folder : " + fs.getSize("ZIPDrive\\Work"));

		// Scenario 3rd
		// Move folder
		System.out.println("##########Demo For Move folder ##########");
		fs.createDrive("Demo");
		System.out.println(fs.create("Demo\\", "Folder1", "folder"));
		System.out.println(fs.create("Demo\\", "Folder2", "folder"));

		fs.createDrive("MoveDemo");
		System.out.println(fs.create("MoveDemo\\", "Folder3", "folder"));

		System.out.println(fs.move("Demo\\Folder1", "MoveDemo\\"));
		System.out.println(fs.exists("Demo\\Folder1"));
		System.out.println(fs.exists("Demo\\Folder2"));
		System.out.println(fs.exists("MoveDemo\\Folder1"));

		// scenario 4th
		// Delete folder
		System.out.println("##########Demo For Delete folder ##########");
		fs.createDrive("DeleteDemo");
		System.out.println(fs.create("DeleteDemo\\", "Folder1", "folder"));
		System.out.println(fs.create("DeleteDemo\\Folder1\\", "report.txt", "text"));
		System.out.println(fs.create("DeleteDemo\\", "Folder2", "folder"));

		System.out.println(fs.delete("DeleteDemo\\Folder1"));
		System.out.println(fs.exists("DeleteDemo\\Folder1"));
		System.out.println(fs.exists("DeleteDemo\\Folder2"));

		// Negative scenario
		System.out.println("##########Demo For Negative scenario1 ##########");
		fs.createDrive("DemoDeleteDrive");
		System.out.println(fs.create("DemoDeleteDrive\\", "Folder1", "folder"));
		System.out.println(fs.create("DemoDeleteDrive\\Folder1\\", "report.txt", "text"));
		System.out.println(fs.delete("DemoDeleteDrive"));

		System.out.println("##########Demo For Negative scenario2 ##########");
		fs.createDrive("DemoDeleteDrive");
		System.out.println(fs.create("DemoDeleteDrive\\", "Folder1", "folder"));
		System.out.println(fs.create("DemoDeleteDrive\\Folder1\\", "report.txt", "text"));
		System.out.println(fs.delete("DemoDeleteDrive\\Folder5\\"));

		System.out.println("##########Demo For Negative scenario3 ##########");
		fs.createDrive("DemoMoveFolder");
		System.out.println(fs.create("DemoMoveFolder\\", "Folder1", "folder"));
		System.out.println(fs.create("DemoMoveFolder\\", "Folder2", "folder"));

		System.out.println(fs.move("DemoMoveFolder\\Folder1", "TargetFolder\\"));

		System.out.println("##########Demo For Negative scenario4 ##########");
		fs.createDrive("DemoForWritingOverFolder");
		System.out.println(fs.create("DemoForWritingOverFolder\\", "Users", "folder"));
		System.out.println(fs.write("DemoForWritingOverFolder\\Users\\", "Hello World"));

		System.out.println("##########Demo For Negative scenario5 ##########");
		fs.createDrive("DemoWithExeption");
		System.out.println(fs.create("DemoWithExeption\\", "Folder1", "folder"));
		System.out.println(fs.create("DemoWithExeption\\Folder1\\", "report.txt", "text"));
		System.out.println(fs.create("DemoWithExeption\\Folder1\\report.txt\\", "Exception.txt", "text"));

	}

}
