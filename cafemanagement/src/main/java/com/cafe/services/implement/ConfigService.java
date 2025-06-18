package com.cafe.services.implement;

import com.cafe.entities.AppSetting;
import com.cafe.repositories.AppSettingRepository;
import org.springframework.stereotype.Component;

@Component
public class ConfigService {
    private static ConfigService instance;
    private final AppSettingRepository appSettingRepository;
    
    private static final String STORE_NAME_KEY = "STORE_NAME";
    private static final String STORE_ADDRESS_KEY = "STORE_ADDRESS";
    private static final String STORE_PHONE_KEY = "STORE_PHONE";
    private static final String STORE_EMAIL_KEY = "STORE_EMAIL";
    private static final String STORE_TAX_CODE_KEY = "STORE_TAX_CODE";

    private ConfigService(AppSettingRepository appSettingRepository) {
        this.appSettingRepository = appSettingRepository;
        initializeDefaultSettings();
    }

    public static synchronized ConfigService getInstance(AppSettingRepository appSettingRepository) {
        if (instance == null) {
            instance = new ConfigService(appSettingRepository);
        }
        return instance;
    }

    private void initializeDefaultSettings() {
        createIfNotExists(STORE_NAME_KEY, "Cafe Management System", "Ten cua hang");
        createIfNotExists(STORE_ADDRESS_KEY, "Default Address", "Dia chi cua hang");
        createIfNotExists(STORE_PHONE_KEY, "Default Phone", "So dien thoai cua hang");
        createIfNotExists(STORE_EMAIL_KEY, "default@cafe.com", "Email cua hang");
        createIfNotExists(STORE_TAX_CODE_KEY, "Default Tax Code", "Ma so thue");
    }

    private void createIfNotExists(String key, String defaultValue, String description) {
        if (!appSettingRepository.existsBySettingKey(key)) {
            AppSetting setting = new AppSetting();
            setting.setSettingKey(key);
            setting.setSettingValue(defaultValue);
            setting.setDescription(description);
            appSettingRepository.save(setting);
        }
    }

    private String getSettingValue(String key) {
        return appSettingRepository.findBySettingKey(key)
                .map(AppSetting::getSettingValue)
                .orElse("");
    }

    private void updateSetting(String key, String value) {
        AppSetting setting = appSettingRepository.findBySettingKey(key)
                .orElseGet(() -> {
                    AppSetting newSetting = new AppSetting();
                    newSetting.setSettingKey(key);
                    return newSetting;
                });
        setting.setSettingValue(value);
        appSettingRepository.save(setting);
    }

    // Getters
    public String getStoreName() {
        return getSettingValue(STORE_NAME_KEY);
    }

    public String getAddress() {
        return getSettingValue(STORE_ADDRESS_KEY);
    }

    public String getPhone() {
        return getSettingValue(STORE_PHONE_KEY);
    }

    public String getEmail() {
        return getSettingValue(STORE_EMAIL_KEY);
    }

    public String getTaxCode() {
        return getSettingValue(STORE_TAX_CODE_KEY);
    }

    // Setters
    public void setStoreName(String storeName) {
        updateSetting(STORE_NAME_KEY, storeName);
    }

    public void setAddress(String address) {
        updateSetting(STORE_ADDRESS_KEY, address);
    }

    public void setPhone(String phone) {
        updateSetting(STORE_PHONE_KEY, phone);
    }

    public void setEmail(String email) {
        updateSetting(STORE_EMAIL_KEY, email);
    }

    public void setTaxCode(String taxCode) {
        updateSetting(STORE_TAX_CODE_KEY, taxCode);
    }

    @Override
    public String toString() {
        return "StoreConfig{" +
                "storeName='" + getStoreName() + '\'' +
                ", address='" + getAddress() + '\'' +
                ", phone='" + getPhone() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", taxCode='" + getTaxCode() + '\'' +
                '}';
    }
} 