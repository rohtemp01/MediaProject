package com.example.mp_app.util;

import android.os.AsyncTask;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslateUtil{
    static String target;
    static String textTo;
    public static String translateText(String textToTranslate, String targetLanguage) {
        target = targetLanguage;
        textTo = textToTranslate;
        String ans = String.valueOf(new multi().execute(textTo));
        return ans;
//        try {
//            target = targetLanguage;
////             Google Cloud Translate API를 사용하기 위한 인증 및 설정
//            Translate translate = TranslateOptions.getDefaultInstance().getService();
////
////             번역 수행
//            Translation translation = translate.translate(
//                    textToTranslate,
//                    Translate.TranslateOption.targetLanguage(targetLanguage)
//            );
////
////             번역된 결과 반환
//            return translation.getTranslatedText();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "Translation Error";
//        }
    }

    private static class multi extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            Translate translate = TranslateOptions.getDefaultInstance().getService();
            Translation translation = translate.translate(String.valueOf(strings), Translate.TranslateOption.targetLanguage("en"));
            return translation.getTranslatedText();
        }
    }
}

