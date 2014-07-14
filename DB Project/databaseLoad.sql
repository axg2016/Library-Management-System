

drop database library;
CREATE DATABASE LIBRARY;

CREATE TABLE LIBRARY.BOOK(
Book_Id CHAR(10) NOT NULL,
Title VARCHAR(30) NOT NULL,
PRIMARY KEY(Book_Id)
);


CREATE TABLE LIBRARY.BOOK_AUTHOR(
sBook_Id CHAR(30) NOT NULL,
sAuthor_name VARCHAR(100) NOT NULL,
PRIMARY KEY(sBook_Id, sAuthor_name),
FOREIGN KEY (sBook_Id) REFERENCES BOOK (Book_Id)
);



CREATE TABLE LIBRARY.LIBRARY_BRANCH(
Branch_Id CHAR(20) NOT NULL,
Branch_name VARCHAR(20) NOT NULL,
Address VARCHAR(100) NOT NULL,
PRIMARY KEY(Branch_Id)
);

CREATE TABLE LIBRARY.BOOK_COPIES(
Book_Id CHAR(30) NOT NULL,
Branch_Id CHAR(20) NOT NULL,
No_Of_Copies INTEGER(10) NOT NULL,
PRIMARY KEY(Book_Id,Branch_Id),
FOREIGN KEY (Book_Id) REFERENCES BOOK (Book_Id),
FOREIGN KEY (Branch_Id) REFERENCES LIBRARY_BRANCH (Branch_Id)
);

CREATE TABLE LIBRARY.BORROWER(
Card_no INTEGER(20) NOT NULL AUTO_INCREMENT, 
Fname VARCHAR(15) NOT NULL, 
Lname VARCHAR(15) NOT NULL, 
Address VARCHAR(100) NOT NULL, 
Phone Varchar(20) NOT NULL, 
PRIMARY KEY(Card_no)
);

CREATE TABLE LIBRARY.BOOK_LOANS(
Loan_Id integer(10) NOT NULL auto_increment,
Book_Id CHAR(30) NOT NULL,
Branch_Id CHAR(20) NOT NULL,
Card_no INTEGER(20) NOT NULL,
Date_out DATE NOT NULL,
Due_date DATE NOT NULL,
Date_in DATE NULL,
PRIMARY KEY(Loan_Id),
FOREIGN KEY (Book_Id) REFERENCES BOOK (Book_Id),
FOREIGN KEY (Branch_Id) REFERENCES LIBRARY_BRANCH (Branch_Id),
FOREIGN KEY (Card_no) REFERENCES BORROWER (Card_no)
);

CREATE TABLE LIBRARY.FINES(
loan_id integer(10) not null auto_increment,
fine_amt decimal(4,2) default 0.00,
paid tinyint default 0,
Primary Key(loan_id),
Foreign Key (loan_id) references book_loans(loan_id)
);
use library;


load data local infile 'C:/Users/Archit Garg.ARCHIT/Desktop/DB Project/Library_data/books_authors.csv' 
into table book 
fields terminated by '\t' 
enclosed by '"' 
lines terminated by '\n'  
ignore 1 lines 
(book_id, @dummy, title);

load data local infile 'C:/Users/Archit Garg.ARCHIT/Desktop/DB Project/Library_data/books_authors.csv' 
into table BOOK_AUTHOR
fields terminated by '\t' 
enclosed by '"' lines 
terminated by '\n'  
ignore 1 lines 
(sBook_Id, sauthor_name);

load data local infile 'C:/Users/Archit Garg.ARCHIT/Desktop/DB Project/Library_data/library_branch.csv' 
into table library_branch 
fields terminated by '\t' 
enclosed by '"' 
lines terminated by '\n'
ignore 1 lines 
(branch_id, branch_name, address);

