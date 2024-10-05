package org.project.model.clients;

public class ClientType {

    public ClientType() {
    }

    protected int applyLimits() {
    return 0;
}

public String getInfo(){


    return "Limit: " + applyLimits();
}

}
