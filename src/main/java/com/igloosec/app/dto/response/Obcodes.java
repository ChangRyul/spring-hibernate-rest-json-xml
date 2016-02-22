package com.igloosec.app.dto.response;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by CRChoi on 2016. 2. 22..
 */
@XmlRootElement(name="obcodes")
public class Obcodes implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Obcode> obcode = new ArrayList<Obcode>();

    public List<Obcode> getObcode() {
        return obcode;
    }

    public void setObcode(List<Obcode> obcode) {
        this.obcode = obcode;
    }
}