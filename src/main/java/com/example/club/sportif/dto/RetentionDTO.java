package com.example.club.sportif.dto;

public class RetentionDTO {
    private double retentionRate;
    private long baseCohortSize;
    private long retainedCount;

    public RetentionDTO() {}
    public RetentionDTO(double retentionRate, long baseCohortSize, long retainedCount) {
        this.retentionRate = retentionRate;
        this.baseCohortSize = baseCohortSize;
        this.retainedCount = retainedCount;
    }
    public double getRetentionRate() { return retentionRate; }
    public void setRetentionRate(double retentionRate) { this.retentionRate = retentionRate; }
    public long getBaseCohortSize() { return baseCohortSize; }
    public void setBaseCohortSize(long baseCohortSize) { this.baseCohortSize = baseCohortSize; }
    public long getRetainedCount() { return retainedCount; }
    public void setRetainedCount(long retainedCount) { this.retainedCount = retainedCount; }
}


