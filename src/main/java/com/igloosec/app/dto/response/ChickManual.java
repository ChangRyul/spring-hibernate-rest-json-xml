package com.igloosec.app.dto.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
public class ChickManual implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Table1> table1 = new ArrayList<>();

    public List<Table1> getTable1() {
        return table1;
    }

    public void setTable1(List<Table1> table1) {
        this.table1 = table1;
    }
}
