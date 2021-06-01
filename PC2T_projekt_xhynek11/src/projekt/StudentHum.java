package projekt;

public class StudentHum extends Student{
	//konstruktor pro vytváøení studentù 
	StudentHum(String Jmeno, String Prijmeni, String DatumNarozeni) {
		super(Jmeno, Prijmeni, DatumNarozeni);
	}
	//konstruktor pro ukládání studentù z SQL databáze(je tam navíc ID , protože studenti už mají ID z SQL databáze)
	StudentHum(int ID,String Jmeno, String Prijmeni, String DatumNarozeni) {
		super(ID,Jmeno, Prijmeni, DatumNarozeni);
	}
	//vrací zanmení zvìrokruhu podle data narození studenta
	public String dovednostHum() {
		String[] Datum=getDatumNarozeni().split("[.]+");
		int den = Integer.parseInt(Datum[0]);
		int mesic=Integer.parseInt(Datum[1]);
		
		switch(mesic) {
		case 1:
			if(den<=20)
				return "Kozoroh";
			return "Vodnar";
		case 2:
			if(den<=20)
				return "Vodnar";
			return "Ryby";
		case 3:
			if(den<=20)
				return "Ryby";
			return "Beran";
		case 4:
			if(den<=20)
				return "Beran";
			return "Byk";
		case 5:
			if(den<=21)
				return "Byk";
			return "Blizenci";
		case 6:
			if(den<=21)
				return "Blizenci";
			return "Rak";
		case 7:
			if(den<=22)
				return "Rak";
			return "Lev";
		case 8:
			if(den<=22)
				return "Lev";
			return "Panna";
		case 9:
			if(den<=22)
				return "Panna";
			return "Vahy";
		case 10:
			if(den<=23)
				return "Vahy";
			return "Stir";
		case 11:
			if(den<=22)
				return "Stir";
			return "Strelec";
		case 12:
			if(den<=21)
				return "Strelec";
			return "Kozoroh";
		default:
			return "Chyba";
		}
	}
	//vrací skratku oboru studenta, využívá se pøi ukládání studenta do databáze , aby bylo dané jaký student je v databázi uložen
	public String getobor() {
		return "Hum";
	}
}
