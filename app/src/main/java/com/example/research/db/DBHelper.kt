package com.example.research.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

open class DBHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    companion object {
        private const val DATABASE_NAME = "belice_pr.db"
        private const val DATABASE_VERSION = 1
        public const val TABLE_FUZZ_GERMINATION_COUNT = "fuzz_germination_count"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("CREATE TABLE $TABLE_FUZZ_GERMINATION_COUNT (id_fgc INTEGER PRIMARY KEY AUTOINCREMENT, nursery_barcode_fc TEXT(50) NOT NULL," +
                "fuzz_Activity_fgc TEXT,fuzz_Count_fgc DOUBLE NOT NULL DEFAULT 0,date_fgc TEXT,comments_fgc TEXT,data_update BOOL)")
    }
//Comment
    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("DROP TABLE $TABLE_FUZZ_GERMINATION_COUNT")
        onCreate(p0)
    }
}

