package pl.kielce.tu.cassandra.mapper;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Delete;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;
import com.datastax.oss.driver.api.mapper.annotations.Update;

@Dao
public interface FilmDao {
  @Select
  Film findById(Integer id);

  @Insert
  void save(Film film);

  @Update
  void update(Film film);
  
  @Delete
  void delete(Film film);
}
