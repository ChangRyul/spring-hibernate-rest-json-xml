package com.igloosec.app.dto.response;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
@XmlRootElement(name="fluxes")
public class Fluxes implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<AgentDesc> agentDescs = new ArrayList<>();

    private List<Flux> flux = new ArrayList<>();

    public List<Flux> getFlux() {
        return flux;
    }

    public void setFlux(List<Flux> flux) {
        this.flux = flux;
    }

    public List<AgentDesc> getAgentDescs() {
        return agentDescs;
    }

    public void setAgentDescs(List<AgentDesc> agentDescs) {
        this.agentDescs = agentDescs;
    }
}
