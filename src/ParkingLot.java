import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午2:58
 * To change this template use File | Settings | File Templates.
 */
public class ParkingLot implements PrintReport{
    private int NO;
    private int capacity;
    private Map<Ticket, Car> parkMap;

    public ParkingLot(int NO, int capacity) {
        this.NO = NO;
        this.capacity = capacity;
        this.parkMap = new HashMap<Ticket, Car>();
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkMap = new HashMap<Ticket, Car>();
    }

    public Ticket park(Car a) {
      //  return null;  //To change body of created methods use File | Settings | File Templates.
        Ticket t = null;
        if (capacity - parkMap.size()>0){
            t =  new Ticket();
            parkMap.put(t, a);
        }
        return t;
    }

    public Car getCar(Ticket t) {
//        return null;  //To change body of created methods use File | Settings | File Templates.
        Car res = parkMap.get(t);
        if (res != null)
            parkMap.remove(t);
        return res;
    }

    @Override
    public void printInfo() {
        //To change body of implemented methods use File | Settings | File Templates.
        System.out.println(toString(""));
    }

    @Override
    public int getTotalSpaces() {
        return capacity;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getTotalEmptySpaces() {
        return this.capacity - this.parkMap.size();  //To change body of implemented methods use File | Settings | File Templates.
    }

//    @Override
//    public String toString() {
//        return "ParkingLot{" +
//                "停车场编号： " + NO +
//                "\t车位数 ： " + capacity +
//                "\t空位数 ： " + (capacity - parkMap.size()) +
//                 "\tTotal车位数 ：" + getTotalSpaces() +
//                 "\tTotal空位数 ：" + getTotalEmptySpaces() +
//                '}';
//    }

   public String toString(String blanks) {
        return  blanks+"停车场编号： " + NO +"\n"+
                 blanks+"\t车位数 ： " + getTotalSpaces() +"\n"+
                 blanks+"\t空位数 ： " + getTotalEmptySpaces() +"\n";
    }
}
