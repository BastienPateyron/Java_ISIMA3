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

   @BeforeEach
   public void setUp() throws Exception {
      // super.setUp();
      monArret = new MetroStop(1975, 2.33871281165883, 48.8844176451841, "Abbesses", "PARIS-18EME", "metro");
      maStopList = new StopList();
   }

   @AfterEach
   public void tearDown() throws Exception {
      monArret = null;
      maStopList = null;
   }

   @Test
   public void testAdd() {
      assertEquals(maStopList.size(), 0);
      
      maStopList.add(monArret); 
      assertEquals(maStopList.size(), 1);
   }

   @Test
   public void testToString() {
      assertEquals(maStopList.toString(), "");
      
      maStopList.add(monArret);
      assertEquals(maStopList.toString(), "1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841\n");
      
      maStopList.add(monArret);
      assertEquals(maStopList.toString(), "1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841\n1975\tAbbesses\tPARIS-18EME\tmetro\t2.33871281165883\t48.8844176451841\n");
   }

   // @Test
   // @Disabled
   // public void testLoadFileMock() {
   //    // TODO
   // }
}
