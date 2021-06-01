package projekt;

import java.awt.List;
import java.util.ArrayList;

abstract class Student implements Comparable<Student> {
		
		private int IDns;
		private String Jmeno;
		private String Prijmeni;
		private String DatumNarozeni;
		//Slouží k automatické generaci ID pro studenty , každý další student bude mít ID vetší o jedna
		static int ID=0;
		
		//list známek sloužící pro uložení známek studenta
		ArrayList<Integer> ListZnamek = new ArrayList<Integer>();
		
		//konstruktor pro vytváøení studentù 
		public Student(String Jmeno, String Prijmeni, String DatumNarozeni){
			this.Jmeno=Jmeno;
			this.Prijmeni=Prijmeni;
			this.DatumNarozeni=DatumNarozeni;
			this.IDns=ID;
			ID++;
		}
		//konstruktor pro ukládání studentù z SQL databáze(je tam navíc ID , protože studenti už mají ID z SQL databáze)
		public Student(int ID,String Jmeno, String Prijmeni, String DatumNarozeni){
			this.Jmeno=Jmeno;
			this.Prijmeni=Prijmeni;
			this.DatumNarozeni=DatumNarozeni;
			this.IDns=ID;
		}
		//ze známek uložených v listu ListZnamek se spoèítá prùmìr a metoda ho vrátí
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
		//setter pro datum narození,uloží datumNarození
		public void setDatumNarozeni(String datumNarozeni) {
			DatumNarozeni = datumNarozeni;
		}	
		//setter pro jmeno, uloží jmeno
		public String getJmeno() {
			return Jmeno;
		}
		//getter pro prijmeni,vrátí ho
		public String getPrijmeni() {
			return Prijmeni;
		}
		//gette pro datumNarození, vrátí ho
		public String getDatumNarozeni() {
			return DatumNarozeni;
		}
		//getter pro datum narození , vrátí datumNarození ve formátu vhodném pro SQL databázy YYYY-MM-DD
		public String getDatumNarozeniSql() {
			String Datum[]=this.DatumNarozeni.split("[.]+");
			String DAtumSql=Datum[2]+"-"+Datum[1]+"-"+Datum[0];
			return DAtumSql;
		}
		//pøidá studentovi známku do listu ListZnamek pomocí metody add
		public boolean addZnamka(int Znamka){
			if(!ListZnamek.add(Znamka)) {
				return false;
			}
				return true;
		}
		//slouží pro seøazení listu pomocí metody sort
		public int compareTo(Student o){
			return this.getPrijmeni().compareTo(o.getPrijmeni());
		}
		//getter pro ID studenta,vrátí ID studenta
		public int getIDns() {
			return this.IDns;
		}
		//getter pro rok narození , vrátí rok naroztení z datumu narození studenta
		public int getRokNarozeni() {
			String[] Datum=getDatumNarozeni().split("[.]+");
			return Integer.parseInt(Datum[2]);
		}
		//abstraktní metoda , která je v každé tøídì implementována jinak , ale vždy vrací obor studenta
		abstract String getobor();
		
}
