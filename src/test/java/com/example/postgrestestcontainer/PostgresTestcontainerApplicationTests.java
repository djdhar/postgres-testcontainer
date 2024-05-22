package com.example.postgrestestcontainer;

import com.example.postgrestestcontainer.dto.BookCreateRequest;
import com.example.postgrestestcontainer.dto.BookUpdateRequest;
import com.example.postgrestestcontainer.model.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostgresTestcontainerApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Order(1)
	@Test
	void contextLoads() {
	}

	@Order(2)
	@Test
	void testCreateRequest() throws JsonProcessingException {
		this.webTestClient
				.post()
				.uri("/v1/book")
				.bodyValue(createRequest())
				.exchange()
				.expectStatus().isOk()
				.expectBody().json(createdBook());
	}

	@Order(3)
	@Test
	void testGetRequestOnCreation() throws JsonProcessingException {
		this.webTestClient
				.get()
				.uri("/v1/book/1")
				.exchange()
				.expectStatus().isOk()
				.expectBody().json(createdBook());
	}

	@Order(4)
	@Test
	void testUpdateRequest() throws JsonProcessingException {
		this.webTestClient
				.put()
				.uri("/v1/book/1")
				.bodyValue(updateBookRequest())
				.exchange()
				.expectStatus().isOk()
				.expectBody().json(updatedBook());
	}

	@Order(5)
	@Test
	void testGetRequestOnUpdate() throws JsonProcessingException {
		this.webTestClient
				.get()
				.uri("/v1/book/1")
				.exchange()
				.expectStatus().isOk()
				.expectBody().json(updatedBook());
	}

	@Order(6)
	@Test
	void testDeleteRequest() throws JsonProcessingException {
		this.webTestClient
				.delete()
				.uri("/v1/book/1")
				.exchange()
				.expectStatus().isOk()
				.expectBody().json(updatedBook());
	}

	@Order(7)
	@Test
	void testGetRequestOnDelete() throws JsonProcessingException {
		this.webTestClient
				.get()
				.uri("/v1/book/1")
				.exchange()
				.expectStatus().isOk()
				.expectBody().json("{}");
	}

	@Order(8)
	@Test
	void testGetAllBooks() throws JsonProcessingException {
		this.webTestClient
				.post()
				.uri("/v1/book")
				.bodyValue(createRequest2())
				.exchange()
				.expectStatus().isOk(); //created id is 2
		this.webTestClient
				.post()
				.uri("/v1/book")
				.bodyValue(createRequest3())
				.exchange()
				.expectStatus().isOk(); //created id is 3
		this.webTestClient
				.post()
				.uri("/v1/book")
				.bodyValue(createRequest4())
				.exchange()
				.expectStatus().isOk(); //created id is 4

		this.webTestClient
				.get()
				.uri("/v1/books")
				.exchange()
				.expectStatus().isOk()
				.expectBody().json(createdBooks()); //display books with ids 2, 3, 4
	}

	private BookUpdateRequest updateBookRequest() {
		BookUpdateRequest request = new BookUpdateRequest();
		request.setBookName("book1_modified");
		request.setBookDescription("desc1_modified");
		return request;
	}
	private BookCreateRequest createRequest() {
		BookCreateRequest request = new BookCreateRequest();
		request.setBookName("book1");
		request.setBookDescription("desc1");
		return request;
	}

	private BookCreateRequest createRequest2() {
		BookCreateRequest request = new BookCreateRequest();
		request.setBookName("book2");
		request.setBookDescription("desc2");
		return request;
	}
	private BookCreateRequest createRequest3() {
		BookCreateRequest request = new BookCreateRequest();
		request.setBookName("book3");
		request.setBookDescription("desc3");
		return request;
	}
	private BookCreateRequest createRequest4() {
		BookCreateRequest request = new BookCreateRequest();
		request.setBookName("book4");
		request.setBookDescription("desc4");
		return request;
	}

	private String updatedBook() throws JsonProcessingException {
		Book book = new Book();
		book.setId(1L);
		book.setBookName("book1_modified");
		book.setBookDescription("desc1_modified");
		return objectToJsonString(book);
	}

	private String createdBook() throws JsonProcessingException {
		Book book = new Book();
		book.setId(1L);
		book.setBookName("book1");
		book.setBookDescription("desc1");
		return objectToJsonString(book);
	}

	private String createdBooks() throws JsonProcessingException {
		Book book2 = new Book();
		book2.setId(2L);
		book2.setBookName("book2");
		book2.setBookDescription("desc2");
		Book book3 = new Book();
		book3.setId(3L);
		book3.setBookName("book3");
		book3.setBookDescription("desc3");
		Book book4 = new Book();
		book4.setId(4L);
		book4.setBookName("book4");
		book4.setBookDescription("desc4");
		return objectToJsonString(List.of(book2, book3, book4));
	}

	private String objectToJsonString(Object object) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}

}
