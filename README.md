Currentcy
Currentcy is an exchange rate aggregator that basically parses info from public sites belonging to official exchanges.
A list of supported exchanges can be seen here listed alphabetically:
1. Abitab (Cambilex)  http://www.cambilex.com.uy
2. Banco Central del Uruguay  http://www.bcu.gub.uy
3. Banco República  http://www.brou.com.uy
4. Cambio 18  http://www.cambio18.com
5. Cambio SIR http://www.cambiosir.com.uy
6. Cotización Dólar Uruguay  http://uy.cotizacion-dolar.com
7. Currency.wiki  http://currency.wiki
8. Google Finance https://www.google.com
9. Indumex  http://www.indumex.com
10. Yahoo http://finance.yahoo.com

This project is just a test ground for technology I like to learn or keep current with, and it is not intended for profit or any other purpose. I'm scratching my own itch here, and if it helps someone else, well, that´s a very nice bonus :)

This is also a test for cloud deployment. I´m using Openshift (openshift.com) from Redhat, which is free for small projects like this as long as it gets used at least once a day. this means that some configuration is externalized in environment variables.

i'm using Spring Tool Suite (Eclipse with EGit). To set this variables on a working copy of these repository:
Go to Run -> Run Configurations -> Environments

add the following variables with values that make sense to your setup:
MAIL_PASSWORD
MAIL_USERNAME
OPENSHIFT_MYSQL_DB_HOST
OPENSHIFT_MYSQL_DB_PASSWORD
OPENSHIFT_MYSQL_DB_PORT
OPENSHIFT_MYSQL_DB_USERNAME

Mail variables are needed for registering and suscribing to exchange updates. The sender must be a Gmail account (settings for GMail through SSL are hardwired, though changing those is pretty simple). All other variables are Openshift standard variables to connect to a MySQL instance.

Happy coding.


