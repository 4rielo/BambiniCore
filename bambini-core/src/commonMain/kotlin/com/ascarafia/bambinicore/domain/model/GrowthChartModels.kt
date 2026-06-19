package com.ascarafia.bambinicore.domain.model

enum class GrowthChartType {
    PERCENTILES,
    ZSCORE
}

enum class GrowthChartAgeRange {
    ZERO_TO_TWO,
    TWO_TO_FIVE,

    FIVE_TO_TEN,
    TEN_TO_FIFTEEN,
    FIFTEEN_TO_NINETEEN,
    FIVE_TO_NINETEEN,
    ZERO_TO_AGE
}