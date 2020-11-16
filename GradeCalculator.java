import java.util.Scanner;
public class GradeCalculator {

	public static void main(String[] args) {
		int[] lab = new int[7];// array for all lab grades
		int[] pp = new int[8];// array for all practice problems grades
		double[] test = new double[3]; // array for all test grades
		int[] possiblepoints = new int[18];// array for completion of each assignment
		char let;

		System.out.println("WELCOME CISC 3130 GRADEING CALCULATOR ");// tell the user the programs
		System.out.println("Enter in all of your grades. Leave blank for ungraded work");
		getGrades(lab,pp,test);// ask the user for all the grades

		while(true)
		{

			System.out.println("Enter first letter of show add grades, edit a grade, show grades, calculate current grade, wanted letter grade:  ");

			int choice = getChar();
			switch(choice)
			{case 'a':// case for adding grades
				getGrades(lab,pp,test);
				break;
			case 'e':// case for editing grades
				editGrades(lab,pp,test);
				break;
			case's':// case for showing grades
				showGrades(lab,pp,test);
				break;
			case 'c':// case for showing current grade
				System.out.println("Your current grade is: "+CurrentGrade(lab,pp,test));
				break;
			case 'w':// case for showing assignment need for wanted letter grade
				System.out.println("Enter the letter grade you want");
				let = getChar();
				WantedGrade(lab,pp,test,let,possiblepoints);
				break;
			default:
				System.out.print("Invalid entry\n");
			}
		}
	}
	//Calculates the current grade of the user by adding all graded assiments
	static	public double CurrentGrade(int[] l,int[] p,double[] t) {
		double totalgrade=0;// the combine grade of all graded assiments
		int lgrade=0;//all grades for labs
		int pgrade = 0;// all grades for practice problems
		double tgrade=0;// all grades for tests
		int[] l2 = new int[l.length]; //dummy lab array
		int[] p2 = new int[p.length]; //dummy practice problem array
		double[] t2 = new double[t.length]; //dummy test array

		//adds all the lab grades together
		for(int i =0;i<l.length;i++) {
			l2[i]=l[i];
			if (l[i]==-1) {// checks for ungraded work
				l2[i]=0;//stores ungraded work as 0s in the dummy lab array

			}
			lgrade+=l2[i];
		}
		//adds all the practice problems grades together
		for(int i =0;i<p.length;i++) {
			p2[i]=p[i];
			if (p[i]==-1) {// checks for ungraded work
				p2[i]=0;//stores ungraded work as 0s in the dummy practice problem array

			}
			pgrade+=p2[i];
		}

		//adds all the test grades together
		for(int i =0;i<t.length;i++) {
			t2[i]=t[i];
			if (t[i]==-1) {// checks for ungraded work
				t2[i]=0;//stores ungraded work as 0s in the dummy test array

			}
			tgrade+=t2[i];
		}

		totalgrade=lgrade+pgrade+tgrade;//calulate the grade of all complete assiments

		return totalgrade;
	}

