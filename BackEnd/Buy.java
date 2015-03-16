//For buying tickets

import java.io.*;
import java.util.*;

public class Buy {
    public Buy(){}

    public void apply(User buyer, User seller, Ticket event, int price, int tix){
    	
    	buyer.updateCredit(-(price * tix));
    	seller.updateCredit((price * tix));
    	event.updateTick(-tix);
    }
}
