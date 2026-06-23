package com.example.demo.dto;

public class DashboardDTO {

    private long totalTopics;
    private long completedTopics;
    private long pendingTopics;
    private long dsaTopics;
    private double completedPercentage;

    public long getTotalTopics()
    {
        return totalTopics;
    }

    public void setTotalTopics(long totalTopics)
    {
        this.totalTopics=totalTopics;
    }

    public long getCompletedTopics()
    {
        return completedTopics;
    }

    public void setCompletedTopics(long completedTopics)
    {
        this.completedTopics=completedTopics;
    }

    public long getPendingTopics()
    {
        return pendingTopics;
    }

    public void setPendingTopics(long pendingTopics)
    {
        this.pendingTopics=pendingTopics;
    }

    public long getDsaTopics()
    {
        return dsaTopics;
    }

    public void setDsaTopics(long dsaTopics)
    {
        this.dsaTopics=dsaTopics;
    }

    public double getCompletedPercentage()
    {
        return completedPercentage;
    }

    public void setCompletedPercentage(double completedPercentage)
    {
        this.completedPercentage=completedPercentage;
    }
}
