Am implementat o aplicatie REST cu cele doua endpoint-uri precizate in document.

Implementarea lor se gaseste in clasele Format si Ngram.

Abordare Format:
- se imparte body-ul intr-un array de Stringuri
- se cauta tokenul de inceput si se extrag elementele dupa formatul precizat
- intr-un StringBuilder se adauga elementele una cate una
- cand s-a terminat extragerea se cauta din nou tokenul de inceput pana cand am procesat toate elementele

Abordare Ngram:
- se imparte body-ul intr-un array de Stringuri
- se selecteaza lungimea curenta a ngram-ului (n)
- intr-un StringBuilder se genereaza toate ngram-urile de lungimea n
- se adauga intr-un HashMap ngram-ul curent cu maparea ngram -> nr de aparitii
- dupa ce s-au creat toate ngram-urile de lungime n, se sorteaza rezultatul descrescator dupa numarul de aparitii
- se continua cu urmatoarea lungime de ngram (n-1)
- complexitatea este O(l * log l), unde l e numarul de cuvinte din body, deoarece avem 0 < n <=3 (constant) iar generarea de ngramuri e O(l) iar inserarea in HashMap e constanta, iar sortarea lor e O(l * log l)

In aplicatie am activat POJOMappingFeature pentru a transforma body-ul requestului in JSON si invers. Obiectele care ma ajuta in asta sunt FormatMessage, NgramMessage si NgramResponse.

Pentru /format am creat o exceptie MessageFormatException in cazul in care request-ul nu are formatul precizat in enunt.

Unit testele se afla in FormatTest si NgramTest in directorul test.

Dependintele externe le-am importat cu Maven.

Inbunatatiri:

- s-ar putea adauga suport pentru caractere speciale in afara Unicode, care in Java se fac cu "supplementary characters"
- pentru load-uri mari (milioane de requesturi) ar fi necesar un sistem distribuit, in care fiecare nod sa ruleze business logic-ul si request-urile sa fie distribuite de un load balancer
- daca request-urile se repeta se poate crea un sistem de caching
- la nivel de aplicatie se poate crea un Queue de mesaje care sa fie deservit de un ThreadPool cu un numar de threaduri care deservesc requesturile cum vin ele.

