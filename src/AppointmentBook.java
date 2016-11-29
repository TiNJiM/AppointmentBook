import java.util.*;
import java.io.*;
public class AppointmentBook {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		Scanner n1 = new Scanner(System.in);
		List<Appointment>appointmentBook = new ArrayList<>();
		System.out.println("Tin Jim's Appointment Adder™");
		int newApptType;
		String newApptDesc = "";
		int newApptYear;
		int newApptMonth;
		int newApptDay;
		int saveCheck;
		do{
			System.out.print("Please enter:\n\"1\" for a one-time appointment.\n\"2\" for a monthly appointment.\n\"3\" for a daily appointment.\n\"0\" to quit.");
			newApptType = n1.nextInt();
			if(newApptType == 0){
				break;
			}
			else if(!(newApptType == 0 || newApptType == 1 || newApptType == 2 || newApptType == 3)){
				System.out.println("Invalid entry, please try again.");
				continue;
			}	
			Scanner n2 = new Scanner(System.in);
			System.out.print("Please write a description of the appointment: ");
			newApptDesc = n2.nextLine();
			Scanner n3 = new Scanner(System.in);
			System.out.print("Please enter the year of the appointment: ");
			newApptYear = n3.nextInt();
			System.out.print("Please enter the month of the appointment (in number format): ");
			newApptMonth = n3.nextInt();
			System.out.print("Please enter the day of the appointment (in number format)");
			newApptDay = n3.nextInt();
			System.out.print("If you would like to save this appointment to a file, enter \"1\", else enter\"0\"");
			saveCheck = n3.nextInt();
			if(newApptType == 1){
				Appointment nextOneTime = new Onetime(newApptDesc, newApptYear, newApptMonth, newApptDay);
				appointmentBook.add(nextOneTime);
				if(saveCheck == 1){
					nextOneTime.save();
				}
			}
			else if(newApptType == 2){
				Appointment nextMonthly = new Monthly(newApptDesc, newApptYear, newApptMonth, newApptDay);
				appointmentBook.add(nextMonthly);
				if(saveCheck == 1){
					nextMonthly.save();
				}
			}
			else{
				Appointment nextDaily = new Daily(newApptDesc, newApptYear, newApptMonth, newApptDay);
				appointmentBook.add(nextDaily);
				if(saveCheck == 1){
					nextDaily.save();
				}
			}
		}while(!(newApptType == 0));
		int checkSave;
		int checkYear;
		int checkMonth;
		int checkDay;
		String testSavedType;
		System.out.println("Tin Jim's Appointment Checker™");
		do{
			Scanner n4 = new Scanner(System.in);
			System.out.print("If you wish to exit, enter 0.\nIf you wish to load the saved appointment, enter 1.\nIf you wish to continue, press 2. ");
			checkSave = n4.nextInt();
			if(checkSave == 0){
				break;
			}
			else if (checkSave == 1){
				Scanner sf = new Scanner(new File("U:\\Workshop\\AppointmentBook\\Appointments.txt"));
				int maxIndx = -1;//save uses multiple lines
				String text[] = new String[10];//there should never be more than this
				while(sf.hasNext()){ //this first part is stolen from the method bc I didn't need to change it
					maxIndx++;
					text[maxIndx] = sf.nextLine();
				}
				sf.close();
				Scanner sc = new Scanner(text[0]);//I only need the first line
				testSavedType = sc.next();
				sc.close();
				if(testSavedType.equals("Onetime")){
					appointmentBook.add(Onetime.load());
				}
				else if(testSavedType.equals("Monthly")){
					appointmentBook.add(Monthly.load());
				}
				else{
					appointmentBook.add(Daily.load());
				}
				continue;
			}
			else if(checkSave == 2){
				
			}
			else{
				System.out.println("Invalid entry, please try again. ");
				continue;
			}
			System.out.print("Please enter year: ");
			checkYear = n4.nextInt();
			System.out.print("Please enter month (in number format): ");
			checkMonth = n4.nextInt();
			System.out.print("Pleade enter day (in number format) ");
			checkDay = n4.nextInt();
			System.out.println("Your appointments: ");
			for(int x = 0; x<appointmentBook.size(); x++){
				Appointment checkAppt = appointmentBook.get(x);
				if(checkAppt.occursOn(checkYear, checkMonth, checkDay) == true){
					System.out.println (checkAppt.apptDesc);
				}
			}
		}while(!(checkSave == 0));

	}
}
