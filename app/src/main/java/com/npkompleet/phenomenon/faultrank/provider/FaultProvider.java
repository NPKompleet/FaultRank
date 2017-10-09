package com.npkompleet.phenomenon.faultrank.provider;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

/**
 * Created by PHENOMENON on 7/13/2017.
 * 
 */

@ContentProvider(
        authority = FaultProvider.AUTHORITY,
        database = FaultDatabase.class)
public class FaultProvider {

    public static final String AUTHORITY= "com.npkompleet.phenomenon.faultrank.provider.authority";

    @TableEndpoint(table = FaultDatabase.FAULT_TABLE)
    public static class Faults {

        @ContentUri(
                path = "faults",
                type = "vnd.android.cursor.dir/faults",
                defaultSort = FaultContract._ID)
        public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/faults");
    }

}
