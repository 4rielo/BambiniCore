package com.ascarafia.bambinicore.domain

import java.util.Locale

class AndroidLanguageProvider : LanguageProvider {
    override fun getLanguage(): String {
        return Locale.getDefault().language
    }
}
