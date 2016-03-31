package com.igloosec.app.dto.response;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
@XmlRootElement(name="humidities")
public class Humidities implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<AgentDesc> agentDescs = new ArrayList<>();

    private List<HumidityResponse> humidity = new ArrayList<>();

    public List<HumidityResponse> getHumidity() {
        return humidity;
    }

    public void setHumidity(List<HumidityResponse> humidity) {
        this.humidity = humidity;
    }

    public List<AgentDesc> getAgentDescs() {
        return agentDescs;
    }

    public void setAgentDescs(List<AgentDesc> agentDescs) {
        this.agentDescs = agentDescs;
    }
}
