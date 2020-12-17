import com.company.UrlObservers;
import com.company.memento.CareTaker;
import com.company.memento.Memento;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CareTakerTest {


    CareTaker careTaker;

    @Before
    public void setUp(){
        this.careTaker = new CareTaker();
    }


    @After
    public  void shutDown(){
        File myObj = new File("mem.bin");
        myObj.delete();
    }


    @Test
    public  void testMemento(){
        assertEquals(0,this.careTaker.mementos.size());
    }

    @Test
    public  void testAddMemento(){
        HashMap<String, UrlObservers> hashMap = new HashMap<>();
        hashMap.put("https://google.com",new UrlObservers(new Date()));
        Memento memento = new Memento(hashMap);
        this.careTaker.addMemento(memento);
        assertEquals(1,this.careTaker.mementos.size());
        assertSame(memento,this.careTaker.mementos.get(this.careTaker.mementos.size()-1));

    }

    @Test
    public  void removeMemento(){
        HashMap<String, UrlObservers> hashMap = new HashMap<>();
        hashMap.put("https://google.com",new UrlObservers(new Date()));
        Memento memento = new Memento(hashMap);
        this.careTaker.addMemento(memento);
        assertEquals(1,this.careTaker.mementos.size());
        assertSame(memento,this.careTaker.mementos.get(this.careTaker.mementos.size()-1));
        this.careTaker.removeMemento(memento);
        assertEquals(0,this.careTaker.mementos.size());

    }

}
