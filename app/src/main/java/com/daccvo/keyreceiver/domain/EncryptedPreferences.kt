package com.daccvo.keyreceiver.domain

import com.daccvo.keyreceiver.model.Keys

interface EncryptedPreferences {
    suspend fun saveEncryptedData(keys: Keys): Boolean
    suspend fun readEncryptedData(): Keys?
    suspend fun areApiKeysReady(): Boolean
}