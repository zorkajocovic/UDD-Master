package com.example.NaucnaCentrala.elasticsearch.services;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;
import static org.elasticsearch.index.query.QueryBuilders.matchQuery;

import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchPhraseQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.NaucnaCentrala.lucene.model.QueryDto;

public class QueryBuilder {

    private static int maxEdits = 1;

    @Autowired
    IndexerService indexerService;
    
    public static org.elasticsearch.index.query.QueryBuilder buildQuery(List<QueryDto> fields, List<String> operations,
                                                                        boolean phrase) throws IllegalArgumentException, ParseException{
        String errorMessage = "";
        List<org.elasticsearch.index.query.QueryBuilder> allQueries = new ArrayList<>();
        for (QueryDto entry : fields)
        {
            String field = entry.getField();
            String value = IndexerService.changeCharacter(entry.getValue());

            if(field == null || field.equals("")){
                errorMessage += "Field not specified";
            }
            if(value == null){
                if(!errorMessage.equals("")) errorMessage += "\n";
                errorMessage += "Value not specified";
            }
            if(!errorMessage.equals("")){
                throw new IllegalArgumentException(errorMessage);
            }

            org.elasticsearch.index.query.QueryBuilder retVal = null;

            if(phrase){
                if(field.equals("author")){
                    retVal = new NestedQueryBuilder("author",
                            QueryBuilders.boolQuery().should(matchPhraseQuery("author.name", value))
                                    .should(matchPhraseQuery("author.surname", value)), ScoreMode.Avg);
                }else{
                    retVal = new MatchPhraseQueryBuilder(field, value);
                }
            }else{
                if(field.equals("author")){
                    retVal = new NestedQueryBuilder("author",
                            QueryBuilders.boolQuery().should(matchQuery("author.name", value))
                                    .should(matchQuery("author.surname", value)), ScoreMode.Avg);
                }else{
                    retVal = new MatchQueryBuilder(field, value);
                }
            }

            allQueries.add(retVal);
        }

        BoolQueryBuilder finalQuery = QueryBuilders.boolQuery();
   
        if(!operations.isEmpty()){
            int counter = 0;
            boolean firstTime = true;
            for(org.elasticsearch.index.query.QueryBuilder query: allQueries) {
                String oper = operations.get(counter);
                if (oper.equals("AND"))
                    finalQuery.must(query);
                else if (oper.equals("OR"))
                    finalQuery.should(query);
                if (!firstTime)
                    counter++;
                firstTime = false;
            }
        }else{
            for(org.elasticsearch.index.query.QueryBuilder query: allQueries){
                finalQuery.must(query);
            }
        }

        return finalQuery;
    }

	public static int getMaxEdits() {
		return maxEdits;
	}

	public static void setMaxEdits(int maxEdits) {
		QueryBuilder.maxEdits = maxEdits;
	}
}
