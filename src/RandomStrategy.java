import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午5:18
 * To change this template use File | Settings | File Templates.
 */
public class RandomStrategy implements  ParkingStrategy{
    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLotList) {
       // return null;  //To change body of implemented methods use File | Settings | File Templates.
       for (ParkingLot lot : parkingLotList){
           if (lot.getTotalEmptySpaces() > 0)
               return lot;
       }
        return null;
    }
}
