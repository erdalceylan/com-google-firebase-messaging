//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.firebase.messaging;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.os.SystemClock;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat.BigTextStyle;
import android.support.v4.app.NotificationCompat.Builder;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.R.string;
import com.google.android.gms.common.util.zzp;
import com.google.firebase.iid.zzq;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.MissingFormatArgumentException;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONException;

final class zza {
    private static zza zzmjt;
    private final Context mContext;
    private Bundle zzfqi;
    private Method zzhqj;
    private Method zzhqk;
    private final AtomicInteger zzmju = new AtomicInteger((int)SystemClock.elapsedRealtime());

    static synchronized zza zzep(Context var0) {
        if(zzmjt == null) {
            zzmjt = new zza(var0);
        }

        return zzmjt;
    }

    static boolean zzac(Bundle var0) {
        return "1".equals(zze(var0, "gcm.n.e")) || zze(var0, "gcm.n.icon") != null;
    }

    static String zze(Bundle var0, String var1) {
        String var2;
        if((var2 = var0.getString(var1)) == null) {
            var2 = var0.getString(var1.replace("gcm.n.", "gcm.notification."));
        }

        return var2;
    }

    static String zzh(Bundle var0, String var1) {
        String var10001 = String.valueOf(var1);
        String var10002 = String.valueOf("_loc_key");
        if(var10002.length() != 0) {
            var10001 = var10001.concat(var10002);
        } else {
            String var10003 = new String(var10002);
            var10001 = var10003;
        }

        return zze(var0, var10001);
    }

    static Object[] zzi(Bundle var0, String var1) {
        String var10001 = String.valueOf(var1);
        String var10002 = String.valueOf("_loc_args");
        String var10003;
        if(var10002.length() != 0) {
            var10001 = var10001.concat(var10002);
        } else {
            var10003 = new String(var10002);
            var10002 = var10001;
            var10001 = var10003;
        }

        String var2;
        if(TextUtils.isEmpty(var2 = zze(var0, var10001))) {
            return null;
        } else {
            try {
                JSONArray var3;
                String[] var4 = new String[(var3 = new JSONArray(var2)).length()];

                for(int var8 = 0; var8 < var4.length; ++var8) {
                    var4[var8] = (String)var3.opt(var8);
                }

                return var4;
            } catch (JSONException var7) {
                var10001 = String.valueOf(var1);
                var10002 = String.valueOf("_loc_args");
                if(var10002.length() != 0) {
                    var10001 = var10001.concat(var10002);
                } else {

                }

                String var5 = var10001.substring(6);
                Log.w("FirebaseMessaging", (new StringBuilder(41 + String.valueOf(var5).length() + String.valueOf(var2).length())).append("Malformed ").append(var5).append(": ").append(var2).append("  Default value will be used.").toString());
                return null;
            }
        }
    }

    @Nullable
    static Uri zzad(@NonNull Bundle var0) {
        String var1;
        if(TextUtils.isEmpty(var1 = zze(var0, "gcm.n.link_android"))) {
            var1 = zze(var0, "gcm.n.link");
        }

        return !TextUtils.isEmpty(var1)?Uri.parse(var1):null;
    }

    private zza(Context var1) {
        this.mContext = var1.getApplicationContext();
    }

