package com.cafe.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "AppSettings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class AppSetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    
    @Column(name = "SettingKey", length = 50, nullable = false, unique = true)
    String settingKey;
    
    @Column(name = "SettingValue", length = 255, nullable = false)
    String settingValue;
    
    @Column(length = 255)
    String description;
    
    @Column(name = "UpdatedDate")
    LocalDateTime updatedDate = LocalDateTime.now();
}
