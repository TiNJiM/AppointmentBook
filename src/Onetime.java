import java.io.*;
import java.util.*;
public class Onetime extends Appointment{
	public Onetime(String desc, int year, int month, int day){
		super(desc, year, month, day);
	}
	public boolean occursOn(int year, int month, int day){
		if(year == this.apptYear && month == this.apptMonth && day == this.apptDay){
			return true;
		}
		else{
			return false;
		}
	}
	public void save() throws IOException{
		FileWriter fw = new FileWriter ("U:\\Workshop\\AppointmentBook\\Appointments.txt");
		PrintWriter output = new PrintWriter(fw);
		output.println("Onetime");
		output.println(apptDesc);
		output.println(apptYear + " " + apptMonth + " " + apptDay);
		output.close();
		fw.close();
	}
	public static Appointment load() throws IOException{
		Scanner sf = new Scanner(new File("U:\\Workshop\\AppointmentBook\\Appointments.txt"));
		int maxIndx = -1;//save uses multiple lines
		String text[] = new String[10];//there should never be more than this
		while(sf.hasNext()){
			maxIndx++;
			text[maxIndx] = sf.nextLine();
		}
		sf.close();
		String skip = "";
		String desc = "";
		int year = 0;
		int month = 0;
		int day = 0;
		for(int j = 0; j<=maxIndx;j++){ //again, there should only be one line actually read here
			Scanner sc = new Scanner(text[j]);
			while (sc.hasNext()){
				if(j == 0){
					skip = skip.concat(sc.next());
				}
				if(j == 1){
					desc = desc.concat(sc.next() + " ");
				}
				if(j == 2){
					year = sc.nextInt();
					month = sc.nextInt();
					day = sc.nextInt();
				}
			}
			sc.close();
		}
		Appointment ans = new Onetime(desc, year, month, day);
		return ans;
	}
}
