package org.delivery.db.userordermenu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.storemenu.StoreMenuEntity;
import org.delivery.db.userorder.UserOrderEntity;
import org.delivery.db.userordermenu.enums.UserOrderMenuStatus;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_order_menu")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserOrderMenuEntity extends BaseEntity {

    @JoinColumn(nullable = false, name = "user_order_id")
    @ManyToOne
    private UserOrderEntity userOrder;       // n : 1

    @JoinColumn(nullable = false)
    @ManyToOne
    private StoreMenuEntity storeMenu;       // n : 1

    @Column(precision = 11, scale = 4, length = 45, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserOrderMenuStatus status;        //누락, 취소 등을 위함


}
