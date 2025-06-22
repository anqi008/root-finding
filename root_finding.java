public class STRECKE {

   int länge;
   int nummerstadt1;
   int nummerstadt2;
   public STRECKE(int länge,int nummerstadt1, int nummerstadt2) {
      
      this.länge = länge;
      this.nummerstadt1 = nummerstadt1;
      this.nummerstadt2= nummerstadt2;
   }
   

}

public class STADT {
   String name;
   int nummer;
   int entfernung;
   boolean besucht;
   

   public STADT(String name, int nummer) {
      this.name = name;
      this.nummer= nummer;
      entfernung = Integer.MAX_VALUE;
      besucht = false;
   }
   
   public void entfernungÄndern(int entfernung) {
      this.entfernung = entfernung;
   }
   public void besuchtÄndern(boolean besucht) {
      this.besucht = besucht;
   }


}

public class ROUTENPLANER {
   STADT [] besuchbareStädte;
   STADT startStadt;
   STADT zielStadt;
   int anzahlStädte;
   STRECKE [] weg;
   STADT nächsteStadt;
   STADT [] reihe = new STADT[5];

   public ROUTENPLANER() {
      for (int i = 0; i < 5; i++) {
         reihe[i] = new STADT("", 0);
      }
      startStadt = new STADT("", 0);
      zielStadt = new STADT("", 0);
      nächsteStadt = new STADT("", 0);
      anzahlStädte = 5;
      besuchbareStädte = new STADT[anzahlStädte];
      besuchbareStädte[0] = new STADT("München", 1);
      besuchbareStädte[1] = new STADT("Berlin", 2);
      besuchbareStädte[2] = new STADT("Frankfurt", 3);
      besuchbareStädte[3] = new STADT("Hamburg", 4);
      besuchbareStädte[4] = new STADT("Hannover", 5);
      weg = new STRECKE[7];
      weg[0] = new STRECKE(428, 1, 3);
      weg[1] = new STRECKE(585, 1, 2);
      weg[2] = new STRECKE(655, 3, 2);
      weg[3] = new STRECKE(350, 3, 5);
      weg[4] = new STRECKE(286, 5, 2);
      weg[5] = new STRECKE(151, 5, 4);
      weg[6] = new STRECKE(292, 4, 2);
            
      
      vorwort();
      startstadtSetzen();
      zielstadtSetzen();
      streckeBerechnen();
       
   }
   public void vorwort() {
      println("**************ROUTENPLANER********************");
      println();
      println();
      println("Berechnen Sie die Länge der kürzisten Strecke zwischen diesen Städten!");
      println();
      println();

   }
   
   public void startstadtSetzen() {
      for (int i = 0; i < anzahlStädte; i++) {
         println(besuchbareStädte[i].nummer + " " + besuchbareStädte[i].name);
      }
      
      int a = Input.readInt("Von wo wollen Sie Ihre Fahrt starten? (Geben Sie bitte die Zahl ein):");
      startStadt = besuchbareStädte[a - 1];
      startStadt.entfernungÄndern(0);
      startStadt.besuchtÄndern(true);

   }

   public STADT zielstadtSetzen() {
      int a = Input.readInt("Geben Sie das gewünschte Ziel ein (Geben Sie die Zahl ein):");
      zielStadt = besuchbareStädte[a - 1];
      println("Ihre Route von " + startStadt.name + " bis nach " + zielStadt.name + " wird geplant.");
      
      return zielStadt;
   }

