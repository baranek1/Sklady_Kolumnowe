package pl.kielce.tu.cassandra.mapper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

@Entity
public class Film {

	@PartitionKey
	public Integer id;

	public String tytul;

	public String imieRezysera;

	public String nazwiskoRezysera;

	public String czas;

	public Seans seans;


	public Film() {
	}

	public Film(Integer id, String tytul, String imieRezysera, String nazwiskoRezysera, String czas, Seans seans) {
		this.id = id;
		this.tytul = tytul;
		this.imieRezysera = imieRezysera;
		this.nazwiskoRezysera = nazwiskoRezysera;
		this.czas = czas;
		this.seans = seans;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTytul() {
		return tytul;
	}

	public void setTytul(String tytul) {
		this.tytul = tytul;
	}

	public String getImieRezysera() {
		return imieRezysera;
	}

	public void setImieRezysera(String imieRezysera) {
		this.imieRezysera = imieRezysera;
	}

	public String getNazwiskoRezysera() {
		return nazwiskoRezysera;
	}

	public void setNazwiskoRezysera(String nazwiskoRezysera) {
		this.nazwiskoRezysera = nazwiskoRezysera;
	}

	public String getCzas(){ return czas;}

	public void setCzas(String czas) {
		this.czas = czas;
	}

	public Seans getSeans() {
		return seans;
	}

	public void setSeans(Seans seans) {
		this.seans = seans;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder()
			.append("student {")
			.append(id + ",")
			.append(tytul + ",")
			.append(imieRezysera + ",")
			.append(nazwiskoRezysera + ",")
			.append(czas + ",")
			.append(seans + ",")
			.append("}");
		return builder.toString();
	}
}
