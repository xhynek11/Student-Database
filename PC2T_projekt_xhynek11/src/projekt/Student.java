package projekt;

import java.awt.List;
import java.util.ArrayList;

abstract class Student implements Comparable<Student> {
		
		private int IDns;
		private String Jmeno;
		private String Prijmeni;
		private String DatumNarozeni;
		//Slou�� k automatick� generaci ID pro studenty , ka�d� dal�� student bude m�t ID vet�� o jedna
		static int ID=0;
		
		//list zn�mek slou��c� pro ulo�en� zn�mek studenta
		ArrayList<Integer> ListZnamek = new ArrayList<Integer>();
		
		//konstruktor pro vytv��en� student� 
		public Student(String Jmeno, String Prijmeni, String DatumNarozeni){
			this.Jmeno=Jmeno;
			this.Prijmeni=Prijmeni;
			this.DatumNarozeni=DatumNarozeni;
			this.IDns=ID;
			ID++;
		}
		//konstruktor pro ukl�d�n� student� z SQL datab�ze(je tam nav�c ID , proto�e studenti u� maj� ID z SQL datab�ze)
		public Student(int ID,String Jmeno, String Prijmeni, String DatumNarozeni){
			this.Jmeno=Jmeno;
			this.Prijmeni=Prijmeni;
			this.DatumNarozeni=DatumNarozeni;
			this.IDns=ID;
		}
		//ze zn�mek ulo�en�ch v listu ListZnamek se spo��t� pr�m�r a metoda ho vr�t�
		public float getPrumer() {
			int pocet;
			float soucet=0;
			float prumer=0;
			
			if(ListZnamek.size()==0)
				return 0;
			
			pocet=ListZnamek.size()-1;
			for(int i=0;i<=pocet;i++) {
				soucet=soucet+ListZnamek.get(i);
			}
			prumer=soucet/ListZnamek.size();
			return prumer;
		}
		//setter pro datum narozen�,ulo�� datumNarozen�
		public void setDatumNarozeni(String datumNarozeni) {
			DatumNarozeni = datumNarozeni;
		}	
		//setter pro jmeno, ulo�� jmeno
		public String getJmeno() {
			return Jmeno;
		}
		//getter pro prijmeni,vr�t� ho
		public String getPrijmeni() {
			return Prijmeni;
		}
		//gette pro datumNarozen�, vr�t� ho
		public String getDatumNarozeni() {
			return DatumNarozeni;
		}
		//getter pro datum narozen� , vr�t� datumNarozen� ve form�tu vhodn�m pro SQL datab�zy YYYY-MM-DD
		public String getDatumNarozeniSql() {
			String Datum[]=this.DatumNarozeni.split("[.]+");
			String DAtumSql=Datum[2]+"-"+Datum[1]+"-"+Datum[0];
			return DAtumSql;
		}
		//p�id� studentovi zn�mku do listu ListZnamek pomoc� metody add
		public boolean addZnamka(int Znamka){
			if(!ListZnamek.add(Znamka)) {
				return false;
			}
				return true;
		}
		//slou�� pro se�azen� listu pomoc� metody sort
		public int compareTo(Student o){
			return this.getPrijmeni().compareTo(o.getPrijmeni());
		}
		//getter pro ID studenta,vr�t� ID studenta
		public int getIDns() {
			return this.IDns;
		}
		//getter pro rok narozen� , vr�t� rok narozten� z datumu narozen� studenta
		public int getRokNarozeni() {
			String[] Datum=getDatumNarozeni().split("[.]+");
			return Integer.parseInt(Datum[2]);
		}
		//abstraktn� metoda , kter� je v ka�d� t��d� implementov�na jinak , ale v�dy vrac� obor studenta
		abstract String getobor();
		
}
