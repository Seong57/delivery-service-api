package org.delivery.db.userorder;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.userorder.enums.UserOrderStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = "user_order")
public class UserOrderEntity extends BaseEntity {

    @Column(nullable = false)
    private Long userId;    //user Table 과 1:n

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserOrderStatus status;

    @Column(precision = 11, scale = 4, nullable = false)
    private BigDecimal amount;

    private LocalDateTime orderedAt;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    protected LocalDateTime receivedAt;
}
