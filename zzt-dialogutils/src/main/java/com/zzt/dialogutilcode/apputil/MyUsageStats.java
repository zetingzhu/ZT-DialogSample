package com.zzt.dialogutilcode.apputil;

/**
 * Created by adu on 2019-06-20.
 */
public class MyUsageStats {
    private String packageName;
    long totalTimeInForeground;
    long lastTimeUsed;
    int launchCount;

    public MyUsageStats() {
    }

    public MyUsageStats(String packageName, long totalTimeInForeground, long lastTimeUsed, int launchCount) {
        this.packageName = packageName;
        this.totalTimeInForeground = totalTimeInForeground;
        this.lastTimeUsed = lastTimeUsed;
        this.launchCount = launchCount;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getTotalTimeInForeground() {
        return totalTimeInForeground;
    }

    public void setTotalTimeInForeground(long totalTimeInForeground) {
        this.totalTimeInForeground = totalTimeInForeground;
    }

    public long getLastTimeUsed() {
        return lastTimeUsed;
    }

    public void setLastTimeUsed(long lastTimeUsed) {
        this.lastTimeUsed = lastTimeUsed;
    }

    public int getLaunchCount() {
        return launchCount;
    }

    public void setLaunchCount(int launchCount) {
        this.launchCount = launchCount;
    }
}
