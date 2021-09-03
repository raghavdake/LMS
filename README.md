
	
	1.All List of book in Library
	
	GET
	api/books
	http://localhost:9080/api/books
	Response : 
	[{"title":"Book5","author":"Mark","category":"Romance","status":"Available","borrower":"none","borrowDate":"none","returnDate":"none","bookId":"111-5","_links":{"self":{"href":"http://localhost:9080/api/book/111-5"}}},{"title":"Book4","author":"Martina","category":"Romance","status":"Available","borrower":"none","borrowDate":"none","returnDate":"none","bookId":"111-4","_links":{"self":{"href":"http://localhost:9080/api/book/111-4"}}},{"title":"Book1","author":"James","category":"Thriller","status":"Not Available","borrower":"111-1","borrowDate":"none","returnDate":"none","bookId":"111-1","_links":{"self":{"href":"http://localhost:9080/api/book/111-1"}}},{"title":"Book3","author":"Daniel","category":"Sci-Fi","status":"Not Available","borrower":"111-1","borrowDate":"none","returnDate":"none","bookId":"111-3","_links":{"self":{"href":"http://localhost:9080/api/book/111-3"}}},{"title":"Book2","author":"John","category":"Comedy","status":"Not Available","borrower":"111-1","borrowDate":"none","returnDate":"none","bookId":"111-2","_links":{"self":{"href":"http://localhost:9080/api/book/111-2"}}}]


	2.Ability to add books to the system.

	POST
	api/books
	
	http://localhost:9080/api/book/add
	
	Ex- 
	{
        "bookid": "111-5",
	 	"title": "DotNet",
	 	"author": "John Daves", 	
	 	"category": "Technology",
	 	"status": "Available",
	 	"borrower": "none",
	 	"borrowDate": "none",
	 	"returnDate": "none"
	        
	}
	
	3.Ability to delete books from the system.

	DELETE
	http://localhost:9080/api/book/delete/{bookid}

	4.Ability to lend books to users.

	GET
	API End point http://localhost:9080/api/lendbook/{userid}/{bookid}
	--Response : 
	{"userid":"111-1","name":"USER1","address":"DUBLIN","book1issue":"Book1","book2issue":"none","book3issue":"none","book1return":"none","book2return":"none","book3return":"none","lentBookCount":2,"status":"none"}


	5.Ability to return books to the library.

	GET
	API End point http://localhost:9080/api/returnbook/{userid}/{bookid}
	
	Response : 
	Listing all the books 
	[{"title":"Book5","author":"Mark","category":"Romance","status":"Available","borrower":"none","borrowDate":"none","returnDate":"none","bookId":"111-5"},{"title":"Book4","author":"Martina","category":"Romance","status":"Available","borrower":"none","borrowDate":"none","returnDate":"none","bookId":"111-4"},{"title":"Book1","author":"James","category":"Thriller","status":"Available","borrower":"none","borrowDate":"none","returnDate":"none","bookId":"111-1"},{"title":"Book3","author":"Daniel","category":"Sci-Fi","status":"Not Available","borrower":"111-1","borrowDate":"none","returnDate":"none","bookId":"111-3"},{"title":"Book2","author":"John","category":"Comedy","status":"Not Available","borrower":"111-1","borrowDate":"none","returnDate":"none","bookId":"111-2"}]
	

	6.Ability to limit the number of books borrowed by user.

	"Max 3  books can borrowed by per user.";
	if user tries to add more then 3 books, "Max 3  books can borrowed by per user" will be displayed in status
	Ex : 
	{"userid":"111-1","name":"USER1","address":"DUBLIN","book1issue":"none","book2issue":"none","book3issue":"none","book1return":"none","book2return":"none","book3return":"none","lentBookCount":4,"status":"Max 3  books can borrowed by per user"}
	
	7.If Book is already lentout 
	http://localhost:9080/api/lendbook/111-2/111-2
	Response : 
	{"userid":"111-2","name":"USER2","address":"CORK","book1issue":"none","book2issue":"none","book3issue":"none","book1return":"none","book2return":"none","book3return":"none","lentBookCount":2,"status":"This book is already lent out. Sorry."}


--This all could have done in a better way using in-memory databse or using any RDBMS.

Thanks.
 