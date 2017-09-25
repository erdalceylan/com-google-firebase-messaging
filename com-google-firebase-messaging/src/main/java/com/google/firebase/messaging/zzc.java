//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.firebase.messaging;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.internal.zzegn;
import com.google.android.gms.internal.zzehl;
import com.google.android.gms.internal.zzehm;
import com.google.android.gms.measurement.AppMeasurement;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class zzc {
    @Nullable
    private static AppMeasurement zzct(Context var0) {
        try {
            return AppMeasurement.getInstance(var0);
        } catch (NoClassDefFoundError var1) {
            return null;
        }
    }

    private static boolean zzeg(Context var0) {
        if(zzct(var0) == null) {
            if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                Log.v("FirebaseAbtUtil", "Firebase Analytics not available");
            }

            return false;
        } else {
            try {
                Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                return true;
            } catch (ClassNotFoundException var1) {
                if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                    Log.v("FirebaseAbtUtil", "Firebase Analytics library is missing support for abt. Please update to a more recent version.");
                }

                return false;
            }
        }
    }

    public static void zza(@NonNull Context var0, @NonNull String var1, @NonNull byte[] var2, @NonNull zzb var3, int var4) {
        if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
            String var10002 = String.valueOf(var1);
            String var10001;
            if(var10002.length() != 0) {
                var10001 = "_SE called by ".concat(var10002);
            } else {
                String var10003 = new String("_SE called by ");
                var10001 = var10003;
            }

            Log.v("FirebaseAbtUtil", var10001);
        }

        if(zzeg(var0)) {
            AppMeasurement var5 = zzct(var0);
            zzehm var6;
            if((var6 = zzaj(var2)) == null) {
                if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                    Log.v("FirebaseAbtUtil", "_SE failed; either _P was not set, or we couldn't deserialize the _P.");
                }

            } else {
                try {
                    Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                    List var8 = zza(var5, var1);
                    boolean var9 = false;
                    Iterator var10 = var8.iterator();

                    while(true) {
                        while(var10.hasNext()) {
                            Object var11;
                            String var12 = zzas(var11 = var10.next());
                            String var13 = zzat(var11);
                            long var14 = ((Long)Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mCreationTimestamp").get(var11)).longValue();
                            if(var6.zzngv.equals(var12) && var6.zzngw.equals(var13)) {
                                if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                                    Log.v("FirebaseAbtUtil", (new StringBuilder(23 + String.valueOf(var12).length() + String.valueOf(var13).length())).append("_E is already set. [").append(var12).append(", ").append(var13).append("]").toString());
                                }

                                var9 = true;
                            } else {
                                boolean var16 = false;
                                zzehl[] var17 = var6.zznhg;
                                int var18 = var6.zznhg.length;

                                for(int var19 = 0; var19 < var18; ++var19) {
                                    if(var17[var19].zzngv.equals(var12)) {
                                        if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                                            Log.v("FirebaseAbtUtil", (new StringBuilder(33 + String.valueOf(var12).length() + String.valueOf(var13).length())).append("_E is found in the _OE list. [").append(var12).append(", ").append(var13).append("]").toString());
                                        }

                                        var16 = true;
                                        break;
                                    }
                                }

                                if(!var16) {
                                    if(var6.zzngx > var14) {
                                        if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                                            Log.v("FirebaseAbtUtil", (new StringBuilder(115 + String.valueOf(var12).length() + String.valueOf(var13).length())).append("Clearing _E as it was not in the _OE list, andits start time is older than the start time of the _E to be set. [").append(var12).append(", ").append(var13).append("]").toString());
                                        }

                                        zza(var0, var1, var12, var13, zza(var6, var3));
                                    } else if(Log.isLoggable("FirebaseAbtUtil",Log.VERBOSE)) {
                                        Log.v("FirebaseAbtUtil", (new StringBuilder(109 + String.valueOf(var12).length() + String.valueOf(var13).length())).append("_E was not found in the _OE list, but not clearing it as it has a new start time than the _E to be set.  [").append(var12).append(", ").append(var13).append("]").toString());
                                    }
                                }
                            }
                        }

                        if(var9) {
                            if(Log.isLoggable("FirebaseAbtUtil",Log.VERBOSE)) {
                                String var22 = var6.zzngv;
                                String var23 = var6.zzngw;
                                Log.v("FirebaseAbtUtil", (new StringBuilder(44 + String.valueOf(var22).length() + String.valueOf(var23).length())).append("_E is already set. Not setting it again [").append(var22).append(", ").append(var23).append("]").toString());
                            }

                            return;
                        }

                        zza(var5, var0, var1, var6, var3, 1);
                        return;
                    }
                } catch (Exception var21) {
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", var21);
                }
            }
        }
    }

    private static void zza(@NonNull AppMeasurement var0, @NonNull Context var1, @NonNull String var2, @NonNull zzehm var3, @NonNull zzb var4, int var5) {
        if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
            String var6 = var3.zzngv;
            String var7 = var3.zzngw;
            Log.v("FirebaseAbtUtil", (new StringBuilder(7 + String.valueOf(var6).length() + String.valueOf(var7).length())).append("_SEI: ").append(var6).append(" ").append(var7).toString());
        }

        try {
            Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            List var24 = zza(var0, var2);
            int var14 = zzb(var0, var2);
            Object var8;
            String var9;
            String var10;
            if(zza(var0, var2).size() >= var14) {
                if((var3.zznhf != 0?var3.zznhf:1) != 1) {
                    if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                        String var26 = var3.zzngv;
                        var9 = var3.zzngw;
                        Log.v("FirebaseAbtUtil", (new StringBuilder(44 + String.valueOf(var26).length() + String.valueOf(var9).length())).append("_E won't be set due to overflow policy. [").append(var26).append(", ").append(var9).append("]").toString());
                    }

                    return;
                }

                var9 = zzas(var8 = var24.get(0));
                var10 = zzat(var8);
                if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                    Log.v("FirebaseAbtUtil", (new StringBuilder(38 + String.valueOf(var9).length())).append("Clearing _E due to overflow policy: [").append(var9).append("]").toString());
                }

                zza(var1, var2, var9, var10, zza(var3, var4));
            }

            Iterator var25 = var24.iterator();

            while(var25.hasNext()) {
                Object var27;
                var10 = zzas(var27 = var25.next());
                String var11 = zzat(var27);
                if(var10.equals(var3.zzngv) && !var11.equals(var3.zzngw) && Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                    Log.v("FirebaseAbtUtil", (new StringBuilder(77 + String.valueOf(var10).length() + String.valueOf(var11).length())).append("Clearing _E, as only one _V of the same _E can be set atany given time: [").append(var10).append(", ").append(var11).append("].").toString());
                    zza(var1, var2, var10, var11, zza(var3, var4));
                }
            }

            if((var8 = zza(var3, var2, var4)) == null) {
                if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                    var9 = var3.zzngv;
                    var10 = var3.zzngw;
                    Log.v("FirebaseAbtUtil", (new StringBuilder(42 + String.valueOf(var9).length() + String.valueOf(var10).length())).append("Could not create _CUP for: [").append(var9).append(", ").append(var10).append("]. Skipping.").toString());
                }

            } else {
                String var16 = var2;
                Object var15 = var8;
                zzb var28 = var4;
                zzehm var13 = var3;
                AppMeasurement var12 = var0;
                if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                    String var17 = var3.zzngv;
                    String var18 = var3.zzngw;
                    String var19 = var3.zzngy;
                    Log.v("FirebaseAbtUtil", (new StringBuilder(27 + String.valueOf(var17).length() + String.valueOf(var18).length() + String.valueOf(var19).length())).append("Setting _CUP for _E: [").append(var17).append(", ").append(var18).append(", ").append(var19).append("]").toString());
                }

                try {
                    Class var29 = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
                    Method var30;
                    (var30 = AppMeasurement.class.getDeclaredMethod("setConditionalUserProperty", new Class[]{var29})).setAccessible(true);
                    var12.logEventInternal(var16, !TextUtils.isEmpty(var13.zznha)?var13.zznha:var28.zzbno(), zza(var13));
                    var30.invoke(var12, new Object[]{var15});
                } catch (Exception var22) {
                    Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", var22);
                }
            }
        } catch (Exception var23) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", var23);
        }
    }

    @Nullable
    private static Object zza(@NonNull zzehm var0, @NonNull String var1, @NonNull zzb var2) {
        Object var3 = null;

        try {
            Class var4 = Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty");
            Bundle var5 = zza(var0);
            var3 = var4.getConstructor(new Class[0]).newInstance(new Object[0]);
            var4.getField("mOrigin").set(var3, var1);
            var4.getField("mCreationTimestamp").set(var3, Long.valueOf(var0.zzngx));
            var4.getField("mName").set(var3, var0.zzngv);
            var4.getField("mValue").set(var3, var0.zzngw);
            String var6 = TextUtils.isEmpty(var0.zzngy)?null:var0.zzngy;
            var4.getField("mTriggerEventName").set(var3, var6);
            var4.getField("mTimedOutEventName").set(var3, !TextUtils.isEmpty(var0.zznhd)?var0.zznhd:var2.zzbnq());
            var4.getField("mTimedOutEventParams").set(var3, var5);
            var4.getField("mTriggerTimeout").set(var3, Long.valueOf(var0.zzngz));
            var4.getField("mTriggeredEventName").set(var3, !TextUtils.isEmpty(var0.zznhb)?var0.zznhb:var2.zzbnp());
            var4.getField("mTriggeredEventParams").set(var3, var5);
            var4.getField("mTimeToLive").set(var3, Long.valueOf(var0.zzgbv));
            var4.getField("mExpiredEventName").set(var3, !TextUtils.isEmpty(var0.zznhe)?var0.zznhe:var2.zzbnr());
            var4.getField("mExpiredEventParams").set(var3, var5);
        } catch (Exception var9) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", var9);
        }

        return var3;
    }

    private static Bundle zza(@NonNull zzehm var0) {
        return zzba(var0.zzngv, var0.zzngw);
    }

    private static Bundle zzba(@NonNull String var0, @NonNull String var1) {
        Bundle var2;
        (var2 = new Bundle()).putString(var0, var1);
        return var2;
    }

    private static List<Object> zza(@NonNull AppMeasurement var0, @NonNull String var1) {
        Object var2 = new ArrayList();

        try {
            Method var3;
            (var3 = AppMeasurement.class.getDeclaredMethod("getConditionalUserProperties", new Class[]{String.class, String.class})).setAccessible(true);
            var2 = (List)var3.invoke(var0, new Object[]{var1, ""});
        } catch (Exception var4) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", var4);
        }

        if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
            int var5 = ((List)var2).size();
            Log.v("FirebaseAbtUtil", (new StringBuilder(55 + String.valueOf(var1).length())).append("Number of currently set _Es for origin: ").append(var1).append(" is ").append(var5).toString());
        }

        return (List)var2;
    }

    private static String zzas(@NonNull Object var0) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String)Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mName").get(var0);
    }

    private static String zzat(@NonNull Object var0) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return (String)Class.forName("com.google.android.gms.measurement.AppMeasurement$ConditionalUserProperty").getField("mValue").get(var0);
    }

    private static void zza(@NonNull Context var0, @NonNull String var1, @NonNull String var2, @NonNull String var3, @NonNull String var4) {
        if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
            String var10002 = String.valueOf(var1);
            String var10001;
            if(var10002.length() != 0) {
                var10001 = "_CE(experimentId) called by ".concat(var10002);
            } else {
                String var10003 = new String("_CE(experimentId) called by ");
                var10001 = var10003;
            }

            Log.v("FirebaseAbtUtil", var10001);
        }

        if(zzeg(var0)) {
            AppMeasurement var5 = zzct(var0);

            try {
                Method var6;
                (var6 = AppMeasurement.class.getDeclaredMethod("clearConditionalUserProperty", new Class[]{String.class, String.class, Bundle.class})).setAccessible(true);
                if(Log.isLoggable("FirebaseAbtUtil", Log.VERBOSE)) {
                    Log.v("FirebaseAbtUtil", (new StringBuilder(17 + String.valueOf(var2).length() + String.valueOf(var3).length())).append("Clearing _E: [").append(var2).append(", ").append(var3).append("]").toString());
                }

                var6.invoke(var5, new Object[]{var2, var4, zzba(var2, var3)});
            } catch (Exception var7) {
                Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", var7);
            }
        }
    }

    private static String zza(@Nullable zzehm var0, @NonNull zzb var1) {
        return var0 != null && !TextUtils.isEmpty(var0.zznhc)?var0.zznhc:var1.zzbns();
    }

    private static int zzb(@NonNull AppMeasurement var0, @NonNull String var1) {
        try {
            Method var2;
            (var2 = AppMeasurement.class.getDeclaredMethod("getMaxUserProperties", new Class[]{String.class})).setAccessible(true);
            return ((Integer)var2.invoke(var0, new Object[]{var1})).intValue();
        } catch (Exception var3) {
            Log.e("FirebaseAbtUtil", "Could not complete the operation due to an internal error.", var3);
            return 20;
        }
    }

    @Nullable
    private static zzehm zzaj(@NonNull byte[] var0) {
        try {
            return zzehm.zzay(var0);
        } catch (zzegn var1) {
            return null;
        }
    }
}
