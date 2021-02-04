# Studypoint 3 semester

Ugernes opgaver er lagt ind i mapper, som findes under hver uge.

## Week 1
* Day 2: Et mindre projekt med JPA hvor jeg får forbindelse til minDB. Jeg har øvet mig på at få dataen til at se pæn ud i DB
* Day 3: Et mindre projekt med REST. Jeg har øvet mig i at bruge Gson på to forskellige måder, da Java klager lidt over den ene metode hvor Gson er statik -> kræver en clean og build for at virke
* Day 4: Et større projekt hvor jeg kombinerer REST med JPA. Dette gav lidt problemer da jeg bruger JDK14 Java. Dog fik jeg det til at virke med dep. Selve Deploy til server manglede instrukser, men fandt ud af at localhost ikke virker med MySql når man connecter til Docker. Her skal man connecte til docker container. Jeg har især fået forståelsen af JSON og hvordan jeg bruger endpoints til at vise den data jeg får ud af min DB via ORM
* Day 5: Prøvede den nye git temp file som vi skal bruge. Den virkede ok. Manglede lidt dep. men ellers virkede alt. Dog skal jeg huke at genstarte docker container når jeg laver en ny DB connection via .yml. Et rigtig godt emne var DTO, som jeg kan bruge til at "sikre" den data jeg sender ud via mit. DTO giver mig nemlig friheden til at vælge hvilken data jeg vil dele med andre :)