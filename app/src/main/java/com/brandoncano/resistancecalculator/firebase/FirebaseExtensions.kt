package com.brandoncano.resistancecalculator.firebase

import com.brandoncano.resistancecalculator.keys.FirebaseRemoteConfigKeys

const val FIREBASE_TAG = "FirebaseTag"

fun FirebaseRemoteConfigKeys.getStringOrEmpty(): String {
    return FirebaseRemoteConfigStringRetriever.execute(this).orEmpty()
}
