package com.vpaliy.morsenotifier.domain.converter;

import android.support.annotation.NonNull;

import com.vpaliy.morsenotifier.domain.wrapper.TransformWrapper;

import java.util.HashMap;
import java.util.Map;

public class MorseConverter<T extends TransformWrapper> extends Converter<T> {

    private final T transformWrapper;

    private static final int DOTS_IN_DASH       = 3;
    private static final int DOTS_IN_GAP        = 1;
    private static final int DOTS_IN_LETTER_GAP = 3;
    private static final int DOTS_IN_WORD_GAP   = 7;

    private static final String      CHARSET_MORSE  = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,?'!/()&:;=+-_\"$@";

    private static final boolean     DOT  = true;
    private static final boolean     DASH = false;

    private static final boolean[][] MORSE  = {
            {DOT, DASH}, //A
            {DASH, DOT, DOT, DOT}, //B
            {DASH, DOT, DASH, DOT}, //C
            {DASH, DOT, DOT}, //D
            {DOT}, //E
            {DOT, DOT, DASH, DOT}, //F
            {DASH, DASH, DOT}, //G
            {DOT, DOT, DOT, DOT}, //H
            {DOT, DOT}, //I
            {DOT, DASH, DASH, DASH}, //J
            {DASH, DOT, DASH}, //K
            {DOT, DASH, DOT, DOT}, //L
            {DASH, DASH}, //M
            {DASH, DOT}, //N
            {DASH, DASH, DASH}, //O
            {DOT, DASH, DASH, DOT}, //P
            {DASH, DASH, DOT, DASH}, //Q
            {DOT, DASH, DOT}, //R
            {DOT, DOT, DOT}, //S
            {DASH}, //T
            {DOT, DOT, DASH}, //U
            {DOT, DOT, DOT, DASH}, //V
            {DOT, DASH, DASH}, //W
            {DASH, DOT, DOT, DASH}, //X
            {DASH, DOT, DASH, DASH}, //Y
            {DASH, DASH, DOT, DOT}, //Z
            {DASH, DASH, DASH, DASH, DASH}, //0
            {DOT, DASH, DASH, DASH, DASH}, //1
            {DOT, DOT, DASH, DASH, DASH}, //2
            {DOT, DOT, DOT, DASH, DASH}, //3
            {DOT, DOT, DOT, DOT, DASH}, //4
            {DOT, DOT, DOT, DOT, DOT}, //5
            {DASH, DOT, DOT, DOT, DOT}, //6
            {DASH, DASH, DOT, DOT, DOT}, //7
            {DASH, DASH, DASH, DOT, DOT}, //8
            {DASH, DASH, DASH, DASH, DOT}, //9
            {DOT, DASH, DOT, DASH, DOT, DASH}, //.
            {DASH, DASH, DOT, DOT, DASH, DASH}, //,
            {DOT, DOT, DASH, DASH, DOT, DOT}, //?
            {DOT, DASH, DASH, DASH, DASH, DOT}, //'
            {DASH, DOT, DASH, DOT, DASH, DASH}, //!
            {DASH, DOT, DOT, DASH, DOT}, ///
            {DASH, DOT, DASH, DASH, DOT}, //(
            {DASH, DOT, DASH, DASH, DOT, DASH}, //)
            {DOT, DASH, DOT, DOT, DOT}, //&
            {DASH, DASH, DASH, DOT, DOT, DOT}, //:
            {DASH, DOT, DASH, DOT, DASH, DOT}, //;
            {DASH, DOT, DOT, DOT, DASH}, //=
            {DOT, DASH, DOT, DASH, DOT}, //+
            {DASH, DOT, DOT, DOT, DOT, DASH}, //-
            {DOT, DOT, DASH, DASH, DOT, DASH}, //_
            {DOT, DASH, DOT, DOT, DASH, DOT}, //"
            {DOT, DOT, DOT, DASH, DOT, DOT, DASH}, //$
            {DOT, DASH, DASH, DOT, DASH, DOT} //@
    };

    public MorseConverter(@NonNull T wrapper) {
        this.transformWrapper=wrapper;
    }

    @Override
    protected long lengthFor(int charX) {
        return 0;
    }

    public void convert() {
    }



}