    final boolean zzr(Bundle var1) {
        if("1".equals(zze(var1, "gcm.n.noui"))) {
            return true;
        } else {
            boolean var10000;
            label120: {
                if(!((KeyguardManager)this.mContext.getSystemService(Context.KEYGUARD_SERVICE)).inKeyguardRestrictedInputMode()) {
                    if(!zzp.zzalj()) {
                        SystemClock.sleep(10L);
                    }

                    int var5 = Process.myPid();
                    List var7;
                    if((var7 = ((ActivityManager)this.mContext.getSystemService(Context.ACTIVITY_SERVICE)).getRunningAppProcesses()) != null) {
                        Iterator var8 = var7.iterator();

                        while(var8.hasNext()) {
                            RunningAppProcessInfo var9;
                            if((var9 = (RunningAppProcessInfo)var8.next()).pid == var5) {
                                var10000 = var9.importance == 100;
                                break label120;
                            }
                        }
                    }
                }

                var10000 = false;
            }

            if(var10000) {
                return false;
            } else {
                Object var22;
                if(TextUtils.isEmpty((CharSequence)(var22 = this.zzj(var1, "gcm.n.title")))) {
                    var22 = this.mContext.getApplicationInfo().loadLabel(this.mContext.getPackageManager());
                }

                String var6;
                String var14;
                int var30;
                label124: {
                    var6 = this.zzj(var1, "gcm.n.body");
                    var14 = zze(var1, "gcm.n.icon");
                    if(!TextUtils.isEmpty(var14)) {
                        Resources var15;
                        int var16;
                        if((var16 = (var15 = this.mContext.getResources()).getIdentifier(var14, "drawable", this.mContext.getPackageName())) != 0) {
                            var30 = var16;
                            break label124;
                        }

                        if((var16 = var15.getIdentifier(var14, "mipmap", this.mContext.getPackageName())) != 0) {
                            var30 = var16;
                            break label124;
                        }

                        Log.w("FirebaseMessaging", (new StringBuilder(61 + String.valueOf(var14).length())).append("Icon resource ").append(var14).append(" not found. Notification will use default icon.").toString());
                    }

                    int var28;
                    if((var28 = this.zzasg().getInt("com.google.firebase.messaging.default_notification_icon", 0)) == 0) {
                        var28 = this.mContext.getApplicationInfo().icon;
                    }

                    if(var28 == 0) {
                        var28 = 17301651;
                    }

                    var30 = var28;
                }

                int var24 = var30;
                Integer var26 = this.zzqc(zze(var1, "gcm.n.color"));
                var14 = zzae(var1);
                Uri var32;
                if(TextUtils.isEmpty(var14)) {
                    var32 = null;
                } else if(!"default".equals(var14) && this.mContext.getResources().getIdentifier(var14, "raw", this.mContext.getPackageName()) != 0) {
                    String var17 = "android.resource://";
                    String var18 = this.mContext.getPackageName();
                    var32 = Uri.parse((new StringBuilder(5 + String.valueOf(var17).length() + String.valueOf(var18).length() + String.valueOf(var14).length())).append(var17).append(var18).append("/raw/").append(var14).toString());
                } else {
                    var32 = RingtoneManager.getDefaultUri(2);
                }

                Uri var27 = var32;
                PendingIntent var10 = this.zzs(var1);
                PendingIntent var11 = null;
                if(FirebaseMessagingService.zzaf(var1)) {
                    Intent var29;
                    zza(var29 = new Intent("com.google.firebase.messaging.NOTIFICATION_OPEN"), var1);
                    var29.putExtra("pending_intent", var10);
                    var10 = zzq.zzb(this.mContext, this.zzmju.incrementAndGet(), var29, 1073741824);
                    Intent var31;
                    zza(var31 = new Intent("com.google.firebase.messaging.NOTIFICATION_DISMISS"), var1);
                    var11 = zzq.zzb(this.mContext, this.zzmju.incrementAndGet(), var31, 1073741824);
                }

                Notification var33;
                if(zzp.isAtLeastO() && this.mContext.getApplicationInfo().targetSdkVersion > 25) {
                    String var12 = this.zzqd(zze(var1, "gcm.n.android_channel_id"));
                    var33 = this.zza((CharSequence)var22, var6, var24, var26, var27, var10, var11, var12);
                } else {
                    Builder var21 = (new Builder(this.mContext)).setAutoCancel(true).setSmallIcon(var24);
                    if(!TextUtils.isEmpty((CharSequence)var22)) {
                        var21.setContentTitle((CharSequence)var22);
                    }

                    if(!TextUtils.isEmpty(var6)) {
                        var21.setContentText(var6);
                        var21.setStyle((new BigTextStyle()).bigText(var6));
                    }

                    if(var26 != null) {
                        var21.setColor(var26.intValue());
                    }

                    if(var27 != null) {
                        var21.setSound(var27);
                    }

                    if(var10 != null) {
                        var21.setContentIntent(var10);
                    }

                    if(var11 != null) {
                        var21.setDeleteIntent(var11);
                    }

                    var33 = var21.build();
                }

                Notification var2 = var33;
                String var4 = zze(var1, "gcm.n.tag");
                if(Log.isLoggable("FirebaseMessaging", Log.DEBUG)) {
                    Log.d("FirebaseMessaging", "Showing notification");
                }

                NotificationManager var23 = (NotificationManager)this.mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                if(TextUtils.isEmpty(var4)) {
                    long var25 = SystemClock.uptimeMillis();
                    var4 = (new StringBuilder(37)).append("FCM-Notification:").append(var25).toString();
                }

                var23.notify(var4, 0, var2);
                return true;
            }
        }
    }

