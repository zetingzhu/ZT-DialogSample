//package com.zzt.dialogutilcode.apputil;
//
//import android.Manifest;
//import android.app.Activity;
//import android.app.usage.NetworkStats;
//import android.app.usage.NetworkStatsManager;
//import android.app.usage.UsageStats;
//import android.app.usage.UsageStatsManager;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.net.ConnectivityManager;
//
//import androidx.core.app.ActivityCompat;
//import android.telephony.TelephonyManager;
//import android.util.Log;
//
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.math.BigDecimal;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//import static android.content.Context.NETWORK_STATS_SERVICE;
//
///**
// * Created by adu on 2019/6/10.
// */
//public class PkgUseStatsUtil {
//    public static final int reqCode = 10101;
//    private static String TAG = "PkgUseStatsUtil";
//
//    public static boolean checkAppUsagePermission(Context context) {
//        try {
//            UsageStatsManager usageStatsManager = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                usageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
//            }
//            if (usageStatsManager == null) {
//                return false;
//            }
//            Calendar calendar = Calendar.getInstance();
//            long endTime = calendar.getTimeInMillis();
//            calendar.add(Calendar.YEAR, -1);
//            long startTime = calendar.getTimeInMillis();
//
//            long currentTime = System.currentTimeMillis();
//            // try to get app usage state in last 1 min
//            List<UsageStats> stats = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                //            stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, currentTime - 60 * 1000, currentTime);
//                stats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, startTime, endTime);
//
//            }
//            if (stats.size() > 0) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return false;
//    }
//
//    public static void requestAppUsagePermission(Activity context) {
//        try {
//            Intent intent = new Intent(android.provider.Settings.ACTION_USAGE_ACCESS_SETTINGS);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            context.startActivityForResult(intent, reqCode);
//        } catch (Exception e) {
//            e.printStackTrace();
//            Log.v(TAG, "Start usage access settings activity fail!");
//        }
//    }
//
//    /**
//     * > 5.0 && Permission
//     *
//     * @param context
//     * @return
//     */
//    public static Map<String, MyUsageStats> getUsageStats(Activity context) {
//        try {
//            Log.v(TAG, "getUsageStatsList SDK_INT =" + android.os.Build.VERSION.SDK_INT);
//            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
//                return null;
//            }
//            UsageStatsManager usm = null;
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                usm = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
//            }
//            if (usm == null) {
//                Log.v(TAG, "getUsageStatsManager==null");
//                return null;
//            }
//            if (!checkAppUsagePermission(context)) {
////                requestAppUsagePermission(context);
//                return null;
//            }
//
//            Calendar calendar = Calendar.getInstance();
//            long endTime = calendar.getTimeInMillis();
//            calendar.add(Calendar.YEAR, -1);//从当前时间往前推算
//            long startTime = calendar.getTimeInMillis();
//            Log.v(TAG, "getUsageStatsList start:" + DateUtil.formatDate(new Date(startTime), "yyyy-MM-dd HH:mm:ss"));
//            Log.v(TAG, "getUsageStatsList end:" + DateUtil.formatDate(new Date(endTime), "yyyy-MM-dd HH:mm:ss"));
//
////            List<UsageStats> usageStatsList = null;
////            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
////            使用 usm.queryUsageStats得到list的方式 同一个pkg会有多个数据;
////                usageStatsList = usm.queryUsageStats(UsageStatsManager.INTERVAL_BEST, startTime, endTime);
////            }
//
//            Map<String, MyUsageStats> usageStatsMap = new LinkedHashMap<>();
//            //这种方式查出来的才是总的
//            Map<String, UsageStats> map = usm.queryAndAggregateUsageStats(startTime, endTime);
//            for (Map.Entry<String, UsageStats> entry : map.entrySet()) {
//                UsageStats stats = entry.getValue();
//                Log.v(TAG, "queryAndAggregateUsageStats getPkg=" + stats.getPackageName());
//                //不需要系统app
//                if (isSystemApp(context, stats.getPackageName()))
//                    continue;
//                int count = 0;
//                try {
//                    Field field = stats.getClass().getDeclaredField("mLaunchCount");
//                    field.setAccessible(true);
//                    count = field.getInt(stats);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                usageStatsMap.put(stats.getPackageName(), new MyUsageStats(stats.getPackageName(),
//                        stats.getTotalTimeInForeground(),
//                        stats.getLastTimeUsed(),
//                        count));
//                Log.v(TAG, "queryAndAggregateUsageStats getPackageName:" + stats.getPackageName()
//                        + " getTotalTimeInForeground=" + stats.getTotalTimeInForeground()
//                        + " getLastTimeUsed=" + stats.getLastTimeUsed()
//                        + " mLaunchCount=" + count
//                );
//            }
//            return usageStatsMap;
//        } catch (Exception e) {
//            e.printStackTrace();
//        } catch (Error error) {
//            error.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public static String getSubscriberId(Context context, int networkType) {
//        if (ConnectivityManager.TYPE_MOBILE == networkType) {
//            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
//            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
//                PermissHelp permissHelp = new PermissHelp();
//                permissHelp.requestBasicPermission((Activity) context);
//                return "";
//            }
//            return tm.getSubscriberId();
//        }
//        return "";
//    }
//
//    public static long getNetworkStats(Activity context, String pkg) {
//        Log.v(TAG, "SDK_INT =" + android.os.Build.VERSION.SDK_INT);
//        try {
//            if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {
//                return 0;
//            }
//            if (StringUtil.isEmpty(pkg))
//                return 0;
//
//            if (!checkAppUsagePermission(context)) {
////            requestAppUsagePermission(context);
//                return 0;
//            }
//
//            Calendar calendar = Calendar.getInstance();
//            long endTime = calendar.getTimeInMillis();
//            calendar.add(Calendar.YEAR, -1);
//            long startTime = calendar.getTimeInMillis();
//
//            int uid = getPkgUid(context, pkg);
//            NetworkStatsManager networkStatsManager = (NetworkStatsManager) context.getSystemService(NETWORK_STATS_SERVICE);
//            //wifi流量
//            long lwifi = 0;
//            long lmobile = 0;
//            NetworkStats networkStats = networkStatsManager.queryDetailsForUid(ConnectivityManager.TYPE_WIFI, "", startTime, endTime, uid);
//            do {
//                NetworkStats.Bucket b = new NetworkStats.Bucket();
//                networkStats.getNextBucket(b);
//                lwifi += b.getRxBytes() + b.getTxBytes();
//            } while (networkStats.hasNextBucket());
//            Log.v(TAG, "lwifi==" + lwifi);
//
//            //moblie流量
//            String subid = getSubscriberId(context, ConnectivityManager.TYPE_MOBILE);
//            Log.v(TAG, "subid==" + subid);
//            if (subid != null) {
//                NetworkStats networkStats2 = networkStatsManager.queryDetailsForUid(ConnectivityManager.TYPE_MOBILE, subid, startTime, endTime, uid);
//                do {
//                    NetworkStats.Bucket b = new NetworkStats.Bucket();
//                    networkStats2.getNextBucket(b);
//                    lmobile += b.getRxBytes() + b.getTxBytes();
//                    Log.v(TAG, "lmobile==" + lmobile);
//                } while (networkStats2.hasNextBucket());
//            }
//            Log.v(TAG, "lwifi+lmobile==" + pkg + " " + (lwifi + lmobile) + "Byte");
//            return (lwifi + lmobile);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } catch (Error error){
//            error.printStackTrace();
//        }
//
//        return 0;
//    }
//
//
//    public static Map<String, Long> getNetworkStatsAll(Activity context) {
//        Log.v(TAG, "SDK_INT =" + android.os.Build.VERSION.SDK_INT);
//        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.M) {
//            return null;
//        }
//        if (!checkAppUsagePermission(context)) {
////            requestAppUsagePermission(context);
//            return null;
//        }
//
//        Map<String, Long> stringLongMap = new LinkedHashMap<>();
//
//        Calendar calendar = Calendar.getInstance();
//        long endTime = calendar.getTimeInMillis();
//        calendar.add(Calendar.YEAR, -1);
//        long startTime = calendar.getTimeInMillis();
//
//        NetworkStatsManager networkStatsManager = (NetworkStatsManager) context.getSystemService(NETWORK_STATS_SERVICE);
//
//        for (ApplicationInfo info : context.getPackageManager().getInstalledApplications(PackageManager.MATCH_UNINSTALLED_PACKAGES)) {
//            if ((info.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
//                //系统app
//                continue;
//            }
//            Log.v(TAG, "uid=" + info.uid);
//            //wifi流量
//            long lwifi = 0;
//            long lmobile = 0;
//            NetworkStats networkStats = networkStatsManager.queryDetailsForUid(ConnectivityManager.TYPE_WIFI, "", startTime, endTime, info.uid);
//            do {
//                NetworkStats.Bucket b = new NetworkStats.Bucket();
//                networkStats.getNextBucket(b);
//                lwifi += b.getRxBytes() + b.getTxBytes();
//            } while (networkStats.hasNextBucket());
//            Log.v(TAG, "lwifi==" + lwifi);
//
//            //moblie流量
//            String subid = getSubscriberId(context, ConnectivityManager.TYPE_MOBILE);
//            Log.v(TAG, "subid==" + subid);
//            if (subid != null) {
//                NetworkStats networkStats2 = networkStatsManager.queryDetailsForUid(ConnectivityManager.TYPE_MOBILE, subid, startTime, endTime, info.uid);
//                do {
//                    NetworkStats.Bucket b = new NetworkStats.Bucket();
//                    networkStats2.getNextBucket(b);
//                    lmobile += b.getRxBytes() + b.getTxBytes();
//                    Log.v(TAG, "lmobile==" + lmobile);
//                } while (networkStats2.hasNextBucket());
//            }
//            Log.v(TAG, "lwifi+lmobile==" + info.packageName + " " + (lwifi + lmobile) + "Byte");
//            stringLongMap.put(info.processName, (lwifi + lmobile));
//        }
//        return stringLongMap;
//    }
//
//    public static int getPkgUid(Context context, String pkgName) {
//        PackageInfo pi = null;
//        try {
//            PackageManager pm = context.getPackageManager();
//            pi = pm.getPackageInfo(pkgName, 0);
//            return pi.applicationInfo.uid;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    /**
//     * 测试发现oppo手机不准
//     *
//     * @param context
//     * @param pkgName
//     * @return
//     */
//    public static boolean isSystemApp(Context context, String pkgName) {
//        try {
//            PackageInfo pi = null;
//            try {
//                PackageManager pm = context.getPackageManager();
//                pi = pm.getPackageInfo(pkgName, 0);
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//            // 是系统中已安装的应用
//            if (pi != null) {
//                return ((pi.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//
//    /**
//     * 字节转换为M
//     */
//    private static BigDecimal byteToM(long value) {
//        BigDecimal result = new BigDecimal(Long.toString(value));
//        result = result.divide(new BigDecimal("1024.0")).divide(new BigDecimal("1024.0"));
//        return result;
//    }
//
//
//    public static void getPkgUsageStats() {
//        Log.v(TAG, "[getPkgUsageStats]");
//        try {
//            Class<?> cServiceManager = Class
//                    .forName("android.os.ServiceManager");
//            Method mGetService = cServiceManager.getMethod("getService",
//                    String.class);
//            Object oRemoteService = mGetService.invoke(null, "usagestats");
//
//            // IUsageStats oIUsageStats =
//            // IUsageStats.Stub.asInterface(oRemoteService)
//            Class<?> cStub = Class
//                    .forName("com.android.internal.app.IUsageStats$Stub");
//            Method mUsageStatsService = cStub.getMethod("asInterface",
//                    android.os.IBinder.class);
//            Object oIUsageStats = mUsageStatsService.invoke(null,
//                    oRemoteService);
//
//            // PkgUsageStats[] oPkgUsageStatsArray =
//            // mUsageStatsService.getAllPkgUsageStats();
//            Class<?> cIUsageStatus = Class
//                    .forName("com.android.internal.app.IUsageStats");
//            Method mGetAllPkgUsageStats = cIUsageStatus.getMethod(
//                    "getAllPkgUsageStats", (Class[]) null);
//            Object[] oPkgUsageStatsArray = (Object[]) mGetAllPkgUsageStats
//                    .invoke(oIUsageStats, (Object[]) null);
//            Log.v(TAG, "[getPkgUsageStats] oPkgUsageStatsArray = " + oPkgUsageStatsArray);
//
//            Class<?> cPkgUsageStats = Class
//                    .forName("com.android.internal.os.PkgUsageStats");
//
//            StringBuffer sb = new StringBuffer();
//            sb.append("nerver used : ");
//            for (Object pkgUsageStats : oPkgUsageStatsArray) {
//                // get pkgUsageStats.packageName, pkgUsageStats.launchCount,
//                // pkgUsageStats.usageTime
//                String packageName = (String) cPkgUsageStats.getDeclaredField(
//                        "packageName").get(pkgUsageStats);
//                int launchCount = cPkgUsageStats
//                        .getDeclaredField("launchCount").getInt(pkgUsageStats);
//                long usageTime = cPkgUsageStats.getDeclaredField("usageTime")
//                        .getLong(pkgUsageStats);
//                if (launchCount > 0)
//                    Log.v(TAG, "[getPkgUsageStats] " + packageName + "  count: "
//                            + launchCount + "  time:  " + usageTime);
//                else {
//                    sb.append(packageName + "; ");
//                }
//            }
//            Log.v(TAG, "[getPkgUsageStats] " + sb.toString());
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
