package com.team23.domain.enums

import com.team23.R

enum class BidEnum(val nameId: Int, val multiplier: Int) {
    SMALL(R.string.tarot_small, 1),
    GUARD(R.string.tarot_guard, 2),
    GUARD_WITHOUT(R.string.tarot_guard_without, 4),
    GUARD_AGAINST(R.string.tarot_guard_against, 6)
}