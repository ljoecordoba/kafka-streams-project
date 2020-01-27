package com.ljoe.streams;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class LogSerde implements Serde<Log> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public void close() {

    }

    @Override
    public Serializer<Log> serializer() {
        return null;
    }

    @Override
    public Deserializer<Log> deserializer() {
        return null;
    }
}
