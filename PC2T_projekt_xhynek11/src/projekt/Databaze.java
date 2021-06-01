package projekt;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class Databaze {
	 Map<Integer,Student> prvkyDatabaze;
	 
	Databaze(){
		//vytvoøení hashmapy do které se ukládají studenti pøidaní do databáze
		prvkyDatabaze=new HashMap<Integer,Student>();
	}
	//slouží k pøidání studenta technologií do hashmapy pomocí implementované metody put
	public boolean setStudentTech(int ID,String Jmeno, String Prijmeni, String Datum)
	{
		if (prvkyDatabaze.put(ID,new StudentTech(Jmeno,Prijmeni,Datum))==null)
			return true;
		else
			return false;
	}
	//slouží k pøidání studenta humanitního oboru do hashmapy pomocí implementované metody put
	public boolean setStudentHum(int ID,String Jmeno, String Prijmeni, String Datum)
	{
		if (prvkyDatabaze.put(ID,new StudentHum(Jmeno,Prijmeni,Datum))==null)
			return true;
		else
			return false;
	}
	//slouží k pøidání studenta kombinovaného oboru do hashmapy pomocí implementované metody put
	public boolean setStudentKom(int ID,String Jmeno, String Prijmeni, String Datum)
	{
		if (prvkyDatabaze.put(ID,new StudentKom(Jmeno,Prijmeni,Datum))==null)
			return true;
		else
			return false;
	}
	//slouží k vymyzání studenta z hashmapy podle ID pomocí implementované metody remove
	public boolean deleteStudent(int ID)
	{
		if (prvkyDatabaze.remove(ID)==null)
			return false;
		else
			return true;
	}
	//getter který vrací objekt studenta uložený pod pøíslušným ID, pomocí implementované metody get
	public Student getStudent(int ID) 
	{
		return prvkyDatabaze.get(ID);
	}
	//setter sloužící k zadání známky príslušného studneta podle ID, pomocí metody addZnamka
	public boolean setZnamka(int ID,int Znamka) {
		if(!prvkyDatabaze.get(ID).addZnamka(Znamka)) {
			return false;
		}
			return true;
	}
	//seète prùmìry známek všech studentù hum. oboru a udìlá jejích prùmìr
	public float prumerHum() {
		int pocet=0;
		float soucet=0;
		Student info;
		
		for(int i=0;i<prvkyDatabaze.size();i++) {
			info=getStudent(i);
			if(info instanceof StudentHum) {
				pocet++;
				soucet=soucet+prvkyDatabaze.get(i).getPrumer();
			}
		}
		return soucet/pocet;
	}
	//seète prùmìry známek všech studentù tech. oboru a udìlá jejích prùmìr
	public float prumerTech() {
		int pocet=0;
		float soucet=0;
		Student info;
	
		for(int i=0;i<prvkyDatabaze.size();i++) {
			info=getStudent(i);
			if(info instanceof StudentTech) {
				pocet++;
				soucet=soucet+prvkyDatabaze.get(i).getPrumer();
			}
		}
		return soucet/pocet;
	}
	//secte všechny studenty hum. oboru a vrátí jejich poèet
	public int pocetHum() {
		int pocet=0;
		Student info;
		for(int i=0;i<prvkyDatabaze.size();i++) {
			info=getStudent(i);
			if(info instanceof StudentHum)
				pocet++;
		}
		return pocet;
	}
	//secte všechny studenty tech. oboru a vrátí jejich poèet
	public int pocetTech() {
		int pocet=0;
		Student info;
		for(int i=0;i<prvkyDatabaze.size();i++) {
			info=getStudent(i);
			if(info instanceof StudentTech)
				pocet++;
		}
		return pocet;
	}
	//secte všechny studenty kombinovaneho oboru a vrátí jejich poèet
	public int pocetKom() {
		int pocet=0;
		Student info;
		for(int i=0;i<prvkyDatabaze.size();i++) {
			info=getStudent(i);
			if(info instanceof StudentKom)
				pocet++;
		}
		return pocet;
	}
	//slouží pro naètení pøihlašovacího jména k databázi ze souboru udaje.txt
	public String getUsername() {
		FileReader fr=null;
		BufferedReader in=null;
		try {
			fr = new FileReader("udaje.txt");
			in = new BufferedReader(fr);
			String radek=in.readLine();
			String oddelovac = "[ ]+";
			String[] castiTextu = radek.split(oddelovac);
			return castiTextu[0];
		}
		catch(IOException e) {
			System.out.println("Soubor nejde otevrit");
			return "";
		}
	}
	//slouží pro naètení pøihlašovacího hesla k databázi ze souboru udaje.txt
	public String getPassword() {
		FileReader fr=null;
		BufferedReader in=null;
		try {
			fr = new FileReader("udaje.txt");
			in = new BufferedReader(fr);
			String radek=in.readLine();
			String oddelovac = "[ ]+";
			String[] castiTextu = radek.split(oddelovac);
			return castiTextu[1];
		}
		catch(IOException e) {
			System.out.println("Soubor nejde otevrit");
			return "";
		}
	}
	//slouží k zapsání studenta do sql databaze podle ID pomocí metody insertRecord
	/*ve finální verzi projektu není využívná , sloužila jako pomoc pøi tvorbì projektu
	public void zapisDoDatabaze(int ID) {
		Connect con=new Connect();
		ConnectZnamky conZ=new ConnectZnamky();
		
		Student info;
		info=prvkyDatabaze.get(ID);
		int pocet=info.ListZnamek.size();
		con.connect(getUsername(),getPassword());
		conZ.connect(getUsername(),getPassword());
			con.insertRecord(info.getIDns(), info.getJmeno(), info.getPrijmeni(), info.getDatumNarozeniSql(), info.getobor());
			for(int i=0;i<pocet;i++) {
				conZ.insertRecord(info.getIDns(), info.ListZnamek.get(i));
			}
			con.disconnect();
	}*/
	//uloží všechny studenty z Hashmapy do SQL databáze studenti a všechny známky studentù do SQL databaze známky, pomocí metody insertRecord a vypíše kolik studentù bylo uloženo
	public void ulozVseDoDatabaze() {
		Connect con=new Connect();
		ConnectZnamky conZ=new ConnectZnamky();
		con.connect(getUsername(),getPassword());
		conZ.connect(getUsername(),getPassword());
		
		Student info;
		int pridanoPrvku=0;
		int pocet=prvkyDatabaze.size();
		int pocetZnamek;
		for(int i=0;i<pocet;i++) {
			info=prvkyDatabaze.get(i);
			pocetZnamek=prvkyDatabaze.get(i).ListZnamek.size();
			if(con.insertRecord(info.getIDns(), info.getJmeno(), info.getPrijmeni(), info.getDatumNarozeniSql(), info.getobor())) {
				pridanoPrvku++;
			}
			conZ.delete(info.getIDns());
			for(int j=0;j<pocetZnamek;j++) {
				conZ.insertRecord(info.getIDns(), info.ListZnamek.get(j));
			}
		}
		con.disconnect();
		conZ.disconnect();
		System.out.println("Do SQL databaze bylo pridano "+pridanoPrvku+" prvku.");
	}
	//naète všechny studenty z SQL databáze do Hashmapy pomocí metody selectAll která všechny studenty naète do listu v objektu con tøídy Connect
	//a postupnì pokud nejsou obsaženy v hashmapì tak je tam nahraje pomocí implementované metody put
	//dále pomocí metody selectAll naète všechny znamky z SQL databaze znamky a pøiradí je jednotlivím studentùm, to se dìje souèasnì , pokud student není 
	//v Hashmapì vytvoøí se v ní nový objekt s pøíslušnými údaji a vloží se doo nìj pøíslušné známky
	public void nactiVseZDatabaze() {
		Connect con=new Connect();
		ConnectZnamky conZ=new ConnectZnamky();
		con.connect(getUsername(),getPassword());
		conZ.connect(getUsername(),getPassword());
		con.selectAll();
		
		Student info;
		int index = con.arrStudenti.size();
		int nactenoPrvku=0,pocetZnamek=0;
		for(int i=0;i<index;i++) {
			info=con.arrStudenti.get(i);
			if(!prvkyDatabaze.containsKey(info.getIDns())) {
				if(info instanceof StudentHum) {
					prvkyDatabaze.put(info.getIDns(), new StudentHum(info.getJmeno(),info.getPrijmeni(),info.getDatumNarozeni()));
					conZ.selectAll(info.getIDns());
					pocetZnamek=conZ.arrZnamky.size();
					for(int j=0;j<pocetZnamek;j++) {
						prvkyDatabaze.get(info.getIDns()).ListZnamek.add(conZ.arrZnamky.get(j));
					}
				}
				if(info instanceof StudentTech) {
					prvkyDatabaze.put(info.getIDns(), new StudentTech(info.getJmeno(),info.getPrijmeni(),info.getDatumNarozeni()));
					conZ.selectAll(info.getIDns());
					pocetZnamek=conZ.arrZnamky.size();
					for(int j=0;j<pocetZnamek;j++) {
						prvkyDatabaze.get(info.getIDns()).ListZnamek.add(conZ.arrZnamky.get(j));
					}
				}
				if(info instanceof StudentKom) {
					prvkyDatabaze.put(info.getIDns(), new StudentKom(info.getJmeno(),info.getPrijmeni(),info.getDatumNarozeni()));
					conZ.selectAll(info.getIDns());
					pocetZnamek=conZ.arrZnamky.size();
					for(int j=0;j<pocetZnamek;j++) {
						prvkyDatabaze.get(info.getIDns()).ListZnamek.add(conZ.arrZnamky.get(j));
					}
				}
				nactenoPrvku++;
			}
			
		}
		System.out.println("Z SQL databaze bylo nacteno "+nactenoPrvku+" prvku.");
		con.disconnect();
		conZ.disconnect();
	}
	//podle Jména a pøíjmení naète studenta z SQL databaze do hashmapy, funguje podobnì jako metoda nactiVseZDatabaze
	public String nactiStudentaPodleJmePri(String Jmeno,String Prijmeni) {
		Connect con=new Connect();
		ConnectZnamky conZ=new ConnectZnamky();
		con.connect(getUsername(),getPassword());
		conZ.connect(getUsername(),getPassword());
		con.nactiStudentaPodleJmePri(Jmeno, Prijmeni);
		int pocetZnamek=0;
		Student info;
		info=con.arrStudenti.get(0);
		if(!prvkyDatabaze.containsKey(info.getIDns())) {
			if(info instanceof StudentHum) {
				prvkyDatabaze.put(info.getIDns(), new StudentHum(info.getJmeno(),info.getPrijmeni(),info.getDatumNarozeni()));
				conZ.selectAll(info.getIDns());
				pocetZnamek=conZ.arrZnamky.size();
				for(int j=0;j<pocetZnamek;j++) {
					prvkyDatabaze.get(info.getIDns()).ListZnamek.add(conZ.arrZnamky.get(j));
				}
			}
			if(info instanceof StudentTech) {
				prvkyDatabaze.put(info.getIDns(), new StudentTech(info.getJmeno(),info.getPrijmeni(),info.getDatumNarozeni()));
				conZ.selectAll(info.getIDns());
				pocetZnamek=conZ.arrZnamky.size();
				for(int j=0;j<pocetZnamek;j++) {
					prvkyDatabaze.get(info.getIDns()).ListZnamek.add(conZ.arrZnamky.get(j));
				}
			}
			if(info instanceof StudentKom) {
				prvkyDatabaze.put(info.getIDns(), new StudentKom(info.getJmeno(),info.getPrijmeni(),info.getDatumNarozeni()));
				conZ.selectAll(info.getIDns());
				pocetZnamek=conZ.arrZnamky.size();
				for(int j=0;j<pocetZnamek;j++) {
					prvkyDatabaze.get(info.getIDns()).ListZnamek.add(conZ.arrZnamky.get(j));
				}
			}
			return "Student byl pridan";
		}
		return "Student nebyl pridan";
	}
	//vymaže studenta z SQL databaze podle ID, pomocí metody delete
	public void vymazStudentaZSql(int id) {
		Connect con=new Connect();
		ConnectZnamky conZ=new ConnectZnamky();
		con.connect(getUsername(),getPassword());
		conZ.connect(getUsername(),getPassword());
		
		con.delete(id);
		conZ.delete(id);
		con.disconnect();
		conZ.disconnect();
	}
	//vypíše studenty seøazené podle hashmapy, jelikož hashmapa se obtížnì tøídí nejdøíve se nahrají všichni studenti do listu ,ten se setøídí podle metody sort a ten se pak vypíše
	public void vypisPodlePrijmeni() {
		 Student info;
		 ArrayList<Student> arr=new ArrayList<Student>();
		 int pocet = prvkyDatabaze.size()-1;
		 for(int i=0;i<=pocet;i++) {
			 arr.add(getStudent(i));
		 }
		 arr.sort(null);
		
		 Iterator<Student> iter = arr.iterator();
			while (iter.hasNext()) {
			info= iter.next();
			System.out.println("ID: "+info.getIDns()+", Jmeno: "+info.getJmeno()+", Prijmeni: "+info.getPrijmeni()+", Rok narozeni: "+info.getRokNarozeni()+", Studijni prumer: "+info.getPrumer());
			}
	}
	//spouští jednotlivé dovednosti podle toho do jaké tøídy student patøí,u studenta kombinovaného oboru si mùžeme ještì vybrat jaká dovednost se spustí
	public void spustDovednost(int ID) {
		Scanner sc=new Scanner(System.in);
		Student info;
		int volba;
		info=getStudent(ID);
		
		if (info!=null){
			if(info instanceof StudentHum) {
				System.out.println(((StudentHum) info).dovednostHum());
			}
			if(info instanceof StudentTech) {
				System.out.println(((StudentTech) info).dovednostTech());
			}
			if(info instanceof StudentKom) {
				System.out.println("Vyberte dovednost studneta:");
				System.out.println("1 .. Dovednost studenta humanitniho oboru");
				System.out.println("2 .. Dovednost studenta technickeho oboru");
				volba=sc.nextInt();
				switch(volba) {
				case 1:
					System.out.println(((StudentKom) info).dovednostHum());
					break;
				case 2:
					System.out.println(((StudentKom) info).dovednostTech());
					break;
				default:
					System.out.println("Spatne zadane cislo");
					break;
				}
			}
			
		}	
		else
			System.out.println("Vybrana polozka neexistuje");
	}
	//metoda která vytvoøí požadovaného studenta s námi zadanými parametry a pøidá do hashmapy, pomocí metody setStudentTech/Hum/Kom
	public void zadejStudenta(int volba) {
		Scanner sc=new Scanner(System.in);
		String Jmeno,Prijmeni,Datum;
		
		switch(volba) {
		case 1:
			System.out.println("Zadejte jmeno studenta, prijmeni studenta, datum narozeni studneta");
			Jmeno=sc.next();
			Prijmeni=sc.next();
			Datum=sc.next();
						
			if (!setStudentHum(Student.ID,Jmeno,Prijmeni,Datum))
				System.out.println("nepodaøilo se studenta zapsat do databáze");
			break;
		case 2:
			System.out.println("Zadejte jmeno studenta, prijmeni studenta, datum narozeni studneta");
			Jmeno=sc.next();
			Prijmeni=sc.next();
			Datum=sc.next();
						
			if (!setStudentTech(Student.ID,Jmeno,Prijmeni,Datum))
				System.out.println("nepodaøilo se studenta zapsat do databáze");
			break;
		case 3:
			System.out.println("Zadejte jmeno studenta, prijmeni studenta, datum narozeni studneta");
			Jmeno=sc.next();
			Prijmeni=sc.next();
			Datum=sc.next();
						
			if (!setStudentKom(Student.ID,Jmeno,Prijmeni,Datum))
				System.out.println("nepodaøilo se studenta zapsat do databáze");
			break;
		}
	}

}
