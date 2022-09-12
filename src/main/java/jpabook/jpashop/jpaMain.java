package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import jpabook.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class jpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

//            Book book = new Book();
//            book.setName("JPA book");
//            book.setAuthor("김영한");
//            em.persist(book);

            //주문
            Order order = new Order();
            order.addOrderItem(new OrderItem()); //연관관계 편의 메소드
            order.addDelivery(new Delivery());
            em.persist(order); //영속성 전이로 인해 order만 영속화해도 orderItem, delivery 모두 영속화

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
