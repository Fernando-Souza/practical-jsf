CREATE sequence IF NOT EXISTS  translation
INCREMENT 1
MINVALUE 1 
MAXVALUE 2147483647
START 1
OWNED by catgorytranslation.catid ;

CREATE sequence IF NOT EXISTS  books
INCREMENT 1
MINVALUE 1 
MAXVALUE 2147483647
START 1
OWNED by mapbookcategory.bookid ;

CREATE sequence IF NOT EXISTS  books
INCREMENT 1
MINVALUE 1 
MAXVALUE 2147483647
START 1
OWNED by catgorytranslation.catid ;