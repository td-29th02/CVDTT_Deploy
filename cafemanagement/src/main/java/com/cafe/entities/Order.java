package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    User user;
    
    @Column(name = "OrderCode", length = 20, nullable = false, unique = true)
    String orderCode;
    
    @Column(name = "TotalAmount", precision = 10, scale = 2, nullable = false)
    BigDecimal totalAmount;
    
    @Column(name = "PaymentMethod", length = 20, nullable = false)
    String paymentMethod; // 'Cash', 'Card'
    
    @Column(name = "PaymentStatus", length = 20)
    String paymentStatus = "Pending"; // 'Pending', 'Paid', 'Failed'
    
    @Column(name = "IsCloned")
    Boolean isCloned = false;
    
    @ManyToOne
    @JoinColumn(name = "OriginalOrderId")
    Order originalOrder;
    
    @Column(name = "CreatedDate")
    LocalDateTime createdDate = LocalDateTime.now();
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<OrderDetail> orderDetails;
    
    @OneToMany(mappedBy = "originalOrder", cascade = CascadeType.ALL)
    List<Order> clonedOrders;
}
