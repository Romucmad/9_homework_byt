import com.company.UrlObserver;
import com.company.UrlObservers;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UrlObserversTest {

    UrlObservers observers;
    Date date = new Date();
    @Before
    public  void setUp(){
        observers = new UrlObservers(this.date);
    }


    @Test
    public void testDateModified(){
        assertSame(this.date,this.observers.getLastModifiedDate());
    }


    @Test
    public  void testAddObserver(){

        UrlObserver roman = new UrlObserver("Roman");
        UrlObserver donald = new UrlObserver("Donald");
        this.observers.addObserver(roman);
        assertEquals(1,this.observers.getSubs().size());
        this.observers.addObserver(donald);
        assertEquals(2,this.observers.getSubs().size());
        assertTrue(this.observers.getSubs().contains(donald));

    }

    @Test
    public void removeObservers(){
        UrlObserver roman = new UrlObserver("Roman");
        UrlObserver donald = new UrlObserver("Donald");
        this.observers.addObserver(roman);
        this.observers.addObserver(donald);
        assertEquals(2,this.observers.getSubs().size());
        this.observers.removeObserver(donald);
        assertEquals(1,this.observers.getSubs().size());
        assertFalse(this.observers.getSubs().contains(donald));
    }




}
