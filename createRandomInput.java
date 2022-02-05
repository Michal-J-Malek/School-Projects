package CS116_Malek_Michal_FinalProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class createRandomInput {//customer IDs from companylist; productIDS EX:A1,B3,etc; order datses in 2019, orderAmounts less than 150; repeatedOrderPeriods are 1-30 days; OrderEndDates are in 2019
	private static ArrayList<String> randomOrders = new ArrayList<String>();
	private static ArrayList<String> companyList = new ArrayList<String>();
	
	private static int typeOfOrder;
	private static Scanner say;
	
	public static void main(String[] args) {
		System.out.println("Enter full file name");
		say = new Scanner(System.in);
		String nameOfFile = say.nextLine();
		inport(nameOfFile);
		
		System.out.println("How many lines of random orders");
		int lineAmount = say.nextInt();
		
		makeList(lineAmount);
		exportFile();
	}
	public static void exportFile() {
		String str ="";
		
		for(int i = 0; i<randomOrders.size();i++) {
			str = str + randomOrders.get(i)+"\n";
		}
		try (PrintWriter out = new PrintWriter("order.txt")){
			out.print(str);
		}catch (IOException e){
		}
	}
	public static void inport(String input) {
		Scanner file = null;
		try{
			File inputFile = new File (input);
			file = new Scanner (inputFile);
			while (file.hasNextLine()){
				String cont= file.nextLine();
				companyList.add(cont);
			}
		}
		catch ( FileNotFoundException fnfe ){
			System.out.println("Error: No File Found");
		}
		finally{
		file.close();
		}
	}
	
	public static LocalDate randomDate() {
		Random random = new Random();
		int minDay = (int) LocalDate.of(2019, 1, 1).toEpochDay();
		int maxDay = (int) LocalDate.of(2020, 12, 31).toEpochDay();
		long randomDay = minDay + random.nextInt(maxDay - minDay);
		LocalDate randomDate = LocalDate.ofEpochDay(randomDay);
		//String formattedDate = randomBirthDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
		return randomDate;
	}
	
	public static void type() {
		Random random = new Random();
		int oneOrTwo = random.nextInt(2);
		typeOfOrder = oneOrTwo;
	}
	
	public static String company() {
		Random random = new Random();
		int rando = random.nextInt(companyList.size());
		String randyComp = companyList.get(rando);
		return randyComp;
	}
	
	public static String product() {
		Random random = new Random();
		String randy = "";
		int rando = random.nextInt(11)+1;
		if(rando == 1) {
			randy = "A1";
		}if(rando == 2) {
			randy = "A2";
		}if(rando == 3) {
			randy = "A3";
		}if(rando == 4) {
			randy = "B1";
		}if(rando == 5) {
			randy = "B2";
		}if(rando == 6) {
			randy = "B3";
		}if(rando == 7) {
			randy = "C1";
		}if(rando == 8) {
			randy = "C2";
		}if(rando == 9) {
			randy = "C3";
		}if(rando == 10) {
			randy = "D1";
		}if(rando == 11) {
			randy = "D2";
		}if(rando == 12) {
			randy = "D3";
		}
		return randy;
	}
	public static String randomAmount() {
		Random random = new Random();
		int rando = random.nextInt(22)+3;
		String randy = Integer.toString(rando);
		return randy;
	}
	
	public static String randomPeriod(){
		Random random = new Random();
		int rando = random.nextInt(22)+3;
		String randy = Integer.toString(rando);
		return randy;
	}
	
	public static void makeList(int howManyRandomLines) {
		for(int i=0; i<howManyRandomLines; i++) {
			type();
			if(typeOfOrder == 0) {
				String str = "O, " + company() + ", "+ product()+", "+ randomDate()+", "+ randomAmount();
				randomOrders.add(str);
			}
			else{
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
				LocalDate randomDate1 = randomDate();
				LocalDate randomDate2 = randomDate();
				if(randomDate1.compareTo(randomDate2) > 0) {
					String formattedDate1 = randomDate1.format(formatter);
					String formattedDate2 = randomDate2.format(formatter);
					
					String str = "R, " + company() + ", "+ product()+", "+ formattedDate2+", "+ randomAmount()+", "+randomPeriod()+", " +formattedDate1;
					randomOrders.add(str);
				}else {
					String formattedDate1 = randomDate1.format(formatter);
					String formattedDate2 = randomDate2.format(formatter);
					
					String str = "R, " + company() + ", "+ product()+", "+ formattedDate1+", "+ randomAmount()+", "+randomPeriod()+", " +formattedDate2;
					randomOrders.add(str);
				}
			}	
		}
	}
}