package com.example.mongoproject.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Base64;
import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceGeneratorService {

    private final Logger log = LoggerFactory.getLogger(SequenceGeneratorService.class);

    @Autowired
    private MongoOperations mongoOperations;

    protected static String encodeBase64(long id) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(BigInteger.valueOf(id).toByteArray());
    }

    public long generateSequenceLong(String seqName) {
        log.debug("generate sequence long {}", seqName);
        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        long result = !Objects.isNull(counter) ? counter.getSeq() : 1;
        log.debug("after {}", result);
        return result;
    }

    public String generateSequence(String seqName) {
        long id = generateSequenceLong(seqName);
        return encodeBase64(id);
    }
}
