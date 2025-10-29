package com.example.club.sportif.service;

import java.time.LocalDate;

public class InscriptionFilter {

    private LocalDate startDate;
    private LocalDate endDate;

    public InscriptionFilter() {}

    public InscriptionFilter(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public boolean isValidRange() {
        return startDate != null && endDate != null && !endDate.isBefore(startDate);
    }

    @Override
    public String toString() {
        return "InscriptionFilter{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}


