import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午2:53
 * To change this template use File | Settings | File Templates.
 */
public class ParkTest {
     ParkingLot parkEntity;

    @Before
    public void init(){
        parkEntity = new ParkingLot(100);
    }

    /**
     *  车库为空	停车	停车成功
     */
    @Test
    public void park_empty_parkingLots(){
        int avaiableSpace = parkEntity.getTotalEmptySpaces();
        Car a = new Car();
        Ticket t = parkEntity.park(a);
        Assert.assertEquals(avaiableSpace-1, parkEntity.getTotalEmptySpaces());
    }

    /**
     *  车库未满	停车	停车成功
     */
    @Test
    public void park_have_empty_space(){
        Car a = new Car();
        Ticket ta = parkEntity.park(a);
        int avaiableSpace = parkEntity.getTotalEmptySpaces();
        Car b = new Car();
        Ticket tb = parkEntity.park(b);
        Assert.assertEquals(avaiableSpace-1, parkEntity.getTotalEmptySpaces());
    }

    /**
     *  车库已满	停车	停车失败
     */
    @Test
    public void park_no_empty_space(){
        int avaiableSpace = parkEntity.getTotalEmptySpaces();
        for (int i = 0; i < avaiableSpace; i++){
            Car x = new Car();
            Ticket tx = parkEntity.park(x);
        }
        Car a = new Car();
        Ticket ta = parkEntity.park(a);
        Assert.assertEquals(null, ta);
    }

    /**
     * 车库为空	取车	取车失败
     */
    @Test
    public void get_car_empty_parkingLot(){
        Ticket t = new Ticket();
        Car rescar = parkEntity.getCar(t);
        Assert.assertEquals(null, rescar);
    }

    /**
     *   车库停了一辆车	凭有效停车凭证取车	取车成功
     */
    @Test
    public void get_car_via_valid_ticket(){
        int avaiableSpace = parkEntity.getTotalEmptySpaces();
        Car car = new Car();
        Ticket t = parkEntity.park(car);
        Car resCar = parkEntity.getCar(t);
        Assert.assertEquals(car, resCar);
        Assert.assertEquals(avaiableSpace,parkEntity.getTotalEmptySpaces());
    }

    /**
     *  车库停了一辆车	凭无效停车凭证取车	取车失败
     */
    @Test
    public void get_car_vid_invalid_ticket(){
        Car car = new Car();
        Ticket t = parkEntity.park(car);
        Ticket t_invalid = new Ticket();
        Car resCar = parkEntity.getCar(t_invalid);
        Assert.assertEquals(null, resCar);
    }

    /**
     * 车库停了一辆车	有效停车凭证两次驱车	第一次取车成功，第二次取车失败
     */
    @Test
   public void get_car_twice(){
        Car car = new Car();
        Ticket t = parkEntity.park(car);
        Car resCar = parkEntity.getCar(t);
        Assert.assertEquals(car,resCar);
        resCar = parkEntity.getCar(t);
        Assert.assertEquals(null, resCar);
    }

}
