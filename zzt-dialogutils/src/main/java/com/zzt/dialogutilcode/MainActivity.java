package com.zzt.dialogutilcode;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.zzt.adapter.StartActivityRecyclerAdapter;
import com.zzt.dialogutilcode.apputil.AppObj;
import com.zzt.dialogutilcode.util.AppInfo;
import com.zzt.dialogutilcode.web.ActWebViewPrtSc;
import com.zzt.dialogutilcode.web.SrcWebAct;
import com.zzt.dialogutilcode.widget.dialog.QMUIDialog;
import com.zzt.dialogutilcode.widget.dialog.QMUIDialogAction;
import com.zzt.dialogutilcode.zdialog.BaseShowDismissAppCompatDialog;
import com.zzt.dialogutilcode.zdialog.MDialogA;
import com.zzt.dialogutilcode.zdialog.SSSDialog;
import com.zzt.dialogutilcode.zdialog.ZDialogAction;
import com.zzt.dialogutilcode.zdialog.ZDialogBuildHelp;
import com.zzt.dialogutilcode.zdialog.ZDialogImageView;
import com.zzt.dialogutilcode.zdialog.ZDialogTextView;
import com.zzt.dialogutilcode.zdialog.ZDialogUtil;
import com.zzt.entity.StartActivityDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    RecyclerView rv_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        List<AppInfo> allAppIofo = getAllAppIofo();
        Log.d(TAG, "所有应用列表：" + allAppIofo);

        getAppList(this, 0, false);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {
        LeftFixedEditText lfet_text = findViewById(R.id.lfet_text);
        lfet_text.setFixedText("$");

        rv_list = findViewById(R.id.rv_list);


        List<StartActivityDao> mListDialog = new ArrayList<>();
        mListDialog.add(new StartActivityDao("列表对话框", "", "1"));
        mListDialog.add(new StartActivityDao("确定取消对话框", "", "2"));
        mListDialog.add(new StartActivityDao("新的自定义的对话框3", "", "3"));
        mListDialog.add(new StartActivityDao("新的自定义的对话框4", "", "4"));
        mListDialog.add(new StartActivityDao("新的自定义的对话框5", "", "5"));
        mListDialog.add(new StartActivityDao("新的自定义的对话框6", "", "6"));
        mListDialog.add(new StartActivityDao("打开页面", "", "7"));
        mListDialog.add(new StartActivityDao("自定义布局对话框", "", "8"));

        StartActivityRecyclerAdapter.setAdapterData(rv_list, RecyclerView.VERTICAL, mListDialog, (itemView, position, data) -> {
            switch (data.getArouter()) {
                case "1":
//                    final String[] items = new String[]{"蓝色（默认）", "黑色", "白色"};
//                    new QMUIDialog.MenuDialogBuilder(MainActivity.this)
//                            .addItems(items, new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    log("点击了 " + which);
//                                    dialog.dismiss();
//                                }
//                            })
//                            .create()
//                            .show();

                    SrcWebAct.start(MainActivity.this);

//                    SSSDialog sssDialog = new SSSDialog(MainActivity.this);
//                    sssDialog.show();

                    break;
                case "2":
                    new QMUIDialog.MessageDialogBuilder(MainActivity.this)
                            .setTitle("这个是标题")
                            .setMessage("确认下载此文件？")
                            .addAction("去掉", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                    log("点击了 " + index);
                                }
                            })
                            .addAction("确定", new QMUIDialogAction.ActionListener() {
                                @Override
                                public void onClick(QMUIDialog dialog, int index) {
                                    dialog.dismiss();
                                    log("点击了 " + index);
                                }
                            })
                            .show();
                    break;
                case "3":
                    new ZDialogUtil.MessageDialogBuilder(MainActivity.this, R.style.Style_Base_Dialog)
                            .setMessage("sssssss")
                            .setRightDel(new ZDialogImageView.DialogImageViewListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog) {
                                    dialog.dismiss();
                                }
                            })
                            .addActionTopBlue(new ZDialogAction("AAA", new ZDialogAction.ActionListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            }))
                            .addActionBottomGray(new ZDialogAction("BBB", new ZDialogAction.ActionListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            }))
                            .show();
                    break;
                case "4":
                    new ZDialogUtil.MessageDialogBuilder(MainActivity.this, R.style.Style_Base_Dialog)
                            .setTopImage(com.zzt.toolslib.R.drawable.ic_compare)
                            .setRightDel(new ZDialogImageView.DialogImageViewListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog) {
                                    dialog.dismiss();
                                }
                            })
                            .setTitleMessage("ttttt", "MMMMMM")
                            .addActionTopBlue(new ZDialogAction("AAA", new ZDialogAction.ActionListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            }))
                            .addActionBottomGray(new ZDialogAction("BBB", new ZDialogAction.ActionListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            }))
                            .show();
                    break;
                case "5":
                    new ZDialogUtil.MessageDialogBuilder(MainActivity.this, R.style.Style_Base_Dialog)
                            .setTitleMessage("55555", "5555")
                            .addActionTopBlue(new ZDialogAction("AAA", new ZDialogAction.ActionListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            }))
                            .addActionBottomGray(new ZDialogAction("BBB", new ZDialogAction.ActionListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            }))

                            .show();
                    break;
                case "6":
                    new ZDialogUtil.MessageDialogBuilder(MainActivity.this, R.style.Style_Base_Dialog)
                            .setTitleMessage("66666", "66666")
                            .setBottomDel(new ZDialogImageView.DialogImageViewListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog) {
                                    dialog.dismiss();
                                }
                            })
                            .addActionTopBlue(new ZDialogAction("AAA", new ZDialogAction.ActionListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            }))
                            .addActionBottomGray(new ZDialogAction("BBB", new ZDialogAction.ActionListener() {
                                @Override
                                public void onClick(BaseShowDismissAppCompatDialog dialog, int index) {
                                    dialog.dismiss();
                                }
                            }))
                            .addTextView(new ZDialogTextView("后来添加的")
                                    .setTextColor(Color.parseColor("#252C58"))
                                    .setTextSize(18)
                                    .setLpMargin(0, 0, 0, 0)
                                    .setGravity(Gravity.CENTER)
                                    .setTypefaceStyle(Typeface.BOLD))
                            .setCancelable(false)
                            .setCanceledOnTouchOutside(false)
                            .show();
                    break;
                case "7":
                    DialogRootAct.start(MainActivity.this);
                    break;
                case "8":
                    MDialogA mDialogA = new MDialogA(MainActivity.this);
                    mDialogA.show();
                    break;
            }
        });
    }

    protected List<AppInfo> getAllAppIofo() {
        List<AppInfo> list = new ArrayList<AppInfo>();

        //得到应用packageManager
        PackageManager packageManager = getPackageManager();

        //创建一个主界面intent
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        //得到包含应用信息列表
        List<ResolveInfo> ResolveInfos = packageManager.queryIntentActivities(intent, 0);
        //遍历
        for (ResolveInfo ri : ResolveInfos) {
            ComponentInfo componentInfo = getComponentInfo(ri);
            String pk = "";
            if (componentInfo != null) {
                pk = componentInfo.packageName;
            }

            //得到包名
            String packageName = ri.resolvePackageName;
            //得到图标
            Drawable icon = ri.loadIcon(packageManager);
            //得到应用名称
            String appName = ri.loadLabel(packageManager).toString();


            Log.w(TAG, "所有应用列表 包名：" + pk + "        [[" + appName + "]]");

            //封装应用信息对象
            AppInfo appInfo = new AppInfo(icon, appName, packageName);
            //添加到list
            list.add(appInfo);
        }
        return list;
    }

    public ComponentInfo getComponentInfo(ResolveInfo ri) {
        if (ri.activityInfo != null) return ri.activityInfo;
        if (ri.serviceInfo != null) return ri.serviceInfo;
        if (ri.providerInfo != null) return ri.providerInfo;
        return null;
    }

    public static List<AppObj> getAppList(Activity context, int size, boolean usecache) {
        List<AppObj> appObjList = new ArrayList<>();
        try {
//            Map<String, MyUsageStats> usageStatsMap = PkgUseStatsUtil.getUsageStats(context);
            PackageManager pm = context.getPackageManager();
            // Return a List of all packages that are installed on the device.
            /**
             * As of Android 11, this method no longer returns information about all apps; see https://g.co/dev/packagevisibility for details
             */
            List<PackageInfo> packages = pm.getInstalledPackages(0);
//            Intent intent = new Intent(Intent.ACTION_MAIN);
////            intent.addCategory(Intent.CATEGORY_DEFAULT);
//            intent.addCategory(Intent.CATEGORY_LAUNCHER);
//            int index = 1;
//            List<ResolveInfo> list = pm.queryIntentActivities(intent, 0);
//            try {
//                for (ResolveInfo resolveInfo : list) {
//                    Log.e(TAG, "resolveInfo " + resolveInfo.activityInfo.packageName + "firstInstallTime=" + context.getPackageManager().getPackageInfo(resolveInfo.activityInfo.packageName, 0).firstInstallTime + "=update=" + context.getPackageManager().getPackageInfo(resolveInfo.activityInfo.packageName, 0).lastUpdateTime);
//                }
//            } catch (Exception e) {
//                Log.e(TAG, "获取应用安装信息报错!!!!");
//            }


            //已经缓存过的apps
            for (PackageInfo packageInfo : packages) {
                // 判断系统/非系统应用
                if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    AppObj appObj = new AppObj(packageInfo.applicationInfo.loadLabel(pm).toString(), packageInfo.packageName);
                    appObj.setIt(packageInfo.firstInstallTime + "");
                    appObj.setUt(packageInfo.lastUpdateTime + "");

//                    appObj.setLl(PkgUseStatsUtil.getNetworkStats(context, packageInfo.packageName));
//                        if (usageStatsMap != null && usageStatsMap.containsKey(packageInfo.packageName)) {
//                            MyUsageStats usageStats = usageStatsMap.get(packageInfo.packageName);
//                            if (usageStats != null) {
//                                appObj.setOc(usageStats.getLaunchCount());
//                                appObj.setLt(usageStats.getLastTimeUsed());
//                                appObj.setRt(usageStats.getTotalTimeInForeground());
//                            }
//                        }

                    appObjList.add(appObj);
                } else {
                    // 系统应用
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i(TAG, "所有应用列表 2=" + appObjList.toString());
        return appObjList;
    }

    public void log(String str) {
        Log.d(TAG, ">>>>>" + str);
        Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
    }
}