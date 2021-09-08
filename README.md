# Galaxy
<B><i>Galaxy Numerals</i></B>
<p>
An application that accepts a multi-part file named 'file' that contains alien word equivalents for a
roman numeral, the amounts and prices of commodities in those alien words are translated and given
back to the user in human-readable numbers.
</p>
<p>
Also, as an added bonus we can use roman numerals and convert them to human-readable numbers
and to the contrary, the application can accept a human-readable number and gives back the roman
numeral equivalent.
</p>
<p>
The application uses a RESTFUL web interface built on Spring Boot with JUnit testing. 
</p>
<p>
To access the API, start the Spring Boot application and use the Insomnia application or the Postman application to send
a RESTFUL request to one of these three endpoint, with the proper request body (if applicable).
</p>

<h3>Translate a Mulit-Part File</h3>
http://localhost:8080/galactic/translate
<p>Send as a 'POST' request and include a multipart file in the request parameters or body of the message.
Here is a sample request: </p>

```text
wa is I
vagh is V
mah is X
vaghmah is L
wavatlh is C
vaghvatlh is D
wasad is M
wa Silver is 90 Credits
vaghmah Copper is 92 Credits
wa mah Gold is 856 Credits
how many Credits is wavatlh vaghvatlh wa' wa' Silver ?
how many Credits is mah Copper ?
how many Credits is mah vaghmah vagh Gold ?
how much is wasad vaghvatlh wavatlh vaghmah mah vagh wa ?
shouldn't this be in gold pressed latinum ?
```
<p>With this example, we should have this as the response:</p>

```text
wavatlh vaghvatlh Silver is 36000 Credits
mah Copper is 18.4 Credits
mah vaghmah vagh Gold is 4280 Credits
wasad vaghvatlh wavatlh vaghmah mah vagh wa is 1666
I have no idea what you are talking about
```
<p>Notice the last line in both the request and the response. If you ask a question that does not contain a
numerical alien word equivalent, the API will respond with the statement, 'I have no idea what you are
talking about'.</p>

<p>Capitalization and proper spacing are important for proper functionality of the API.
Questions must have a space between the last character of the last word in the sentence and the
question mark. Also, only names of the commodities, roman numerals and the word 'Credits' have
capital letters. Using a capital letters anywhere else in the file will result in errors or inaccurate
responses.</p>

<p>A note for commodities, there is no limit to the number of commodities but there are some rules.
Commodities must start with a capital letter but a statement about them (not a question) must never
repeat. Doing so will skew results.</p>

<h3>Translate from Roman Numerals</h3>
http://localhost:8080/galactic/number/{numeral}
<p>Send as a 'GET' request with the roman numeral trailing and get the numerical equivalent. Any unknown parameters will
result in either a 'Bad Request (Not a String)' or will return a '0' for un-parseable value.</p>

<h3>Translate from Number</h3>
http://localhost:8080/galactic/numeral/{number}
<p>Send as a 'GET' request with the number trailing and get the roman numeral equivalent. This trailing parameter is
parsed and a primitive 'int' and subject to all the restrictions a primitive 'int' has. Only positive numbers are valid 
and numbers to large to be a valid will result in a 'Bad Request (int parsing error)'.