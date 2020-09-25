package shop.main;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import shop.command.Command;
import shop.data.Data;
import shop.data.Record;
import shop.data.Video;
import shop.data.Inventory;
import java.util.Iterator;

// write an integration test that tests the data classes.
// add in some videos, check out, check in, delete videos, etc.
// check that errors are reported when necessary.
// check that things are going as expected.
public class TEST1{
  private Inventory _inventory = Data.newInventory();
  public TEST1() {

  }
  private void expect(Video v, String s) {
    Assertions.assertEquals(s,_inventory.get(v).toString());
  }
  private void expect(Record r, String s) {
    Assertions.assertEquals(s,r.toString());
  }
  @Test
  private void check(Video v, int numOwned, int numOut, int numRentals) {
    Record r = _inventory.get(v);
    Assertions.assertEquals(numOwned, r.numOwned());
    Assertions.assertEquals(numOut, r.numOut());
    Assertions.assertEquals(numRentals, r.numRentals());
  }
  @Test
  public void test1() {
    Command clearCmd = Data.newClearCmd(_inventory);
    clearCmd.run();
    
    Video v1 = Data.newVideo("Title1", 2000, "Director1");
    Assertions.assertEquals(0, _inventory.size());
    Assertions.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    Assertions.assertEquals(1, _inventory.size());
    Assertions.assertTrue(Data.newAddCmd(_inventory, v1, 5).run());
    Assertions.assertEquals(1, _inventory.size());
    check(v1,10,0,0);

    Video v2 = Data.newVideo("Title2", 2001, "Director2");
    Assertions.assertTrue(Data.newAddCmd(_inventory, v2, 1).run());
    Assertions.assertEquals(2, _inventory.size());
    expect(v2,"Title2 (2001) : Director2 [1,0,0]");

    Assertions.assertFalse(Data.newAddCmd(_inventory, null, 5).run());
    Assertions.assertEquals(2, _inventory.size());

    Assertions.assertTrue(Data.newOutCmd(_inventory, v2).run());
    expect(v2,"Title2 (2001) : Director2 [1,1,1]");

    Assertions.assertTrue(Data.newInCmd(_inventory, v2).run());
    expect(v2,"Title2 (2001) : Director2 [1,0,1]");

    Assertions.assertTrue(Data.newAddCmd(_inventory, v2, -1).run());
    Assertions.assertEquals(1, _inventory.size());
    expect(v1,"Title1 (2000) : Director1 [10,0,0]");

    Command outCmd = Data.newOutCmd(_inventory, v1);
    Assertions.assertTrue(outCmd.run());
    Assertions.assertTrue(outCmd.run());
    Assertions.assertTrue(outCmd.run());
    Assertions.assertTrue(outCmd.run());
    expect(v1,"Title1 (2000) : Director1 [10,4,4]");

    Assertions.assertTrue(Data.newInCmd(_inventory, v1).run());
    expect(v1,"Title1 (2000) : Director1 [10,3,4]");

    Assertions.assertTrue(Data.newAddCmd(_inventory, v2, 5).run());
    Assertions.assertEquals(2, _inventory.size());
    expect(v2,"Title2 (2001) : Director2 [5,0,0]");
    expect(v1,"Title1 (2000) : Director1 [10,3,4]");

    Iterator<Record> it = _inventory.iterator(new java.util.Comparator<Record>() {
      public int compare (Record r1, Record r2) {
        return r2.numRentals() - r1.numRentals();
      }
    });
    expect(it.next(),"Title1 (2000) : Director1 [10,3,4]");
    expect(it.next(),"Title2 (2001) : Director2 [5,0,0]");
    Assertions.assertFalse(it.hasNext());
  }
}