    @TargetApi(26)
    private final Notification zza(CharSequence var1, String var2, int var3, Integer var4, Uri var5, PendingIntent var6, PendingIntent var7, String var8) {
        Notification.Builder var9 = (new Notification.Builder(this.mContext)).setAutoCancel(true).setSmallIcon(var3);
        if(!TextUtils.isEmpty(var1)) {
            var9.setContentTitle(var1);
        }

        if(!TextUtils.isEmpty(var2)) {
            var9.setContentText(var2);
            var9.setStyle((new Notification.BigTextStyle()).bigText(var2));
        }

        if(var4 != null) {
            var9.setColor(var4.intValue());
        }

        if(var5 != null) {
            var9.setSound(var5);
        }

        if(var6 != null) {
            var9.setContentIntent(var6);
        }

        if(var7 != null) {
            var9.setDeleteIntent(var7);
        }

        if(var8 != null) {
            if(this.zzhqj == null) {
                this.zzhqj = zzhq("setChannelId");
            }

            if(this.zzhqj == null) {
                this.zzhqj = zzhq("setChannel");
            }

            if(this.zzhqj == null) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel");
            } else {
                try {
                    this.zzhqj.invoke(var9, new Object[]{var8});
                } catch (IllegalAccessException var11) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", var11);
                } catch (InvocationTargetException var12) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", var12);
                } catch (SecurityException var13) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", var13);
                } catch (IllegalArgumentException var14) {
                    Log.e("FirebaseMessaging", "Error while setting the notification channel", var14);
                }
            }
        }

        return var9.build();
    }

    @TargetApi(26)
    private static Method zzhq(String var0) {
        try {
            return Notification.Builder.class.getMethod(var0, new Class[]{String.class});
        } catch (NoSuchMethodException var1) {
            ;
        } catch (SecurityException var2) {
            ;
        }

        return null;
    }

    private final String zzj(Bundle var1, String var2) {
        String var3;
        if(!TextUtils.isEmpty(var3 = zze(var1, var2))) {
            return var3;
        } else {
            String var4;
            if(TextUtils.isEmpty(var4 = zzh(var1, var2))) {
                return null;
            } else {
                Resources var5;
                int var6;
                if((var6 = (var5 = this.mContext.getResources()).getIdentifier(var4, "string", this.mContext.getPackageName())) == 0) {
                    String var10001 = String.valueOf(var2);
                    String var10002 = String.valueOf("_loc_key");
                    if(var10002.length() != 0) {
                        var10001 = var10001.concat(var10002);
                    } else {

                    }

                    String var12 = var10001.substring(6);
                    Log.w("FirebaseMessaging", (new StringBuilder(49 + String.valueOf(var12).length() + String.valueOf(var4).length())).append(var12).append(" resource not found: ").append(var4).append(" Default value will be used.").toString());
                    return null;
                } else {
                    Object[] var7;
                    if((var7 = zzi(var1, var2)) == null) {
                        return var5.getString(var6);
                    } else {
                        try {
                            return var5.getString(var6, var7);
                        } catch (MissingFormatArgumentException var11) {
                            String var9 = Arrays.toString(var7);
                            Log.w("FirebaseMessaging", (new StringBuilder(58 + String.valueOf(var4).length() + String.valueOf(var9).length())).append("Missing format argument for ").append(var4).append(": ").append(var9).append(" Default value will be used.").toString(), var11);
                            return null;
                        }
                    }
                }
            }
        }
    }

    private final Integer zzqc(String var1) {
        if(VERSION.SDK_INT < 21) {
            return null;
        } else {
            if(!TextUtils.isEmpty(var1)) {
                try {
                    return Integer.valueOf(Color.parseColor(var1));
                } catch (IllegalArgumentException var4) {
                    Log.w("FirebaseMessaging", (new StringBuilder(54 + String.valueOf(var1).length())).append("Color ").append(var1).append(" not valid. Notification will use default color.").toString());
                }
            }

            int var2;
            if((var2 = this.zzasg().getInt("com.google.firebase.messaging.default_notification_color", 0)) != 0) {
                try {
                    return Integer.valueOf(ContextCompat.getColor(this.mContext, var2));
                } catch (NotFoundException var3) {
                    Log.w("FirebaseMessaging", "Cannot find the color resource referenced in AndroidManifest.");
                }
            }

            return null;
        }
    }

    @TargetApi(26)
    private final String zzqd(String var1) {
        if(!zzp.isAtLeastO()) {
            return null;
        } else {
            NotificationManager var2 = (NotificationManager)this.mContext.getSystemService(NotificationManager.class);

            try {
                if(this.zzhqk == null) {
                    this.zzhqk = var2.getClass().getMethod("getNotificationChannel", new Class[]{String.class});
                }

                if(!TextUtils.isEmpty(var1)) {
                    if(this.zzhqk.invoke(var2, new Object[]{var1}) != null) {
                        return var1;
                    }

                    Log.w("FirebaseMessaging", (new StringBuilder(122 + String.valueOf(var1).length())).append("Notification Channel requested (").append(var1).append(") has not been created by the app. Manifest configuration, or default, value will be used.").toString());
                }

                String var3;
                if(!TextUtils.isEmpty(var3 = this.zzasg().getString("com.google.firebase.messaging.default_notification_channel_id"))) {
                    if(this.zzhqk.invoke(var2, new Object[]{var3}) != null) {
                        return var3;
                    }

                    Log.w("FirebaseMessaging", "Notification Channel set in AndroidManifest.xml has not been created by the app. Default value will be used.");
                } else {
                    Log.w("FirebaseMessaging", "Missing Default Notification Channel metadata in AndroidManifest. Default value will be used.");
                }

                if(this.zzhqk.invoke(var2, new Object[]{"fcm_fallback_notification_channel"}) == null) {
                    Class var4;
                    Object var5 = (var4 = Class.forName("android.app.NotificationChannel")).getConstructor(new Class[]{String.class, CharSequence.class, Integer.TYPE}).newInstance(new Object[]{"fcm_fallback_notification_channel", "fcm_fallback_notification_channel_label", Integer.valueOf(3)});
                    var2.getClass().getMethod("createNotificationChannel", new Class[]{var4}).invoke(var2, new Object[]{var5});
                }

                return "fcm_fallback_notification_channel";
            } catch (InstantiationException var6) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel", var6);
            } catch (InvocationTargetException var7) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel", var7);
            } catch (NoSuchMethodException var8) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel", var8);
            } catch (IllegalAccessException var9) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel", var9);
            } catch (ClassNotFoundException var10) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel", var10);
            } catch (SecurityException var11) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel", var11);
            } catch (IllegalArgumentException var12) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel", var12);
            } catch (LinkageError var13) {
                Log.e("FirebaseMessaging", "Error while setting the notification channel", var13);
            }

            return null;
        }
    }

    static String zzae(Bundle var0) {
        String var1;
        if(TextUtils.isEmpty(var1 = zze(var0, "gcm.n.sound2"))) {
            var1 = zze(var0, "gcm.n.sound");
        }

        return var1;
    }

    private final PendingIntent zzs(Bundle var1) {
        Intent var10000;
        String var7;
        if(!TextUtils.isEmpty(var7 = zze(var1, "gcm.n.click_action"))) {
            Intent var8;
            (var8 = new Intent(var7)).setPackage(this.mContext.getPackageName());
            var8.setFlags(268435456);
            var10000 = var8;
        } else {
            Uri var11;
            if((var11 = zzad(var1)) != null) {
                Intent var9;
                (var9 = new Intent("android.intent.action.VIEW")).setPackage(this.mContext.getPackageName());
                var9.setData(var11);
                var10000 = var9;
            } else {
                Intent var10;
                if((var10 = this.mContext.getPackageManager().getLaunchIntentForPackage(this.mContext.getPackageName())) == null) {
                    Log.w("FirebaseMessaging", "No activity found to launch app");
                }

                var10000 = var10;
            }
        }

        Intent var2 = var10000;
        if(var10000 == null) {
            return null;
        } else {
            var2.addFlags(67108864);
            FirebaseMessagingService.zzp(var1 = new Bundle(var1));
            var2.putExtras(var1);
            Iterator var3 = var1.keySet().iterator();

            while(true) {
                String var4;
                do {
                    if(!var3.hasNext()) {
                        return PendingIntent.getActivity(this.mContext, this.zzmju.incrementAndGet(), var2, PendingIntent.FLAG_ONE_SHOT);
                    }
                } while(!(var4 = (String)var3.next()).startsWith("gcm.n.") && !var4.startsWith("gcm.notification."));

                var2.removeExtra(var4);
            }
        }
    }

    private static void zza(Intent var0, Bundle var1) {
        Iterator var2 = var1.keySet().iterator();

        while(true) {
            String var3;
            do {
                if(!var2.hasNext()) {
                    return;
                }
            } while(!(var3 = (String)var2.next()).startsWith("google.c.a.") && !var3.equals("from"));

            var0.putExtra(var3, var1.getString(var3));
        }
    }

    private final Bundle zzasg() {
        if(this.zzfqi != null) {
            return this.zzfqi;
        } else {
            ApplicationInfo var1 = null;

            try {
                var1 = this.mContext.getPackageManager().getApplicationInfo(this.mContext.getPackageName(), PackageManager.GET_META_DATA);
            } catch (NameNotFoundException var2) {
                ;
            }

            if(var1 != null && var1.metaData != null) {
                this.zzfqi = var1.metaData;
                return this.zzfqi;
            } else {
                return Bundle.EMPTY;
            }
        }
    }
}
