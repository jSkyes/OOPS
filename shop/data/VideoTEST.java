package shop.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
public class VideoTEST {
  public VideoTEST() {

  }
  @Test
  public void testEquals() {
    String title = "A";
    int year = 2009;
    String director = "Zebra";
    VideoObj a = new VideoObj(title,year,director);
    Assertions.assertTrue( a.equals(a) );
    Assertions.assertTrue( a.equals( new VideoObj(title, year, director) ) );
    Assertions.assertTrue( a.equals( new VideoObj(new String(title), year, director) ) );
    Assertions.assertTrue( a.equals( new VideoObj(title, year, new String(director)) ) );
    Assertions.assertFalse( a.equals( new VideoObj(title+"1", year, director) ) );
    Assertions.assertFalse( a.equals( new VideoObj(title, year+1, director) ) );
    Assertions.assertFalse( a.equals( new VideoObj(title, year, director+"1") ) );
    Assertions.assertFalse( a.equals( new Object() ) );
    Assertions.assertFalse( a.equals( null ) );
  }
  @Test
  public void testCompareTo() {
    String title = "A", title2 = "B";
    int year = 2009, year2 = 2010;
    String director = "Zebra", director2 = "Zzz";
    VideoObj a = new VideoObj(title,year,director);
    VideoObj b = new VideoObj(title2,year,director);
    Assertions.assertTrue( a.compareTo(b) < 0 );
    Assertions.assertTrue( a.compareTo(b) == -b.compareTo(a) );
    Assertions.assertTrue( a.compareTo(a) == 0 );

    b = new VideoObj(title,year2,director);
    Assertions.assertTrue( a.compareTo(b) < 0 );
    Assertions.assertTrue( a.compareTo(b) == -b.compareTo(a) );

    b = new VideoObj(title,year,director2);
    Assertions.assertTrue( a.compareTo(b) < 0 );
    Assertions.assertTrue( a.compareTo(b) == -b.compareTo(a) );

    b = new VideoObj(title2,year2,director2);
    Assertions.assertTrue( a.compareTo(b) < 0 );
    Assertions.assertTrue( a.compareTo(b) == -b.compareTo(a) );

    try {
      a.compareTo(null);
      Assertions.fail("");
    } catch ( NullPointerException e ) {}
  }
  @Test
  public void testToString() {
    Video v1 = new VideoObj("Title" , 1999, "Director");
    String ansString = v1.toString();
    Assertions.assertTrue(ansString.equals("Title (1999) : Director"));
    Video v2 = new VideoObj("T" , 2120, "1234");
    String str = v2.toString();
    Assertions.assertTrue(str.equals("T (2120) : 1234"));
  }
  @Test
  public void testHashCode() {
    Assertions.assertEquals
      (-875826552,
       new VideoObj("None", 2009, "Zebra").hashCode());
    Assertions.assertEquals
      (-1391078111,
       new VideoObj("Blah", 1954, "Cante").hashCode());
  }
}
