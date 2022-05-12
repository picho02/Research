package com.example.research.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.research.clases.FuzzGerminationCount
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

class DBBelice_PR(context: Context?) : DBHelper(context) {
    val context = context

    fun insertFuzzGerminationCount(nursery_barcode_fc: String,fuzz_Activity_fgc: String,fuzz_Count_fgc: Double,date_fgc: String,comments:String): Long {
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var id: Long = 0
        try {
            val values = ContentValues()
            values.put("nursery_barcode_fc",nursery_barcode_fc)
            values.put("fuzz_Activity_fgc",fuzz_Activity_fgc)
            values.put("fuzz_Count_fgc",fuzz_Count_fgc)
            values.put("date_fgc",date_fgc.toString())
            values.put("comments",comments)
            values.put("data_update",1)
            id = db.insert(TABLE_FUZZ_GERMINATION_COUNT,null,values)
        } catch (e: Exception){

        } finally {
            db.close()
        }
        return id
    }
    fun getFuzzGerminationCountLista():ArrayList<FuzzGerminationCount>{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase
        var listFuzzGerminationCount = ArrayList<FuzzGerminationCount>()
        var temporal: FuzzGerminationCount? = null
        var cursorGerminationCount: Cursor? = null
        cursorGerminationCount = db.rawQuery("SELECT * FROM $TABLE_FUZZ_GERMINATION_COUNT",null)
        if(cursorGerminationCount.moveToFirst()){
            do {
                temporal = FuzzGerminationCount(
                    cursorGerminationCount.getInt(0),
                    cursorGerminationCount.getString(1),
                    cursorGerminationCount.getString(2),
                    cursorGerminationCount.getDouble(3),
                    cursorGerminationCount.getString(4),
                    cursorGerminationCount.getString(5),
                    1
                )
                listFuzzGerminationCount.add(temporal)
            }while (cursorGerminationCount.moveToNext())
        }
        cursorGerminationCount.close()
        return listFuzzGerminationCount
    }
    fun getFuzzGerminationCount(id: Int):FuzzGerminationCount?{
        val dbHelper = DBHelper(context)
        val db = dbHelper.writableDatabase

        var elemento: FuzzGerminationCount? = null
        var cursorGerminationCount: Cursor? = null

        cursorGerminationCount = db.rawQuery("SELECT * FROM $TABLE_FUZZ_GERMINATION_COUNT WHERE id = $id LIMIT 1", null)
        if (cursorGerminationCount.moveToFirst()){
            elemento = FuzzGerminationCount(
                cursorGerminationCount.getInt(0),
                cursorGerminationCount.getString(1),
                cursorGerminationCount.getString(2),
                cursorGerminationCount.getDouble(3),
                cursorGerminationCount.getString(4),
                cursorGerminationCount.getString(5),
                1
            )
        }
        cursorGerminationCount.close()
        return elemento
    }


}

