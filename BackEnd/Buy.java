//For buying tickets

import java.io.*;
import java.util.*;

public class Buy {
    public Buy(){}

    public void apply(User seller, Ticket event, int tix){
    	int price = event.getPrice();
    	
    	event.updateTick(-tix);
    }
}
