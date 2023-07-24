package org.delivery.db.userordermenu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_order_menu")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserOrderMenuEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userOrderId;       // 1(userOrder) : n(UserOrderMenu)

    @Column(nullable = false)
    private Long storeMenuId;       // 1(storeMenu) : n(UserOrderMenu)

    @Column(precision = 11, scale = 4, length = 45, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserOrderMenuStatus status;        //누락, 취소 등을 위함
}
