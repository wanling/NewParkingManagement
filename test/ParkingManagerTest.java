import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午6:00
 * To change this template use File | Settings | File Templates.
 */
public class ParkingManagerTest {
    ParkingManager parkingManager;
    ParkingBoy Tom;
    ParkingBoy Jerry;
    ParkingStrategy mps; //  manager的停车策略

    @Before
    public void init(){
        List<ParkingBoy> boyList = new ArrayList<ParkingBoy>();
        List<ParkingLot> plist1 = new ArrayList<ParkingLot>();
        List<ParkingLot> plist2 = new ArrayList<ParkingLot>();
        List<ParkingLot> plist3 = new ArrayList<ParkingLot>();
        ParkingLot lot1 =  new ParkingLot(50);
        ParkingLot lot2 =  new ParkingLot(50);
        ParkingLot lot3 =  new ParkingLot(50);
        ParkingLot lot4 =  new ParkingLot(50);
        ParkingLot lot5 =  new ParkingLot(50);
        plist1.add(lot1);
        plist2.add(lot2); plist2.add(lot3);
        plist3.add(lot4); plist3.add(lot5);
        Tom = new ParkingBoy(plist2);
        Jerry = new ParkingBoy(plist3);
        boyList.add(Tom);
        boyList.add(Jerry);
        parkingManager = new ParkingManager(boyList,plist1);

        mps = new RandomStrategy();
    }

    /**
     *  经理管理的停车场均为空	经理随机停车	停车成功
     */
    @Test
    public void park_by_manager_with_all_empty(){
        int avaiableSpaces = parkingManager.getTotalEmptySpaces();
        Car car = new Car();
        Ticket t = parkingManager.park_own(car,mps);
        Assert.assertEquals(avaiableSpaces-1, parkingManager.getTotalEmptySpaces());
    }

    /**
     * 经理管理的停车场都满	经理随机停车	停车失败
     */
    @Test
    public void  park_by_manager_with_all_full(){
        int avaiableSpaces = parkingManager.getTotalEmptySpaces();
        for (int i = 0; i < avaiableSpaces; i++){
            Car car  = new Car();
            Ticket t = parkingManager.park_own(car, mps);
        }
        Car car = new Car();
        Ticket t = parkingManager.park_own(car,mps);
        Assert.assertEquals(null, t);
    }

    /**
     * 经理管理的停车场有空车位	经理随机停车	停车成功
     */
    @Test
    public void park_by_manager_with_empty_spaces(){
        Car ca = new Car();
        Ticket ta = parkingManager.park_own(ca, mps);
        int avaiableSpaces = parkingManager.getTotalEmptySpaces();
        Car car  = new Car();
        Ticket t = parkingManager.park_own(car, mps);
        Assert.assertEquals(avaiableSpaces-1, parkingManager.getTotalEmptySpaces());
    }

    /**
     * Tom管理的停车场均为空	经理让Tom停车	停车成功
     */
     @Test
    public void park_by_boy_with_all_empty(){
         int avaiableSpaces = parkingManager.getTotalEmptySpaces();
         Car car = new Car();
         Ticket t = parkingManager.park_boy(car,Tom);
         Assert.assertEquals(avaiableSpaces-1, parkingManager.getTotalEmptySpaces());
     }

    /**
     *  Tom管理的停车场均为满	经理让Tom停车	停车失败
     */
    @Test
    public void park_by_boy_with_all_full(){
        int tomCount = Tom.getTotalEmptySpaces();
        for (int i = 0; i < tomCount; i++){
            Car car = new Car();
            Ticket t = Tom.park(car);
        }
        Car car = new Car();
        Ticket t = parkingManager.park_boy(car, Tom);
        Assert.assertEquals(null, t);
    }

    /**
     *  Tom 管理的停车场有空车位	经理让Tom停车	停车成功
     */
    @Test
    public void park_by_boy_with_empty_spaces(){
        int tomCount = Tom.getTotalEmptySpaces();
        for (int i = 0; i < tomCount/2; i++){
            Car car = new Car();
            Ticket t = Tom.park(car);
        }
        int  avaiableSpaces = Tom.getTotalEmptySpaces();
        Car car = new Car();
        Ticket t = parkingManager.park_boy(car, Tom);
        Assert.assertEquals(avaiableSpaces-1, Tom.getTotalEmptySpaces());
    }

    /**
     *  经理在自己管理的车库里随机停了一辆车	凭有效停车凭证取车	取车成功
     */
    @Test
    public void get_car_valid_ticket_by_manager(){
        Car car = new Car();
        Ticket t = parkingManager.park_own(car,mps);
        Car resCar = parkingManager.getCar(t);
        Assert.assertEquals(car,resCar);
    }

    /**
     * 经理让Tom在Tom管理的停车场停了一辆车	凭有效停车凭证取车	取车成功
     */
    @Test
    public void get_car_valid_ticket_by_boy(){
        Car car = new Car();
        Ticket t = parkingManager.park_boy(car,Tom);
        Car resCar = parkingManager.getCar(t);
        Assert.assertEquals(car,resCar);
    }

    /**
     *  车库里停了一辆车	凭无效停车凭证取车	取车失败
     */
    @Test
    public void get_via_invalid_ticket(){
        Car car = new Car();
        Ticket t = parkingManager.park_own(car,mps);
        Ticket t_invalid = new Ticket();
        Car resCar = parkingManager.getCar(t_invalid);
        Assert.assertEquals(null, resCar);
    }

    /**
     *  车库为空时	取车	取车失败
     */
    @Test
    public void  get_car_all_empty(){
        Ticket t = new Ticket();
        Car resCar = parkingManager.getCar(t);
        Assert.assertEquals(null, resCar);
    }

}
