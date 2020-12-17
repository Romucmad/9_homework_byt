import com.company.UrlObserver;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class UrlObserverTest {


    UrlObserver roman;
    UrlObserver donal;


    @Before
    public void setUp(){
        this.roman = new UrlObserver("Roman Hrybinchuk");
        this.donal = new UrlObserver("Donald Trump");

    }


    @Test
    public void updateTest(){
        assertEquals("Subscriber Roman Hrybinchuk has got update that https://google.com has been updated to "+new Date().toString(),
                this.roman.update("https://google.com",new Date()));

        assertEquals("Subscriber Donald Trump has got update that https://google.com has been updated to "+new Date().toString(),
                this.donal.update("https://google.com",new Date()));
    }

}
