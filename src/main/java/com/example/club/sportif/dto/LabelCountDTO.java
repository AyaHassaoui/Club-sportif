package com.example.club.sportif.dto;

public class LabelCountDTO {
    private String label;
    private long total;

    public LabelCountDTO() {}
    public LabelCountDTO(String label, long total) {
        this.label = label;
        this.total = total;
    }

    public String getLabel() { return label; }
    public void setLabel(String label) { this.label = label; }
    public long getTotal() { return total; }
    public void setTotal(long total) { this.total = total; }
}



