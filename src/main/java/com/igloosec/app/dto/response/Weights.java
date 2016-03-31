package com.igloosec.app.dto.response;

import javax.security.sasl.SaslClient;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2016-03-31.
 */
@XmlRootElement(name="weights")
public class Weights implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<AgentDesc> agentDescs = new ArrayList<>();

    private List<Weight> weight = new ArrayList<>();

    public List<Weight> getWeight() {
        return weight;
    }

    public void setWeight(List<Weight> weight) {
        this.weight = weight;
    }

    public List<AgentDesc> getAgentDescs() {
        return agentDescs;
    }

    public void setAgentDescs(List<AgentDesc> agentDescs) {
        this.agentDescs = agentDescs;
    }
}
