import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午4:29
 * To change this template use File | Settings | File Templates.
 */
public class ParkingBoy implements  PrintReport{
    private int BoyNO;
    private List<ParkingLot> parkingLots;
    public ParkingBoy(List<ParkingLot> parkinglots) {
        this.parkingLots = parkinglots;
    }

    public ParkingBoy(int boyNO, List<ParkingLot> parkingLots) {
        BoyNO = boyNO;
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        //return null;  //To change body of created methods use File | Settings | File Templates.
        for (ParkingLot lot : parkingLots){
            if (lot.getTotalEmptySpaces() > 0){
                Ticket t = lot.park(car);
                return  t;
            }
        }
        return  null;
    }

    public Car getCar(Ticket t) {
         Car res = null;
        for (ParkingLot lot : parkingLots){
            res = lot.getCar(t);
            if (res != null)
                return  res;
        }
        return res;
    }

    public Ticket park4Strategy(Car car, ParkingStrategy ps) {
        ParkingLot lot = ps.getParkingLot(parkingLots);
        if (lot != null)
            return lot.park(car);
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

    @Override
    public void printInfo() {
        System.out.println(toString("\t"));
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getTotalSpaces() {
        int res = 0;
        for (ParkingLot lot : parkingLots){
             res += lot.getTotalSpaces();
        }
        return res;
    }

    @Override
    public int getTotalEmptySpaces() {
        int num = 0;
        for (ParkingLot lot : parkingLots)
            num += lot.getTotalEmptySpaces();
        return  num;
    }

    public String toString(String blanks) {
        StringBuilder sb = new StringBuilder();
        for (ParkingLot lot : parkingLots){
            sb.append(blanks+blanks+lot.toString()+"\n") ;
        }
        return  "ParkingBoy{\n" +
                 blanks+"BoyNO: " + BoyNO +"\n"+
                 blanks+"parkingLots:{ \n" + sb.toString() + "}\n" +
                 blanks+"Total车位数 ：" + getTotalSpaces() +  "\n" +
                 blanks+"Total空位数 ：" + getTotalEmptySpaces() + "\n" +
                 '}';
    }
}
