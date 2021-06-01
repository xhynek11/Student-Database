package projekt;

import java.util.Scanner;

public class App {
	
	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	
	public static void main(String[] args) {	
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Databaze mojeDatabaze=new Databaze();
		
		int ID=Student.ID;
		Student info = null;
		String Jmeno;
		String Prijmeni;
		String Datum;
		int Znamka;
		int volba;
		
		//Vytvoøení databází studenti a znamky
		Connect con=new Connect();
		if(con.connect(mojeDatabaze.getUsername(),mojeDatabaze.getPassword())) {
			System.out.println("Pripojena");
			con.createTable();
			con.disconnect();
		}
		ConnectZnamky conZ=new ConnectZnamky();
		if(conZ.connect(mojeDatabaze.getUsername(),mojeDatabaze.getPassword())) {
			System.out.println("Pripojena");
			conZ.createTable();
			conZ.disconnect();
		}
		
		//automatické zadání pár studentù se známkama do databaze 
		mojeDatabaze.setStudentKom(Student.ID,"Vojta","Hynek","21.7.1999");
		mojeDatabaze.prvkyDatabaze.get(0).ListZnamek.add(1);
		mojeDatabaze.setStudentTech(Student.ID,"Ladislav","Mateju","10.2.1997");
		mojeDatabaze.prvkyDatabaze.get(1).ListZnamek.add(1);
		mojeDatabaze.prvkyDatabaze.get(1).ListZnamek.add(4);
		mojeDatabaze.setStudentTech(Student.ID,"Tonda","Seriv","11.2.1995");
		mojeDatabaze.prvkyDatabaze.get(2).ListZnamek.add(4);
		mojeDatabaze.setStudentHum(Student.ID,"David","Alitr","17.11.2000");
		mojeDatabaze.prvkyDatabaze.get(3).ListZnamek.add(2);
		mojeDatabaze.prvkyDatabaze.get(3).ListZnamek.add(1);
		
		boolean run=true;
		while(run)
		{
			//Vypis rozcestníku ovládání databaze
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("0 .. KONEC");
			System.out.println("1 .. vlozeni noveho studenta");
			System.out.println("2 .. vypis studenta podle ID");
			System.out.println("3 .. pridej studentovi známku podle ID");
			System.out.println("4 .. odstran studenta z databaze podle ID");
			System.out.println("5 .. spust studentovu dovednost podle ID");
			System.out.println("6 .. vypis vsechni studenty serazene podle prijmeni");
			System.out.println("7 .. vypis prumer studentu humanitniho a technickeho oboru");
			System.out.println("8 .. vypis pocet studentu v jednotlivich oborech");
			System.out.println("9 .. uloz vse do SQL databze");
			System.out.println("10 .. nacti vse z SQL databaze");
			System.out.println("11 .. vymaz studenta z SQL databaze");
			System.out.println("12 .. pridej studenta podle Jmena a Prijmeni z SQL databaze");
			
			volba=pouzeCelaCisla(sc);
			switch(volba)
			{
				case 0:
					run=false;
					break;
				case 1:
					//Vybrání skupiny studenta a pridání studenta do databaze pomocí metody zadejStudenta
					System.out.println("Vyberte jakeho studenta chcete pridat");
					System.out.println("1 .. Student Humanitniho oboru");
					System.out.println("2 .. Student Technického oboru");
					System.out.println("3 .. Student Kombinovaného oboru");
					volba=pouzeCelaCisla(sc);
					mojeDatabaze.zadejStudenta(volba);
					break;
				case 2:
					//zadání ID studenta kterého chceme vypsat, uložení daného objektu studenta do promìné info a následné zavolání getterù aby jsme vypsali informace o studentovi
					System.out.println("Zadejte ID studenta");
					ID=pouzeCelaCisla(sc);
					info=mojeDatabaze.getStudent(ID);
					if (info!=null)
						System.out.println("Jmeno: " + info.getJmeno() + " Prijmeni: " + info.getPrijmeni() + " DatumNarozeni: " + info.getDatumNarozeni()+" Prumer: "+ info.getPrumer());
					else
						System.out.println("Vybrana polozka neexistuje");
					break;
				case 3:
					//zadaní ID studenta kterému chceme pøipsat známku a zavolání metody setZnamka(jednotkový test)
					System.out.println("Zadejte ID studenta");
					ID=pouzeCelaCisla(sc);
					if(ID>Student.ID) {
						System.out.println("Student se zadaným ID neexistuje");
						break;
					}
					System.out.println("Zadejte znamku studentovi");
					Znamka=pouzeCelaCisla(sc);
					if(!mojeDatabaze.setZnamka(ID, Znamka))
						System.out.println("Známka nebyla pridana");
					break;
				case 4:
					//zadáním ID studenta vymazání studenta pomocí metody deleteStudent(jednotkový test)
					System.out.println("Zadejte ID studenta");
					ID=pouzeCelaCisla(sc);
					if(ID>Student.ID) {
						System.out.println("Student se zadaným ID neexistuje");
						break;
					}
					if(!mojeDatabaze.deleteStudent(ID))
						System.out.println("Student nebyl odstranen");
					break;
				case 5:
					//Zadáním ID studneta spustení jeho dovednosti pomocí metody spustDovednost
					System.out.println("Zadejte ID studenta");
					ID=pouzeCelaCisla(sc);
					mojeDatabaze.spustDovednost(ID);
					break;
				case 6:
					//Vypsání seznamu studentù seøazeného podle pøijmení pomocí metody vypisPodlePrijmeni
					mojeDatabaze.vypisPodlePrijmeni();
					break;
				case 7:
					//Vypsání prumìru studentù hum. a tech. oboru zavolanim metod prumerHum a prumerTech
					System.out.println("Prumer studentu humanitniho oboru je:"+mojeDatabaze.prumerHum());
					System.out.println("Prumer studentu technickeho oboru je:"+mojeDatabaze.prumerTech());
					break;
				case 8:
					//Vypsání poètu studentù v jednotlivých skupinách pomocí metod pocetHum, pocetTech a pocetKom
					System.out.println("Celkovy pocet studentu je: "+mojeDatabaze.prvkyDatabaze.size());
					System.out.println("Pocet studentù humanitního oboru je: "+mojeDatabaze.pocetHum());
					System.out.println("Pocet studentù technickeho oboru je: "+mojeDatabaze.pocetTech());
					System.out.println("Pocet studentù kombinovaneho oboru je: "+mojeDatabaze.pocetKom());
					break;
				case 9:
					//Uložení všech studentù z Hashmapy do SQL databáze studenti pomocí metody ulozVseDoDatabaze
					mojeDatabaze.ulozVseDoDatabaze();
					break;
				case 10:
					//Naètení všech studentù z SQL databaze do hashmapy pomocí metody nactiVseZDatabaze
					mojeDatabaze.nactiVseZDatabaze();
					break;
				case 11:
					//Vymazaní studenta z SQL databaze pomocí metody vymazStudentaZSql
					volba=pouzeCelaCisla(sc);
					mojeDatabaze.vymazStudentaZSql(volba);
					break;
				case 12:
					//Naètení studenta z SQL databaze podle Jmena a Prijmení pomocí metody nactiStudnetaPodleJmePri
					Jmeno=sc.next();
					Prijmeni=sc.next();
					System.out.println(mojeDatabaze.nactiStudentaPodleJmePri(Jmeno, Prijmeni));
					break;
				default:
					//pokud zada uzivatel cislo mimo rozsah switche vypise se toto
					System.out.println("Zadejte cislo v pozadovanem rozsahu");
					break;
			
		}
		
	}

}
}