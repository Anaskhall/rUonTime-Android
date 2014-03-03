/**
 * 
 */
package se.kth.mobdev.ruontime.demo;
import java.util.GregorianCalendar;

import org.hibernate.Session;  
import org.hibernate.SessionFactory;  

import se.kth.mobdev.ruontime.persistence.HibernateUtil;
import se.kth.mobdev.ruontime.persistence.PersistenceFactory;
import se.kth.mobdev.ruontime.persistence.model.CheckIn;
import se.kth.mobdev.ruontime.persistence.model.Group;
import se.kth.mobdev.ruontime.persistence.model.Meeting;
import se.kth.mobdev.ruontime.persistence.model.User;
  
  
public class Demo {  
  
    public static void main(String[] args) {  
  
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();  
//        Session session = sessionFactory.openSession();  
//        session.beginTransaction();  
          
        User user = new User();  
        user.setUserName("y0m4m4");
        user.setFirstName("Yo");  
        user.setLastName("Mama");
        user.setPwHash("sss");
        user = PersistenceFactory.getUserDao().save(user);

        
        CheckIn checkin = new CheckIn(user, GregorianCalendar.getInstance(), 3, false, false);
        PersistenceFactory.getCheckinDao().save(checkin);
        
        Group group = new Group("new group" + Math.random(), user);
        group.addParticipant(new User("user1", "donald", "duck", "aaa"));
        group.addParticipant(new User("user2", "darth", "vader", "aaa"));
        
        group = PersistenceFactory.getGroupDao().save(group);
       Meeting meet = new Meeting();
       meet.Meeting("awsm meeting" + Math.random(), group, GregorianCalendar.getInstance(), GregorianCalendar.getInstance());

       PersistenceFactory.getMeetingDao().save(meet);
    }  
}  
