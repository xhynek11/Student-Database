package projekt;

public class StudentTech extends Student{
	//konstruktor pro vytv��en� student� 
	StudentTech(String Jmeno, String Prijmeni, String DatumNarozeni) {
		super(Jmeno, Prijmeni, DatumNarozeni);
	}
	//konstruktor pro ukl�d�n� student� z SQL datab�ze(je tam nav�c ID , proto�e studenti u� maj� ID z SQL datab�ze)
	StudentTech(int ID,String Jmeno, String Prijmeni, String DatumNarozeni) {
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
	//vrac� skratku oboru studenta, vyu��v� se p�i ukl�d�n� studenta do datab�ze , aby bylo dan� jak� student je v datab�zi ulo�en
	public String getobor() {
		return "Tech";
	}
}
