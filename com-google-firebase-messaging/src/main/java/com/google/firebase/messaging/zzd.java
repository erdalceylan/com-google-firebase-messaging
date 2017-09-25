//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import com.google.android.gms.measurement.AppMeasurement;

final class zzd {
    public static void zzg(Context var0, Intent var1) {
        String var4;
        if((var4 = var1.getStringExtra("google.c.a.abt")) != null) {
            byte[] var5 = Base64.decode(var4, 0);
            zzc.zza(var0, "fcm", var5, new zzb(), 1);
        }

        zzb(var0, "_nr", var1);
    }

    public static void zzh(Context var0, Intent var1) {
        if(var1 != null) {
            String var4 = var1.getStringExtra("google.c.a.tc");
            if("1".equals(var4)) {
                AppMeasurement var5 = zzct(var0);
                if(Log.isLoggable("FirebaseMessaging", Log.DEBUG)) {
                    Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
                }

                if(var5 != null) {
                    String var6 = var1.getStringExtra("google.c.a.c_id");
                    var5.setUserPropertyInternal("fcm", "_ln", var6);
                    Bundle var7;
                    (var7 = new Bundle()).putString("source", "Firebase");
                    var7.putString("medium", "notification");
                    var7.putString("campaign", var6);
                    var5.logEventInternal("fcm", "_cmp", var7);
                } else {
                    Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
                }
            } else if(Log.isLoggable("FirebaseMessaging", Log.DEBUG)) {
                Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
            }
        }

        zzb(var0, "_no", var1);
    }

    public static void zzi(Context var0, Intent var1) {
        zzb(var0, "_nd", var1);
    }

    public static void zzj(Context var0, Intent var1) {
        zzb(var0, "_nf", var1);
    }

    private static void zzb(Context var0, String var1, Intent var2) {
        Bundle var3 = new Bundle();
        String var4;
        if((var4 = var2.getStringExtra("google.c.a.c_id")) != null) {
            var3.putString("_nmid", var4);
        }

        String var5;
        if((var5 = var2.getStringExtra("google.c.a.c_l")) != null) {
            var3.putString("_nmn", var5);
        }

        String var6;
        String var10000 = (var6 = var2.getStringExtra("from")) != null && var6.startsWith("/topics/")?var6:null;
        String var7 = var10000;
        if(var10000 != null) {
            var3.putString("_nt", var7);
        }

        int var8;
        try {
            var8 = Integer.valueOf(var2.getStringExtra("google.c.a.ts")).intValue();
            var3.putInt("_nmt", var8);
        } catch (NumberFormatException var10) {
            Log.w("FirebaseMessaging", "Error while parsing timestamp in GCM event", var10);
        }

        if(var2.hasExtra("google.c.a.udt")) {
            try {
                var8 = Integer.valueOf(var2.getStringExtra("google.c.a.udt")).intValue();
                var3.putInt("_ndt", var8);
            } catch (NumberFormatException var9) {
                Log.w("FirebaseMessaging", "Error while parsing use_device_time in GCM event", var9);
            }
        }

        if(Log.isLoggable("FirebaseMessaging", Log.DEBUG)) {
            String var11 = String.valueOf(var3);
            Log.d("FirebaseMessaging", (new StringBuilder(22 + String.valueOf(var1).length() + String.valueOf(var11).length())).append("Sending event=").append(var1).append(" params=").append(var11).toString());
        }

        AppMeasurement var12;
        if((var12 = zzct(var0)) != null) {
            var12.logEventInternal("fcm", var1, var3);
        } else {
            Log.w("FirebaseMessaging", "Unable to log event: analytics library is missing");
        }
    }

    private static AppMeasurement zzct(Context var0) {
        try {
            return AppMeasurement.getInstance(var0);
        } catch (NoClassDefFoundError var1) {
            return null;
        }
    }
}
