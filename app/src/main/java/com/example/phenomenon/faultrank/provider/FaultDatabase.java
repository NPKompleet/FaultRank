package com.example.phenomenon.faultrank.provider;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

/**
 * Created by PHENOMENON on 7/13/2017.
 */

@Database(version= FaultDatabase.VERSION)
public class FaultDatabase {
    public static final int VERSION=1;

    @Table(FaultContract.class)
    public static final String FAULT_TABLE= "faults";

}