	//Calculates the wanted grade of the user by subtracting current grade from wanted grade
	//then dividing wanted grade by possible points left in the class and using that number
	// as a percentage need for assignment. Will calculate Labs and Practice problem with this 
	//percentage then rounding up to the possible points . (Lab grades are only 0,1,2 and 
	//Practice problem grades are 0,2,4,6). recalculates percentage needed for test and states grades need on test
	static	public void WantedGrade(int[] l,int[] pr,double[] t,char a,int[] po) {
		double want=0;// The grade wanted in the class
		double has=0;// The current grade in the class
		double percentNeeded=0;// The grade percentage needed on remaining work for the wanted grade
		double gotten = 0;//


		//case switch to change letter grade to its lowest possible number grade
		boolean flg = true;
		while(flg) {
			int Itemp =a;
			switch(Itemp)
			{
			case 'a':
				want= 90;
				flg = false;
				break;
			case 'b':
				want= 80;
				flg = false;
				break;
			case 'c':
				want= 70;
				flg = false;
				break;
			case 'd':
				want= 60;
				flg = false;
				break;
			case 'f':
				System.out.println("You can figure this one out by yourself");
				return;

			default :
				System.out.println("Enter a viable letter grade");
				Itemp = getChar();
			}


			want -= CurrentGrade( l, pr, t);// calls CurrentGrade to find the remaisdasdsadasds

			NotDone(l,pr,t,po);// calls NotDone to create a list of non grade work
			//Calculates the total possible points remaining in the class
			for(int i=0; i< po.length;i++) {
				if (i<15) {
					has+=po[i]*2;
				}else {
					has+=po[i];
				}
			}

			percentNeeded = want/has;


			//checks to see if the grade is possible to achieve
			if(percentNeeded>1) {
				System.out.println("Wanted grade is too high to reach");
				return;
			}
			if(percentNeeded<=0) {
				System.out.println("Wanted grade has been reach already");
				return;
			}


			for(int i=0; i< 15;i++) {

				//calculates the possible points that can be recieved from labs and practice problems rounded up 	
				double Dtemp =Math.ceil(po[i]*percentNeeded)*2;
				gotten+=Dtemp;

				if(Dtemp!=0) {//
					if (i<7) {
						System.out.println("You need to get "+Dtemp+" Lab "+(i+1));	// tells the user what grade they need for they're labs
					}else {
						System.out.println("You need to get "+Dtemp+" Practice Problem "+(i-6));	// tells the user what grade they need for they're practice problems
					}
					if (gotten >= want ) {// only print the assignment needed for grade
						return;
					}
				}
			}

			//recalculates percentNeeded for the test, as labs and practice problem ponits are rounded up
			percentNeeded = (want-gotten)/(po[15]+po[16]+po[17]);

			//checks to see if wanted grade has already been reached without test
			if (percentNeeded<=0) {
				return;
			}


			for(int i=15; i< 18;i++) {

				gotten+=Math.ceil(po[i]*percentNeeded);		
				if(po[i]!=0) {
					System.out.println("You need to get  "+po[i]*percentNeeded+" test"+(i-14));
				}
			}

		}
	}







	//checks assignment for non graded  work
	//score worth of labs and practice problems half so the can be rounded up correctly
	//So Wanted Grade can calculated need points by the full points, not half
	//Tests are not added as halfs,  score half a point is possible
	static	public void NotDone(int[] l,int[] pr,double[] t, int[] po) {
		for(int i=0;i<l.length;i++) {
			if (l[i]==-1) {//checks for non graded work in labs
				if (i!=6) {//specifics of the value of each lab
					po[i]=1;
				}else {
					po[i]=2;// lab6 is worth 4 points
				}
			}
		}

		// po[] index is added by 7 to be in the section where practice problem data is stored
		for(int i =0;i<pr.length;i++) {
			if (pr[i]==-1) {//checks for non graded work in labs
				if (i!=0) {//specifics of the value of each practice problem
					po[i+7]=3;
				}else {
					po[i+7]=1;//  practice problem1 is worth 2 points
				}
			}	
		}

		// po[] index is added by 15 to be in the section where test data is stored
		for(int i =0;i<t.length;i++) {
			if (t[i]==-1) {		//checks for non graded work in labs
				if (t[i]==-1) {
					if (i!=2) {//specifics of the value of each test
						po[i+15]=10;
					}else {
						po[i+15]=20;// test1/final is worth 20 points
					}
				}
			}
		}
	}

