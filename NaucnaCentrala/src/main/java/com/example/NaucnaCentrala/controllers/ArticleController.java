package com.example.NaucnaCentrala.controllers;

import com.example.NaucnaCentrala.lucene.model.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.NaucnaCentrala.dto.ArticleDTO;
import com.example.NaucnaCentrala.elasticsearch.services.IndexerService;
import com.example.NaucnaCentrala.elasticsearch.services.QueryBuilder;
import com.example.NaucnaCentrala.elasticsearch.services.ResultRetrieverService;
import com.example.NaucnaCentrala.lucene.model.IndexUnit;
import com.example.NaucnaCentrala.model.Article;
import com.example.NaucnaCentrala.model.Author;
import com.example.NaucnaCentrala.model.Magazine;
import com.example.NaucnaCentrala.services.ArticleService;
import com.example.NaucnaCentrala.services.AuthorService;
import com.example.NaucnaCentrala.services.MagazineService;
import com.example.NaucnaCentrala.services.UploadFileService;
import com.itextpdf.text.DocumentException;


@RestController
@RequestMapping(value = "api/article")
public class ArticleController {
	
	@Autowired
	ArticleService articleService;
	
	@Autowired
	AuthorService authorService;
	
	@Autowired
	MagazineService magazineService;
	
	@Autowired
	UploadFileService uploadService;
	
	@Autowired
	IndexerService indexerService;
	
	@Autowired
	ResultRetrieverService resultRetrieverService; 
	
    private final String path = System.getProperty("user.dir") + "\\src\\main\\resources\\upload-pdf";

	@GetMapping("/allArticles")
	public ResponseEntity<List<ArticleDTO>> getAllArticles(){
		
		List<Article> articles = articleService.getAll();
		List<ArticleDTO> articleDTOs = new ArrayList<>();
		
		for(Article c : articles){
			articleDTOs.add(articleService.mapToDTO(c));
		}
		
		return new ResponseEntity<>(articleDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/allIndexedArticles")
	public ResponseEntity<List<IndexUnit>> getAllIndexedArticles(){
		
		List<IndexUnit> articles = indexerService.findAll();
		return new ResponseEntity<>(articles, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ArticleDTO> getArticle(@PathVariable Long id) {
		Article c = articleService.getOne(id);
		if (c == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(new ArticleDTO(c), HttpStatus.OK);
	}

	@PostMapping("/addArticle")
	public ResponseEntity<ArticleDTO> addArticle(@RequestParam("fileName") String fileName, 
												@RequestParam("pdf") MultipartFile file, 
												@RequestParam("article") String article,
												@RequestParam("magazineId") String magazineId) throws IOException {
	
		org.json.JSONObject jsonArticle = new org.json.JSONObject(article);		

		fileName = fileName.replace(' ', '_');
	    String filePath = uploadService.store(file, fileName);
        filePath = filePath.replace("\\", "/");
	    
		Article c = new Article();
		c.setTitle(jsonArticle.getString("title"));
		c.setPdfLocation(filePath);
		c.setKeyWords(jsonArticle.getString("keywords"));
		c.setMagazineName(jsonArticle.getString("magazineName"));
		c.setScienceAreaName(jsonArticle.getString("selectedAreaName"));
		Long mag_id = Long.parseLong(magazineId);
		c.setMagazine(magazineService.getMagazine(mag_id));
		c.setAuthor(authorService.getAuthor(1));
		articleService.addArticle(c);

		IndexUnit indexUnit = new IndexUnit();
		indexUnit.setId(c.getId().toString());
		indexUnit.setTitle(c.getTitle());
		indexUnit.setMagazineName(c.getMagazineName());
		Magazine magazine = magazineService.getMagazine(mag_id);

		indexUnit.setOpenAccessMagazine(magazine.isOpenAccess());
		
		Author author = new Author();
		author.setId(c.getId());
		author.setCity(authorService.getAuthor(1).getCity());
		author.setCountry(authorService.getAuthor(1).getCountry());
		author.setName(authorService.getAuthor(1).getName());
		author.setSurname(authorService.getAuthor(1).getSurname());
		author.setEmail(authorService.getAuthor(1).getEmail());
		author.setPassword(authorService.getAuthor(1).getPassword());

		indexUnit.setAuthor(author);
		
		indexUnit.setKeywords(jsonArticle.getString("keywords"));
		indexUnit.setPdfText(articleService.getPdfText(c.getPdfLocation()));
		indexUnit.setPdfLocation(c.getPdfLocation());
		indexUnit.setScienceAreaName(c.getScienceAreaName());
		
		indexerService.add(indexUnit);
		return new ResponseEntity<>(new ArticleDTO(), HttpStatus.CREATED);
	}
	
	@PostMapping(value="/search", consumes="application/json")
    public ResponseEntity<List<IndexUnitDto>> searchTermQuery(@RequestBody ComplexQueryDto queryDto) throws Exception {
        org.elasticsearch.index.query.QueryBuilder query= QueryBuilder.buildQuery(queryDto.getValues(),
        		queryDto.getOperations(), queryDto.isPhrase());

        List<IndexUnitDto> results = resultRetrieverService.getResults(query);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
	
    @RequestMapping(value="downloadPdf/{title}", method=RequestMethod.GET)
    public ResponseEntity<byte[]> downloadPdf(@PathVariable String title) throws IOException, DocumentException {

        Article paper = this.articleService.getByTitle(title);
        String filename = paper.getTitle().replace(' ', '_') + ".pdf";

        File pdfFile = Paths.get(path + "/" + filename).toFile();

        byte[] contents = Files.readAllBytes(pdfFile.toPath());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        // Here you have to set the actual filename of your pdf
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(contents, headers, HttpStatus.OK);
        return response;

    }
}
