package com.example.demo.controller;

public class DashboardStats {

    private final long total;
    private final long completed;
    private final long ongoing;
    private final long notstarted;

    public DashboardStats(long total,long completed,long ongoing,long notstarted)
    {
        this.total=total;
        this.completed=completed;
        this.ongoing=ongoing;
        this.notstarted=notstarted;
    }

    public long getTotal()
    {
        return total;
    }

    public long getCompleted()
    {
        return completed;
    }

    public long getOngoing()
    {
        return ongoing;
    }

    public long getNotstarted()
    {
        return notstarted;
    }
}
