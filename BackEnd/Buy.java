//For buying tickets

public class Buy {
    public Buy(){}

    public void apply(User seller, Ticket event, int tix){
    	event.updateTick(-tix);
    }
}
