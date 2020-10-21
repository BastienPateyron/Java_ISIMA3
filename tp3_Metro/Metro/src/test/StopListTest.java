package test;

import main.MetroStop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import main.StopList;

public class StopListTest {

   private MetroStop monArret;
   private StopList maStopList;
   String ratp_file = "Metro/ratp_arret.csv";

   @BeforeEach
   public void setUp() throws Exception {
      maStopList = new StopList();
   }

   @AfterEach
   public void tearDown() throws Exception {
      maStopList = null;
   }

   @Test
   public void testAdd() {
      assertEquals(maStopList.size(), 0);

      monArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
      maStopList.add(monArret);
      assertEquals(maStopList.size(), 1);
   }

   @Test
   public void testToString() {
      assertEquals(maStopList.toString(), "");

      monArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");

      maStopList.add(monArret);
      assertEquals(maStopList.toString(), "1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841\n");

      maStopList.add(monArret);
      assertEquals(maStopList.toString(),
            "1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841\n1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841\n");
   }

   @Test
   public void testSortById() {
      maStopList.loadFile(ratp_file);
      maStopList.sortById();

      MetroStop premierArret = new MetroStop(1621, 2.34709106533484, 48.8946326891969, "Simplon", "PARIS-18EME", "metro");
      MetroStop premierSorted = maStopList.getStopList().get(0);
      assertEquals(premierArret, premierSorted, "Tester le premier élément");

      MetroStop dernierArret = new MetroStop(11492528, 2.36719568975062, 48.8163966575408, "VERDUN - CHATEAUDUN", "IVRY-SUR-SEINE", "bus");
      MetroStop dernierSorted = maStopList.getStopList().get(maStopList.getStopList().size() - 1);
      assertEquals(dernierArret, dernierSorted, "Tester le dernier élément");
   }

   @Test
   public void testSortByArrondissement() {
      maStopList.loadFile(ratp_file);
      maStopList.sortByArrondissementThenNom();

      MetroStop premierArret = new MetroStop(11338577, 2.42525752896781, 48.7943657316951, "ACHTARAK", "ALFORTVILLE", "bus");
      MetroStop premierSorted = maStopList.getStopList().get(0);
      assertEquals(premierArret, premierSorted, "Tester le premier élément");

      MetroStop dernierArret = new MetroStop(11344593, 2.32166979400847, 48.7322085923013, "VILLEMILAN 3", "WISSOUS", "bus");
      MetroStop dernierSorted = maStopList.getStopList().get(maStopList.getStopList().size() - 1);
      assertEquals(dernierArret, dernierSorted, "Tester le dernier élément");
   }

}
