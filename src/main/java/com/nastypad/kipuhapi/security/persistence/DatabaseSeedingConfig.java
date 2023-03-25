package com.nastypad.kipuhapi.security.persistence;

import com.nastypad.kipuhapi.security.domain.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeedingConfig {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseSeedingConfig.class);

    @Autowired
    private RoleService roleService;

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent applicationReadyEvent) {
        roleService.seed(); //Set data.
    }
}
