package com.example.mongoproject.repo.User;

import com.example.mongoproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Repository
@Transactional(readOnly = true)
public class UserRepoCustomImpl implements UserRepoCustom {

    @Autowired
    MongoTemplate temp;

    @Override
    public List<User> custom() {
        var query = new Query();
        List<AggregationOperation> matchOperations = new ArrayList<>();
//        matchOperations.add(Aggregation.match(Criteria.where("name").regex("a")));

        ProjectionOperation projectToMatchModel = project()
                .andExpression("id").as("id")
                .andExpression("name").as("name")
                .andExpression("address").as("address")
                .andExpression("address.city").as("city2");
        matchOperations.add(projectToMatchModel);


        Aggregation aggregation = Aggregation.newAggregation(matchOperations);

        return temp.aggregate(aggregation, User.class, User.class).getMappedResults();
//        temp.find(query, User.class);

    }
}
