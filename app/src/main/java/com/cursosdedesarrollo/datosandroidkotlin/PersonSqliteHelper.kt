package com.cursosdedesarrollo.datosandroidkotlin

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class PersonSqliteHelper(context: Context): SQLiteOpenHelper(context, DB_NAME, null, VERSION) {

override fun onCreate(db: SQLiteDatabase) {
dropAndCreate(db)

}

override fun onUpgrade(db: SQLiteDatabase,
                              oldVersion:Int,
                              newVersion:Int) {
if (oldVersion == 1 && newVersion == 2)
{
db.execSQL("alter table " + PERSON_TABLE
+ " add column "
+ PERSON_PHOTOPATH + " text")
db.execSQL("alter table " + PERSON_TABLE +
" add column " + PERSON_THUMBPHOTOPATH
+ " text")
db.execSQL("alter table " + PERSON_TABLE + " add column "
+ PERSON_LOCATION + " text")
db.execSQL("alter table " + PERSON_TABLE + " add column "
+ PERSON_LATITUDE + " real")
db.execSQL("alter table " + PERSON_TABLE + " add column "
+ PERSON_LONGITUDE + " real")
Log.d("app", "database actualizada")
}
}
protected fun dropAndCreate(db: SQLiteDatabase) {
db.execSQL("drop table if exists $PERSON_TABLE;")
db.execSQL("drop table if exists $VALORATION_TABLE;")
createTables(db)
}

protected fun createTables(db: SQLiteDatabase) {
db.execSQL(
        "create table " + PERSON_TABLE + " (" +
        PERSON_ID + " integer primary key autoincrement " +
        "not null," +
        PERSON_NAME + " text not null," +
        PERSON_TLF + " text," +
        PERSON_GLOBAL + " text," +
        PERSON_PHOTOPATH + " text, " +
        PERSON_THUMBPHOTOPATH + " text, " +
        PERSON_LOCATION + " text, " +
        PERSON_LATITUDE + " real, " +
        PERSON_LONGITUDE + " real " +
        ");"
)
db.execSQL(
("create table " + VALORATION_TABLE + " (" +
VALORATION_ID + " integer primary key " +
"autoincrement not null," +
PERSON_ID + " integer," +
VALORATION_FACE + " float," +
VALORATION_BOOBS + " float," +
VALORATION_BUTT + " float," +
VALORATION_NOTE + " text " +
PERSON_ID + " REFERENCES " + PERSON_TABLE + "("
+ PERSON_ID + ") "
+ "ON DELETE CASCADE);")
)
}

companion object {

 val VERSION = 2
 val DB_NAME = "person_db.sqlite"
 val PERSON_TABLE = "person"
 val PERSON_ID = "person_id"
 val PERSON_NAME = "name"
 val PERSON_TLF = "tlf"
 val PERSON_GLOBAL = "global"
 val PERSON_PHOTOPATH = "photopath"
 val PERSON_THUMBPHOTOPATH = "thumbphotopath"
 val PERSON_LOCATION = "location"
 val PERSON_LONGITUDE = "longitude"
 val PERSON_LATITUDE = "latitude"
 val VALORATION_TABLE = "valorations"
 val VALORATION_ID = "valoration_id"
 val VALORATION_FACE = "face"
 val VALORATION_BOOBS = "boobs"
 val VALORATION_BUTT = "butt"
 val VALORATION_NOTE = "note"
}

}
