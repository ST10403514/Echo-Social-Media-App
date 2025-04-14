package com.mason.echo

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val profilePicUrl: String? = null
)

class FirestoreManager {
    private val db = FirebaseFirestore.getInstance()

    suspend fun saveUser(user: User): Result<Unit> {
        return try {
            db.collection("users").document(user.uid).set(user).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}