load data local infile 'C:/Users/Archit Garg.ARCHIT/Desktop/DB Project/Library_data/book_copies.csv' 
into table book_copies 
fields terminated by '\t' 
enclosed by '"' 
lines terminated by '\n'  
ignore 1 lines 
(book_id, branch_id, no_of_copies);


load data local infile 'C:/Users/Archit Garg.ARCHIT/Desktop/DB Project/Library_data/borrowers.csv' 
into table borrower 
fields terminated by '\t' 
enclosed by '"' 
lines terminated by '\n' 
ignore 1 lines (card_no,fname,lname,@a,@b,@c,phone) set address=concat(@a,' , ',@b,' , ',@c);

load data local infile 'C:/Users/Archit Garg.ARCHIT/Desktop/DB Project/Library_data/book_loans.csv' 
into table book_loans 
fields terminated by ',' 
enclosed by '"' 
lines terminated by '\n' 
ignore 1 lines (loan_id,Book_id,Branch_id,card_no,date_out,due_date,date_in);


DELIMITER $$

DROP PROCEDURE IF EXISTS explode_table $$
CREATE PROCEDURE explode_table(bound VARCHAR(255))

  BEGIN

    DECLARE sbook_id Text;
    DECLARE sauthor_name TEXT;
    DECLARE occurance INT DEFAULT 0;
    DECLARE i INT DEFAULT 0;
    DECLARE splitted_author_name TEXT;
    DECLARE done INT DEFAULT 0;
    DECLARE cur1 CURSOR FOR SELECT library.book_author.sbook_id, book_author.sauthor_name
                                         FROM library.book_author
                                         WHERE library.book_author.sauthor_name != '';
     

DROP TABLE IF EXISTS book_authors;
    CREATE TABLE book_authors(
    Book_Id CHAR(10) NOT NULL,
Author_name VARCHAR(100) NOT NULL,
Fname VARCHAR(20) ,
Minit VARCHAR(20) ,
Lname VARCHAR(20) ,
PRIMARY KEY(Book_Id, Author_name),
FOREIGN KEY (Book_Id) REFERENCES BOOK (Book_Id)
    ) ENGINE=Memory;

    OPEN cur1;
      read_loop: LOOP
        FETCH cur1 INTO sbook_id, sauthor_name;
        IF done THEN
          LEAVE read_loop;
        END IF;

        SET occurance = (SELECT LENGTH(sauthor_name)
                                 - LENGTH(REPLACE(sauthor_name, bound, ''))
                                 +1);
        SET i=1;
        WHILE i <= occurance DO
          SET splitted_author_name =
          (SELECT REPLACE(SUBSTRING(SUBSTRING_INDEX(sauthor_name, bound, i),
          LENGTH(SUBSTRING_INDEX(sauthor_name, bound, i - 1)) + 1), ',', ''));

          INSERT INTO book_authors (book_id,author_name) values (sbook_id, trim(both ' ' from splitted_author_name));
          SET i = i + 1;

        END WHILE;
      END LOOP;

      SELECT * FROM book_authors;
    CLOSE cur1;
  END; $$

delimiter ;

CALL explode_table(',');

Update book_authors set minit = Null where minit = ' '; 
Update book_authors set fname = Null , minit = Null , lname = Null where author_name = 'Various'; 
Update book_authors set fname = Null , minit = Null , lname = Null where author_name = 'The Beatles'; 
Update book_authors set fname = Null , minit = Null , lname = Null where author_name = 'Los Angeles County Museum of Art California'; 
Update book_authors set lname = Null where author_name = 'Preiss-Glasser';









update book_authors set Minit = NULL where Minit = " ";

Update book_authors set fname = trim(both ' ' from substring_index(ltrim(author_name),' ',1)) ;

Update book_authors set lname = trim(both ' ' from substring_index(substring_index(author_name,' (', 1),' ',-1)); Update book_authors set minit = trim(both ' ' from substring_index(substring_index(trim(lname from (trim(fname from ltrim(author_name)))),' (',-1),')',-1));

