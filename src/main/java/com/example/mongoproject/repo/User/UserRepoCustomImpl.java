package com.example.mongoproject.repo.User;

import com.example.mongoproject.model.User;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.project;

@Repository
@Transactional(readOnly = true)
public class UserRepoCustomImpl implements UserRepoCustom {

    @Autowired
    MongoTemplate temp;

    @Override
    public Page<User> custom(Pageable pageable) {
        var query = new Query();
        query.with(pageable);
        List<User> list = temp.find(query, User.class);
        System.out.println("list.size() = " + list.size());
        list.forEach(u -> {
            u.setCity2(u.getAddress().getCity());
            System.out.println("u " + u.getName());
        });
        return new PageImpl<User>(list);
//
//        List<AggregationOperation> matchOperations = new ArrayList<>();
////        matchOperations.add(Aggregation.match(Criteria.where("name").is("Marek")));
//        matchOperations.add(getProjectionOperation());
//        Aggregation aggregation = Aggregation.newAggregation(matchOperations);
//        AggregationResults<User> results = temp.aggregate(aggregation, User.class, User.class);
//        List<User> list = results.getMappedResults();
    }

    private ProjectionOperation getProjectionOperation() {
        ProjectionOperation projectToMatchModel = project()
                .andExpression("id").as("id")
                .andExpression("name").as("name")
                .andExpression("address").as("address")
                .andExpression("address.city").as("city2");
        return projectToMatchModel;
    }
}
