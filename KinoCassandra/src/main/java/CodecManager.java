package pl.kielce.tu.cassandra.mapper;
import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.data.UdtValue;
import com.datastax.oss.driver.api.core.type.UserDefinedType;
import com.datastax.oss.driver.api.core.type.codec.TypeCodec;
import com.datastax.oss.driver.api.core.type.codec.registry.CodecRegistry;
import com.datastax.oss.driver.api.core.type.codec.registry.MutableCodecRegistry;

import pl.kielce.tu.cassandra.simple.SimpleManager;

public class CodecManager extends SimpleManager{

	public CodecManager(CqlSession session) {
		super(session);
	}

	public void registerSeansCodec() {
		CodecRegistry codecRegistry = session.getContext().getCodecRegistry();
		UserDefinedType coordinatesUdt =
		    session
		        .getMetadata()
		        .getKeyspace("film")
		        .flatMap(ks -> ks.getUserDefinedType("seans"))
		        .orElseThrow(IllegalStateException::new);
		TypeCodec<UdtValue> typeCodec = codecRegistry.codecFor(coordinatesUdt);
		SeansCodec seansCodec = new SeansCodec(typeCodec);
		((MutableCodecRegistry) codecRegistry).register(seansCodec);
	}
}
