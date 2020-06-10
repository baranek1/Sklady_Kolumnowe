package pl.kielce.tu.cassandra.mapper;


import javax.annotation.Nullable;

import com.datastax.oss.driver.api.core.data.UdtValue;
import com.datastax.oss.driver.api.core.type.UserDefinedType;
import com.datastax.oss.driver.api.core.type.codec.MappingCodec;
import com.datastax.oss.driver.api.core.type.codec.TypeCodec;
import com.datastax.oss.driver.api.core.type.reflect.GenericType;

import edu.umd.cs.findbugs.annotations.NonNull;

public class SeansCodec extends MappingCodec<UdtValue, Seans> {
    public SeansCodec(@NonNull TypeCodec<UdtValue> innerCodec) {
        super(innerCodec, GenericType.of(Seans.class));
    }

    @NonNull
    @Override
    public UserDefinedType getCqlType() {
        return (UserDefinedType) super.getCqlType();
    }

    @Nullable
    @Override
    protected Seans innerToOuter(@Nullable UdtValue seans) {
        return seans == null ? null : new Seans(seans.getInt("sala"), seans.getInt("ilMiejsc"));
    }

    @Nullable
    @Override
    protected UdtValue outerToInner(@Nullable Seans seans) {
        return seans == null ? null : getCqlType().newValue().setInt("sala", seans.getSala()).setInt("ilMiejsc", seans.getIlMiejsc());
    }
}
