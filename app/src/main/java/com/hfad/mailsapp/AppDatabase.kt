package com.hfad.mailsapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hfad.mailsapp.dao.AttachmentDao
import com.hfad.mailsapp.dao.LetterDao
import com.hfad.mailsapp.dao.MailLabelDao
import com.hfad.mailsapp.dao.MailboxDao
import com.hfad.mailsapp.dao.RoleDao
import com.hfad.mailsapp.dao.StatusDao
import com.hfad.mailsapp.dao.UserDao
import com.hfad.mailsapp.models.Attachment
import com.hfad.mailsapp.models.Letter
import com.hfad.mailsapp.models.MailLabel
import com.hfad.mailsapp.models.Mailbox
import com.hfad.mailsapp.models.Role
import com.hfad.mailsapp.models.Status
import com.hfad.mailsapp.models.User

@Database(
    entities = [User::class, Status::class, Role::class,
        MailLabel::class, Mailbox::class, Letter::class, Attachment::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun attachmentDao(): AttachmentDao
    abstract fun letterDao(): LetterDao
    abstract fun mailboxDao(): MailboxDao
    abstract fun mailLabelDao(): MailLabelDao
    abstract fun roleDao(): RoleDao
    abstract fun statusDao(): StatusDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mails_app" // Имя БД
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}