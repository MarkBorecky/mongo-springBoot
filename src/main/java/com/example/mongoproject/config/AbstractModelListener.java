package com.example.mongoproject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;

public class AbstractModelListener<T extends Entity<String>> extends AbstractMongoEventListener<T> {
    private final Class<T> clazz;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    protected AbstractModelListener() {
        Class<?> typeArgument = GenericTypeResolver.resolveTypeArgument(this.getClass(), AbstractModelListener.class);
        this.clazz = (Class<T>) typeArgument;
    }

    @Override
    public void onBeforeConvert(BeforeConvertEvent<T> event) {
        if (event.getSource().getId() == null) {
            event.getSource().setId(sequenceGenerator.generateSequence(clazz.getSimpleName()));
        }
    }
}
