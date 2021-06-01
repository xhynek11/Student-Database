package projekt;

public class StudentTech extends Student{
	//konstruktor pro vytváøení studentù 
	StudentTech(String Jmeno, String Prijmeni, String DatumNarozeni) {
		super(Jmeno, Prijmeni, DatumNarozeni);
	}
	//konstruktor pro ukládání studentù z SQL databáze(je tam navíc ID , protože studenti už mají ID z SQL databáze)
	StudentTech(int ID,String Jmeno, String Prijmeni, String DatumNarozeni) {
		super(ID,Jmeno, Prijmeni, DatumNarozeni);
	}
	//vrací textový øetìzec podle toho jestli je datum narození studenta pøestupný, jednoduché využití modula 
	public String dovednostTech() {
		String[] Datum=getDatumNarozeni().split("[.]+");
		int rok = Integer.parseInt(Datum[2]);
		if(rok%4==0) {
			return "Rok narozeni studenta je prestupny";
		}
		return "Rok narozeni studenta neni prestupny";
	}
	//vrací skratku oboru studenta, využívá se pøi ukládání studenta do databáze , aby bylo dané jaký student je v databázi uložen
	public String getobor() {
		return "Tech";
	}
}
