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
		//vytvo�en� hashmapy do kter� se ukl�daj� studenti p�idan� do datab�ze
		prvkyDatabaze=new HashMap<Integer,Student>();
	}
	//slou�� k p�id�n� studenta technologi� do hashmapy pomoc� implementovan� metody put
	public boolean setStudentTech(int ID,String Jmeno, String Prijmeni, String Datum)
	{
		if (prvkyDatabaze.put(ID,new StudentTech(Jmeno,Prijmeni,Datum))==null)
			return true;
		else
			return false;
	}
	//slou�� k p�id�n� studenta humanitn�ho oboru do hashmapy pomoc� implementovan� metody put
	public boolean setStudentHum(int ID,String Jmeno, String Prijmeni, String Datum)
	{
		if (prvkyDatabaze.put(ID,new StudentHum(Jmeno,Prijmeni,Datum))==null)
			return true;
		else
			return false;
	}
	//slou�� k p�id�n� studenta kombinovan�ho oboru do hashmapy pomoc� implementovan� metody put
	public boolean setStudentKom(int ID,String Jmeno, String Prijmeni, String Datum)
	{
		if (prvkyDatabaze.put(ID,new StudentKom(Jmeno,Prijmeni,Datum))==null)
			return true;
		else
			return false;
	}
	//slou�� k vymyz�n� studenta z hashmapy podle ID pomoc� implementovan� metody remove
	public boolean deleteStudent(int ID)
	{
		if (prvkyDatabaze.remove(ID)==null)
			return false;
		else
			return true;
	}
	//getter kter� vrac� objekt studenta ulo�en� pod p��slu�n�m ID, pomoc� implementovan� metody get
	public Student getStudent(int ID) 
	{
		return prvkyDatabaze.get(ID);
	}
	//setter slou��c� k zad�n� zn�mky pr�slu�n�ho studneta podle ID, pomoc� metody addZnamka
	public boolean setZnamka(int ID,int Znamka) {
		if(!prvkyDatabaze.get(ID).addZnamka(Znamka)) {
			return false;
		}
			return true;
	}
	//se�te pr�m�ry zn�mek v�ech student� hum. oboru a ud�l� jej�ch pr�m�r
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
	//se�te pr�m�ry zn�mek v�ech student� tech. oboru a ud�l� jej�ch pr�m�r
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
	//secte v�echny studenty hum. oboru a vr�t� jejich po�et
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
	//secte v�echny studenty tech. oboru a vr�t� jejich po�et
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
	//secte v�echny studenty kombinovaneho oboru a vr�t� jejich po�et
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
	//slou�� pro na�ten� p�ihla�ovac�ho jm�na k datab�zi ze souboru udaje.txt
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
	//slou�� pro na�ten� p�ihla�ovac�ho hesla k datab�zi ze souboru udaje.txt
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
	//slou�� k zaps�n� studenta do sql databaze podle ID pomoc� metody insertRecord
	/*ve fin�ln� verzi projektu nen� vyu��vn� , slou�ila jako pomoc p�i tvorb� projektu
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
	//ulo�� v�echny studenty z Hashmapy do SQL datab�ze studenti a v�echny zn�mky student� do SQL databaze zn�mky, pomoc� metody insertRecord a vyp�e kolik student� bylo ulo�eno
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
	//na�te v�echny studenty z SQL datab�ze do Hashmapy pomoc� metody selectAll kter� v�echny studenty na�te do listu v objektu con t��dy Connect
	//a postupn� pokud nejsou obsa�eny v hashmap� tak je tam nahraje pomoc� implementovan� metody put
	//d�le pomoc� metody selectAll na�te v�echny znamky z SQL databaze znamky a p�irad� je jednotliv�m student�m, to se d�je sou�asn� , pokud student nen� 
	//v Hashmap� vytvo�� se v n� nov� objekt s p��slu�n�mi �daji a vlo�� se doo n�j p��slu�n� zn�mky
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
	//podle Jm�na a p��jmen� na�te studenta z SQL databaze do hashmapy, funguje podobn� jako metoda nactiVseZDatabaze
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
	//vyma�e studenta z SQL databaze podle ID, pomoc� metody delete
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
	//vyp�e studenty se�azen� podle hashmapy, jeliko� hashmapa se obt�n� t��d� nejd��ve se nahraj� v�ichni studenti do listu ,ten se set��d� podle metody sort a ten se pak vyp�e
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
	//spou�t� jednotliv� dovednosti podle toho do jak� t��dy student pat��,u studenta kombinovan�ho oboru si m��eme je�t� vybrat jak� dovednost se spust�
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
	//metoda kter� vytvo�� po�adovan�ho studenta s n�mi zadan�mi parametry a p�id� do hashmapy, pomoc� metody setStudentTech/Hum/Kom
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
				System.out.println("nepoda�ilo se studenta zapsat do datab�ze");
			break;
		case 2:
			System.out.println("Zadejte jmeno studenta, prijmeni studenta, datum narozeni studneta");
			Jmeno=sc.next();
			Prijmeni=sc.next();
			Datum=sc.next();
						
			if (!setStudentTech(Student.ID,Jmeno,Prijmeni,Datum))
				System.out.println("nepoda�ilo se studenta zapsat do datab�ze");
			break;
		case 3:
			System.out.println("Zadejte jmeno studenta, prijmeni studenta, datum narozeni studneta");
			Jmeno=sc.next();
			Prijmeni=sc.next();
			Datum=sc.next();
						
			if (!setStudentKom(Student.ID,Jmeno,Prijmeni,Datum))
				System.out.println("nepoda�ilo se studenta zapsat do datab�ze");
			break;
		}
	}

}
