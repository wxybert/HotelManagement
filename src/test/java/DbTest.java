import com.tellh.entity.Customer;
import com.tellh.entity.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by tlh on 2016/10/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:SpringAppContext.xml")
public class DbTest {
    @Autowired
    SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    @Before
    public void before() {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void after() {
        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    @Test
    public void add_ordinary_standard_room() {
        int floor = 0;
        for (int i = 0; i < 100; i++) {
            int num = i % 20;
            if (num == 0)
                floor++;
            Room room = new Room(String.valueOf(floor * 100 + num),
                    "http://o762c73os.bkt.clouddn.com/ordinary_standard.png",
                    true, 128f, 2, Room.Type.ORDINARY_STANDARD, "双人普通标间，舒适自然");
            session.save(room);
        }
    }

    @Test
    public void add_deluxe_standard_room() {
        for (int i = 0; i < 10; i++) {
            Room room = new Room(String.valueOf(6 * 100 + i),
                    "http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg",
                    true, 288f, 2, Room.Type.DELUXE_STANDARD, "双人豪华标间，高端大气上档次");
            session.save(room);
        }
    }

    @Test
    public void add_ordinary_suite_room() {
        for (int i = 0; i < 10; i++) {
            Room room = new Room(String.valueOf(7 * 100 + i),
                    "http://o762c73os.bkt.clouddn.com/deluxe_standard.jpg",
                    true, 300f, 2, Room.Type.ORDINARY_SUITE, "双人普通套房，提供全套服务");
            session.save(room);
        }
    }

    @Test
    public void add_deluxe_suite_room() {
        for (int i = 0; i < 3; i++) {
            Room room = new Room(String.valueOf(8 * 100 + i),
                    "http://o762c73os.bkt.clouddn.com/deluxe_suite.jpg",
                    true, 888f, 2, Room.Type.DELUXE_SUITE, "双人豪华套房，成功人士的选择");
            session.save(room);
        }
    }

    @Test
    public void add_customer() {
        String[] names = RandomProduce.getRandomName(30);
        String idNum = "110101198010018233\n" +
                "110101198010016414\n" +
                "110101198010014451\n" +
                "110101198010013731\n" +
                "110101198010011090\n" +
                "110101198010013993\n" +
                "110101198010015331\n" +
                "110101198010015657\n" +
                "110101198010017871\n" +
                "110101198010014312\n" +
                "110101198010014419\n" +
                "110101198010018874\n" +
                "110101198010013475\n" +
                "110101198010019359\n" +
                "11010119801001147X\n" +
                "110101198010016852\n" +
                "110101198010018479\n" +
                "110101198010013053\n" +
                "110101198010014857\n" +
                "110101198010019658\n" +
                "110101198010018196\n" +
                "110101198010012835\n" +
                "110101198010016473\n" +
                "110101198010016334\n" +
                "11010119801001745X\n" +
                "110101198010016019\n" +
                "110101198010014750\n" +
                "110101198010013838\n" +
                "110101198010011699\n" +
                "110101198010014734";
        String[] idNums = idNum.split("\\n");
        for (int i = 0; i < names.length; i++) {
            Customer customer = new Customer();
            customer.setName(names[i]);
            customer.setIdNum(idNums[i]);
            if (i % 10 == 8)
                customer.setVIP(true);
            session.save(customer);
        }
    }

    @Test
    public void alterPrice() {
        List<Room> list = session.createQuery("From Room r where r.size=1 and r.type=:type", Room.class)
                .setParameter("type", Room.Type.DELUXE_STANDARD)
                .list();
        for (Room room : list) {
            room.setPrice(208);
        }
    }

    @Test
    public void testdb() {

    }
}
