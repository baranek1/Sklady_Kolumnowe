package pl.kielce.tu.cassandra.mapper;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import org.omg.CORBA.TIMEOUT;

import java.time.Duration;

public class KinoTableManager {
    CqlSession session;
     public KinoTableManager(CqlSession session) {
        this.session = session;
    }
     public void createTable() {
        String fil = " CREATE TABLE Film (id int PRIMARY KEY, tytul text, imieRzysera text, nazwiskoRezysera text, czas text, sean List<Seans>);";
        String sea = " CREATE TYPE Seans (sala int, ilMiejsc int);";
        session.execute(new SimpleStatementBuilder(sea).build());
        session.execute(new SimpleStatementBuilder(fil).build());
    }

}
