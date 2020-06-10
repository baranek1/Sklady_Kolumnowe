package pl.kielce.tu.cassandra.mapper;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatementBuilder;
import com.datastax.oss.driver.api.querybuilder.schema.CreateKeyspace;
import com.datastax.oss.driver.api.querybuilder.schema.Drop;
import com.datastax.oss.driver.api.querybuilder.SchemaBuilder;
import com.datastax.oss.driver.api.querybuilder.select.Select;

import java.time.Duration;

import static com.datastax.oss.driver.api.querybuilder.QueryBuilder.selectFrom;


public class Builder {

    final private static Duration TIMEOUT = Duration.ofSeconds(10);

    protected CqlSession session;

    public Builder(CqlSession session, String keyspaceName) {
        super(session);
        this.keyspaceName = keyspaceName;

    public void executeSimpleStatement(String statement) {
        session.execute(new SimpleStatementBuilder(statement).build().setTimeout(TIMEOUT));
        System.out.println("Statement \"" + statement + "\" executed successfully");
    }

    public void executeSimpleStatement(SimpleStatement statement) {
        session.execute(statement.setTimeout(TIMEOUT));
        System.out.println("Statement \"" + statement.getQuery() + "\" executed successfully");
    }
    }
private String keyspaceName;
public void selectKeyspaces() {
        Select query = selectFrom("system_schema", "keyspaces").column("keyspace_name");
        SimpleStatement statement = query.build();
        ResultSet resultSet = session.execute(statement);

        System.out.print("Keyspaces = ");
        for (Row row : resultSet) {
        System.out.print(row.getString("keyspace_name") + ", ");
        }
        System.out.println();
        }

public void createKeyspace() {
        CreateKeyspace create = SchemaBuilder.createKeyspace(keyspaceName).withSimpleStrategy(1);
        executeSimpleStatement(create.build());
        }

public void useKeyspace() {
        executeSimpleStatement("USE " + keyspaceName + ";");
        }

public void dropKeyspace() {
        Drop drop = SchemaBuilder.dropKeyspace(keyspaceName).ifExists();
        executeSimpleStatement(drop.build());
        }
}
