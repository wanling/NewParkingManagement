import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午5:13
 * To change this template use File | Settings | File Templates.
 */
public interface ParkingStrategy {
   public ParkingLot getParkingLot(List<ParkingLot> parkingLotList);
}
