package com.daccvo.keyreceiver.data

import android.content.Context
import androidx.datastore.dataStore
import com.daccvo.keyreceiver.domain.EncryptedPreferences
import com.daccvo.keyreceiver.model.Keys
import com.daccvo.keyreceiver.model.KeysSerializer
import kotlinx.coroutines.flow.firstOrNull

const val PREF_NAME = "apiKeys"
const val PREF_FIRST_KEY = "firstKey"
const val PREF_SECOND_KEY = "secondKey"

private val Context.dataStore by dataStore(
    fileName = PREF_NAME,
    serializer = KeysSerializer
)

class EncryptedPreferencesImpl (context: Context) : EncryptedPreferences {

    private val dataStore = context.dataStore

    override suspend fun saveEncryptedData(keys: Keys): Boolean {
        return try {
            dataStore.updateData { keys }
            true
        } catch (e: Exception) {
            println("Error during saveEncryptedData: ${e.message}")
            false
        }
    }

    override suspend fun readEncryptedData(): Keys? {
        return dataStore.data.firstOrNull()
    }

    override suspend fun areApiKeysReady(): Boolean {
        return try {
            val keys = readEncryptedData()
            keys != null && keys.firstKey.isNotEmpty() && keys.secondKey.isNotEmpty()
        } catch (e: Exception) {
            println("Error during areApiKeysReady: ${e.message}")
            false
        }
    }


}