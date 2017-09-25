//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.google.firebase.messaging;

import java.util.Locale;

public final class SendException extends Exception {
    public static final int ERROR_UNKNOWN = 0;
    public static final int ERROR_INVALID_PARAMETERS = 1;
    public static final int ERROR_SIZE = 2;
    public static final int ERROR_TTL_EXCEEDED = 3;
    public static final int ERROR_TOO_MANY_MESSAGES = 4;
    private final int mErrorCode;

    SendException(String var1) {
        super(var1);
        byte var10001;
        label37: {

            if(var1 != null) {
                String var3 = var1.toLowerCase(Locale.US);
                byte var4 = -1;
                switch(var3.hashCode()) {
                    case -1743242157:
                        if(var3.equals("service_not_available")) {
                            var4 = 3;
                        }
                        break;
                    case -1290953729:
                        if(var3.equals("toomanymessages")) {
                            var4 = 4;
                        }
                        break;
                    case -920906446:
                        if(var3.equals("invalid_parameters")) {
                            var4 = 0;
                        }
                        break;
                    case -617027085:
                        if(var3.equals("messagetoobig")) {
                            var4 = 2;
                        }
                        break;
                    case -95047692:
                        if(var3.equals("missing_to")) {
                            var4 = 1;
                        }
                }

                switch(var4) {
                    case 0:
                    case 1:
                        var10001 = 1;
                        break label37;
                    case 2:
                        var10001 = 2;
                        break label37;
                    case 3:
                        var10001 = 3;
                        break label37;
                    case 4:
                        var10001 = 4;
                        break label37;
                }
            }

            var10001 = 0;
        }

        this.mErrorCode = var10001;
    }

    public final int getErrorCode() {
        return this.mErrorCode;
    }
}
