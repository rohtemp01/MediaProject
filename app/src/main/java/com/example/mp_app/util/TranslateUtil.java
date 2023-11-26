package com.example.mp_app.util;

import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

public class TranslateUtil {

    public static String translateText(String textToTranslate, String targetLanguage) {
        try {
            // Google Cloud Translate API를 사용하기 위한 인증 및 설정
            Translate translate = TranslateOptions.getDefaultInstance().getService();

            // 번역 수행
            Translation translation = translate.translate(
                    textToTranslate,
                    Translate.TranslateOption.targetLanguage(targetLanguage)
            );

            // 번역된 결과 반환
            return translation.getTranslatedText();
        } catch (Exception e) {
            e.printStackTrace();
            return "Translation Error";
        }
    }
}

