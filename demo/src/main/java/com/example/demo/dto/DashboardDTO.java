package com.example.demo.dto;

public class DashboardDTO {

    private long totalTopics;
    private long completedTopics;
    private long pendingTopics;
    private long dsaTopics;
    private double completedPercentage;
    private long javaTopics;
    private long oopsTopics;
    private long totalHoursSpent;
    private long ongoingTopics;
    private double averageHoursSpent;
    private String mostTimeSpent;
    private long targetHours;
    private long remainingHours;
    private double goalPercentage;


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

    public long getJavaTopics()
    {
        return javaTopics;
    }

    public void setJavaTopics(long javaTopics)
    {
        this.javaTopics=javaTopics;
    }

    public long getOopsTopics()
    {
        return oopsTopics;
    }

    public void setOopsTopics(long oopsTopics)
    {
        this.oopsTopics=oopsTopics;
    }

    public long getTotalHoursSpent()
    {
        return totalHoursSpent;
    }

    public void setTotalHoursSpent(long totalHoursSpent)
    {
        this.totalHoursSpent=totalHoursSpent;
    }

    public long getOngoingTopics()
    {
        return ongoingTopics;
    }

    public void setOngoingTopics(long ongoingTopics)
    {
        this.ongoingTopics=ongoingTopics;
    }

    public double getAverageHoursSpent()
    {
        return averageHoursSpent;
    }

    public void setAverageHoursSpent(double averageHoursSpent)
    {
        this.averageHoursSpent=averageHoursSpent;
    }

    public String getMostTimeSpent()
    {
        return mostTimeSpent;
    }

    public void setMostTimeSpent(String mostTimeSpent)
    {
        this.mostTimeSpent=mostTimeSpent;
    }

    public long getTargetHours()
    {
        return targetHours;
    }

    public void setTargetHours(long targetHours)
    {
        this.targetHours=targetHours;
    }

    public long getRemainingHours()
    {
        return remainingHours;
    }

    public void setRemainingHours(long remainingHours)
    {
        this.remainingHours=remainingHours;
    }

    public double getGoalPercentage()
    {
        return goalPercentage;
    }

    public void setGoalPercentage(double goalPercentage)
    {
        this.goalPercentage=goalPercentage;
    }

}
