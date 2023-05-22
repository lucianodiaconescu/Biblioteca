# Biblioteca

Acest proiect are ca scop sa ne arate toate cartile din biblioteca pentru a afla sau nu daca respectiva carte se afla in biblioteca, pentru a nu bate drumul degeaba!

Ca si functionalitati avem:

login (admin / user)
register (doar user)
Admin:

adauga o noua carte in librarie (titlu, autor si un scurt rezumat (continut))
sterge o carte existenta din librarie
sterge un user existent (delete account)
User:

cauta carti dupa numele autorului
cauta o carte dupa titlu
arata toate cartile
Proiectul este conectata la o baza de date in SQL Developer si are 2 table-uri: utilizatori si carti.

Utilizatori:

username
parola
functie (admin sau user, functia de admin poate fi adaugata doar din baza de date, iar functie de user este setata automat la register)
Carti:

titlu
autor
continut (un mic rezumat)
