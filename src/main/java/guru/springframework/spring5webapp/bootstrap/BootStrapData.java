package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Started in Bootstrap");

        Publisher ss = new Publisher("Simon & Schuster","New York","NY",10020,"1230 Avenue of the Americas " );
        publisherRepository.save(ss);

        System.out.println("Number of publishers: "+publisherRepository.count());

        Author erik = new Author("Erik", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");

        erik.getBooks().add(ddd);
        ddd.getAuthors().add(erik);
        ddd.setPublisher(ss);
        ss.getBooks().add(ddd);

        authorRepository.save(erik);
        bookRepository.save(ddd);
        publisherRepository.save(ss);

        Author rdd = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "12312312321321");
        rdd.getBooks().add(noEJB);
        noEJB.getAuthors().add(rdd);
        noEJB.setPublisher(ss);
        ss.getBooks().add(noEJB);

        authorRepository.save(rdd);
        bookRepository.save(noEJB);
        publisherRepository.save(ss);

        System.out.println("Number of books: "+bookRepository.count());
        System.out.println("Number of books for publisher: "+publisherRepository.findAll().iterator().next().getBooks().size());


    }
}
