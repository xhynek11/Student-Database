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
		
		//Vytvo�en� datab�z� studenti a znamky
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
		
		//automatick� zad�n� p�r student� se zn�mkama do databaze 
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
			//Vypis rozcestn�ku ovl�d�n� databaze
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("0 .. KONEC");
			System.out.println("1 .. vlozeni noveho studenta");
			System.out.println("2 .. vypis studenta podle ID");
			System.out.println("3 .. pridej studentovi zn�mku podle ID");
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
					//Vybr�n� skupiny studenta a prid�n� studenta do databaze pomoc� metody zadejStudenta
					System.out.println("Vyberte jakeho studenta chcete pridat");
					System.out.println("1 .. Student Humanitniho oboru");
					System.out.println("2 .. Student Technick�ho oboru");
					System.out.println("3 .. Student Kombinovan�ho oboru");
					volba=pouzeCelaCisla(sc);
					mojeDatabaze.zadejStudenta(volba);
					break;
				case 2:
					//zad�n� ID studenta kter�ho chceme vypsat, ulo�en� dan�ho objektu studenta do prom�n� info a n�sledn� zavol�n� getter� aby jsme vypsali informace o studentovi
					System.out.println("Zadejte ID studenta");
					ID=pouzeCelaCisla(sc);
					info=mojeDatabaze.getStudent(ID);
					if (info!=null)
						System.out.println("Jmeno: " + info.getJmeno() + " Prijmeni: " + info.getPrijmeni() + " DatumNarozeni: " + info.getDatumNarozeni()+" Prumer: "+ info.getPrumer());
					else
						System.out.println("Vybrana polozka neexistuje");
					break;
				case 3:
					//zadan� ID studenta kter�mu chceme p�ipsat zn�mku a zavol�n� metody setZnamka(jednotkov� test)
					System.out.println("Zadejte ID studenta");
					ID=pouzeCelaCisla(sc);
					if(ID>Student.ID) {
						System.out.println("Student se zadan�m ID neexistuje");
						break;
					}
					System.out.println("Zadejte znamku studentovi");
					Znamka=pouzeCelaCisla(sc);
					if(!mojeDatabaze.setZnamka(ID, Znamka))
						System.out.println("Zn�mka nebyla pridana");
					break;
				case 4:
					//zad�n�m ID studenta vymaz�n� studenta pomoc� metody deleteStudent(jednotkov� test)
					System.out.println("Zadejte ID studenta");
					ID=pouzeCelaCisla(sc);
					if(ID>Student.ID) {
						System.out.println("Student se zadan�m ID neexistuje");
						break;
					}
					if(!mojeDatabaze.deleteStudent(ID))
						System.out.println("Student nebyl odstranen");
					break;
				case 5:
					//Zad�n�m ID studneta spusten� jeho dovednosti pomoc� metody spustDovednost
					System.out.println("Zadejte ID studenta");
					ID=pouzeCelaCisla(sc);
					mojeDatabaze.spustDovednost(ID);
					break;
				case 6:
					//Vyps�n� seznamu student� se�azen�ho podle p�ijmen� pomoc� metody vypisPodlePrijmeni
					mojeDatabaze.vypisPodlePrijmeni();
					break;
				case 7:
					//Vyps�n� prum�ru student� hum. a tech. oboru zavolanim metod prumerHum a prumerTech
					System.out.println("Prumer studentu humanitniho oboru je:"+mojeDatabaze.prumerHum());
					System.out.println("Prumer studentu technickeho oboru je:"+mojeDatabaze.prumerTech());
					break;
				case 8:
					//Vyps�n� po�tu student� v jednotliv�ch skupin�ch pomoc� metod pocetHum, pocetTech a pocetKom
					System.out.println("Celkovy pocet studentu je: "+mojeDatabaze.prvkyDatabaze.size());
					System.out.println("Pocet student� humanitn�ho oboru je: "+mojeDatabaze.pocetHum());
					System.out.println("Pocet student� technickeho oboru je: "+mojeDatabaze.pocetTech());
					System.out.println("Pocet student� kombinovaneho oboru je: "+mojeDatabaze.pocetKom());
					break;
				case 9:
					//Ulo�en� v�ech student� z Hashmapy do SQL datab�ze studenti pomoc� metody ulozVseDoDatabaze
					mojeDatabaze.ulozVseDoDatabaze();
					break;
				case 10:
					//Na�ten� v�ech student� z SQL databaze do hashmapy pomoc� metody nactiVseZDatabaze
					mojeDatabaze.nactiVseZDatabaze();
					break;
				case 11:
					//Vymazan� studenta z SQL databaze pomoc� metody vymazStudentaZSql
					volba=pouzeCelaCisla(sc);
					mojeDatabaze.vymazStudentaZSql(volba);
					break;
				case 12:
					//Na�ten� studenta z SQL databaze podle Jmena a Prijmen� pomoc� metody nactiStudnetaPodleJmePri
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