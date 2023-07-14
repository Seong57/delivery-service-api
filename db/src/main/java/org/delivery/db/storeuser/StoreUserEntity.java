package org.delivery.db.storeuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.storeuser.enums.StoreUserRole;
import org.delivery.db.storeuser.enums.StoreUserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store_user")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class StoreUserEntity extends BaseEntity {

    @Column(nullable = false)
    private Long storeId;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private StoreUserStatus status;

    @Column(nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private StoreUserRole role;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;


}
