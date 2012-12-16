import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午5:15
 * To change this template use File | Settings | File Templates.
 */
public class SmartStrategy implements ParkingStrategy{
    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLotList) {
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
        int max = 0;
        ParkingLot res = null;
        for (ParkingLot lot : parkingLotList){
            if (lot.getTotalEmptySpaces() > max){
                max = lot.getTotalEmptySpaces();
                res = lot;
            }
        }
        return  res;
    }
}
