package com.example.NaucnaCentrala.elasticsearch.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.directory.SearchResult;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchResultMapper;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.aggregation.impl.AggregatedPageImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.example.NaucnaCentrala.dto.AuthorDTO;
import com.example.NaucnaCentrala.elasticsarch.repository.BookRepository;
import com.example.NaucnaCentrala.lucene.model.IndexUnit;
import com.example.NaucnaCentrala.lucene.model.IndexUnitDto;
import com.example.NaucnaCentrala.lucene.model.RequiredHighlighter;
import com.example.NaucnaCentrala.lucene.model.ResultDto;
import com.example.NaucnaCentrala.model.Author;

@Service
public class ResultRetrieverService {
	
	    @Autowired
	    ElasticsearchTemplate template;

	    public List<IndexUnitDto> getResults(org.elasticsearch.index.query.QueryBuilder query) {
	        if (query == null) {
	            return null;
	        }
	        
	        Map<IndexUnit, IndexUnitDto> highlights = new HashMap<>();

	        SearchQuery searchQuery = new NativeSearchQueryBuilder()
							                .withQuery(query)
							                .withHighlightFields(new HighlightBuilder.Field("pdfText")).build();

	        Page<IndexUnit> result = template.queryForPage(searchQuery, IndexUnit.class, new SearchResultMapper() {
	        	
	            @Override
	            public <T> AggregatedPage<T> mapResults(SearchResponse response, Class<T> aClass, Pageable pageable) {
	                List<IndexUnit> chunk = new ArrayList<IndexUnit>();
	                for (SearchHit searchHit : response.getHits()) {
	                    if (response.getHits().getHits().length <= 0) {
	                        return null;
	                    }
	                    IndexUnit indexEntity = new IndexUnit();
	                    indexEntity.setId((String) searchHit.getSourceAsMap().get("id"));
	                    indexEntity.setTitle((String) searchHit.getSourceAsMap().get("title"));
	                    indexEntity.setMagazineName((String) searchHit.getSourceAsMap().get("magazineName"));
	                    indexEntity.setKeywords((String) searchHit.getSourceAsMap().get("keywords"));
	                    indexEntity.setScienceAreaName((String) searchHit.getSourceAsMap().get("scienceAreaName"));
	                    indexEntity.setPdfText((String) searchHit.getSourceAsMap().get("pdfText"));
	                    indexEntity.setOpenAccessMagazine((boolean)searchHit.getSourceAsMap().get("openAccessMagazine"));
	                    //indexEntity.setAuthor((Author) searchHit.getSourceAsMap().get("author"));

	                    StringBuilder stringBuilder = new StringBuilder();
	                    if(searchHit.getHighlightFields().get("pdfText") != null){
	                        Text[] text = searchHit.getHighlightFields().get("pdfText").fragments();
	                        for(Text t : text){
	                            stringBuilder.append(t.toString());
	                            stringBuilder.append("...");
	                        }
	                    }
	                    IndexUnitDto paperDto = convertSciencePaperESToSearchSciencePaperES(indexEntity);
	                    if(!stringBuilder.toString().equals("")){
	                        paperDto.setText(indexEntity.getPdfText().substring(0, 300));
	                    }else
	                        paperDto.setText(stringBuilder.toString());

	                    highlights.put(indexEntity, paperDto);
	                    chunk.add(indexEntity);
	                }
	                if (chunk.size() > 0) {
	                    return new AggregatedPageImpl<T>((List<T>) chunk);
	                }
	                return null;
	            }
	        });

	        List<IndexUnitDto> retVal = new ArrayList<>();

	        if(result != null){
	            for(IndexUnit paperES: result){
	                retVal.add(highlights.get(paperES));
	            }
	        }

	        return retVal;
	    }

	    public static IndexUnitDto convertSciencePaperESToSearchSciencePaperES(IndexUnit sciencePaperES) {
	        ModelMapper mapper = new ModelMapper();
	        return mapper.map(sciencePaperES, IndexUnitDto.class);
	    }
}