   public int streckeBerechnen() {
      if(startStadt.nummer == zielStadt.nummer) {

         println("Sie befinden sich schon in der Stadt " + zielStadt.name + ".");
         println("Die Entfernung ist :" + startStadt.entfernung);
         return startStadt.entfernung;
      }
      else{
         println("Entfernung von " + startStadt.name + " bis:");
         teilrechnung();
   
         for (int i = 0; i < 5; i++) {
            if(reihe[i].nummer != 0) {
               
               println("Dann über " + reihe[i].name + " bis:");
               reihe[i].besucht = true;
               startStadt = reihe[i];
               teilrechnung();
            }
         }
         for (int i = 0; i < 4; i++) {
            if(reihe[i].nummer != 0) {
               
               println("Dann über " + reihe[i].name + " bis:");
               reihe[i].besucht = true;
               startStadt = reihe[i];
               teilrechnung();
            }
         }
             
      }
      println("");
      println("Die kürziste Strecke bis " + zielStadt.name + " ist " + zielStadt.entfernung + "km lang.", new Color(235, 166, 64));
      return zielStadt.entfernung;
   }
   public void teilrechnung() {
      int a = 0;

      for (int i = 0; i < 7; i++) {
         if(weg[i].nummerstadt1 == startStadt.nummer && startStadt.entfernung + weg[i].länge < besuchbareStädte[weg[i].nummerstadt2 - 1].entfernung) {
            besuchbareStädte[weg[i].nummerstadt2 - 1].entfernung = startStadt.entfernung + weg[i].länge;
            


            println(besuchbareStädte[weg[i].nummerstadt2 - 1].name + " " + besuchbareStädte[weg[i].nummerstadt2 - 1].entfernung + "km", new Color(128, 255, 0));
            
            
            nächsteStadt = besuchbareStädte[weg[i].nummerstadt2 - 1];
            
            a++;
         }
         if(weg[i].nummerstadt2 == startStadt.nummer && startStadt.entfernung + weg[i].länge <= besuchbareStädte[weg[i].nummerstadt1 - 1].entfernung) {
            besuchbareStädte[weg[i].nummerstadt1 - 1].entfernung = startStadt.entfernung + weg[i].länge;


            println(besuchbareStädte[weg[i].nummerstadt1 - 1].name + " " + besuchbareStädte[weg[i].nummerstadt1 - 1].entfernung + "km", new Color(128, 255, 0));
               
            nächsteStadt = besuchbareStädte[weg[i].nummerstadt1 - 1];

            a++;
            
         }
         
         reihe[a] = nächsteStadt;
      }

      

   }


}

public class BENUTZER {
   
   String name;

   public BENUTZER() {
      nameSetzen();
      ROUTENPLANER route1 = new ROUTENPLANER();

     
      
   }
   public String nameSetzen() {
      name = Input.readString("Geben Sie bitte Ihren Namen ein:");
      println();
      println("Willkommen " + name + "! Wir wünschen Ihnen viel Spaß bei der Nutzung dieses Programms.");
      println();
      return name;
   }

   
}
public class VIP extends BENUTZER {

   public VIP() {
      gutscheinbestellen();
   }
   public String gutscheinbestellen() {
      int a;
      String bestellung;
      String []gutschein = new String[5];
      gutschein[0] = "Cola";
      gutschein[1] = "Apfelsaft";
      gutschein[2] = "Kaffee";
      gutschein[3] = "Ice Tee";
      gutschein[4] = "Wasser";
      
      println();
      println("Wählen Sie ein Getränkegutschein aus:");
      println();
      for (int i = 0; i < 5; i++) {
         println((i + 1) +" " + gutschein[i]);
      }
      a = Input.readInt("Geben Sie die Zahl ein");
      bestellung = gutschein[a - 1];
      println("Der Gutschein für " + bestellung + " wird gleich erscheinen");
      println("Gutschein Code: " + a + 2022 + a * 22, new Color(120, 151, 8));
      return bestellung;
   }

   
   
}




println("Hallo! Für die Benutzung dieser App erstellen Sie bitte einen Benutzeraccount.", new Color(133, 218, 151));
println();
println("Wollen Sie 3 Euro dazuzahlen um VIP-Benutzer zu werden? Als VIP bekommen Sie einen Gutschein für ein Gratis-Getränk an der Tankstelle.");

String geldgezahlt = Input.readString("Geben Sie dafür ein 'Ja' ein. (Wenn nicht, drücken Sie die Return-Taste)");

if(geldgezahlt == "Ja") {
   VIP vip1 = new VIP();
}

else{
BENUTZER b1 = new BENUTZER();
}
