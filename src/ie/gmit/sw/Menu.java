/*
 * Author: Conor McGrath
 * 
 */
package ie.gmit.sw;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class Menu.
 */
public class Menu {

	/**
	 * Show.
	 *
	 * @throws InterruptedException the interrupted exception
	 */
	//Show Method
	public void show() throws InterruptedException {	
		String fileNameA, fileNameB;
		int shingles;
		
		Scanner console = new Scanner(System.in);
		
		//source path of files must be entered
		System.out.print("\nEnter Source Path and File Name: ");
		fileNameA = console.nextLine();
		System.out.print("\nEnter Source Path and File Name: ");
		fileNameB = console.nextLine();
		
		//prompt user to enter number of shingles
		System.out.print("\nEnter Number of Shingles: ");
		shingles = console.nextInt();
		
		// create a new instance of the class Launcher
		new Launcher(fileNameA,fileNameB,100,shingles);
		console.close();
	}
}//Menu