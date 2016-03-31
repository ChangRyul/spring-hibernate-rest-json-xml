package com.igloosec.app.dto.response;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
@XmlRootElement(name="Temperatures")
public class Temperatures implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<AgentDesc> agentDescs = new ArrayList<>();

    private List<TemperatureResponse> temperature = new ArrayList<>();

    public List<TemperatureResponse> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<TemperatureResponse> temperature) {
        this.temperature = temperature;
    }

    public List<AgentDesc> getAgentDescs() {
        return agentDescs;
    }

    public void setAgentDescs(List<AgentDesc> agentDescs) {
        this.agentDescs = agentDescs;
    }
}
