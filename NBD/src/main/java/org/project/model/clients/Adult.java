package org.project.model.clients;

public class Adult extends ClientType {

    public Adult() {
    }

    @Override
    protected int applyLimits() {
        return super.applyLimits() + 15000;
    }

    @Override
    public String getInfo() {
        return "Typ klienta: Dorosly \n" + super.getInfo();
    }
}
