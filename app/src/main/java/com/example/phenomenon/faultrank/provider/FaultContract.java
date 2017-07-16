package com.example.phenomenon.faultrank.provider;

import android.database.Cursor;

import net.simonvt.schematic.annotation.AutoIncrement;
import net.simonvt.schematic.annotation.DataType;
import net.simonvt.schematic.annotation.NotNull;
import net.simonvt.schematic.annotation.PrimaryKey;

/**
 * Created by PHENOMENON on 7/13/2017.
 *
 */

public class FaultContract {
    @DataType(DataType.Type.INTEGER)
    @PrimaryKey
    @AutoIncrement
    public  static final String _ID = "_id";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String COLUMN_BU= "BU";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String COLUMN_UNDERTAKING= "undertaking";

    @DataType(DataType.Type.REAL)
    @NotNull
    public static final String COLUMN_REVENUE= "revenue";

    @DataType(DataType.Type.REAL)
    @NotNull
    public static final String COLUMN_ENERGY= "energy";

    @DataType(DataType.Type.REAL)
    @NotNull
    public static final String COLUMN_REVENUE_PER_ENERGY= "revenue_per_energy";

    @DataType(DataType.Type.INTEGER)
    @NotNull
    public static final String COLUMN_NUM_OF_CUSTOMERS= "num_of_customers";

    @DataType(DataType.Type.TEXT)
    @NotNull
    public static final String COLUMN_FAULT_TYPE= "fault_type";

    @DataType(DataType.Type.REAL)
    @NotNull
    public static final String COLUMN_COST= "cost";

    @DataType(DataType.Type.REAL)
    @NotNull
    public static final String COLUMN_DATE= "date";


    /* Helpers to retrieve column values */
    public static String getColumnString(Cursor cursor, String columnName) {
        return cursor.getString( cursor.getColumnIndex(columnName) );
    }

    public static int getColumnInt(Cursor cursor, String columnName) {
        return cursor.getInt( cursor.getColumnIndex(columnName) );
    }

    public static long getColumnLong(Cursor cursor, String columnName) {
        return cursor.getLong( cursor.getColumnIndex(columnName) );
    }

    public static float getColumnFloat(Cursor cursor, String columnName) {
        return cursor.getFloat( cursor.getColumnIndex(columnName) );
    }


}
