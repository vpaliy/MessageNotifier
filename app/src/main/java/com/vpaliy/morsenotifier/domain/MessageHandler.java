package com.vpaliy.morsenotifier.domain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.telephony.SmsMessage;

import java.util.List;

public class MessageHandler {

    @NonNull
    private final List<SmsMessage> smsMessageList;

    @NonNull
    private final Context context;

    private static  MessageHandler INSTANCE;

    private MessageHandler(Context context, List<SmsMessage> smsMessageList) {
        this.smsMessageList=smsMessageList;
        this.context=context;
    }

    public static MessageHandler buildWith(@NonNull Context context, @NonNull List<SmsMessage> smsMessageList) {
        if(INSTANCE==null) {
            synchronized (MessageHandler.class) {
                if(INSTANCE==null) {
                    INSTANCE=new MessageHandler(context, smsMessageList);
                }
            }
        }
        return INSTANCE;
    }

    public void handle() {

    }

}
