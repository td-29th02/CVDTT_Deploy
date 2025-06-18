package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "ViewConfigs", 
       uniqueConstraints = @UniqueConstraint(columnNames = {"UserId", "PageName"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ViewConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    User user;
    
    @Column(name = "ViewType", length = 20, nullable = false)
    String viewType; // 'Table', 'Card'
    
    @Column(name = "PageName", length = 50, nullable = false)
    String pageName; // 'Menu', 'Orders'
}