import sun.beans.editors.DoubleEditor;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午5:19
 * To change this template use File | Settings | File Templates.
 */
public class SuperSmartStrategy implements ParkingStrategy{
    @Override
    public ParkingLot getParkingLot(List<ParkingLot> parkingLotList) {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
        ParkingLot res = null;
        Double maximumRate = 0.0;
        Double rate = 0.0;
        for (ParkingLot lot : parkingLotList){
            rate = 0.0;
            if (lot.getTotalSpaces() > 0 )
                rate = (double)lot.getTotalEmptySpaces()/lot.getTotalSpaces();
            if (rate > maximumRate){
                maximumRate = rate;
                res = lot;
            }
        }
        return  res;
    }
}
