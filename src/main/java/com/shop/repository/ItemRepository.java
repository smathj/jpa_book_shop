package com.shop.repository;


import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.List;

/*
엔티티 매니저를 직접 사용하지 않는게 좋다 (엔티티매니저의 CRUD 메서드)
DAO 역할을 하는 Repository 인터페이스를 구현하면 된다
 */
// * JpaRepository<엔티티 클래스, 기본키>
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findByItemNm(String itemNm); // where itemNm = ?

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);   // itemNm = ? Or itemDetail = ?

    List<Item> findByPriceLessThan(Integer price);  // where price < ?

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

    // JPQL
    @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

}
