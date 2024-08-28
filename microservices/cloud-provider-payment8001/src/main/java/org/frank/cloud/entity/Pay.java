package org.frank.cloud.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "t_pay", schema = "mytest")
@ToString
@Schema(title = "支付交易表 Entity")
public class Pay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Integer id;

    @Column(name = "pay_no", nullable = false, length = 50)
    @Schema(title = "支付流水號")
    private String payNo;

    @Column(name = "order_no", nullable = false, length = 50)
    @Schema(title = "訂單流水號")
    private String orderNo;

    @Column(name = "user_id")
    @Schema(title = "用戶ID")
    private Integer userId;

    @Column(name = "amount", nullable = false, precision = 8, scale = 2)
    @Schema(title = "交易金額")
    private BigDecimal amount;

    @Column(name = "deleted", columnDefinition = "tinyint UNSIGNED not null", insertable = false)
    private Byte deleted;

    @Column(name = "create_time", insertable = false, updatable = false)
    @Schema(title = "創建時間")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant createTime;

    @Column(name = "update_time", insertable = false, updatable = false)
    @Schema(title = "修改時間")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Instant updateTime;

    @PreUpdate
    public void preUpdate() {
        if (this.deleted == null) {
            deleted = 0;
        }
    }

}