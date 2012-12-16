import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午4:27
 * To change this template use File | Settings | File Templates.
 */
public class ParkingBoyTest {
    ParkingBoy parkingBoy;

    @Before
    public void init(){
        List<ParkingLot> parkinglots = new ArrayList<ParkingLot>();
        ParkingLot lot1 = new ParkingLot(50);
        ParkingLot lot2 = new ParkingLot(50);
        parkinglots.add(lot1);
        parkinglots.add(lot2);
        parkingBoy = new ParkingBoy(parkinglots);
    }

    /**
     *   两个空的停车场	停车	停车成功
     */
    @Test
    public void park_two_empty_parkingLots(){
        int avaiableSpaces = parkingBoy.getTotalEmptySpaces();
        Car car = new Car();
        Ticket t = parkingBoy.park(car);
        Assert.assertEquals(avaiableSpaces -1, parkingBoy.getTotalEmptySpaces());
    }

    /**
     *   两个停车场，一个空，一个满	停车	停车成功
     */
    @Test
    public void park_one_empty_one_full_parkingLots(){
        for (int i = 0; i < 50; i++){
            Car car = new Car();
            Ticket t = parkingBoy.park(car);
        }
        int avaiableSpaces = parkingBoy.getTotalEmptySpaces();
        Car car = new Car();
        Ticket t = parkingBoy.park(car);
        Assert.assertEquals(avaiableSpaces - 1, parkingBoy.getTotalEmptySpaces());
    }

    /**
     *   两个满的停车场	停车	停车失败
     */
    @Test
    public void park_two_full_parkingLots(){
        for (int i = 0; i < 100; i++){
            Car car = new Car();
            Ticket t = parkingBoy.park(car);
        }
        Car car = new Car();
        Ticket t = parkingBoy.park(car);
        Assert.assertEquals(null, t);
    }

    /**
     *    两个停车场，均有空车位	停车	停车成功
     */
    @Test
    public void park_have_empty_spaces(){
        Car carx = new Car();
        Ticket tx = parkingBoy.park(carx);
        int avaiableSpaces = parkingBoy.getTotalEmptySpaces();
        Car car = new Car();
        Ticket t = parkingBoy.park(car);
        Assert.assertEquals(avaiableSpaces-1, parkingBoy.getTotalEmptySpaces());
    }

    /**
     *   两个空的停车场		取车	取车失败
     */
    @Test
    public void get_car_two_empty_parkingLots(){
        Ticket t = new Ticket();
        Car resCar = parkingBoy.getCar(t);
        Assert.assertEquals(null, resCar);
    }

    /**
     *   车库里停了一辆车	凭有效停车凭证取车	取车成功
     */
    @Test
    public void get_car_valid_ticket(){
        Car car = new Car();
        Ticket t = parkingBoy.park(car);
        Car resCar = parkingBoy.getCar(t);
        Assert.assertEquals(car,resCar);
    }

    /**
     *   车库里停了一辆车	凭无效停车凭证取车	取车失败
     */
    @Test
    public void get_car_invalid_ticket(){
        Car car = new Car();
        Ticket t = parkingBoy.park(car);
        Ticket invalid_t = new Ticket();
        Car resCar = parkingBoy.getCar(invalid_t);
        Assert.assertEquals(null, resCar);
    }

}
