package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        System.out.println("hi");
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            //비영속
            Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");

            //영속
            em.persist(member1);
            em.persist(member2);

            //조회
            Member findMember1 = em.find(Member.class, 150L);
            Member findMember2 = em.find(Member.class, 150L);

            //영속 엔티티의 동일성 비교
            System.out.println("result = " + (findMember1 == findMember2));

            //수정
            findMember1.setName("AAA");

            //삭제
            em.remove(findMember2);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
