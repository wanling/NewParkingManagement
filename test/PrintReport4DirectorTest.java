import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: software
 * Date: 12-12-10
 * Time: 下午9:19
 * To change this template use File | Settings | File Templates.
 */
public class PrintReport4DirectorTest {
    ParkingManager parkingManager;
    ParkingBoy Tom;
    ParkingBoy Jerry;
    ParkingStrategy mps; //  manager的停车策略
    ParkingLot lot1 ;
    ParkingLot lot2 ;
    ParkingLot lot3 ;
    ParkingLot lot4 ;
    ParkingLot lot5 ;

    @Before
    public void init(){
        List<ParkingBoy> boyList = new ArrayList<ParkingBoy>();
        List<ParkingLot> plist1 = new ArrayList<ParkingLot>();
        List<ParkingLot> plist2 = new ArrayList<ParkingLot>();
        List<ParkingLot> plist3 = new ArrayList<ParkingLot>();

//        lot1 =  new ParkingLot(50);
//        lot2 =  new ParkingLot(50);
//        lot3 =  new ParkingLot(50);
//        lot4 =  new ParkingLot(50);
//        lot5 =  new ParkingLot(50);
        lot1 =  new ParkingLot(1,50);
        lot2 =  new ParkingLot(2,50);
        lot3 =  new ParkingLot(3,50);
        lot4 =  new ParkingLot(4,50);
        lot5 =  new ParkingLot(5,50);


        plist1.add(lot1);
        plist2.add(lot2); plist2.add(lot3);
        plist3.add(lot4); plist3.add(lot5);

//        Tom = new ParkingBoy(plist2);
//        Jerry = new ParkingBoy(plist3);
        Tom = new ParkingBoy(1,plist2);
        Jerry = new ParkingBoy(2,plist3);

        boyList.add(Tom);
        boyList.add(Jerry);
        parkingManager = new ParkingManager(boyList,plist1);
        mps = new RandomStrategy();

        //设置停车场的停车状况
        Car[] carlist = new Car[50];
        for (int i = 0; i < 50 ; i++)
            carlist[i] = new Car();
        for (int i = 0; i < 10; i++)
            parkingManager.park_own(carlist[i],mps);
        for (int i = 0; i < 20; i++){
            parkingManager.park_boy(carlist[i+10],Tom);
            parkingManager.park_boy(carlist[i+20],Jerry);
        }
    }

    /**
     * 1号停车场停了10辆车	打印1号停车场报表	正确打印1号停车场信息
     */
    @Test
    public void print_ParkingLot(){
        String lotReport = "停车场编号： 1\n\t车位数 ： 50\n\t空位数 ： 40\n";
        Assert.assertEquals(lotReport,lot1.toString(""));
      //  lot1.printInfo();
    }

    /**
     *  Tom管理两个停车场	打印停车仔Tom管理停车场信息	正确打印停车仔Tom管理的停车场信息
     */
    @Test
    public void print_ParkingBoy(){
        String boyReport = "停车仔编号： 1\n\t停车场编号： 2\n\t\t车位数 ： 50\n\t\t空位数 ： 30\n\t停车场编号： 3\n\t\t车位数 ： 50\n\t\t空位数 ： 50\n\tTotal车位数 ： 100\n\tTotal空位数 ： 80\n" ;
        Assert.assertEquals(boyReport,Tom.toString(""));
       // Tom.printInfo();
    }

    /**
     *    经理管理一个停车场，两个停车仔	打印经理管理的停车场信息	正确打印经理管理的停车场信息
     */
    @Test
    public void print_ParkingManager(){
        String managementReport = "停车场经理编号： 0\n\t停车场编号： 1\n\t\t车位数 ： 50\n\t\t空位数 ： 40\n\n\t停车仔编号： 1\n\t\t停车场编号： 2\n\t\t\t车位数 ： 50\n\t\t\t空位数 ： 30\n\t\t停车场编号： 3\n\t\t\t车位数 ： 50\n\t\t\t空位数 ： 50\n\t\tTotal车位数 ： 100\n\t\tTotal空位数 ： 80\n\n\t停车仔编号： 2\n\t\t停车场编号： 4\n\t\t\t车位数 ： 50\n\t\t\t空位数 ： 30\n\t\t停车场编号： 5\n\t\t\t车位数 ： 50\n\t\t\t空位数 ： 50\n\t\tTotal车位数 ： 100\n\t\tTotal空位数 ： 80\n\n\tTotal车位数 ： 250\n\tTotal空位数 ： 200\n";
        Assert.assertEquals(managementReport, parkingManager.toString(""));
      //  parkingManager.printInfo();
    }
}
