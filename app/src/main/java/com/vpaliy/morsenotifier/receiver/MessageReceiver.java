package com.vpaliy.morsenotifier.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import com.vpaliy.morsenotifier.domain.MessageHandler;
import com.vpaliy.morsenotifier.utils.ProjectUtils;

import java.util.LinkedList;
import java.util.List;

import static com.vpaliy.morsenotifier.utils.ProjectUtils.TAG;

public class MessageReceiver extends BroadcastReceiver {

    private static final String ACTION_SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    private static final String EXTRAS_DATA="pdus";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION_SMS_RECEIVED)) {
            Log.d(TAG(this),"Received");
            List<SmsMessage> messageList=convertToSms(intent.getExtras());
            if(messageList!=null) {
                MessageHandler.buildWith(context,messageList);
            }
        }

    }


    private List<SmsMessage> convertToSms(Bundle extras) {
        if(extras==null) {
            return null;
        }

        List<SmsMessage> smsMessageList=new LinkedList<>();
        Object[] pdus=Object[].class.cast(extras.get(EXTRAS_DATA));

        for(Object pdu:pdus) {
            smsMessageList.add(SmsMessage.createFromPdu(byte[].class.cast(pdu)));
        }
        return smsMessageList.isEmpty()?null:smsMessageList;

    }
}
