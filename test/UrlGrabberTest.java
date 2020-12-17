import com.company.IUrlObserver;
import com.company.UrlGrabber;
import com.company.UrlObserver;
import com.company.memento.CareTaker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.Date;
import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

public class UrlGrabberTest {



    CareTaker careTaker;
    UrlGrabber urlGrabber;


    @Before
    public  void setUp(){
        careTaker = new CareTaker();
        this.urlGrabber = new UrlGrabber(careTaker);
    }

    @After
    public void ShutDown(){
        File myObj = new File("mem.bin");
        myObj.delete();
    }


    @Test
    public  void urlGrabberCreation(){
        assertEquals(0,this.urlGrabber.getCurrentMementoIndex());
        assertEquals(1,this.urlGrabber.getCareTaker().mementos.size());
    }


    @Test
    public  void registerTest(){
        IUrlObserver roman = new UrlObserver("Roman");
        IUrlObserver donal = new UrlObserver("Donald");

        this.urlGrabber.register(roman,"https://google.com/");
        assertTrue(this.urlGrabber.getCurrentSubsMap().containsKey("https://google.com/"));
        assertTrue(this.urlGrabber.getCurrentSubsMap().get("https://google.com/").getSubs().contains(roman));

        this.urlGrabber.register(donal,"https://pjatk.com/");
        assertTrue(this.urlGrabber.getCurrentSubsMap().containsKey("https://pjatk.com/"));
        assertTrue(this.urlGrabber.getCurrentSubsMap().get("https://pjatk.com/").getSubs().contains(donal));

        this.urlGrabber.register(roman,"https://pjatk.com/");
        assertTrue(this.urlGrabber.getCurrentSubsMap().get("https://pjatk.com/").getSubs().contains(roman));

    }


    @Test
    public  void unRegisterTest(){
        IUrlObserver roman = new UrlObserver("Roman");
        IUrlObserver donal = new UrlObserver("Donald");

        this.urlGrabber.register(roman,"https://google.com/");
        this.urlGrabber.register(donal,"https://pjatk.com/");
        this.urlGrabber.register(roman,"https://pjatk.com/");

        assertEquals(2,this.urlGrabber.getCurrentSubsMap().get("https://pjatk.com/").getSubs().size());

        this.urlGrabber.unregister(roman,"https://pjatk.com/");
        assertEquals(1,this.urlGrabber.getCurrentSubsMap().get("https://pjatk.com/").getSubs().size());
        this.urlGrabber.unregister(donal,"https://pjatk.com/");
        assertEquals(0,this.urlGrabber.getCurrentSubsMap().get("https://pjatk.com/").getSubs().size());

    }

    @Test
    public void testGetLastModifiedDate(){

    }

    @Test
    public  void setNewDateTest(){
        IUrlObserver roman = new UrlObserver("Roman");
        IUrlObserver donal = new UrlObserver("Donald");

        this.urlGrabber.register(roman,"https://google.com/");
        this.urlGrabber.register(donal,"https://pjatk.com/");
        this.urlGrabber.register(roman,"https://pjatk.com/");

        assertEquals(3,this.urlGrabber.getCurrentMementoIndex());

        Date date = new Date();
        this.urlGrabber.setNewDate("https://pjatk.com/",date);
        assertEquals(4,this.urlGrabber.getCurrentMementoIndex());
        assertEquals(date,this.urlGrabber.getCurrentSubsMap().get("https://pjatk.com/").getLastModifiedDate());
    }

/// Sorry, I could not manage mocking framework
    @Test
    public  void getLastDateModified(){

    }

}
