package com.brandoncano.resistancecalculator.firebase

const val FIREBASE_TAG = "FirebaseTag"

fun FirebaseRemoteConfigKeys.getStringOrEmpty(): String {
    return FirebaseRemoteConfigStringRetriever.execute(this).orEmpty()
}
