import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午5:07
 * To change this template use File | Settings | File Templates.
 */
public class ParkingSmartBoyTest {
    ParkingBoy parkingBoy;
    ParkingStrategy ps;  //在测试super smart 和 smart 停车策略的时候只需对ps赋不同的初始值，只对下面两个测试用例由区分，其他测试用例都不需更改
    // park_both_have_empty_spaces_with_smart 用于smart方法测试
    // park_both_have_empty_spaces_with_superSmart  用户superSmart方法测试

    @Before
    public void init(){
        List<ParkingLot> parkinglots = new ArrayList<ParkingLot>();
        ParkingLot lot1 = new ParkingLot(50);
        ParkingLot lot2 = new ParkingLot(50);
        parkinglots.add(lot1);
        parkinglots.add(lot2);
        parkingBoy = new ParkingBoy(parkinglots);
       // ps = new SmartStrategy();
       ps = new SuperSmartStrategy();
    }

    /**
     *  两个空的停车场	停车	停车成功
     */
    @Test
    public void park_two_empty_parkingLots(){
        int avaiableSpaces = parkingBoy.getTotalSpaces();
        Car car = new Car();
        Ticket t = parkingBoy.park4Strategy(car, ps);
        Assert.assertEquals(avaiableSpaces - 1, parkingBoy.getTotalEmptySpaces());
    }


    /**
     *  两个满的停车场	停车	停车失败
     */
    @Test
    public void park_two_full_parkingLots(){
        int avaiableSpaces = parkingBoy.getTotalEmptySpaces();
        for (int i = 0; i < avaiableSpaces; i++){
            Car car = new Car();
            Ticket t = parkingBoy.park(car);
        }
        Car car = new Car();
        Ticket t = parkingBoy.park4Strategy(car, ps);
        Assert.assertEquals(null, t);
    }

    /**
     *  两个停车场，均有空车位	停车	将车停在空车位最多的停车场，停车成功
     */
    @Test
    public void park_both_have_empty_spaces_with_smart(){
         ParkingLot targetLot = null;
         for (ParkingLot lot : parkingBoy.getParkingLots()){
             if (targetLot == null || lot.getTotalEmptySpaces() > targetLot.getTotalEmptySpaces()){
                 targetLot = lot;
             }
         }
         int targetNum = targetLot.getTotalEmptySpaces();
         Car car = new Car() ;
         Ticket t = parkingBoy.park4Strategy(car, ps);
         Assert.assertEquals(targetNum-1, targetLot.getTotalEmptySpaces());
    }


    /**
     *  两个停车场，均有空车位	停车	将车停在空车位最多的停车场，停车成功
     */
    @Test
    public void park_both_have_empty_spaces_with_superSmart(){
        ParkingLot targetLot = null;
        for (ParkingLot lot : parkingBoy.getParkingLots()){
            if (lot.getTotalSpaces() <= 0)
                continue;
            if (targetLot == null || (double)lot.getTotalEmptySpaces()/lot.getTotalSpaces() > (double)targetLot.getTotalEmptySpaces()/lot.getTotalSpaces()){
                targetLot = lot;
            }
        }
        int targetNum = targetLot.getTotalEmptySpaces();
        Car car = new Car() ;
        Ticket t = parkingBoy.park4Strategy(car, ps);
        Assert.assertEquals(targetNum-1, targetLot.getTotalEmptySpaces());
    }

    /**
     *   车库里停了一辆车	凭有效停车凭证取车	取车成功
     */
    @Test
    public void get_car_via_valid_ticket(){
        Car car = new Car();
        Ticket t = parkingBoy.park4Strategy(car,ps);
        Car resCar = parkingBoy.getCar(t);
        Assert.assertEquals(car,resCar);
    }

    /**
     *   车库里停了一辆车	凭无效停车凭证取车	取车失败
     */
    @Test
    public void get_car_via_invalid_ticket(){
        Car car = new Car();
        Ticket t = parkingBoy.park4Strategy(car, ps);
        Ticket t_invalid = new Ticket();
        Car resCar = parkingBoy.getCar(t_invalid);
        Assert.assertEquals(null, resCar);
    }

    /**
     *   两个空的停车场	取车	取车失败
     */
    @Test
    public void get_car_with_two_empty_parkingLots(){
        Ticket t = new Ticket();
        Car resCar = parkingBoy.getCar(t);
        Assert.assertEquals(null, resCar);
    }

}
