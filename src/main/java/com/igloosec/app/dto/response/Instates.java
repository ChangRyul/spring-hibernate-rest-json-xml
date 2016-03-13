package com.igloosec.app.dto.response;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-13.
 */
@XmlRootElement(name="instates")
public class Instates implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<InstateResponse> instate = new ArrayList<InstateResponse>();

    public List<InstateResponse> getInstate() {
        return instate;
    }

    public void setInstate(List<InstateResponse> instate) {
        this.instate = instate;
    }
}
