package org.project.model.clients;

public class Child extends  ClientType{

    public Child() {
    }

    @Override
    public String getInfo() {
        return "Typ klienta: Mlody perspektywiczny czlowiek \n" + super.getInfo();
    }

    @Override
    protected int applyLimits() {
        return super.applyLimits() + 1500;
    }
}
