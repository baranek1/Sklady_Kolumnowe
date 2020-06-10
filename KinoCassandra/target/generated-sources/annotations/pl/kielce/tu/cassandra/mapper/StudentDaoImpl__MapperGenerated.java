package pl.kielce.tu.cassandra.mapper;

import com.datastax.oss.driver.api.core.cql.BoundStatement;
import com.datastax.oss.driver.api.core.cql.BoundStatementBuilder;
import com.datastax.oss.driver.api.core.cql.PreparedStatement;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.api.mapper.MapperContext;
import com.datastax.oss.driver.api.mapper.entity.saving.NullSavingStrategy;
import com.datastax.oss.driver.internal.core.util.concurrent.BlockingOperation;
import com.datastax.oss.driver.internal.core.util.concurrent.CompletableFutures;
import com.datastax.oss.driver.internal.mapper.DaoBase;
import com.datastax.oss.driver.internal.querybuilder.update.DefaultUpdate;
import java.lang.Boolean;
import java.lang.Integer;
import java.lang.Override;
import java.lang.Throwable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Generated by the DataStax driver mapper, do not edit directly.
 */
public class StudentDaoImpl__MapperGenerated extends DaoBase implements StudentDao {
  private static final Logger LOG = LoggerFactory.getLogger(StudentDaoImpl__MapperGenerated.class);

  private final StudentHelper__MapperGenerated studentHelper;

  private final PreparedStatement findByIdStatement;

  private final PreparedStatement saveStatement;

  private final PreparedStatement updateStatement;

  private final PreparedStatement deleteStatement;

  private StudentDaoImpl__MapperGenerated(MapperContext context,
      StudentHelper__MapperGenerated studentHelper, PreparedStatement findByIdStatement,
      PreparedStatement saveStatement, PreparedStatement updateStatement,
      PreparedStatement deleteStatement) {
    super(context);
    this.studentHelper = studentHelper;
    this.findByIdStatement = findByIdStatement;
    this.saveStatement = saveStatement;
    this.updateStatement = updateStatement;
    this.deleteStatement = deleteStatement;
  }

  @Override
  public Film findById(Integer id) {
    BoundStatementBuilder boundStatementBuilder = findByIdStatement.boundStatementBuilder();

    boundStatementBuilder = boundStatementBuilder.set("id", id, Integer.class);

    BoundStatement boundStatement = boundStatementBuilder.build();
    return executeAndMapToSingleEntity(boundStatement, studentHelper);
  }

  @Override
  public void save(Film film) {
    BoundStatementBuilder boundStatementBuilder = saveStatement.boundStatementBuilder();
    studentHelper.set(film, boundStatementBuilder, NullSavingStrategy.DO_NOT_SET);

    BoundStatement boundStatement = boundStatementBuilder.build();
    execute(boundStatement);
  }

  @Override
  public void update(Film film) {
    BoundStatementBuilder boundStatementBuilder = updateStatement.boundStatementBuilder();
    studentHelper.set(film, boundStatementBuilder, NullSavingStrategy.DO_NOT_SET);

    BoundStatement boundStatement = boundStatementBuilder.build();
    execute(boundStatement);
  }

  @Override
  public void delete(Film film) {
    BoundStatementBuilder boundStatementBuilder = deleteStatement.boundStatementBuilder();

    boundStatementBuilder = boundStatementBuilder.set("id", film.getId(), Integer.class);

    BoundStatement boundStatement = boundStatementBuilder.build();
    execute(boundStatement);
  }

  public static CompletableFuture<StudentDao> initAsync(MapperContext context) {
    LOG.debug("[{}] Initializing new instance for keyspace = {} and table = {}",
        context.getSession().getName(),
        context.getKeyspaceId(),
        context.getTableId());
    throwIfProtocolVersionV3(context);
    try {
      // Initialize all entity helpers
      StudentHelper__MapperGenerated studentHelper = new StudentHelper__MapperGenerated(context);
      if ((Boolean)context.getCustomState().get("datastax.mapper.schemaValidationEnabled")) {
        studentHelper.validateEntityFields();
      }
      List<CompletionStage<PreparedStatement>> prepareStages = new ArrayList<>();
      // Prepare the statement for `findById(java.lang.Integer)`:
      SimpleStatement findByIdStatement_simple = studentHelper.selectByPrimaryKeyParts(1).build();
      LOG.debug("[{}] Preparing query `{}` for method findById(java.lang.Integer)",
          context.getSession().getName(),
          findByIdStatement_simple.getQuery());
      CompletionStage<PreparedStatement> findByIdStatement = prepare(findByIdStatement_simple, context);
      prepareStages.add(findByIdStatement);
      // Prepare the statement for `save(pl.kielce.tu.cassandra.mapper.Film)`:
      SimpleStatement saveStatement_simple = studentHelper.insert().build();
      LOG.debug("[{}] Preparing query `{}` for method save(pl.kielce.tu.cassandra.mapper.Film)",
          context.getSession().getName(),
          saveStatement_simple.getQuery());
      CompletionStage<PreparedStatement> saveStatement = prepare(saveStatement_simple, context);
      prepareStages.add(saveStatement);
      // Prepare the statement for `update(pl.kielce.tu.cassandra.mapper.Film)`:
      SimpleStatement updateStatement_simple = SimpleStatement.newInstance(((DefaultUpdate)studentHelper.updateByPrimaryKey()).asCql());
      LOG.debug("[{}] Preparing query `{}` for method update(pl.kielce.tu.cassandra.mapper.Film)",
          context.getSession().getName(),
          updateStatement_simple.getQuery());
      CompletionStage<PreparedStatement> updateStatement = prepare(updateStatement_simple, context);
      prepareStages.add(updateStatement);
      // Prepare the statement for `delete(pl.kielce.tu.cassandra.mapper.Film)`:
      SimpleStatement deleteStatement_simple = studentHelper.deleteByPrimaryKeyParts(1).build();
      LOG.debug("[{}] Preparing query `{}` for method delete(pl.kielce.tu.cassandra.mapper.Film)",
          context.getSession().getName(),
          deleteStatement_simple.getQuery());
      CompletionStage<PreparedStatement> deleteStatement = prepare(deleteStatement_simple, context);
      prepareStages.add(deleteStatement);
      // Initialize all method invokers
      // Build the DAO when all statements are prepared
      return CompletableFutures.allSuccessful(prepareStages)
          .thenApply(v -> (StudentDao) new StudentDaoImpl__MapperGenerated(context,
              studentHelper,
              CompletableFutures.getCompleted(findByIdStatement),
              CompletableFutures.getCompleted(saveStatement),
              CompletableFutures.getCompleted(updateStatement),
              CompletableFutures.getCompleted(deleteStatement)))
          .toCompletableFuture();
    } catch (Throwable t) {
      return CompletableFutures.failedFuture(t);
    }
  }

  public static StudentDao init(MapperContext context) {
    BlockingOperation.checkNotDriverThread();
    return CompletableFutures.getUninterruptibly(initAsync(context));
  }
}