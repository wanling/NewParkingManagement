import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午6:43
 * To change this template use File | Settings | File Templates.
 */
public class ParkingManager implements PrintReport{
    private int ManagerID;
    private List<ParkingBoy> parkingBoyList;
    private List<ParkingLot> parkingLotList;

    public ParkingManager(List<ParkingBoy> parkingBoyList, List<ParkingLot> parkingLotList) {
        this.parkingBoyList = parkingBoyList;
        this.parkingLotList = parkingLotList;
    }

    public ParkingManager(int managerID, List<ParkingBoy> parkingBoyList, List<ParkingLot> parkingLotList) {
        ManagerID = managerID;
        this.parkingBoyList = parkingBoyList;
        this.parkingLotList = parkingLotList;
    }

    public Ticket park_own(Car car, ParkingStrategy ps) {
        ParkingLot lot = ps.getParkingLot(parkingLotList);
        if (lot != null && lot.getTotalEmptySpaces() > 0){
            return lot.park(car);
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public Ticket park_boy(Car car ,ParkingBoy tom) {
        return tom.park(car);
      //  return null;  //To change body of created methods use File | Settings | File Templates.
    }

    public Car getCar(Ticket t) {
        Car res = null;
        for (ParkingLot lot : parkingLotList){
            res = lot.getCar(t);
            if (res != null)
                return res;
        }
        for (ParkingBoy boy : parkingBoyList){
            res = boy.getCar(t);
            if (res != null)
                return  res;
        }
        return null;  //To change body of created methods use File | Settings | File Templates.
    }

    @Override
    public void printInfo() {
        System.out.println(toString("\t"));
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getTotalSpaces() {
        int capacity = 0;
        for (ParkingLot lot : parkingLotList)
            capacity += lot.getTotalSpaces();
        for (ParkingBoy boy : parkingBoyList)
            capacity += boy.getTotalSpaces();
        return capacity;
    }

    @Override
    public int getTotalEmptySpaces() {
        int avaiable = 0;
        for (ParkingLot lot : parkingLotList)
            avaiable += lot.getTotalEmptySpaces();
        for (ParkingBoy boy : parkingBoyList)
            avaiable += boy.getTotalEmptySpaces();
        return avaiable;
    }

    public String toString(String blanks) {
        StringBuilder lot_sb = new StringBuilder();
        for (ParkingLot lot : parkingLotList){
            lot_sb.append(blanks+lot.toString()+"\n");
        }
        StringBuilder boy_sb = new StringBuilder();
        for (ParkingBoy boy : parkingBoyList){
            boy_sb.append(blanks+boy.toString(blanks)+"\n");
        }
        return "ParkingManager{\n" +
                "ManagerID: " + ManagerID + "\n"+
                "parkingLotList: { \n" + lot_sb.toString() +"}\n"+
                "parkingBoyList: { \n" + boy_sb.toString() +"}\n"+
                "Total车位数 ：" + getTotalSpaces() +  "\n" +
                "Total空位数 ：" + getTotalEmptySpaces() + "\n" +
                '}';
    }
}
