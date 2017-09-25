//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.firebase.messaging;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.zzl;
import java.util.regex.Pattern;

public class FirebaseMessaging {
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final Pattern zzmjv = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    private static FirebaseMessaging zzmjw;
    private final FirebaseInstanceId zzmiv;

    public static synchronized FirebaseMessaging getInstance() {
        if(zzmjw == null) {
            zzmjw = new FirebaseMessaging(FirebaseInstanceId.getInstance());
        }

        return zzmjw;
    }

    private FirebaseMessaging(FirebaseInstanceId var1) {
        this.zzmiv = var1;
    }

    public void subscribeToTopic(String var1) {
        if(var1 != null && var1.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
            var1 = var1.substring(8);
        }

        if(var1 != null && zzmjv.matcher(var1).matches()) {
            FirebaseInstanceId var10000 = this.zzmiv;
            String var10001 = String.valueOf("S!");
            String var10002 = String.valueOf(var1);
            if(var10002.length() != 0) {
                var10001 = var10001.concat(var10002);
            } else {
                String var10003 = new String(var10002);
                var10001 = var10003;
            }

            var10000.zzpq(var10001);
        } else {
            String var3 = "[a-zA-Z0-9-_.~%]{1,900}";
            throw new IllegalArgumentException((new StringBuilder(55 + String.valueOf(var1).length() + String.valueOf(var3).length())).append("Invalid topic name: ").append(var1).append(" does not match the allowed format ").append(var3).toString());
        }
    }

    public void unsubscribeFromTopic(String var1) {
        if(var1 != null && var1.startsWith("/topics/")) {
            Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
            var1 = var1.substring(8);
        }

        if(var1 != null && zzmjv.matcher(var1).matches()) {
            FirebaseInstanceId var10000 = this.zzmiv;
            String var10001 = String.valueOf("U!");
            String var10002 = String.valueOf(var1);
            if(var10002.length() != 0) {
                var10001 = var10001.concat(var10002);
            } else {
            }

            var10000.zzpq(var10001);
        } else {
            String var3 = "[a-zA-Z0-9-_.~%]{1,900}";
            throw new IllegalArgumentException((new StringBuilder(55 + String.valueOf(var1).length() + String.valueOf(var3).length())).append("Invalid topic name: ").append(var1).append(" does not match the allowed format ").append(var3).toString());
        }
    }

    public void send(RemoteMessage var1) {
        if(TextUtils.isEmpty(var1.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        } else {
            Context var2;
            String var3;
            if((var3 = zzl.zzdg(var2 = FirebaseApp.getInstance().getApplicationContext())) == null) {
                Log.e("FirebaseMessaging", "Google Play services package is missing. Impossible to send message");
            } else {
                Intent var4 = new Intent("com.google.android.gcm.intent.SEND");
                zzl.zzd(var2, var4);
                var4.setPackage(var3);
                var4.putExtras(var1.mBundle);
                var2.sendOrderedBroadcast(var4, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
            }
        }
    }
}
