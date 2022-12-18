package com.shop.entity;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity // 엔티디 선언 (엔티디 매니저가 관리함)
@Table(name = "item")   // 연결 테이블 명시
public class Item extends BaseEntity {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO) // JPA에게 위임, 자동 증가해주세여
    private Long id;    // 상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm;  // 상품명

    @Column(name="price", nullable = false)
    private int price;  // 가격

    @Column(nullable = false)
    private int stockNumber;    // 재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail;  // 상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;  // 상품 판매 상태

//    private LocalDateTime regTime;  // 등록 시간

//    private LocalDateTime updateTime;   // 수정 시간

    
}
