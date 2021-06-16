package com.example.task.dto;

import java.math.BigDecimal;

public interface OverpaidInvoicesDTO {

    int getInvoice_id();

    BigDecimal getAmount_to_be_reimbursed();
}
