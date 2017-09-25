//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.firebase.messaging;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.firebase.iid.zzb;
import com.google.firebase.iid.zzq;
import java.util.Iterator;

public class FirebaseMessagingService extends zzb {
    public FirebaseMessagingService() {
    }

    @WorkerThread
    public void onMessageReceived(RemoteMessage var1) {
    }

    @WorkerThread
    public void onDeletedMessages() {
    }

    @WorkerThread
    public void onMessageSent(String var1) {
    }

    @WorkerThread
    public void onSendError(String var1, Exception var2) {
    }

    protected final Intent zzn(Intent var1) {
        return zzq.zzbyp().zzbyq();
    }

    public final boolean zzo(Intent var1) {
        if("com.google.firebase.messaging.NOTIFICATION_OPEN".equals(var1.getAction())) {
            PendingIntent var4;
            if((var4 = (PendingIntent)var1.getParcelableExtra("pending_intent")) != null) {
                try {
                    var4.send();
                } catch (CanceledException var5) {
                    Log.e("FirebaseMessaging", "Notification pending intent canceled");
                }
            }

            if(zzaf(var1.getExtras())) {
                zzd.zzh(this, var1);
            }

            return true;
        } else {
            return false;
        }
    }

    public void handleIntent(Intent var1) {
        String var2;
        if((var2 = var1.getAction()) == null) {
            var2 = "";
        }

        byte var4 = -1;
        switch(var2.hashCode()) {
            case 75300319:
                if(var2.equals("com.google.firebase.messaging.NOTIFICATION_DISMISS")) {
                    var4 = 1;
                }
                break;
            case 366519424:
                if(var2.equals("com.google.android.c2dm.intent.RECEIVE")) {
                    var4 = 0;
                }
        }

        String var10001;
        String var10002;
        String var10003;
        switch(var4) {
            case 0:
                String var7;
                if((var7 = var1.getStringExtra("message_type")) == null) {
                    var7 = "gcm";
                }

                byte var9 = -1;
                switch(var7.hashCode()) {
                    case -2062414158:
                        if(var7.equals("deleted_messages")) {
                            var9 = 1;
                        }
                        break;
                    case 102161:
                        if(var7.equals("gcm")) {
                            var9 = 0;
                        }
                        break;
                    case 814694033:
                        if(var7.equals("send_error")) {
                            var9 = 3;
                        }
                        break;
                    case 814800675:
                        if(var7.equals("send_event")) {
                            var9 = 2;
                        }
                }

                switch(var9) {
                    case 0:
                        if(zzaf(var1.getExtras())) {
                            zzd.zzg(this, var1);
                        }

                        Bundle var13;
                        if((var13 = var1.getExtras()) == null) {
                            var13 = new Bundle();
                        }

                        var13.remove("android.support.content.wakelockid");
                        if(zza.zzac(var13)) {
                            if(zza.zzep(this).zzr(var13)) {
                                return;
                            }

                            if(zzaf(var13)) {
                                zzd.zzj(this, var1);
                            }
                        }

                        this.onMessageReceived(new RemoteMessage(var13));
                        return;
                    case 1:
                        this.onDeletedMessages();
                        return;
                    case 2:
                        this.onMessageSent(var1.getStringExtra("google.message_id"));
                        return;
                    case 3:
                        String var12;
                        if((var12 = var1.getStringExtra("google.message_id")) == null) {
                            var12 = var1.getStringExtra("message_id");
                        }

                        this.onSendError(var12, new SendException(var1.getStringExtra("error")));
                        return;
                    default:
                        var10002 = String.valueOf(var7);
                        if(var10002.length() != 0) {
                            var10001 = "Received message with unknown type: ".concat(var10002);
                        } else {
                            var10003 = new String("Received message with unknown type: ");
                            var10001 = var10003;
                        }

                        Log.w("FirebaseMessaging", var10001);
                        return;
                }
            case 1:
                if(zzaf(var1.getExtras())) {
                    zzd.zzi(this, var1);
                    return;
                }
                break;
            default:
                var10002 = String.valueOf(var1.getAction());
                if(var10002.length() != 0) {
                    var10001 = "Unknown intent action: ".concat(var10002);
                } else {
                    var10003 = new String("Unknown intent action: ");
                    var10001 = var10003;
                }

                Log.d("FirebaseMessaging", var10001);
        }

    }

    static void zzp(Bundle var0) {
        Iterator var1 = var0.keySet().iterator();

        while(var1.hasNext()) {
            String var2;
            if((var2 = (String)var1.next()) != null && var2.startsWith("google.c.")) {
                var1.remove();
            }
        }

    }

    static boolean zzaf(Bundle var0) {
        return var0 == null?false:"1".equals(var0.getString("google.c.a.e"));
    }
}
