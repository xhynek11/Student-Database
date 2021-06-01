package projekt;

public class StudentKom extends Student{
	//konstruktor pro vytv��en� student� 
	StudentKom(String Jmeno, String Prijmeni, String DatumNarozeni) {
		super(Jmeno, Prijmeni, DatumNarozeni);
	}
	//konstruktor pro ukl�d�n� student� z SQL datab�ze(je tam nav�c ID , proto�e studenti u� maj� ID z SQL datab�ze)
	StudentKom(int ID,String Jmeno, String Prijmeni, String DatumNarozeni) {
		super(ID,Jmeno, Prijmeni, DatumNarozeni);
	}
	//vrac� textov� �et�zec podle toho jestli je datum narozen� studenta p�estupn�, jednoduch� vyu�it� modula 
	public String dovednostTech() {
		String[] Datum=getDatumNarozeni().split("[.]+");
		int rok = Integer.parseInt(Datum[2]);
		if(rok%4==0) {
			return "Rok narozeni studenta je prestupny";
		}
		return "Rok narozeni studenta neni prestupny";
	}
	//vrac� zanmen� zv�rokruhu podle data narozen� studenta
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
	//vrac� skratku oboru studenta, vyu��v� se p�i ukl�d�n� studenta do datab�ze , aby bylo dan� jak� student je v datab�zi ulo�en
	public String getobor() {
		return "Kom";
	}
}