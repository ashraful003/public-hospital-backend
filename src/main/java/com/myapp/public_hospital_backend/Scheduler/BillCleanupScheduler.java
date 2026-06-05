package com.myapp.public_hospital_backend.Scheduler;

import com.myapp.public_hospital_backend.service.BillService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BillCleanupScheduler {
    private final BillService billService;

    public BillCleanupScheduler(BillService billService) {
        this.billService = billService;
    }

    @Scheduled(cron = "0 * * * * *")
    public void cleanupBills() {
        billService.deleteExpiredUnpaidBills();
    }
}