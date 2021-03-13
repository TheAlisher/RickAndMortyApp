package com.alis.rickandmorty.data.db.realm

import android.content.Context
import io.realm.Realm

class RealmClient {

    fun provideRealm(context: Context) {
        Realm.init(context)
    }
}