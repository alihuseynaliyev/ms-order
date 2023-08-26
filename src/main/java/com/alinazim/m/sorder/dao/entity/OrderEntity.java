package com.alinazim.m.sorder.dao.entity;

import com.alinazim.m.sorder.model.enums.OrderStatus;
import com.alinazim.m.sorder.model.enums.ProductType;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;

@Entity
@Table(name = "orders")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Builder

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class OrderEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;

    @Enumerated(STRING)
    private OrderStatus orderStatus;

    @Enumerated(STRING)
    private ProductType productType;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    String details;

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;
}