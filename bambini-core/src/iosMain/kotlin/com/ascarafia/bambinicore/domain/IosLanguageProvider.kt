package com.ascarafia.bambinicore.domain

import platform.Foundation.NSBundle
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

class IosLanguageProvider : LanguageProvider {
    override fun getLanguage(): String {
        return NSLocale.currentLocale.languageCode ?: "en"
    }
}
