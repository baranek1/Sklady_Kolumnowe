package pl.kielce.tu.cassandra.mapper;

public class Seans {
    public Integer sala;
    public Integer ilMiejsc;

    public Seans() {
    }

    public Seans(Integer sala, Integer ilMiejsc) {
        this.sala = sala;
        this.ilMiejsc = ilMiejsc;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(Integer sala) {
        this.sala = sala;
    }

    public Integer getIlMiejsc() {
        return ilMiejsc;
    }

    public void setIlMiejsc(Integer ilMiejsc) {
        this.ilMiejsc = ilMiejsc;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder()
                .append("{")
                .append(sala + ",")
                .append(ilMiejsc + ",")
                .append("}");
        return builder.toString();
    }
}