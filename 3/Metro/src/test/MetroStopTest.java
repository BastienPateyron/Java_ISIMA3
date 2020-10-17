package test;

import junit.framework.*;
import main.MetroStop;
import main.StopList;

public class MetroStopTest extends TestCase {


   private MetroStop monArret;

   protected void setUp() throws Exception {
      super.setUp();
      monArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
   }

   protected void tearDown() throws Exception {
      super.tearDown();
      monArret = null;
   }

   public void testAccesseurs() {
      assertEquals(1975, monArret.getId());
      assertEquals(2.33871281165883, monArret.getLongitude());
      assertEquals(48.8844176451841, monArret.getLatitude());
      assertEquals("Abbesses", monArret.getNom());
      assertEquals("PARIS-18EME", monArret.getArrondissement());
      assertEquals("metro", monArret.getType());
   }

   public void testEquals() {
      MetroStop monArret_2 = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
      assertEquals(monArret, monArret_2);
      assertTrue(monArret.equals(monArret_2)); // Probablement inutile
   }

   public void testToString() {
      String affichage = "1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841";
      assertEquals(monArret.toString(), affichage);
   }


   public void testStopListAdd() {
      StopList maStopList = new StopList();
      assertEquals(maStopList.size(), 0);
      
      maStopList.add(monArret); 
      assertEquals(maStopList.size(), 1);
   }
   
   public void testStopListToString() {
      StopList maStopList = new StopList();
      assertEquals(maStopList.toString(), "");
      
      maStopList.add(monArret);
      assertEquals(maStopList.toString(), "1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841\n");
      
      maStopList.add(monArret);
      assertEquals(maStopList.toString(), "1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841\n1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841\n");
   }
}