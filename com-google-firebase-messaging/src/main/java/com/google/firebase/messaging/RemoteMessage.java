//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.firebase.messaging;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzd;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public final class RemoteMessage extends zza {
    public static final Creator<RemoteMessage> CREATOR = new zzf();
    Bundle mBundle;
    private Map<String, String> zzdkx;
    private Notification zzmjx;

    RemoteMessage(Bundle var1) {
        this.mBundle = var1;
    }

    public final void writeToParcel(Parcel var1, int var2) {
        int var5 = zzd.zze(var1);
        zzd.zza(var1, 2, this.mBundle, false);
        zzd.zzai(var1, var5);
    }

    public final String getFrom() {
        return this.mBundle.getString("from");
    }

    public final String getTo() {
        return this.mBundle.getString("google.to");
    }

    public final Map<String, String> getData() {
        if(this.zzdkx == null) {
            this.zzdkx = new ArrayMap();
            Iterator var1 = this.mBundle.keySet().iterator();

            while(var1.hasNext()) {
                String var2 = (String)var1.next();
                Object var3;
                if((var3 = this.mBundle.get(var2)) instanceof String) {
                    String var4 = (String)var3;
                    if(!var2.startsWith("google.") && !var2.startsWith("gcm.") && !var2.equals("from") && !var2.equals("message_type") && !var2.equals("collapse_key")) {
                        this.zzdkx.put(var2, var4);
                    }
                }
            }
        }

        return this.zzdkx;
    }

    public final String getCollapseKey() {
        return this.mBundle.getString("collapse_key");
    }

    public final String getMessageId() {
        String var1;
        if((var1 = this.mBundle.getString("google.message_id")) == null) {
            var1 = this.mBundle.getString("message_id");
        }

        return var1;
    }

    public final String getMessageType() {
        return this.mBundle.getString("message_type");
    }

    public final long getSentTime() {
        Object var1;
        if((var1 = this.mBundle.get("google.sent_time")) instanceof Long) {
            return ((Long)var1).longValue();
        } else {
            if(var1 instanceof String) {
                try {
                    return Long.parseLong((String)var1);
                } catch (NumberFormatException var3) {
                    String var2 = String.valueOf(var1);
                    Log.w("FirebaseMessaging", (new StringBuilder(19 + String.valueOf(var2).length())).append("Invalid sent time: ").append(var2).toString());
                }
            }

            return 0L;
        }
    }

    public final int getTtl() {
        Object var1;
        if((var1 = this.mBundle.get("google.ttl")) instanceof Integer) {
            return ((Integer)var1).intValue();
        } else {
            if(var1 instanceof String) {
                try {
                    return Integer.parseInt((String)var1);
                } catch (NumberFormatException var3) {
                    String var2 = String.valueOf(var1);
                    Log.w("FirebaseMessaging", (new StringBuilder(13 + String.valueOf(var2).length())).append("Invalid TTL: ").append(var2).toString());
                }
            }

            return 0;
        }
    }

    public final Notification getNotification() {
        if(this.zzmjx == null && com.google.firebase.messaging.zza.zzac(this.mBundle)) {
            this.zzmjx = new Notification(this.mBundle);
        }

        return this.zzmjx;
    }

    public static class Notification {
        private final String zzehi;
        private final String zzmjy;
        private final String[] zzmjz;
        private final String zzbrs;
        private final String zzmka;
        private final String[] zzmkb;
        private final String zzmkc;
        private final String zzmkd;
        private final String mTag;
        private final String zzmke;
        private final String zzmkf;
        private final Uri zzmkg;

        private Notification(Bundle var1) {
            this.zzehi = com.google.firebase.messaging.zza.zze(var1, "gcm.n.title");
            this.zzmjy = com.google.firebase.messaging.zza.zzh(var1, "gcm.n.title");
            this.zzmjz = zzk(var1, "gcm.n.title");
            this.zzbrs = com.google.firebase.messaging.zza.zze(var1, "gcm.n.body");
            this.zzmka = com.google.firebase.messaging.zza.zzh(var1, "gcm.n.body");
            this.zzmkb = zzk(var1, "gcm.n.body");
            this.zzmkc = com.google.firebase.messaging.zza.zze(var1, "gcm.n.icon");
            this.zzmkd = com.google.firebase.messaging.zza.zzae(var1);
            this.mTag = com.google.firebase.messaging.zza.zze(var1, "gcm.n.tag");
            this.zzmke = com.google.firebase.messaging.zza.zze(var1, "gcm.n.color");
            this.zzmkf = com.google.firebase.messaging.zza.zze(var1, "gcm.n.click_action");
            this.zzmkg = com.google.firebase.messaging.zza.zzad(var1);
        }

        private static String[] zzk(Bundle var0, String var1) {
            Object[] var2;
            if((var2 = com.google.firebase.messaging.zza.zzi(var0, var1)) == null) {
                return null;
            } else {
                String[] var3 = new String[var2.length];

                for(int var4 = 0; var4 < var2.length; ++var4) {
                    var3[var4] = String.valueOf(var2[var4]);
                }

                return var3;
            }
        }

        @Nullable
        public String getTitle() {
            return this.zzehi;
        }

        @Nullable
        public String getTitleLocalizationKey() {
            return this.zzmjy;
        }

        @Nullable
        public String[] getTitleLocalizationArgs() {
            return this.zzmjz;
        }

        @Nullable
        public String getBody() {
            return this.zzbrs;
        }

        @Nullable
        public String getBodyLocalizationKey() {
            return this.zzmka;
        }

        @Nullable
        public String[] getBodyLocalizationArgs() {
            return this.zzmkb;
        }

        @Nullable
        public String getIcon() {
            return this.zzmkc;
        }

        @Nullable
        public String getSound() {
            return this.zzmkd;
        }

        @Nullable
        public String getTag() {
            return this.mTag;
        }

        @Nullable
        public String getColor() {
            return this.zzmke;
        }

        @Nullable
        public String getClickAction() {
            return this.zzmkf;
        }

        @Nullable
        public Uri getLink() {
            return this.zzmkg;
        }
    }

    public static class Builder {
        private final Bundle mBundle = new Bundle();
        private final Map<String, String> zzdkx = new ArrayMap();

        public Builder(String var1) {
            if(TextUtils.isEmpty(var1)) {

                String var10003 = String.valueOf(var1);
                String var10002;
                if(var10003.length() != 0) {
                    var10002 = "Invalid to: ".concat(var10003);
                } else {
                    String var10004 = new String("Invalid to: ");
                    var10002 = var10004;
                }
                IllegalArgumentException var10000 = new IllegalArgumentException(var10002);
                throw var10000;
            } else {
                this.mBundle.putString("google.to", var1);
            }
        }

        public RemoteMessage build() {
            Bundle var1 = new Bundle();
            Iterator var2 = this.zzdkx.entrySet().iterator();

            while(var2.hasNext()) {
                Entry var3 = (Entry)var2.next();
                var1.putString((String)var3.getKey(), (String)var3.getValue());
            }

            var1.putAll(this.mBundle);
            String var4;
            if((var4 = FirebaseInstanceId.getInstance().getToken()) != null) {
                this.mBundle.putString("from", var4);
            } else {
                this.mBundle.remove("from");
            }

            return new RemoteMessage(var1);
        }

        public Builder addData(String var1, String var2) {
            this.zzdkx.put(var1, var2);
            return this;
        }

        public Builder setData(Map<String, String> var1) {
            this.zzdkx.clear();
            this.zzdkx.putAll(var1);
            return this;
        }

        public Builder clearData() {
            this.zzdkx.clear();
            return this;
        }

        public Builder setMessageId(String var1) {
            this.mBundle.putString("google.message_id", var1);
            return this;
        }

        public Builder setMessageType(String var1) {
            this.mBundle.putString("message_type", var1);
            return this;
        }

        public Builder setTtl(int var1) {
            this.mBundle.putString("google.ttl", String.valueOf(var1));
            return this;
        }

        public Builder setCollapseKey(String var1) {
            this.mBundle.putString("collapse_key", var1);
            return this;
        }
    }
}
