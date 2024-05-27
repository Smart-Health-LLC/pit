package platform

import app.cash.sqldelight.db.SqlDriver

expect class LocalDatabaseDriverFactory {
    fun create(): SqlDriver
}