	// Ask the user for all of the grades,
	static	public void getGrades (int[] l,int[] pr,double[] t){
		System.out.println("Enter in your Lab grades");
		for(int i =0; i<l.length;i++) {
			System.out.println("Labs["+(i+1)+"]:");// display the current lab number asked for
			l[i] = getInt();
		}
		System.out.println("Enter in your Practice Problem grades");

		for(int i =0; i<pr.length;i++) {
			System.out.println("Pracrice Problem["+(i+1)+"]:");// display the current Pracrice Problem number asked for
			pr[i] = getInt();

		}
		System.out.println("Enter in your Test grades");
		for(int i =0; i<t.length;i++) {
			System.out.println("Test["+(i+1)+"]:");// display the current Test number asked for
			t[i] = getDouble();

		}
		showGrades(l,pr,t);// prints out all of the users grades in a chart
	}

	// edits one of the users grade to a new value
	static	public void editGrades (int[] l,int[] pr,double[] t){
		boolean flg = true;// flag for case switch
		int index;// assignment's number 
		while(flg) {
			System.out.println("Enter the first letter of the assiment you want to changer lab,practice problem, or test");
			int Itemp = getChar();
			switch(Itemp)
			{
			case 'l':// Lab case

				System.out.println("Enter the Lab number");
				index = getInt();
				if(index>7||index<1){//checks if a viable  lab is called
					System.out.println("Invalid Lab number");
					return;
				}
				System.out.println("Enter the new grade");
				l[index-1] = getInt();
				flg= false;
				break;

			case 'p':// Practice problem case
				System.out.println("Enter the Practice Problem number");
				index = getInt();
				if(index>8||index<1){//checks if a viable  Practice problem is called
					System.out.println("Invalid Practice Problem number");
					return;
				}
				System.out.println("Enter the new grade");
				pr[index-1] = getInt();
				flg= false;
				break;

			case 't':// test case
				System.out.println("Enter the Lab number");
				index = getInt();
				if(index>3||index<1){ //checks if a viable  test is called
					System.out.println("Invalid Test number");
					return;
				}
				System.out.println("Enter the new grade");

				t[index-1] = getDouble();
				flg= false;

				break;
			default:// invalid entry case
				System.out.println("Invalid entry");
			}
		}



	}

	//Prints out the users grade in a chart
	static	public void showGrades (int[] l,int[] pr,double[] t) {
		System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
		System.out.println("				Current Grades");
		System.out.println("assignment        |[1]| [2]| [3]| [4]| [5]| [6]| [7]| [8]");
		System.out.println("Practice Problems |  "+pr[0]+"|   "+pr[1]+"|   "+pr[2]+"|   "+pr[3]+"|   "+pr[4]+"|   "+pr[5]+"|   "+pr[6]+"|   "+pr[7]);
		System.out.println("Lab               |  "+l[0]+"|   "+l[1]+"|   "+l[2]+"|   "+l[3]+"|   "+l[4]+"|   "+l[5]+"|   "+l[6]+"|   ");
		System.out.println("Test              |"+(double)t[0]+"| "+(double)t[1]+"| "+(double)t[2]+"|   ");
		System.out.println("'''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''''");
	}

	//Returns the user input as a string variable
	public static String getString() {
		Scanner tempIn = new Scanner(System.in);
		String s = tempIn.nextLine();
		return s;
	}

	//Returns the user input as a char variable
	public static char getChar() {
		String s = getString();
		return s.charAt(0);		
	}

	//Returns the user input as a int variable
	public static int getInt() {
		String s = getString();
		if(s.contentEquals("")) {// catches if the input was empty
			return -1;
		}
		try {// catches if the input is not a int variable
			return Integer.parseInt(s);		
		}catch(Exception e) {
			System.out.println("Enter in a interger");
		}
		return getInt();
	}

	//Returns the user input as a double variable
	public static double getDouble() {
		String s = getString();
		if(s.contentEquals("")) {// catches if the input was empty
			return -1;
		}
		try {// catches if the input is not a double variable
			return Double.parseDouble(s);	
		}catch(Exception e) {
			System.out.println("Enter in a real number");
		}
		return getDouble();
	}
}




